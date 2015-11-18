package com.sxc.cai.weather.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by cai on 2015/10/12.
 * 全局获取Context
 */
public class MyApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
