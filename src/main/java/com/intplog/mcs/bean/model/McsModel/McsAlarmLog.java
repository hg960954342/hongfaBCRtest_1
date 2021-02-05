package com.intplog.mcs.bean.model.McsModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class McsAlarmLog {
        /**
         * 主键
         */
        @Excel(name = "编号",width = 15)
        private String id;
        /**
         * 生成时间
         */
        @Excel(name = "创建时间",width = 15)
        private Date createTime;
        /**
         * 故障类型
         */
        @Excel(name = "故障类型",width = 15)
        private String type;
        /**
         * 内容
         */
        @Excel(name = "内容",width = 15)
        private String content;
}
