package com.intplog.mcs.enums;

/**
 * @author liaoliming
 * @Date 2019-11-22 11:02
 * 枚举类：回告wcs-eis作业状态
 */
public enum ReportStatus {

    notReport(0,"未上报"),
    reported(1,"已上报");


    private final int value;
    private final String desc;

    ReportStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
    
    public static ReportStatus ofValue(Integer target){
        for (ReportStatus status : values()) {
            if(status.value == target){
                return status;
            }
        }
        return null;
    }
}
