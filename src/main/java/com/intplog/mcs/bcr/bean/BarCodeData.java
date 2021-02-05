package com.intplog.mcs.bcr.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @Classname BarCodeData
 * @Description TODO
 * @Date 2020/2/24 16:09
 * @Created by jiangzhongxing
 */
@Getter
@Setter
@ToString
public class BarCodeData {
    /**
     * 编号
     */
    private String id;

    /**
     * 条码
     */
    private String barcode;

    /**
     *长
     */
    private String length;
    /**
     * 宽
     */
    private String width;
    /**
     * 高
     */
    private String height;
}
