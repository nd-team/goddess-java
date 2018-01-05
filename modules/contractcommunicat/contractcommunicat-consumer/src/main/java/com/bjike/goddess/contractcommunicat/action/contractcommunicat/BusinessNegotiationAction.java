package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.BusinessNegotiationAPI;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商务洽谈
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessnegotiation")
public class BusinessNegotiationAction {
    @Autowired
    private BusinessNegotiationAPI businessNegotiationAPI;
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

            Boolean isHasPermission = businessNegotiationAPI.guidePermission(guidePermissionTO);
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
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessNegotiationDTO dto) throws ActException {
        try {
            Long count = businessNegotiationAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询商务洽谈
     *
     * @param id 商务洽谈id
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @GetMapping("v1/business/{id}")
    public Result business(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BusinessNegotiationVO vo = BeanTransform.copyProperties(businessNegotiationAPI.getOne(id), BusinessNegotiationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务洽谈列表
     *
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessNegotiationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessNegotiationVO> vo = BeanTransform.copyProperties(businessNegotiationAPI.list(dto), InProjectsVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class BusinessNegotiationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BusinessNegotiationTO to, BindingResult bindingResult) throws ActException {
        try {
            BusinessNegotiationVO voList = BeanTransform.copyProperties(businessNegotiationAPI.insert(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BusinessNegotiationTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(businessNegotiationAPI.edit(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目承包洽谈
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/remove")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            businessNegotiationAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}