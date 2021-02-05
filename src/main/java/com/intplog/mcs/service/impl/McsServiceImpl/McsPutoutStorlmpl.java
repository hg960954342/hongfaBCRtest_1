package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsPutoutStor;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsPutoutStorMapper;
import com.intplog.mcs.service.McsService.McsPutoutStorService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/13
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class McsPutoutStorlmpl implements McsPutoutStorService {
    @Resource
    private McsPutoutStorMapper mcsPutoutStorMapper;
    @Override
    public PageData getAll(String mcsId, String taskId, int pageNum, int pageSize) {
        PageData pageData= new PageData();
        Page<Object> page= PageHelper.startPage(pageNum,pageSize);
        List<McsPutoutStor>mcsTaskInfos= mcsPutoutStorMapper.getList(mcsId,taskId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(mcsTaskInfos);
        return pageData;
    }

    @Override
    public List<McsPutoutStor> getRefuseList(int layer) {
        return mcsPutoutStorMapper.getRefuseList(layer);
    }

    @Override
    public List<McsPutoutStor> getByStatus(int status) {
        return mcsPutoutStorMapper.getListByStatus(status);
    }

    @Override
    public List<McsPutoutStor> getByStatusAndHoist(String hoistId, int status, int type) {
        return mcsPutoutStorMapper.getListByStatusAndHoist(hoistId,status,type);
    }

    @Override
    public List<McsPutoutStor> getListMove(int status, int type) {
        return mcsPutoutStorMapper.getListMove(status,type);
    }

    @Override
    public McsPutoutStor getListByTaskStatus(String taskId, int status) {
        return mcsPutoutStorMapper.getListByTaskStatus(status,taskId);
    }

    @Override
    public McsPutoutStor getBySourceAndStatus(String crood, int status) {
        return mcsPutoutStorMapper.getListByStatusAndSource(crood,status);
    }

    @Override
    public McsPutoutStor getByStatusAndCroodAndHoist(String hoistId, String crood, int status) {
        return mcsPutoutStorMapper.getListByStatusAndHoistAndPlcId(hoistId,crood,status);
    }

    @Override
    public McsPutoutStor getByTaskId(String taskId) {
        return mcsPutoutStorMapper.getByTaskId(taskId);
    }

    @Override
    public McsPutoutStor getByMcsId(String mcsId) {
        return mcsPutoutStorMapper.getByMcsId(mcsId);
    }

    @Override
    public McsPutoutStor getByStatusAndMcsId(String mcsId, int status) {
        return mcsPutoutStorMapper.getByStatusAndMcsId(mcsId,status);
    }

    @Override
    public int getCountByStatus(String hoistId, int status) {
        return mcsPutoutStorMapper.getCountByStatusAndHoist(hoistId,status);
    }

    @Override
    public int insert(McsPutoutStor mcsTaskInfo) {
        return mcsPutoutStorMapper.insert(mcsTaskInfo);
    }

    @Override
    public int update(McsPutoutStor mcsTaskInfo) {
        return mcsPutoutStorMapper.update(mcsTaskInfo);
    }

    @Override
    public int deleteByDay(int day) {
        Date date = DateHelpUtil.getDate(day);
        return mcsPutoutStorMapper.deleteTask(date);
    }

    @Override
    public int deleteId(String id) {
        return mcsPutoutStorMapper.deleteId(id);
    }

    @Override
    public int deleteStatus(int status) {
        return mcsPutoutStorMapper.deleteStatus(status);
    }
}
