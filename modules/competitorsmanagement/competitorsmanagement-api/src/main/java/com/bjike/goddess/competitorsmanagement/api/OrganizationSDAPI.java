package com.bjike.goddess.competitorsmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitorsmanagement.to.OrganizationSDTO;

/**
 * 组织结构详情业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-14 02:33 ]
 * @Description: [ 组织结构详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OrganizationSDAPI {

    void upDate(OrganizationSDTO sdto) throws SerException;

}