package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerReport;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.enums.ReportStatus;
import com.intplog.mcs.mapper.McsMapper.McsCrossLayerReportMapper;
import com.intplog.mcs.service.McsService.McsCrossLayerReportService;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class McsCrossLayerReportServiceImpl implements McsCrossLayerReportService {
    @Resource
    private McsCrossLayerReportMapper mcsCrossLayerReportMapper;
    @Override
    public PageData getAll(String taskId, String hoistId, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object>objects= PageHelper.startPage(pageNum,pageSize);
        List<McsCrossLayerReport>list= mcsCrossLayerReportMapper.getList(taskId,hoistId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(objects.getTotal());
        pageData.setData(list);
        return pageData;
    }

    @Override
    public List<McsCrossLayerReport> getByStatusAndHoist(int status, String hoistId) {
        return mcsCrossLayerReportMapper.getByStatusAndHoist(status,hoistId);
    }

    @Override
    public List<McsCrossLayerReport> getByStatus(int status) {
        return mcsCrossLayerReportMapper.getByStatus(status);
    }

    @Override
    public McsCrossLayerReport getByTaskId(String taskId) {
        return mcsCrossLayerReportMapper.getByTaskId(taskId);
    }

    @Override
    public McsCrossLayerReport getByTaskIdAndStatus(String taskId, int status) {
        return mcsCrossLayerReportMapper.getByTaskIdAndStatus(taskId,status);
    }

    @Override
    public int update(McsCrossLayerReport mcsCrossLayerReport) {
        return mcsCrossLayerReportMapper.update(mcsCrossLayerReport);
    }

    @Override
    public int insert(McsCrossLayerReport mcsCrossLayerReport) {
        return mcsCrossLayerReportMapper.insert(mcsCrossLayerReport);
    }

    @Override
    public int insertCrossLayerReport(McsCrossLayerTask mcsCrossLayerTask, int status) {
        String taskId= mcsCrossLayerTask.getTaskId();
        //是否存在该任务的数据
        McsCrossLayerReport mcs= mcsCrossLayerReportMapper.getByTaskIdAndStatus(taskId,status);
        //不存在插入数据库
        if(StringUtils.isEmpty(mcs)) {
            McsCrossLayerReport mcsCrossLayerReport = new McsCrossLayerReport();
            mcsCrossLayerReport.setId(StringUtil.getUUID32());
            mcsCrossLayerReport.setTaskId(mcsCrossLayerTask.getTaskId());
            mcsCrossLayerReport.setTaskType(mcsCrossLayerTask.getTaskType());
            mcsCrossLayerReport.setFloor(mcsCrossLayerTask.getFloor());
            mcsCrossLayerReport.setHoistId(mcsCrossLayerTask.getHoistId());
            mcsCrossLayerReport.setReportStatus(ReportStatus.notReport.getValue());
            mcsCrossLayerReport.setWorkStatus(status);
            mcsCrossLayerReport.setCreateTime(new Date());
            return mcsCrossLayerReportMapper.insert(mcsCrossLayerReport);
        }
        return 0;
    }

    @Override
    public int delete(String id) {
        return mcsCrossLayerReportMapper.deleteById(id);
    }
}
