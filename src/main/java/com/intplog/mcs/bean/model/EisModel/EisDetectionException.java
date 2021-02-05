package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/19
 */

@Getter
@Setter
@ToString
public class EisDetectionException {
    /**
     * detention String 【异常尺寸】
     * taskId String 【任务编号】
     * containerNo; String【容器号】
     */
    private String detention;

    private String taskId;

    private String  containerNo;
}
