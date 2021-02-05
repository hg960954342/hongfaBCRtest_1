package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class McsCrossLayerReport {
    /**
     *主键
     */
    private String id;
    /**
     *任务号
     */
    private String taskId;
    /**
     *楼层
     */
    private int floor;
    /**
     *任务类型
     */
    private int  taskType ;
    /**
     * 任务状态
     */
    private  int workStatus;
    /**
     *提升机编号
     */
    private String hoistId;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 上报状态
     */
    private int reportStatus;
}
