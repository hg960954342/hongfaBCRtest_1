package com.intplog.mcs.utils;

import com.google.common.base.Splitter;
import com.intplog.siemens.handler.SiemensHandler;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StringUtil {

    private static int taskSN = 1;

    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    /**
     * 时间转换为字符串
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 生成随机不重复的10位数字：日时分秒+3位流水号
     */
    public static synchronized String getTaskCode() {

        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String dateStr = sdf.format(new Date());
        int number = taskSN > 9999 ? 1 : taskSN++;
        return dateStr + String.format("%04d", number);
    }

    /**
     * bytes转换成 16进制字符串
     *
     * @param in
     * @param offset
     * @param count
     * @return
     */
    public static String bytesToHexStr(ByteBuf in, int offset, int count) {
        String temp = "";
        int n = 0;
        for (int i = offset; i < count; i++) {
            n = in.getByte(i) & 0xff;
            temp += String.format("%02X", n) + " ";
        }
        return temp;
    }

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(SiemensHandler.class);

    /**
     * 显示日志信息
     *
     * @param msg
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * 显示错误信息
     *
     * @param msg
     */
    public static void error(String msg) {
        logger.error(msg);
    }


}
