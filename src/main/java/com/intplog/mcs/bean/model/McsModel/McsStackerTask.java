package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class McsStackerTask {
    /**
     * 主键
     */
    private String id;
    /**
     *拆叠盘机编号
     */
    private String deviceNo;
    /**
     * 上报状态
     */
    private int reportStatus;
    /**
     * 条码
     */
    private String number;
    /**
     * 类型
     */
    private int type;
    /**
     * 生成时间
     */
    private Date createTime;
}
