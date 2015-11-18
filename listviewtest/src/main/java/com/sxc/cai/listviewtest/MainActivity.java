package com.sxc.cai.listviewtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        //1.新建一个数据适配器
        //ArrayAdapter(上下文，当前ListView加载的每一个列表项所对应的布局文件，数据源)
        //SimpleAdapter():
        /**
         * context:上下文
         * data:数据源(List<? extends Map<String,?>> data)一个Map所组成的List集合
         *      每一个Map都会去对应ListView列表中的一行
         *      每一个Map中的键必须包含所有在from中所指定的键
         * resource:列表项的布局文件ID
         * from:Map中的键名
         * to:绑定数据视图中的ID，与from成对应关系
         */
        //适配器加载数据源
        String []arr_data = {"呵呵1","呵呵2","呵呵3","呵呵4"};
        mapList = new ArrayList<Map<String,Object>>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);
        simpleAdapter = new SimpleAdapter(this,getData(),R.layout.item_list,new String[]{"text","image_button"},new int[]{R.id.text});
        //视图(ListView)加载适配器
        listView.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> getData(){
        for (int i=0;i<20;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text","杭州"+i);
            mapList.add(map);

        }
        return mapList;
    }
}
