package com.intplog.mcs.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author quqingmao
 * @date 2020/9/25
 */
@NoArgsConstructor
@Data
public class JsonDataEis
{

    /**
     * code :
     * data : {"bcrId":"","destination":""}
     * message : 失败消息
     * success : true
     */

    private String code;
    private DataDTO data;
    private String message;
    private Boolean success;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * bcrId :
         * destination :
         */

        private String bcrId;
        private int destination;
        private int stackType;

    }
}
