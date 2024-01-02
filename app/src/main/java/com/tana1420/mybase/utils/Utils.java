package com.tana1420.mybase.utils;

import android.view.Window;
import android.view.WindowManager;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class Utils {

    // Hide systemBars
    public static void hideSystemBars(Window window) {
        WindowInsetsControllerCompat wicc = ViewCompat.getWindowInsetsController(window.getDecorView());
        if (wicc == null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            return;
        }
        // Configure the behavior of the hidden system bars
        wicc.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        // Hide both the status bar and the navigation bar
        wicc.hide(WindowInsetsCompat.Type.systemBars());
    }
}
