package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.TicketInfoRecordAPI;
import com.bjike.goddess.buyticket.bo.TicketInfoRecordBO;
import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.to.TicketInfoRecordTO;
import com.bjike.goddess.buyticket.vo.TicketInfoRecordVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 车票信息记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ticketinforecord")
public class TicketInfoRecordAction {
    @Autowired
    private TicketInfoRecordAPI ticketInfoRecordAPI;

    /**
     * 车票信息记录列表总条数
     *
     * @param ticketInfoRecordDTO 车票信息记录dto
     * @des 获取所有车票信息记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TicketInfoRecordDTO ticketInfoRecordDTO) throws ActException {
        try {
            Long count = ticketInfoRecordAPI.countTicketInfoRecord(ticketInfoRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个车票信息记录
     *
     * @param id
     * @return class TicketInfoRecordVO
     * @des 获取一个车票信息记录
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            TicketInfoRecordBO ticketInfoRecordBO = ticketInfoRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(ticketInfoRecordBO, TicketInfoRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 车票信息记录列表
     *
     * @param ticketInfoRecordDTO 车票信息记录dto
     * @return class TicketInfoRecordVO
     * @des 获取所有车票信息记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TicketInfoRecordDTO ticketInfoRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<TicketInfoRecordVO> ticketInfoRecordVOS = BeanTransform.copyProperties
                    (ticketInfoRecordAPI.findListTicketInfoRecord(ticketInfoRecordDTO), TicketInfoRecordVO.class, request);
            return ActResult.initialize(ticketInfoRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加车票信息记录
     *
     * @param ticketInfoRecordTO 车票信息记录数据to
     * @return class TicketInfoRecordVO
     * @des 添加车票信息记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TicketInfoRecordTO ticketInfoRecordTO, BindingResult bindingResult) throws ActException {
        try {
            TicketInfoRecordBO ticketInfoRecordBO = ticketInfoRecordAPI.insertTicketInfoRecord(ticketInfoRecordTO);
            return ActResult.initialize(ticketInfoRecordBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑车票信息记录
     *
     * @param ticketInfoRecordTO 车票信息记录数据to
     * @return class TicketInfoRecordVO
     * @des 编辑车票信息记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TicketInfoRecordTO ticketInfoRecordTO, BindingResult bindingResult) throws ActException {
        try {
            TicketInfoRecordBO ticketInfoRecordBO = ticketInfoRecordAPI.editTicketInfoRecord(ticketInfoRecordTO);
            return ActResult.initialize(ticketInfoRecordBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除车票信息记录
     *
     * @param id 用户id
     * @des 根据用户id删除车票信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            ticketInfoRecordAPI.removeTicketInfoRecord(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户车票信息记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            ticketInfoRecordAPI.congealTicketInfoRecord(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException("冻结失败：" + e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻车票信息记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            ticketInfoRecordAPI.thawTicketInfoRecord(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException("解冻失败：" + e.getMessage());
        }
    }

}