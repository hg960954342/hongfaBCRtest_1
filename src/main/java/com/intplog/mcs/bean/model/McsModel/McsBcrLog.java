package com.intplog.mcs.bean.model.McsModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2020-02-22 15:28
 **/
@Getter
@Setter
@ToString
public class McsBcrLog {

    /**
     * 主键
     */
    @Excel(name = "主键",width = 15)
    private String id ;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间",width = 15)
    private Date createTime;
    /**
     * 读码器名称
     */
    @Excel(name = "读码器名称",width = 15)
    private String name;
    /**
     * 条码
     */
    @Excel(name = "条码",width = 15)
    private String boxNum;
    /**
     * 读码器编号
     */
    @Excel(name = "读码器编号",width = 15)
    private String bcrNum;
    /**
     * 备注
     */
    @Excel(name = "备注",width = 15)
    private String remark;
}
