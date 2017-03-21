package com.dev.nutclass.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
    public static void cellPhoneDialog(final Context mContext, final String phone){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(phone);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + phone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}
