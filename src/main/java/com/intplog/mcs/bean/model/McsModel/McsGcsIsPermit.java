package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/9
 */
@Getter
@Setter
@ToString
public class McsGcsIsPermit {
    /**
     * id编号
     */
    private   String id;

    /**
     * plc名称
     */
    private  String plcName;

    /**
     * 提升机名称
     *
     */
    private String hoistName;

    /**
     * 进入提升机状态
     */
    private int isPermit;

    /**
     * 层
     */
    private String  layer;
}
