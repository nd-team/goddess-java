package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractProjectInfoAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.vo.ContractProjectInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 合同项目基本信息(临时表存放数据商务模块获取数据)
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-21 07:18 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractprojectinfo")
public class ContractProjectInfoAct {

    @Autowired
    private ContractProjectInfoAPI contractProjectInfoAPI;

    /**
     * 根据id查询合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractprojectinfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ContractProjectInfoBO bo = contractProjectInfoAPI.findById(id);
            ContractProjectInfoVO vo = BeanTransform.copyProperties(bo, ContractProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同项目基本信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ContractProjectInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = contractProjectInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 合同项目基本信息dto
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ContractProjectInfoDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ContractProjectInfoBO> boList = contractProjectInfoAPI.list(dto);
            List<ContractProjectInfoVO> voList = BeanTransform.copyProperties(boList, ContractProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同项目基本信息
     *
     * @param to 合同项目基本信息to信息
     * @return class ContractProjectInfoVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractProjectInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ContractProjectInfoBO bo = contractProjectInfoAPI.save(to);
            ContractProjectInfoVO vo = BeanTransform.copyProperties(bo, ContractProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同项目基本信息
     *
     * @param id 合同项目基本信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractProjectInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同项目基本信息
     *
     * @param to 合同项目基本信息to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractProjectInfoTO to, BindingResult result) throws ActException {
        try {
            contractProjectInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}