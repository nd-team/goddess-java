package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyCertificateBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.capability.entity.CompanyCertificate;
import com.bjike.goddess.capability.dto.CompanyCertificateDTO;

/**
* 公司荣誉证书业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-17 09:49 ]
* @Description:	[ 公司荣誉证书业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyCertificateSer extends Ser<CompanyCertificate, CompanyCertificateDTO> {
    /**
     *  添加
     * @param managerAuths 公司荣誉证书信息
     * @param companyId 公司id
     * @return class CompanyCertificateBO
     */
    default CompanyCertificateBO addCompanyCertificate(String [] managerAuths , String companyId ) throws SerException { return null;}

    /**
     *  编辑
     * @param managerAuths 公司荣誉证书信息
     * @param companyId 公司id
     * @return class CompanyCertificateBO
     */
    default CompanyCertificateBO editCompanyCertificate(String [] managerAuths , String companyId ) throws SerException { return null;}

    /**
     * 删除公司荣誉证书
     * @param id id
     */
    default void deleteCompanyCertificate(String id ) throws SerException {return;};


}