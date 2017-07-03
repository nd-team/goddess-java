package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.MaterialClassifyYearSumAPI;
import com.bjike.goddess.materialsummary.bo.MaterialClassifyYearSumBO;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.to.MaterialClassifyYearSumTO;
import com.bjike.goddess.materialsummary.vo.MaterialClassifyYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资分类年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 10:48 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialclassifyyearsum")
public class MaterialClassifyYearSumAct {

    @Autowired
    private MaterialClassifyYearSumAPI materialClassifyYearSumAPI;

    /**
     * 根据id查询物资分类年汇总记录
     *
     * @param id 物资分类年汇总记录唯一标识
     * @return class MaterialClassifyYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialclassifyyearsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialClassifyYearSumBO bo = materialClassifyYearSumAPI.findById(id);
            MaterialClassifyYearSumVO vo = BeanTransform.copyProperties(bo, MaterialClassifyYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资分类年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialClassifyYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialClassifyYearSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 物资分类年汇总记录dto
     * @return class MaterialClassifyYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialClassifyYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MaterialClassifyYearSumBO> boList = materialClassifyYearSumAPI.list(dto);
            List<MaterialClassifyYearSumVO> voList = BeanTransform.copyProperties(boList, MaterialClassifyYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资分类年汇总记录
     *
     * @param to 物资分类年汇总记录to信息
     * @return class MaterialClassifyYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MaterialClassifyYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialClassifyYearSumBO bo = materialClassifyYearSumAPI.save(to);
            MaterialClassifyYearSumVO vo = BeanTransform.copyProperties(bo, MaterialClassifyYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资分类年汇总记录
     *
     * @param id 物资分类年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialClassifyYearSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资分类年汇总记录
     *
     * @param to 物资分类年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MaterialClassifyYearSumTO to, BindingResult result) throws ActException {
        try {
            materialClassifyYearSumAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summary")
    public Result summary(HttpServletRequest request) throws ActException {
        try {
            List<MaterialClassifyYearSumBO> listBO = materialClassifyYearSumAPI.summary();
            List<MaterialClassifyYearSumVO> listVO = BeanTransform.copyProperties(listBO, MaterialClassifyYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}