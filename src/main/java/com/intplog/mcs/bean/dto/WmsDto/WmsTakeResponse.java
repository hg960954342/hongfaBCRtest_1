package com.intplog.mcs.bean.dto.WmsDto;

import lombok.Data;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/27
 */
@Data
public class WmsTakeResponse {

    /// <summary>
    /// 是否压缩
    /// </summary>
    public String isCompression;

    /// <summary>
    /// 错误数据明细（集合）
    /// </summary>
    public String errorMember ;

    /// <summary>
    /// 响应信息
    /// </summary>

    public String message;

    /// <summary>
    /// 返回编码：1:成功，-1失败
    /// </summary>
    public String stateCode;

    /// <summary>
    /// 路向
    /// </summary>
    public List<WmsData> data;

}
