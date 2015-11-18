package com.sxc.cai.weather.util;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by cai on 2015/11/6.
 */
public class LocationName {
    public static String countyName;
    public synchronized static String queryLocationCity(final String address) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("addressComponent");
                        String str = jsonObject2.getString("city");
                        countyName = str.substring(0, str.length() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return countyName;
    }

}