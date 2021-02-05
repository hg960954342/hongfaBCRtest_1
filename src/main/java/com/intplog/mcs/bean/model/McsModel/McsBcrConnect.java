package com.intplog.mcs.bean.model.McsModel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class McsBcrConnect {
        /**
         * 主键(编号)
         */
        @Excel(name = "编号",width = 15)
        @NotBlank(message = "该字段不能为空")
        private String id;
        /**
         * ip地址
         */
        @Excel(name = "ip地址",width = 15)
        private String ip;
        /**
         * 端口号
         */
        @Excel(name = "端口号",width = 15)
        private Integer port;
        /**
         * 名称
         */
        @Excel(name = "名称",width = 15)
        private String name;
        /**
         * 备注
         */
        @Excel(name = "备注",width = 15)
        private String remark;

        @Override
        public boolean equals(Object o) {
                if (o instanceof McsBcrConnect) {
                        McsBcrConnect mcsBcrConnect = (McsBcrConnect) o;
                        return this.id.equals(mcsBcrConnect.id)
                                && this.ip.equals(mcsBcrConnect.ip)
                                && this.port.equals(mcsBcrConnect.port)
                                && this.name.equals(mcsBcrConnect.name)
                                &&this.remark.equals(mcsBcrConnect.remark);
                }
                return super.equals(o);
        }

}
