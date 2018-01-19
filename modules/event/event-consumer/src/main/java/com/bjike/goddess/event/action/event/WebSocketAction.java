package com.bjike.goddess.event.action.event;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.bo.EventBO;
import com.bjike.goddess.event.bo.FatherBO;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.enums.EventTime;
import com.bjike.goddess.event.enums.PlanType;
import com.bjike.goddess.event.enums.Status;
import com.bjike.goddess.user.api.UserAPI;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/**
 * @Author: [chenjunhao]
 * @Date: [2018-01-16 14:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@ServerEndpoint(value = "/websocket/{user}")
@Component
public class WebSocketAction {


    private EventAPI eventAPI;

    //当前登陆人
    private String currentUser;

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    private CopyOnWriteArraySet<WebSocketAction> webSocketSet = new CopyOnWriteArraySet<WebSocketAction>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private static ApplicationContext applicationContext;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("user") String currentUser, Session session) throws SerException {
        eventAPI = applicationContext.getBean(EventAPI.class);
        setCurrentUser(currentUser);
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + webSocketSet.size());
        try {
            sendMessage(currentUser+session.getId());
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) throws SerException {
        System.out.println("来自客户端的消息:" + message);

        for (WebSocketAction item : webSocketSet) {
            //判断当前人 是否存在
            if (message.equals(item.getCurrentUser())){

                EventDTO dto = new EventDTO();
                dto.setPlanTypes(new PlanType[]{PlanType.DAY});
                dto.setYear(LocalDate.now().getYear());
                dto.setMonth(LocalDate.now().getMonth().getValue());
//        dto.setDay(LocalDate.now().getDayOfMonth());
                dto.setDay(6);

                List<FatherBO> fatherBOS = eventAPI.findByName(dto,item.getCurrentUser());
                List<LocalDateTime> localDateTimes = new ArrayList<>();

                if (fatherBOS.size()>0){
                    List<EventBO> list = fatherBOS.get(0).getEventBOs();

                    for (int i = 0;i<list.size();i++){
                        EventBO bo = list.get(i);
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

                    Boolean bool = false;
                    for (LocalDateTime s:localDateTimes){
                        java.time.Duration duration = java.time.Duration.between( s,  LocalDateTime.now() );
                        if (duration.toMinutes() == 0){
                            bool = true;
                            break;
                        }
                    }
                    try {
//                        item.sendMessage(message);
                        if (bool){
                            this.sendMessage("true");
                        }else {
                            this.sendMessage("false");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        this.sendMessage("今天没有推送");
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

//        if (fatherBOS.size()>0){
//            List<EventBO> list = fatherBOS.get(0).getEventBOs();
//
//            for (int i = 0;i<list.size();i++){
//                EventBO bo = list.get(i);
//                String startTime = bo.getStartTime();
//                String remindTime = bo.getRemindTime();
//                Status eStatus = bo.geteStatus();
//                LocalDateTime day = LocalDateTime.now();
//                if (EventTime.MINUTES_15.name().equals(remindTime)){
//                    LocalDateTime start = DateUtil.parseDateTime(startTime);
//                    day = start.plusMinutes(-15);
//                }else if (EventTime.MINUTES_30.name().equals(remindTime)){
//                    LocalDateTime start = DateUtil.parseDateTime(startTime);
//                    day = start.plusMinutes(-30);
//                }else if (EventTime.MINUTES_60.name().equals(remindTime)){
//                    LocalDateTime start = DateUtil.parseDateTime(startTime);
//                    day = start.plusMinutes(-60);
//                }
//                localDateTimes.add(day);
//            }
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    for (WebSocketAction item : webSocketSet) {
//                        try {
//                            if ("admin".equals(item.getCurrentUser())){
//                                Boolean bool = false;
//                                for (LocalDateTime s:localDateTimes){
//                                    java.time.Duration duration = java.time.Duration.between( s,  LocalDateTime.now() );
//                                    if (duration.toMinutes() == 0){
//                                        bool = true;
//                                        break;
//                                    }
//                                }
//                                if (bool){
//                                    item.sendMessage("true");
//                                }else {
//                                    item.sendMessage("false");
//                                }
//                            }else {
//                                item.sendMessage("false");
//                            }
//                        } catch (IOException e) {
//                            continue;
//                        }
//                    }
//                }
//            }, 1000, 5000);
//        }else {
//            try {
//                this.sendMessage("今天没有推送");
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发生错误时调用
     *
     * */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

     /**
      * 群发自定义消息
      * */
//    public static void sendInfo(String message) throws IOException {
//        for (WebSocketAction item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                continue;
//            }
//        }
//    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketAction.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketAction.onlineCount--;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketAction.applicationContext = applicationContext;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

//    Timer timer = new Timer();
}