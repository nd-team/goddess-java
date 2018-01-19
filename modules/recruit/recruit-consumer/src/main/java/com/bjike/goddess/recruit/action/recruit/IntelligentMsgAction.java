package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.IntelligentMsgAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 智能消息提醒
 *
 * @Author: [ wany ]
 * @Date: [ 2018-01-16 11:07 ]
 * @Description: [ 智能消息提醒 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("intelligentmsg")
public class IntelligentMsgAction {
    @Autowired
    private IntelligentMsgAPI intelligentMsgAPI;

    @PostMapping("v1/delete")
    public Result delete(String id) throws SerException {
//        System.out.println(id);
        intelligentMsgAPI.del(id);
        return new ActResult("success",id);
    }
}