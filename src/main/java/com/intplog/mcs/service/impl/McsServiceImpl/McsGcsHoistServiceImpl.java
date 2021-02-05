package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsGcsHoistInout;
import com.intplog.mcs.mapper.McsMapper.McsGcsHoistInOutMapper;
import com.intplog.mcs.service.McsService.McsGcsHoistInoutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsGcsHoistServiceImpl implements McsGcsHoistInoutService {

    @Resource
    private McsGcsHoistInOutMapper mcsGcsHoistInOutMapper;


    @Override
    public McsGcsHoistInout getHoistName( String type) {
        return mcsGcsHoistInOutMapper.getHoistName(type);
    }

    @Override
    public McsGcsHoistInout getInPlaces(String inPlace) {
        return mcsGcsHoistInOutMapper.getInPlace(inPlace);
    }

    @Override
    public int insertData(McsGcsHoistInout mcsGcsHoistInout) {
        return mcsGcsHoistInOutMapper.insertData(mcsGcsHoistInout);
    }

    @Override
    public int updateDataIs(McsGcsHoistInout mcsGcsHoistInout) {
        return mcsGcsHoistInOutMapper.updateInPlace(mcsGcsHoistInout);
    }

    @Override
    public int deleteData(String id) {
        return mcsGcsHoistInOutMapper.deleteType(id);
    }

    @Override
    public List<McsGcsHoistInout> getId(String id) {
        return mcsGcsHoistInOutMapper.getId(id);
    }
}
