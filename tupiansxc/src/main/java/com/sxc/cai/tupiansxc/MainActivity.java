package com.sxc.cai.tupiansxc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private ImageView imageView;
    private Handler handler = new Handler();
    private int images[] = {R.drawable.sxc1,R.drawable.sxc2,R.drawable.sxc3,R.drawable.sxc4};
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        index = index%(images.length);
                        imageView.setImageResource(images[index]);
                    }
                },2000);
            }
        }).start();
    }
}
