package com.sxc.cai.viewpagertest;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener{
    private List<View> viewList;
    private ViewPager pager;
//    private PagerTabStrip tab;
    private List<String> titleList; //标题集

    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewList = new ArrayList<View>();
        /**
         * 将view对象作为viewpager的数据源
         */
        View view1 = View.inflate(this,R.layout.view,null);
        View view2 = View.inflate(this,R.layout.view,null);
        View view3 = View.inflate(this,R.layout.view,null);
        View view4 = View.inflate(this,R.layout.view,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        /**
         * 将Fragment作为ViewPager的数据源
         */
        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());

        //为页卡设置标题
//        titleList = new ArrayList<String>();
//        titleList.add("第一页");
//        titleList.add("第二页");
//        titleList.add("第三页");
//        titleList.add("第四页");

        //为PagerTabStrip
//        tab = (PagerTabStrip) findViewById(R.id.tab);
//        tab.setBackgroundColor(Color.RED); //设置背景颜色
//        tab.setTextColor(Color.YELLOW); //设置字体颜色
//        tab.setDrawFullUnderline(false); //设置长线不可见
//        tab.setTabIndicatorColor(Color.GREEN); //设置小粗线颜色
        //初始化ViewPager
        pager = (ViewPager) findViewById(R.id.pager);
        //创建pagerAdapter适配器
        MyPagerAdapter adapter = new MyPagerAdapter(viewList);
        //设置适配器
//        pager.setAdapter(adapter);

        MyFragmentAdapter2 adapter3 = new MyFragmentAdapter2(getSupportFragmentManager(),fragmentList,titleList);
        pager.setAdapter(adapter3);
        pager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
