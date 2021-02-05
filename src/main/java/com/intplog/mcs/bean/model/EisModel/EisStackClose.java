package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EisStackClose {
    /**
     * 拆盘机编号
     */
    private String  deviceNo;
    /**
     * 条码
     */
    private  String containerNo;
}
