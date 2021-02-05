package com.intplog.mcs.controller.McsController;

import com.intplog.mcs.service.McsService.McsHoistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author quqingmao
 * @date 2020/7/28
 */
@Controller
@RequestMapping("/mcsHoist")
public class McsHoistController {
    @Autowired
    private McsHoistService mcsHoistService;

    /**
     * 系统list页面
     *
     * @return
     */
    @GetMapping("/")
    public String toData() {
        return "mcsHoist/list";
    }

    /**
     * Json请求数据
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/Data")
    @ResponseBody
    public Object toData(String id, int page, int limit) {
        String _name = id.equals("") ? null : id;
        return mcsHoistService.getAllList(_name,page, limit);
    }
}
