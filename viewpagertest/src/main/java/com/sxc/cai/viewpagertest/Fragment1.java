package com.sxc.cai.viewpagertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by cai on 2015/11/2.
 */
public class Fragment1 extends Fragment implements View.OnClickListener{
    private View view;
    private MainActivity activity = (MainActivity) getActivity();
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
    private Button sousuo;
    private Button refresh;

    private Location location;

    private LocationManager locationManager;

    private DrawerLayout drawerLayout;
    private LinearLayout rightDrawer;
    private ListView drawerListView;
    private Button addButton;
    private List<String> cityLists = new ArrayList<String>();
    private List<String> cityListForC = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private String countyNameToList;
    private static final int UPDATE_LIST = 1;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_layout,container,false);


        //初始化各控件
//        weatherInfoLayout = (LinearLayout) view.findViewById(R.id.blue_layout);
//        FweatherInfoLayout = (LinearLayout) view.findViewById(R.id.white_layout);
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



        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        rightDrawer = (LinearLayout) view.findViewById(R.id.right_drawer);
        drawerListView = (ListView) view.findViewById(R.id.drawer_list);
        addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        fanhui = (Button) view.findViewById(R.id.fanhui);
        sousuo = (Button) view.findViewById(R.id.city_sousuo);
        refresh = (Button) view.findViewById(R.id.shuaxin);
        fanhui.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        refresh.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,cityLists);
        drawerListView.setAdapter(adapter);

        SharedPreferences spfs = activity.getSharedPreferences("citydata", activity.MODE_PRIVATE);
        Set<String> dft = new TreeSet<String>();
        dft = spfs.getStringSet("cityname", dft);
        cityLists.addAll(dft);
        adapter.notifyDataSetChanged();
        return view;
//        if (GetLocation.getNowLocation()!=null){
//            location = GetLocation.getNowLocation();
//            cityNameText.setText("同步中...");
//            weatherInfoLayout.setVisibility(View.INVISIBLE); //设置控件不可见
//            FweatherInfoLayout.setVisibility(View.INVISIBLE);
//            queryCountyName(location);
        }
//        locationManager = GetLocation.getLocationManager();


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
//                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
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
//    }

    @Override
    public void onClick(View v) {

    }
}
