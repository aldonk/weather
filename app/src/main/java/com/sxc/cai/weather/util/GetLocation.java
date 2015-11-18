package com.sxc.cai.weather.util;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by cai on 2015/10/26.
 */
public class GetLocation {

    private static String provider;

    private static Location location;

    private static String countyname;

    private static LocationManager locationManager =
            (LocationManager)MyApplication.getContext().getSystemService(MyApplication.getContext().LOCATION_SERVICE);
    public static LocationManager getLocationManager(){
        return locationManager;
    }
    public static Location getNowLocation(){
        //定位当前所处城市位置
//        locationManager =
//                (LocationManager)MyApplication.getContext().getSystemService(MyApplication.getContext().LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(locationManager.GPS_PROVIDER)){
            provider = locationManager.GPS_PROVIDER;
        }else if (providerList.contains(locationManager.NETWORK_PROVIDER)){
            provider = locationManager.NETWORK_PROVIDER;
        }else {
            //当没有可用的位置提供器时，弹出提示
            Toast.makeText(MyApplication.getContext(), "没有定位权限", Toast.LENGTH_SHORT).show();
            return null;
        }
        location = locationManager.getLastKnownLocation(provider);
//        if (location != null){
//            return location;
//        }


        locationManager.requestLocationUpdates(provider, 1, 1, locationListener);
        Log.e("地址Location",location+"");
//        String address = "http://api.map.baidu.com/geocoder?output=json&location=" +
//                location.getLatitude() + "," + location.getLongitude() + "&key=aHoAKXaHxtvd7TnRgqo1lejM";
//        Log.e("第1步","a");
//        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//            @Override
//            public void onFinish(String response) {
//                if (!TextUtils.isEmpty(response)) {
//                    try {
//                        Log.e("第2步","a");
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONObject jsonObject1 = jsonObject.getJSONObject("result");
//                        JSONObject jsonObject2 = jsonObject1.getJSONObject("addressComponent");
//                        Log.e("第3步","a");
//                        String str = jsonObject2.getString("city");
//                        countyname = str.substring(0, str.length() - 1);
//                        Log.e("第4步",countyname);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        return location;
    }
    static LocationListener locationListener = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {
//            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    };
    public static LocationListener getLocationListener(){
        return locationListener;
    }
}
