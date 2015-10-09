package com.sxc.cai.weather.util;

/**
 * Created by cai on 2015/10/7.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
