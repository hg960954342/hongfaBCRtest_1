package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
@Getter
@Setter
@ToString
public class RgvData {
    /**
     * rgvId String 小车id
     * layer int 层
     * status int 状态 0-不可用 1-
     */


    private String rgvId;

    private int layer;

    private int status;

    private int alarm;

    private String currCoord;

    private int isUp;

}
