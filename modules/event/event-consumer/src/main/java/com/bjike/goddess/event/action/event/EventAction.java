package com.bjike.goddess.event.action.event;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.bo.ContentBO;
import com.bjike.goddess.event.bo.FatherBO;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.vo.ContentVO;
import com.bjike.goddess.event.vo.FatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 事件
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("event")
public class EventAction {
    @Autowired
    private EventAPI eventAPI;

    /**
     * 事件列表
     *
     * @param dto 事件数据传输
     * @return class FatherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FatherDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FatherBO> list = eventAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FatherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 事件总条数
     *
     * @param dto 事件数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(eventAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过日历查看待办事件
     *
     * @param dto dto
     * @return class ContentVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByMonth")
    public Result findByMonth(@Validated(EventDTO.MONTH.class) EventDTO dto, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            List<ContentBO> list = eventAPI.findByMonth(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list,ContentVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}