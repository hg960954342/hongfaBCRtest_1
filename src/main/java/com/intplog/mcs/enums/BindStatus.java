package com.intplog.mcs.enums;

public enum BindStatus {

    FIRSTCODEREPORT(1,"读码器条码1上报"),
    TWOCODEREPORT(2,"读码器条码1上报"),
    WEIGHTREPORT(3,"称数据上报完成，可以开始上报EIS"),
    REPORETD(4,"已上报");

    private final int value;
    private final String desc;

    BindStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static BindStatus ofValue(Integer target){
        for(BindStatus status : values()){
            if(status.value == target){
                return status;
            }
        }
        return null;
    }

}
