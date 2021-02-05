package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.model.McsModel.McsTaskOrderReport;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsTaskOrderReportMapper;
import com.intplog.mcs.service.McsService.McsTaskOrderReportService;
import com.intplog.mcs.utils.DateHelpUtil;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 17:09
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTaskOrderReportServiceImpl implements McsTaskOrderReportService {
    @Resource
    private McsTaskOrderReportMapper mcsTaskOrderReportMapper;
    @Override
    public PageData getAll(String taskId, String reportStatus, String createTime, int pageNum, int pageSize) {
        PageData pageData= new PageData();
        Page<Object>page= PageHelper.startPage(pageNum,pageSize);
        int satatus= StringUtils.isEmpty(reportStatus)?0:Integer.parseInt(reportStatus);
        List<McsTaskOrderReport>getList= mcsTaskOrderReportMapper.getList(taskId,satatus,createTime);
        pageData.setCode(0);
        pageData.setMsg("");
        pageData.setCount(page.getTotal());
        pageData.setData(getList);
        return pageData;
    }

    @Override
    public List<McsTaskOrderReport> getAllList() {
        return mcsTaskOrderReportMapper.getAll();
    }

    @Override
    public List<McsTaskOrderReport> getListByTaskId(String taskId) {
        return mcsTaskOrderReportMapper.getListByTaskId(taskId);
    }

    @Override
    public McsTaskOrderReport getListByTaskIdAndStatus(String taskId, int status) {
        return mcsTaskOrderReportMapper.getListByTaskIdAndStatus(taskId,status);
    }

    @Override
    public List<McsTaskOrderReport> getListByReportStatus(int reportStatus) {
        return mcsTaskOrderReportMapper.getListByReportStatus(reportStatus);
    }



    @Override
    public McsTaskOrderReport getListById(String id) {
        return mcsTaskOrderReportMapper.getById(id);
    }

    @Override
    public int insert(McsTaskOrderReport mcsTaskOrderReport) {
        return mcsTaskOrderReportMapper.insert(mcsTaskOrderReport);
    }

    @Override
    public int update(McsTaskOrderReport mcsTaskOrderReport) {
        mcsTaskOrderReport.setUpdateTime(new Date());
        return mcsTaskOrderReportMapper.update(mcsTaskOrderReport);
    }

    @Override
    public int updateById(int status, String id, Date date) {
        return mcsTaskOrderReportMapper.updateById(status,id,date);
    }


    @Override
    public int deleteByCreateTime(int day) {
        Date date= DateHelpUtil.getDate(day);
        return mcsTaskOrderReportMapper.delete(date);
    }

    @Override
    public int  insertReport(McsTaskInfo mcsTaskInfo,int status,int reportStatus) {
        McsTaskOrderReport mcs=mcsTaskOrderReportMapper.getListByTaskIdAndStatus(mcsTaskInfo.getTaskId(),status);
        //是否存在已回告的数据，不存在生成回告数据
        if(StringUtils.isEmpty(mcs)) {
            McsTaskOrderReport mcsTaskOrderReport = new McsTaskOrderReport();
            mcsTaskOrderReport.setId(StringUtil.getUUID32());
            mcsTaskOrderReport.setTaskId(mcsTaskInfo.getTaskId());
            mcsTaskOrderReport.setType(mcsTaskInfo.getType());
            mcsTaskOrderReport.setStockId(mcsTaskInfo.getStockId());
            mcsTaskOrderReport.setSource(mcsTaskInfo.getSource());
            mcsTaskOrderReport.setTarget(mcsTaskInfo.getTarget());
            mcsTaskOrderReport.setWeight(mcsTaskInfo.getWeight());
            mcsTaskOrderReport.setPriority(mcsTaskInfo.getPriority());
            mcsTaskOrderReport.setStatus(status);
            Date begin = new Date();
            mcsTaskOrderReport.setCreateTime(begin);
            mcsTaskOrderReport.setReportStatus(reportStatus);
            return mcsTaskOrderReportMapper.insert(mcsTaskOrderReport);
        }
        return 0;
    }
}
