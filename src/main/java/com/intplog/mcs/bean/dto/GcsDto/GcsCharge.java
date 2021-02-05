package com.intplog.mcs.bean.dto.GcsDto;

import lombok.Data;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/8/5
 */
@Data
public class GcsCharge {

    private  String id;
    /**
     * 当前层
     */
    private  Integer sourceFloor;
    /**
     * 目标层
     */
    private Integer targetFloor;
    /**
     * RGV编号
     */
    private  String rgvId;

    private Date createTime;

}
