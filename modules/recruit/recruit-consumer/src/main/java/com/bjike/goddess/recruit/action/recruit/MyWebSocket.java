package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.WanyJackson;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailUserBO;
import com.bjike.goddess.recruit.api.IntelligentMsgAPI;
import com.bjike.goddess.recruit.api.LifeOGAPI;
import com.bjike.goddess.recruit.api.WorkOGAPI;

import com.bjike.goddess.recruit.bo.LifeOGBO;
import com.bjike.goddess.recruit.bo.WorkOGBO;
import com.bjike.goddess.recruit.entity.IntelligentMsg;
import com.bjike.goddess.recruit.entity.LifeOG;
import com.bjike.goddess.recruit.entity.WorkOG;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {
    @Autowired
    private WorkOGAPI workOGAPI;
    @Autowired
    private IntelligentMsgAPI intelligentMsgAPI;
    @Autowired
    private LifeOGAPI lifeOGAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    private static ConcurrentHashMap<String, Session> map = new ConcurrentHashMap<String, Session>();

    private static ConcurrentHashMap<String, List<String>> userMap = new ConcurrentHashMap<String, List<String>>();

    private static ConcurrentHashMap<String, String> userList = new ConcurrentHashMap<String, String>();

    @OnOpen

    public void onOpen(Session session) throws IOException {
//        positionDetailUserAPI
        map.put(session.getQueryString(), session);
//        System.out.println(map);
        webSocketSet.add(this);
//        System.out.println(session.getQueryString());
        addOnlineCount();
        System.out.println("------上线消息-----");
        System.out.println("当前连接数" + getOnlineCount());
        System.out.println("当前用户--" + map);
        Iterator<Map.Entry<String, Session>> iterator = map.entrySet().iterator();
        //上线发消息
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            if (userMap.containsKey(key)) {
                List<String> list = userMap.get(key);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("{\"index\":\"" + list.get(i) + "\"}");
                    if (list.get(i).indexOf("\"alertIndexBOS\"") != -1) {
                        map.get(key).getBasicRemote().sendText(list.get(i));
                    } else {
                        map.get(key).getBasicRemote().sendText("{\"index\":\"" + list.get(i) + "\"}");
                    }
                }
                userMap.remove(key);
            }
        }
        System.out.println("等待发送用户---" + userMap);
        System.out.println("-----我是结束线-------");
    }

    @OnClose
    public void onClose(Session session) {
        map.remove(session.getQueryString());
        subOnlineCount();
        System.out.println("当前连接数" + getOnlineCount());
    }

    @OnMessage
    public synchronized void onMessage(String data) throws IOException, SerException {
        System.out.println(data);
    }

    public synchronized void sendMsg(String msg, String name) throws IOException {
        if (map.containsKey(name)) {
            map.get(name).getBasicRemote().sendText(msg);
        }
    }

    public synchronized void sendMsg1(String index, String content) throws IOException, SerException {
        String[] names = {"ike002689", "ike002669", "ike002592", "ike002651", "ike002623","罗怡","何思萍","刘国玲","胡媚","陈珊珊"};
        for (String name : names) {
            if (userList.containsKey(name)) {
                map.get(name).getBasicRemote().sendText("{\"index\":\"" + index + "\"}");
//                map.get(userList.get(name)).getBasicRemote().sendText("{\"index\":\"" + index + "\"}");
            } else {
                if (userMap.containsKey(name)) {
                    userMap.get(name).add(index);
                } else {
                    List<String> list = new ArrayList();
                    list.add(index);
                    userMap.put(name, list);
                }
            }
            IntelligentMsg intelligentMsg = new IntelligentMsg(LocalDateTime.now().toString(), content, name);
            intelligentMsgAPI.add(intelligentMsg);
        }
        System.out.println("等待发送用户---" + userMap);
    }

    public void scoreMsg(String data) throws IOException, SerException {
        LifeOGBO lifeOGBO = WanyJackson.superman(data, LifeOGBO.class);
        lifeOGAPI.add(BeanTransform.wanycopyProperties(lifeOGBO, LifeOG.class));
    }

    public void test(String id) throws SerException {
        workOGAPI.del(id);
    }

    public void dataMsg(String data) throws IOException, SerException {

        Iterator<Map.Entry<String, Session>> iterator = map.entrySet().iterator();
        WorkOGBO workOGBO = WanyJackson.superman(data, WorkOGBO.class);

        if (data.indexOf("\"state\":\"接受\"") != -1) {

            WorkOG workOG = workOGAPI.findByModular(workOGBO.getModular());
            WorkOG workOG1 = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);
            workOG.setAlertIndices(workOG1.getAlertIndices());
            workOGAPI.update(workOG);
            //msg 对方已接受对赌
            map.get(workOGBO.getRaters()).getBasicRemote().sendText("{\"index\":\"msg\"}");

        }

        if (data.indexOf("\"state\":\"取消\"") != -1) {
            System.out.println("进去取消");
//            workOGAPI.getWorkOG()
            System.out.println(workOGBO.getModular());
            workOGAPI.del(workOGBO.getModular());
        }

        if (data.indexOf("state") != -1) {

        } else {
            System.out.println("第一次对赌消息");
            WorkOGBO workOGBO1 = WanyJackson.superman(data, WorkOGBO.class);
            System.out.println(data);
            WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO1, WorkOG.class);
            workOG.setModular(UUID.randomUUID().toString().replace("-", ""));
            workOGAPI.addWork(workOG);
            ObjectMapper objectMapper = new ObjectMapper();
            String newdata = objectMapper.writeValueAsString(workOG);
            if (map.containsKey(userList.get(workOG.getScoringOB()))) {
//                map.get(workOGBO.getScoringOB()).getBasicRemote().sendText(data);
                map.get(userList.get(workOGBO.getScoringOB())).getBasicRemote().sendText(newdata);
            } else {
                if (userMap.containsKey(workOG.getScoringOB())) {
                    System.out.println(workOG.getScoringOB()+"纯在，取出user");
//                    userMap.get(workOG.getScoringOB()).add(data);
                    userMap.get(userList.get(workOG.getScoringOB())).add(data);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(data);
//                    userMap.put(workOG.getScoringOB(), list);
                    System.out.println(workOG.getScoringOB()+"不纯在，添加新user");
                    System.out.println(workOG.getScoringOB());
                    System.out.println(userList.get(workOG.getScoringOB()));
                    userMap.put(userList.get(workOG.getScoringOB()), list);
                }
            }
//            while (iterator.hasNext()) {
//                if (iterator.next().getKey().equals(workOGBO.getScoringOB())) {
//                    map.get(workOGBO.getScoringOB()).getBasicRemote().sendText(data);
//                }
//            }
        }
        System.out.println("待发送信息" + userMap);
        System.out.println("在线人数" + onlineCount + "---" + map);
    }

    @Scheduled(cron ="0 0 11 * * ?")
    public void userList() throws SerException {
        System.out.println("执行数据录入");
        List<PositionDetailUserBO> userBOS = positionDetailUserAPI.wanyfindAll();
        System.out.println(userBOS);
        if (userBOS != null) {
            System.out.println("开始录入");
            for (PositionDetailUserBO userBO : userBOS) {
                userList.put(userBO.getName(), userBO.getNumber());
                System.out.println("员工姓名:" + userBO.getName() + "--" + "员工编号:" + userBO.getNumber() + "--添加成功");
            }
        }
        System.out.println("录入结束");
    }

    public void groupMsg() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LifeOGBO lifeOGBO = new LifeOGBO();
        lifeOGBO.setScoringOB("");
        lifeOGBO.setRaters("");
        List<String> list = new ArrayList<String>();
        userMap.put("", list);

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            if (map.containsKey("")) {
                map.get("").getBasicRemote().sendText("");
            }
        }

        if (map.containsKey("")) {
            map.get("").getBasicRemote().sendText("");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
