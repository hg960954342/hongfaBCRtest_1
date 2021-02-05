package com.intplog.mcs.service.impl.GcsServiceImpl;

import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.common.jsonDataGcs;
import com.intplog.mcs.service.GcsService.GcsAccountService;
import com.intplog.mcs.service.impl.InitDataListener;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(rollbackFor = Exception.class)
public class GcsAccountServiceImpl implements GcsAccountService {
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public jsonDataGcs ClearTaskToGcs(Object object) {
        try {
            String url = "/Interface/ClearTaskFromMcs";
            return this.restTemplate.postForObject(InitDataListener.GCS_Host_URL + url, object, jsonDataGcs.class);
        } catch (Exception e) {
            StringUtil.error("清除gcs出库口坐标");
            return null;
        }
    }

    @Override
    public JsonData IsPermitGCS(Object object) {
        try {
            String url = "/Interface/";
            return this.restTemplate.postForObject(InitDataListener.GCS_Host_URL + url, object, JsonData.class);
        } catch (Exception e) {
            StringUtil.error("GCS进入提升机回告MCS请求超时");
            return null;
        }
    }
}
