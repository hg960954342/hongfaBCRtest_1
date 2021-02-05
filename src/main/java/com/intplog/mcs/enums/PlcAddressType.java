package com.intplog.mcs.enums;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2020-03-17 18:04
 **/
public enum PlcAddressType {

    EXIT(1,"出入口"),
    WEIGHT(2,"称重"),
    SIZE(3,"尺寸检测"),
    Straight(4,"直入直出口"),
    STATUS(5,"提升机状态"),
    StackStatus(6,"拆叠盘机"),
    StackTask(7,"拆叠盘机写值地址"),
    WrappingTask(8,"缠膜机"),
    StraightWeight(9,"直入直出口称重"),
    StraightSize(10,"直入直出口尺寸检测"),
    CrossLayer(11,"小车呼叫提升机"),
    CurrentFloor(12,"提升机当前层"),
    RequestApplication(13,"申请任务"),
    AlarmStatus(14,"设备状态地址"),
    PlcStatus(15,"设备状态地址"),
    AlarmAddress(16,"报警地址"),
    CalibrationAddress(17,"校准地址"),
    IsPermit(18,"是否允许进入提升机"),
    OutStatus(19,"接驳口光电信号"),
    OutClear(20,"接驳口光电信号"),
    intercept(21,"死档拦截信号"),
    inPlace(22,"托盘到达提升机"),
    PlcAddress(23,"箱式输送线plc地址");
    private final int value;
    private final String desc;

    PlcAddressType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static PlcAddressType ofValue(Integer target){
        for (PlcAddressType status : values()) {
            if(status.value == target){
                return status;
            }
        }
        return null;
    }
}
