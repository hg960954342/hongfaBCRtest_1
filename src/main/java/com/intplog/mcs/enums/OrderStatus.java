package com.intplog.mcs.enums;
/**
 * @author liaoliming
 * @Date 2019-11-22 14:25
 * 枚举类：回告eis-wcs设备任务指令状态
 */
public enum OrderStatus {

    UNBEGIN(1,"未开始"),
    GETTED(2,"已下发（GCS/RCS）"),
    JOBING(3,"执行中（GCS/RCS）"),
    FINISHED(4,"已完成（GCS/RCS）"),
    REPORTED(5,"已回告（EIS）"),
    GCSERROR(6,"GCS拒绝数据");

    private final int value;
    private final String desc;

    OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatus ofValue(Integer target){
        for (OrderStatus status : values()) {
            if(status.value == target){
                return status;
            }
        }
        return null;
    }
}
