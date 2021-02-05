package com.intplog.mcs.common;

import java.util.Date;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author quqingmao
 * @date 2020/7/27
 */
public class Utils {
    /**
     * 获取当前时间 - 300毫秒 的差值
     *
     * @param startDate
     */
    public static long getTimeDifference(Date startDate) {
        Date endDate = new Date();
        long different = endDate.getTime() - startDate.getTime();
        long value = 500 - different;
        return value > 0 ? value : 10;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
