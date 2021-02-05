package com.intplog.mcs.service.impl.McsServiceImpl;

import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.service.McsService.McsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liaoliming
 * @Date 2019-12-05 10:18
 */
@Service
public class McsAccountServiceImpl implements McsAccountService {

    private final String uri = "http://127.0.0.1:80/";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JsonData reqLocationFailure(Object obj) {
        try {
            String url = "";
            return this.restTemplate.postForObject(uri + url, obj, JsonData.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
