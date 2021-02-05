package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsAlarmLog;
import com.intplog.mcs.service.McsService.McsAlarmLogService;
import com.intplog.mcs.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: wcs
 * @description
 * @author: tianlei
 * @create: 2019-10-12 11:37
 **/
@Controller
@RequestMapping("/mcsAlarmLog")
public class McsAlarmLogController {
        @Autowired
        private McsAlarmLogService mcsAlarmLogService;

        @GetMapping("/data")
        @ResponseBody
        public Object toData(String createTime, String id, String type, int page, int limit) {
                String ctime = createTime.equals("") ? null : createTime;
                String tp = type.equals("") ? null : type;
                String taskId = id.equals("") ? null : id;
                return mcsAlarmLogService.getAll(ctime, taskId, tp, page, limit);
        }
        @GetMapping("/")
        public  Object toList(){
                return "mcsAlarmLog/list";
        }
        @DeleteMapping("/{day}")
        public String deleteLog(@PathVariable("day") int day) {
                int i = mcsAlarmLogService.deleteLog(day);
                if (i > 0) {
                        return "删除错误日志成功";
                } else {
                        return "删除错误日志失败";
                }
        }

        @PutMapping("/")
        @ResponseBody
        public String updateLog(McsAlarmLog mcsAlarmLog) {
                McsAlarmLog mcsAlarmLog1 = mcsAlarmLogService.getMcsAlarmLogById(mcsAlarmLog.getId());
                if (mcsAlarmLog1 != null) {
                        mcsAlarmLog1.setCreateTime(mcsAlarmLog.getCreateTime());
                        mcsAlarmLog1.setContent(mcsAlarmLog.getContent());
                        mcsAlarmLog1.setType(mcsAlarmLog.getType());
                        int i = mcsAlarmLogService.updateMcsAlarmLog(mcsAlarmLog1);
                        if (i > 0) {
                                return "更新错误日志成功";
                        } else {
                                return "更新错误日志失败";
                        }
                }
                return "更新错误日志失败";
        }

        @RequestMapping("/toAdd")
        @ResponseBody
        public String toAddLog(McsAlarmLog mcsAlarmLog) {
                int i = mcsAlarmLogService.insertMcsAlarmLog(mcsAlarmLog);

                if (i > 0) {
                        return "添加错误日志成功";
                } else {
                        return "添加错误日志失败";
                }
        }
        @GetMapping("export")
        public void exportExcel(HttpServletResponse response, String id, String type, String createTime){
                String bcrId=id.equals("")?null:id;
                String bcrCreateTime=createTime.equals("")?null:createTime;
                String bcrType=type.equals("")?null:type;
                List<McsAlarmLog> mcsAlarmLogList=mcsAlarmLogService.getList(bcrCreateTime,bcrId,bcrType);
                ExcelUtils.exportExcel(mcsAlarmLogList,"输送线错误日志","输送线错误日志",McsAlarmLog.class,"输送线错误日志.xls",response);
        }
}
