package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-07 13:19
 **/
@Getter
@Setter
@ToString
public class EisTaskAlarm extends EisRequest{


    /**
     * 任务状态
     */
    private  int status;
    /**
     * 错误类型，根据错误列表匹配
     */
    private  int errorType;
    /**
     * 错误内用
     */
    private  String msg;

}
