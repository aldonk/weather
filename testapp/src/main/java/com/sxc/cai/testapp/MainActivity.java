package com.sxc.cai.testapp;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements ReFlashLinearLayout.IReFlashListener{

    private TextView textView;
    private ReFlashLinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.test);
        linearLayout = (ReFlashLinearLayout)findViewById(R.id.linearlayout);
        linearLayout.setInterface(this);
    }

    @Override
    public void onReFlash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("刷新完毕");
                linearLayout.reflashComplete();
            }
        },2000);

    }
}
