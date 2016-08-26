package com.hnkntoc.navigationdrawerv2.logic;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.activity.view.ViewActivity;
import com.hnkntoc.navigationdrawerv2.model.SchedulesData;

import org.w3c.dom.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Используется для доступа к SchedulesData из всех ViewActivity.
 */
public class Model {
    private static final String TAG = Model.class.getName();
    private static Model model;
    private static List<ViewActivity> viewList = new ArrayList<>();
    private SchedulesData schedulesData;

    private Model() {
        Log.i(TAG, "Create!");
        for (ViewActivity viewActivity : viewList) {
            if (viewActivity != null) {
                Document document = viewActivity.renewDocument();
                if (document == null) {
                    document = parsingDocument(viewActivity.getActivity());
                }
                schedulesData = new SchedulesData(document);
                Log.i(TAG, "Create successfully!");
                return;
            }
        }
    }

    private Document parsingDocument(Activity activity) {
        Log.i(TAG, "parsingDocument()");
        try {
            return LessonHelper.parsingHTML(activity.getResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "parsingDocument() failed!");
        return null;
    }

    public SchedulesData getSchedulesData() {
        return schedulesData;
    }

    /**
     * Подключает ViewActivity к Model.
     *
     * @param view которое подключается.
     * @return Model к которому подключилась ViewActivity.
     */
    public static Model connect(ViewActivity view) {
        Log.i(TAG, "connect() " + view);
        if (view != null) {
            viewList.add(view);
        }
        return getModel();
    }

    @NonNull
    private static Model getModel() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public void disconnect(ViewActivity view) {
        Log.i(TAG, "disconnect() " + view);
        viewList.remove(view);
        if (viewList.isEmpty()) {
            view.saveSchedulesData(schedulesData.getDocument());
            clear();
        }
    }

    private void clear() {
        model = null;
        viewList = null;
        schedulesData = null;
    }
}
