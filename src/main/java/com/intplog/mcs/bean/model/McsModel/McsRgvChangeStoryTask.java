package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/6/14
 */

@Setter
@Getter
@ToString
public class McsRgvChangeStoryTask {

    /**
     *主键
     */
    private String id;

    /**
     *任务号
     */
    private String taskId;

    private String gcsId;
    /**
     * 请求位置:源层
     */
    private int source;
    /**
     * 目的位置：目的层
     */
    private int target;
    /**
     * 【小车编号】
     */
    private String rgvId;
    /**
     *提升机编号
     */
    private String hoistId;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 任务状态
     */
    private  int status;
}
