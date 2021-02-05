package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsInterfaceLog;
import com.intplog.mcs.service.McsService.McsBcrInterfaceLogService;
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
@RequestMapping("/mcsInterfaceLog")
public class McsInterfaceLogController {
    @Autowired
    private McsBcrInterfaceLogService mcsBcrInterfaceLogService;

    @RequestMapping("/")
    public String mcsInterface() {

        return "mcslog/interfacelog";
    }

    @PostMapping("/addLog")
    @ResponseBody
    public String addLog(McsInterfaceLog mcsInterfaceLog) {

        int i = mcsBcrInterfaceLogService.save(mcsInterfaceLog);
        if (i > 0) {
            return "保存成功";
        } else {
            return "保存失败";
        }
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public Object selectAll(String method, int page, int limit) {
        String cMethod = method.equals("") ? null : method;
        return mcsBcrInterfaceLogService.getAll(cMethod, page, limit);
    }

    @GetMapping("/selectData")
    public String selectData(String method) {

        mcsBcrInterfaceLogService.getList(method);
        return "bcr";
    }

    @PostMapping("/selectId")
    public String selectId(String id) {

        mcsBcrInterfaceLogService.getInterfaceLogById(id);

        return "bcr";
    }

    @DeleteMapping("/deleteData")
    @ResponseBody
    public String deleteData(@RequestParam("date") int date) {

        int i = mcsBcrInterfaceLogService.deleteLog(date);
        if (i < 1) {
            return "删除失败";
        }
        return "删除成功";
    }

    @PutMapping("/up")
    @ResponseBody
    public String updateMcsInterfaceLog(McsInterfaceLog info) {

        McsInterfaceLog mcsInterfaceLog = mcsBcrInterfaceLogService.getInterfaceLogById(info.getId());
        if (mcsInterfaceLog != null) {
            mcsInterfaceLog.setMethod(info.getMethod());
            mcsInterfaceLog.setRpData(info.getRpData());
            mcsInterfaceLog.setRpTime(info.getRpTime());
            mcsInterfaceLog.setRqData(info.getRqData());
            mcsInterfaceLog.setStatus(info.getStatus());
            mcsInterfaceLog.setRqTime(info.getRqTime());
            int i = mcsBcrInterfaceLogService.updateId(mcsInterfaceLog);
            if (i > 0) {
                return "更新成功";
            }
        }

        return "更新失败";
    }
    @GetMapping("/export")
    public void export(HttpServletResponse response, String method){

        String methods = method.equals("") ? null :method;
        List<McsInterfaceLog> mcsInterfaceLogs = mcsBcrInterfaceLogService.getList(methods);
        ExcelUtils.exportExcel(mcsInterfaceLogs,"接口日志","接口日志",McsInterfaceLog.class,"接口日志.xls",response);
    }
}
