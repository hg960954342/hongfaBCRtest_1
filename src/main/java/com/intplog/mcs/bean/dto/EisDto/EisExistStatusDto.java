package com.intplog.mcs.bean.dto.EisDto;

import com.intplog.mcs.bean.model.EisModel.EisExistStatusRequest;
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
public class EisExistStatusDto {
    List<EisExistStatusRequest>carryList;
}
