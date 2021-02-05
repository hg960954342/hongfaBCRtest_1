package com.intplog.mcs.plc.common;

import com.intplog.mcs.enums.TaskType;

import java.util.Date;

public class Utils {

    /**
     * 获取当前时间 - 500毫秒 的差值
     *
     * @param startDate
     */
    public static long getTimeDifference(Date startDate) {
        Date endDate = new Date();
        long different = endDate.getTime() - startDate.getTime();
        long value = 600 - different;
        return value > 0 ? value : 10;
    }

    /**
     * 获取写值目标层
     *
     * @param type
     * @param target
     * @return
     */
    public static int getLayer(int type, String target) {
        int layer = 0;
        if (type == TaskType.InTask.getValue()) {
            String getZ = target.substring(0, 2);
            layer = Integer.parseInt(getZ);
        } else if (type == TaskType.OutTAsk.getValue()) {
            String getZ = target.substring(0, 2);
            layer =  Integer.parseInt(getZ);
        } else {
            String getZ = target.substring(0, 2);
            layer = Integer.parseInt(getZ);
        }
        return layer;
    }

    /**
     * 获取写值目标口
     *
     * @param target
     * @return
     */
    public static int getDirection(String target) {
        int values = 0;
        switch (target) {
            case "-1":
                values = 3;
                break;
            default:
                values = 0;

        }
        return values;
    }

    /**
     * 任务下发转换成byte
     *
     * @param type
     * @param targetLayer
     * @param taskId
     * @param direction
     * @return
     */
    public static byte[] taskByte(int type, int targetLayer, String taskId, int direction) {
        int repeat = 0;
        if (type == 4) {
            //手动触发重复入库14个byte写1
            repeat = 1;
            type=0;
            taskId="";
            direction=0;
            targetLayer=0;
        }
        byte[] bytes = new byte[16];
        bytes[0] = (byte) ((type >> 8) & 0xFF);
        bytes[1] = (byte) ((type) & 0xFF);
        byte[] code = taskId.getBytes();
        for (int i = 0; i < code.length; i++) {
            bytes[2 + i] = code[i];
        }
        bytes[12] = (byte) ((direction) & 0xFF);
        bytes[13] = (byte) ((repeat) & 0xFF);
        bytes[14] = (byte) ((targetLayer >> 8) & 0xFF);
        bytes[15] = (byte) ((targetLayer) & 0xFF);
        return bytes;
    }

    public static byte[] wcsToPlcByte(int ready,int address,String barCode){
        byte[] bytes = new byte[16];
        bytes[0] = (byte) ((ready >> 8) & 0xFF);
        bytes[1] = (byte) ((ready) & 0xFF);
        bytes[2] = (byte) ((address >> 8) & 0xFF);
        bytes[3] = (byte) ((address) & 0xFF);
        byte[] code = barCode.getBytes();
        System.arraycopy(code, 0, bytes, 4, code.length);
        return bytes;
    }
    public static byte[] intToByte(int ready,int address){
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((ready >> 8) & 0xFF);
        bytes[1] = (byte) ((ready) & 0xFF);
        bytes[2] = (byte) ((address >> 8) & 0xFF);
        bytes[3] = (byte) ((address) & 0xFF);
        return bytes;
    }
    /**
     * byte[] 转 Int
     *
     * @param bytes
     * @param offset
     * @return
     */
    public static int bytesToInt(byte[] bytes, int offset) {
        //如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
        int int1 = bytes[offset + 3] & 0xff;
        int int2 = (bytes[offset + 2] & 0xff) << 8;
        int int3 = (bytes[offset + 1] & 0xff) << 16;
        int int4 = (bytes[offset] & 0xff) << 24;
        return int1 | int2 | int3 | int4;
    }

    /**
     * @param bytes
     * @param offset
     * @return
     */
    public static int bytesToShort(byte[] bytes, int offset) {
        //如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
        int int1 = bytes[offset + 1] & 0xff;
        int int2 = (bytes[offset] & 0xff) << 8;
        return int1 | int2;
    }

    public static byte[]  outByte(int type,int taskId,int targetLayer ){
        byte[] result = new byte[16];
        result[0] = (byte)((type >> 24) & 0xFF);
        result[1] = (byte)((type >> 16) & 0xFF);
        result[2] = (byte)((type >> 8) & 0xFF);
        result[3] = (byte)(type & 0xFF);

        result[4] = (byte)((taskId >> 24) & 0xFF);
        result[5] = (byte)((taskId >> 16) & 0xFF);
        result[6] = (byte)((taskId >> 8) & 0xFF);
        result[7] = (byte)(taskId & 0xFF);

        result[8] = (byte)(( taskId>> 24) & 0xFF);
        result[9] = (byte)((taskId >> 16) & 0xFF);
        result[10] = (byte)((taskId >> 8) & 0xFF);
        result[11] = (byte)( taskId & 0xFF);

        result[12] = (byte)((targetLayer >> 24) & 0xFF);
        result[13] = (byte)((targetLayer >> 16) & 0xFF);
        result[14] = (byte)((targetLayer >> 8) & 0xFF);
        result[15] = (byte)(targetLayer & 0xFF);

        return result;
    }

    /**
     * 根据·坐标获取，plc点位类型
     *
     * @param coord
     * @return
     */
    public static int getType(String coord) {
        int type = 1;
        int floor = Integer.parseInt(coord.substring(0, 2));
        if (floor == 1 || floor == 2) {
            type = 3;
        } else {
            type = 10;
        }
        return type;
    }

}
