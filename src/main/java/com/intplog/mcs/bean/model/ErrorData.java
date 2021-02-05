package com.intplog.mcs.bean.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorData {
    private String taskId;
    private String errMsg;
}
