package com.bjike.goddess.event.jods;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.event.action.event.WebSocketAction;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.bo.EventBO;
import com.bjike.goddess.event.bo.FatherBO;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.enums.EventTime;
import com.bjike.goddess.event.enums.PlanType;
import com.bjike.goddess.event.enums.Status;
import com.bjike.goddess.recruit.action.recruit.MyWebSocket;
import com.bjike.goddess.recruit.action.recruit.WorkOGAction;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.apache.poi.hssf.record.OldFormulaRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.websocket.Session;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-22 10:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class Jods {

    @Autowired
    private EventAPI eventAPI;

    //concurrent包的线程安全Set，用来存放每个客户端提醒时间。
    private static CopyOnWriteArraySet<Map<String,List>> jodsSet = new CopyOnWriteArraySet<>();

//    public final static long ONE_Minute =  60 * 1000;
    public final static long ONE_Minute = 10 * 1000;


//    RestTemplate restTemplate = new RestTemplate();
//    Object result = restTemplate.getForObject("168.192.0.72:51105", String.class);

    @Autowired
    private RestTemplate template;


    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob() throws MalformedURLException, URISyntaxException {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>fixedDelay执行....");
//        System.out.println( jodsSet +" >>fixedDelay执行....");
        ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<>() ;
//        map.put("admin",null);
//        URL url = new URL("http://192.168.0.72:51100/workog/v1/send");
//        URI uri = new URI(url.getProtocol(), url.getHost() + ":" + url.getPort(),url.getPath(), url.getQuery(), null);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Auth-Token", UUID.randomUUID().toString());
//        MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
//        postParameters.add("msg", "true");
//        postParameters.add("user", "admin");
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(postParameters
//                , headers);
//        template.postForObject(uri,requestEntity,String.class);


        URL url = new URL("http://192.168.0.72:51100/workog/v1/getOnline");
        URI uri = new URI(url.getProtocol(), url.getHost() + ":" + url.getPort(),url.getPath(), url.getQuery(), null);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>("", headers);
        String result =  template.postForObject(uri,formEntity,String.class);

        System.out.println( result +" >>fixedDelay执行....");

        //取出
//        for(Map<String,List> m : jodsSet){
//            for (String key : m.keySet()){
//                //判断是否上线
//                if(map.containsKey(key)){
//                    List<LocalDateTime> localDateTimes = m.get(key);
//                    if (localDateTimes.contains(LocalDateTime.now())){
//                        System.out.println("可以发消息拉");
//                    }
//                }
//            }
//        }

    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>fixedRate执行....");
    }

    /**
     * 每天12点定时取第二天最新的数据
     */
    @Scheduled(cron="0 25 15 * * ?")
    public void cronJob(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" >>cron执行....");



        EventDTO dto = new EventDTO();
        dto.setPlanTypes(new PlanType[]{PlanType.DAY});
        dto.setYear(LocalDate.now().getYear());
        dto.setMonth(LocalDate.now().getMonth().getValue());
//        dto.setDay(LocalDate.now().getDayOfMonth());
        dto.setDay(6);

        try {
            List<Object> nameList =  eventAPI.findAllName(dto);
            System.out.println(nameList);
            for (int i = 0;i < nameList.size() ;i++){
                Map<String,List> map = new HashMap<>();
                List<FatherBO> fatherBOS = eventAPI.findByName(dto, nameList.get(i)+"");
                List<LocalDateTime> localDateTimes = new ArrayList<>();

                if (fatherBOS.size()>0){
                    List<EventBO> eventlist = fatherBOS.get(0).getEventBOs();
                    //还没有定制重复
                    for (int j = 0;j<eventlist.size();j++){
                        EventBO bo = eventlist.get(j);
                        String startTime = bo.getStartTime();
                        String remindTime = bo.getRemindTime();
                        Status eStatus = bo.geteStatus();
                        LocalDateTime day = LocalDateTime.now();
                        if (EventTime.MINUTES_15.name().equals(remindTime)){
                            LocalDateTime start = DateUtil.parseDateTime(startTime);
                            day = start.plusMinutes(-15);
                        }else if (EventTime.MINUTES_30.name().equals(remindTime)){
                            LocalDateTime start = DateUtil.parseDateTime(startTime);
                            day = start.plusMinutes(-30);
                        }else if (EventTime.MINUTES_60.name().equals(remindTime)){
                            LocalDateTime start = DateUtil.parseDateTime(startTime);
                            day = start.plusMinutes(-60);
                        }
                        localDateTimes.add(day);
                    }
                }
                map.put(nameList.get(i)+"",localDateTimes);
                jodsSet.add(map);
            }
        } catch (SerException e) {
            e.printStackTrace();
        }

    }

    //线程池
//    public static void exec(){
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        threadPool.execute(new Runnable() {
//            public void run() {
//
//            }
//        });
//    }


}
