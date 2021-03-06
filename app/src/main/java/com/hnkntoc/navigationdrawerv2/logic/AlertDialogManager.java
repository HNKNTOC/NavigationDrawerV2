package com.hnkntoc.navigationdrawerv2.logic;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.hnkntoc.navigationdrawerv2.R;

/**
 * Для удобной работы с AlertDialog.
 */
public class AlertDialogManager {
    private static final String TAG = AlertDialogManager.class.getName();

    /**
     * Показать сообщение об ошибке.
     *
     * @param context Context который должен показать сообщение.
     * @param e       Ошибка которую нужно показать.
     */
    public static void showAlertDialogError(Context context, Exception e) {
        Log.w(TAG, "showAlertDialogError()", e);
        AlertDialogFactory.createAlertDialogError(context, e).show();
    }


    /**
     * Фабрика для создания AlertDialog.
     */
    private static class AlertDialogFactory {
        /**
         * Создание сообщение об ошибке.
         *
         * @param context Context который должен показать сообщение.
         * @param e       Ошибка которую нужно показать.
         */
        public static AlertDialog createAlertDialogError(Context context, Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.error)
                    .setMessage(e.getMessage())
                    .setIcon(R.drawable.error)
                    .setCancelable(false)
                    .setNegativeButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            return builder.create();
        }
    }

}
