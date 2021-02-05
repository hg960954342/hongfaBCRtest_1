package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsTaskInfoMapper;
import com.intplog.mcs.service.McsService.McsTaskInfoService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-06 10:48
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTaskInfoServiceImpl implements McsTaskInfoService {

    @Resource
    private McsTaskInfoMapper mcsTaskInfoMapper;

    @Override
    public PageData getAll(String taskId, String mcsId,  int pageNum, int pageSize) {
        PageData pageData= new PageData();
        Page<Object>page= PageHelper.startPage(pageNum,pageSize);
        List<McsTaskInfo>mcsTaskInfos= mcsTaskInfoMapper.getList(mcsId,taskId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(mcsTaskInfos);
        return pageData;
    }

    @Override
    public List<McsTaskInfo> getRefuseList(int layer) {
        return mcsTaskInfoMapper.getRefuseList(layer);
    }

    @Override
    public List<McsTaskInfo> getByStatus(int status) {
        return mcsTaskInfoMapper.getListByStatus(status);
    }

    @Override
    public List<McsTaskInfo> getByStatusAndHoist(String hoistId, int status ,int type) {
        return mcsTaskInfoMapper.getListByStatusAndHoist(hoistId,status , type);
    }

    @Override
    public List<McsTaskInfo> getListMove(int status, int type) {
        return mcsTaskInfoMapper.getListMove(status ,type);
    }

    @Override
    public McsTaskInfo getListByTaskStatus(String taskId, int status) {
        return mcsTaskInfoMapper.getListByTaskStatus(status,taskId);
    }

    @Override
    public McsTaskInfo getBySourceAndStatus(String crood, int status) {
        return mcsTaskInfoMapper.getListByStatusAndSource(crood,status);
    }


    @Override
    public McsTaskInfo getByStatusAndCroodAndHoist(String hoistId, String crood, int status) {
        return mcsTaskInfoMapper.getListByStatusAndHoistAndPlcId(hoistId,crood,status);
    }

    @Override
    public McsTaskInfo getByTaskId(String taskId) {
        return mcsTaskInfoMapper.getByTaskId(taskId);
    }

    @Override
    public McsTaskInfo getByMcsId(String mcsId) {
        return mcsTaskInfoMapper.getByMcsId(mcsId);
    }

    @Override
    public McsTaskInfo getByStatusAndMcsId(String mcsId, int status) {
        return mcsTaskInfoMapper.getByStatusAndMcsId(mcsId, status);
    }

    @Override
    public int getCountByStatus(String hoistId, int status) {
        return mcsTaskInfoMapper.getCountByStatusAndHoist(hoistId,status);
    }

    @Override
    public int insert(McsTaskInfo mcsTaskInfo) {
        return mcsTaskInfoMapper.insert(mcsTaskInfo);
    }

    @Override
    public int update(McsTaskInfo mcsTaskInfo) {
        mcsTaskInfo.setUpdateTime(new Date());
        return mcsTaskInfoMapper.update(mcsTaskInfo);
    }

    @Override
    public int deleteByDay(int day) {
        Date date = DateHelpUtil.getDate(day);
        return mcsTaskInfoMapper.deleteTask(date);
    }

    @Override
    public int deleteId(String id) {
        return mcsTaskInfoMapper.deleteId(id);
    }

    @Override
    public int deleteStatus(int status) {
        return mcsTaskInfoMapper.deleteStatus(status);
    }
}
