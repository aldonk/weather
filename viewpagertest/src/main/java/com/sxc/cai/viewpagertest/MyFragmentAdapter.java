package com.sxc.cai.viewpagertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cai on 2015/11/2.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    /**
     * 设置ViewPager页卡的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
