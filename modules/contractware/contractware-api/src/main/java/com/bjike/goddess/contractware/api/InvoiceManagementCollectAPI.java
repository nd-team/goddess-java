package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.InvoiceCollectBO;
import com.bjike.goddess.contractware.bo.InvoiceYearCollectBO;
import com.bjike.goddess.contractware.bo.OptionBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementCollectDTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;


/**
* 发票管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceManagementCollectAPI  {
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
    InvoiceCollectBO monthCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws SerException;

    /**
     * 年汇总
     */
    InvoiceYearCollectBO yearCollect(InvoiceManagementCollectDTO invoiceManagementCollectDTO) throws SerException;

    /**
     * 发票管理图形化
     */
    OptionBO figureShowYear(Integer yar) throws SerException;
}