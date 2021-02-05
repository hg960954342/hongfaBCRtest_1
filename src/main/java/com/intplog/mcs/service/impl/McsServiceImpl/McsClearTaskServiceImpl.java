package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsClearTask;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsClearTaskMapper;
import com.intplog.mcs.service.McsService.McsClearTaskService;
import com.intplog.mcs.utils.DateHelpUtil;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class McsClearTaskServiceImpl implements McsClearTaskService {
    @Resource
    private McsClearTaskMapper mcsClearTaskMapper;

    @Override
    public PageData getAll(String taskId, String clearCrood, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsClearTask> list = mcsClearTaskMapper.getList(taskId, clearCrood);
        pageData.setMsg("");
        pageData.setCount(page.getTotal());
        pageData.setCode(0);
        pageData.setData(list);
        return pageData;
    }

    @Override
    public List<McsClearTask> getByStatusAndHoist(int status, String hoistId) {
        return mcsClearTaskMapper.getByStatusAndHoist(status, hoistId);
    }

    @Override
    public List<McsClearTask> getByStatusAndType(int status, int type) {
        return mcsClearTaskMapper.getByStatusAndType(status, type);
    }

    @Override
    public List<McsClearTask> getByStatusAndPlcAndAddress(int status, int addressType, String plcId) {
        return mcsClearTaskMapper.getByStatusAndPlcIdAndHoist(status, plcId, addressType);
    }

    @Override
    public McsClearTask getByTaskId(String taskId) {
        return mcsClearTaskMapper.getByTaskId(taskId);
    }

    @Override
    public int update(McsClearTask mcsClearTask) {
        return mcsClearTaskMapper.update(mcsClearTask);
    }

    @Override
    public int updateByTaskId(String taskId, int status) {
        Date date = new Date();
        return mcsClearTaskMapper.updateByTaskId(taskId, status, date);
    }

    @Override
    public int insert(McsClearTask mcsClearTask) {
        return mcsClearTaskMapper.insert(mcsClearTask);
    }

    @Override
    public int insertGcsClearTask(McsTaskInfo mcsTaskInfo) {
        //查询是否存在该任务号的数据
        McsClearTask mcsClearTask=mcsClearTaskMapper.getByTaskId(mcsTaskInfo.getTaskId());
        //不存在就插入数据
        if(StringUtils.isEmpty(mcsClearTask)) {
            McsClearTask mcsClearTask1= new McsClearTask();
            String id = StringUtil.getUUID32();
            mcsClearTask1.setId(id);
            mcsClearTask1.setTaskId(mcsTaskInfo.getTaskId());
            mcsClearTask1.setType(2);
            mcsClearTask1.setStatus(0);
            mcsClearTask1.setClearCrood(mcsTaskInfo.getSource());
            mcsClearTask1.setStockId(mcsTaskInfo.getStockId());
            mcsClearTask1.setCreateTime(new Date());
            mcsClearTask1.setPlcName(mcsTaskInfo.getPlcName());
            mcsClearTask1.setHoistId(mcsTaskInfo.getHoistId());
           return  mcsClearTaskMapper.insert(mcsClearTask1);
        }
        return 0;
    }

    @Override
    public int delete(String id) {
        return mcsClearTaskMapper.deleteById(id);
    }

    @Override
    public int delete(int data) {
        Date dt = DateHelpUtil.getDate(data);
        return mcsClearTaskMapper.deleteLog(dt);
    }
}
