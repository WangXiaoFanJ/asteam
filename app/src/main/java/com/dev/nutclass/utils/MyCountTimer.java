package com.dev.nutclass.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/1.
 */
public class MyCountTimer extends CountDownTimer {
    private TextView timerTv;
    public MyCountTimer(long millisInFuture, long countDownInterval,TextView timerTv) {
        super(millisInFuture, countDownInterval);
        this.timerTv = timerTv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timerTv.setEnabled(false);
        long second = millisUntilFinished / 1000;
        String tick = second + "";
        if (second < 10) {
            tick = "0" + tick;
        }
        timerTv.setText(tick + "秒重试");
    }

    @Override
    public void onFinish() {
        timerTv.setEnabled(true);
        timerTv.setText("重新发送");
    }
}
