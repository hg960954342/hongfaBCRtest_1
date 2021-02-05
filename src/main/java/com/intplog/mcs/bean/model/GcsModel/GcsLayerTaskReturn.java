package com.intplog.mcs.bean.model.GcsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/21
 */

@Getter
@Setter
@ToString
public class GcsLayerTaskReturn {


    /**
     * 【32位uuid】
     */
    private String id;
    /**
     * 【任务号】
     */
    private String taskId;
    /**
     * 【楼层】
     */
    private int floor;

    /**
     * 【1接驳口  2进入  3出】
     */
    private int taskType;

    /**
     * 【提升机编号】
     */
    private String hoistId;

    /**
     * 【创建时间】
     */
    private String createTime;

    /**
     * 【rgv小车编号】
     */
    private String rgvId;
    /**
     * 【作业状态:1:开始  2:结束】
     */
    private int workStatus;

}
