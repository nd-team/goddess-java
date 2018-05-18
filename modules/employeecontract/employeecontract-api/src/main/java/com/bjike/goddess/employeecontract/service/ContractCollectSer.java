package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.ContractAreaCollectBO;
import com.bjike.goddess.employeecontract.bo.ContractCollectBO;
import com.bjike.goddess.employeecontract.bo.OptionBO;
import com.bjike.goddess.employeecontract.entity.ContractCollect;
import com.bjike.goddess.employeecontract.dto.ContractCollectDTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;

import java.util.List;

/**
* 员工合同管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-10 03:22 ]
* @Description:	[ 员工合同管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractCollectSer extends Ser<ContractCollect, ContractCollectDTO> {

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
     * 月汇总
     */
    List<ContractAreaCollectBO> monthCollect(Integer yar, Integer month) throws SerException;

    /**
     * 累计汇总
     */
    List<ContractAreaCollectBO>  allCollect(String endDate) throws SerException;


    /**
     * 图形化月汇总
     */
    OptionBO figureShowMonth(Integer year, Integer month) throws SerException;

    /**
     * 图形化累计汇总
     */
    OptionBO figureShowTotal(String endDate) throws SerException;
 }