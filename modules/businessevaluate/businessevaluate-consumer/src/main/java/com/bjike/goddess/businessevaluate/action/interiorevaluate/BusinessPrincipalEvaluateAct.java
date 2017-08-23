package com.bjike.goddess.businessevaluate.action.interiorevaluate;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.api.interiorevaluate.BusinessPrincipalEvaluateAPI;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.BusinessPrincipalEvaluateTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.businessevaluate.vo.interiorevaluate.BusinessPrincipalEvaluateVO;
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
 * 商务负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 11:34 ]
 * @Description: [ 商务负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("business")
public class BusinessPrincipalEvaluateAct {

    @Autowired
    private BusinessPrincipalEvaluateAPI principalEvaluateAPI;

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

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
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessPrincipalEvaluateDTO dto) throws ActException {
        try {
            Long count = principalEvaluateAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询商务负责人评价
     *
     * @param id 商务负责人评价id
     * @return class BusinessPrincipalEvaluateVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BusinessPrincipalEvaluateVO vo = BeanTransform.copyProperties(principalEvaluateAPI.findById(id), BusinessPrincipalEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增商务负责人评价
     *
     * @param to 商务负责人评价
     * @return class BusinessPrincipalEvaluateVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BusinessPrincipalEvaluateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            BusinessPrincipalEvaluateVO vo = BeanTransform.copyProperties(principalEvaluateAPI.addModel(to), BusinessPrincipalEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务负责人评价
     *
     * @param to 商务负责人评价
     * @return class BusinessPrincipalEvaluateVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BusinessPrincipalEvaluateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            BusinessPrincipalEvaluateVO vo = BeanTransform.copyProperties(principalEvaluateAPI.editModel(to), BusinessPrincipalEvaluateVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商务负责人评价
     *
     * @param id 商务负责人评价ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            principalEvaluateAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询商务负责人评价
     *
     * @param dto 分页条件
     * @return class BusinessPrincipalEvaluateVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(BusinessPrincipalEvaluateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessPrincipalEvaluateVO> voList = BeanTransform.copyProperties(principalEvaluateAPI.pageList(dto), BusinessPrincipalEvaluateVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}