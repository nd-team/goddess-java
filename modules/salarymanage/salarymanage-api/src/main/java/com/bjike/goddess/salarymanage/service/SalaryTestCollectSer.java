package com.bjike.goddess.salarymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.salarymanage.bo.AreaBO;
import com.bjike.goddess.salarymanage.bo.SalaryTestCollectBO;
import com.bjike.goddess.salarymanage.entity.SalaryTestCollect;
import com.bjike.goddess.salarymanage.dto.SalaryTestCollectDTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryTestCollectTO;

import java.util.List;

/**
* 薪资测算汇总表业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryTestCollectSer extends Ser<SalaryTestCollect, SalaryTestCollectDTO> {

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
     * 列表
     */
    List<AreaBO> pageList(SalaryTestCollectDTO dto) throws SerException;

    /**
     * 薪资标准制定
     */
    void makeSalary(SalaryTestCollectTO to) throws SerException;

    /**
     * 根据id来查询单个薪资汇总
     *
     * @param id
     * @throws SerException
     */
    SalaryTestCollectBO findOne(String id) throws SerException;
 }