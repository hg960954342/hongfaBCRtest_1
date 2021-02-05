package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsCrossLayerTaskMapper;
import com.intplog.mcs.service.McsService.McsCrossLayerTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class McsCrossLayerTaskServiceImpl implements McsCrossLayerTaskService {
    @Resource
    private McsCrossLayerTaskMapper mcsCrossLayerTaskMapper;
    @Override
    public PageData getAll(String taskId, String floor, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object>page= PageHelper.startPage(pageNum,pageSize);
        List<McsCrossLayerTask>getAll=mcsCrossLayerTaskMapper.getList(taskId,floor);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(getAll);
        return pageData;
    }

    @Override
    public List<McsCrossLayerTask> getByStatusAndHoist(int status, String hoistId) {
        return mcsCrossLayerTaskMapper.getByStatusAndHoist(status,hoistId);
    }



    @Override
    public McsCrossLayerTask getByTaskId(String taskId) {
        return mcsCrossLayerTaskMapper.getByTaskId(taskId);
    }

    @Override
    public McsCrossLayerTask getByTaskIdAndStatus(String taskId, int status) {
        return mcsCrossLayerTaskMapper.getByTaskIdAndStatus(taskId,status);
    }

    @Override
    public int update(McsCrossLayerTask mcsCrossLayerTask) {
        return mcsCrossLayerTaskMapper.update(mcsCrossLayerTask);
    }

    @Override
    public int insert(McsCrossLayerTask mcsCrossLayerTask) {
        return mcsCrossLayerTaskMapper.insert(mcsCrossLayerTask);
    }

    @Override
    public List<McsCrossLayerTask> getLayerTask() {
        return mcsCrossLayerTaskMapper.getLayerTask();
    }

    @Override
    public int delete(String id) {
        return mcsCrossLayerTaskMapper.deleteById(id);
    }
}
