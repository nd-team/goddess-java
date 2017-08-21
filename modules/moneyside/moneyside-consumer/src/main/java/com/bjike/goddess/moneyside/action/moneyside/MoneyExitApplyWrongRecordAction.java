package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.MoneyExitApplyWrongRecordAPI;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyWrongRecordBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyWrongRecord;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.vo.MoneyExitApplyVO;
import com.bjike.goddess.moneyside.vo.MoneyExitApplyWrongRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金退出申请有误记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyexitapplywrongrecord")
public class MoneyExitApplyWrongRecordAction {
    @Autowired
    private MoneyExitApplyWrongRecordAPI moneyExitApplyWrongRecordAPI;
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

            Boolean isHasPermission = moneyExitApplyWrongRecordAPI.guidePermission(guidePermissionTO);
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
     * 资金退出申请有误记录列表总条数
     *
     * @param moneyExitApplyWrongRecordDTO 资金退出申请有误记录dto
     * @des 获取所有资金退出申请有误记录总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws ActException {
        try {
            Long count = moneyExitApplyWrongRecordAPI.countMoneyExitApplyWrongRecord(moneyExitApplyWrongRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金退出申请有误记录
     *
     * @param id
     * @return class MoneyExitApplyWrongRecordVO
     * @des 获取一个资金退出申请有误记录
     * @version v1
     */
    @GetMapping("v1/fund/{id}")
    public Result fund(@PathVariable String id) throws ActException {
        try {
            MoneyExitApplyWrongRecordBO moneyExitApplyWrongRecordBO = moneyExitApplyWrongRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(moneyExitApplyWrongRecordBO, MoneyExitApplyWrongRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金退出申请有误记录列表
     *
     * @param moneyExitApplyWrongRecordDTO 资金退出申请有误记录dto
     * @return class MoneyExitApplyWrongRecordVO
     * @des 获取所有资金退出申请有误记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<MoneyExitApplyWrongRecordVO> moneyExitApplyWrongRecordVOS = BeanTransform.copyProperties
                    (moneyExitApplyWrongRecordAPI.findListMoneyExitApplyWrongRecord(moneyExitApplyWrongRecordDTO), MoneyExitApplyWrongRecordVO.class, request);
            return ActResult.initialize(moneyExitApplyWrongRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除资金退出申请有误记录
     *
     * @param id 用户id
     * @des 根据用户id删除资金退出申请有误记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyExitApplyWrongRecordAPI.removeMoneyExitApplyWrongRecord(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}