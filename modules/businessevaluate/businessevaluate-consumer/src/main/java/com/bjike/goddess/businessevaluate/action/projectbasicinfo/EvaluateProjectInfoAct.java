package com.bjike.goddess.businessevaluate.action.projectbasicinfo;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.EvaluateProjectInfoDTO;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
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
 * 项目基本信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("baseinfo")
public class EvaluateProjectInfoAct {

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;


    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EvaluateProjectInfoDTO dto) throws ActException {
        try {
            Long count = evaluateProjectInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目基本信息
     *
     * @param id 项目基本信息id
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            EvaluateProjectInfoVO vo = BeanTransform.copyProperties(evaluateProjectInfoAPI.findById(id), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    /**
     * 新增商务评估项目基本信息
     *
     * @param to 商务评估项目基本信息
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) EvaluateProjectInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EvaluateProjectInfoVO vo = BeanTransform.copyProperties(evaluateProjectInfoAPI.addModel(to), EvaluateProjectInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务评估项目基本信息
     *
     * @param to 商务评估项目基本信息
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) EvaluateProjectInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            EvaluateProjectInfoVO vo = BeanTransform.copyProperties(evaluateProjectInfoAPI.editModel(to), EvaluateProjectInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商务评估项目基本信息
     *
     * @param id 商务评估项目基本信息ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            evaluateProjectInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(EvaluateProjectInfoDTO dto) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.pageList(dto), EvaluateProjectInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}