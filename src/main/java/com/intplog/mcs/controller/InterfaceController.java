package com.intplog.mcs.controller;

import com.intplog.mcs.bean.dto.EisDto.*;
import com.intplog.mcs.bean.dto.GcsDto.GcsAlarmDto;
import com.intplog.mcs.bean.dto.GcsDto.GcsCharge;
import com.intplog.mcs.bean.dto.GcsDto.GcsIsHoist;
import com.intplog.mcs.bean.dto.GcsDto.GcsOrderReport;
import com.intplog.mcs.bean.model.EisModel.*;
import com.intplog.mcs.bean.model.ErrorData;
import com.intplog.mcs.bean.model.GcsModel.GcsLayerTaskReturn;
import com.intplog.mcs.bean.model.GcsModel.RgvInfo;
import com.intplog.mcs.bean.model.McsModel.*;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.common.jsonDataGcs;
import com.intplog.mcs.enums.PlcAddressType;
import com.intplog.mcs.enums.TaskStage;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.InterfaceLogService;
import com.intplog.mcs.service.McsService.*;
import com.intplog.mcs.utils.StringUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: mcs_j
 * @description
 * @author: tianlei
 * @create: 2020-03-04 17:17
 **/
@Slf4j
@Controller
@RequestMapping("/Interface")
public class InterfaceController {
    @Autowired
    private InterfaceLogService interfaceLogService;
    @Autowired
    private McsTaskInfoService mcsTaskInfoService;
    @Autowired
    private McsPlcVariableService mcsPlcVariableService;
    @Autowired
    private McsClearTaskService mcsClearTaskService;
    @Autowired
    private McsCrossLayerTaskService mcsCrossLayerTaskService;
    @Autowired
    private McsHoistService mcsHoistService;
    @Autowired
    private McsGcsHoistInoutService mcsGcsHoistInoutService;
    @Autowired
    private McsRgvChangeStoryTaskService mcsRgvChangeStoryTaskService;
    @Autowired
    private McsTaskReturnService mcsTaskReturnService;
    @Autowired
    private EisAccountService eisAccountService;
    @Autowired
    private McsPutoutStorService mcsPutoutStorService;

    ///----------------------Eis接口--------------------------------------------
    //region 出入库接口

    @PostMapping("/inOutTaskRequest")
    @ResponseBody
    public Object inOutTaskRequest(@RequestBody EisInOutTaskRequestDto eisInOutTaskRequestDto) {
        Date begin = new Date();
        JsonData jsonData = addTaskInOf(eisInOutTaskRequestDto.getCarryList());
        Date end = new Date();
        ///记录接口任务下发日志
        interfaceLogService.insertInterfaceLog("EIS出入库任务下发", eisInOutTaskRequestDto, begin, jsonData, end);
        return jsonData;
    }

    private JsonData addTaskInOf(List<EisInOutTaskRequest> eisInOutTaskRequests) {
        JsonData jsonData = new JsonData();
        String message = "";
        String t;

        boolean isOk = false;
        List<String> errorList = new ArrayList<>();
        if (eisInOutTaskRequests.size() > 0) {
            for (EisInOutTaskRequest eisRequest : eisInOutTaskRequests) {
                String substring = eisRequest.getAddress().substring(0, 6);
                if (substring.equals("010010")) t = "0100100012";
                else t = "0100210012";
                McsPlcVariable mcsPlcVariable = mcsPlcVariableService.getByTypeAndCoord(t);
                if (mcsPlcVariable == null) {
                    message += "任务编号为:" + eisRequest.getTaskId() + "的任务,的提升机处于故障状态或跨层锁定状态";
                    errorList.add(eisRequest.getTaskId());
                    jsonData.setSuccess(false);
                    continue;
                }
                if (!StringUtils.isEmpty(eisRequest) && !eisRequest.getTarget().equals("-1") && !eisRequest.getTarget().equals("1")) {
                    //是通过提升机的出入库任务需判断当前提升机的状态和是否锁定
                    if (mcsPlcVariable.getType() == PlcAddressType.EXIT.getValue()) {
                        McsHoist mcsHoist = mcsHoistService.getMcsHoistById(mcsPlcVariable.getGroupNumber());
                        if (mcsHoist.getStatus() == 1 || mcsHoist.getLock() == 1 || mcsHoist.getStatus() == 3) {
                            message += "任务编号为:" + eisRequest.getTaskId() + "的任务,的提升机处于故障状态或跨层锁定状态";
                            errorList.add(eisRequest.getTaskId());
                            jsonData.setSuccess(false);
                            continue;
                        }
                    }
                    if (StringUtils.isEmpty(eisRequest.getTaskId())) {
                        message += ",任务单号为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                    McsTaskInfo mcsTaskInfo = mcsTaskInfoService.getByTaskId(eisRequest.getTaskId());
                    if (!StringUtils.isEmpty(mcsTaskInfo)) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务,已存在";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getType())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的任务类型为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getContainerNo())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的容器号为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getAddress())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的源位置为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getTarget())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的目的位置为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }

                    if (StringUtils.isEmpty(eisRequest.getPriority())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的优先级为空";
                        errorList.add(eisRequest.getTaskId());
                        jsonData.setSuccess(false);
                        continue;
                    }
                }
                int i = insertMcsTaskInFo(eisRequest, mcsPlcVariable);
                if (i > 0) {
                    jsonData.setSuccess(true);
                } else {
                    message += ",任务编号为:" + eisRequest.getTaskId() + "插入数据库失败";
                    errorList.add(eisRequest.getTaskId());
                    jsonData.setSuccess(false);
                }
            }
            jsonData.setData(errorList);
            jsonData.setMessage(message);
            jsonData.setCode("200");
        } else {
            jsonData.setSuccess(false);
            jsonData.setCode("500");
            jsonData.setMessage("请求任务为空");
        }
        Date begin = new Date();
        interfaceLogService.insertInterfaceLog("返回eis出入库数据", jsonData, begin, jsonData, begin);
        return jsonData;
    }

    private int insertMcsTaskInFo(EisInOutTaskRequest eisInOutTaskRequest, McsPlcVariable mcsPlcVariable) {
        McsTaskInfo mcsTaskInfo = new McsTaskInfo();
        if (eisInOutTaskRequest.getType() == 2) {
            return outTaskData(eisInOutTaskRequest);
        } else {
            mcsTaskInfo.setTaskId(eisInOutTaskRequest.getTaskId());
            mcsTaskInfo.setMcsId(StringUtil.getTaskCode());
            mcsTaskInfo.setType(eisInOutTaskRequest.getType());
            mcsTaskInfo.setTarget(eisInOutTaskRequest.getTarget());
            mcsTaskInfo.setStatus(TaskStage.SEND.getValue());
            mcsTaskInfo.setAddress(eisInOutTaskRequest.getAddress());
            mcsTaskInfo.setSource(eisInOutTaskRequest.getAddress());
            String in = eisInOutTaskRequest.getTarget().substring(0, 2);
            if (eisInOutTaskRequest.getType() == 1) {
                mcsTaskInfo.setHoistId("T01");
                mcsTaskInfo.setPlcName("PLC1");
                mcsTaskInfo.setLayer(Integer.parseInt(in));

            }
            mcsTaskInfo.setStockId(eisInOutTaskRequest.getContainerNo());
            mcsTaskInfo.setWeight(eisInOutTaskRequest.getWeight());
            mcsTaskInfo.setPriority(eisInOutTaskRequest.getPriority());
            mcsTaskInfo.setCreateTime(new Date());
            mcsTaskInfo.setAddressType(mcsPlcVariable.getType());
            return mcsTaskInfoService.insert(mcsTaskInfo);
        }
    }

    private int outTaskData(EisInOutTaskRequest eisInOutTaskRequest) {
        McsPutoutStor mcsPutoutStor = new McsPutoutStor();
        mcsPutoutStor.setTaskId(eisInOutTaskRequest.getTaskId());
        mcsPutoutStor.setMcsId(StringUtil.getTaskCode());
        mcsPutoutStor.setType(eisInOutTaskRequest.getType());
        mcsPutoutStor.setTarget(eisInOutTaskRequest.getTarget());
        mcsPutoutStor.setStatus(TaskStage.SEND.getValue());
        mcsPutoutStor.setAddress(eisInOutTaskRequest.getAddress());
        mcsPutoutStor.setSource(eisInOutTaskRequest.getAddress());
        String out = eisInOutTaskRequest.getAddress().substring(0, 2);
        mcsPutoutStor.setHoistId("T02");
        mcsPutoutStor.setPlcName("PLC2");
        mcsPutoutStor.setLayer(Integer.parseInt(out));
        mcsPutoutStor.setStockId(eisInOutTaskRequest.getContainerNo());
        mcsPutoutStor.setWeight(eisInOutTaskRequest.getWeight());
        mcsPutoutStor.setPriority(eisInOutTaskRequest.getPriority());
        mcsPutoutStor.setCreateTime(new Date());
        mcsPutoutStor.setAddressType(1);
        return mcsPutoutStorService.insert(mcsPutoutStor);

    }
    //endregion

    //region  小车换层任务下发接口
    @PostMapping("/cartCrossLayer")
    @ResponseBody
    public Object cartCrossLayer(@RequestBody EisRgvCrossLayer eisRgvCrossLayer) {

        Date begin = new Date();
        JsonData jsonData = cartCrossLayerTask(eisRgvCrossLayer);
        Date end = new Date();
        interfaceLogService.insertInterFaceLogByResPCross("EIS跨层任务下发", eisRgvCrossLayer, begin, jsonData, end);
        return jsonData;
    }

    private JsonData cartCrossLayerTask(EisRgvCrossLayer eisRgvCrossLayer) {

        JsonData jsonData = new JsonData();
        if (StringUtils.isEmpty(eisRgvCrossLayer)) {
            int layer = eisRgvCrossLayer.getSource();
            List<McsTaskInfo> byStatus = mcsTaskInfoService.getRefuseList(eisRgvCrossLayer.getSource());
            if (byStatus.size() > 0) {
                jsonData.setMessage("当前层有出入库任务没有完成，需要等待");
                jsonData.setData(eisRgvCrossLayer.getTaskId());
                jsonData.setSuccess(false);
                return jsonData;
            }
        }
        List<McsCrossLayerTask> layerTask = mcsRgvChangeStoryTaskService.getLayerTask();
        if (layerTask.size() > 0) {
            jsonData.setMessage("正在执行层任务，需要等待");
            jsonData.setData(eisRgvCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }

        if (StringUtils.isEmpty(eisRgvCrossLayer.getTaskId())) {
            jsonData.setMessage("任务单号为空");
            jsonData.setData(eisRgvCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        List<McsRgvChangeStoryTask> byTaskId = mcsRgvChangeStoryTaskService.getByTaskId(eisRgvCrossLayer.getTaskId());
        if (byTaskId.size() > 0) {
            jsonData.setMessage("任务单号为" + eisRgvCrossLayer.getTaskId() + "已存在");
            jsonData.setData(eisRgvCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }

        if (eisRgvCrossLayer.getTarget() < 1 || eisRgvCrossLayer.getTarget() > 6) {
            jsonData.setMessage("任务单号为" + eisRgvCrossLayer.getTaskId() + "的楼层不存在");
            jsonData.setData(eisRgvCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }


        int i = insertMcsRgvChangeStoryTask(eisRgvCrossLayer);
        if (i > 0) {
            jsonData.setSuccess(true);
            jsonData.setCode("200");
            jsonData.setMessage("下发小车换层任务成功");
        } else {
            jsonData.setMessage("任务编号为:" + eisRgvCrossLayer.getTaskId() + "操作失败");
            jsonData.setData(eisRgvCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            jsonData.setCode("500");
        }
        return jsonData;
    }

    private int insertMcsRgvChangeStoryTask(EisRgvCrossLayer eisRgvCrossLayer) {

        McsRgvChangeStoryTask mcsRgvChangeStoryTask = new McsRgvChangeStoryTask();
        mcsRgvChangeStoryTask.setCreateTime(new Date());
        mcsRgvChangeStoryTask.setId(StringUtil.getUUID32());
        mcsRgvChangeStoryTask.setRgvId(eisRgvCrossLayer.getRgvId());
        mcsRgvChangeStoryTask.setSource(eisRgvCrossLayer.getSource());
        mcsRgvChangeStoryTask.setStatus(1);
        mcsRgvChangeStoryTask.setTaskId(eisRgvCrossLayer.getTaskId());
        mcsRgvChangeStoryTask.setTarget(eisRgvCrossLayer.getTarget());

        return mcsRgvChangeStoryTaskService.insert(mcsRgvChangeStoryTask);
    }
    //endregion


    /**
     * 小车进出提升机
     *
     * @param gcsIsHoist
     * @return
     */
    @PostMapping("/rgvIsInOutHoist")
    @ResponseBody
    public Object rgvIsInHoist(@RequestBody GcsIsHoist gcsIsHoist) {
        jsonDataGcs jsonData = new jsonDataGcs();
        if (!StringUtils.isEmpty(gcsIsHoist)) {
            String taskId = gcsIsHoist.getTaskId();
            McsTaskInfo byTaskId = mcsTaskInfoService.getByTaskId(taskId);
            if (!StringUtils.isEmpty(byTaskId)) {
                byTaskId.setStatus(31);
                byTaskId.setRgvId(gcsIsHoist.getRgvId());
                mcsTaskInfoService.update(byTaskId);
                jsonData.setRet(true);
                jsonData.setMsg("接收成功");
                jsonData.setData(gcsIsHoist.getRgvId());
            }

        } else {
            jsonData.setMsg("数据为空");
            jsonData.setData(gcsIsHoist);
            jsonData.setRet(false);
        }
        return jsonData;
    }


    /**
     * @param reportList
     * @return
     */
    @PostMapping("/api/v1/master/gcs/interface/GcsOrderReport")
    @ResponseBody
    public Object gcsOutTask(@RequestBody List<GcsOrderReport> reportList) {
        jsonDataGcs jsonData = insertGcsOutTask(reportList);
        return jsonData;
    }


    private jsonDataGcs outGcsOrderReport(String billCode) {
        jsonDataGcs jsonData = new jsonDataGcs();
        ErrorData errorData = new ErrorData();
        McsPutoutStor byTaskId1 = mcsPutoutStorService.getListByTaskStatus(billCode, 20);
        if (!StringUtils.isEmpty(byTaskId1)) {
            byTaskId1.setStatus(30);
            int update = mcsPutoutStorService.update(byTaskId1);
            if (update > 0) {
                errorData.setErrMsg("");
                errorData.setTaskId("");
                jsonData.setData(errorData);
                jsonData.setMsg("接收成功");
                jsonData.setRet(true);
                return jsonData;
            }
        }
        jsonData.setMsg("数据为空");
        jsonData.setRet(false);
        return jsonData;
    }

    private jsonDataGcs insertGcsOutTask(List<GcsOrderReport> gcsOrderReports) {
        jsonDataGcs jsonData = new jsonDataGcs();
        ErrorData errorData = new ErrorData();
        if (gcsOrderReports.size() > 0) {
            for (GcsOrderReport gcsOrderReport : gcsOrderReports) {
                String billCode = gcsOrderReport.getBillCode();
                //GCS回告出库处理
                if (gcsOrderReport.getTaskType() == 2) {
                    if (gcsOrderReport.getWorkStatus() == 2) {
                        return outGcsOrderReport(billCode);
                    } else {
                        errorData.setErrMsg("");
                        errorData.setTaskId("");
                        jsonData.setData(errorData);
                        jsonData.setMsg("接收成功");
                        jsonData.setRet(true);
                        return jsonData;
                    }
                }
                //GCS回告入库处理
                if (gcsOrderReport.getTaskType() == 1) {
                    McsTaskInfo byTaskIds = mcsTaskInfoService.getByTaskId(billCode);
                    if (gcsOrderReport.getWorkStatus() == 1) {
                        byTaskIds.setStatus(61);
                        byTaskIds.setRgvId(gcsOrderReport.getRgvId());
                        int update = mcsTaskInfoService.update(byTaskIds);
                        if (update > 0) {
                            errorData.setErrMsg("");
                            errorData.setTaskId("");
                            jsonData.setData(errorData);
                            jsonData.setMsg("接收成功");
                            jsonData.setRet(true);
                            return jsonData;
                        }
                    }
                    //开始回告入库完成
                    if (!StringUtils.isEmpty(byTaskIds)) {
                        int i = eisRequest(billCode);
                        if (i > 0) {
                            errorData.setErrMsg("");
                            errorData.setTaskId("");
                            jsonData.setData(errorData);
                            jsonData.setMsg("接收成功");
                            jsonData.setRet(true);
                            return jsonData;
                        }
                    }
                }
                //GCS回告移库处理
                if (gcsOrderReport.getTaskType() == 3) {
                    if (gcsOrderReport.getWorkStatus() == 2) {
                        McsTaskInfo byTaskId = mcsTaskInfoService.getByTaskId(billCode);
                        byTaskId.setStatus(102);
                        mcsTaskInfoService.update(byTaskId);
                        int i = eisRequest(billCode);
                        if (i > 0) {
                            errorData.setErrMsg("");
                            errorData.setTaskId("");
                            jsonData.setData(errorData);
                            jsonData.setMsg("接收成功");
                            jsonData.setRet(true);
                            return jsonData;
                        }
                    }
                } else {
                    errorData.setErrMsg("");
                    errorData.setTaskId("");
                    jsonData.setData(errorData);
                    jsonData.setMsg("接收成功");
                    jsonData.setRet(true);
                    return jsonData;
                }
            }
        }
        jsonData.setMsg("数据为空");
        jsonData.setRet(false);
        return jsonData;
    }

    private int eisRequest(String billCode) {
        McsTaskInfo byTaskIds = mcsTaskInfoService.getByTaskId(billCode);
        McsTaskReturn mcsTaskReturn = new McsTaskReturn();
        mcsTaskReturn.setId(StringUtil.getUUID32());
        mcsTaskReturn.setTaskId(byTaskIds.getTaskId());
        mcsTaskReturn.setType(byTaskIds.getType());
        mcsTaskReturn.setContainerNo(byTaskIds.getStockId());
        mcsTaskReturn.setStatus(2);
        mcsTaskReturn.setReportStatus(1);
        mcsTaskReturn.setCreateTime(new Date());
        int insert = mcsTaskReturnService.insert(mcsTaskReturn);
        byTaskIds.setStatus(80);

        mcsTaskInfoService.update(byTaskIds);
        if (insert > 0) {
            return insert;
        }
        return 0;
    }


    //region eis任务下发接口

    /**
     * EIS任务下发接口
     *
     * @param eisRequestDto
     * @return
     */
    @PostMapping("/Request")
    @ResponseBody
    public Object addTaskByEis(@RequestBody EisRequestDto eisRequestDto) {

        Date begin = new Date();
        JsonData jsonData = addTask(eisRequestDto.getCarryList());
        Date end = new Date();
        ///记录接口任务下发日志
        interfaceLogService.insertInterfaceLog("EIS出入库任务下发", eisRequestDto, begin, jsonData, end);
        return jsonData;
    }

    /**
     * @param eisRequests
     * @return
     */
    public JsonData addTask(List<EisRequest> eisRequests) {
        JsonData jsonData = new JsonData();
        String message = "";
        boolean isOk = false;
        List<String> errorList = new ArrayList<>();
        if (eisRequests.size() > 0) {
            for (EisRequest eisRequest : eisRequests) {
                McsPlcVariable mcsPlcVariable = mcsPlcVariableService.getByTypeAndCoord(eisRequest.getSource());
                McsPlcVariable mcsPlcVariableTarget = mcsPlcVariableService.getByTypeAndCoord(eisRequest.getTarget());
                if (!StringUtils.isEmpty(eisRequest) && !eisRequest.getTarget().equals("-1") && !eisRequest.getTarget().equals("1")) {
                    if (StringUtils.isEmpty(mcsPlcVariable)) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务,源位置在MCS不存在";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(mcsPlcVariableTarget)) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务,目的位置在MCS不存在";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (!mcsPlcVariable.getGroupNumber().equals("0")) {
                        if (mcsPlcVariable.getDirection() != eisRequest.getType()) {
                            message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的任务类型与提升机方向不一致";
                            errorList.add(eisRequest.getTaskId());
                            continue;
                        }
                    }
                    if (!mcsPlcVariable.getGroupNumber().equals(mcsPlcVariableTarget.getGroupNumber())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的原位置和目标位置不在同一提升机或目的位置到不了";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    //是通过提升机的出入库任务需判断当前提升机的状态和是否锁定
                    if (mcsPlcVariable.getType() == PlcAddressType.EXIT.getValue()) {
                        McsHoist mcsHoist = mcsHoistService.getMcsHoistById(mcsPlcVariable.getGroupNumber());
                        if (mcsHoist.getStatus() == 1 || mcsHoist.getLock() == 1 || mcsHoist.getStatus() == 3) {
                            message += "任务编号为:" + eisRequest.getTaskId() + "的任务,的提升机处于故障状态或跨层锁定状态";
                            errorList.add(eisRequest.getTaskId());
                            continue;
                        }
                    }
                    if (StringUtils.isEmpty(eisRequest.getTaskId())) {
                        message += ",任务单号为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    McsTaskInfo mcsTaskInfo = mcsTaskInfoService.getByTaskId(eisRequest.getTaskId());
                    if (!StringUtils.isEmpty(mcsTaskInfo)) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务,已存在";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getType())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的任务类型为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getStockId())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的托盘号为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getSource())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的源位置为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getTarget())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的目的位置为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                    if (StringUtils.isEmpty(eisRequest.getPriority())) {
                        message += ",任务编号为:" + eisRequest.getTaskId() + "的任务，的优先级为空";
                        errorList.add(eisRequest.getTaskId());
                        continue;
                    }
                }
                int i = insertTaskInfo(eisRequest, mcsPlcVariable);
                if (i > 0) {
                    isOk = true;
                } else {
                    message += ",任务编号为:" + eisRequest.getTaskId() + "插入数据库失败";
                    errorList.add(eisRequest.getTaskId());
                }
            }
            jsonData.setData(errorList);
            isOk = message.equals("") ? true : false;
            jsonData.setSuccess(isOk);
            jsonData.setMessage(message);
        } else {
            jsonData.setSuccess(false);
            jsonData.setMessage("请求任务为空");
        }
        return jsonData;
    }

    ///插入数据库
    private int insertTaskInfo(EisRequest eisRequest, McsPlcVariable mcsPlcVariable) {
        McsTaskInfo mcsTaskInfo = new McsTaskInfo();
        mcsTaskInfo.setTaskId(eisRequest.getTaskId());
        mcsTaskInfo.setMcsId(StringUtil.getTaskCode());
        mcsTaskInfo.setType(eisRequest.getType());
        mcsTaskInfo.setHoistId(mcsPlcVariable.getGroupNumber());
        mcsTaskInfo.setStockId(eisRequest.getStockId());
        mcsTaskInfo.setSource(eisRequest.getSource());
        mcsTaskInfo.setTarget(eisRequest.getTarget());
        mcsTaskInfo.setWeight(eisRequest.getWeight());
        mcsTaskInfo.setPriority(eisRequest.getPriority());
        mcsTaskInfo.setCreateTime(new Date());
        mcsTaskInfo.setStatus(TaskStage.SEND.getValue());
        mcsTaskInfo.setAddressType(mcsPlcVariable.getType());
        mcsTaskInfo.setPlcName(mcsPlcVariable.getPlcName());
        return mcsTaskInfoService.insert(mcsTaskInfo);
    }
    //endregion

    //region 跨层任务下发接口
    @PostMapping("/CrossLayer")
    @ResponseBody
    public Object addCrossLayer(@RequestBody EisCrossLayer eisCrossLayer) {
        Date begin = new Date();
        JsonData jsonData = addCrossLayerTask(eisCrossLayer);
        Date end = new Date();
        ///记录接口任务下发日志
        interfaceLogService.insertInterFaceLogByResPCross("EIS跨层任务下发", eisCrossLayer, begin, jsonData, end);
        return jsonData;
    }

    private JsonData addCrossLayerTask(EisCrossLayer eisCrossLayer) {
        JsonData jsonData = new JsonData();
        //未下发任务数量
        int noSendCount = mcsTaskInfoService.getCountByStatus(eisCrossLayer.getHoistId(), TaskStage.SEND.getValue());
        //正在执行任务数量
        int startCount = mcsTaskInfoService.getCountByStatus(eisCrossLayer.getHoistId(), TaskStage.START.getValue());
        //未下发跨层任务数量
        int noSendCross = mcsCrossLayerTaskService.getByStatusAndHoist(TaskStage.SEND.getValue(), eisCrossLayer.getHoistId()).size();
        //正在执行跨层任务数量
        int startCountCross = mcsCrossLayerTaskService.getByStatusAndHoist(TaskStage.START.getValue(), eisCrossLayer.getHoistId()).size();
        if (noSendCount + startCount > 0) {
            jsonData.setMessage("当前提升机有出入库任务未做完，需等待执行完");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (noSendCross + startCountCross > 0) {
            jsonData.setMessage("当前提升机有跨层任务未做完，需等待执行完");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (StringUtils.isEmpty(eisCrossLayer.getTaskId())) {
            jsonData.setMessage("任务单号为空");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        McsCrossLayerTask mcsCrossLayerTask = mcsCrossLayerTaskService.getByTaskId(eisCrossLayer.getTaskId());
        if (!StringUtils.isEmpty(mcsCrossLayerTask)) {
            jsonData.setMessage("任务单号为" + eisCrossLayer.getTaskId() + "已存在");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (eisCrossLayer.getFloor() < 1 || eisCrossLayer.getFloor() > 5) {
            jsonData.setMessage("任务单号为" + eisCrossLayer.getTaskId() + "的楼层不存在");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (StringUtils.isEmpty(eisCrossLayer.getHoistId())) {
            jsonData.setMessage("任务单号为" + eisCrossLayer.getTaskId() + "的提升机编号为空");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (eisCrossLayer.getTaskType() < 1 || eisCrossLayer.getTaskType() > 3) {
            jsonData.setMessage("任务单号为" + eisCrossLayer.getTaskId() + "的任务类型不存在");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        if (StringUtils.isEmpty(eisCrossLayer.getId())) {
            jsonData.setMessage("任务单号为" + eisCrossLayer.getTaskId() + "的UUId为空");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
            return jsonData;
        }
        int i = insertCrossLayer(eisCrossLayer);
        if (i > 0) {
            jsonData.setSuccess(true);
            jsonData.setMessage("任务下发成功");
        } else {
            jsonData.setMessage("任务编号为:" + eisCrossLayer.getTaskId() + "插入数据库失败");
            jsonData.setData(eisCrossLayer.getTaskId());
            jsonData.setSuccess(false);
        }
        return jsonData;
    }

    /**
     * 插入数据库
     *
     * @param eisCrossLayer
     * @return
     */
    private int insertCrossLayer(EisCrossLayer eisCrossLayer) {
        McsCrossLayerTask mcsCrossLayerTask = new McsCrossLayerTask();
        mcsCrossLayerTask.setId(StringUtil.getUUID32());
        mcsCrossLayerTask.setTaskId(eisCrossLayer.getTaskId());
        mcsCrossLayerTask.setFloor(eisCrossLayer.getFloor());
        mcsCrossLayerTask.setTaskType(eisCrossLayer.getTaskType());
        mcsCrossLayerTask.setHoistId(eisCrossLayer.getHoistId());
        mcsCrossLayerTask.setCreateTime(new Date());
        mcsCrossLayerTask.setStatus(TaskStage.SEND.getValue());
        return mcsCrossLayerTaskService.insert(mcsCrossLayerTask);

    }


    //endregion

    //region 清除数据下发接口
    @PostMapping("/ClearTask")
    @ResponseBody
    public Object addCrossLayer(@RequestBody EisClearTaskDto eisClearTaskDto) {
        Date begin = new Date();
        JsonData jsonData = new JsonData();
        jsonData.setSuccess(true);
        Date end = new Date();
        ///记录接口任务下发日志
        interfaceLogService.insertInterfaceLog("EIS清除任务下发", eisClearTaskDto, begin, jsonData, end);
        return jsonData;
    }

    private JsonData addClearTask(List<GcsClearTask> eisClearTasks) {
        JsonData jsonData = new JsonData();
        String message = "";
        boolean isOk = false;
        List<String> errorList = new ArrayList<>();
        if (eisClearTasks.size() > 0) {
            for (GcsClearTask eisClearTask : eisClearTasks) {
                if (StringUtils.isEmpty(eisClearTask.getTaskId())) {
                    message += ",任务单号为空";
                    errorList.add(eisClearTask.getTaskId());
                    continue;
                }
                McsClearTask mcsClearTask = mcsClearTaskService.getByTaskId(eisClearTask.getTaskId());
                if (!StringUtils.isEmpty(mcsClearTask)) {
                    message += ",任务单号为" + eisClearTask.getTaskId() + "已存在";
                    errorList.add(eisClearTask.getTaskId());
                    continue;
                }
                if (StringUtils.isEmpty(eisClearTask.getContainerId())) {
                    message += ",任务单号为" + eisClearTask.getTaskId() + "的托盘号为空";
                    errorList.add(eisClearTask.getTaskId());
                    continue;
                }
                if (StringUtils.isEmpty(eisClearTask.getLocIdFrom())) {
                    message += ",任务单号为" + eisClearTask.getTaskId() + "的清除坐标为空";
                    errorList.add(eisClearTask.getTaskId());
                    continue;
                }
                if (StringUtils.isEmpty(eisClearTask.getType())) {
                    message += ",任务单号为" + eisClearTask.getTaskId() + "的任务类型为空";
                    errorList.add(eisClearTask.getTaskId());
                    continue;
                }
                int i = 1;
                if (i > 0) {
                    isOk = true;
                } else {
                    message += ",任务编号为:" + eisClearTask.getTaskId() + "插入数据库失败";
                    errorList.add(eisClearTask.getTaskId());
                }
            }
            jsonData.setData(errorList);
            isOk = message.equals("") ? true : false;
            jsonData.setSuccess(isOk);
            jsonData.setMessage(message);
        }
        return jsonData;
    }

    @PostMapping("/ClearTaskToMcs")
    @ResponseBody
    public Object addCrossLayer(@RequestBody GcsClearTask gcsClearTask) {
        Date begin = new Date();
        jsonDataGcs jsonData = new jsonDataGcs();
        jsonData.setRet(true);
        jsonData.setData(gcsClearTask);
        jsonData.setMsg("操作成功");
        Date end = new Date();
        ///记录接口任务下发日志
        interfaceLogService.insertInterfaceLog("GCS清除任务下发", gcsClearTask, begin, jsonData, end);
        return jsonData;
    }
    //endregion

    private int status;




    /**
     * 小车换层回告
     */
    @PostMapping("/api/v1/master/gcs/interface/CrossLayerReport")
    @ResponseBody
    public Object rgvLayerTaskStatus(@RequestBody GcsLayerTaskReturn gcsLayerTaskReturn) {

        return updateLayerTask(gcsLayerTaskReturn);
    }

    private jsonDataGcs updateLayerTask(GcsLayerTaskReturn gcsLayerTaskReturnList) {
        jsonDataGcs jsonData = new jsonDataGcs();
        if (!StringUtils.isEmpty(gcsLayerTaskReturnList)) {
            String taskId = gcsLayerTaskReturnList.getTaskId();
            String eisTaskId = taskId.substring(0, taskId.length() - 1);
            String rgvId = gcsLayerTaskReturnList.getRgvId();
            int taskType = gcsLayerTaskReturnList.getTaskType();
            int workStatus = gcsLayerTaskReturnList.getWorkStatus();
            if (taskType == 1 && workStatus == 2) {
                McsRgvChangeStoryTask byTaskIdAndStatus = mcsRgvChangeStoryTaskService.getIdStatus(eisTaskId, 4);
                if (!StringUtils.isEmpty(byTaskIdAndStatus)) {
                    //小车到达接驳口更新为5
                    byTaskIdAndStatus.setStatus(5);
                    byTaskIdAndStatus.setRgvId(rgvId);
                    mcsRgvChangeStoryTaskService.update(byTaskIdAndStatus);
                    jsonData.setRet(true);
                    jsonData.setMsg("操作成功");
                    gcsLayerTaskReturnList.setCreateTime(String.valueOf(new Date()));
                    jsonData.setData(gcsLayerTaskReturnList);
                    return jsonData;
                }
            } else if (taskType == 2 && workStatus == 2) {
                McsRgvChangeStoryTask byTaskIdAndStatus = mcsRgvChangeStoryTaskService.getIdStatus(eisTaskId, 6);
                if (!StringUtils.isEmpty(byTaskIdAndStatus)) {
                    //小车进入提升机完成更新为7
                    byTaskIdAndStatus.setStatus(7);
                    byTaskIdAndStatus.setRgvId(rgvId);
                    mcsRgvChangeStoryTaskService.update(byTaskIdAndStatus);
                    jsonData.setRet(true);
                    jsonData.setMsg("操作成功");
                    gcsLayerTaskReturnList.setCreateTime(String.valueOf(new Date()));
                    jsonData.setData(gcsLayerTaskReturnList);
                    return jsonData;
                }

            } else if (taskType == 3 && workStatus == 2) {
                //解锁提升机

                McsRgvChangeStoryTask gcsId = mcsRgvChangeStoryTaskService.getGcsId(9, "1111");
                if (!StringUtils.isEmpty(gcsId)) {
                    McsHoist hoist = mcsHoistService.getHoist(gcsId.getHoistId());
                    hoist.setLock(0);
                    mcsHoistService.updateMcsHoist(hoist);
                    //小车出提升机完成更新为10
                    gcsId.setStatus(10);
                    mcsRgvChangeStoryTaskService.update(gcsId);
                    jsonData.setRet(true);
                    jsonData.setMsg("操作成功");
                    gcsLayerTaskReturnList.setCreateTime(String.valueOf(new Date()));
                    jsonData.setData(gcsLayerTaskReturnList);
                    return jsonData;
                }

                McsRgvChangeStoryTask byTaskIdAndStatus = mcsRgvChangeStoryTaskService.getIdStatus(eisTaskId, 9);
                if (!StringUtils.isEmpty(byTaskIdAndStatus)) {
                    McsHoist hoist = mcsHoistService.getHoist(byTaskIdAndStatus.getHoistId());
                    hoist.setLock(0);
                    mcsHoistService.updateMcsHoist(hoist);
                    //小车出提升机完成更新为9
                    byTaskIdAndStatus.setStatus(10);
                    mcsRgvChangeStoryTaskService.update(byTaskIdAndStatus);

                    McsTaskReturn mcsTaskReturn = new McsTaskReturn();
                    mcsTaskReturn.setId(StringUtil.getUUID32());

                    mcsTaskReturn.setTaskId(byTaskIdAndStatus.getTaskId());
                    mcsTaskReturn.setType(4);
                    mcsTaskReturn.setStatus(2);
                    mcsTaskReturn.setReportStatus(1);
                    mcsTaskReturn.setCreateTime(new Date());
                    mcsTaskReturnService.insert(mcsTaskReturn);

                    jsonData.setRet(true);
                    jsonData.setMsg("操作成功");
                    gcsLayerTaskReturnList.setCreateTime(String.valueOf(new Date()));
                    jsonData.setData(gcsLayerTaskReturnList);
                    return jsonData;
                }
            }
            jsonData.setRet(true);
            jsonData.setMsg("操作成功");
            gcsLayerTaskReturnList.setCreateTime(String.valueOf(new Date()));
            jsonData.setData(gcsLayerTaskReturnList);
            return jsonData;
        }
        jsonData.setRet(false);
        jsonData.setMsg("数据为空");
        return jsonData;
    }

    /**
     * 小车充电换层接口
     *
     * @return
     */
    @PostMapping("/gcsCharge")
    @ResponseBody
    public Object gcsCharge(@RequestBody GcsCharge gcsCharge) {
        jsonDataGcs jsonData = new jsonDataGcs();
        if (!StringUtils.isEmpty(gcsCharge)) {
            McsRgvChangeStoryTask mcsRgvChangeStoryTask = new McsRgvChangeStoryTask();
            mcsRgvChangeStoryTask.setId(StringUtil.getUUID32());
            mcsRgvChangeStoryTask.setTaskId(StringUtil.getUUID32());
            mcsRgvChangeStoryTask.setRgvId(gcsCharge.getRgvId());
            mcsRgvChangeStoryTask.setSource(gcsCharge.getSourceFloor());
            mcsRgvChangeStoryTask.setTarget(gcsCharge.getTargetFloor());
            mcsRgvChangeStoryTask.setCreateTime(gcsCharge.getCreateTime());
            mcsRgvChangeStoryTask.setGcsId("1111");
            mcsRgvChangeStoryTask.setStatus(1);
            int insert = mcsRgvChangeStoryTaskService.insert(mcsRgvChangeStoryTask);
            if (insert > 0) {
                jsonData.setRet(true);
                jsonData.setMsg("操作成功");
            } else {
                jsonData.setRet(false);
                jsonData.setMsg("操作失败");
            }
        } else {
            jsonData.setRet(false);
            jsonData.setMsg("数据为空");
        }
        return jsonData;
    }
}

