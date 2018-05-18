package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.CompanyBasicInfoBO;
import com.bjike.goddess.financeinit.dto.CompanyBasicInfoDTO;
import com.bjike.goddess.financeinit.to.CompanyBasicInfoTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

import java.util.List;

/**
 * 公司基本信息业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:08 ]
 * @Description: [ 公司基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyBasicInfoAPI {
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
     * 公司基本信息列表总条数
     */
    default Long countBasicInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取公司基本信息
     *
     * @return class CompanyBasicInfoBO
     */
    default CompanyBasicInfoBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 公司基本信息列表
     *
     * @return class CompanyBasicInfoBO
     */
    default List<CompanyBasicInfoBO> listBaseInfo(CompanyBasicInfoDTO companyBasicInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param companyBasicInfoTO 公司基本信息
     * @return class CompanyBasicInfoBO
     */
    default CompanyBasicInfoBO addBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param companyBasicInfoTO 公司基本信息
     * @return class CompanyBasicInfoBO
     */
    default CompanyBasicInfoBO editBaseInfo(CompanyBasicInfoTO companyBasicInfoTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 公司基本信息id
     */
    default void deleteBaseInfo(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;
    /**
     * 获取所有的公司名称
     * @return
     * @throws SerException
     */
    default List<String> findCompanyName()throws SerException{
        return null;
    }
    /**
     * 根据公司名称获取数据
     * @return
     * @throws SerException
     */
    default CompanyBasicInfoBO findByCompanyName(String companyName)throws SerException{
        return null;
    }
}