package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 14:24
 **/
@Getter
@Setter
@ToString
public class EisTaskReport extends  EisRequest {

    /**
     * 任务状态
     */
    private int status;
}
