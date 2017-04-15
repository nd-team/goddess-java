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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@RequestMapping("demo/ticket")
@DefaultProperties
public class TicketAct {

    @Autowired
    private TicketAPI ticketAPI;

    private int cc = 0;
    @GetMapping("test")
    public Result tt() throws ActException{
        cc++;
        System.out.println(cc);
        if(cc<=20){
            throw new RuntimeException("tt exception");
        }
        return new ActResult("success");
    }


    @GetMapping("test1")
    public Result aa(){
        return new ActResult("success");
    }

    @GetMapping("test2")
    public Result aa1(){
        return new ActResult("success");
    }

    /**
     * 初始化创建车票
     *
     * @version v1
     * @throws ActException
     */
    @PostMapping("v1/createTicket")
    public Result createTicket() throws ActException {
        try {
            //初始化5张票
            List<TicketBO> ticketBOS = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                TicketBO bo = new TicketBO();
                bo.setMoney(142);
                bo.setNumber("68234-3423-5435-3423" + i); //票号
                bo.setOffTime(DateUtil.dateToString(LocalDateTime.now()));
                bo.setPosition("NO-" + i);// 座位号唯一
                ticketBOS.add(bo);
            }


            List<TicketBO> bos = ticketAPI.createTicket(ticketBOS);
            List<TicketVO> vos = BeanTransform.copyProperties(bos, TicketVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 剩余车票
     * @version v1
     * @throws ActException
     */
    @GetMapping("v1/surplusTicket")
    public Result surplusTicket() throws ActException {
        try {
            List<TicketBO> bos = ticketAPI.queryTicket();
            List<TicketVO> vos = BeanTransform.copyProperties(bos, TicketVO.class);
            Map<String,Object> ticketBOMap = new HashMap<>(2);
            ticketBOMap.put("surplusCount",String.valueOf(vos.size()));
            ticketBOMap.put("tickets",vos);
            return ActResult.initialize(ticketBOMap);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
