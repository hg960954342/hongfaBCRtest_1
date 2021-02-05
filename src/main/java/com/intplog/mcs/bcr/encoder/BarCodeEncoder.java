package com.intplog.mcs.bcr.encoder;

import com.intplog.mcs.bcr.bean.BarCodeData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

/**
 * @Classname BarCodeEncoder
 * @Description TODO
 * @Date 2020/2/24 16:06
 * @Created by jiangzhongxing
 */
public class BarCodeEncoder extends MessageToByteEncoder<BarCodeData> {
    @Override
    protected void encode(ChannelHandlerContext ctx, BarCodeData msg, ByteBuf out) throws Exception {
        out.writeByte(02);
        out.writeBytes(Unpooled.copiedBuffer(msg.getId(), CharsetUtil.UTF_8));
        out.writeBytes(Unpooled.copiedBuffer("#", CharsetUtil.UTF_8));
        if (!StringUtil.isNullOrEmpty(msg.getBarcode()))
            out.writeBytes(Unpooled.copiedBuffer(msg.getBarcode(), CharsetUtil.UTF_8));
        out.writeByte(03);
    }
}
