package com.example.wenlaisu.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenlaisu on 2018/4/12.
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {

    protected List<Fragment> mFragmentList;

    protected String[] mTitles;

//    public BaseFragmentAdapter(FragmentManager fm) {
//        this(fm, null, null);
//    }

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mTitles) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.mFragmentList = fragmentList;
        this.mTitles = mTitles;
    }

//    public void add(Fragment fragment) {
//        if (isEmpty()) {
//            mFragmentList = new ArrayList<>();
//
//        }
//        mFragmentList.add(fragment);
//    }

    @Override
    public Fragment getItem(int position) {
        //        Logger.i("BaseFragmentAdapter position=" +position);
        return isEmpty() ? null : mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return isEmpty() ? 0 : mFragmentList.size();
    }

    public boolean isEmpty() {
        return mFragmentList == null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    /*  @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }*/


}
