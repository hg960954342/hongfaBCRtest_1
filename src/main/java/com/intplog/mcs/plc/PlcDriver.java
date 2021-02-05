package com.intplog.mcs.plc;

import com.intplog.mcs.bean.model.McsModel.*;
import com.intplog.mcs.common.SpringContextUtil;
import com.intplog.mcs.common.Utils;
import com.intplog.mcs.plc.bean.HoistDataPacket;
import com.intplog.mcs.plc.common.PlcGetByte;
import com.intplog.mcs.service.McsService.*;
import com.intplog.mcs.utils.StringUtil;
import com.intplog.siemens.SiemensNet;
import com.intplog.siemens.bean.ResultData;
import com.intplog.siemens.enumerate.SiemensPLCS;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/7/21
 */
@Slf4j
public class PlcDriver extends Thread {
    private McsPlcLogService mcsPlcLogService;
    private SiemensNet HoistPlc;
    private McsBoxMessageService mcsBoxMessageService;
    public boolean connect;
    /**
     * 是否连接成功
     *
     * @return
     */
    public boolean isConnects() {
        return connect;
    }

    public HoistDataPacket hoistDataPacket = new HoistDataPacket();

    private String ip;

    public String getHoistId() {
        return hoistId;
    }

    public void setHoistId(String hoistId) {
        this.hoistId = hoistId;
    }

    private String hoistId;
    /**
     * 流水号记录
     */
    private String taskIds = "0";

    @Override
    public void run() {
        long temp = 0;
        Date begin;
        boolean isCon = false;
        //等待Spring 容器加载完成
        while (true) {
            try {
                if (SpringContextUtil.getApplicationContext() != null) {
                    init();
                    break;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            try {
                begin = new Date();
                if (HoistPlc == null) {
                    break;
                }
                //连接
                if (isConnects()) {
                    isCon = true;
                    Thread.sleep(1000);
                }
                //断连
                else {
                    if (!isCon) {
//                        isCon = false;
                        String msg = "PLC【" + ip + "】连接中断";
                        log.error(msg);
                    }
                    log.info("5000毫秒后开始重新连接！");
                    Thread.sleep(PlcServer.reconnectTime);
                    connected();
                    //检查是否连接恢复
                    if (isConnects()) {
                        String msg = "提升机【" + ip + "】 连接成功！";
                        log.info(msg);
                    }

                }
                //延时
                if (isCon) {
                    temp = Utils.getTimeDifference(begin);
                    Thread.sleep(temp);
                } else {
                    Thread.sleep(PlcServer.reconnectTime);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error("run:" + ex.getMessage());
            }
        }
    }

    private void connected() {
        if (isConnects()) {
            return;
        }
        try {
            HoistPlc.connect(ip);
            connect = HoistPlc.isConnectd();
        } catch (Exception ex) {
            connect = false;
            log.error("connect:" + ex.getMessage());
        }
    }

    /**
     * 初始化
     */
    public void init() {
        mcsPlcLogService = SpringContextUtil.getBean(McsPlcLogService.class);
        mcsBoxMessageService = SpringContextUtil.getBean(McsBoxMessageService.class);
    }

    /**
     * 创建PLC对象
     *
     * @param ip
     */
    public void createPlc(String ip, SiemensPLCS type) {
        HoistPlc = new SiemensNet(type);
        this.ip = ip;
        connect = false;
    }

    /**
     * 关闭连接
     */
    public void closes() {
        if (connect) {
            connect = false;
            HoistPlc.close();
        }
    }

    /**
     * 读取PLC byte数组信息
     *
     * @param address
     * @param length
     * @return
     */
    public synchronized ResultData readBytes(String address, int length) {
        try {
            Thread.sleep(100);
            return HoistPlc.readBytes(address, length);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 读取PLC 数据信息
     *
     * @param address
     * @return
     */
    public synchronized ResultData readByte(String address) {
        try {
            Thread.sleep(100);
            return HoistPlc.read(address);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 写byte[]数组到PLC
     *
     * @param address
     * @param bytes
     * @return
     */
    public synchronized ResultData writeBytes(String address, byte[] bytes) {
        return HoistPlc.write(address, bytes);

    }

    /**
     * 写byte到PLC
     *
     * @param address
     * @param path
     * @return
     */
    public synchronized ResultData writeByte(String address, byte path) {
        return HoistPlc.write(address,path);

    }

    public boolean sendToPlc(int floor, String address) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((floor >> 8) & 0xFF);
        bytes[1] = (byte) (floor & 0xFF);
        ResultData resultData = writeBytes(address, bytes);
        return resultData.isSuccess();
    }

    public synchronized ResultData read(String address) {
        try {
            Thread.sleep(100);
            return HoistPlc.read(address);
        } catch (Exception ex) {
            return null;
        }
    }

    public synchronized ResultData writeShout(String address, short a) {
        return HoistPlc.write(address, a);
    }

    /**
     * 获取bit
     *
     * @param input
     * @param index
     * @return
     */
    private boolean getBitValue(byte input, int index) {
        int a = 1 << index;
        int value = input & a;
        return value > 0;
    }

    private void addPlcLog(Date createTime, int type, Date rqTime, String connect, String taskId) {
        McsPlcLog mcsPlcLog = new McsPlcLog();
        mcsPlcLog.setId(StringUtil.getUUID32());
        mcsPlcLog.setCreateTime(createTime);
        mcsPlcLog.setRpTime(rqTime);
        mcsPlcLog.setContent(connect);
        mcsPlcLog.setTaskId(taskId);
        mcsPlcLog.setType(type);
        mcsPlcLogService.inset(mcsPlcLog);
    }

}
