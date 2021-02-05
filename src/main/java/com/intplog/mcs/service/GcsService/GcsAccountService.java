package com.intplog.mcs.service.GcsService;

import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.common.jsonDataGcs;

public interface GcsAccountService {
    jsonDataGcs ClearTaskToGcs(Object object);

    JsonData IsPermitGCS(Object object);
}
