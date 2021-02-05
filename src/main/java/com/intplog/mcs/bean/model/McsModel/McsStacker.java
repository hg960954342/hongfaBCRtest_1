package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McsStacker {

    /**
     * 主键
     */
    private String id;
    /**
     *拆叠盘机编号
     */
    private String number;
    /**
     * 拆叠盘机状态
     */
    private int status;
    /**
     * 坐标
     */
    private String coord;
    /**
     * 类型
     */
    private int type;
    /**
     * PLC名称
     */
    private String plcName;
    /**
     * PLC地址
     */
    private String address;
    /**
     * 条码
     */
    private String containerCode;
}
