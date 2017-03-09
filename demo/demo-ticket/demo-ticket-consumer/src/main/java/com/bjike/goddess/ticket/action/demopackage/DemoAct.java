package com.bjike.goddess.ticket.action.demopackage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.ticket.vo.TicketVO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示功能
 * Created by lake on 17-3-9.
 */
@RestController
@RequestMapping("demopackage/demo")
public class DemoAct {

    /**
     * 获取列表
     * @param ticketVO 票信息
     * @param bindingResult
     * @return class TicketVO
     * @deprecated yes
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TicketVO ticketVO, BindingResult bindingResult) throws ActException{
        return null;
    }

    /**
     * 获取列表
     * @param ticketVO 票信息
     * @param bindingResult
     * @return class TicketVO
     * @throws ActException
     * @version v2
     */
    @GetMapping("v2/list")
    public Result listUp(@Validated({TicketVO.TESTDemoList.class}) TicketVO ticketVO, BindingResult bindingResult) throws ActException{
        return null;
    }

}
