package com.bjike.goddess.user.common.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huanghuanlai on 2017/1/10.
 */
@RestController("user")
public class UserAction {

    @GetMapping("login")
    public String login(){
        return "{\"msg\":\"success\"}";
    }
}
