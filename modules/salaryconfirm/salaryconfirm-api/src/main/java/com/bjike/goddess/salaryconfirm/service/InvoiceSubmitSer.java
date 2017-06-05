package com.bjike.goddess.salaryconfirm.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.entity.InvoiceSubmit;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;

import java.util.List;

/**
* 上交发票业务接口
* @Author:			[ Jason ]
* @Date:			[  2017-05-17 05:47 ]
* @Description:	[ 上交发票业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceSubmitSer extends Ser<InvoiceSubmit, InvoiceSubmitDTO> {

    /**
     * 新增上交发票
     *
     * @param to 上交发票
     * @return 上交发票
     */
    InvoiceSubmitBO insertModel(InvoiceSubmitTO to) throws SerException;

    /**
     * 编辑上交发票
     *
     * @param to 上交发票
     * @return 上交发票
     */
    InvoiceSubmitBO updateModel(InvoiceSubmitTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 上交发票结果集
     * @throws SerException
     */
    List<InvoiceSubmitBO> pageList(InvoiceSubmitDTO dto) throws SerException;

    List<InvoiceSubmitBO> findByCondition(String employeeNumber, Integer year, Integer month) throws SerException;
}