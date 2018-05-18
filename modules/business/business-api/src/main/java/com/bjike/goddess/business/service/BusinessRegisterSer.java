package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.bo.BusinessRegisterListBO;
import com.bjike.goddess.business.bo.RegisterNaTypeCaBO;
import com.bjike.goddess.business.enums.ChangeDataName;
import com.bjike.goddess.business.excel.SonPermissionObject;
import com.bjike.goddess.business.to.BusinessRegisterExcelTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;

import java.util.List;

/**
 * 工商注册业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessRegisterSer extends Ser<BusinessRegister, BusinessRegisterDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 工商注册列表总条数
     */
    default Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return null;
    }
    /**
     * 一个工商注册
     *
     * @return class BusinessRegisterBO
     */
    default BusinessRegisterBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工商注册
     *
     * @param businessRegisterDTO 工商注册dto
     * @return class BusinessRegisterListBO
     * @throws SerException
     */
    default List<BusinessRegisterListBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 添加工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterBO
     * @throws SerException
     */
    default BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return null;
    }

    /**
     * 编辑工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterBO
     * @throws SerException
     */
    default BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工商注册
     *
     * @param id
     * @throws SerException
     */
    default void removeBusinessRegister(String id) throws SerException {

    }

    /**
     * 工商注册的注册公司的名称,注册类型,注册资本
     * lijuntao
     * @return class RegisterNaTypeCaBO
     * @throws SerException
     */
    default List<RegisterNaTypeCaBO> findRegiNaTyCa() throws SerException {
        return null;
    }

    /**
     * 工商注册的地址
     * lijuntao
     * @throws SerException
     */
    default List<String> findAddress() throws SerException {
        return null;
    }
    /**
     * 导出excel
     *
     * @return
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;


    /**
     *  导入
     * @param businessRegisterExcelTOS 工商注册to
     */
    void importExcel(List<BusinessRegisterExcelTO> businessRegisterExcelTOS) throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 获取变更前内容
     *
     * @return class BusinessRegisterListBO
     */
    default BusinessRegisterListBO findOneById(String id) throws SerException {
        return null;
    }
}