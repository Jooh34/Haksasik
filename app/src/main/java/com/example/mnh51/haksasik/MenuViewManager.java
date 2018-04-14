package com.example.mnh51.haksasik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mnh51.haksasik.parser.MenuParser;

import java.util.ArrayList;

/**
 * Created by mnh51 on 2018-04-13.
 */

public class MenuViewManager {
    View rootView;

    static final int BREAKFAST = 0;
    static final int LUNCH = 1;
    static final int DINNER = 2;

    public MenuViewManager(LayoutInflater inflater, ViewGroup container, int index, String menuJSON, String dateJSON) {

        rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        MenuParser menuParser = new MenuParser(menuJSON);

        ArrayList<String> breakfast_list = new ArrayList<>();
        ArrayList<String> lunch_list = new ArrayList<>();
        ArrayList<String> dinner_list = new ArrayList<>();

        menuParser.getMenu(index, BREAKFAST, breakfast_list);
        menuParser.getMenu(index, LUNCH, lunch_list);
        menuParser.getMenu(index, DINNER, dinner_list);

        TextView breakfast = rootView.findViewById(R.id.breakfast);
        TextView lunch = rootView.findViewById(R.id.lunch);
        TextView dinner = rootView.findViewById(R.id.dinner);

        breakfast.setText(breakfast_list.toString());
        lunch.setText(lunch_list.toString());
        dinner.setText(dinner_list.toString());

    }

    public View getRootView() {
        return rootView;
    }
}
