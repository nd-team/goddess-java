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
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket implements Serializable {

    @Autowired
    private WorkOGAPI workOGAPI;
    @Autowired
    private IntelligentMsgAPI intelligentMsgAPI;
    @Autowired
    private LifeOGAPI lifeOGAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private static int onlineCount = 0;

    private static ApplicationContext context;

    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();//WebSocket对象

    public static ConcurrentHashMap<String, Session> onlineMap = new ConcurrentHashMap<String, Session>();//当前在线用户

    private static ConcurrentHashMap<String, List<String>> userMap = new ConcurrentHashMap<String, List<String>>();//离线用户消息缓存

    private static ConcurrentHashMap<String, String> userList = new ConcurrentHashMap<String, String>();//用户编号与用户名称

    private static ConcurrentHashMap<String, String> numList = new ConcurrentHashMap<String, String>();//用户名称与用户编号

    /**
     * 每次上线执行一次onOpen
     *
     * @param session
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        workOGAPI = context.getBean(WorkOGAPI.class);
        onlineMap.put(session.getQueryString().toLowerCase(), session);
//        System.out.println(map);
        webSocketSet.add(this);
//        System.out.println(session.getQueryString());
        addOnlineCount();
        System.out.println("------上线消息-----");
        System.out.println("当前连接数" + getOnlineCount());
        System.out.println("当前用户--" + onlineMap);
        Iterator<Map.Entry<String, Session>> iterator = onlineMap.entrySet().iterator();
        //上线发消息
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            if (userMap.containsKey(key)) {
                List<String> list = userMap.get(key);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("{\"index\":\"" + list.get(i) + "\"}");
                    if (list.get(i).indexOf("\"alertIndexBOS\"") != -1) {
                        onlineMap.get(key).getBasicRemote().sendText(list.get(i));
                    } else {
                        onlineMap.get(key).getBasicRemote().sendText("{\"index\":\"" + list.get(i) + "\"}");
                    }
                }
                userMap.remove(key);
            }
        }
        System.out.println("等待发送用户---" + userMap);
        System.out.println("-----我是结束线-------");
    }

    /**
     * 下线执行一次onClose
     *
     * @param session
     */

    @OnClose
    public void onClose(Session session) {
        onlineMap.remove(session.getQueryString());
        subOnlineCount();
        System.out.println("当前连接数" + getOnlineCount());
        webSocketSet.remove(this);
    }

    /**
     * 对赌状态更改通过onMessage
     *
     * @param data
     * @throws IOException
     * @throws SerException
     */
    @OnMessage
    public synchronized void onMessage(String data) throws IOException, SerException {
        System.out.println("onMessage---Data" + data);
        WorkOGBO workOGBO = WanyJackson.superman(data, WorkOGBO.class);
        ObjectMapper objectMapper = new ObjectMapper();
        WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);

        if (data.indexOf("\"state\":\"已读\"") != -1) {
            System.out.println("已读");
            System.out.println(data);
            WorkOGBO ogbo = workOGAPI.findByTime(workOGBO.getTime());
            System.out.println(ogbo);
            if (ogbo != null) {
                WorkOG og = BeanTransform.wanycopyProperties(ogbo, WorkOG.class);
                og.setState(workOGBO.getState());
                workOGAPI.update(og);
            }

        }

        if (data.indexOf("\"state\":\"未读\"") != -1) {
            System.out.println("未读");
            WorkOGBO ogbo = workOGAPI.findByTime(workOGBO.getTime());
            if (ogbo != null) {
                WorkOG og = BeanTransform.wanycopyProperties(ogbo, WorkOG.class);
                og.setState(workOGBO.getState());
                System.out.println("workOGBO---" + objectMapper.writeValueAsString(og));
                workOGAPI.update(og);
            }
//            System.out.println("workOGBO---" + objectMapper.writeValueAsString(ogbo));
        }

        if (data.indexOf("\"state\":\"接受\"") != -1) {
            System.out.println("接受");
            WorkOGBO ogbo = workOGAPI.findByTime(workOGBO.getTime());
            if (ogbo != null) {
                WorkOG og = BeanTransform.wanycopyProperties(ogbo, WorkOG.class);
                og.setState(workOGBO.getState());
                workOGAPI.update(og);
            }
//            System.out.println("workOGBO---" + objectMapper.writeValueAsString(ogbo));

        }
        System.out.println("onMessage");
        System.out.println("onMessage" + data);
        System.out.println("-------onMessage-------");
    }


    public synchronized void sendMsg(String msg, String name) throws IOException {
        if (onlineMap.containsKey(name)) {
            onlineMap.get(name).getBasicRemote().sendText(msg);
        }
    }


    /**
     * @param index
     * @param content
     * @param username
     * @throws IOException
     * @throws SerException
     */


    public synchronized void sendMsg1(String index, String content, String... username) throws IOException, SerException {
        String[] names = {"ike002689", "ike002669", "ike002592", "ike002651", "ike002623"};
        for (String name : names) {
            if (onlineMap.containsKey(name)) {
//                onlineMap.get(name).getBasicRemote().sendText("{\"index\":\"" + index + "\"}");
                onlineMap.get(userList.get(name)).getBasicRemote().sendText("{\"index\":\"" + index + "\"}");
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

    /**
     * 发送对赌数据方法
     *
     * @param data
     * @throws IOException
     * @throws SerException
     */
    public void dataMsg(String data) throws IOException, SerException {
//        System.out.println("每次经过--" + data);
        Iterator<Map.Entry<String, Session>> iterator = onlineMap.entrySet().iterator();
        WorkOGBO workOGBO = WanyJackson.superman(data, WorkOGBO.class);


        if (data.indexOf("\"state\":\"编辑\"") != -1) {
            System.out.println("进入编辑");
            System.out.println(data);
//            System.out.println("\"state\":\"编辑\"}");
//            String editData = data.replace("\"state\":\"编辑\"}", "\"state\":\"编辑\",\"indexType\":\"hello\"}");
//            System.out.println("修改后" + editData);
            System.out.println("workOGBO-->" + workOGBO);
            WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);
            System.out.println(workOG.getScoringOB());
            if (onlineMap.containsKey(workOG.getScoringOB())) {
                onlineMap.get(workOG.getScoringOB()).getBasicRemote().sendText(data);
            } else {
                if (userMap.containsKey(workOG.getScoringOB())) {
                    userMap.get(workOG.getScoringOB()).add(data);
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(data);
                    userMap.put(workOG.getScoringOB(), list);
                }
            }
            System.out.println(workOG);
//            workOGAPI.update(workOG);
        }

        if (data.indexOf("\"state\":\"已读\"") != -1) {
            System.out.println("进入已读");
            WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);
            workOGAPI.update(workOG);
        }

        if (data.indexOf("\"state\":\"未读\"") != -1) {
            System.out.println("进入未读");
            WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);
            workOGAPI.update(workOG);
        }

        if (data.indexOf("\"state\":\"接受\"") != -1) {
            WorkOG workOG = workOGAPI.findByModular(workOGBO.getModular());
            WorkOG workOG1 = BeanTransform.wanycopyProperties(workOGBO, WorkOG.class);
            workOG.setAlertIndices(workOG1.getAlertIndices());
            workOGAPI.update(workOG);
            //msg 对方已接受对赌
            onlineMap.get(workOGBO.getRaters()).getBasicRemote().sendText("{\"index\":\"msg\"}");
        }


        if (data.indexOf("\"state\":\"取消\"") != -1) {
            System.out.println("进去取消");
//            workOGAPI.getWorkOG()
            System.out.println(workOGBO.getScoringOB());
            System.out.println(workOGBO.getRaters());
            System.out.println(userMap.get(workOGBO.getScoringOB()));
            onlineMap.get(workOGBO.getRaters()).getBasicRemote().sendText("{\"index\":\"取消对赌\"}");
            System.out.println(workOGBO.getModular());
            workOGAPI.del(workOGBO.getModular());
        }

        if (data.indexOf("\"state\":null") != -1) {
            System.out.println("第一次对赌消息");
            System.out.println(data);
//            workOG.setModular(uuid);

            String newdata = data.replace("\"state\":\"null\"", "\"state\":\"未读\"");
//            System.out.println(newdata);
//            userList.get(workOG.getScoringOB())
            WorkOGBO workOGBO1 = WanyJackson.superman(newdata, WorkOGBO.class);
            WorkOG workOG = BeanTransform.wanycopyProperties(workOGBO1, WorkOG.class);
            workOGAPI.addWork(workOG);
//            userList.get(workOG.getScoringOB())
            if (onlineMap.containsKey(userList.get(workOG.getScoringOB()))) {
//                onlineMap.get(workOGBO.getScoringOB()).getBasicRemote().sendText(newdata);
                onlineMap.get(userList.get(workOGBO.getScoringOB())).getBasicRemote().sendText(newdata);
            } else {
                if (userMap.containsKey(workOG.getScoringOB())) {
                    System.out.println(workOG.getScoringOB() + "纯在，取出user");
//                    userMap.get(workOG.getScoringOB()).add(data);//线下
                    userMap.get(userList.get(workOG.getScoringOB())).add(newdata);//线上
                } else {
                    List<String> list = new ArrayList<String>();
                    list.add(data);
//                    userMap.put(workOG.getScoringOB(), list);
//                    System.out.println(workOG.getScoringOB()+"不纯在，添加新user");
                    System.out.println(workOG.getScoringOB());
//                    System.out.println(userList.get(workOG.getScoringOB()));
                    userMap.put(userList.get(workOG.getScoringOB()), list);
                }
            }
            System.out.println("-----第一次对赌结束线------");
        }
        System.out.println("待发送信息" + userMap);
        System.out.println("在线人数" + onlineCount + "---" + onlineMap);
    }

    /**
     * 获取所有用户名单
     *
     * @throws SerException
     */
    @Scheduled(fixedDelay = 120 * 1000)
    public void userList() throws SerException {
        System.out.println("执行数据录入");
        List<PositionDetailUserBO> userBOS = positionDetailUserAPI.wanyfindAll();
//        System.out.println(userBOS);
        if (userBOS != null) {
            System.out.println("开始录入");
            for (PositionDetailUserBO userBO : userBOS) {
                userList.put(userBO.getName(), userBO.getNumber().toLowerCase());
                numList.put(userBO.getNumber().toLowerCase(), userBO.getName());
                System.out.println("员工姓名:" + userBO.getName() + "--" + "员工编号:" + userBO.getNumber() + "--添加成功");
            }
        }
        System.out.println("录入结束");
        System.out.println("用户名单" + userList + "");
    }

    public void groupMsg() throws IOException {

    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        MyWebSocket.context = context;
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
