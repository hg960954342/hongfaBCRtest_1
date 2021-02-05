package com.intplog.mcs.plc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-09 15:07
 **/
@Setter
@Getter
@ToString
public class HoistPlc {
    /**
     *plc地址
     */
    private String address;
    /**
     *任务类型
     */
    private  int type;
    /**
     *提升机出入库方向
     */
    private  int direction;
    /**
     *流水号
     */
    private  String taskCode;
    /**
     *是否错误退回 1正常 2 退回
     */
    private  int Error;
    /**
     * 目标层
     */
    private  int TargetLayer;
    /**
     * 尺寸检测
     */
    private  int detection;
    /**
     * 是否称重
     */
    private  boolean isWeight;
    /**
     * 坐标
     */
    private String crood;
    /**
     * 数据是否更新
     */
    private  boolean change;

}
