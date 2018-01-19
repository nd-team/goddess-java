package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.LifeOGAPI;
import com.bjike.goddess.recruit.bo.LifeOGBO;
import com.bjike.goddess.recruit.entity.LifeOG;
import com.bjike.goddess.recruit.service.LifeOGSer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 工作对赌
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 工作对赌 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("lifeog")
public class LifeOGAction {

    @Autowired
    private MyWebSocket myWebSocket;

    @Autowired
    private LifeOGAPI lifeOGAPI;

    @GetMapping("v1/gg")
    public Result get(String name) throws IOException, SerException {
//        myWebSocket.onMessage(data);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = new HashMap<String, String>();
        map.put("index", "life");
        LifeOGBO lifeOGBO = new LifeOGBO();
        lifeOGBO.setScoringOB("小明");
        String msg = objectMapper.writeValueAsString(lifeOGBO);
        map.put("life", msg);
        String lifeMap = objectMapper.writeValueAsString(map);
//        myWebSocket.sendMsg(lifeMap);
        return new ActResult("success", lifeOGAPI.getlist(name));
    }

    @PostMapping("v1/add")
    public Result add(String data) throws IOException, SerException {
        System.out.println(data);
        myWebSocket.scoreMsg(data);
        return new ActResult("success", data);
    }

    @PostMapping("v1/del")
    public Result del() {

        return new ActResult();
    }
}