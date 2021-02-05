package com.intplog.mcs.service.impl;


import com.intplog.mcs.bean.model.SysParam;
import com.intplog.mcs.service.SysParamService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * @author jiangzhongxing
 * @Date 2019-12-23 19:29
 */
@Service
public class InitDataListener implements InitializingBean, ServletContextAware {

    /**
     * WCS请求地址
     */
    public static String EIS_URL = "";

    public static String GCS_Host_URL = "";


    @Resource
    private SysParamService sysParamService;


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        SysParam eis_url = sysParamService.getSysParamByCode("EIS_URL");
        SysParam gcs_url = sysParamService.getSysParamByCode("GCS_URL");
        if (!StringUtils.isEmpty(eis_url))
            EIS_URL = eis_url.getValue();
        if (!StringUtils.isEmpty(eis_url))
            GCS_Host_URL = gcs_url.getValue();

    }


}