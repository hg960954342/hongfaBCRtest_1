package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsLog;
import com.intplog.mcs.service.McsService.McsLogService;
import com.intplog.mcs.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author quqingmao
 * @date 2019-10-11
 */
@Controller
@RequestMapping("/mcsLog")
public class McsLogController {
    @Autowired
    private McsLogService mcsLogService;

    @RequestMapping("/")
    public String mcsLog() {
        return "mcslog/list";
    }

    /**
     * @param createTime
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/data")
    @ResponseBody
    public Object toData(String createTime, int page, int limit) {

        String ctime = createTime.equals("") ? null : createTime;
        return mcsLogService.getAll(ctime, page, limit);
    }

    @PostMapping("/add")
    @ResponseBody
    public String toAddMcsLog(McsLog mcsLog) {

        int i = mcsLogService.insertMcsLog(mcsLog);

        if (i > 0) {
            return "添加mcsLog信息成功！";
        } else {
            return "添加mcsLog信息失败！";
        }
    }

    @PutMapping("/up")
    @ResponseBody
    public String toEditMcsLog(McsLog info) {

        McsLog mcsLog = mcsLogService.getMcsLogById(info.getId());
        if (mcsLog != null) {
            mcsLog.setType(info.getType());
            mcsLog.setContent(info.getContent());
            mcsLog.setCreateTime(info.getCreateTime());
            int i = mcsLogService.updateMcsLog(mcsLog);
            if (i > 0) {
                return "修改信息成功！";
            }
        }
        return "修改信息失败";
    }

    /**
     * 删除数据
     *
     * @param date
     * @return
     */
    @DeleteMapping("/del")
    @ResponseBody
    public Object toDeleteMcsLog(int date) {


        return mcsLogService.deleteMcsLog(date);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response , String createTime){
        String createTimes = createTime.equals("") ? null : createTime;
        List<McsLog> mcsLogList = mcsLogService.getListExcel(createTimes);
        ExcelUtils.exportExcel(mcsLogList ,"MCS日志","MCS日志",McsLog.class,"MCS日志.xls",response);
    }
}
