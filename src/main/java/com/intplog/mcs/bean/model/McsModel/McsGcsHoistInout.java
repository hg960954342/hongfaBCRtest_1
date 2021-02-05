package com.intplog.mcs.bean.model.McsModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/6/7
 */
@Getter
@Setter
@ToString
public class McsGcsHoistInout {
    /**
     * 主键
     */
    private String id;

    /**
     * 提升机编号
     */
    private String hoistName;

    /**
     * 是否到位
     */
    private String inPlace;

    /**
     * 类型
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    private String coord;
}
