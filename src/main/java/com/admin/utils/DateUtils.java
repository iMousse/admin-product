package com.admin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-12
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class DateUtils {


    /**
     * 日期转换成字符串
     */
    public static String date2String(Date date, String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(date);
    }


    /**
     * 字符串转换为日期
     */
    public static Date string2Date(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("运行");
        }
        return parse;
    }

}
