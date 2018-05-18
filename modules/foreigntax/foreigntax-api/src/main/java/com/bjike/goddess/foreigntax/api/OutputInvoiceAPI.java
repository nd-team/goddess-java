package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.OutputInvoiceBO;
import com.bjike.goddess.foreigntax.dto.OutputInvoiceDTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.OutputInvoiceTO;

import java.util.List;

/**
 * 销项发票业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:43 ]
 * @Description: [ 销项发票业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OutputInvoiceAPI {
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
     * 销项发票列表总条数
     */
    default Long count(OutputInvoiceDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个销项发票
     *
     * @return class OutputInvoiceBO
     */
    default OutputInvoiceBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 销项发票
     *
     * @param dto 销项发票dto
     * @return class OutputInvoiceBO
     * @throws SerException
     */
    default List<OutputInvoiceBO> list(OutputInvoiceDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加销项发票
     *
     * @param to 销项发票数据to
     * @return class OutputInvoiceBO
     * @throws SerException
     */
    default OutputInvoiceBO insert(OutputInvoiceTO to) throws SerException {
        return null;
    }

    /**
     * 编辑销项发票
     *
     * @param to 销项发票数据to
     * @return class OutputInvoiceBO
     * @throws SerException
     */
    default OutputInvoiceBO edit(OutputInvoiceTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除销项发票
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 根据月份汇总
     *
     * @param year
     * @param month
     * @return class OutputInvoiceBO
     * @throws SerException
     */
    default List<OutputInvoiceBO> collect(Integer year, Integer month) throws SerException {
        return null;
    }
}