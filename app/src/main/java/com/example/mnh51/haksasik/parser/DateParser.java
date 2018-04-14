package com.example.mnh51.haksasik.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mnh51 on 2018-04-14.
 */

/**
 * JSON형식의 date 정보를 파싱하는 Class
 * getDate(day) 입력으로 요일 입력시 해당 날짜 반환
 * 날짜는 day(yyyy-mm-dd) 형식으로 반환됨. ( ex : 일(2018-04-08) )
 */

public class DateParser {

    String dateJSON;

    public DateParser(String dateJSON) {
        this.dateJSON = dateJSON;
    }

    public String getDate(int day) {

        try {

            JSONArray datelist = new JSONArray(dateJSON);

            int len = datelist.length();
            for(int i = 0; i<len; i++) {
                JSONObject date = datelist.getJSONObject(i);

                int day_ = date.optInt("day");

                if(day == day_) {
                    String result = date.optString("date");
                    return result;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }
}
