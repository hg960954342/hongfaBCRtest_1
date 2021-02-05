package com.intplog.mcs.bean.model.GcsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/17
 */
@Getter
@Setter
@ToString
public class GcsInOutTask {
    /**
     * 任务号
     *id 		  	  String	【32位uuid】
     * taskId 		  String	【任务号】
     * containerId   String    【容器编号】
     * floor		  Int       【楼层】
     * taskType	  Int       【任务类型：1:入库 2:出库 3:搬运】
     * priority      Int
     * locIdFrom     String    【源位置】
     * locIdTo       String    【目标位置】
     * createTime    String    【创建时间】
     *
     */
    private String id;


    private int floor;

    private String taskId ;
    /**
     * 任务类型1：入库:2：出库 3：同层移库
     */
    private  int taskType;

    /**
     * 【容器编号】
     */
    private String containerId;
    /**
     * 【请求位置:原坐标】
     */
    private String locIdFrom;
    /**
     * 目的位置
     */
    private String locIdTo;

    /**
     * 优先级
     */
    private int priority;
    /**
     * 【创建时间】
     */
    private  String createTime;
}
