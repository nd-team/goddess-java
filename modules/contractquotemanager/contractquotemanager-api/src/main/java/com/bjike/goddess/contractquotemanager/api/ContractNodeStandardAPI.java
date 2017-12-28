package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ColationBO;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.to.FilterTO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同节点标准信息业务接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.717 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractNodeStandardAPI {

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
     * 根据id查询合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    ContractNodeStandardBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 合同节点标准信息dto
     * @throws SerException
     */
    Long count(ContractNodeStandardDTO dto) throws SerException;

    /**
     * 添加合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    default ContractNodeStandardBO save(ContractNodeStandardTO to) throws SerException {
        return null;
    }

    /**
     * 分页查询合同节点标准信息
     *
     * @param dto 合同节点标准信息dto
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    default List<ContractNodeStandardBO> list(ContractNodeStandardDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @throws SerException
     */
    default void update(ContractNodeStandardTO to) throws SerException {

    }

    /**
     * 根据id删除合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 汇总合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    default List<ContractNodeStandardBO> collect(ContractNodeStandardTO to) throws SerException {
        return null;
    }

    /**
     * 搜索合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return
     * @throws SerException
     */
    default List<ContractNodeStandardBO> searchContractNodeStandard(ContractNodeStandardTO to) throws SerException {
        return null;
    }

    /**
     * 获取类别
     *
     * @return
     * @throws SerException
     */
    default List<ColationBO> findType() throws SerException {
        return null;
    }

    /**
     * 获取节点
     *
     * @return
     * @throws SerException
     */
    default List<ColationBO> findNode() throws SerException {
        return null;
    }

    /**
     * 根据查询条件获取合同节点标准数据
     *
     * @param to 查询数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractNodeStandardBO> findByTo(FilterTO to) throws SerException {
        return null;
    }

}