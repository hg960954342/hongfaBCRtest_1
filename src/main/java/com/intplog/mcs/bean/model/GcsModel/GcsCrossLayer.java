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
public class GcsCrossLayer {
    /**
     * 【32位uuid】
     */
    private String id;

    private String rgvId;
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

}
