package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsTaskLog;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsTaskLogMapper;
import com.intplog.mcs.service.McsService.McsTaskLogService;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/16 12:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsTaskLogServiceImp implements McsTaskLogService {

    @Resource
    private McsTaskLogMapper mcsTaskLogMapper;

    @Override
    public List<McsTaskLog> getMcsTaskLogById(String id) {
        return mcsTaskLogMapper.getMcsTaskLogById(id);
    }

    @Override
    public PageData getAll(String type, String boxNum, int pageNum, int pageSize) {
        PageData pageData=new PageData();
        Page<Object> page= PageHelper.startPage(pageNum,pageSize);
        List<McsTaskLog> mcsTaskLogs=mcsTaskLogMapper.getList(type,boxNum);
        pageData.setMsg("");
        pageData.setCode(0);
        pageData.setCount(page.getTotal());
        pageData.setData(mcsTaskLogs);
        return pageData;
    }

    @Override
    public int insertMcsTaskLog(McsTaskLog mcsTaskLog) {
        mcsTaskLog.setId(StringUtil.getUUID32());
        return mcsTaskLogMapper.insertMcsTaskLog(mcsTaskLog);
    }

    @Override
    public int deleteMcsTaskLog(String id) {
        return mcsTaskLogMapper.deleteMcsTaskLog(id);
    }

    @Override
    public List<McsTaskLog> getListExcel(String createTime) {
        return mcsTaskLogMapper.getListExcel(createTime);
    }
}
