package com.bjike.goddess.businessevaluate.action.projectcost;

import com.bjike.goddess.businessevaluate.api.AnotherCostAPI;
import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.AnotherCostDTO;
import com.bjike.goddess.businessevaluate.to.AnotherCostTO;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.businessevaluate.vo.AnotherCostVO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
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
 * 其它成本
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 01:46 ]
 * @Description: [ 其它成本 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("anothercost")
public class AnotherCostAct {

    @Autowired
    private AnotherCostAPI anotherCostAPI;
    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;


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

            Boolean isHasPermission = anotherCostAPI.guidePermission(guidePermissionTO);
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
    public Result count(AnotherCostDTO dto) throws ActException {
        try {
            Long count = anotherCostAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询其它成本
     *
     * @param id 其它成本id
     * @return class AnotherCostVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AnotherCostVO vo = BeanTransform.copyProperties(anotherCostAPI.findById(id), AnotherCostVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增其它成本
     *
     * @param to 其它成本
     * @return class AnotherCostVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) AnotherCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AnotherCostVO vo = BeanTransform.copyProperties(anotherCostAPI.addModel(to), AnotherCostVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑其它成本
     *
     * @param to 其它成本
     * @return class AnotherCostVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AnotherCostTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AnotherCostVO vo = BeanTransform.copyProperties(anotherCostAPI.editModel(to), AnotherCostVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除其它成本
     *
     * @param id 其它成本id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            anotherCostAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @return class AnotherCostVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result delete(AnotherCostDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AnotherCostVO> voList = BeanTransform.copyProperties(anotherCostAPI.pageList(dto), AnotherCostVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}