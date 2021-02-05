package com.intplog.mcs.bean.model.GcsModel;

import com.intplog.mcs.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
@Getter
@Setter
@ToString
public class RgvInfo {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "RGV类型")
    private String rgvType;
    @Excel(name = "楼层编号")
    private Integer floor;
    @Excel(name = "模式")
    private boolean auto;
    @Excel(name = "IP地址")
    private String ip;
    @Excel(name = "区域")
    private String area;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "等待区域")
    private String waitNode;
    @Excel(name = "剩余电量")
    private Integer dumpEnergy;
    @Excel(name = "当前电压")
    private Integer dumpVoltage;
    @Excel(name = "是否空闲")
    private boolean isLeisure;
    @Excel(name = "故障信息")
    private Integer alarm;
    @Excel(name = "当前坐标")
    private String currCoord;
    @Excel(name = "是否托举")
    private boolean isUp;
    @Excel(name = "容器编号")
    private String containerId;
    @Excel(name = "是否在线")
    private boolean isOnline;
    private boolean isUse;
    private Date createTime;
    private Date updateTime;
}