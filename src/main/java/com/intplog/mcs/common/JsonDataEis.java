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
     * data : {"bcrId":"","destination":0,"point":""}
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
         * destination : 0
         * point :
         */

        private String bcrId;
        private int destination;
        private String point;
    }
}
