package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.EisTaskAlarm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-07 12:53
 **/
@Setter
@Getter
@ToString
public class EisTaskAlarmReportDto {

    private List<EisTaskAlarm>carryList;

}
