package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsSlidCount;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsTriggerTaskMapper;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTriggerTaskImpl implements McsTriggerTaskService {
    @Resource
    private McsTriggerTaskMapper mcsTriggerTaskMapper;

    @Override
    public PageData getAll(String bcrId, String bcrCode, int pageNum, int pageSize) {
        PageData pageData= new PageData();
        Page<Object> page= PageHelper.startPage(pageNum,pageSize);
        List<McsTriggerTask> mcsTaskInfos= mcsTriggerTaskMapper.getList(bcrId,bcrCode);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(mcsTaskInfos);
        return pageData;
    }

    @Override
    public McsTriggerTask getMcsTriggerTaskData(String bcrId, int status) {
        return mcsTriggerTaskMapper.getMcsTriggerTaskData(bcrId,status);
    }

    @Override
    public McsPlcVariable1 getMcsPlcVariable1(String bcrId) {
        return mcsTriggerTaskMapper.getMcsplcvariable1(bcrId);
    }

    @Override
    public McsTriggerTask getMcsTriggerTaskDataTheLatest() {
        return mcsTriggerTaskMapper.getMcsTriggerTaskDataTheLatest();
    }

    @Override
    public int insertMcsTriggerTaskData(McsTriggerTask mcsTriggerTask) {
        return mcsTriggerTaskMapper.insertMcsTriggerTaskData(mcsTriggerTask);
    }

    @Override
    public int updateMcsTriggerTaskData(McsTriggerTask mcsTriggerTask) {
        return mcsTriggerTaskMapper.updateMcsTriggerTaskData(mcsTriggerTask);
    }

    @Override
    public int deleteMcsTriggerTaskData(int day) {
        Date date = DateHelpUtil.getDate(day);
        return mcsTriggerTaskMapper.deleteMcsTriggerTaskData(date);
    }

    @Override
    public int deleteStatusData(int status) {
        return mcsTriggerTaskMapper.deleteStatus(status);
    }

    @Override
    public McsTriggerTask getMcsTriggerTaskId(String bcrId) {
        return mcsTriggerTaskMapper.getMcsTriggerTaskId(bcrId);
    }
}
