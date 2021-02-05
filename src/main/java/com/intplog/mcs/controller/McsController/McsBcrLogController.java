package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsAlarmLog;
import com.intplog.mcs.bean.model.McsModel.McsBcrLog;
import com.intplog.mcs.service.McsService.McsBcrLogService;
import com.intplog.mcs.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: mcs
 * @description
 * @author: tianlei
 * @create: 2020-03-14 12:39
 **/
@Controller
@RequestMapping("/mcsBcrLog")
public class McsBcrLogController {

    @Autowired
    private McsBcrLogService mcsBcrLogService;
    @GetMapping("/data")
    @ResponseBody
    public Object toData(String createTime, String boxNum, String bcrNum, int page, int limit) {
        String ctime = createTime.equals("") ? null : createTime;
        String boxN = boxNum.equals("") ? null : boxNum;
        String bcrN = bcrNum.equals("") ? null : bcrNum;
        return mcsBcrLogService.getAll(ctime, boxN, bcrN, page, limit);
    }
    @GetMapping("/")
    public  Object toList(){
        return "bcrLog/list";
    }
    @DeleteMapping("/{day}")
    public String deleteLog(@PathVariable("day") int day) {
        int i = mcsBcrLogService.deleteMcsLog(day);
        if (i > 0) {
            return "删除错误日志成功";
        } else {
            return "删除错误日志失败";
        }
    }

    @RequestMapping("/toAdd")
    @ResponseBody
    public String toAddLog(McsBcrLog mcs) {
        int i = mcsBcrLogService.insertMcsLog(mcs);
        if (i > 0) {
            return "添加读码器日志成功";
        } else {
            return "添加读码器日志失败";
        }
    }
    @GetMapping("export")
    public void exportExcel(HttpServletResponse response, String createTime, String boxNum, String bcrNum){
        String code=boxNum.equals("")?null:boxNum;
        String bcrCreateTime=createTime.equals("")?null:createTime;
        String bcrId=bcrNum.equals("")?null:bcrNum;
        List<McsBcrLog> mcsAlarmLogList=mcsBcrLogService.getListExcel(bcrCreateTime,code,bcrId);
        ExcelUtils.exportExcel(mcsAlarmLogList,"MCS读码器日志","MCS读码器日志",McsBcrLog.class,"MCS读码器日志.xls",response);
    }
}

