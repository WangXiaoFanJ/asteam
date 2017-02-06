package com.dev.nutclass.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/1/6.
 */
public class DialogUtils {
    public static void showToast(Context context, CharSequence msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }
    public static void showToast(Context context, CharSequence msg, int duration) {
        Toast.makeText(context, msg, duration).show();
    }
}
