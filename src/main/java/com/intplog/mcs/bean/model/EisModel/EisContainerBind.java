package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liaoliming
 * @Date 2019-10-31 11:08
 * 子母托盘绑定,WCS-EIS
 */
@Getter
@Setter
@ToString
public class EisContainerBind {

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
     * 子托盘
     */
    private String stockIdSub;
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
     * 尺寸检测
     */
    private int detection;
    /**
     * 优先级
     */
    private int priority;
    /**
     * 任务状态
     */
    private int status;
    /**
     * 创建时间
     */
    private Date createTime;

}
