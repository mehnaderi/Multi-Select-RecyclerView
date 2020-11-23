package com.merrix.multiselectrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class GeneralFunctions {
    public static int getWidthInDP(Activity activity)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);

        int height = Math.round(displayMetrics.heightPixels / displayMetrics.density);

        int width = Math.round(displayMetrics.widthPixels / displayMetrics.density);
        return width;
    }

}
