package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.recruit.api.WorkOGAPI;
import com.bjike.goddess.recruit.entity.WorkOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息管理
 */
@RestController
@RequestMapping("MessageMgt")
public class MessageMgt {

    @Autowired
    private WorkOGAPI workOGAPI;

    @GetMapping("v1/msglist")
    public Result getList(String name) throws SerException {
        return new ActResult("success", workOGAPI.getRemind(name));
    }

    @GetMapping("v1/remid")
    public Result getRemid(String name) throws SerException {
        return new ActResult("success", workOGAPI.getWorkMsg(name));
    }

    @GetMapping("v1/wo")
    public Result getWo(String name) throws SerException {
        return new ActResult("success",workOGAPI.getWorkOG(name));
    }
}
