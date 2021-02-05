package com.intplog.mcs.bean.model.McsModel;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/9 17:26
 */

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * MCS任务日志
 */
@Getter
@Setter
@ToString
public class McsTaskLog {
    /**
     * 编号
     */
    @Excel(name = "编号", width = 15)
    @NotBlank(message = "该字段不能为空")
    public String id;

    /**
     * 周转箱号
     */
    @Excel(name = "周转箱号", width = 15)
    public String boxNum;

    /**
     * 内容
     */
    @Excel(name = "内容", width = 15)
    public String content;

    /**
     * 任务类型 1：入库，2、出库
     */
    @Excel(name = "任务类型", width = 15)
    public String type;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30)
    public Date createTime;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", width = 30)
    public Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (o instanceof McsTaskLog) {
            McsTaskLog mcsTaskLog = (McsTaskLog) o;
            return this.id.equals(mcsTaskLog.id)
                    && this.boxNum.equals(mcsTaskLog.boxNum)
                    && this.content.equals(mcsTaskLog.content)
                    && this.type.equals(mcsTaskLog.type)
                    && this.createTime.equals(mcsTaskLog.createTime)
                    && this.updateTime.equals(mcsTaskLog.updateTime);
        }
        return super.equals(o);
    }
}
