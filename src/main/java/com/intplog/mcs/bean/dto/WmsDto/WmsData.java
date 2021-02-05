package com.intplog.mcs.bean.dto.WmsDto;

import lombok.Data;

/**
 * @author quqingmao
 * @date 2020/8/27
 */
@Data
public class WmsData {
    /// <summary>
    /// 分拨路向：00 剔除口，1 滑道号
    /// </summary>
    public int result;
    /// <summary>
    /// 描述
    /// </summary>
    public String tag;
    /// <summary>
    /// 滑道号
    /// </summary>
    public String fhtbh;
    public String slide;
    public int isChecked;
    public int itemState;
    public int totalcount;
}
