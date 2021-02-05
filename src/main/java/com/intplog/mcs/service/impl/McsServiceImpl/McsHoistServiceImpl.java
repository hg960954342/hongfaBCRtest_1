package com.intplog.mcs.service.impl.McsServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.intplog.mcs.bean.model.McsModel.McsHoist;
import com.intplog.mcs.bean.model.McsModel.McsInterfaceLog;
import com.intplog.mcs.bean.model.McsModel.McsTaskInfo;
import com.intplog.mcs.bean.viewmodel.PageData;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.mapper.McsMapper.McsHoistMapper;
import com.intplog.mcs.service.McsService.McsHoistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianlei
 * @date 2020-4-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class McsHoistServiceImpl implements McsHoistService {
    @Resource
    private McsHoistMapper mcsHoistMapper;

    @Override
    public PageData getAllList(String id, int pageNum, int pageSize) {
        PageData pd = new PageData();
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<McsHoist> all = mcsHoistMapper.getList(id);
        pd.setMsg("");
        pd.setCount(page.getTotal());
        pd.setCode(0);
        pd.setData(all);
        return pd;
    }


    @Override
    public List<McsHoist> getAll() {
        return mcsHoistMapper.getAll();
    }

    @Override
    public List<McsHoist> getListByPlc(String plcName) {
        return mcsHoistMapper.getListByPLC(plcName);
    }

    @Override
    public McsHoist getMcsHoistById(String id) {
        return mcsHoistMapper.getMcsHoistById(id);
    }

    @Override
    public int insertMcsHoist(McsHoist mcsHoist) {
        return mcsHoistMapper.insert(mcsHoist);
    }

    @Override
    public int updateMcsHoist(McsHoist mcsHoist) {
        return mcsHoistMapper.update(mcsHoist);
    }

    @Override
    public PageData deleteMcsHoistById(String id) {
        PageData pd = new PageData();
        int i = mcsHoistMapper.delete(id);
        pd.setCode(0);
        pd.setMsg("");
        if (i < 1) {
            pd.setMsg("删除数据失败！");
        } else {
            pd.setMsg("删除数据成功");
        }
        return pd;
    }

    @Override
    public McsHoist getPLcName(String name) {
        return mcsHoistMapper.getPlcName(name);
    }

    @Override
    public McsHoist getHoist(String id) {
        return mcsHoistMapper.getHoist(id);
    }


}
