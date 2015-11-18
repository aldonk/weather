package com.sxc.cai.weather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.sxc.cai.weather.R;
import com.sxc.cai.weather.fragment.Fragment5;
import com.sxc.cai.weather.fragment.MyFragmentAdapter2;
import com.sxc.cai.weather.receiver.AutoUpdateReceiver;
import com.sxc.cai.weather.util.GetLocation;
import com.sxc.cai.weather.util.HttpCallbackListener;
import com.sxc.cai.weather.util.HttpUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragmentActivity extends FragmentActivity implements View.OnClickListener {

    private Button fanhui;
    private Button change;
    private Location location;

    private ViewGroup main;
    private LinearLayout frameLayout;
    private ViewPager viewPager;
    private List<View> viewList;
    private List<View> tList;
    private List<Fragment> fragmentList;
    private List<String> c_list;
    private List<String> y_list;
    private List<String> x_list;
    private MyFragmentAdapter2 adapter2;

    private static final int UPDATE_LIST = 1;

    protected void onCreate(Bundle savedInstanceState) {
        Log.e("活动1onCreate", "1");
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();

        main = (ViewGroup) inflater.inflate(R.layout.main_layout, null);
        fanhui = (Button) main.findViewById(R.id.fanhui);
        change = (Button) main.findViewById(R.id.city_change);
        fanhui.setOnClickListener(this);
        change.setOnClickListener(this);

        if (GetLocation.getNowLocation() != null) {
            location = GetLocation.getNowLocation();
//            Log.e("定位城市",locName);
//            cityNameText.setText("同步中...");
//            weatherInfoLayout.setVisibility(View.INVISIBLE); //设置控件不可见
//            FweatherInfoLayout.setVisibility(View.INVISIBLE);
            queryCountyName(location);
        }

        frameLayout = (LinearLayout) main.findViewById(R.id.layout);
        viewPager = (ViewPager) main.findViewById(R.id.pager);
        viewList = new ArrayList<View>();
        tList = new ArrayList<View>();
        fragmentList = new ArrayList<Fragment>();
        c_list = new ArrayList<String>();
        y_list = new ArrayList<String>();
        SharedPreferences pref = getSharedPreferences("list_data", MODE_PRIVATE);
        String name = pref.getString("name", null);
        y_list.add(name);
        int size = pref.getInt("list_size", 0);
        for (int i = 0; i < size; i++) {
            y_list.add(pref.getString("list_" + i, null));
        }

        for (String i : y_list) {
            if (!c_list.contains(i)) {
                c_list.add(i);
            }
        }
//        View view1 = inflater.inflate(R.layout.view, null);
//        View view2 = inflater.inflate(R.layout.view1, null);
//        tList.add(view1);
//        tList.add(view2);
//        ((TextView) view1.findViewById(R.id.text)).setText("一一一");
//        ((TextView) view2.findViewById(R.id.text)).setText("二二二");
//        for (int i=0;i<c_list.size();i++){
//            viewList.add(inflater.inflate(R.layout.weather_layout, null));
////            Log.e("城市list", c_list.get(i));
//        }
//        fragmentList.add(new Fragment5());
        for (int i = 0; i < c_list.size(); i++) {
            fragmentList.add(new Fragment5(c_list.get(i)));
        }

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(main);
//        MyPagerAdapter adapter = new MyPagerAdapter(viewList,c_list);
//        adapter2 = new MyFragmentAdapter(getSupportFragmentManager(),fragmentList);
        adapter2 = new MyFragmentAdapter2(getSupportFragmentManager(), fragmentList);
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setAdapter(adapter2);

        Intent intent = new Intent(this, AutoUpdateReceiver.class);
        startService(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2: {
                switch (resultCode) {
                    case 1: {
                        int countyno = data.getIntExtra("countyno",0);
                        viewPager.setCurrentItem(countyno);
                        break;
                    }
                    case 2: {
                        List<Integer> deleteno = data.getIntegerArrayListExtra("deleteno");
                        for (int i = 0; i < deleteno.size(); i++) {
                            int no = deleteno.get(i);
                            fragmentList.remove(no);
//                            fragmentList.
                            Log.e("活动1deleteno.get(i)", deleteno.get(i) + "");
                        }
                        adapter2.notifyDataSetChanged();
                        break;
                    }
                    case 3: {
                        String nameFrom3 = data.getStringExtra("county_name");
                        Boolean nameExist = false;
                        int existNo = 0;
                        for (int i=0;i<c_list.size();i++){
                            if (nameFrom3.equals(c_list.get(i))){
                                nameExist = true;
                                existNo = i;
                            }
                        }
                        if (nameExist){
                            viewPager.setCurrentItem(existNo);
                        }else {
                            fragmentList.add(new Fragment5(nameFrom3));
                            adapter2.notifyDataSetChanged();
                            viewPager.setCurrentItem(fragmentList.size()-1);
                        }
                        break;
                    }
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("活动1onStart", "1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("活动1onResume", "1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("活动1onPause", "1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("活动1onStop", "1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("活动1onRestart", "1");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.e("活动1onDestroy", "1");
    }

    /**
     * 查询定位经纬度所对应的城市名
     */
    private void queryCountyName(Location location) {
        String address = "http://api.map.baidu.com/geocoder?output=json&location=" +
                location.getLatitude() + "," + location.getLongitude() + "&key=aHoAKXaHxtvd7TnRgqo1lejM";

        queryFromServer(address, "forCountyName");
    }

    /**
     * 根据传入的地址和类型去向服务器查询天气代号或者天气信息
     */
    private void queryFromServer(final String address, final String type) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(final String response) {
                if ("forCountyName".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("addressComponent");
                            String str = jsonObject2.getString("city");
                            String countyName = str.substring(0, str.length() - 1);
                            SharedPreferences.Editor editor = getSharedPreferences("list_data", MODE_PRIVATE).edit();
                            editor.putString("name", countyName);
                            editor.commit();
                            Log.i("承认书", countyName);
//                            queryMiniWeather(countyName);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fanhui: {
                finish();
                break;
            }
            case R.id.city_change: {
//                drawerLayout.openDrawer(rightDrawer);
                Intent intent = new Intent(this, CityChangeActivity.class);
//                intent.putExtra("countyname","杭州");
                startActivityForResult(intent, 2);
//                startActivity(intent);
                break;
            }
            default:
                break;
        }
    }

}
