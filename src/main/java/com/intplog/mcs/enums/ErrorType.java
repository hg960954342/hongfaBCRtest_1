package com.intplog.mcs.enums;

/**
 * @author liaoliming
 * @Date 2019-11-07 09:45
 * 枚举类：异常设备类型
 */
public enum ErrorType {

    SIZEAlARM(1,"尺寸检测日常"),
    GCS(20,"GCS 四向车");

    private final int value;
    private final String desc;

    ErrorType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static ErrorType ofValue(Integer target){
        for(ErrorType type : values()){
            if(type.value == target){
                return type;
            }
        }
        return null;
    }

}
