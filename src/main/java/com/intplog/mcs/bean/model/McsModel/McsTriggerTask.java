package com.intplog.mcs.bean.model.McsModel;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
@Data
@ToString
public class McsTriggerTask {


    /**
     * 主键
     */
    private String id;
    /**
     * 读码器ID
     */
    private String bcrId;
    /**
     * 条码
     */
    private String bcrCode;
    /**
     * 长
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
    /**
     * 重量
     */
    private String weight;
    /**
     * 外形检测 true:合格  false:不合格
     */
    private boolean shape;
    /**
     * 状态1未使用，10已使用
     */
    private int status;
    /**
     * 时间
     */
    private Date createTime;

}
