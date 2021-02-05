package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.EisInOutTaskRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/13
 */

@Setter
@Getter
@ToString
public class EisInOutTaskRequestDto {

    private List<EisInOutTaskRequest>carryList;
}
