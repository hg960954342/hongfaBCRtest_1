package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsTaskAlarmReport;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.enums.ReportStatus;
import com.intplog.mcs.enums.TaskStage;
import com.intplog.mcs.mapper.McsMapper.McsTaskAlarmReportMapper;
import com.intplog.mcs.service.McsService.McsTaskAlarmReportService;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2019-11-06 12:01
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTaskAlarmReportServiceImpl implements McsTaskAlarmReportService {
    @Resource
    public McsTaskAlarmReportMapper mcsTaskAlarmReportMapper;

    @Override
    public List<McsTaskAlarmReport> getAll() {
        return mcsTaskAlarmReportMapper.getAll();
    }

    @Override
    public int insertMcsAlarmReport(McsTaskAlarmReport mcsTaskAlarmReport) {
        return mcsTaskAlarmReportMapper.insertIntoMcsAlarmReport(mcsTaskAlarmReport);
    }

    @Override
    public int insertByMcsTaskInfo(McsTaskInfo mcsTaskInfo, int status, int type, String msg) {
        McsTaskAlarmReport mcsTaskAlarmReport = new McsTaskAlarmReport();
        mcsTaskAlarmReport.setId(StringUtil.getUUID32());
        mcsTaskAlarmReport.setTaskId(mcsTaskInfo.getTaskId());
        mcsTaskAlarmReport.setType(mcsTaskInfo.getType());
        mcsTaskAlarmReport.setStockId(mcsTaskInfo.getStockId());
        mcsTaskAlarmReport.setSource(mcsTaskInfo.getSource());
        mcsTaskAlarmReport.setTarget(mcsTaskInfo.getTarget());
        mcsTaskAlarmReport.setWeight(mcsTaskInfo.getWeight());
        mcsTaskAlarmReport.setPriority(mcsTaskInfo.getPriority());
        mcsTaskAlarmReport.setStatus(TaskStage.START.getValue());
        mcsTaskAlarmReport.setErrorType(2);
        mcsTaskAlarmReport.setMsg("入库尺寸检测异常");
        mcsTaskAlarmReport.setCreateTime(new Date());
        mcsTaskAlarmReport.setReportStatus(ReportStatus.notReport.getValue());
        return mcsTaskAlarmReportMapper.insertIntoMcsAlarmReport(mcsTaskAlarmReport);
    }

    @Override
    public int batchInsert(List<McsTaskAlarmReport> mcsTaskAlarmReports) {
        return mcsTaskAlarmReportMapper.butchInsert(mcsTaskAlarmReports);
    }

    @Override
    public List<McsTaskAlarmReport> getListByReportStatus(int reportStatus) {
        return mcsTaskAlarmReportMapper.getOrderReportByStatus(reportStatus);
    }

    @Override
    public int update(McsTaskAlarmReport mcsTaskAlarmReport) {
        return mcsTaskAlarmReportMapper.update(mcsTaskAlarmReport);
    }

}
