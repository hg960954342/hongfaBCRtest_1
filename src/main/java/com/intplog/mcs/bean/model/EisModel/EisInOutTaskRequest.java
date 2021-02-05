package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/13
 */
@Getter
@Setter
@ToString
public class EisInOutTaskRequest {
    /**
     * 任务号
     */
    private String taskId ;
    /**
     * 任务类型1：入库:2：出库 3：同层移库
     */
    private  int type;

    /**
     * 【料箱编号】
     */
    private String containerNo;
    /**
     * 【请求位置:原坐标】
     */
    private String address;
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
}
