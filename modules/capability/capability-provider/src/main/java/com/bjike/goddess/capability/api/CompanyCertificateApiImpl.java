package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyCertificateBO;
import com.bjike.goddess.capability.service.CompanyCertificateSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 公司荣誉证书业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-17 09:49 ]
* @Description:	[ 公司荣誉证书业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("companyCertificateApiImpl")
public class CompanyCertificateApiImpl implements CompanyCertificateAPI  {
    @Autowired
    private CompanyCertificateSer companyCertificateSer;

    @Override
    public CompanyCertificateBO addCompanyCertificate(String [] managerAuths , String companyId) throws SerException {
        return companyCertificateSer.addCompanyCertificate( managerAuths , companyId );
    }

    @Override
    public CompanyCertificateBO editCompanyCertificate(String [] managerAuths , String companyId) throws SerException {
        return companyCertificateSer.editCompanyCertificate( managerAuths, companyId );
    }

    @Override
    public void deleteCompanyCertificate(String id) throws SerException {
        companyCertificateSer.deleteCompanyCertificate(id);
    }

 }