package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/12
 */
@Getter
@Setter
@ToString
public class EisRgvCrossLayer {
    /**
     *任务号
     */
    private String taskId;
    /**
     * 小车编号
     */
    private String rgvId;

    /**
     *  请求位置:源层
     */
    private int source;

    /**
     * 【目的位置：目的层】
     */
    private int target;

    /**
     * 【提升机编号】
     */
    private String  hoistId;
    /**
     * 【状态】
     */
    private int status;
}
