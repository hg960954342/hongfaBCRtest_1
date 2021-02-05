package com.intplog.mcs.enums;

/**
 * @author liaoliming
 * @Date 2019-12-04 17:50
 * 任务阶段枚举类：1.请求货位 2.生成货位 3.完成
 */
public enum TaskStage {

    SEND(1,"EIS任务下发"),
    START(10,"任务开始下发到PLC"),
    SendReport(2,"提升机到位"),
    Finish(3,"任务下发到gCS"),
    END(4,"GCS回告完成");


    private final int value;
    private final String desc;

    TaskStage(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static TaskStage ofValue(Integer target){
        for(TaskStage status : values()){
            if(status.value == target){
                return status;
            }
        }
        return null;
    }
}
