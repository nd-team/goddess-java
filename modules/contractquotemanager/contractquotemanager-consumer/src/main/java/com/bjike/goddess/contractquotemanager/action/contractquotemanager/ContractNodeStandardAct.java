package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractNodeStandardAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.vo.ContractNodeStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 合同节点标准信息
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.722 ]
 * @Description: [ 合同节点标准信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractnodestandard")
public class ContractNodeStandardAct {

    @Autowired
    private ContractNodeStandardAPI contractNodeStandardAPI;

    /**
     * 根据id查询合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/contractnodestandard/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ContractNodeStandardBO bo = contractNodeStandardAPI.findById(id);
            ContractNodeStandardVO vo = BeanTransform.copyProperties(bo, ContractNodeStandardVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 合同节点标准信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ContractNodeStandardDTO dto, BindingResult result) throws ActException {
        try {
            Long count = contractNodeStandardAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 合同节点标准信息dto
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ContractNodeStandardDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ContractNodeStandardBO> boList = contractNodeStandardAPI.list(dto);
            List<ContractNodeStandardVO> voList = BeanTransform.copyProperties(boList, ContractNodeStandardVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加合同节点标准信息
     *
     * @param to 合同节点标准信息to信息
     * @return class ContractNodeStandardVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractNodeStandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ContractNodeStandardBO bo = contractNodeStandardAPI.save(to);
            ContractNodeStandardVO vo = BeanTransform.copyProperties(bo, ContractNodeStandardVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractNodeStandardAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑合同节点标准信息
     *
     * @param to 合同节点标准信息to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractNodeStandardTO to, BindingResult result) throws ActException {
        try {
            contractNodeStandardAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合同节点标准信息
     *
     * @param to 合同节点标准信息bo
     * @throws ActException
     * @des class ContractNodeStandardVO 根据日期(date)、地区(area)、项目组(project)和节点(node)汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(ContractNodeStandardTO to) throws ActException {
        try {

            List<ContractNodeStandardVO> contractNodeStandardVOs = BeanTransform.copyProperties(
                    contractNodeStandardAPI.collect(to),
                    ContractNodeStandardVO.class, true);
            return ActResult.initialize(contractNodeStandardVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @throws ActException
     * @des 根据地区(area)、项目组(project)搜索
     * @version v1
     */
    @GetMapping("v1/search")
    public Result search(ContractNodeStandardTO to) throws ActException {
        try {
            List<ContractNodeStandardVO> contractNodeStandardVOs = BeanTransform.copyProperties(
                    contractNodeStandardAPI.searchContractNodeStandard(to),
                    ContractNodeStandardVO.class, true);
            return ActResult.initialize(contractNodeStandardVOs);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}