package com.bjike.goddess.task.quartz;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.api.CollectSchemeAPI;
import com.bjike.goddess.task.api.CustomizeAPI;
import com.bjike.goddess.task.bo.CollectSchemeBO;
import com.bjike.goddess.task.bo.CustomizeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-31 09:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class Jods {

    @Autowired
    private CustomizeAPI customizeAPI;

    @Autowired
    private CollectSchemeAPI collectSchemeAPI;

    public final static long ONE_Minute = 60  * 1000;

    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob() throws MalformedURLException, URISyntaxException, SerException {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>fixedDelay执行....");
        String localDateTimestr = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
        System.out.println(localDateTimestr + " >>fixedDelay执行....");
        List<CustomizeBO> customizeBOS = customizeAPI.findAll();
        List<CustomizeBO> list = customizeBOS.stream().filter( n -> n.getSendTime() != null && n.getEnable()).distinct().collect(Collectors.toList());
        for (CustomizeBO bo : list){
            if (bo.getSendTime().contains(localDateTimestr)){
                customizeAPI.send(bo.getId());
            }
        }

    }


    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob() throws SerException {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>fixedRate执行....");

        String localDateTimestr = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
        System.out.println(localDateTimestr + " >>fixedRate执行....");
        List<CollectSchemeBO> customizeBOS = collectSchemeAPI.findAll();
        List<CollectSchemeBO> list = customizeBOS.stream().filter( n -> n.getSendTime() != null && n.getEnable()).distinct().collect(Collectors.toList());
        for (CollectSchemeBO bo : list){
            if (bo.getSendTime().contains(localDateTimestr)){
                collectSchemeAPI.send(bo.getId());
            }
        }

    }

    @Scheduled(cron="0 24 11 * * ?")
    public void cronJob(){
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>cron执行....");
    }


}
