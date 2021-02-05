package com.intplog.mcs;

import com.intplog.mcs.bean.model.EisModel.EisExistStatusRequest;
import com.intplog.mcs.common.JsonData;
import com.intplog.mcs.plc.bean.HoistPlc;
import com.intplog.mcs.plc.common.PlcTypeUtils;
import com.intplog.mcs.common.SpringContextUtil;
import com.intplog.mcs.service.McsService.McsTaskInfoService;
import com.intplog.mcs.service.McsService.McsTaskOrderReportService;
import com.intplog.siemens.SiemensNet;
import com.intplog.siemens.bean.ResultData;
import com.intplog.siemens.enumerate.SiemensPLCS;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liaoliming
 * @Date 2020/2/19 12:42 下午
 */
@Component
public class aaa {
    public  static McsTaskOrderReportService mcsTaskOrderReportService;
    public static   McsTaskInfoService mcsTaskInfoService;
    @Autowired
    public static RestTemplate restTemplate;
    public  aaa a;

    private static  boolean getbitValue(byte input, int index) {
        int a = 1 << index;
        int value = input & a;
        return value > 0;
    }
    @PostConstruct
    public static void init(){
        mcsTaskInfoService= SpringContextUtil.getBean(McsTaskInfoService.class);
        mcsTaskOrderReportService=SpringContextUtil.getBean(McsTaskOrderReportService.class);
        restTemplate=SpringContextUtil.getBean(RestTemplate.class);

    }

    public  static  void main(String[] args){

        List<EisExistStatusRequest> list= new ArrayList<>();
        EisExistStatusRequest eisExistStatusRequest= new EisExistStatusRequest();
        eisExistStatusRequest.setCoord("0100100013");
        list.add(eisExistStatusRequest);
        String url="api/v1/master/splitFold/splitOutBound";
        JsonData jsonData=restTemplate.postForObject("10.10.8.22:80/Interface/getExitStatus",list,JsonData.class);
        Date end = new Date();
//        JsonData jsonData= new JsonData();
//        boolean is= StringUtils.isEmpty(jsonData.getData());
//        String ip = "10.10.8.100";
//        SiemensPLCS siemensPLCS = PlcTypeUtils.getPlcType(1);
//        SiemensNet siemensNet = new SiemensNet(SiemensPLCS.S1500);
//        siemensNet.connect();
//        ResultData resultData= siemensNet.readBytes("DB*.DBB1242",41);


    }

    /**
     *
     */
    @Test
    public  void contextLoads() {

        List<EisExistStatusRequest> list= new ArrayList<>();
        EisExistStatusRequest eisExistStatusRequest= new EisExistStatusRequest();
        eisExistStatusRequest.setCoord("0100100013");
        list.add(eisExistStatusRequest);
        String url="api/v1/master/splitFold/splitOutBound";
        JsonData jsonData=restTemplate.postForObject("10.10.8.22:80/Interface/getExitStatus",list,JsonData.class);
        Date end = new Date();
           HoistPlc hoistPlc = new HoistPlc();
            hoistPlc.setAddress("DB8.DBB966");
            hoistPlc.setType(3);
            hoistPlc.setTargetLayer(13);
            hoistPlc.setDirection(3);
            hoistPlc.setTaskCode("12346");
            byte[] bytes3 = new byte[16];
            bytes3[0] = (byte) ((hoistPlc.getType() >> 8) & 0xFF);
            bytes3[1] = (byte) ((hoistPlc.getType()) & 0xFF);
            byte[] taskId = hoistPlc.getTaskCode().getBytes();
            for (int i = 0; i < taskId.length; i++) {
                bytes3[2 + i] = (byte)(taskId[i]);
            }

        String dd= "1718460020";
        byte[] bytes=dd.getBytes();
        //
         String date = new Date().toString();
        String ip = "10.10.8.100";
        SiemensPLCS siemensPLCS = PlcTypeUtils.getPlcType(1);
        SiemensNet siemensNet = new SiemensNet(SiemensPLCS.S1500);
        //siemensNet.setPlcIP(ip);
        siemensNet.connect(ip);
        if (siemensNet.isConnectd()) {
            //--------读值
            ResultData resultData = siemensNet.readBytes("DB8.DBB1242",41);
//            byte[] bytes2 = (byte[]) resultData.getValue();
//            int type = PlcGetByte.bytesToInt16Hight2(bytes2, 0);
//            int error = PlcGetByte.bytesToInt16Hight2(bytes2, 13);
//            int layer = PlcGetByte.bytesToInt16Hight2(bytes2, 14);
//            byte[] bytes1 = PlcGetByte.subBytes(bytes2, 2, 10);
//            String code = PlcGetByte.byteToString(bytes2, 2, 10);
//            System.out.println(code);
//            HoistPlc hoistPlc = new HoistPlc();
//            hoistPlc.setAddress("DB8.DBB966");
//            hoistPlc.setType(3);
//            hoistPlc.setTargetLayer(13);
//            hoistPlc.setDirection(3);
//            hoistPlc.setTaskCode("12346");
//            byte[] bytes3 = new byte[16];
//            bytes3[0] = (byte) ((hoistPlc.getType() >> 8) & 0xFF);
//            bytes3[1] = (byte) ((hoistPlc.getType()) & 0xFF);
//            byte[] taskId = hoistPlc.getTaskCode().getBytes();
//            for (int i = 0; i < taskId.length; i++) {
//                bytes3[2 + i] = (byte)(taskId[i]);
//            }
//            bytes3[12] = (byte) ((hoistPlc.getDirection()) & 0xFF);
//            bytes3[13] = (byte) ((hoistPlc.getDirection()) & 0xFF);
//            bytes3[14]=(byte)((hoistPlc.getTargetLayer()>>8)& 0xFF);
//            bytes3[15]=(byte)((hoistPlc.getTargetLayer())& 0xFF);
//            ResultData resultData1 = siemensNet.write(hoistPlc.getAddress(),bytes3);
            System.out.println(resultData);

        } else {

            String content = "PLC:" + ip + "   连接失败";
            String msg = "PLC连接";

        }
    }

}
