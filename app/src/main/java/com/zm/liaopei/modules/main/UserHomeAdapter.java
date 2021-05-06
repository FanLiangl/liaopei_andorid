package com.zm.liaopei.modules.main;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanliangliang
 * @description:用户详情
 * @date : 2021/4/29 14:27
 */
public class UserHomeAdapter  extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private List<String> mTitleList;

    public UserHomeAdapter(FragmentManager fm, ArrayList<Fragment> fragments, List<String> titleList) {
        super(fm);
        this.mFragments = fragments;
        this.mTitleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
