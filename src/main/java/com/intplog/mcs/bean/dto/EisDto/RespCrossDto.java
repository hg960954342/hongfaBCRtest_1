package com.intplog.mcs.bean.dto.EisDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RespCrossDto {
    /**
     * 返回数据
     */
    private Boolean success;
    /**
     * 状态编号（0:操作成功，1-99自定义错误,1部分错误，2全部错误，401: token不存在或已失效，404:未找到对应资源）
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 发生错误的原件,如ID,Name等
     */
    private String data;
}
