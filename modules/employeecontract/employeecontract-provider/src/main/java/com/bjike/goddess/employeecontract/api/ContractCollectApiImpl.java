package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractAreaCollectBO;
import com.bjike.goddess.employeecontract.bo.OptionBO;
import com.bjike.goddess.employeecontract.service.ContractCollectSer;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 员工合同管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-10 03:22 ]
* @Description:	[ 员工合同管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("contractCollectApiImpl")
public class ContractCollectApiImpl implements ContractCollectAPI  {
    @Autowired
    private ContractCollectSer contractCollectSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return contractCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ContractAreaCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return contractCollectSer.monthCollect(year,month);
    }

    @Override
    public List<ContractAreaCollectBO>  allCollect(String endDate) throws SerException {
        return contractCollectSer.allCollect(endDate);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return contractCollectSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return contractCollectSer.figureShowTotal(endDate);
    }
}