package com.sxc.cai.weather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.sxc.cai.weather.db.WeatherDb;
import com.sxc.cai.weather.model.City;
import com.sxc.cai.weather.model.County;
import com.sxc.cai.weather.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by cai on 2015/10/7.
 */
public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(WeatherDb weatherDb,String response){
        if (!TextUtils.isEmpty((response))){ //判断是否为空
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0){
                for (String p : allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    weatherDb.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public synchronized static boolean handleCitiesResponse(WeatherDb weatherDb,String response,
                                                             int provinceId){
        if (!TextUtils.isEmpty((response))){ //判断是否为空
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0){
                for (String c : allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    weatherDb.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public synchronized  static boolean handleCountiesResponse(WeatherDb weatherDb,String response,
                                                             int cityId){
        if (!TextUtils.isEmpty((response))){ //判断是否为空
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    weatherDb.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的JSON数据，并将解析出的数据存储到本地
     */
    public static void handleWeatherJsonResponse(Context context,String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            String wendu = data.getString("wendu");
            JSONArray forecast = data.getJSONArray("forecast");
            JSONObject weather0 = forecast.getJSONObject(0);
            String high = weather0.getString("high");
            String type = weather0.getString("type");
            String low = weather0.getString("low");
            String date = (weather0.getString("date")).replaceAll("天","日");
            JSONObject weather1 = forecast.getJSONObject(1);
            String high1 = weather1.getString("high");
            String type1 = weather1.getString("type");
            String low1 = weather1.getString("low");
            String date1 = (weather1.getString("date")).replaceAll("天","日");
            JSONObject weather2 = forecast.getJSONObject(2);
            String high2 = weather2.getString("high");
            String type2 = weather2.getString("type");
            String low2 = weather2.getString("low");
            String date2 = (weather2.getString("date")).replaceAll("天","日");
            JSONObject weather3 = forecast.getJSONObject(3);
            String high3 = weather3.getString("high");
            String type3 = weather3.getString("type");
            String low3 = weather3.getString("low");
            String date3 = (weather3.getString("date")).replaceAll("天","日");
            JSONObject weather4 = forecast.getJSONObject(4);
            String high4 = weather4.getString("high");
            String type4 = weather4.getString("type");
            String low4 = weather4.getString("low");
            String date4 = (weather4.getString("date")).replaceAll("天","日");

            JSONObject yesterday = data.getJSONObject("yesterday");
            String high0 = yesterday.getString("high");
            String type0 = yesterday.getString("type");
            String low0 = yesterday.getString("low");
            String date0 = (yesterday.getString("date")).replaceAll("天","日");

//            String aqi = data.getString("aqi");
            String city = data.getString("city");

            saveWeatherInfo(context,wendu,high,type,low,date,high1,type1,low1,date1,high2,type2,low2,date2,
                    high3,type3,low3,date3,high4,type4,low4,date4,high0,type0,low0,date0,city);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析服务器返回的XML数据，并将解析出的数据存储到本地（弃用）
     */
//    public static void handleWeatherXmlResponseNow(Context context,String response){
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser xmlPullParser = factory.newPullParser();
//            xmlPullParser.setInput(new StringReader(response));
//            int eventType = xmlPullParser.getEventType();
//            String city = "";
//            String updatetime = "";
//            String wendu = "";
//            String shidu = "";
//            String sunrise = "";
//            String sunset = "";
//            String aqi = "";
//            String pm25 = "";
//            String quality = "";
//            String pm10 = "";
//            while (eventType != XmlPullParser.END_DOCUMENT){
//                String nodeName = xmlPullParser.getName();
//                switch (eventType){
//                    case XmlPullParser.START_TAG: {
//                        if ("city".equals(nodeName)){
//                            city = xmlPullParser.nextText();
//                        }else if (("updatetime".equals(nodeName))){
//                            updatetime = xmlPullParser.nextText();
//                        }else if ("wendu".equals(nodeName)){
//                            wendu = xmlPullParser.nextText();
//                        }else if ("shidu".equals(nodeName)){
//                            shidu = xmlPullParser.nextText();
//                        }else if ("sunrise".equals(nodeName)){
//                            sunrise = xmlPullParser.nextText();
//                        }else if ("sunset".equals(nodeName)) {
//                            sunset = xmlPullParser.nextText();
//                        }else if ("aqi".equals(nodeName)) {
//                            aqi = xmlPullParser.nextText();
//                        }else if ("pm25".equals(nodeName)) {
//                            pm25 = xmlPullParser.nextText();
//                        }else if ("quality".equals(nodeName)) {
//                            quality = xmlPullParser.nextText();
//                        }else if ("pm10".equals(nodeName)) {
//                            pm10 = xmlPullParser.nextText();
//                        }
//                        break;
//                    }
//                    case XmlPullParser.END_TAG: {
//                        if ("sunset_2".equals(nodeName)) {
//                            saveWeatherXmlInfo(context, city, updatetime, wendu, shidu, sunrise, sunset, aqi,
//                                    pm25, quality, pm10);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 将服务器返回的所有天气信息存储到SharedPreferences文件中
     */
    public static void saveWeatherInfo(Context context,String wendu,String high,String type,String low,String date,
                                       String high1,String type1,String low1,String date1,String high2,String type2,
                                       String low2,String date2,String high3,String type3,String low3,String date3,
                                       String high4,String type4,String low4,String date4,String high0,String type0,
                                       String low0,String date0,String city){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("wendu", wendu);
        editor.putString("high", high);
        editor.putString("type", type);
        editor.putString("low", low);
        editor.putString("date", date);
        editor.putString("high1", high1);
        editor.putString("type1", type1);
        editor.putString("low1", low1);
        editor.putString("date1", date1);
        editor.putString("high2", high2);
        editor.putString("type2", type2);
        editor.putString("low2", low2);
        editor.putString("date2", date2);
        editor.putString("high3", high3);
        editor.putString("type3", type3);
        editor.putString("low3", low3);
        editor.putString("date3", date3);
        editor.putString("high4", high4);
        editor.putString("type4", type4);
        editor.putString("low4", low4);
        editor.putString("date4", date4);
        editor.putString("high0", high0);
        editor.putString("type0", type0);
        editor.putString("low0", low0);
        editor.putString("date0", date0);
//        editor.putString("aqi", aqi);
        editor.putString("city", city);
        editor.putString("current_date",sdf.format(new Date()));
        editor.commit();
    }
}
