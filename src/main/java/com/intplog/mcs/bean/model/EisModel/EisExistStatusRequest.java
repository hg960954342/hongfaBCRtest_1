package com.intplog.mcs.bean.model.EisModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class EisExistStatusRequest {
    /**
     * 出入库空坐标
     */
   private String coord;
}
