package com.intplog.mcs.enums;

/**
 * @author liaoliming
 * @Date 2019-11-22 16:39
 * 枚举类：业务异常类型(母托盘入库异常接口用到)
 */
public enum ErrorBindType {

    SIZEERROR(1,"外形异常"),
    CODEISEMPTY(2,"母托盘编号异常"),
    LOCATIONERROR(3,"货位异常");

    private final int value;
    private final String desc;

    ErrorBindType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static ErrorBindType ofValue(Integer target){
        for(ErrorBindType type : values()){
            if(type.value == target){
                return type;
            }
        }
        return null;
    }
}
