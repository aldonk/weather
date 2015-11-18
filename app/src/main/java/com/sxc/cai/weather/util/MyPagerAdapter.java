package com.sxc.cai.weather.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by cai on 2015/11/2.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> y_list;
    private View currentView;
    private String name;
    private String city;
    private String high;
    private String low;
    private String wendu;
    private String type;
//    private List<String> titleList;
    public MyPagerAdapter(List<View> viewList,List<String> y_list){
        this.viewList = viewList;
//        this.titleList = titleList;
        this.y_list = y_list;
    }
    /**
     *返回的是页卡的数量
     */
    public int getCount() {
        return viewList.size();
    }

    /**
     * View是否来自于对象
     */
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 实例化一个页卡
     */
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
//        name = y_list.get(position);
//        String urlCountyName = "";
//        try {
//            urlCountyName = URLEncoder.encode(name, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String address = "http://wthrcdn.etouch.cn/weather_mini?city=" + urlCountyName;
//        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//            @Override
//            public void onFinish(final String response) {
//
//                try {
//                    Log.e("自己的adapter中",name);
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    wendu = data.getString("wendu");
//                    JSONArray forecast = data.getJSONArray("forecast");
//                    JSONObject weather0 = forecast.getJSONObject(0);
//                    Log.e("温度",wendu);
//                    high = weather0.getString("high");
//                    type = weather0.getString("type");
//                    low = weather0.getString("low");
//                    String date = (weather0.getString("date")).replaceAll("天", "日");
//                    JSONObject weather1 = forecast.getJSONObject(1);
//                    String high1 = weather1.getString("high");
//                    String type1 = weather1.getString("type");
//                    String low1 = weather1.getString("low");
//                    String date1 = (weather1.getString("date")).replaceAll("天", "日");
//                    JSONObject weather2 = forecast.getJSONObject(2);
//                    String high2 = weather2.getString("high");
//                    String type2 = weather2.getString("type");
//                    String low2 = weather2.getString("low");
//                    String date2 = (weather2.getString("date")).replaceAll("天", "日");
//                    JSONObject weather3 = forecast.getJSONObject(3);
//                    String high3 = weather3.getString("high");
//                    String type3 = weather3.getString("type");
//                    String low3 = weather3.getString("low");
//                    String date3 = (weather3.getString("date")).replaceAll("天", "日");
//                    JSONObject weather4 = forecast.getJSONObject(4);
//                    String high4 = weather4.getString("high");
//                    String type4 = weather4.getString("type");
//                    String low4 = weather4.getString("low");
//                    String date4 = (weather4.getString("date")).replaceAll("天", "日");
//
//                    JSONObject yesterday = data.getJSONObject("yesterday");
//                    String high0 = yesterday.getString("high");
//                    String type0 = yesterday.getString("type");
//                    String low0 = yesterday.getString("low");
//                    String date0 = (yesterday.getString("date")).replaceAll("天", "日");
//
//                    city = data.getString("city");
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
////        queryMiniWeather(name);
////        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
////        TextView textView = (TextView)viewList.get(position).findViewById(R.id.text);
////        textView.setText("现在界面");
//        ((TextView) viewList.get(position).findViewById(R.id.city_name)).setText(city);
//        ((TextView) viewList.get(position).findViewById(R.id.weather_wendu)).setText(wendu);
//        ((TextView) viewList.get(position).findViewById(R.id.temp1)).setText(low);
//        ((TextView) viewList.get(position).findViewById(R.id.temp2)).setText(high);
//        ((TextView) viewList.get(position).findViewById(R.id.weather_desp)).setText(type);

        return viewList.get(position);
    }

    /**
     * 销毁一个页卡
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position,final Object object) {
        super.setPrimaryItem(container, position, object);
//        name = y_list.get(position);
//        String urlCountyName = "";
//        try {
//            urlCountyName = URLEncoder.encode(name, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String address = "http://wthrcdn.etouch.cn/weather_mini?city=" + urlCountyName;
//        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//            @Override
//            public void onFinish(final String response) {
//
//                try {
//                    Log.e("自己的adapter中", name);
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONObject data = jsonObject.getJSONObject("data");
//                    wendu = data.getString("wendu");
//                    JSONArray forecast = data.getJSONArray("forecast");
//                    JSONObject weather0 = forecast.getJSONObject(0);
//                    Log.e("温度", wendu);
//                    high = weather0.getString("high");
//                    type = weather0.getString("type");
//                    low = weather0.getString("low");
//                    String date = (weather0.getString("date")).replaceAll("天", "日");
//                    JSONObject weather1 = forecast.getJSONObject(1);
//                    String high1 = weather1.getString("high");
//                    String type1 = weather1.getString("type");
//                    String low1 = weather1.getString("low");
//                    String date1 = (weather1.getString("date")).replaceAll("天", "日");
//                    JSONObject weather2 = forecast.getJSONObject(2);
//                    String high2 = weather2.getString("high");
//                    String type2 = weather2.getString("type");
//                    String low2 = weather2.getString("low");
//                    String date2 = (weather2.getString("date")).replaceAll("天", "日");
//                    JSONObject weather3 = forecast.getJSONObject(3);
//                    String high3 = weather3.getString("high");
//                    String type3 = weather3.getString("type");
//                    String low3 = weather3.getString("low");
//                    String date3 = (weather3.getString("date")).replaceAll("天", "日");
//                    JSONObject weather4 = forecast.getJSONObject(4);
//                    String high4 = weather4.getString("high");
//                    String type4 = weather4.getString("type");
//                    String low4 = weather4.getString("low");
//                    String date4 = (weather4.getString("date")).replaceAll("天", "日");
//
//                    JSONObject yesterday = data.getJSONObject("yesterday");
//                    String high0 = yesterday.getString("high");
//                    String type0 = yesterday.getString("type");
//                    String low0 = yesterday.getString("low");
//                    String date0 = (yesterday.getString("date")).replaceAll("天", "日");
//
//                    city = data.getString("city");
//
//                    ((TextView) ((View) object).findViewById(R.id.city_name)).setText(city);
//                    ((TextView) ((View) object).findViewById(R.id.weather_wendu)).setText(wendu);
//                    ((TextView) ((View) object).findViewById(R.id.temp1)).setText(low);
//                    ((TextView) ((View) object).findViewById(R.id.temp2)).setText(high);
//                    ((TextView) ((View) object).findViewById(R.id.weather_desp)).setText(type);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });

    }

    /**
     * 设置ViewPager页卡的标题
     */
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titleList.get(position);
//   }

    /**
     * 查询定位经纬度所对应的城市的天气
     */
    private void queryMiniWeather(String countyName) {
//        countyNameToList = countyName;
//        Message message = new Message();
//        message.what = UPDATE_LIST;
//        handler.sendMessage(message);
        String urlCountyName = "";
        try {
            urlCountyName = URLEncoder.encode(countyName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String address = "http://wthrcdn.etouch.cn/weather_mini?city=" + urlCountyName;
        queryFromServer(address, "forMiniWeather");
    }

    /**
     * 根据传入的地址和类型去向服务器查询天气代号或者天气信息
     */
    private void queryFromServer(final String address, final String type) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {

                if ("forMiniWeather".equals(type)) {
                    //处理服务器返回的天气信息
                    Utility.handleWeatherJsonResponse(MyApplication.getContext(), response);

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            showWeather();
//                            sendFragmentmsg();
//                        }
//                    });
                }
            }

            @Override
            public void onError(Exception e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        publishText.setText("同步失败");
//                    }
//                });
            }
        });
    }
}
