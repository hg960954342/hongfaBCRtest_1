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

import java.util.Date;

/**
 * PLC配置
 */
@Getter
@Setter
@ToString
public class McsSlidCount {

    /**
     * 滑道编号
     */
    @Excel(name = "滑道编号", width = 15)
    @NotBlank(message = "该字段不能为空")
    public String slidId;

    /**
     * 码箱计数
     */
    @Excel(name = "码箱计数", width = 15)
    public String boxCount;

    /**
     * 码箱次数
     */
    @Excel(name = "码箱次数", width = 15)
    public String actionCount;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", width = 15)
    public Date creatTime;

    /**
     * 数量读值地址
     */
    @Excel(name = "数量读值地址", width = 15)
    public String address;

    /**
     * 抓取动作完成数量读取地址
     */
    @Excel(name = "抓取动作完成数量读取地址", width = 15)
    public String address1;

    /**
     * PLC类型
     */
    @Excel(name = "PLC名称", width = 15)
    public String plcName;

}
