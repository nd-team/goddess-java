package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityCoalescAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityCoalescBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityCoalescDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityCoalescTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.vo.EquityCoalescVO;
import com.bjike.goddess.shareholdersmanage.vo.ProportioAnmountVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 股权合并
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equitycoalesc")
public class EquityCoalescAction {
    @Autowired
    private EquityCoalescAPI equityCoalescAPI;

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

            Boolean isHasPermission = equityCoalescAPI.guidePermission(guidePermissionTO);
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
     * @param equityCoalescDTO 股权合并dto
     * @des 获取所有股权合并总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EquityCoalescDTO equityCoalescDTO) throws ActException {
        try {
            Long count = equityCoalescAPI.countCoalesc(equityCoalescDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股权合并
     *
     * @param id 股权合并id
     * @return class EquityCoalescVO
     * @des 根据id获取股权合并
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            EquityCoalescVO equityCoalescVO = BeanTransform.copyProperties(
                    equityCoalescAPI.getOne(id), EquityCoalescVO.class);
            return ActResult.initialize(equityCoalescVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股权合并列表
     *
     * @param equityCoalescDTO 股权合并dto
     * @return class EquityCoalescVO
     * @des 获取所有股权合并
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findCoalesc(EquityCoalescDTO equityCoalescDTO, HttpServletRequest request) throws ActException {
        try {
            List<EquityCoalescVO> equityCoalescVOS = BeanTransform.copyProperties(
                    equityCoalescAPI.findList(equityCoalescDTO), EquityCoalescVO.class, request);
            return ActResult.initialize(equityCoalescVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股权合并
     *
     * @param equityCoalescTO 股权合并to
     * @return class EquityCoalescVO
     * @des 添加股权合并
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) EquityCoalescTO equityCoalescTO, BindingResult result) throws ActException {
        try {
            EquityCoalescBO equityCoalescBO = equityCoalescAPI.save(equityCoalescTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityCoalescBO, EquityCoalescVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股权合并
     *
     * @param equityCoalescTO 股权合并数据bo
     * @return class EquityCoalescVO
     * @des 编辑股权合并
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) EquityCoalescTO equityCoalescTO, BindingResult result) throws ActException {
        try {
            EquityCoalescBO equityCoalescBO = equityCoalescAPI.edit(equityCoalescTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityCoalescBO, EquityCoalescVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股权合并
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            equityCoalescAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据被合并方链接数据
     *
     * @param beCombined 被合并方
     * @return class ProportioAnmountVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam String beCombined) throws ActException {
        try {
            ProportioAnmountVO proportioAnmountVO = BeanTransform.copyProperties(
                    equityCoalescAPI.proAnmount(beCombined), ProportioAnmountVO.class);
            return ActResult.initialize(proportioAnmountVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}