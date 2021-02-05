package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsBcrConnect;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;

import java.util.List;

/**
 * @program: wcs
 * @description
 * @author: tianlei
 * @create: 2019-10-12 15:02
 **/
public interface McsBcrConnectService {
        List<McsBcrConnect> getExcelExport(String ip, String name);

        PageData getList(String ip, String name, int pageNum, int pageSize);

        List<McsBcrConnect> getAll();

        McsBcrConnect getMcsBcrConnectById(String id);

        int insertMcsBcrConnect(McsBcrConnect mcsBcrConnect);

        int updateMcsBcrConnect(McsBcrConnect mcsBcrConnect);
        JsonData importExcel(List<McsBcrConnect> mcsBcrConnectList);

        PageData deleteMcsBcrConnect(String id);
}
