package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsStacker;
import com.intplog.mcs.mapper.McsMapper.McsStackerMapper;
import com.intplog.mcs.service.McsService.McsStackerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class McsStackerServiceImpl implements McsStackerService {
    @Resource
    private McsStackerMapper mcsStackerMapper;
    @Override
    public List<McsStacker> getALL() {
        return mcsStackerMapper.getAll();
    }

    @Override
    public List<McsStacker> getByStatus(int status) {
        return mcsStackerMapper.getByStatus(status);
    }

    @Override
    public List<McsStacker> getByTypeAndPLcName(int type, String plcName) {
        return mcsStackerMapper.getByTypeAndPlcName(type,plcName);
    }

    @Override
    public McsStacker getByCoord(String coord) {
        return mcsStackerMapper.getByCoord(coord);
    }

    @Override
    public int insert(McsStacker mcsStacker) {
        return mcsStackerMapper.insert(mcsStacker);
    }

    @Override
    public int update(McsStacker mcsStacker) {
        return mcsStackerMapper.update(mcsStacker);
    }

    @Override
    public int updateByNumber(McsStacker mcsStacker) {
        return mcsStackerMapper.updateByNumber(mcsStacker);
    }

    @Override
    public int delete(String id) {
        return mcsStackerMapper.deleteById(id);
    }
}
