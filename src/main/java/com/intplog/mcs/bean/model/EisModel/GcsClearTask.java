package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GcsClearTask {
    /**
     * 主键
     */
    private String id;

    /**
     * 任务编号
     */
    private String taskId;

    /**
     * 容器编号
     */
    private String containerId;

    /**
     * 源位置:mcs回告出库任务开始的源位置
     */
    private String locIdFrom;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态:1未清除；2已清除
     */
    private Integer status;

    /**
     * 任务类型:1为gcs入库开始任务    2为mcs出库开始任务
     */
    private Integer type;
}
