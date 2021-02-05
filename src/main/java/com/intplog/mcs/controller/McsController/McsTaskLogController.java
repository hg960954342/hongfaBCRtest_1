package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.bean.model.McsModel.McsTaskLog;
import com.intplog.mcs.service.McsService.McsTaskLogService;
import com.intplog.mcs.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author suizhonghao
 * @version 1.0
 * @date 2019/10/16 14:14
 */
@Controller
@RequestMapping("/mcsTaskLog")
public class McsTaskLogController {

    @Autowired
    private McsTaskLogService mcsTaskLogService;

    /**
     * 系统list页面
     *
     * @return
     */
    @GetMapping("/")
    public String toData() {
        return "mcsTaskLog/list";
    }

    /**
     * Json请求数据
     *
     * @param type
     * @param boxNum
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/Data")
    @ResponseBody
    public Object toData(String boxNum, String type, int page, int limit) {
        String _type = type.equals("") ? null : type;
        String _boxNum = boxNum.equals("") ? null : boxNum;
        return mcsTaskLogService.getAll(_type, _boxNum, page, limit);
    }

    /**
     * 插入信息
     *
     * @param mcsTaskLog
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public String toAddMcsTaskLog(@RequestBody McsTaskLog mcsTaskLog) {
        int i = mcsTaskLogService.insertMcsTaskLog(mcsTaskLog);

        if (i > 0) {
            return "插入信息成功";
        } else {
            return "插入信息失败";
        }
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public Object toDeleteMcsTaskLog(@RequestBody String id) {
        return mcsTaskLogService.deleteMcsTaskLog(id);
    }

    /**
     * 导出EXCEL
     *
     * @param response
     * @param createTime
     */
    @GetMapping("/export")
    public void exportMcsTaskLog(HttpServletResponse response, String createTime) {
        String _createTime = createTime.equals("") ? null : createTime;
        List<McsTaskLog> mcsTaskLogList = mcsTaskLogService.getListExcel(_createTime);
        ExcelUtils.exportExcel(mcsTaskLogList, "MCSTaskLog日志", "MCSTaskLog日志", McsTaskLog.class, "MCSTaskLog日志.xls", response);
    }
}
