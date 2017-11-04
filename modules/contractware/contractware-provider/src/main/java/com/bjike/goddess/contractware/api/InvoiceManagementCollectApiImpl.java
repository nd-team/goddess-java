package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.InvoiceCollectBO;
import com.bjike.goddess.contractware.bo.InvoiceYearCollectBO;
import com.bjike.goddess.contractware.bo.OptionBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementCollectDTO;
import com.bjike.goddess.contractware.service.InvoiceManagementCollectSer;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 发票管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("invoiceManagementCollectApiImpl")
public class InvoiceManagementCollectApiImpl implements InvoiceManagementCollectAPI  {

    @Autowired
    private InvoiceManagementCollectSer invoiceManagementCollectSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return invoiceManagementCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return invoiceManagementCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public InvoiceCollectBO monthCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws SerException {
        return invoiceManagementCollectSer.monthCollect(invoiceManagementCollectDTO);
    }

    @Override
    public InvoiceYearCollectBO yearCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws SerException {
        return invoiceManagementCollectSer.yearCollect(invoiceManagementCollectDTO);
    }

    @Override
    public OptionBO figureShowYear(Integer yar) throws SerException {
        return invoiceManagementCollectSer.figureShowYear(yar);
    }
}