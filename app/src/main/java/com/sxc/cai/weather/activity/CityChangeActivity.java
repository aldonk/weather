package com.sxc.cai.weather.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sxc.cai.weather.R;
import com.sxc.cai.weather.db.WeatherDb;
import com.sxc.cai.weather.model.City;
import com.sxc.cai.weather.model.County;
import com.sxc.cai.weather.model.Province;
import com.sxc.cai.weather.util.HttpCallbackListener;
import com.sxc.cai.weather.util.HttpUtil;
import com.sxc.cai.weather.util.Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CityChangeActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    public static final int RESULT_OK2 = 1;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> c_list;
    private List<String> y_list;
    private List<String> x_list;
    List<Integer> deleteno;
    private Button fanhui;
    private Button tianjia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("活动2onCreate", "2");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.citylist_layout);
        fanhui = (Button) findViewById(R.id.back_at2);
        tianjia = (Button) findViewById(R.id.add_at2);
        listView = (ListView) findViewById(R.id.city_listview);
        c_list = new ArrayList<String>();
        y_list = new ArrayList<String>();
        deleteno = new ArrayList<Integer>();
//        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences spf = getSharedPreferences("list_data", MODE_PRIVATE);
        String cityname = spf.getString("name",null);

        y_list.clear();
        y_list.add(cityname);
        int size = spf.getInt("list_size", 0);
        for (int i=0;i<size;i++){
            y_list.add(spf.getString("list_"+i ,null));
        }
        for (String i : y_list) {
            if (!c_list.contains(i)) {
                c_list.add(i);
            }
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,c_list);
        listView.setAdapter(adapter);

        fanhui.setOnClickListener(this);
        tianjia.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("活动2onStart", "2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("活动2onPause", "2");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("活动2onResume", "2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("活动2onRestart", "2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("活动2onDestroy","2");
        SharedPreferences.Editor editor = getSharedPreferences("list_data", MODE_PRIVATE).edit();
        editor.putInt("list_size", c_list.size());
        Log.e("list活动二城市名", c_list.get(c_list.size() - 1));
        for (int i=0;i<c_list.size();i++){
            editor.remove("list_"+i);
            editor.putString("list_"+i, c_list.get(i));
        }
        editor.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("活动2onSaveInstanceState", "2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("活动2onStop", "2");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 3:{
                if (resultCode == RESULT_OK){
                    String countyName = data.getStringExtra("county_name");
//                    Log.e("活动2",countyName);
                    Intent intent = new Intent();
                    intent.putExtra("county_name", countyName);
                    setResult(3, intent);
                    y_list.add(countyName);
                    for (String i : y_list) {
                        if (!c_list.contains(i)) {
                            c_list.add(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    finish();
                }
                break;
            }
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_at2 :{
                finish();
                break;
            }
            case R.id.add_at2 :{
                Intent intent = new Intent(this,ChooseAreaActivity.class);
                startActivityForResult(intent,3);
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String cityname = c_list.get(position);
        Intent intent = new Intent();
        intent.putExtra("countyno",position);
        setResult(1, intent);
        finish();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("提示");
                dialog.setMessage("确定删除该条目？");
                dialog.setCancelable(false);
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        c_list.remove(position);
                        adapter.notifyDataSetChanged();
//                        String deletename = c_list.get(position);
                        Log.e("活动2position",position+"");
                        Intent intent = new Intent();
                        deleteno.add(position);
                        intent.putIntegerArrayListExtra("deleteno", (ArrayList<Integer>) deleteno);
                        setResult(2, intent);
                    }
                });

                dialog.show();
                return true;
    }

}
