package com.intplog.mcs.schedule;

import com.intplog.mcs.bean.model.ErrorData;
import com.intplog.mcs.bean.model.GcsModel.RgvInfo;
import com.intplog.mcs.bean.model.SysParam;
import com.intplog.mcs.plc.PlcController;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.InterfaceLogService;
import com.intplog.mcs.service.McsService.McsAlarmLogService;
import com.intplog.mcs.service.McsService.McsClearTaskService;
import com.intplog.mcs.service.McsService.McsPlcLogService;
import com.intplog.mcs.service.McsService.McsTaskReturnService;
import com.intplog.mcs.service.SysParamService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author liaoliming
 * @Date 2019-10-31 10:45
 * 定时任务与任务计划
 */
@Component
@Slf4j
public class ScheduleTask {

    @Autowired
    private InterfaceLogService interfaceLogService;
    @Autowired
    private McsPlcLogService mcsPlcLogService;
    @Autowired
    private McsClearTaskService mcsClearTaskService;
    @Autowired
    private McsAlarmLogService mcsAlarmLogService;
    @Autowired
    private McsTaskReturnService mcsTaskReturnService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private PlcController plcController ;


    /**
     * 解析json并返回对象
     *
     * @param object
     * @return
     */
    private List<ErrorData> parseEisJsonObject(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        List<ErrorData> errorData = (List<ErrorData>) JSONArray.toCollection(jsonArray, ErrorData.class);
        return errorData;
    }

    private List<RgvInfo> parseEisJsonObjects(Object object) {
        JSONArray jsonArray = JSONArray.fromObject(object);
        List<RgvInfo> rgvInfos = (List<RgvInfo>) JSONArray.toCollection(jsonArray, RgvInfo.class);
        return rgvInfos;
    }



    /**
     * 分拣口1F
     */
    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void distribution1F(){
        plcController.Distribution1F();
    }

    /**
     * 分拣口2F
     */
    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void distribution2F(){
        plcController.Distribution2F();
    }

    /**
     * 分拣口3F
     */
    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void distribution3F(){
        plcController.Distribution3F();
    }

    /**
     * 分拣口4F
     */
    @Scheduled(cron = "0/1 * * * * ?")
    @Async
    public void distribution4F(){
        plcController.Distribution4F();
    }


    private int getDayValue(String key, String name, int value) {
        SysParam sysParam = sysParamService.getSysParamByCode(key);
        int day = value;

        if (sysParam == null) {
            SysParam model = new SysParam();
            model.setCode(key);
            model.setName(name);
            model.setValue(String.valueOf(value));
            model.setSort(sysParamService.getTotal() + 1);
            sysParamService.insertSysParam(model);
        }
        try {
            day = Integer.valueOf(sysParam.getValue());
        } catch (Exception ex) {

        }
        return day;
    }
    /**
     * 清除日志记录
     */
    //  @Scheduled(cron = "0 30 12 * * ?")
    private void autoClear() {
        int clearLogDT = getDayValue("ClearLogDT", "清理日志时间", 10);
        int clearTaskDT = getDayValue("ClearTaskDT", "清理任务表时间", 10);
        interfaceLogService.deleteLog(clearLogDT);
        mcsClearTaskService.delete(clearTaskDT);
        mcsAlarmLogService.deleteLog(clearLogDT);
        mcsPlcLogService.deleteMcsLog(clearLogDT);
        mcsTaskReturnService.deleteDate(clearLogDT);
    }
}

