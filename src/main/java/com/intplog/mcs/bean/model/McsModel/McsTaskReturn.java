package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/6/12
 */

@Setter
@Getter
@ToString
public class McsTaskReturn {

    private String id;

    /**
     * 任务号
     */
    private String taskId;

    private String mcsId;

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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    private int reportStatus;

    private String detection;

}
