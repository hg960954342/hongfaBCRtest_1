package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.GcsModel.GcsAlarm;
import com.intplog.mcs.mapper.McsMapper.McsGcsAlarmMapper;
import com.intplog.mcs.service.McsService.McsGcsAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/1
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsGcsAlarmImpl implements McsGcsAlarmService {
    @Resource
    private McsGcsAlarmMapper mcsGcsAlarmMapper;

    @Override
    public List<GcsAlarm> getStatusList(int reportStatus) {
        return mcsGcsAlarmMapper.getStatusList(reportStatus);
    }

    @Override
    public GcsAlarm getId(String id) {
        return mcsGcsAlarmMapper.getId(id);
    }

    @Override
    public int insert(GcsAlarm gcsAlarm) {
        return mcsGcsAlarmMapper.insert(gcsAlarm);
    }

    @Override
    public int update(GcsAlarm gcsAlarm) {
        return mcsGcsAlarmMapper.update(gcsAlarm);
    }

    @Override
    public int delete(int reportStatus) {
        return mcsGcsAlarmMapper.delete(reportStatus);
    }
}
