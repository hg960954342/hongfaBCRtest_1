package com.intplog.mcs.bean.model.McsModel;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/9 17:16
 */

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * PLC配置
 */
@Getter
@Setter
@ToString
public class McsPlcVariable1 {

    /**
     * 编号
     */
    @Excel(name = "BCR编号", width = 15)
    @NotBlank(message = "该字段不能为空")
    public String bcrId;

    /**
     * PLC类型
     */
    @Excel(name = "PLC名称", width = 15)
    public String plcName;

    /**
     * 准备号写值地址
     */
    @Excel(name = "写值地址", width = 15)
    public String readyAddress;

    /**
     * 任务号写值地址
     */
    @Excel(name = "写值地址", width = 15)
    public String forkNumAddress;

    /**
     * 码垛类型写值地址
     */
    @Excel(name = "写值地址", width = 15)
    public String stackTypeAddress;

}
