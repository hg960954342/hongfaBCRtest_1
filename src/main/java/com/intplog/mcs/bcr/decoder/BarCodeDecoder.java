package com.intplog.mcs.bcr.decoder;

import com.intplog.mcs.bcr.bean.BarCodeData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.StringUtil;

import java.util.List;

/**
 * @Classname BarCodeDecoder
 * @Description TODO
 * @Date 2020/2/24 16:06
 * @Created by jiangzhongxing
 */
public class BarCodeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        if (length < 3) return;

        if (in.getByte(0) == 2 && in.getByte(length - 1) == 3) {
            String temp = in.toString(CharsetUtil.UTF_8).substring(1, length - 1);
            if (!StringUtil.isNullOrEmpty(temp)) {
                String[] arr = temp.split("#");
                if(arr.length==2) {
                    BarCodeData bcd = new BarCodeData();
                    bcd.setId(arr[0]);
                    bcd.setBarcode(arr[1]);
                    out.add(bcd);
                }
                else if(arr.length==3) {
                    BarCodeData bcd = new BarCodeData();
                    bcd.setLength(arr[0]);
                    bcd.setWidth(arr[1]);
                    bcd.setHeight(arr[2]);
                }

            }
        }
        in.clear();
    }
}
