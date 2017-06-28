package com.airy.drawerlayoutqq;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Airy on 2017/6/27.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"消息", "联系人", "动态"};
    private Context context;
    private int[] items;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public MyFragmentPagerAdapter(FragmentManager fm, int[] items, Context context) {
        super(fm);
        this.items = items;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return CommonFragment.newInstance(titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    // TabLayout关联viewpager时会调用此方法.
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
