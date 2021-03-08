package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsBcrLog;
import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsBcrLogMapper;
import com.intplog.mcs.service.McsService.McsBcrLogService;
import com.intplog.mcs.service.McsService.McsBcrPropertiesService;
import com.intplog.mcs.utils.DateHelpUtil;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2020-02-22 16:28
 **/
@Service
public class McsBcrLogServiceImpl implements McsBcrLogService {
    @Resource
    public McsBcrLogMapper mcsBcrLogMapper;
    @Autowired
    public McsBcrPropertiesService mcsBcrPropertiesService;

    @Override
    public PageData getAll(String createTime, String boxNum, String bcrNum, int pageNum, int pageSize) {
        PageData pd = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsBcrLog> all = mcsBcrLogMapper.getLists(createTime,boxNum,bcrNum);
        pd.setMsg("");
        pd.setCount(page.getTotal());
        pd.setCode(0);
        pd.setData(all);
        return pd;
    }

    public McsBcrLog getMcsLogById(String id) {
        return mcsBcrLogMapper.getMcsLogById(id);
    }

    public int deleteMcsLog(int day) {
        Date dt = DateHelpUtil.getDate(day);
        return mcsBcrLogMapper.deleteLogs(dt);
    }

    public int insertMcsLog(McsBcrLog mcsLog) {
        return mcsBcrLogMapper.inserts(mcsLog);
    }

    @Override
    public void insertMcsLog(String id, String code) {
        McsBcrProperties mcsBcrProperties= mcsBcrPropertiesService.getByConnectId(id);
        McsBcrLog mcsBcrLog= new McsBcrLog();
        mcsBcrLog.setId(StringUtil.getUUID32());
        mcsBcrLog.setName(mcsBcrProperties.getName());

    }

    public int updateMcsLog(McsBcrLog mcsLog) {
        return mcsBcrLogMapper.updateByPrimaryKeys(mcsLog);
    }

    @Override
    public List<McsBcrLog> getListExcel(String createTime, String boxNum, String bcrNum) {
        return mcsBcrLogMapper.getListExcel(createTime,boxNum,bcrNum);
    }


}
