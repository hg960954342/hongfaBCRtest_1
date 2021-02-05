package com.intplog.mcs.bean.model.McsModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class McsHoist {

    /**
     * 提升机编号
     */
    @Excel(name = "提升机编号", width = 15)
    private String id;
    /**
     * 状态
     */
    @Excel(name = "状态", width = 15)
    private int status;
    /**
     * 故障内容
     */
    @Excel(name = "故障内容", width = 15)
    private String content;
    /**
     * 提升机工作类型
     */
    @Excel(name = "提升机工作类型", width = 15)
    private int type;
    /**
     * 是否跨层锁定
     */
    @Excel(name = "是否跨层锁定", width = 15)
    private int lock;
    /**
     *连接PLc名称
     */
    @Excel(name = "连接PLC名称", width = 15)
    private String PlcName;
    /**
     * 当前楼层
     */
    private int currentFloor;
    /**
     * 能否进入提升机
     */
    private int isPermit;

    private int taskStatus;

}