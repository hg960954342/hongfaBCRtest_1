package com.intplog.mcs.service.McsService;

import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import com.intplog.mcs.bean.viewmodel.PageData;

import java.util.List;

/**
 * @author quqingmao
 * @date 2019-10-09
 */
public interface McsBcrPropertiesService {

        int save(McsBcrProperties param);

        PageData getAll(String name, String connectId, int pageNum, int pageSize);
        List<McsBcrProperties> getAllList();

        List<McsBcrProperties> getBcrName(String name, String connectId);

        PageData deleteBcrConnectId(String id);

        int updateMcsBcr(McsBcrProperties mcsBcrProperties);

        McsBcrProperties getBcrId(String id);

        McsBcrProperties getByConnectId(String connectId);

        int batchInsert(List<McsBcrProperties> mcsBcrProperties);
}
