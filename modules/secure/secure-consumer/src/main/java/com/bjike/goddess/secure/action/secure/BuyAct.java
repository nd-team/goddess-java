package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.BuyAPI;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.to.BuyTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.BuyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购买社保人员
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buy")
public class BuyAct {
    @Autowired
    private BuyAPI buyAPI;

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

            Boolean isHasPermission = buyAPI.guidePermission(guidePermissionTO);
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
     * 编辑
     *
     * @param to      购买社保人员信息
     * @param request 请求对象
     * @return class BuyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BuyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            BuyBO bo = buyAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BuyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 购买社保人员id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            buyAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     购买社保分页信息
     * @param request 请求对象
     * @return class BuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(BuyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BuyBO> list = buyAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, BuyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      购买社保id
     * @param request 请求对象
     * @return class BuyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/buy/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BuyBO bo = buyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BuyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyDTO dto) throws ActException {
        try {
            return ActResult.initialize(buyAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}