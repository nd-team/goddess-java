package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BuyTicketApplyAPI;
import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.buyticket.vo.BuyTicketApplyVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 车票购买申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyticketapply")
public class BuyTicketApplyAction {
    @Autowired
    private BuyTicketApplyAPI buyTicketApplyAPI;

    /**
     * 车票购买申请列表总条数
     *
     * @param buyTicketApplyDTO 车票购买申请dto
     * @des 获取所有车票购买申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyTicketApplyDTO buyTicketApplyDTO) throws ActException {
        try {
            Long count = buyTicketApplyAPI.countBuyTicketApply(buyTicketApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个车票购买申请
     *
     * @param id
     * @return class BuyTicketApplyVO
     * @des 获取一个车票购买申请
     * @version v1
     */
    @GetMapping("v1/apply/{id}")
    public Result apply(@PathVariable String id) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(buyTicketApplyBO, BuyTicketApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 车票购买申请列表
     *
     * @param buyTicketApplyDTO 车票购买申请dto
     * @return class BuyTicketApplyVO
     * @des 获取所有车票购买申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BuyTicketApplyDTO buyTicketApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<BuyTicketApplyVO> buyTicketApplyVOS = BeanTransform.copyProperties
                    (buyTicketApplyAPI.findListBuyTicketApply(buyTicketApplyDTO), BuyTicketApplyVO.class, request);
            return ActResult.initialize(buyTicketApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyVO
     * @des 添加车票购买申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BuyTicketApplyTO buyTicketApplyTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.insertBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyVO
     * @des 编辑车票购买申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BuyTicketApplyTO buyTicketApplyTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.editBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除车票购买申请
     *
     * @param id 用户id
     * @des 根据用户id删除车票购买申请记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            buyTicketApplyAPI.removeBuyTicketApply(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyVO
     * @des 审核车票购买申请
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(BuyTicketApplyTO buyTicketApplyTO) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.auditBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件
     *
     * @version v1
     */
    @PostMapping("v1/send")
    public Result sendBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.sendBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}