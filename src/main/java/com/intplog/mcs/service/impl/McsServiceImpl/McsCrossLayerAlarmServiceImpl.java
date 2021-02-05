package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerAlarm;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsCrossLayerAlarmMapper;
import com.intplog.mcs.service.McsService.McsCrossLayerAlarmService;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class McsCrossLayerAlarmServiceImpl implements McsCrossLayerAlarmService {
    @Resource
    private McsCrossLayerAlarmMapper mcsCrossLayerAlarmMapper;
    @Override
    public PageData getAll(String taskId, String hoistId, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object> page= PageHelper.startPage(pageNum,pageSize);
        List<McsCrossLayerAlarm>list= mcsCrossLayerAlarmMapper.getList(taskId,hoistId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(list);
        return pageData;
    }

    @Override
    public List<McsCrossLayerAlarm> getByStatusAndHoist(int status, String hoistId) {
        return mcsCrossLayerAlarmMapper.getByStatusAndHoist(status,hoistId);
    }

    @Override
    public List<McsCrossLayerAlarm> getByStatus(int status) {
        return mcsCrossLayerAlarmMapper.getByStatus(status);
    }

    @Override
    public McsCrossLayerAlarm getByTaskId(String taskId) {
        return mcsCrossLayerAlarmMapper.getByTaskId(taskId);
    }

    @Override
    public int update(McsCrossLayerAlarm mcsCrossLayerAlarm) {
        return mcsCrossLayerAlarmMapper.update(mcsCrossLayerAlarm);
    }

    @Override
    public int insert(McsCrossLayerAlarm mcsCrossLayerAlarm) {
        return mcsCrossLayerAlarmMapper.insert(mcsCrossLayerAlarm);
    }

    @Override
    public int delete(String id) {
        return mcsCrossLayerAlarmMapper.deleteById(id);
    }
}
