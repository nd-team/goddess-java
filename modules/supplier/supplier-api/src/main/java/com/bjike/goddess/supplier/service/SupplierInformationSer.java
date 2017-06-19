package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.SupplierInfoCollectBO;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;
import com.bjike.goddess.supplier.to.CollectTo;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SupplierInformationTO;
import com.bjike.goddess.supplier.vo.SonPermissionObject;

import java.util.List;

/**
 * 供应商基本信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.052 ]
 * @Description: [ 供应商基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInformationSer extends Ser<SupplierInformation, SupplierInformationDTO> {

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO update(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 编辑详细信息
     *
     * @param to 供应商信息传输对象
     * @return
     * @throws SerException
     */
    default SupplierInformationBO updateDetail(SupplierInformationTO to) throws SerException {
        return null;
    }

    /**
     * 查询根据供应商名称排序的供应商信息
     *
     * @return
     * @throws SerException
     */
    default List<SupplierInformationBO> findOrderName() throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 供应商基本信息数据id
     * @return
     * @throws SerException
     */
    default SupplierInformationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 供应商基本信息数据传输对象
     * @return
     * @throws SerException
     */
    default List<SupplierInformationBO> maps(SupplierInformationDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商基本信息数据
     *
     * @param id 供应商基本信息数据id
     * @return
     * @throws SerException
     */
    default SupplierInformationBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 修改营业执照附件状态
     *
     * @param id 供应商信息id
     * @throws SerException
     */
    void changeEnclosure(String id) throws SerException;

    /**
     * 汇总
     *
     * @param to 供应商汇总传输对象
     * @return
     * @throws SerException
     */
    default List<SupplierInfoCollectBO> collect(CollectTo to) throws SerException {
        return null;
    }


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 通过供应商名称查找
     *
     * @param name 供应商名称
     * @return
     * @throws SerException
     */
    List<SupplierInformationBO> findByName(String name) throws SerException;
}