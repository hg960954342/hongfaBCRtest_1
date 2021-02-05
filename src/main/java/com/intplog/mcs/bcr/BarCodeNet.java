package com.intplog.mcs.bcr;

import com.intplog.mcs.bcr.decoder.BarCodeDecoder;
import com.intplog.mcs.bcr.encoder.BarCodeEncoder;
import com.intplog.mcs.bcr.handler.BarCodeHandler;
import com.intplog.mcs.bean.model.McsModel.McsBcrProperties;
import com.intplog.mcs.bean.model.McsModel.McsLog;
import com.intplog.mcs.bean.model.McsModel.McsPlcVariable;
import com.intplog.mcs.enums.McsLogType;
import com.intplog.mcs.common.SpringContextUtil;
import com.intplog.mcs.service.McsService.McsBcrPropertiesService;
import com.intplog.mcs.service.McsService.McsLogService;
import com.intplog.mcs.service.McsService.McsPlcVariableService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Classname BarCodeNet
 * @Description TODO
 * @Date 2020/2/24 14:59
 * @Created by jiangzhongxing
 */
@Slf4j
public class BarCodeNet extends Thread {



    /**
     * 心跳间隔时间（单位秒）
     */
    private final int activeTime = 3;

    /**
     * plc连接端口
     */
    private int bcr_Port = 51235;

    /**
     * plc设备IP
     */
    private String bcr_IP = "";

    /**
     *
     */
    private String bcr_Id = "";

    /**
     * 事件池
     */
    private EventLoopGroup group;

    public int getBcr_Port() {
        return bcr_Port;
    }

    public void setBcr_Port(int bcr_Port) {
        this.bcr_Port = bcr_Port;
    }

    public String getBcr_IP() {
        return bcr_IP;
    }

    public void setBcr_IP(String bcr_IP) {
        this.bcr_IP = bcr_IP;
    }

    public String getBcr_Id() {
        return bcr_Id;
    }

    public void setBcr_Id(String bcr_Id) {
        this.bcr_Id = bcr_Id;
    }


    /**
     * BCR连接
     */
    private boolean connected;


    /**
     * 是否连接
     *
     * @return
     */
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (SpringContextUtil.getApplicationContext() != null) {
                    break;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            this.connect();
            McsLog mcsLog = new McsLog();
            mcsLog.setCreateTime(new Date());
            mcsLog.setId(com.intplog.mcs.utils.StringUtil.getUUID32());
            mcsLog.setType(McsLogType.BCRCONNECT.getDesc());
            if (isConnected()) {
                mcsLog.setContent("读码器" + getBcr_IP() + "连接成功");
            } else {
                mcsLog.setContent("读码器" + getBcr_IP() + "连接失败");
            }
            McsLogService mcsLogService=SpringContextUtil.getBean(McsLogService.class);
            mcsLogService.insertMcsLog(mcsLog);

        } catch (Exception ex) {

        }
        while (true) {
            try {
                bcrStatus();
                connectAgain();
                Thread.sleep(10000);

            } catch (Exception ex) {

            }
        }
    }

    /**
     * 连接
     */
    public void connect() {
        this.connect(bcr_IP, bcr_Port, bcr_Id);
    }

    /**
     * 客户端连接
     *
     * @param ip
     * @param port
     * @param id
     */
    public void connect(String ip, int port, String id) {
        if (isConnected() || StringUtil.isNullOrEmpty(id)) return; //如果当前连接那就退出

        bcr_IP = ip;
        bcr_Port = port;
        bcr_Id = id;

        Bootstrap bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast("BarCodeDecoder", new BarCodeDecoder());
                        channel.pipeline().addLast("BarCodeEncoder", new BarCodeEncoder());
                        channel.pipeline().addLast(new IdleStateHandler(activeTime, activeTime, activeTime));
                        BarCodeHandler barCodeHandler = new BarCodeHandler();
                        barCodeHandler.setId(id);
                        channel.pipeline().addLast("BarCodeHandler", barCodeHandler);
                    }
                });

        //发起连接操作
        ChannelFuture channelFuture = bootstrap.connect(bcr_IP, bcr_Port);
        channelFuture.awaitUninterruptibly(10000);
        if (!channelFuture.isSuccess()) {
            System.out.println("连接[" + bcr_IP + "]超时！");
            log.info("连接[" + bcr_IP + "]超时！");
            return;
        }else{
            connected=true;
        }

        //注册关闭事件
        channelFuture.channel().closeFuture().addListener(cfl -> {
            close();
        });
    }

    /**
     * 客户端关闭
     */
    public void close() {
        //关闭客户端线程组
        if (group != null) {
            group.shutdownGracefully();
        }
        log.info("关闭连接，释放对象！");
        log.info(getBcr_IP() + "关闭连接，释放对象！");
        //TODO 获取Spring管理对象
        McsBcrPropertiesService mcsBcrPropertiesService=SpringContextUtil.getBean(McsBcrPropertiesService.class);
        McsBcrProperties mcsBcrProperties = mcsBcrPropertiesService.getByConnectId(getBcr_Id());
        if (!StringUtils.isEmpty(mcsBcrProperties)) {
            mcsBcrProperties.setOnline(false);
        }
        mcsBcrPropertiesService.updateMcsBcr(mcsBcrProperties);
    }

    /**
     * 获取读码器状态
     */
    private void bcrStatus() {
        McsBcrPropertiesService mcsBcrPropertiesService=SpringContextUtil.getBean(McsBcrPropertiesService.class);
        McsBcrProperties mcsBcrProperties = mcsBcrPropertiesService.getByConnectId(getBcr_Id());
        if (!StringUtils.isEmpty(mcsBcrProperties)) {
            mcsBcrProperties.setOnline(isConnected());
        }
        mcsBcrPropertiesService.updateMcsBcr(mcsBcrProperties);

    }

    /**
     * bcr掉线，重新建立连接
     */
    private void connectAgain() {
        if (isConnected()) {
            return;
        } else {
            this.close();
            connect();
        }
    }

}