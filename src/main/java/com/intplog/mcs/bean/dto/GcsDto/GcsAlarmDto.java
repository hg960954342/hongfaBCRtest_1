package com.intplog.mcs.bean.dto.GcsDto;

import com.intplog.mcs.bean.model.GcsModel.GcsAlarm;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/1
 */
@Data
public class GcsAlarmDto {
    private List<GcsAlarm> GcsAlarmList;
}
