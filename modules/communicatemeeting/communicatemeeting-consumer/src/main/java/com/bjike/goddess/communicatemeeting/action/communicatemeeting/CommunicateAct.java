package com.bjike.goddess.communicatemeeting.action.communicatemeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.communicatemeeting.api.CommunicateAPI;
import com.bjike.goddess.communicatemeeting.bo.CommunicateBO;
import com.bjike.goddess.communicatemeeting.dto.CommunicateDTO;
import com.bjike.goddess.communicatemeeting.to.CommunicateTO;
import com.bjike.goddess.communicatemeeting.vo.CommunicateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 交流讨论
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-27 02:00 ]
 * @Description: [ 交流讨论 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("communicate")
public class CommunicateAct {
    @Autowired
    private CommunicateAPI communicateAPI;

    /**
     * 交流讨论列表总条数
     *
     * @param dto 交流讨论dto
     * @des 获取所有交流讨论总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(CommunicateDTO dto) throws ActException {
        try {
            Long count = communicateAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交流讨论
     *
     * @param id id
     * @return class CommunicateVO
     * @des 获取一个交流讨论
     * @version v1
     */
    @GetMapping("v1/communicate/{id}")
    public Result communicate(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CommunicateBO bo = communicateAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CommunicateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 二轮交流
     *
     * @param dto 交流讨论信息dto
     * @return class CommunicateVO
     * @version v1
     */
    @GetMapping("v1/twoRound")
    public Result twoRound(CommunicateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CommunicateVO> VOS = BeanTransform.copyProperties
                    (communicateAPI.twoRound(dto), CommunicateVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一轮交流
     *
     * @param to 交流讨论信息数据to
     * @return class CommunicateVO
     * @version v1
     */
    //  @LoginAuth
    @PostMapping("v1/oneRound")
    public Result oneRound(@Validated({ADD.class}) CommunicateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CommunicateBO bo = communicateAPI.oneRound(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CommunicateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补充一轮意见
     *
     * @param to 交流讨论信息数据to
     * @version v1
     */
    // @LoginAuth
    @PatchMapping("v1/replenish")
    public Result replenish(@Validated({EDIT.class}) CommunicateTO to, BindingResult bindingResult) throws ActException {
        try {
            communicateAPI.replenish(to);
            return new ActResult("补充成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投票
     *
     * @param id id
     * @version v1
     */
    // @LoginAuth
    @PatchMapping("v1/vote/{id}")
    public Result vote(@PathVariable String id) throws ActException {
        try {
            communicateAPI.vote(id);
            return new ActResult("投票成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看投票结果
     *
     * @param dto dto
     * @return class CommunicateVO
     * @throws SerException
     * @version v1
     */
    @GetMapping("v1/showVote")
    public Result showVote(CommunicateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<CommunicateBO> list = communicateAPI.showVote(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CommunicateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}