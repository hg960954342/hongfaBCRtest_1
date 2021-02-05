package com.intplog.mcs.enums;

/**
 * @author quqingmao
 * @date 2020/6/14
 */
public enum HoistCrossLayerStatus {

    RECEIVE(1, "EIS任务下发"),
    ACTIVE(2, "已激活"),
    GCS1(8,"提升机到达目的层/下发小车进入提升机"),
    GCSTASK1(9,"gcs任务接收成功"),
    GCS3(10,"gcs出提升机完成"),
    GETTED(3,"提升机到达起始层/下发gcs去接驳口"),
    GCSTASK2(4,"gcs任务接收成功"),
    FINISHED(5,"gcs到达接驳口/下发小车进入提升机"),
    GCSTASK3(6,"gcs任务接收成功"),
    EISEND(7,"gcs进入提升机完成/调度提升机去目的层");
    private  final int value;

    private final String  desc;

    HoistCrossLayerStatus(int value,String desc){
        this.value =value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static HoistCrossLayerStatus ofValue(Integer target){
        for (HoistCrossLayerStatus status : values()) {
            if(status.value == target){
                return status;
            }
        }
        return null;
    }
}
