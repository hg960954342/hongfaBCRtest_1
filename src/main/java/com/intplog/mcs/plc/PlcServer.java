package com.intplog.mcs.plc;

import com.intplog.mcs.bean.model.McsModel.McsHoist;
import com.intplog.mcs.bean.model.McsModel.McsPlcConnect;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import com.intplog.mcs.enums.PlcAddressType;
import com.intplog.mcs.plc.bean.HoistDataPacket;
import com.intplog.mcs.plc.bean.HoistPlc;
import com.intplog.mcs.plc.common.PlcTypeUtils;
import com.intplog.mcs.service.McsService.McsHoistService;
import com.intplog.mcs.service.McsService.McsPlcConnectService;
import com.intplog.mcs.service.McsService.McsPlcVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author quqingmao
 * @date 2020/7/21
 */
@Component
@Order(Integer.MAX_VALUE)
public class PlcServer {
    @Autowired
    public McsPlcConnectService mcsPlcConnectService;
    @Autowired
    public McsPlcVariableService mcsPlcVariableService;
    @Autowired
    public McsHoistService mcsHoistService;


    //重连时间
    public static final int reconnectTime = 1000 * 5;
    /**
     * plc连接对象
     */
    private static Map<String , PlcDriver> hoistDriverMap = new ConcurrentHashMap<>();
    /**
     * 返回plc连接对象
     */
    public static Map<String, PlcDriver> getHoistDriverMap() {
        return hoistDriverMap;
    }
    /**
     * plc初始化连接
     */
    @PostConstruct
    private void init(){
        getPlcInfo();
    }
    /**
     * 关闭连接
     */
    @PreDestroy
    private void close(){
        for (PlcDriver item : hoistDriverMap.values()){
            item.closes();
        }
    }
    /**
     * 获取plc信息
     */
    private void getPlcInfo(){
        List<McsPlcConnect> all = mcsPlcConnectService.getAll();
        if(all.size()==0) return;
        try{
            for (McsPlcConnect mcsPlcConnect : all){
                PlcDriver hoistDriver = new PlcDriver();
                hoistDriver.createPlc(mcsPlcConnect.getIp(), PlcTypeUtils.getPlcType(mcsPlcConnect.getType()));
                HoistDataPacket hoistDataPacket = hoistDriver.hoistDataPacket;
                //根据提升机编号转换成map
                List<McsHoist> mcsHoists = mcsHoistService.getListByPlc(mcsPlcConnect.getPlcName());
                Map<String, McsHoist> mcsHoistMap = mcsHoists.stream().collect(Collectors.toMap(McsHoist::getId, a -> a));
                //加载提升机对象到内存
                hoistDataPacket.getHoistMap().putAll(mcsHoistMap);
                hoistDriverMap.put(mcsPlcConnect.getPlcName(),hoistDriver);
                hoistDriver.start();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    /**
     * 加载plc地址到内存
     * @param hoistDataPacket
     * @param mcsPlcVariables
     */
//    private void plcAddressData(HoistDataPacket hoistDataPacket,List<McsPlcVariable> mcsPlcVariables){
//        int type = PlcAddressType.PlcAddress.getValue();
//        List<McsPlcVariable> collect = mcsPlcVariables.stream().filter(x -> x.getType() == type).collect(Collectors.toList());
//        if(!StringUtils.isEmpty(collect) && collect.size()>0){
//            for (McsPlcVariable mcsPlcVariable : collect){
//                hoistDataPacket.getPlcAddress().put(mcsPlcVariable.getCoord() ,mcsPlcVariable );
//            }
//        }
//    }

}

