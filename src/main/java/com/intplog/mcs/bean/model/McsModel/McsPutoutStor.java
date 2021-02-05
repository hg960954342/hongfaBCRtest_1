package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/8/13
 */

@Setter
@Getter
@ToString
public class McsPutoutStor {
    /**
     * 任务号
     */
    private String taskId ;
    /**
     * mcs内部流水号
     */
    private String mcsId;

    private String rgvId;
    /**
     * 任务类型
     */
    private  int type;
    /**
     * 提升机编号
     */
    private String hoistId;
    /**
     * PLC编号
     */
    private String plcName;

    /**
     * 母托盘
     */
    private String stockId ;
    /**
     * 原地址类型
     */
    private int addressType ;
    /**
     * 出入库口（原位置）
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
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 【请求位置:原坐标】
     */
    private String address;

    private int layer;
}
