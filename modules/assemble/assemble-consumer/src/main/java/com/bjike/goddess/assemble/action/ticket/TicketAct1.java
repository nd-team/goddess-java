package com.bjike.goddess.assemble.action.ticket;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购票信息
 */
@RestController
@RequestMapping("demo/ticket1")
@DefaultProperties
public class TicketAct1 {


    @GetMapping("test1")
    public Result tt() throws ActException{
        return new ActResult("success");
    }

}
