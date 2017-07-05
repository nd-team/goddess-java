package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.excele.SonPermissionObject;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 公司能力展示业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyCapabilityAPI {

    /**
     * 总条数
     */
    default Long counts(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 一个个公司能力
     *
     * @return class CompanyCapabilityBO
     */
    default CompanyCapabilityBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 公司能力展示列表
     *
     * @return class CompanyCapabilityBO
     */
    default List<CompanyCapabilityBO> listCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param companyCapabilityTO 公司能力展示信息
     * @return class CompanyCapabilityBO
     */
    default CompanyCapabilityBO addCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param companyCapabilityTO 公司能力展示信息
     * @return class CompanyCapabilityBO
     */
    default CompanyCapabilityBO editCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        return null;
    }

    /**
     * 删除级别
     *
     * @param id id
     */
    default void deleteCompanyCapability(String id) throws SerException {
        return;
    }

    ;

    /**
     * 搜索
     *
     * @return class CompanyCapabilityBO
     */
    default List<CompanyCapabilityBO> listCompanyCapabilityByName(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        return null;
    }

    /**
     * 查找所有公司名
     */
    default List<String> listAllCompanyName() throws SerException {
        return null;
    }


    /**
     * 导出Excel
     *
     * @param companyName 公司名称
     * @throws SerException
     */
    byte[] exportExcel(String companyName) throws SerException;

    /**
     * 下拉导航权限
     *
     * @throws SerException
     * @version v1
     */
    List<SonPermissionObject> sonPermission() throws SerException;

    /**
     * 功能导航权限
     * @throws SerException
     * @version v1
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;

    /**
     * 导入
     *
     * @param companyCapabilityTO 公司能力
     * @return class SiginManageBO
     */
    default CompanyCapabilityBO importExcel(List<CompanyCapabilityTO> companyCapabilityTO) throws SerException {
        return null;
    }
}