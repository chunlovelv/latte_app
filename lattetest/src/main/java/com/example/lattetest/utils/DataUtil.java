package com.example.lattetest.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明:
 */
public class DataUtil {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");

    public static String getCurrentTime() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String long2Second(long timeMillon) {
        return timeMillon / 1000 + "";
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTime());
    }
}
