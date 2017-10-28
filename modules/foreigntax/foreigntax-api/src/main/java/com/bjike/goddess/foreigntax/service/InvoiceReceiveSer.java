package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.foreigntax.bo.InvoiceReceiveBO;
import com.bjike.goddess.foreigntax.dto.InvoiceReceiveDTO;
import com.bjike.goddess.foreigntax.entity.InvoiceReceive;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceReceiveTO;

import java.util.List;

/**
 * 发票领用业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvoiceReceiveSer extends Ser<InvoiceReceive, InvoiceReceiveDTO> {
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
     * 发票领用列表总条数
     */
    default Long count(InvoiceReceiveDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个发票领用
     *
     * @return class InvoiceReceiveBO
     */
    default InvoiceReceiveBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 发票领用
     *
     * @param dto 发票领用dto
     * @return class InvoiceReceiveBO
     * @throws SerException
     */
    default List<InvoiceReceiveBO> list(InvoiceReceiveDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加发票领用
     *
     * @param to 发票领用数据to
     * @return class InvoiceReceiveBO
     * @throws SerException
     */
    default InvoiceReceiveBO insert(InvoiceReceiveTO to) throws SerException {
        return null;
    }

    /**
     * 编辑发票领用
     *
     * @param to 发票领用数据to
     * @return class InvoiceReceiveBO
     * @throws SerException
     */
    default InvoiceReceiveBO edit(InvoiceReceiveTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除发票领用
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}