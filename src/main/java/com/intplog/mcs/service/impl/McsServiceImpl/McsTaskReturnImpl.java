package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsTaskReturn;
import com.intplog.mcs.mapper.McsMapper.McsTaskReturnMapper;
import com.intplog.mcs.service.McsService.McsTaskReturnService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTaskReturnImpl implements McsTaskReturnService {
    @Resource
    private McsTaskReturnMapper mcsTaskReturnMapper;

    @Override
    public List<McsTaskReturn> getAll() {
        return mcsTaskReturnMapper.getAll();
    }

    @Override
    public List<McsTaskReturn> getListByReportStatus(int status) {
        return mcsTaskReturnMapper.getListByReportStatus(status);
    }

    @Override
    public List<McsTaskReturn> getListByReportTaskStatus(int status, String taskId) {
        return mcsTaskReturnMapper.getListByReportTaskStatus(status,taskId);
    }

    @Override
    public List<McsTaskReturn> getListByReportTask(String taskId) {
        return mcsTaskReturnMapper.getListByReportTask(taskId);
    }

    @Override
    public List<McsTaskReturn> getListByReportType(int type) {
        return mcsTaskReturnMapper.getListByReportType(type);
    }

    @Override
    public List<McsTaskReturn>  eisTaskReturn(int status, int reportStatus) {
        return mcsTaskReturnMapper.eisTaskReturn(status,reportStatus);
    }

    @Override
    public int insert(McsTaskReturn mcsTaskReturn) {
        return mcsTaskReturnMapper.insert(mcsTaskReturn);
    }

    @Override
    public int update(McsTaskReturn mcsTaskReturn) {
        return mcsTaskReturnMapper.update(mcsTaskReturn);
    }

    @Override
    public int delete(String taskId) {
        return mcsTaskReturnMapper.delete(taskId);
    }

    @Override
    public int deleteDate(int data) {
        Date dt = DateHelpUtil.getDate(data);
        return mcsTaskReturnMapper.deletes(dt);
    }

}
