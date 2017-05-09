package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.to.EnterpriseQualificationTO;

import java.util.List;

/**
 * 企业资质业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.259 ]
 * @Description: [ 企业资质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EnterpriseQualificationAPI {


    /**
     * 根据供应商基本信息ID查询企业资质
     *
     * @param info_id 供应商基本信息ID
     * @return
     * @throws SerException
     */
    default List<EnterpriseQualificationBO> findByInformation(String info_id) throws SerException {
        return null;
    }

    /**
     * 保存供应商企业资质数据
     *
     * @param to 供应商企业资质传输对象
     * @return
     * @throws SerException
     */
    default EnterpriseQualificationBO save(EnterpriseQualificationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商企业资质数据
     *
     * @param to 供应商企业资质传输对象
     * @return
     * @throws SerException
     */
    default EnterpriseQualificationBO update(EnterpriseQualificationTO to) throws SerException {
        return null;
    }

    /**
     * 删除供应商企业资质数据
     *
     * @param id 供应商企业资质id
     * @return
     * @throws SerException
     */
    default EnterpriseQualificationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商企业资质数据
     *
     * @param id 供应商企业资质数据id
     * @return
     * @throws SerException
     */
    default EnterpriseQualificationBO getById(String id) throws SerException {
        return null;
    }
}