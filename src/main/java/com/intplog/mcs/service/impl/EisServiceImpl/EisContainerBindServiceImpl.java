package com.intplog.mcs.service.impl.EisServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.EisModel.EisContainerBind;
import com.intplog.mcs.bean.model.McsModel.*;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.enums.*;
import com.intplog.mcs.mapper.EisMapper.EisContainerBindMapper;
import com.intplog.mcs.service.EisService.EisAccountService;
import com.intplog.mcs.service.EisService.EisContainerBindService;
import com.intplog.mcs.service.McsService.*;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author tianlei
 * @Date 2019-10-31 14:11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EisContainerBindServiceImpl implements EisContainerBindService {

    @Resource
    private EisContainerBindMapper eisContainerBindMapper;
    @Autowired
    private McsBcrPropertiesService mcsBcrPropertiesService;
    @Autowired
    private McsBcrLogService mcsBcrLogService;
    @Autowired
    private McsWeightProperTiesService mcsWeightProperTiesService;
    @Autowired
    private McsTaskAlarmReportService mcsTaskAlarmReportService;
    @Autowired
    private McsPlcVariableService mcsPlcVariableService;
    @Autowired
    private McsStackerService mcsStackerService;
    @Autowired
    private EisAccountService eisAccountService;
    @Autowired
    private McsStackerTaskService mcsStackerTaskService;

    @Override
    public PageData getList(int status, String taskId, int pageNum, int pageSize) {
        PageData pd = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<EisContainerBind> all = eisContainerBindMapper.getListByStatus(status, taskId);
        pd.setMsg("");
        pd.setCount(page.getTotal());
        pd.setCode(0);
        pd.setData(all);
        return pd;
    }

    @Override
    public List<EisContainerBind> getAll() {
        return eisContainerBindMapper.getAll();
    }


    @Override
    public List<EisContainerBind> getMcsContainerBindByStatus(int status) {
        return eisContainerBindMapper.getMcsContainerBindByStatus(status);
    }

    @Override
    public EisContainerBind getByStatusAndCode(int status, String taskId) {
        return eisContainerBindMapper.getByStatusAndCode(status, taskId);
    }

    @Override
    public int update(EisContainerBind eisContainerBind) {
        return eisContainerBindMapper.updateTaskStageByCode(eisContainerBind);
    }

    @Override
    public int updateByTaskId(int status, String taskId) {
        return eisContainerBindMapper.updateByReportStatus(status, taskId);
    }

    @Override
    public int updateByReport(int status, String taskId) {
        return eisContainerBindMapper.updateByReportStatus(status, taskId);
    }

    @Override
    public int insertMcsContainerBind(EisContainerBind eisContainerBind) {
        eisContainerBind.setCreateTime(new Date());
        return eisContainerBindMapper.insertMcsContainerBind(eisContainerBind);
    }

    @Override
    public void insertMcsContainer(String id, String code) {
        ///根据读码器ID获取读码器配置信息
        McsBcrProperties mcsBcrProperties = mcsBcrPropertiesService.getByConnectId(id);
        ///插入读码器日志记录表
        insertBcrLog(mcsBcrProperties, code);
        //id为
        if(!StringUtils.isEmpty(mcsBcrProperties)) {
            if (mcsBcrProperties.getType() == 4) {
                McsStacker mcsStacker = mcsStackerService.getByCoord(mcsBcrProperties.getRemark());
                if (!StringUtils.isEmpty(mcsStacker)) {
                    McsStackerTask mcsStackerTask = new McsStackerTask();
                    mcsStackerTask.setId(StringUtil.getUUID32());
                    mcsStackerTask.setType(mcsStacker.getType());
                    mcsStackerTask.setNumber(code);
                    mcsStackerTask.setDeviceNo(mcsStacker.getNumber());
                    mcsStackerTask.setReportStatus(0);
                    mcsStackerTask.setCreateTime(new Date());
                    mcsStackerTaskService.insert(mcsStackerTask);
                }

            } else if (mcsBcrProperties.getType() == 5) {
                McsStacker mcsStacker = mcsStackerService.getByCoord(mcsBcrProperties.getRemark());
                if (!StringUtils.isEmpty(mcsStacker) && !StringUtils.isEmpty(mcsBcrProperties)) {
                    McsStackerTask mcsStackerTask = new McsStackerTask();
                    mcsStackerTask.setId(StringUtil.getUUID32());
                    mcsStackerTask.setType(mcsStacker.getType());
                    mcsStackerTask.setNumber(code);
                    mcsStackerTask.setDeviceNo(mcsStacker.getNumber());
                    mcsStackerTask.setReportStatus(0);
                    mcsStackerTask.setCreateTime(new Date());
                    mcsStackerTaskService.insert(mcsStackerTask);
                }
            } else {
                insertEisBind(code, mcsBcrProperties);
            }
        }
    }

    /**
     * 插入字母托绑定表
     *
     * @param code
     * @param mcsBcrProperties
     */
    private void insertEisBind(String code, McsBcrProperties mcsBcrProperties) {
        //根据入库口判断上报任务状态
        List<EisContainerBind>eisContainerBinds= eisContainerBindMapper.getCountBySource(mcsBcrProperties.getRemark());
        if(eisContainerBinds.size()>0) {
            for(EisContainerBind eisContainerBind:eisContainerBinds){
                eisContainerBindMapper.deleteByTaskId(eisContainerBind.getTaskId());
            }
        }
        int status = getByBcrRemark(mcsBcrProperties.getRemark());
        EisContainerBind eisContainerBind = new EisContainerBind();
        eisContainerBind.setStockId(code);
        eisContainerBind.setTaskId(StringUtil.getUUID32());
        eisContainerBind.setType(BusinessType.PUTIN.getValue());
        eisContainerBind.setSource(mcsBcrProperties.getRemark());
        eisContainerBind.setCreateTime(new Date());
        eisContainerBind.setStatus(status);
        eisContainerBind.setPriority(99);
        eisContainerBindMapper.insertMcsContainerBind(eisContainerBind);
    }

    private int getByBcrRemark(String crood) {
        int status = BindStatus.FIRSTCODEREPORT.getValue();
        if (crood.substring(0, 2).equals("01")) {
            status = BindStatus.TWOCODEREPORT.getValue();
        } else {
            status = BindStatus.WEIGHTREPORT.getValue();
        }
        return status;
    }

    ///收到读码器数据插入读码器日志表
    private void insertBcrLog(McsBcrProperties mcsBcrProperties, String code) {
        McsBcrLog mcsBcrLog = new McsBcrLog();
        mcsBcrLog.setId(StringUtil.getUUID32());
        mcsBcrLog.setBcrNum(mcsBcrProperties.getConnectId());
        mcsBcrLog.setName(mcsBcrProperties.getName());
        mcsBcrLog.setBoxNum(code);
        mcsBcrLog.setCreateTime(new Date());
        mcsBcrLogService.insertMcsLog(mcsBcrLog);
    }

    @Override
    public void updateByWeight(String weight, String ip) {
        McsWeightProperTies mcsWeightProperties = mcsWeightProperTiesService.getByIp(ip);
        //是否为空
        boolean isAddress = StringUtils.isEmpty(mcsWeightProperties);
        if (!isAddress) {
            int bind = BindStatus.TWOCODEREPORT.getValue();
            //获取出入库坐标
            String coord = mcsWeightProperties.getRemark();
            EisContainerBind eisContainerBind = eisContainerBindMapper.getByStatusAndSource(bind, coord);
            if (!StringUtils.isEmpty(eisContainerBind)) {
                McsPlcVariable mcsPlcVariable = mcsPlcVariableService.getByCoord(coord);

                //获取尺寸检测状态
                eisContainerBind.setStatus(BindStatus.WEIGHTREPORT.getValue());
                eisContainerBind.setWeight(weight);
                eisContainerBindMapper.updateTaskStageByCode(eisContainerBind);
            }
        }

    }




    @Override
    public int batchInsert(List<EisContainerBind> mcsContainerBindList) {
        int i = 0;
        for (EisContainerBind eisContainerBind : mcsContainerBindList) {
            i = eisContainerBindMapper.insertMcsContainerBind(eisContainerBind);
        }
        return i;
    }

    @Override
    public List<EisContainerBind> getListBySource(String source, int status) {
        return eisContainerBindMapper.getListByStatusAndSource(status, source);
    }

    @Override
    public EisContainerBind getEisBind(String source, int status) {
        return eisContainerBindMapper.getByStatusAndSource(status, source);
    }


    @Override
    public EisContainerBind getMcsContainerBindByTaskCode(String taskId) {
        return eisContainerBindMapper.getMcsContainerBindByCode(taskId);
    }

    @Override
    public PageData deleteByTaskId(String taskId) {

        String msg = "删除数据失败";
        int i = eisContainerBindMapper.deleteByTaskId(taskId);
        if (i > 0) {
            msg = "删除数据成功";
        }
        PageData pd = new PageData();
        pd.setMsg(msg);
        pd.setCount(1);
        pd.setCode(0);
        pd.setData("");
        return pd;
    }
}

