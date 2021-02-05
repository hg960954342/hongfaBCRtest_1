package com.intplog.mcs.enums;

/**
 * @author liaoliming
 * @Date 2019-11-06 18:00
 * 枚举类：回告wcs-eis设备类型
 */
public enum TaskType {

    InTask(1,"入库任务"),
    OutTAsk(2,"出库任务"),
    MoveTaSK(3,"跨层任务");

    private final int value;
    private final String desc;

    TaskType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static TaskType ofValue(Integer target){
        for(TaskType status : values()){
            if(status.value == target){
                return status;
            }
        }
        return null;
    }

}
