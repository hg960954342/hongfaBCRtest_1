package com.intplog.mcs.bean.model.GcsModel;

import lombok.Data;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/8/1
 */
@Data
public class GcsAlarm {
//     `id` varchar(32) NOT NULL COMMENT '编号',
//            `task_id` varchar(32) NOT NULL COMMENT '任务号(EIS生成的任务单号)',
//            `container_id` varchar(32) DEFAULT NULL COMMENT '执行母托盘号',
//            `error_type` int(32) NOT NULL COMMENT '异常类型1:RGV故障 2:托盘不可用 3:其他',
//            `msg` varchar(255) DEFAULT NULL COMMENT '消息内容',
//            `report_status` int(32) NOT NULL COMMENT '任务状态 1未上报 2已上报',
//            `create_time` varchar(32) DEFAULT NULL COMMENT 'GCS异常回告时间',
//
    /**
     * 编号
     */
    private String id;
    /**
     * 任务号
     */
    private String taskId;
    /**
     * 执行母托盘号
     */
    private String containerId;
    /**
     * 异常类型1:RGV故障 2:托盘不可用 3:其他
     */
    private int errorType;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 任务状态 1未上报 2已上报
     */
    private int reportStatus;
    /**
     * GCS异常回告时间
     */
    private Date createTime;
}
