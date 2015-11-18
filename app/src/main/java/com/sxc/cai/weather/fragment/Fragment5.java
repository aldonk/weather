package com.sxc.cai.weather.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sxc.cai.weather.R;
import com.sxc.cai.weather.util.ReFlashLinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai on 2015/11/3.
 */
public class Fragment5 extends Fragment implements ReFlashLinearLayout.IReFlashListener{
    private String address;
    private String cityname;

    private static final int SHOW_INFO = 0;

    private TextView cityNameText; //用于显示城市名
    private TextView weatherDespText; //用于显示天气描述
    private TextView tempText1; //用于显示气温1
    private TextView tempText2; //用于显示气温2
    private TextView weatherWendu; //用于显示当前温度
    private TextView date3;
    private TextView date4;
    private TextView date5;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private TextView wendu1;
    private TextView wendu2;
    private TextView wendu3;
    private TextView wendu4;
    private TextView wendu5;
    //下拉刷新布局
    private ReFlashLinearLayout myLayout;
    public Fragment5(String cityname){
        this.cityname = cityname;
//        sendRequest();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_INFO:{
                    List<String> info_list = new ArrayList<String>();
                    info_list = (List<String>) msg.obj;
//                    Log.e("城市城市",nameci);
                    cityNameText.setText(info_list.get(0));
                    weatherWendu.setText(info_list.get(1));
                    tempText1.setText(info_list.get(3));
                    tempText2.setText(info_list.get(4));
                    weatherDespText.setText(info_list.get(2));
                    String riqi1 = info_list.get(9);
                    String riqi2 = info_list.get(13);
                    String riqi3 = info_list.get(17);
                    wendu1.setText((info_list.get(23)).substring(3) + "～" + (info_list.get(24)).substring(3));
                    wendu2.setText((info_list.get(7)).substring(3) + "～" + (info_list.get(8)).substring(3));
                    wendu3.setText((info_list.get(11)).substring(3) + "～" + (info_list.get(12)).substring(3));
                    wendu4.setText((info_list.get(15)).substring(3) + "～" + (info_list.get(16)).substring(3));
                    wendu5.setText((info_list.get(19)).substring(3) + "～" + (info_list.get(20)).substring(3));
                    date3.setText("周" + riqi1.substring(riqi1.length() - 1));
                    date4.setText("周" + riqi2.substring(riqi2.length() - 1));
                    date5.setText("周" + riqi3.substring(riqi3.length() - 1));
                    String type0 = info_list.get(22);
                    switch (type0) {
                        case "晴": {
                            image1.setImageResource(R.drawable.d00);
                            break;
                        }
                        case "多云": {
                            image1.setImageResource(R.drawable.d01);
                            break;
                        }
                        case "阴": {
                            image1.setImageResource(R.drawable.d02);
                            break;
                        }
                        case "阵雨": {
                            image1.setImageResource(R.drawable.d03);
                            break;
                        }
                        case "雷阵雨": {
                            image1.setImageResource(R.drawable.d04);
                            break;
                        }
                        case "雷雨伴冰雹": {
                            image1.setImageResource(R.drawable.d05);
                            break;
                        }
                        case "雨夹雪": {
                            image1.setImageResource(R.drawable.d06);
                            break;
                        }
                        case "小雨": {
                            image1.setImageResource(R.drawable.d07);
                            break;
                        }
                        case "中雨": {
                            image1.setImageResource(R.drawable.d08);
                            break;
                        }
                        case "大雨": {
                            image1.setImageResource(R.drawable.d09);
                            break;
                        }
                        case "暴雨": {
                            image1.setImageResource(R.drawable.d10);
                            break;
                        }
                        case "大暴雨": {
                            image1.setImageResource(R.drawable.d11);
                            break;
                        }
                        case "特大暴雨": {
                            image1.setImageResource(R.drawable.d12);
                            break;
                        }
                        case "阵雪": {
                            image1.setImageResource(R.drawable.d13);
                            break;
                        }
                        case "小雪": {
                            image1.setImageResource(R.drawable.d14);
                            break;
                        }
                        case "中雪": {
                            image1.setImageResource(R.drawable.d15);
                            break;
                        }
                        case "大雪": {
                            image1.setImageResource(R.drawable.d16);
                            break;
                        }
                        case "暴雪": {
                            image1.setImageResource(R.drawable.d17);
                            break;
                        }
                        case "霾": {
                            image1.setImageResource(R.drawable.d18);
                            break;
                        }
                        case "雾": {
                            image1.setImageResource(R.drawable.d18);
                            break;
                        }
                        default:
                            break;
                    }
                    String type1 = info_list.get(6);
                    switch (type1) {
                        case "晴": {
                            image2.setImageResource(R.drawable.d00);
                            break;
                        }
                        case "多云": {
                            image2.setImageResource(R.drawable.d01);
                            break;
                        }
                        case "阴": {
                            image2.setImageResource(R.drawable.d02);
                            break;
                        }
                        case "阵雨": {
                            image2.setImageResource(R.drawable.d03);
                            break;
                        }
                        case "雷阵雨": {
                            image2.setImageResource(R.drawable.d04);
                            break;
                        }
                        case "雷雨伴冰雹": {
                            image2.setImageResource(R.drawable.d05);
                            break;
                        }
                        case "雨夹雪": {
                            image2.setImageResource(R.drawable.d06);
                            break;
                        }
                        case "小雨": {
                            image2.setImageResource(R.drawable.d07);
                            break;
                        }
                        case "中雨": {
                            image2.setImageResource(R.drawable.d08);
                            break;
                        }
                        case "大雨": {
                            image2.setImageResource(R.drawable.d09);
                            break;
                        }
                        case "暴雨": {
                            image2.setImageResource(R.drawable.d10);
                            break;
                        }
                        case "大暴雨": {
                            image2.setImageResource(R.drawable.d11);
                            break;
                        }
                        case "特大暴雨": {
                            image2.setImageResource(R.drawable.d12);
                            break;
                        }
                        case "阵雪": {
                            image2.setImageResource(R.drawable.d13);
                            break;
                        }
                        case "小雪": {
                            image2.setImageResource(R.drawable.d14);
                            break;
                        }
                        case "中雪": {
                            image2.setImageResource(R.drawable.d15);
                            break;
                        }
                        case "大雪": {
                            image2.setImageResource(R.drawable.d16);
                            break;
                        }
                        case "暴雪": {
                            image2.setImageResource(R.drawable.d17);
                            break;
                        }
                        case "霾": {
                            image2.setImageResource(R.drawable.d18);
                            break;
                        }
                        case "雾": {
                            image2.setImageResource(R.drawable.d18);
                            break;
                        }
                        default:
                            break;
                    }

                    String type2 = info_list.get(10);
                    switch (type2) {
                        case "晴": {
                            image3.setImageResource(R.drawable.d00);
                            break;
                        }
                        case "多云": {
                            image3.setImageResource(R.drawable.d01);
                            break;
                        }
                        case "阴": {
                            image3.setImageResource(R.drawable.d02);
                            break;
                        }
                        case "阵雨": {
                            image3.setImageResource(R.drawable.d03);
                            break;
                        }
                        case "雷阵雨": {
                            image3.setImageResource(R.drawable.d04);
                            break;
                        }
                        case "雷雨伴冰雹": {
                            image3.setImageResource(R.drawable.d05);
                            break;
                        }
                        case "雨夹雪": {
                            image3.setImageResource(R.drawable.d06);
                            break;
                        }
                        case "小雨": {
                            image3.setImageResource(R.drawable.d07);
                            break;
                        }
                        case "中雨": {
                            image3.setImageResource(R.drawable.d08);
                            break;
                        }
                        case "大雨": {
                            image3.setImageResource(R.drawable.d09);
                            break;
                        }
                        case "暴雨": {
                            image3.setImageResource(R.drawable.d10);
                            break;
                        }
                        case "大暴雨": {
                            image3.setImageResource(R.drawable.d11);
                            break;
                        }
                        case "特大暴雨": {
                            image3.setImageResource(R.drawable.d12);
                            break;
                        }
                        case "阵雪": {
                            image3.setImageResource(R.drawable.d13);
                            break;
                        }
                        case "小雪": {
                            image3.setImageResource(R.drawable.d14);
                            break;
                        }
                        case "中雪": {
                            image3.setImageResource(R.drawable.d15);
                            break;
                        }
                        case "大雪": {
                            image3.setImageResource(R.drawable.d16);
                            break;
                        }
                        case "暴雪": {
                            image3.setImageResource(R.drawable.d17);
                            break;
                        }
                        case "霾": {
                            image3.setImageResource(R.drawable.d18);
                            break;
                        }
                        case "雾": {
                            image3.setImageResource(R.drawable.d18);
                            break;
                        }
                        default:
                            break;
                    }

                    String type3 = info_list.get(14);
                    switch (type3) {
                        case "晴": {
                            image4.setImageResource(R.drawable.d00);
                            break;
                        }
                        case "多云": {
                            image4.setImageResource(R.drawable.d01);
                            break;
                        }
                        case "阴": {
                            image4.setImageResource(R.drawable.d02);
                            break;
                        }
                        case "阵雨": {
                            image4.setImageResource(R.drawable.d03);
                            break;
                        }
                        case "雷阵雨": {
                            image4.setImageResource(R.drawable.d04);
                            break;
                        }
                        case "雷雨伴冰雹": {
                            image4.setImageResource(R.drawable.d05);
                            break;
                        }
                        case "雨夹雪": {
                            image4.setImageResource(R.drawable.d06);
                            break;
                        }
                        case "小雨": {
                            image4.setImageResource(R.drawable.d07);
                            break;
                        }
                        case "中雨": {
                            image4.setImageResource(R.drawable.d08);
                            break;
                        }
                        case "大雨": {
                            image4.setImageResource(R.drawable.d09);
                            break;
                        }
                        case "暴雨": {
                            image4.setImageResource(R.drawable.d10);
                            break;
                        }
                        case "大暴雨": {
                            image4.setImageResource(R.drawable.d11);
                            break;
                        }
                        case "特大暴雨": {
                            image4.setImageResource(R.drawable.d12);
                            break;
                        }
                        case "阵雪": {
                            image4.setImageResource(R.drawable.d13);
                            break;
                        }
                        case "小雪": {
                            image4.setImageResource(R.drawable.d14);
                            break;
                        }
                        case "中雪": {
                            image4.setImageResource(R.drawable.d15);
                            break;
                        }
                        case "大雪": {
                            image4.setImageResource(R.drawable.d16);
                            break;
                        }
                        case "暴雪": {
                            image4.setImageResource(R.drawable.d17);
                            break;
                        }
                        case "霾": {
                            image4.setImageResource(R.drawable.d18);
                            break;
                        }
                        case "雾": {
                            image4.setImageResource(R.drawable.d18);
                            break;
                        }
                        default:
                            break;
                    }

                    String type4 = info_list.get(18);
                    switch (type4) {
                        case "晴": {
                            image5.setImageResource(R.drawable.d00);
                            break;
                        }
                        case "多云": {
                            image5.setImageResource(R.drawable.d01);
                            break;
                        }
                        case "阴": {
                            image5.setImageResource(R.drawable.d02);
                            break;
                        }
                        case "阵雨": {
                            image5.setImageResource(R.drawable.d03);
                            break;
                        }
                        case "雷阵雨": {
                            image5.setImageResource(R.drawable.d04);
                            break;
                        }
                        case "雷雨伴冰雹": {
                            image5.setImageResource(R.drawable.d05);
                            break;
                        }
                        case "雨夹雪": {
                            image5.setImageResource(R.drawable.d06);
                            break;
                        }
                        case "小雨": {
                            image5.setImageResource(R.drawable.d07);
                            break;
                        }
                        case "中雨": {
                            image5.setImageResource(R.drawable.d08);
                            break;
                        }
                        case "大雨": {
                            image5.setImageResource(R.drawable.d09);
                            break;
                        }
                        case "暴雨": {
                            image5.setImageResource(R.drawable.d10);
                            break;
                        }
                        case "大暴雨": {
                            image5.setImageResource(R.drawable.d11);
                            break;
                        }
                        case "特大暴雨": {
                            image5.setImageResource(R.drawable.d12);
                            break;
                        }
                        case "阵雪": {
                            image5.setImageResource(R.drawable.d13);
                            break;
                        }
                        case "小雪": {
                            image5.setImageResource(R.drawable.d14);
                            break;
                        }
                        case "中雪": {
                            image5.setImageResource(R.drawable.d15);
                            break;
                        }
                        case "大雪": {
                            image5.setImageResource(R.drawable.d16);
                            break;
                        }
                        case "暴雪": {
                            image5.setImageResource(R.drawable.d17);
                            break;
                        }
                        case "霾": {
                            image5.setImageResource(R.drawable.d18);
                            break;
                        }
                        case "雾": {
                            image5.setImageResource(R.drawable.d18);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_layout,container,false);

        myLayout = (ReFlashLinearLayout) view.findViewById(R.id.my_layout);
        cityNameText = (TextView) view.findViewById(R.id.city_name);
        weatherDespText = (TextView) view.findViewById(R.id.weather_desp);
        tempText1 = (TextView) view.findViewById(R.id.temp1);
        tempText2 = (TextView) view.findViewById(R.id.temp2);
        weatherWendu = (TextView) view.findViewById(R.id.weather_wendu);
        date3 = (TextView) view.findViewById(R.id.date3);
        date4 = (TextView) view.findViewById(R.id.date4);
        date5 = (TextView) view.findViewById(R.id.date5);
        image1 = (ImageView) view.findViewById(R.id.image1);
        image2 = (ImageView) view.findViewById(R.id.image2);
        image3 = (ImageView) view.findViewById(R.id.image3);
        image4 = (ImageView) view.findViewById(R.id.image4);
        image5 = (ImageView) view.findViewById(R.id.image5);
        wendu1 = (TextView) view.findViewById(R.id.wendu1);
        wendu2 = (TextView) view.findViewById(R.id.wendu2);
        wendu3 = (TextView) view.findViewById(R.id.wendu3);
        wendu4 = (TextView) view.findViewById(R.id.wendu4);
        wendu5 = (TextView) view.findViewById(R.id.wendu5);
        sendRequest();
        myLayout.setInterface(this);
        return view;
    }

    private void sendRequest(){
        String urlCountyName = "";
        try {
            urlCountyName = URLEncoder.encode(cityname, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        address = "http://wthrcdn.etouch.cn/weather_mini?city=" + urlCountyName;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET"); //从服务器获取数据
                    connection.setConnectTimeout(8000); //连接超时时间
                    connection.setReadTimeout(8000);    //读取超时时间
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    List<String> json_list = new ArrayList<String>();
                    JSONObject jsonObject = new JSONObject(response.toString());
                    Log.e("天气",response.toString());
                    JSONObject data = jsonObject.getJSONObject("data");
//                    wendu = data.getString("wendu");
                    JSONArray forecast = data.getJSONArray("forecast");
                    JSONObject weather0 = forecast.getJSONObject(0);
                    json_list.add(data.getString("city"));
                    json_list.add(data.getString("wendu"));
                    json_list.add(weather0.getString("type"));
                    json_list.add(weather0.getString("low"));
                    json_list.add(weather0.getString("high"));
                    json_list.add((weather0.getString("date")).replaceAll("天", "日"));
                    JSONObject weather1 = forecast.getJSONObject(1);
                    json_list.add(weather1.getString("type"));
                    json_list.add(weather1.getString("low"));
                    json_list.add(weather1.getString("high"));
                    json_list.add((weather1.getString("date")).replaceAll("天", "日"));
                    JSONObject weather2 = forecast.getJSONObject(2);
                    json_list.add(weather2.getString("type"));
                    json_list.add(weather2.getString("low"));
                    json_list.add(weather2.getString("high"));
                    json_list.add((weather2.getString("date")).replaceAll("天", "日"));
                    JSONObject weather3 = forecast.getJSONObject(3);
                    json_list.add(weather3.getString("type"));
                    json_list.add(weather3.getString("low"));
                    json_list.add(weather3.getString("high"));
                    json_list.add((weather3.getString("date")).replaceAll("天", "日"));
                    JSONObject weather4 = forecast.getJSONObject(4);
                    json_list.add(weather4.getString("type"));
                    json_list.add(weather4.getString("low"));
                    json_list.add(weather4.getString("high"));
                    json_list.add((weather4.getString("date")).replaceAll("天", "日"));
                    JSONObject yesterday = data.getJSONObject("yesterday");
                    json_list.add(yesterday.getString("type"));
                    json_list.add(yesterday.getString("low"));
                    json_list.add(yesterday.getString("high"));
                    json_list.add((yesterday.getString("date")).replaceAll("天","日"));
                    Message message = new Message();
                    message.what = SHOW_INFO;
//                    message.obj = city;
                    message.obj = json_list;
                    handler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }).start();
    }

    @Override
    public void onReFlash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendRequest();
                myLayout.reflashComplete();
            }
        },1000);
    }
}