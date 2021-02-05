package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.model.McsModel.McsGcsIsPermit;
import com.intplog.mcs.mapper.McsMapper.McsGcsHoistInOutMapper;
import com.intplog.mcs.mapper.McsMapper.McsGcsIsPermitMapper;
import com.intplog.mcs.service.McsService.McsGcsIsPermitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/9
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsGcsIsPermitImpl implements McsGcsIsPermitService {
    @Resource
    private McsGcsIsPermitMapper mcsGcsIsPermitMapper;
    @Override
    public McsGcsIsPermit getMcsGcsIsPermitList(String plcName, String isPermit) {
        return mcsGcsIsPermitMapper.getListByPLC(plcName,isPermit);
    }

    @Override
    public int updateMcsGcsIsPermit(String plcName , String layer ,int isPermit) {
        return mcsGcsIsPermitMapper.update(plcName ,layer ,isPermit);
    }

    @Override
    public McsGcsIsPermit getInOut(String hoistName, String layer) {
        return mcsGcsIsPermitMapper.getInOut(hoistName,layer);
    }

}
