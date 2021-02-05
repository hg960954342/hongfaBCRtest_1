package com.intplog.mcs.bean.dto.EisDto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author quqingmao
 * @date 2020/9/22
 */
@NoArgsConstructor
@Data
public class EisBoxCheckReply {

    /**
     * code :
     * data :
     * message :
     * success : false
     */

    private String code;
    private String data;
    private String message;
    private Boolean success;
}
