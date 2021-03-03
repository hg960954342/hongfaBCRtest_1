package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable1;
import com.intplog.mcs.bean.model.McsModel.McsSlidCount;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsSlidCountMapper;
import com.intplog.mcs.mapper.McsMapper.McsTriggerTaskMapper;
import com.intplog.mcs.service.McsService.McsSlidCountService;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import com.intplog.mcs.utils.DateHelpUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/9/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsSlidCountImpl implements McsSlidCountService {
    @Resource
    private McsSlidCountMapper mcsSlidCountMapper;

    @Override
    public McsSlidCount getMcsSlidCount(String slidId) {
        return mcsSlidCountMapper.getMcsSlidCount(slidId);
    }

    @Override
    public int updateMcsSlidCountData(String boxCount, String actionCount, Date creatTime) {
       return mcsSlidCountMapper.updateMcsSlidCountData(boxCount,actionCount,creatTime);
    }


}
