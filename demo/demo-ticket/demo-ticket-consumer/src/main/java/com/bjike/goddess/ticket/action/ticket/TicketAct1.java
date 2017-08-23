package com.bjike.goddess.ticket.action.ticket;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.ticket.api.TicketAPI;
import com.bjike.goddess.ticket.bo.TicketBO;
import com.bjike.goddess.ticket.vo.TicketVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
