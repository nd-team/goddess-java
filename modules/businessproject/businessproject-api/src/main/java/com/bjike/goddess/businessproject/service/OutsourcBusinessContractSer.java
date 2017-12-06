package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.OutsourcBusinessContractBO;
import com.bjike.goddess.businessproject.dto.OutsourcBusinessContractDTO;
import com.bjike.goddess.businessproject.entity.OutsourcBusinessContract;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.OutsourcBusinessContractTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;
import java.util.Set;

/**
 * 外包半外包项目合同管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OutsourcBusinessContractSer extends Ser<OutsourcBusinessContract, OutsourcBusinessContractDTO> {
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
     * 外包半外包项目合同管理列表总条数
     */
    default Long count(OutsourcBusinessContractDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取外包半外包项目合同管理
     *
     * @return class OutsourcBusinessContractBO
     */
    default OutsourcBusinessContractBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 外包半外包项目合同管理列表
     *
     * @return class OutsourcBusinessContractBO
     */
    default List<OutsourcBusinessContractBO> list(OutsourcBusinessContractDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加外包半外包项目合同管理
     *
     * @param to 外包半外包项目合同管理
     * @return class OutsourcBusinessContractBO
     */
    default OutsourcBusinessContractBO add(OutsourcBusinessContractTO to) throws SerException {
        return null;
    }
    /**
     * 编辑供应商
     *
     * @param to 外包半外包项目合同管理
     * @return class OutsourcBusinessContractBO
     */
    default OutsourcBusinessContractBO supplier(OutsourcBusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 编辑外包半外包项目合同管理
     *
     * @param to 外包半外包项目合同管理
     * @return class OutsourcBusinessContractBO
     */
    default OutsourcBusinessContractBO edit(OutsourcBusinessContractTO to) throws SerException {
        return null;
    }

    /**
     * 删除外包半外包项目合同管理
     *
     * @param id id
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 获取所有地区
     *
     * @throws SerException
     */
    default Set<String> areas() throws SerException {
        return null;
    }

    /**
     * 导入
     *
     * @param contractTOS 外包半外包项目合同管理信息
     * @return class OutsourcBusinessContractBO
     */
    default OutsourcBusinessContractBO importExcel(List<OutsourcBusinessContractTO> contractTOS) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(OutsourcBusinessContractDTO dto) throws SerException;

    /**
     * 导入Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 获取所有的供应商名称
     * lijuntao
     * @throws SerException
     */
    default List<String> findSupplierName() throws SerException {
        return null;
    }
    /**
     * 根据供应商名称获取所有的供应商编号
     * lijuntao
     * @param supplierName
     * @throws SerException
     */
    default List<String> findSupplierNum(String supplierName) throws SerException {
        return null;
    }
    /**
     * 根据供应商编号获取信息
     * lijuntao
     * @param supplierNum
     * @throws SerException
     */
    default OutsourcBusinessContract findBySupplierNum(String supplierNum) throws SerException {
        return null;
    }
}