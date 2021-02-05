package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/16
 */
@Getter
@Setter
@ToString
public class EisTaskReturn {
    /**
     * 任务号
     */
    private String taskId;

    /**
     * 任务状态
     */
    private int status;
    /**
     *任务类型
     */
    private int type;
    /**
     * 托盘编号
     */
    private String containerNo;
    /**
     * 小车编号
     */
    private String rgvId;
    /**
     * 当前点位
     */
    private String address;
}
