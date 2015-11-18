package com.sxc.cai.weather.activity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sxc.cai.weather.R;
import com.sxc.cai.weather.receiver.AutoUpdateReceiver;
import com.sxc.cai.weather.util.GetLocation;
import com.sxc.cai.weather.util.HttpCallbackListener;
import com.sxc.cai.weather.util.HttpUtil;
import com.sxc.cai.weather.util.Utility;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends Activity implements View.OnClickListener {

    private LinearLayout weatherInfoLayout;
    private LinearLayout FweatherInfoLayout;

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
    private Button fanhui;
    private Button change;
    //
    private Location location;
    //
    private LocationManager locationManager;
    //
    private DrawerLayout drawerLayout;
    private LinearLayout rightDrawer;
    private ListView drawerListView;
    private Button addButton;
    private List<String> cityLists = new ArrayList<String>();
    private List<String> cityListForC = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    //
    private String countyNameToList;
    private static final int UPDATE_LIST = 1;
    private ViewPager pager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);

//        MyFragmentAdapter2 adapter3 = new MyFragmentAdapter2(getSupportFragmentManager(),fragmentList);
//        pager.setAdapter(adapter3);
//        pager.addOnPageChangeListener(this);
        //初始化各控件
        weatherInfoLayout = (LinearLayout) findViewById(R.id.blue_layout);
        FweatherInfoLayout = (LinearLayout) findViewById(R.id.white_layout);
        cityNameText = (TextView) findViewById(R.id.city_name);
        weatherDespText = (TextView) findViewById(R.id.weather_desp);
        tempText1 = (TextView) findViewById(R.id.temp1);
        tempText2 = (TextView) findViewById(R.id.temp2);
        weatherWendu = (TextView) findViewById(R.id.weather_wendu);
        date3 = (TextView) findViewById(R.id.date3);
        date4 = (TextView) findViewById(R.id.date4);
        date5 = (TextView) findViewById(R.id.date5);
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
        image5 = (ImageView) findViewById(R.id.image5);
        wendu1 = (TextView) findViewById(R.id.wendu1);
        wendu2 = (TextView) findViewById(R.id.wendu2);
        wendu3 = (TextView) findViewById(R.id.wendu3);
        wendu4 = (TextView) findViewById(R.id.wendu4);
        wendu5 = (TextView) findViewById(R.id.wendu5);

        if (GetLocation.getNowLocation() != null) {
            location = GetLocation.getNowLocation();
            cityNameText.setText("同步中...");
            weatherInfoLayout.setVisibility(View.INVISIBLE); //设置控件不可见
            FweatherInfoLayout.setVisibility(View.INVISIBLE);
            Log.e("这是测试", location + "");
            queryCountyName(location);
        }
        locationManager = GetLocation.getLocationManager();

//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        rightDrawer = (LinearLayout) findViewById(R.id.right_drawer);
//        drawerListView = (ListView) findViewById(R.id.drawer_list);
//        addButton = (Button) findViewById(R.id.add_button);
//        addButton.setOnClickListener(this);

        fanhui = (Button) findViewById(R.id.fanhui);
        change = (Button) findViewById(R.id.city_change);
        fanhui.setOnClickListener(this);
        change.setOnClickListener(this);

//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityLists);
//        drawerListView.setAdapter(adapter);

//        SharedPreferences spfs = getSharedPreferences("citydata", MODE_PRIVATE);
//        Set<String> dft = new TreeSet<String>();
//        dft = spfs.getStringSet("cityname", dft);
//        cityLists.addAll(dft);
//        adapter.notifyDataSetChanged();


//        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String getCityName = cityLists.get(position);
//                drawerLayout.closeDrawer(rightDrawer);
//                queryMiniWeather(getCityName);
//            }
//        });
//        drawerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                AlertDialog.Builder dialog = new AlertDialog.Builder(WeatherActivity.this);
//                dialog.setTitle("提示");
//                dialog.setMessage("确定删除该条目？");
//                dialog.setCancelable(false);
//                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        cityLists.remove(position);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//
//                dialog.show();
//                return true;
//            }
//        });
    }

//    private android.os.Handler handler = new android.os.Handler() {
//
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case UPDATE_LIST: {
//                    cityListForC.add(countyNameToList);
//                    for (String i : cityListForC) {
//                        if (!cityLists.contains(i)) {
//                            cityLists.add(i);
//                        }
//                    }
//                    adapter.notifyDataSetChanged();
////                    drawerListView.setSelection(0);
////                    cityLists.clear();
//                    break;
//                }
//                default:
//                    break;
//            }
//        }
//    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2: {
                if (resultCode == RESULT_OK) {
                    String cityNameFromC = data.getStringExtra("county_name");
//                    drawerLayout.closeDrawer(rightDrawer);
                    queryMiniWeather(cityNameFromC);
                }else if (resultCode == RESULT_FIRST_USER){
                    String cityNameFrom2 = data.getStringExtra("countynameFrom2");
                    queryMiniWeather(cityNameFrom2);
                }
                break;
            }
            default:
                break;
        }
    }

    //
//    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(GetLocation.getLocationListener());
        }
//        SharedPreferences.Editor editor = getSharedPreferences("citydata", MODE_PRIVATE).edit();
//        Set<String> cset = new TreeSet<String>();
//        cset.addAll(cityLists);
//        editor.putStringSet("cityname", cset);
//        editor.commit();
    }
//

    /**
     * 查询定位经纬度所对应的城市名
     */
    private void queryCountyName(Location location) {
        String address = "http://api.map.baidu.com/geocoder?output=json&location=" +
                location.getLatitude() + "," + location.getLongitude() + "&key=aHoAKXaHxtvd7TnRgqo1lejM";

        queryFromServer(address, "forCountyName");
    }

    /**
     * 查询定位经纬度所对应的城市名的天气代号
     */
//    private void queryCountyCode(String countyName){
//        String address = "http://apistore.baidu.com/microservice/cityinfo?cityname=" + countyName;
//
//        queryFromServer(address, "forCountyCode");
//    }

    /**
     * 查询定位经纬度所对应的城市的天气
     */
    private void queryMiniWeather(String countyName) {
        countyNameToList = countyName;
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
     * 查询天气代号所对应的天气(弃用)
     */
    private void queryWeatherInfo(String weatherCode) {
        String address = "http://wthrcdn.etouch.cn/WeatherApi?citykey=" + weatherCode;
        queryFromServer(address, "weatherCode");
    }

    /**
     * 根据传入的地址和类型去向服务器查询天气代号或者天气信息
     */
    private void queryFromServer(final String address, final String type) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                if ("countyCode".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        //从服务器返回的数据中解析出天气代号
                        String[] array = response.split("\\|");
                        if (array != null && array.length == 2) {
                            String weatherCode = array[1];
                            queryWeatherInfo(weatherCode);
                        }
                    }
                } else if ("forMiniWeather".equals(type)) {
                    //处理服务器返回的天气信息
                    Utility.handleWeatherJsonResponse(WeatherActivity.this, response);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showWeather();
//                            sendFragmentmsg();
                        }
                    });
                } else if ("forCountyName".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("addressComponent");
                            String str = jsonObject2.getString("city");
                            String countyName = str.substring(0, str.length() - 1);
                            Log.i("承认书", countyName);
                            queryMiniWeather(countyName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        publishText.setText("同步失败");
                    }
                });
            }
        });
    }


//    private void sendFragmentmsg(){
//        Fragment5 fragment5 = new Fragment5();
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("msgfromA",true);
//        Log.i("tag", "传值");
//        fragment5.setArguments(bundle);
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
//        beginTransaction.add(R.id.layout,fragment5,"fragment5");
//        beginTransaction.commit();
//    }

    /**
     * 从SharedPreferences文件中读取存储的天气信息,并显示到界面上
     */
    private void showWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        cityNameText.setText(prefs.getString("city", ""));
        weatherWendu.setText(prefs.getString("wendu", ""));
        tempText1.setText(prefs.getString("low", ""));
        tempText2.setText(prefs.getString("high", ""));
        weatherDespText.setText(prefs.getString("type", ""));
        String riqi1 = prefs.getString("date2", "");
        String riqi2 = prefs.getString("date3", "");
        String riqi3 = prefs.getString("date4", "");
        date3.setText("周" + riqi1.substring(riqi1.length() - 1));
        date4.setText("周" + riqi2.substring(riqi2.length() - 1));
        date5.setText("周" + riqi3.substring(riqi3.length() - 1));
        String type0 = prefs.getString("type0", "");
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
            case "雾": {
                image1.setImageResource(R.drawable.d18);
                break;
            }
            default:
                break;
        }
        String type1 = prefs.getString("type1", "");
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
            case "雾": {
                image2.setImageResource(R.drawable.d18);
                break;
            }
            default:
                break;
        }

        String type2 = prefs.getString("type2", "");
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
            case "雾": {
                image3.setImageResource(R.drawable.d18);
                break;
            }
            default:
                break;
        }

        String type3 = prefs.getString("type3", "");
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
            default:
                break;
        }

        String type4 = prefs.getString("type4", "");
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
            case "雾": {
                image5.setImageResource(R.drawable.d18);
                break;
            }
            default:
                break;
        }
        wendu1.setText((prefs.getString("low0", "")).substring(3) + "～" + (prefs.getString("high0", "")).substring(3));
        wendu2.setText((prefs.getString("low1", "")).substring(3) + "～" + (prefs.getString("high1", "")).substring(3));
        wendu3.setText((prefs.getString("low2", "")).substring(3) + "～" + (prefs.getString("high2", "")).substring(3));
        wendu4.setText((prefs.getString("low3", "")).substring(3) + "～" + (prefs.getString("high3", "")).substring(3));
        wendu5.setText((prefs.getString("low4", "")).substring(3) + "～" + (prefs.getString("high4", "")).substring(3));
//        publishText.setText("今天"+prefs.getString("publish_time","")+"发布");
//        currentDateText.setText(prefs.getString("current_date", ""));
        weatherInfoLayout.setVisibility(View.VISIBLE);
        FweatherInfoLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateReceiver.class);
        startService(intent);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui: {
                finish();
                break;
            }
            case R.id.city_change: {
//                drawerLayout.openDrawer(rightDrawer);
                Intent intent = new Intent(this,CityChangeActivity.class);
                intent.putExtra("countyname", countyNameToList);
//                startActivityForResult(intent,2);
                startActivity(intent);

                break;
            }
            default:
                break;
        }

    }
}
