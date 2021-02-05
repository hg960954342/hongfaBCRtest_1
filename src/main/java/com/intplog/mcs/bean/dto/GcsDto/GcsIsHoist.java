package com.intplog.mcs.bean.dto.GcsDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author quqingmao
 * @date 2020/6/10
 */
@Setter
@Getter
@ToString
public class GcsIsHoist {

    private String id;

    private String coord;

    private String rgvId;

    private String taskId;

    /**
     * 1进提升机 2出提升机
     */
    private String type;


}
