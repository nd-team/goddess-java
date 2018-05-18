package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.FreezeEquityAPI;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.to.FreezeEquityTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.vo.FreezeEquityLinkDateVO;
import com.bjike.goddess.shareholdersmanage.vo.FreezeEquityVO;
import com.bjike.goddess.shareholdersmanage.vo.NewEquityLinkDateVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 冻结股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("freezeequity")
public class FreezeEquityAction {
    @Autowired
    private FreezeEquityAPI freezeEquityAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = freezeEquityAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param freezeEquityDTO 冻结股权dto
     * @des 获取所有冻结股权总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FreezeEquityDTO freezeEquityDTO) throws ActException {
        try {
            Long count = freezeEquityAPI.countFreeze(freezeEquityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个冻结股权
     *
     * @param id 冻结股权id
     * @return class FreezeEquityVO
     * @des 根据id获取冻结股权
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            FreezeEquityVO freezeEquityVO = BeanTransform.copyProperties(
                    freezeEquityAPI.getOne(id), FreezeEquityVO.class);
            return ActResult.initialize(freezeEquityVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结股权列表
     *
     * @param freezeEquityDTO 冻结股权dto
     * @return class FreezeEquityVO
     * @des 获取所有冻结股权
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(FreezeEquityDTO freezeEquityDTO, HttpServletRequest request) throws ActException {
        try {
            List<FreezeEquityVO> freezeEquityVOS = BeanTransform.copyProperties(
                    freezeEquityAPI.findList(freezeEquityDTO), FreezeEquityVO.class, request);
            return ActResult.initialize(freezeEquityVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加冻结股权
     *
     * @param freezeEquityTO 冻结股权to
     * @return class FreezeEquityVO
     * @des 添加冻结股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) FreezeEquityTO freezeEquityTO, BindingResult result) throws ActException {
        try {
            FreezeEquityBO freezeEquityBO = freezeEquityAPI.save(freezeEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(freezeEquityBO, FreezeEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑冻结股权
     *
     * @param freezeEquityTO 冻结股权数据bo
     * @return class FreezeEquityVO
     * @des 编辑冻结股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) FreezeEquityTO freezeEquityTO, BindingResult result) throws ActException {
        try {
            FreezeEquityBO freezeEquityBO = freezeEquityAPI.edit(freezeEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(freezeEquityBO, FreezeEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除质押股权
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            freezeEquityAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据股东名链接数据
     *
     * @param equityType 股权类型
     * @return class FreezeEquityLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam String equityType) throws ActException {
        try {
            FreezeEquityLinkDateVO freezeEquityLinkDateVO = BeanTransform.copyProperties(
                    freezeEquityAPI.totalNum(equityType), FreezeEquityLinkDateVO.class);
            return ActResult.initialize(freezeEquityLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}