package com.tana1420.mybase.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

    public static void touchOutSide(View view, Context context) {


        if (!(view instanceof EditText)) {
            // Set up touch listener for non-text box views to hide keyboard.
            view.setOnTouchListener((v, event) -> {
                hideSoftKeyBoards(view, context);
                return false;
            });
        }


        if (view instanceof ViewGroup) {
            //If a layout container, iterate over children and seed recursion.
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                touchOutSide(innerView, context);
            }
        }
    }
    private static void hideSoftKeyBoards(View v, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
