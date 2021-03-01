package com.intplog.mcs.plc.common;

import org.springframework.util.StringUtils;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-10 13:26
 **/
public class PlcGetByte {

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
     *
     * @param value 要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[3] = (byte) ((value >> 24) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用
     */
    public static byte[] intToBytes2(int value) {
        byte[] src = new byte[2];
        src[0] = (byte) ((value >> 8) & 0xFF);
        src[1] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * byte数组中取 一个byte int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param src    byte数组
     * @param offset 从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt16by1(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF));
        return value;
    }

    /**
     * byte数组中取两个byte int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param src    byte数组
     * @param offset 从数组的第offset位开始
     * @return
     */
    public static int bytesToInt16by2(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8));
        return value;
    }

    public static int bytesToInt32(byte[] src, int offset) {
        int value;
        value = (int) (((src[offset] & 0xFF))
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | (src[offset + 3] & 0xFF) << 24);
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
     */
    public static int bytesToInt16Hight(byte[] src, int offset) {
        int value;
        value = (int) (((src[offset] & 0xFF) << 24));
        return value;
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
     */
    public static int bytesToInt16Hight2(byte[] src, int offset) {
        int value;
        if (src.length < src.length + 2) {
            value = (int) (((src[offset] & 0xFF) << 8)
                    | ((src[offset + 1] & 0xFF)));
        } else {
            value = 0;
        }
        return value;
    }

    /**
     * byte数组中取Dint数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
     */
    public static int bytesToInt32Hight(byte[] src, int offset) {
        int value;
        value = (int) (((src[offset] & 0xFF) << 24)
                | ((src[offset + 1] & 0xFF) << 16)
                | ((src[offset + 2] & 0xFF) << 8)
                | (src[offset + 3] & 0xFF));
        return value;
    }

    /**
     * 从一个byte[]数组中截取一部分
     *
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++) bs[i - begin] = src[i];
        return bs;
    }

    /**
     * 数组反转
     *
     * @param scr
     * @return
     */
    public static byte[] arryByte(byte[] scr) {
        int count = scr.length;
        byte[] bs = new byte[count];
        for (int i = count - 1; i >= 0; i--) {
            bs[count - 1 - i] = scr[i];
        }
        return bs;

    }

    /**
     * byte数组转String
     *
     * @param scr   byte数组
     * @param begin 截取起始位置
     * @param count 截取数组长度
     * @return
     */
    public static String byteToString(byte[] scr, int begin, int count) {
        byte[] bytes = subBytes(scr, begin, count);
        try {
            int length = 0;
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != 0) {
                    length = i+1;
                    continue;
                }
            }
            return new String(bytes, 0, length, "UTF-8");
        } catch (Exception ex) {
            return "";
        }
    }


    /**
     * 16进制的字符串表示转成字节数组
     *
     * @param hexString 16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            throw new IllegalArgumentException("this hexString must not be empty");
        }
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10) {//0~F前面不零
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * byte数组转String去除后面的0
     *
     * @param buffer
     * @return
     */
    public static String byteToStr(byte[] buffer) {
        try {
            int length = 0;
            for (int i = 0; i < buffer.length; ++i) {
                if (buffer[i] == 0) {
                    length = i;
                    break;
                }
            }
            return new String(buffer, 0, length, "UTF-8");
        } catch (Exception e) {
            return "";
        }
    }

}
