package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractTypeBO;
import com.bjike.goddess.employeecontract.dto.ContractTypeDTO;
import com.bjike.goddess.employeecontract.to.ContractTypeTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;

import java.util.List;

/**
 * 合同类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:55 ]
 * @Description: [ 合同类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractTypeAPI {

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
     * @param to 合同类型传输对象
     * @return
     * @throws SerException
     */
    default ContractTypeBO save(ContractTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 合同类型传输对象
     * @return
     * @throws SerException
     */
    default ContractTypeBO update(ContractTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 合同类型数据id
     * @return
     * @throws SerException
     */
    default ContractTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 合同类型数据id
     * @return
     * @throws SerException
     */
    default ContractTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 合同类型数据id
     * @return
     * @throws SerException
     */
    default ContractTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 查询未冻结合同类型数据
     *
     * @return
     * @throws SerException
     */
    default List<ContractTypeBO> findThaw() throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 合同类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractTypeBO> maps(ContractTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取合同类型数据
     *
     * @param id 合同类型数据id
     * @return
     * @throws SerException
     */
    default ContractTypeBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }


}