package com.intplog.mcs.bean.dto.EisDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author quqingmao
 * @date 2020/9/22
 */
@NoArgsConstructor
@Data
public class EisDistributionDto {

    /**
     * bcrId :
     * barCode :
     * empty :
     * shapeInspect : true
     * shapeInspectDesc :
     * taskId :
     * type : 0
     * weight :
     */

    private String bcrId;
    private String barCode;
    private int empty;
    private Boolean shapeInspect;
    private String shapeInspectDesc;
    private String taskId;
    private int type;
    private String weight;
}
