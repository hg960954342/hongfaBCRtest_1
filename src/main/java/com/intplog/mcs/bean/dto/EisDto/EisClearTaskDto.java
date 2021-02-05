package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.GcsClearTask;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class EisClearTaskDto {

    private List<GcsClearTask> carryList;
}
