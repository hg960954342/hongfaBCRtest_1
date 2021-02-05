package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;
import org.apache.ibatis.annotations.Param;

import java.rmi.server.RMIClientSocketFactory;
import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/15 12:24
 */
public interface McsPlcVariableService {

    public McsPlcVariable getPlc(String groupNumber, String cooder ,int type );

    List<McsPlcVariable> getExcelExport(String ip, String name);

    JsonData importExcel(List<McsPlcVariable> mcsPlcVariableList);

    PageData getAll(String name, String groupNumber, int pageNum, int pageSize);

    List<McsPlcVariable> getAll();

    List<McsPlcVariable> getByPlcName(String plcName);

    McsPlcVariable getMcsPlcVariableById(String id);

    McsPlcVariable getByTypeAndCoord(String coord);

    McsPlcVariable getByTypeAndName(int type, String name);

    McsPlcVariable getByCoord(String coord);

    int insertMcsPlcVariable(McsPlcVariable mcsPlcVariable);

    int updateMcsPlcVariable(McsPlcVariable mcsPlcVariable);

    int updateMcsPlcVariableByGroupNumber(McsPlcVariable mcsPlcVariable);

    McsPlcVariable getCoordAndType(String coord,int type);
    PageData deleteMcsPlcVariableById(String id);



}
