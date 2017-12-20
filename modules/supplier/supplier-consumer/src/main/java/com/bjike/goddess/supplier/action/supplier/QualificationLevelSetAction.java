package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.QualificationLevelSetAPI;
import com.bjike.goddess.supplier.bo.QualificationLevelSetBO;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.entity.QualificationLevelSet;
import com.bjike.goddess.supplier.to.QualificationLevelSetTO;
import com.bjike.goddess.supplier.vo.QualificationLevelSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资质等级设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:08 ]
 * @Description: [ 资质等级设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualificationlevelset")
public class QualificationLevelSetAction {
    @Autowired
    private QualificationLevelSetAPI qualificationLevelSetAPI;

    /**
     * 根据id查询资质等级设置
     *
     * @param id 资质等级设置唯一标识
     * @return class QualificationLevelSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/qualificationlevelset/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            QualificationLevelSetBO bo = qualificationLevelSetAPI.getOneById(id);
            QualificationLevelSetVO vo = BeanTransform.copyProperties(bo, QualificationLevelSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 资质等级设置dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated QualificationLevelSetDTO dto, BindingResult result) throws ActException {
        try {
            Long count = qualificationLevelSetAPI.countLevelSet(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 资质等级设置dto
     * @return class QualificationLevelSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated QualificationLevelSetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<QualificationLevelSetBO> boList = qualificationLevelSetAPI.listLevelSet(dto);
            List<QualificationLevelSetVO> voList = BeanTransform.copyProperties(boList, QualificationLevelSetVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加资质等级设置
     *
     * @param to 资质等级设置to信息
     * @return class QualificationLevelSetVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) QualificationLevelSetTO to, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            QualificationLevelSetBO bo = qualificationLevelSetAPI.addLevelSet(to);
            QualificationLevelSetVO vo = BeanTransform.copyProperties(bo, QualificationLevelSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资质等级设置
     *
     * @param to 资质等级设置to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) QualificationLevelSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            QualificationLevelSetBO bo = qualificationLevelSetAPI.editLevelSet(to);
            QualificationLevelSetVO vo = BeanTransform.copyProperties(bo, QualificationLevelSetVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除资质等级设置
     *
     * @param id 资质等级设置唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            qualificationLevelSetAPI.deleteLevelSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}