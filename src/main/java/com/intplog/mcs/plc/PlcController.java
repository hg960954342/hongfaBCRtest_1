package com.intplog.mcs.plc;

import com.alibaba.fastjson.JSONArray;
import com.intplog.mcs.bean.dto.EisDto.EisBoxCountDto;
import com.intplog.mcs.bean.dto.EisDto.EisBoxCountReply;
import com.intplog.mcs.bean.dto.EisDto.EisDistributionDto;
import com.intplog.mcs.bean.dto.EisDto.EisInSizeDto;
import com.intplog.mcs.bean.model.McsModel.*;
import com.intplog.mcs.common.*;
import com.intplog.mcs.plc.common.PlcGetByte;
import com.intplog.mcs.plc.common.Utils;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.McsService.McsBoxMessageService;
import com.intplog.mcs.service.McsService.McsPlcLogService;
import com.intplog.mcs.service.McsService.McsSlidCountService;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import com.intplog.mcs.utils.StringUtil;
import com.intplog.siemens.bean.ResultData;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private McsBoxMessageService mcsBoxMessageService;
    @Autowired
    private McsSlidCountService mcsSlidCountService;


    //-------------------业务逻辑-------------------------------

    //1F分拣口
    public void Distribution1F(){
        long t1 =System.currentTimeMillis();
        Date date = new Date();
        try {
            for (int i = 1; i <= 12; i++) {
                McsTriggerTask mcsTriggerTaskData = mcsTriggerTaskService.getMcsTriggerTaskData(String.valueOf(i), 1);
                McsPlcVariable1 mcsPlcVariable1 = mcsTriggerTaskService.getMcsPlcVariable1(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsPlcVariable1.getPlcName());
                if(!StringUtils.isEmpty(mcsTriggerTaskData)){
                    EisDistributionDto eisDistributionDto = new EisDistributionDto();
                    eisDistributionDto.setBcrId(mcsTriggerTaskData.getBcrId());
                    eisDistributionDto.setBarCode(mcsTriggerTaskData.getBcrCode());
                    String mcg1 = eisDistributionDto.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg1, mcsTriggerTaskData.getBcrCode());
                    JsonDataEis jsonData = eisAccountService.eisDistributionReport(eisDistributionDto);
                    String mcg2 = jsonData.toString();
                    addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 2, date, mcg2, mcsTriggerTaskData.getBcrCode());
                    System.out.print(date+"  :  111111F  eisDistributionDto  "+eisDistributionDto+"\n");
                    System.out.print(date+"  :  111111F  jsonData  "+jsonData+"\n");
                    if(jsonData.getSuccess()){
                        mcsTriggerTaskData.setStatus(10);
                        mcsTriggerTaskData.setCreateTime(date);
                        mcsTriggerTaskService.updateMcsTriggerTaskData(mcsTriggerTaskData);
                        if(!StringUtils.isEmpty(jsonData.getData())&&jsonData.getData().getBcrId().equals(String.valueOf(i))){

                            byte path = (byte) jsonData.getData().getDestination();
                            ResultData data = plcDriver.writeByte(mcsPlcVariable1.getForknumAddress(),path);
                            byte type = (byte) jsonData.getData().getStackType();
                            ResultData data1 = plcDriver.writeByte(mcsPlcVariable1.getForknumAddress(),type);




                            if(!data.isSuccess()){
                                String mcg = "wcs下发分拨口"+mcsTriggerTaskData.getBcrId()+"指令"+path+"到plc成功";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 1, date, mcg, mcsTriggerTaskData.getBcrCode());
                            }
                            else {
                                String mcg = "wcs下发分拨口指令到plc失败";
                                addPlcLog(mcsTriggerTaskData.getBcrId(),mcsTriggerTaskData.getId(),date, 3, date, mcg, mcsTriggerTaskData.getBcrCode());
                                plcDriver.connect=false;
                            }
                        }
                        else continue;
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

    //1F机械臂上报抓取数量
    public void SlidCount(){
        try {
            Date date = new Date();
            for (int i = 1; i <= 8; i++) {
                McsSlidCount mcsSlidCount = mcsSlidCountService.getMcsSlidCount(String.valueOf(i));
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get(mcsSlidCount.getPlcName());
                ResultData data = plcDriver.readBytes(mcsSlidCount.getAddress(),4);
                ResultData data1 = plcDriver.readBytes(mcsSlidCount.getAddress1(),4);

                byte[] value = (byte[]) data.getValue();
                byte[] value1 = (byte[]) data1.getValue();

                String boxcount = String.valueOf(PlcGetByte.bytesToInt32Hight(value,0));
                String actioncount = String.valueOf(PlcGetByte.bytesToInt32Hight(value1,0));

                if(!actioncount.equals(mcsSlidCount.getActionCount())&&!boxcount.equals(mcsSlidCount.getBoxCount())){
                    EisBoxCountDto eisBoxCountDto = new EisBoxCountDto();
                    eisBoxCountDto.setSlidNo(String.valueOf(i));
                    eisBoxCountDto.setBoxCount(boxcount);
                    eisBoxCountDto.setActionCount(actioncount);
                    EisBoxCountReply eisBoxCountReply = eisAccountService.eisBoxCount(eisBoxCountDto);

                    mcsSlidCountService.updateMcsSlidCountData(boxcount,actioncount,date);
                    String mcg2 = eisBoxCountDto.toString();
                    String mcg1 = eisBoxCountReply.toString();
                    String mcg3 = "滑道"+String.valueOf(i);

                    addPlcLog(mcg3,"123",date, 1, date, mcg2, mcsSlidCount.getBoxCount());
                    addPlcLog(mcg3,"123",date, 1, date, mcg1, mcsSlidCount.getBoxCount());

                }
                else if(actioncount.equals(mcsSlidCount.getActionCount())){
                    continue;
                }
            }
        }catch (Exception ex){
            log.error(String.valueOf(ex));
        }

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
