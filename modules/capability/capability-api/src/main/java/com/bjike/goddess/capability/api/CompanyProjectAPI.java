package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CompanyProjectBO;
import com.bjike.goddess.common.api.exception.SerException;

/**
* 公司参与项目数业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:20 ]
* @Description:	[ 公司参与项目数业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyProjectAPI  {
    /**
     *  添加
     * @param companyProjects 公司参与项目数信息
     * @param companyId 公司id
     * @return class CompanyProjectBO
     */
    default CompanyProjectBO addCompanyProject(String [] companyProjects , String companyId) throws SerException { return null;}

    /**
     *  编辑
     * @param companyProjects 公司参与项目数信息
     * @param companyId 公司id
     * @return class CompanyProjectBO
     */
    default CompanyProjectBO editCompanyProject(String [] companyProjects , String companyId) throws SerException { return null;}

    /**
     * 删除公司参与项目数
     * @param id id
     */
    default void deleteCompanyProject(String id ) throws SerException {return;};


}