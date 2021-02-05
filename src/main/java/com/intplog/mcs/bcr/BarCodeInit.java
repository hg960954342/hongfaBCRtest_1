package com.intplog.mcs.bcr;

import com.intplog.mcs.bean.model.McsModel.McsBcrConnect;
import com.intplog.mcs.service.McsService.McsBcrConnectService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname BarCodeInit
 * @Description TODO
 * @Date 2020/2/25 11:24
 * @Created by jiangzhongxing
 */
@Component
public class BarCodeInit {

    @Resource
    private McsBcrConnectService mcsBcrConnectService;

    /**
     * BarCodeMap
     */
    public Map<String, BarCodeNet> BarCodeMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        List<McsBcrConnect> all = mcsBcrConnectService.getAll();
        for (McsBcrConnect item : all) {
            Date date = new Date();
            BarCodeNet bcr = new BarCodeNet();
            bcr.setBcr_Id(item.getId());
            bcr.setBcr_IP(item.getIp());
            bcr.setBcr_Port(item.getPort());
            bcr.start();
            BarCodeMap.put(item.getId(), bcr);
            bcr.connect();
            if(bcr.isConnected()){
                System.out.print(date+"读码器"+item.getIp()+"连接成功");
            }
            else {
                System.out.print(date+"读码器"+item.getIp()+"连接失败");
            }
        }
    }

    @PreDestroy
    public void destroy() {
        for (BarCodeNet bcr : BarCodeMap.values()) {
            bcr.close();
        }
    }
}
