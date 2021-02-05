package com.intplog.mcs.bean.model.McsModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2019-10-09
 */
@Getter
@Setter
@ToString
public class McsBcrProperties {
    /**
     * 编号
     */
    @Excel(name = "编号", width=15)
    private String id;

    /**
     * 名称
     */
    @Excel(name = "名称",width = 15)
    private String name;

    /**
     * 连接编号
     */
    @Excel(name = "连接编号",width = 15)
    private String connectId;

    /**
     * 备注
     */
    @Excel(name = "备注",width = 15)
    private String remark;
    /**
     * 读码器类型
     */
    @Excel(name = "读码器类型",width = 15)
    private int type;
    /**
     * 是否在线
     */
    @Excel(name = "",width = 15)
    private boolean isOnline;
}
