package com.taotao.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获得日期类型
 * @author zhaoyuan
 * @date 2019,Nov 26 11:33 am
 */
public class DateUtils {

    /**
     * 类型：yyyy-MM-dd
     * @return
     */
    public static Date  getDate(){
        Date currentDate = new Date();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String dateStr = dateFormat.format(currentDate);

        Date parseDate = null;
        
        try {
            parseDate = dateFormat.parse(dateStr);
            System.out.println(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return parseDate;
    }

    public static void main(String[] args) {
        DateUtils.getDate();
    }

}
