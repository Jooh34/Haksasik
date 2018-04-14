package com.example.mnh51.haksasik.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mnh51 on 2018-04-13.
 */

/**
 * JSON형식의 menu 정보를 파싱하는 Class
 * getMenu(day, time, arrayList<String>) 로
 * 요일과 time(아침 or 점심 or 저녁), 빈리스트 입력 시 해당 시간 메뉴를 StringList에 add해줌
 */

public class MenuParser {

    String menuJSON;

    public MenuParser(String menuJSON) {
        this.menuJSON = menuJSON;
    }

    public void getMenu(int day, int time, ArrayList<String> menus) {

        try {
            JSONArray menulist = new JSONArray(menuJSON);

            int len = menulist.length();
            for(int i = 0; i<len; i++) {
                JSONObject menu = menulist.getJSONObject(i);

                int day_ = menu.optInt("day");
                int time_ = menu.optInt("time");

                if(day == day_ && time == time_) { // 원하는 시간 대의 메뉴
                    JSONArray namelist = menu.getJSONArray("name");

                    int namelen = namelist.length();

                    for(int j = 0; j<namelen; j++) {
                        menus.add(namelist.optString(j));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
