package com.mahmoudmabrok.azakri.util;

import android.content.Context;
import android.content.Intent;

public class Util {


    public static void share(Context ctx, String item) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, item);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        ctx.startActivity(shareIntent);

    }

}
