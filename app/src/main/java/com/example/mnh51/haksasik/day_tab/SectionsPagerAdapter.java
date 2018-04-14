package com.example.mnh51.haksasik.day_tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mnh51 on 2018-04-14.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    String menuJSON;
    String dateJSON;

    public SectionsPagerAdapter(FragmentManager fm, String menuJSON, String dateJSON) {
        super(fm);
        this.menuJSON = menuJSON;
        this.dateJSON = dateJSON;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position, menuJSON, dateJSON);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 7;
    }
}
