package com.intplog.mcs.bean.dto.EisDto;

import lombok.Data;

/**
 * @author quqingmao
 * @date 2020/9/22
 */
@Data
public class EisInSizeDto
{

    /**
     * 读码器编号
     */
    public String bcrId;
    /**
     * 箱子条码
     */
    public String boxNo;
    /**
     * 箱子长度
     */
    public int boxLength;
    /**
     *箱子宽度
     */
    public int boxWidth;
    /**
     * 箱子高度
     */
    public int boxHeight ;
}
