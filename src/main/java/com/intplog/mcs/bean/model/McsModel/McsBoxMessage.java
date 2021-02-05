package com.intplog.mcs.bean.model.McsModel;

import lombok.Data;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/9/24
 */
@Data
public class McsBoxMessage {
    /**
     * 主键id
     */
    private String id;
    /**
     * 滑道id
     */
    private int slideId;
    /**
     *是否有箱
     */
    private int isBox;
    /**
     * 时间
     */
    private Date createTime;

    private String plcDb;
}
