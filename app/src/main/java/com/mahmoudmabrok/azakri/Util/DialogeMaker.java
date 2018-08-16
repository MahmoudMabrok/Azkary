package com.mahmoudmabrok.azakri.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogeMaker {

    public static AlertDialog makeDialog(Context context, String message,
                                         String positiveStr, DialogInterface.OnClickListener positiveListener,
                                         String negativeStr, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setMessage(message)
                .setPositiveButton(positiveStr, positiveListener)
                .setNegativeButton(negativeStr, negativeListener)
                .setCancelable(true)
                .create();
    }

    public static AlertDialog makeDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setMessage(message)
                .setTitle(title)
                .setCancelable(true)
                .create();
    }


}
