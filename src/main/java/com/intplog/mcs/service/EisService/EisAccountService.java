package com.intplog.mcs.service.EisService;

import com.intplog.mcs.bean.dto.EisDto.EisBoxCheckReply;
import com.intplog.mcs.bean.dto.EisDto.EisRequestDto;
import com.intplog.mcs.bean.dto.EisDto.RespCrossDto;
import com.intplog.mcs.bean.dto.EisDto.RespDto;
import com.intplog.mcs.bean.dto.ResultDto;
import com.intplog.mcs.bean.dto.WmsDto.WmsTakeResponse;
import com.intplog.mcs.bean.model.EisModel.RgvData;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.common.JsonDataEis;
import com.intplog.mcs.common.jsonDataGcs;
import com.intplog.mcs.enums.ReportStatus;

import javax.swing.*;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-04 16:01
 **/
public interface EisAccountService {

    JsonDataEis eisSizeReport(Object object);

    JsonDataEis eisDistributionReport(Object object);

    JsonDataEis eisTemporaryStorageBit(Object object);

    JsonDataEis eisGrabBit(Object object);

    EisBoxCheckReply eisBoxCheck(Object object);

}
