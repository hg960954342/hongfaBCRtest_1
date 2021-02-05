package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class EisAlarmReport {

    /**
     * 任务号
     */
    private String id;
    /**
     * PLC编号
     */
    private String plcId;
    /**
     * 上报数据
     */
    private String content ;
}
