package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    //private List<NewsListTabFragment> fragments;
    private List<String> titles;
    private String countryCode;

    public NewsPagerAdapter(FragmentManager fm, List<String> titles, String countryCode) {
        super(fm);
        this.countryCode = countryCode;
        this.titles = titles;
    //    this.fragments = fragments;
    }

    @Override
    public NewsListTabFragment getItem(int position) {
        return NewsListTabFragment.newInstance(titles.get(position), countryCode);
        //.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

//    @Override
//    public Parcelable saveState() {
//        return null;
//    }

    public void dataSetChanged(){
        notifyDataSetChanged();
    }

    public void addNewPage(String title) {
        titles.add(title);
        notifyDataSetChanged();
    }

    public void removePage(int position) {
        titles.remove(position);
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }
}


