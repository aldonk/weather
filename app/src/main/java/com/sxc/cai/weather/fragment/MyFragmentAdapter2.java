package com.sxc.cai.weather.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cai on 2015/11/2.
 */
public class MyFragmentAdapter2 extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
//    private List<String> titleList;
    public MyFragmentAdapter2(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
//        this.titleList = titleList;
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
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titleList.get(position);
//    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
