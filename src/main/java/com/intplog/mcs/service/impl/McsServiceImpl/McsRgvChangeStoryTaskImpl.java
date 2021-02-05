package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.model.McsModel.McsRgvChangeStoryTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsRgvChangeStoryTaskMapper;
import com.intplog.mcs.service.McsService.McsRgvChangeStoryTaskService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsRgvChangeStoryTaskImpl implements McsRgvChangeStoryTaskService {
    @Resource
    private McsRgvChangeStoryTaskMapper mcsRgvChangeStoryTaskMapper;

    @Override
    public PageData getAllPageData(String taskId, String hoistId, int pageNum, int pageSize) {
        PageData pageData = new PageData();
        Page<Object> page= PageHelper.startPage(pageNum,pageSize);
        List<McsRgvChangeStoryTask> getAll=mcsRgvChangeStoryTaskMapper.getLists(taskId,hoistId);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(getAll);
        return pageData;
    }

    @Override
    public List<McsRgvChangeStoryTask> getList(String taskId) {
        return mcsRgvChangeStoryTaskMapper.getList(taskId);
    }

    @Override
    public List<McsRgvChangeStoryTask> getAll() {
        return mcsRgvChangeStoryTaskMapper.getAll();
    }

    @Override
    public McsRgvChangeStoryTask getId(String id) {
        return mcsRgvChangeStoryTaskMapper.getId(id);
    }

    @Override
    public List<McsRgvChangeStoryTask> getByStatus (int status) {
        return mcsRgvChangeStoryTaskMapper.getStatus(status);
    }

    @Override
    public List<McsRgvChangeStoryTask> getGcsStatus() {
        return mcsRgvChangeStoryTaskMapper.getGcsStatus();
    }

    @Override
    public List<McsRgvChangeStoryTask> getByStatusAndHoist(int status, String hoistId) {
        return mcsRgvChangeStoryTaskMapper.getByStatusAndHoist(status,hoistId);
    }

    @Override
    public List<McsRgvChangeStoryTask> getByTaskId(String taskId) {
        return mcsRgvChangeStoryTaskMapper.getByTaskIdAndStatus(taskId);
    }

    @Override
    public McsRgvChangeStoryTask getIdStatus(String id, int status) {
        return mcsRgvChangeStoryTaskMapper.getIdStatus(id,status);
    }

    @Override
    public int insert(McsRgvChangeStoryTask mcsRgvChangeStoryTask) {
        return mcsRgvChangeStoryTaskMapper.insert(mcsRgvChangeStoryTask);
    }

    @Override
    public List<McsCrossLayerTask> getLayerTask() {
        return mcsRgvChangeStoryTaskMapper.getLayerTask();
    }

    @Override
    public int update(McsRgvChangeStoryTask mcsRgvChangeStoryTask) {
        return mcsRgvChangeStoryTaskMapper.update(mcsRgvChangeStoryTask);
    }

    @Override
    public int deleteById(String id) {
        return mcsRgvChangeStoryTaskMapper.deleteById(id);
    }

    @Override
    public int deleteDate(int a) {
        Date date = DateHelpUtil.getDate(a);

        return mcsRgvChangeStoryTaskMapper.deleteLog(date);
    }

    @Override
    public McsRgvChangeStoryTask getGcsId(int status, String gcsId) {
        return mcsRgvChangeStoryTaskMapper.getGcsId(status ,gcsId);
    }

    @Override
    public int deleteStatus(int status) {
        return mcsRgvChangeStoryTaskMapper.deleteStatus(status);
    }
}
