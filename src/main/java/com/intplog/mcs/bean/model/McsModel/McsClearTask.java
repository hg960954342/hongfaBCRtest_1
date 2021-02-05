package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class McsClearTask {
    /**
     * 主键
     */
    private String id;
    /**
     * eis下发任务号
     */
    private String taskId;
    /**
     * 托盘号
     */
    private String stockId;
    /**
     * 清除位置坐标
     */
    private String clearCrood;
    /**
     * 生成时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 任务状态：0：任务下发；1：清除完成
     */
    private int status;

    /**
     * 任务类型
     */
    private int type;

    /**
     * PLC名称
     */
    private String plcName;
    /**
     * 提升机编号
     */
    private String hoistId;
    /**
     * 原地址类型
     */
    private int addressType;
}
