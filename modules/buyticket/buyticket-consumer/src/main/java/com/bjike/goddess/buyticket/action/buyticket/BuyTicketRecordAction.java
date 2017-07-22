package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BuyTicketRecordAPI;
import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.buyticket.vo.BuyTicketRecordVO;
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
 * 车票购买记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyticketrecord")
public class BuyTicketRecordAction {

    @Autowired
    private BuyTicketRecordAPI buyTicketRecordAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(BuyGuidePermissionTO.TestAdd.class) BuyGuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = buyTicketRecordAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 车票购买记录列表总条数
     *
     * @param buyTicketRecordDTO 车票购买记录dto
     * @des 获取所有车票购买记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyTicketRecordDTO buyTicketRecordDTO) throws ActException {
        try {
            Long count = buyTicketRecordAPI.countBuyTicketRecord(buyTicketRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个车票购买记录
     *
     * @param id
     * @return class BuyTicketRecordVO
     * @des 获取一个车票购买记录
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            BuyTicketRecordBO buyTicketRecordBO = buyTicketRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(buyTicketRecordBO, BuyTicketRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 车票购买记录列表
     *
     * @param buyTicketRecordDTO 车票购买记录dto
     * @return class BuyTicketRecordVO
     * @des 获取所有车票购买记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BuyTicketRecordDTO buyTicketRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<BuyTicketRecordVO> buyTicketRecordVOS = BeanTransform.copyProperties
                    (buyTicketRecordAPI.findListBuyTicketRecord(buyTicketRecordDTO), BuyTicketRecordVO.class, request);
            return ActResult.initialize(buyTicketRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑车票购买记录
     *
     * @param buyTicketRecordTO 车票购买记录数据to
     * @return class BuyTicketRecordVO
     * @des 编辑车票购买记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BuyTicketRecordTO buyTicketRecordTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketRecordBO buyTicketRecordBO = buyTicketRecordAPI.editBuyTicketRecord(buyTicketRecordTO);
            return ActResult.initialize(buyTicketRecordBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除车票购买记录
     *
     * @param id 用户id
     * @des 根据用户id删除车票购买记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            buyTicketRecordAPI.removeBuyTicketRecord(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}