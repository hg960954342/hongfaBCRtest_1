package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.McsModel.McsHoist;
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
public class ReportHoistDto {
    private List<GetHoistStatus>carryList;
}
