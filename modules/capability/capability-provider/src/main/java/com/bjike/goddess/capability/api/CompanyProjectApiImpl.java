package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyProjectBO;
import com.bjike.goddess.capability.service.CompanyProjectSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 公司参与项目数业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:20 ]
* @Description:	[ 公司参与项目数业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("companyProjectApiImpl")
public class CompanyProjectApiImpl implements CompanyProjectAPI  {

    @Autowired
    private CompanyProjectSer companyProjectSer;

    @Override
    public CompanyProjectBO addCompanyProject(String [] companyProjects , String companyId) throws SerException {
        return companyProjectSer.addCompanyProject( companyProjects , companyId );
    }

    @Override
    public CompanyProjectBO editCompanyProject(String [] companyProjects , String companyId) throws SerException {
        return companyProjectSer.editCompanyProject( companyProjects, companyId );
    }

    @Override
    public void deleteCompanyProject(String id) throws SerException {
        companyProjectSer.deleteCompanyProject(id);
    }
}