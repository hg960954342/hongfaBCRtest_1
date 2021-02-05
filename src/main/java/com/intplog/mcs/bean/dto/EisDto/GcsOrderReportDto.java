package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.dto.GcsDto.GcsOrderReport;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/7/17
 */

@Getter
@Setter
@ToString
public class GcsOrderReportDto {
    private List<GcsOrderReport>  gcsOrderReportList ;
}
