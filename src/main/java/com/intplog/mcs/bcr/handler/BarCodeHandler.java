package com.intplog.mcs.bcr.handler;

import com.intplog.mcs.bcr.bean.BarCodeData;
import com.intplog.mcs.bean.model.McsModel.McsTriggerTask;
import com.intplog.mcs.common.SpringContextUtil;
import com.intplog.mcs.plc.PlcDriver;
import com.intplog.mcs.plc.PlcServer;
import com.intplog.mcs.service.EisService.EisContainerBindService;
import com.intplog.mcs.service.McsService.McsTriggerTaskService;
import com.intplog.mcs.utils.StringUtil;
import com.intplog.siemens.bean.ResultData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Classname BarCodeHandler
 * @Description TODO
 * @Date 2020/2/24 15:00
 * @Created by jiangzhongxing
 */

@Slf4j
public class BarCodeHandler extends SimpleChannelInboundHandler<BarCodeData> {

    /**
     * 心跳报文竞间隔时间（单位秒）
     */
    private final int delay = 3;

    /**
     * 连接
     */
    private boolean connected;


    /**
     * 条码数据
     */
    private BarCodeData activeData = new BarCodeData();

    /**
     * @return
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * 编号
     */
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    private static int bytesToInt(byte[] bs) {
        int a = 0;
        for (int i = bs.length - 1; i >= 0; i--) {
            a += bs[i] * Math.pow(0xFF, bs.length - i - 1);
        }
        return a;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BarCodeData msg) throws Exception {
        try {
            Date date = new Date();
            McsTriggerTask mcsTriggerTask = new McsTriggerTask();
            mcsTriggerTask.setId(StringUtil.getUUID32());
            mcsTriggerTask.setBcrId(msg.getId());
            mcsTriggerTask.setBcrCode(msg.getBarcode());
            mcsTriggerTask.setLength("无");
            mcsTriggerTask.setWidth("无");
            mcsTriggerTask.setHeight("无");
            if(mcsTriggerTask.getBcrId().equals("33")){
                PlcDriver plcDriver = PlcServer.getHoistDriverMap().get("1F_PLC");
                ResultData data = plcDriver.readBytes("DB11.DBB8", 4);
                byte[] value = (byte[]) data.getValue();

                String weight = String.valueOf(this.bytesToInt(value));
                mcsTriggerTask.setWeight(weight);

                ResultData data1 = plcDriver.readBytes("DB2.DBB8", 1);
                byte[] value1 = (byte[]) data1.getValue();
                String shape = String.valueOf(this.bytesToInt(value1));

                if(shape.equals("0")){
                    mcsTriggerTask.setShape(true);
                }
                else{
                    mcsTriggerTask.setShape(false);
                }
            }
            else if(mcsTriggerTask.getBcrId().equals("37")){
                PlcDriver plcDriver1 = PlcServer.getHoistDriverMap().get("2F_PLC");
                ResultData data2 = plcDriver1.readBytes("DB11.DBB12", 4);
                byte[] value2 = (byte[]) data2.getValue();
                mcsTriggerTask.setWeight(String.valueOf(this.bytesToInt(value2)));

                ResultData data3 = plcDriver1.readBytes("DB2.DBB2", 1);
                byte[] value3 = (byte[]) data3.getValue();
                String shape = String.valueOf(this.bytesToInt(value3));

                if(shape.equals("0")){
                    mcsTriggerTask.setShape(true);
                }
                else{
                    mcsTriggerTask.setShape(false);
                }
            }
            else if(mcsTriggerTask.getBcrId().equals("46")){
                PlcDriver plcDriver2 = PlcServer.getHoistDriverMap().get("2F_PLC");
                ResultData data4 = plcDriver2.readBytes("DB11.DBB8", 4);
                byte[] value4 = (byte[]) data4.getValue();
                mcsTriggerTask.setWeight(String.valueOf(this.bytesToInt(value4)));

                ResultData data5 = plcDriver2.readBytes("DB2.DBB19", 1);
                byte[] value5 = (byte[]) data5.getValue();
                String shape = String.valueOf(this.bytesToInt(value5));

                if(shape.equals("0")){
                    mcsTriggerTask.setShape(true);
                }
                else{
                    mcsTriggerTask.setShape(false);
                }
            }
            else if(mcsTriggerTask.getBcrId().equals("53")){
                PlcDriver plcDriver3 = PlcServer.getHoistDriverMap().get("3F_PLC");
                ResultData data6 = plcDriver3.readBytes("DB11.DBB8", 4);
                byte[] value6 = (byte[]) data6.getValue();
                mcsTriggerTask.setWeight(String.valueOf(this.bytesToInt(value6)));

                ResultData data7 = plcDriver3.readBytes("DB2.DBB10", 1);
                byte[] value7 = (byte[]) data7.getValue();
                String shape = String.valueOf(this.bytesToInt(value7));

                if(shape.equals("0")){
                    mcsTriggerTask.setShape(true);
                }
                else{
                    mcsTriggerTask.setShape(false);
                }
            }
            else if(mcsTriggerTask.getBcrId().equals("59")){
                PlcDriver plcDriver4 = PlcServer.getHoistDriverMap().get("4F_PLC");
                ResultData data8 = plcDriver4.readBytes("DB11.DBB8", 4);
                byte[] value8 = (byte[]) data8.getValue();
                mcsTriggerTask.setWeight(String.valueOf(this.bytesToInt(value8)));

                ResultData data9 = plcDriver4.readBytes("DB2.DBB10", 1);
                byte[] value9 = (byte[]) data9.getValue();
                String shape = String.valueOf(this.bytesToInt(value9));

                if(shape.equals("0")){
                    mcsTriggerTask.setShape(true);
                }
                else{
                    mcsTriggerTask.setShape(false);
                }
            }
            else {
                mcsTriggerTask.setWeight("0");
                mcsTriggerTask.setShape(true);
            }
            mcsTriggerTask.setStatus(1);
            mcsTriggerTask.setCreateTime(date);
            System.out.print(date+" 收到 222222 BarCodeHandler ：" + mcsTriggerTask.getId()+"\n");
            System.out.print(date+" 收到 333333 BarCodeHandler ：" + mcsTriggerTask.getBcrCode()+"\n");
            System.out.print(date+" 收到 444444 BarCodeHandler ：" + mcsTriggerTask.getWeight()+"\n");
            System.out.print(date+" 收到 555555 BarCodeHandler ：" + mcsTriggerTask+"\n");

            log.info("收到：" + msg.getId() + "    " + msg.getBarcode());
            McsTriggerTaskService mcsTriggerTaskService = SpringContextUtil.getBean(McsTriggerTaskService.class);
            mcsTriggerTaskService.insertMcsTriggerTaskData(mcsTriggerTask);
            System.out.print(date+" 收到 666666 BarCodeHandler ：注册成功" +"\n");
        } catch (Exception ex) {
            log.info(String.valueOf(ex));
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        connected = true;
        activeData.setId(this.id);
        ctx.writeAndFlush(activeData);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            switch (event.state()) {
                case READER_IDLE:
                case WRITER_IDLE:
                    ctx.writeAndFlush(activeData);
                    break;
                case ALL_IDLE:
                    break;
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        connected = false;
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("设备连接中断!");
        log.info(id + "设备连接中断!");

    }

    /**
     * 异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        connected = false;
        cause.printStackTrace();
        ctx.close();
    }


}
