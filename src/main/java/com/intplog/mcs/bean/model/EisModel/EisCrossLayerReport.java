package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EisCrossLayerReport {
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
    private int taskType ;
    /**
     *提升机编号
     */
    private String hoistId;
    /**
     *创建时间
     */
    private String createTime;
    /**
     * 任务状态
     */
    private int workStatus;
}
