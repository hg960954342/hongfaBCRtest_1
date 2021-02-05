package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsStackerTask;
import com.intplog.mcs.mapper.McsMapper.McsStackerTaskMapper;
import com.intplog.mcs.service.McsService.McsStackerTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianlei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsStackerTaskServiceImpl implements McsStackerTaskService {
    @Resource
    private McsStackerTaskMapper mcsStackerTaskMapper;
    @Override
    public List<McsStackerTask> getALL() {
        return mcsStackerTaskMapper.getAll();
    }

    @Override
    public List<McsStackerTask> getByStatus(int status) {
        return mcsStackerTaskMapper.getByStatus(status);
    }

    @Override
    public List<McsStackerTask> getByStatusAndType(int status, int type) {
        return mcsStackerTaskMapper.getByStatusAndType(status,type);
    }


    @Override
    public McsStackerTask getByCoord(String coord) {
        return mcsStackerTaskMapper.getByCoord(coord);
    }

    @Override
    public int insert(McsStackerTask mcsStackerTask) {
        return mcsStackerTaskMapper.insert(mcsStackerTask);
    }

    @Override
    public int update(McsStackerTask mcsStackerTask) {
        return mcsStackerTaskMapper.update(mcsStackerTask);
    }

    @Override
    public int delete(String id) {
        return mcsStackerTaskMapper.deleteById(id);
    }
}
