package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.AccrualAllotAPI;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import com.bjike.goddess.moneyside.vo.AccrualAllotVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权责分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accrualallot")
public class AccrualAllotAction {
    @Autowired
    private AccrualAllotAPI accrualAllotAPI;
    /**
     * 权责分配列表总条数
     *
     * @param accrualAllotDTO 权责分配dto
     * @des 获取所有权责分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccrualAllotDTO accrualAllotDTO) throws ActException {
        try {
            Long count = accrualAllotAPI.countAccrualAllot(accrualAllotDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个权责分配
     *
     * @param id
     * @des 获取一个权责分配
     * @return  class AccrualAllotVO
     * @version v1
     */
    @GetMapping("v1/accrual/{id}")
    public Result accrual(@PathVariable String id) throws ActException {
        try {
            AccrualAllotBO accrualAllotBO = accrualAllotAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(accrualAllotBO ,AccrualAllotVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 权责分配列表
     *
     * @param accrualAllotDTO 权责分配dto
     * @return class AccrualAllotVO
     * @des 获取所有权责分配
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AccrualAllotDTO accrualAllotDTO, HttpServletRequest request) throws ActException {
        try {
            List<AccrualAllotVO> accrualAllotVOS = BeanTransform.copyProperties
                    (accrualAllotAPI.findListAccrualAllot(accrualAllotDTO), AccrualAllotVO.class,request);
            return ActResult.initialize(accrualAllotVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加权责分配
     *
     * @param accrualAllotTO 权责分配数据to
     * @return class AccrualAllotVO
     * @des 添加权责分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) AccrualAllotTO accrualAllotTO, BindingResult bindingResult) throws ActException {
        try {
            AccrualAllotBO accrualAllotBO = accrualAllotAPI.insertAccrualAllot(accrualAllotTO);
            return ActResult.initialize(accrualAllotBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑权责分配
     *
     * @param accrualAllotTO 权责分配数据to
     * @return class AccrualAllotVO
     * @des 编辑权责分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AccrualAllotTO accrualAllotTO, BindingResult bindingResult) throws ActException {
        try {
            AccrualAllotBO accrualAllotBO = accrualAllotAPI.editAccrualAllot(accrualAllotTO);
            return ActResult.initialize(accrualAllotBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除权责分配
     *
     * @param id 用户id
     * @des 根据用户id删除权责分配记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            accrualAllotAPI.removeAccrualAllot(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}