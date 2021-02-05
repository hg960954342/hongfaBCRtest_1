package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.RgvData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
@Getter
@Setter
@ToString
public class EisRgvDataDto {

    private List<EisRgvData>carryList;
}
