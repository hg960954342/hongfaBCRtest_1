package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsInterfaceLog;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.mapper.McsMapper.McsInterfaceLogMapper;
import com.intplog.mcs.service.McsService.McsBcrInterfaceLogService;
import com.intplog.mcs.utils.DateHelpUtil;
import com.intplog.mcs.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2019-10-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsInterfaceLogImpl implements McsBcrInterfaceLogService {


    @Resource
    private McsInterfaceLogMapper mcsInterfaceLogMapper;


    @Override
    public PageData getAll(String method, int pageNum, int pageSize) {
        PageData pd = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsInterfaceLog> all = mcsInterfaceLogMapper.getList(method);
        pd.setMsg("");
        pd.setCount(page.getTotal());
        pd.setCode(0);
        pd.setData(all);
        return pd;

    }

    @Override
    public McsInterfaceLog getInterfaceLogById(String Id) {
        return mcsInterfaceLogMapper.getInterfaceLogById(Id);
    }

    @Override
    public List<McsInterfaceLog> getList(String method) {
        return mcsInterfaceLogMapper.getList(method);
    }



    @Override
    public int deleteLog(int date) {
        Date date1 = DateHelpUtil.getDate(date);
        return mcsInterfaceLogMapper.deleteLog(date1);
    }


    @Override
    public int save(McsInterfaceLog mcsInterfaceLog) {
        mcsInterfaceLog.setId(StringUtil.getUUID32());
        return mcsInterfaceLogMapper.insert(mcsInterfaceLog);
    }

    @Override
    public int updateId(McsInterfaceLog mcsInterfaceLog) {
        return mcsInterfaceLogMapper.updateInterfaceLog(mcsInterfaceLog);
    }
}
