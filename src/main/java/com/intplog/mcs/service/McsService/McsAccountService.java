package com.intplog.mcs.service.McsService;

import com.intplog.mcs.common.JsonData;

/**
 * @author liaoliming
 * @Date 2019-10-31 10:53
 * @Version 1.0
 */
public interface McsAccountService {

    /**
     * 生成货位失败退回入库口：WCS-MCS
     */
    JsonData reqLocationFailure(Object obj);
}
