package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author tianlei
 * 设备状态
 */
@Getter
@Setter
@ToString
public class EisEquipmentStatus {
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备编号
     */
    private String deviceNo;
    /**
     * 是否在线
     */
    private boolean  isOnline;
    /**
     * 生成时间
     */
    private String createTime;

}
