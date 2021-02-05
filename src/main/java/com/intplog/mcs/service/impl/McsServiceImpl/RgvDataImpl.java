package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.bean.dto.EisDto.EisRgvData;
import com.intplog.mcs.bean.model.EisModel.RgvData;
import com.intplog.mcs.mapper.McsMapper.RgvDataMapper;
import com.intplog.mcs.service.McsService.RgvDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RgvDataImpl implements RgvDataService {
    @Resource
    public RgvDataMapper rgvDataMapper;
    @Override
    public List<EisRgvData> getAll() {
        return rgvDataMapper.getAll();
    }

    @Override
    public int update(RgvData rgvData) {
        return rgvDataMapper.update(rgvData);
    }

    @Override
    public int insert(RgvData rgvData) {
        return rgvDataMapper.inset(rgvData);
    }


}
