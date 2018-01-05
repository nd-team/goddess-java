package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyCertificateBO;
import com.bjike.goddess.common.api.exception.SerException;

/**
 * 公司荣誉证书业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-17 09:49 ]
 * @Description: [ 公司荣誉证书业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CompanyCertificateAPI {
    /**
     * 添加
     *
     * @param companyCertificateAuths 公司荣誉证书信息
     * @param companyId               公司id
     * @return class CompanyCertificateBO
     */
    default CompanyCertificateBO addCompanyCertificate(String[] companyCertificateAuths, String companyId) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param companyCertificateAuths 公司荣誉证书信息
     * @param companyId               公司id
     * @return class CompanyCertificateBO
     */
//    default CompanyCertificateBO editCompanyCertificate(String [] companyCertificateAuths , String companyId)) throws SerException { return null;}
    default CompanyCertificateBO editCompanyCertificate(String[] companyCertificateAuths, String companyId) throws SerException {
        return null;
    }

    /**
     * 删除公司荣誉证书
     *
     * @param id id
     */
    default void deleteCompanyCertificate(String id) throws SerException {
        return;
    }

    ;


}