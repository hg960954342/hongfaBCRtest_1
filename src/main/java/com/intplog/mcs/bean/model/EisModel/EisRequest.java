package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-04 14:55
 **/
@Getter
@Setter
@ToString
public class EisRequest {
    /**
     * 任务号
     */
    private String taskId ;
    /**
     * 任务类型
     */
    private  int type;

    private String  bankId;




    private String containerNo;

    private String address;



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
     * 尺寸检测
     */
    private int detection;
    /**
     * 优先级
     */
    private int priority;


}
