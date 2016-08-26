package com.hnkntoc.navigationdrawerv2.activity.view;

import android.app.Activity;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Предоставляет функционал для Model.
 */
public class ViewActivity {
    private static final String TAG = ViewActivity.class.getName();
    private Activity activity;

    public ViewActivity(Activity activity) {
        this.activity = activity;
        Log.d(TAG, "Create activity = " + activity);
    }

    public void saveSchedulesData(Document document) {
        Log.i(TAG, "saveSchedulesData()");
        try {
            File file = new File(activity.getFilesDir().getAbsolutePath(), "Schedules.xml");
            Log.i(TAG, "File path = " + file.getAbsolutePath());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(file);
            Source input = new DOMSource(document);

            transformer.transform(input, output);
        } catch (TransformerException e) {
            Log.e(TAG, "Failed saveSchedulesData()");
            e.printStackTrace();
        }
    }

    public Document renewDocument() {
        Log.i(TAG, "renewDocument()");
        try {
            File file = new File(activity.getFilesDir(), "Schedules.xml");
            if (!file.exists()) {
                Log.i(TAG, "File not exists!");
                return null;
            }
            Log.i(TAG, "File path = " + file.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Log.e(TAG, "Failed renewDocument()", e);
        }
        return null;
    }

    public Activity getActivity() {
        Log.d(TAG, "getActivity()");
        return activity;
    }
}
