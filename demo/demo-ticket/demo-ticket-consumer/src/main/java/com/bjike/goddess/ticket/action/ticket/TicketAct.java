package com.bjike.goddess.ticket.action.ticket;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.interceptor.idem.IdempotencyInterceptor;
import com.bjike.goddess.common.consumer.interceptor.idem.Info;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.ticket.api.TicketAPI;
import com.bjike.goddess.ticket.bo.TicketBO;
import com.bjike.goddess.ticket.vo.TicketVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @PostMapping(value = "test",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result tt(@Validated @RequestBody Info j, BindingResult result) throws ActException{
        return tt1(j,result);
    }

    @PostMapping(value = "test")
    public Result tt1(@Validated Info j, BindingResult result) throws ActException{
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(JSON.toJSON(j));
        System.out.println("进来了");
        return new ActResult("yes");
    }


    @PostMapping("test1")
    @HystrixCommand(commandKey = "a")
    public void aa(HttpServletRequest request,HttpServletResponse response){
        ResponseContext.writeData(response,new ActResult("success"));
        IdempotencyInterceptor.UpdateRepeatResult(request,new ActResult("success"));
    }

    @GetMapping("test2")
    @HystrixCommand(commandKey = "a")
    public Result aa1(){
        return new ActResult("你好");
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
