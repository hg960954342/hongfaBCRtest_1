package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.EisAlarmReport;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tianlei
 */
@Getter
@Setter
@ToString
public class EisAlarmReportDto {
    /**
     * 设备上报状态
     */
    private List<EisAlarmReport>carryList;

}
