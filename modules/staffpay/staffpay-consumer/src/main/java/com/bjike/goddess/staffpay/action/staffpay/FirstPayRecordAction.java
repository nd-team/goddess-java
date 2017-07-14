package com.bjike.goddess.staffpay.action.staffpay;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.api.FirstPayRecordAPI;
import com.bjike.goddess.staffpay.api.WaitPayAPI;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.entity.WaitPay;
import com.bjike.goddess.staffpay.enums.FindType;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;
import com.bjike.goddess.staffpay.vo.FirstPayRecordVO;
import com.bjike.goddess.staffpay.vo.WaitPayVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 第一次已付款记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("firstpayrecord")
public class FirstPayRecordAction {
    @Autowired
    private FirstPayRecordAPI firstPayRecordAPI;
    @Autowired
    private WaitPayAPI waitPayAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = firstPayRecordAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 第一次已付款记录列表总条数
     *
     * @param dto 第一次已付款记录dto
     * @des 获取所有第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WaitPayDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.FIRST));
            Long count = waitPayAPI.countWaitPay(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个第一次已付款记录
     *
     * @param id
     * @return class WaitPayVO
     * @des 获取一个第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/first/{id}")
    public Result first(@PathVariable String id) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(waitPayBO, WaitPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 第一次已付款记录
     *
     * @param dto 第一次已付款记录dto
     * @return class WaitPayVO
     * @des 获取所有第一次已付款记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WaitPayDTO dto, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.FIRST));
            List<WaitPayVO> waitPayVOS = BeanTransform.copyProperties(
                    waitPayAPI.findListWaitPay(dto),WaitPayVO.class, request);
            return ActResult.initialize(waitPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除第一次已付款记录
     *
     * @param id 用户id
     * @des 根据用户id删除第一次已付款记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.removeWaitPay(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}