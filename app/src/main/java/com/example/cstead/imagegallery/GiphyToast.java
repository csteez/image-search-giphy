package com.example.cstead.imagegallery;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chstead on 9/17/17.
 */

public class GiphyToast {
    private static Toast mToast;
    private static final String NO_CONTENT = "No more content :(";

    public static void showToast(Context context) {
        if (mToast == null || !mToast.getView().isShown()) {
            mToast = Toast.makeText(context, NO_CONTENT, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void killToast() {
        if (mToast != null ) {
            mToast.cancel();
        }
    }
}
