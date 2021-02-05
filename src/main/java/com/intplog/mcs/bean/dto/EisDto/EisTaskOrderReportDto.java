package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.EisRequest;
import com.intplog.mcs.bean.model.EisModel.EisTaskReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 19:27
 **/
@Setter
@Getter
@ToString
public class EisTaskOrderReportDto {

    private List<EisTaskReport>carryList;
}
