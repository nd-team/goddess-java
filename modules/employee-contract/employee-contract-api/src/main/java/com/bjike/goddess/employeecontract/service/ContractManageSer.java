package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.*;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.entity.ContractManage;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.to.ContractInfoTO;
import com.bjike.goddess.employeecontract.to.ContractManageTO;
import com.bjike.goddess.employeecontract.to.ContractPersonalTO;

import java.util.List;

/**
 * 合同管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractManageSer extends Ser<ContractManage, ContractManageDTO> {

    /**
     * 数据录入
     *
     * @param to 合同管理传输对象
     * @return
     * @throws SerException
     */
    default ContractManageBO save(ContractManageTO to) throws SerException {
        return null;
    }

    /**
     * 修改详细信息
     *
     * @param to 合同管理传输对象
     * @return
     * @throws SerException
     */
    default ContractManageBO updateDetail(ContractManageTO to) throws SerException {
        return null;
    }

    /**
     * 修改合同信息
     *
     * @param to 合同信息传输对象
     * @return
     * @throws SerException
     */
    default ContractInfoBO updateInfo(ContractInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改员工合同信息
     *
     * @param to 人员合同信息传输对象
     * @return
     * @throws SerException
     */
    default ContractPersonalBO updatePersonal(ContractPersonalTO to) throws SerException {
        return null;
    }

    /**
     * 确认解除合同
     *
     * @param id 合同管理数据id
     * @return
     * @throws SerException
     */
    default ContractInfoBO affirm(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 合同管理数据id
     * @return
     * @throws SerException
     */
    default ContractManageBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取传输数据
     *
     * @param id 合同管理数据id
     * @return
     * @throws SerException
     */
    default ContractManageBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 人员合同信息列表
     *
     * @param dto 合同管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractPersonalBO> personalMaps(ContractManageDTO dto) throws SerException {
        return null;
    }

    /**
     * 合同信息管理列表
     *
     * @param dto 合同管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractInfoBO> infoMaps(ContractManageDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取人员合同信息列表总条数
     *
     * @return
     * @throws SerException
     */
    default Long getPersonalTotal() throws SerException {
        return null;
    }

    /**
     * 获取合同信息列表总条数
     *
     * @return
     * @throws SerException
     */
    default Long getInfoTotal() throws SerException {
        return null;
    }

    /**
     * 获取合同信息列表总条数
     *
     * @return
     * @throws SerException
     */
    default List<ContractManageChoiceBO> getChoice() throws SerException {
        return null;
    }

    /**
     * 查询未接触合同信息
     *
     * @return
     * @throws SerException
     */
    default List<ContractManageBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存合同变更信息
     *
     * @param to 合同变更详细传输对象
     * @return
     * @throws SerException
     */
    default ContractChangeBO saveChange(ContractChangeTO to) throws SerException {
        return null;
    }

}