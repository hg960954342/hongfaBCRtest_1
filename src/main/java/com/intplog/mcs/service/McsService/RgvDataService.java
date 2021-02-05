package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.dto.EisDto.EisRgvData;
import com.intplog.mcs.bean.model.EisModel.RgvData;

import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/20
 */
public interface RgvDataService {
    List<EisRgvData> getAll();

    int update(RgvData rgvData);

    int insert(RgvData rgvData);
}
