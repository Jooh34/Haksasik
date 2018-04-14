package com.example.mnh51.haksasik.day_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mnh51.haksasik.MenuViewManager;

/**
 * Created by mnh51 on 2018-04-14.
 */

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_MENU_JSON = "menu_json";
    private static final String ARG_DATE_JSON = "date_json";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, String menuJSON, String dateJSON) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARG_MENU_JSON, menuJSON);
        args.putString(ARG_DATE_JSON, dateJSON);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MenuViewManager mvm = new MenuViewManager(inflater,
                container,
                getArguments().getInt(ARG_SECTION_NUMBER),
                getArguments().getString(ARG_MENU_JSON),
                getArguments().getString(ARG_DATE_JSON)
        );

        return mvm.getRootView();
    }
}