package com.intplog.mcs.enums;

public enum BusinessType {

    PUTIN(1,"入库"),
    PUTOUT(2,"出库"),
    MOVE(3,"搬运");

    private final int value;
    private final String desc;

    BusinessType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static BusinessType ofValue(Integer target){
        for(BusinessType status : values()){
            if(status.value == target){
                return status;
            }
        }
        return null;
    }

}
