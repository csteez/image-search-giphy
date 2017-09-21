package com.example.cstead.giphysearch;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chstead on 9/17/17.
 */

public class GiphyToast {
    private static Toast mToast;

    public static void showToast(Context context, String message) {
        if (mToast == null || !mToast.getView().isShown()) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void killToast() {
        if (mToast != null ) {
            mToast.cancel();
        }
    }
}
