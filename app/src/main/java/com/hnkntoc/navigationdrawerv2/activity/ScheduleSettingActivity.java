package com.hnkntoc.navigationdrawerv2.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hnkntoc.navigationdrawerv2.R;
import com.hnkntoc.navigationdrawerv2.logic.LessonHelper;
import com.parsingHTML.logic.ParsingHTML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ScheduleSettingActivity extends AppCompatActivity {
    private static final String TAG = ScheduleSettingActivity.class.getName();
    private static final String startURL = "http://rasp.bukep.ru/Default.aspx";
    private Document timeDoc = null;
    private Document lessonDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_description);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick FloatingActionButton. timeDoc = " + timeDoc + " lessonDoc = " + lessonDoc);
                saveDOC(LessonHelper.parsingHTML(timeDoc, lessonDoc));
            }
        });

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(startURL);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onConsoleMessage(ConsoleMessage cmsg) {
                Log.d(TAG, "onConsoleMessage() =  " + cmsg);
                // check secret prefix
                if (cmsg.message().startsWith("MAGIC")) {
                    String msg = cmsg.message().substring(5); // strip off prefix
                    if (ParsingHTML.checkSchedules(msg)) {
                        Log.d(TAG, "This is Schedules!! ");
                        lessonDoc = Jsoup.parse(msg);
                        fab.show();
                    } else {
                        fab.hide();
                        Log.d(TAG, "This is NOT Schedules!! ");
                    }

                    Document parse = Jsoup.parse(msg);
                    if (parse.select(".knock").size() > 0) {
                        Log.d(TAG, "This is Time!! ");
                        timeDoc = parse;
                    } else {
                        Log.d(TAG, "This is NOT Time!! ");
                    }

                    return true;
                }

                return false;
            }
        });
        webView.setWebViewClient(new MyWebViewClient());
    }

    public void saveDOC(org.w3c.dom.Document document) {
        Log.i(TAG, "saveDOC()");
        try {
            File file = new File(getFilesDir().getAbsolutePath(), "Schedules.xml");
            Log.i(TAG, "File path = " + file.getAbsolutePath());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(file);
            Source input = new DOMSource(document);

            transformer.transform(input, output);
        } catch (TransformerException e) {
            Log.e(TAG, "Failed saveDOC()");
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }


    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d(TAG, "shouldOverrideUrlLoading() request = " + request);
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "onPageStarted() url = " + url + " favicon = " + favicon);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished() url = " + url);
            view.loadUrl("javascript:console.log('MAGIC'+document.getElementsByTagName('html')[0].innerHTML);");
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d(TAG, "onLoadResource() url = " + url);
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            Log.d(TAG, "onPageCommitVisible() url = " + url);
            super.onPageCommitVisible(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Log.d(TAG, "onReceivedError() request = " + request + " error = " + error);
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.d(TAG, "onReceivedHttpError() request = " + request + " errorResponse = " + errorResponse);
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            Log.d(TAG, "doUpdateVisitedHistory() url = " + url + " isReload = " + isReload);
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            Log.d(TAG, "onFormResubmission() dontResend = " + dontResend + " resend = " + resend);
            super.onFormResubmission(view, dontResend, resend);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Log.d(TAG, "onReceivedSslError() handler = " + handler + " error = " + error);
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
            Log.d(TAG, "onReceivedClientCertRequest() request = " + request);
            super.onReceivedClientCertRequest(view, request);
        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            Log.d(TAG, "onReceivedHttpAuthRequest() handler = " + handler + " host = " + host + "realm = " + realm);
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            Log.d(TAG, "shouldOverrideKeyEvent() event = " + event);
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            Log.d(TAG, "onUnhandledKeyEvent() event = " + event);
            super.onUnhandledKeyEvent(view, event);
        }

        @Override
        public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
            Log.d(TAG, "onReceivedLoginRequest() realm = " + realm + " account = " + account + "args = " + args);
            super.onReceivedLoginRequest(view, realm, account, args);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            Log.d(TAG, "onScaleChanged() oldScale = " + oldScale + " newScale = " + newScale);
            super.onScaleChanged(view, oldScale, newScale);
        }
    }
}
