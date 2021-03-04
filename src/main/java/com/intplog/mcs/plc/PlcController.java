package com.intplog.mcs.plc;


import com.intplog.mcs.bean.dto.EisDto.EisDistributionDto;
import com.intplog.mcs.bean.model.McsModel.*;
import com.intplog.mcs.common.*;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.McsService.McsPlcLogService;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import com.intplog.mcs.utils.StringUtil;
import com.intplog.siemens.bean.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.Date;


/**
 * @author quqingmao
 * @date 2020/7/21
 */
@Slf4j
@Component
public class PlcController {
    @Autowired
    private McsPlcLogService mcsPlcLogService;
    @Autowired
    private EisAccountService eisAccountService;
    @Autowired
    private McsTriggerTaskService mcsTriggerTaskService;


    //-------------------业务逻辑-------------------------------

    //1F分拣口
    public void Distribution1F(){
        long t1 =System.currentTimeMillis();
        Date date = new Date();
        try {
            for (int i = 30; i <= 35; i++) {
                McsTriggerTask mcsTriggerTaskData = mcsTriggerTaskService.getMcsTriggerTaskData(String.valueOf(i), 1);
                McsPlcVariable1 mcsPlcVariable1 = mcsTriggerTaskService.getMcsPlcVariable1(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsPlcVariable1.getPlcName());
                if(!StringUtils.isEmpty(mcsTriggerTaskData)){
                    EisDistributionDto eisDistributionDto = new EisDistributionDto();
                    eisDistributionDto.setBcrId(mcsTriggerTaskData.getBcrId());
                    eisDistributionDto.setTaskId(StringUtil.getUUID32());
                    eisDistributionDto.setBarCode(mcsTriggerTaskData.getBcrCode());
                    eisDistributionDto.setWeight(mcsTriggerTaskData.getWeight());
                    eisDistributionDto.setShapeInspect(mcsTriggerTaskData.isShape());
                    if(i==30){
                        ResultData data = plcDriver.readBytes("DB2.DBB13", 1);
                        byte[] value = (byte[]) data.getValue();
                        String s = new String(value,"UTF-8");
                        switch(s){
                            case "0" :
                                eisDistributionDto.setEmpty(0);
                                break;
                            default :
                                eisDistributionDto.setEmpty(1);
                        }
                    }
                    else if(i==31){
                        ResultData data1 = plcDriver.readBytes("DB2.DBB14", 1);
                        byte[] value1 = (byte[]) data1.getValue();
                        String s = new String(value1,"UTF-8");
                        switch(s){
                            case "0" :
                                eisDistributionDto.setEmpty(0);
                                break;
                            default :
                                eisDistributionDto.setEmpty(1);
                        }
                    }
                    else {
                        eisDistributionDto.setEmpty(0);
                    }
                    JsonDataEis jsonData = eisAccountService.eisDistributionReport(eisDistributionDto);
                    String mcg1 = eisDistributionDto.toString();
                    String mcg2 = jsonData.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg1, mcsTriggerTaskData.getBcrCode());
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 2, date, mcg2, mcsTriggerTaskData.getBcrCode());

                    if(jsonData.getSuccess()){
                        mcsTriggerTaskData.setStatus(10);
                        mcsTriggerTaskData.setCreateTime(date);
                        mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskData);
                        if(!StringUtils.isEmpty(jsonData.getData())&&jsonData.getData().getBcrId().equals(String.valueOf(i))){
                            byte path = (byte) jsonData.getData().getDestination();
                            ResultData data1 = plcDriver.writeByte(mcsPlcVariable1.getAddress(),path);
                            if(data1.isSuccess()){
                                String mcg = "wcs下发分拨口"+mcsTriggerTaskData.getBcrId()+"指令"+path+"到plc成功";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                            }
                            else if(!data1.isSuccess()){
                                String mcg = "wcs下发分拨口指令到plc失败";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                                plcDriver.connect=false;
                            }
                        }
                        else  continue;
                    }
                    else continue;
                }
                else continue;
            }
        }catch (Exception ex){
            log.error(String.valueOf(ex));
        }
        long t2 =System.currentTimeMillis();
        System.out.println(date+"  :  111111F  时间  "+(t2-t1)+"\n");
    }

    //2F分拣口
    public void Distribution2F(){
        long t1 =System.currentTimeMillis();
        Date date = new Date();
        try {
            for (int i = 36; i <= 48; i++) {
                McsTriggerTask mcsTriggerTaskData = mcsTriggerTaskService.getMcsTriggerTaskData(String.valueOf(i), 1);
                McsPlcVariable1 mcsPlcVariable1 = mcsTriggerTaskService.getMcsPlcVariable1(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsPlcVariable1.getPlcName());
                if(!StringUtils.isEmpty(mcsTriggerTaskData)){
                    EisDistributionDto eisDistributionDto = new EisDistributionDto();
                    eisDistributionDto.setBcrId(mcsTriggerTaskData.getBcrId());
                    eisDistributionDto.setTaskId(StringUtil.getUUID32());
                    eisDistributionDto.setBarCode(mcsTriggerTaskData.getBcrCode());
                    eisDistributionDto.setWeight(mcsTriggerTaskData.getWeight());
                    eisDistributionDto.setShapeInspect(mcsTriggerTaskData.isShape());
                    JsonDataEis jsonData = eisAccountService.eisDistributionReport(eisDistributionDto);
                    String mcg1 = eisDistributionDto.toString();
                    String mcg2 = jsonData.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg1, mcsTriggerTaskData.getBcrCode());
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 2, date, mcg2, mcsTriggerTaskData.getBcrCode());

                    if(jsonData.getSuccess()){
                        mcsTriggerTaskData.setStatus(10);
                        mcsTriggerTaskData.setCreateTime(date);
                        mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskData);
                        if(!StringUtils.isEmpty(jsonData.getData())&&jsonData.getData().getBcrId().equals(String.valueOf(i))){
                            byte path = (byte) jsonData.getData().getDestination();
                            ResultData data1 = plcDriver.writeByte(mcsPlcVariable1.getAddress(),path);
                            if(data1.isSuccess()){
                                String mcg = "wcs下发分拨口"+mcsTriggerTaskData.getBcrId()+"指令"+path+"到plc成功";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                            }
                            else if(!data1.isSuccess()){
                                String mcg = "wcs下发分拨口指令到plc失败";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                                plcDriver.connect=false;
                            }
                        }
                        else  continue;
                    }
                    else continue;
                }
                else continue;
            }
        }catch (Exception ex){
            log.error(String.valueOf(ex));
        }
        long t2 =System.currentTimeMillis();
        System.out.println(date+"  222222F  时间:    "+(t2-t1)+"\n");
    }

    //3F分拣口
    public void Distribution3F(){
        long t1 =System.currentTimeMillis();
        Date date = new Date();
        try {
            for (int i = 49; i <= 54; i++) {
                McsTriggerTask mcsTriggerTaskData = mcsTriggerTaskService.getMcsTriggerTaskData(String.valueOf(i), 1);
                McsPlcVariable1 mcsPlcVariable1 = mcsTriggerTaskService.getMcsPlcVariable1(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsPlcVariable1.getPlcName());
                if(!StringUtils.isEmpty(mcsTriggerTaskData)){
                    EisDistributionDto eisDistributionDto = new EisDistributionDto();
                    eisDistributionDto.setBcrId(mcsTriggerTaskData.getBcrId());
                    eisDistributionDto.setTaskId(StringUtil.getUUID32());
                    eisDistributionDto.setBarCode(mcsTriggerTaskData.getBcrCode());
                    eisDistributionDto.setWeight(mcsTriggerTaskData.getWeight());
                    eisDistributionDto.setShapeInspect(mcsTriggerTaskData.isShape());
                    JsonDataEis jsonData = eisAccountService.eisDistributionReport(eisDistributionDto);
                    String mcg1 = eisDistributionDto.toString();
                    String mcg2 = jsonData.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg1, mcsTriggerTaskData.getBcrCode());
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 2, date, mcg2, mcsTriggerTaskData.getBcrCode());
                    if(jsonData.getSuccess()){
                        mcsTriggerTaskData.setStatus(10);
                        mcsTriggerTaskData.setCreateTime(date);
                        mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskData);
                        if(!StringUtils.isEmpty(jsonData.getData())&&jsonData.getData().getBcrId().equals(String.valueOf(i))){
                            byte path = (byte) jsonData.getData().getDestination();
                            ResultData data1 = plcDriver.writeByte(mcsPlcVariable1.getAddress(),path);
                            if(data1.isSuccess()){
                                String mcg = "wcs下发分拨口"+mcsTriggerTaskData.getBcrId()+"指令"+path+"到plc成功";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                            }
                            else if(!data1.isSuccess()){
                                String mcg = "wcs下发分拨口指令到plc失败";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                                plcDriver.connect=false;
                            }
                        }
                        else  continue;
                    }
                    else continue;
                }
                else continue;
            }
        }catch (Exception ex){
            log.error(String.valueOf(ex));
        }
        long t2 =System.currentTimeMillis();
        System.out.println(date+"  333333F时间:    "+(t2-t1)+"\n");
    }

    //4F分拣口
    public void Distribution4F(){
        long t1 =System.currentTimeMillis();
        Date date = new Date();
        try {
            for (int i = 55; i <= 60; i++) {
                McsTriggerTask mcsTriggerTaskData = mcsTriggerTaskService.getMcsTriggerTaskData(String.valueOf(i), 1);
                McsPlcVariable1 mcsPlcVariable1 = mcsTriggerTaskService.getMcsPlcVariable1(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsPlcVariable1.getPlcName());
                if(!StringUtils.isEmpty(mcsTriggerTaskData)){
                    EisDistributionDto eisDistributionDto = new EisDistributionDto();
                    eisDistributionDto.setBcrId(mcsTriggerTaskData.getBcrId());
                    eisDistributionDto.setTaskId(StringUtil.getUUID32());
                    eisDistributionDto.setBarCode(mcsTriggerTaskData.getBcrCode());
                    eisDistributionDto.setWeight(mcsTriggerTaskData.getWeight());
                    eisDistributionDto.setShapeInspect(mcsTriggerTaskData.isShape());
                    JsonDataEis jsonData = eisAccountService.eisDistributionReport(eisDistributionDto);
                    String mcg1 = eisDistributionDto.toString();
                    String mcg2 = jsonData.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg1, mcsTriggerTaskData.getBcrCode());
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 2, date, mcg2, mcsTriggerTaskData.getBcrCode());

                    if(jsonData.getSuccess()){
                        mcsTriggerTaskData.setStatus(10);
                        mcsTriggerTaskData.setCreateTime(date);
                        mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskData);
                        if(!StringUtils.isEmpty(jsonData.getData())&&jsonData.getData().getBcrId().equals(String.valueOf(i))){
                            byte path = (byte) jsonData.getData().getDestination();
                            ResultData data1 = plcDriver.writeByte(mcsPlcVariable1.getAddress(),path);
                            if(data1.isSuccess()){
                                String mcg = "wcs下发分拨口"+mcsTriggerTaskData.getBcrId()+"指令"+path+"到plc成功";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                            }
                            else if(!data1.isSuccess()){
                                String mcg = "wcs下发分拨口指令到plc失败";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                                plcDriver.connect=false;
                            }
                        }
                        else  continue;
                    }
                    else continue;
                }
                else continue;
            }
        }catch (Exception ex){
            log.error(String.valueOf(ex));
        }
        long t2 =System.currentTimeMillis();
        System.out.println(date+"  444444F  时间:    "+(t2-t1)+"\n");
    }

    /**
     * 添加PLC日志
     *
     * @param bcrid
     * @param taskId
     * @param createTime
     * @param type
     * @param rqTime
     * @param content
     * @param boxNum
     */
    private void addPlcLog(String bcrid, String taskId,Date createTime, int type, Date rqTime, String content, String boxNum) {
        McsPlcLog mcsPlcLog = new McsPlcLog();
        mcsPlcLog.setId(bcrid);
        mcsPlcLog.setTaskId(StringUtil.getUUID32());
        mcsPlcLog.setCreateTime(createTime);
        mcsPlcLog.setRpTime(rqTime);
        mcsPlcLog.setContent(content);
        mcsPlcLog.setType(type);
        mcsPlcLog.setBoxNum(boxNum);
        mcsPlcLogService.inset(mcsPlcLog);
    }
}
