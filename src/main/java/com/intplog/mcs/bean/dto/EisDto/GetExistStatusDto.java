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
public class GetExistStatusDto {
    /**
     * 坐标
     */
    private String coord;
    /**
     * 是否为空
     */
    private boolean isEmpty;
}
