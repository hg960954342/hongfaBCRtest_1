package com.intplog.mcs.bean.dto.EisDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class GetHoistStatus {
    /**
     * 提升机编号
     */
    private String hoistId;
    /**
     * 当前层
     */
    private int layer;
    /**
     * 状态
     */
    private int status;
    /**
     * 是否锁定
     */
    private int lock;
}
