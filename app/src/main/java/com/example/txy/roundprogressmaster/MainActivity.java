package com.example.txy.roundprogressmaster;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends Activity {

    private int totalProgress;
    private RoundProgress rp_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rp_home = (RoundProgress)findViewById(R.id.rp_home);
        totalProgress = 90;
//        让当前的进度条动态的加载显示
        new Thread(new Runnable() {
            @Override
            public void run() {
                rp_home.setMax(100);
                rp_home.setProgress(0);
                for(int i = 0; i < totalProgress; i++) {
                    rp_home.setProgress(rp_home.getProgress() + 1);
                    SystemClock.sleep(30);
//                    强制重绘 Use this to invalidate the View from a non-UI thread
                    rp_home.postInvalidate();
                }
            }
        }).start();
    }
}
