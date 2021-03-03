package com.intplog.mcs.service.impl.EisServiceImpl;

import com.alibaba.fastjson.JSONObject;
import com.intplog.mcs.bean.dto.EisDto.EisBoxCountReply;
import com.intplog.mcs.bean.dto.EisDto.EisRequestDto;
import com.intplog.mcs.bean.dto.EisDto.RespCrossDto;
import com.intplog.mcs.bean.dto.EisDto.RespDto;
import com.intplog.mcs.bean.dto.ResultDto;
import com.intplog.mcs.bean.dto.WmsDto.WmsTakeResponse;
import com.intplog.mcs.bean.model.EisModel.RgvData;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.common.JsonDataEis;
import com.intplog.mcs.common.jsonDataGcs;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.InterfaceLogService;
import com.intplog.mcs.service.impl.InitDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Templates;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-04 16:03
 **/
@Service
@Slf4j
public class EisAccountServiceImpl implements EisAccountService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private InterfaceLogService interfaceLogService;

    @Override
    public JsonDataEis eisSizeReport(Object object) {
        try {
            Date begin = new Date();
            String url="wcs/bcr";
            JsonDataEis jsonData= this.restTemplate.postForObject(InitDataListener.EIS_URL+url,object,JsonDataEis.class);
            Date end = new Date();
            interfaceLogService.insertInterfaceLog("入库口尺寸检测上报eis",object,begin,jsonData,end);
            return  jsonData;
        }catch (Exception ex){
            ex.printStackTrace();
            JsonDataEis jsonData= new JsonDataEis();
            jsonData.setSuccess(false);
            jsonData.setMessage(ex.getMessage());
            return jsonData;
        }
    }

    @Override
    public JsonDataEis eisDistributionReport(Object object) {
        try {
            Date begin = new Date();
            String url="wcs/bcr";
            String s = JSONObject.toJSONString(object);
            log.info("MCS->EIS json: "+s);
            JsonDataEis jsonData= this.restTemplate.postForObject(InitDataListener.EIS_URL+url,s,JsonDataEis.class);
            Date end = new Date();
            interfaceLogService.insertInterfaceLog("容器到达分拨口请求eis",s,begin,jsonData,end);
            return  jsonData;
        }catch (Exception ex){
            ex.printStackTrace();
            JsonDataEis jsonData= new JsonDataEis();
            jsonData.setSuccess(false);
            jsonData.setMessage(ex.getMessage());
            return jsonData;
        }
    }

    @Override
    public JsonDataEis eisTemporaryStorageBit(Object object) {
        try {
            Date begin = new Date();
            String url="api/v1/master/equip/mcsReport";
            JsonDataEis jsonData= this.restTemplate.postForObject(InitDataListener.EIS_URL+url,object,JsonDataEis.class);
            Date end = new Date();
            interfaceLogService.insertInterfaceLog("容器到达暂存位请求eis",object,begin,jsonData,end);
            return  jsonData;
        }catch (Exception ex){
            ex.printStackTrace();
            JsonDataEis jsonData= new JsonDataEis();
            jsonData.setSuccess(false);
            jsonData.setMessage(ex.getMessage());
            return jsonData;
        }
    }

    @Override
    public JsonDataEis eisGrabBit(Object object) {
        try {
            Date begin = new Date();
            String url="api/v1/master/equip/mcsReport";
            JsonDataEis jsonData= this.restTemplate.postForObject(InitDataListener.EIS_URL+url,object,JsonDataEis.class);
            Date end = new Date();
            interfaceLogService.insertInterfaceLog("容器到达抓取位请求eis",object,begin,jsonData,end);
            return  jsonData;
        }catch (Exception ex){
            ex.printStackTrace();
            JsonDataEis jsonData= new JsonDataEis();
            jsonData.setSuccess(false);
            jsonData.setMessage(ex.getMessage());
            return jsonData;
        }
    }

    @Override
    public EisBoxCountReply eisBoxCount(Object object) {
        try {
            Date begin = new Date();
            String url="wcs/task/emptyContainerDto";
            EisBoxCountReply eisBoxCountReply= this.restTemplate.postForObject(InitDataListener.EIS_URL+url,object,EisBoxCountReply.class);
            Date end = new Date();
            interfaceLogService.insertInterfaceLog("空箱位请求eis",object,begin,eisBoxCountReply,end);
            return  eisBoxCountReply;
        }catch (Exception ex){
            ex.printStackTrace();
            EisBoxCountReply eisBoxCountReply= new EisBoxCountReply();
            eisBoxCountReply.setSuccess(false);
            eisBoxCountReply.setMessage(ex.getMessage());
            return eisBoxCountReply;
        }
    }

}
