package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 15:19
 **/
@Setter
@Getter
@ToString
public class McsTaskOrderReport {

    /**
     * 主键
     */
    private  String id;
    /**
     * 任务号
     */
    private String taskId ;
    /**
     * 任务类型
     */
    private  int type;

    /**
     * 母托盘
     */
    private String stockId ;
    /**
     * 入库口（原位置）
     */
    private String source;
    /**
     * 目的位置
     */
    private String target;
    /**
     * 入库重量
     */
    private String weight;
    /**
     * 优先级
     */
    private int priority;
    /**
     * 任务状态
     */
    private int status;
    /**
     * 上报状态
     */
    private int reportStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
