package com.intplog.mcs.plc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class AlarmStatus {
    /**
     * 读取地址
     */
    private String address;
    /**
     * 读取数据
     */
    private String bytes;
    /**
     * 设备名称
     */
    private String name;
}
