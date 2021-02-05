package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EisCrossLayerAlarm {
    /**
     * 主键
     */
    private  String id;
    /**
     * 任务编号
     */
    private  String taskId;
    /**
     * 错误类型
     */
    private  int errorType;
    /**
     * 提升机编号
     */
    private  String hoistId;
    /**
     * c错误消息
     */
    private String msg;
    /**
     * 生成时间
     */
    private String createTime;
}
