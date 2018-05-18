package com.bjike.goddess.competitorsmanagement.action.competitorsmanagement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.competitorsmanagement.api.OrganizationSDAPI;
import com.bjike.goddess.competitorsmanagement.to.OrganizationSDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织结构详情
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-14 02:33 ]
 * @Description: [ 组织结构详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("organizationsd")
public class OrganizationSDAction {
    @Autowired
    private OrganizationSDAPI organizationSDAPI;

    @PutMapping("v1/update")
    public Result upDate(OrganizationSDTO sdto) throws SerException {
        organizationSDAPI.upDate(sdto);
        return new ActResult("success");
    }
}