package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.GcsModel.GcsAlarm;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/1
 */
public interface McsGcsAlarmService {
    List<GcsAlarm> getStatusList(int reportStatus);

    GcsAlarm getId(String id);

    int insert(GcsAlarm gcsAlarm);

    int update(GcsAlarm gcsAlarm);

    int delete(int reportStatus);
}
