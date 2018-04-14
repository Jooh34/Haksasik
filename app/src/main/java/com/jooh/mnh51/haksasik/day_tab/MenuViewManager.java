package com.jooh.mnh51.haksasik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jooh.mnh51.haksasik.parser.DateParser;
import com.jooh.mnh51.haksasik.parser.MenuParser;


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
        DateParser dateParser = new DateParser(dateJSON);

        ArrayList<String> breakfast_list = new ArrayList<>();
        ArrayList<String> lunch_list = new ArrayList<>();
        ArrayList<String> dinner_list = new ArrayList<>();

        menuParser.getMenu(index, BREAKFAST, breakfast_list);
        menuParser.getMenu(index, LUNCH, lunch_list);
        menuParser.getMenu(index, DINNER, dinner_list);
        String date = dateParser.getDate(index);

        TextView breakfast = rootView.findViewById(R.id.breakfast);
        TextView lunch = rootView.findViewById(R.id.lunch);
        TextView dinner = rootView.findViewById(R.id.dinner);
        TextView date_view = rootView.findViewById(R.id.date);

        breakfast.setText(trimList(breakfast_list));
        lunch.setText(trimList(lunch_list));
        dinner.setText(trimList(dinner_list));
        date_view.setText(date);

    }

    public String trimList(ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");

        int len = list.size();
        for(int i = 0; i<len; i++) {
            sb.append(list.get(i));
            sb.append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    public View getRootView() {
        return rootView;
    }
}
