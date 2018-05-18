package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.ContractNatureBO;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.entity.ContractNature;
import com.bjike.goddess.employeecontract.to.ContractNatureTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同性质业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractNatureSer extends Ser<ContractNature, ContractNatureDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 添加
     *
     * @param to 合同性质传输对象
     * @return
     * @throws SerException
     */
    default ContractNatureBO save(ContractNatureTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 合同性质传输对象
     * @return
     * @throws SerException
     */
    default ContractNatureBO update(ContractNatureTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 合同性质数据id
     * @return
     * @throws SerException
     */
    default ContractNatureBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 合同性质数据id
     * @return
     * @throws SerException
     */
    default ContractNatureBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 合同性质数据id
     * @return
     * @throws SerException
     */
    default ContractNatureBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结合同性质数据
     *
     * @return
     * @throws SerException
     */
    default List<ContractNatureBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 合同性质数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractNatureBO> maps(ContractNatureDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取合同性质数据
     *
     * @param id 合同性质数据id
     * @return
     * @throws SerException
     */
    default ContractNatureBO getById(String id) throws SerException {
        return null;
    }
}