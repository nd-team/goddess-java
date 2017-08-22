package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.FundEntryConfirmedAPI;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.bo.FundEntryConfirmedBO;
import com.bjike.goddess.moneyside.dto.FundEntryConfirmedDTO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.entity.FundEntryConfirmed;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.vo.FundEntryConfirmedVO;
import com.bjike.goddess.moneyside.vo.FundEntryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金进入申请已确认
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:08 ]
 * @Description: [ 资金进入申请已确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundentryconfirmed")
public class FundEntryConfirmedAction {
    @Autowired
    private FundEntryConfirmedAPI fundEntryConfirmedAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = fundEntryConfirmedAPI.guidePermission(guidePermissionTO);
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
     * 资金进入申请已确认列表总条数
     *
     * @param fundEntryConfirmedDTO 资金进入申请已确认dto
     * @des 获取所有资金进入申请已确认总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws ActException {
        try {
            Long count = fundEntryConfirmedAPI.countFundEntryConfirmed(fundEntryConfirmedDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金进入申请已确认
     *
     * @param id
     * @return class FundEntryConfirmedVO
     * @des 获取一个资金进入申请已确认
     * @version v1
     */
    @GetMapping("v1/confirmed/{id}")
    public Result confirmed(@PathVariable String id) throws ActException {
        try {
            FundEntryConfirmedBO fundEntryConfirmedBO = fundEntryConfirmedAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fundEntryConfirmedBO, FundEntryConfirmedVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 资金进入申请已确认列表
     *
     * @param fundEntryConfirmedDTO 资金进入申请已确认dto
     * @return class FundEntryConfirmedVO
     * @des 获取所有资金进入申请已确认
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FundEntryConfirmedDTO fundEntryConfirmedDTO, HttpServletRequest request) throws ActException {
        try {
            List<FundEntryConfirmedVO> fundEntryConfirmedVOS = BeanTransform.copyProperties
                    (fundEntryConfirmedAPI.findListFundEntryConfirmed(fundEntryConfirmedDTO), FundEntryConfirmedVO.class, request);
            return ActResult.initialize(fundEntryConfirmedVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除资金进入申请已确认
     *
     * @param id 用户id
     * @des 根据用户id删除资金进入申请已确认记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundEntryConfirmedAPI.removeFundEntryConfirmed(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}