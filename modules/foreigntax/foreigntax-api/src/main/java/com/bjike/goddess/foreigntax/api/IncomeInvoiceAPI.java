package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.CompareCollectBO;
import com.bjike.goddess.foreigntax.bo.IncomeInvoiceBO;
import com.bjike.goddess.foreigntax.dto.IncomeCollectDTO;
import com.bjike.goddess.foreigntax.dto.IncomeInvoiceDTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.IncomeInvoiceTO;

import java.util.List;

/**
 * 进项发票业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface IncomeInvoiceAPI {

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
     * 进项发票列表总条数
     */
    default Long count(IncomeInvoiceDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个进项发票
     *
     * @return class IncomeInvoiceBO
     */
    default IncomeInvoiceBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 进项发票
     *
     * @param dto 进项发票dto
     * @return class IncomeInvoiceBO
     * @throws SerException
     */
    default List<IncomeInvoiceBO> list(IncomeInvoiceDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加进项发票
     *
     * @param to 进项发票数据to
     * @return class IncomeInvoiceBO
     * @throws SerException
     */
    default IncomeInvoiceBO insert(IncomeInvoiceTO to) throws SerException {
        return null;
    }

    /**
     * 编辑进项发票
     *
     * @param to 进项发票数据to
     * @return class IncomeInvoiceBO
     * @throws SerException
     */
    default IncomeInvoiceBO edit(IncomeInvoiceTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除进项发票
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 根据开始时间结束时间汇总
     *
     * @param dto
     * @return class IncomeInvoiceBO
     * @throws SerException
     */
    default List<IncomeInvoiceBO> collect(IncomeCollectDTO dto) throws SerException {
        return null;
    }
    /**
     * 根据开始时间结束时间勾选汇总
     *
     * @param dto
     * @return class IncomeInvoiceBO
     * @throws SerException
     */
    default List<IncomeInvoiceBO> checkCollect(IncomeInvoiceDTO dto) throws SerException {
        return null;
    }
    /**
     * 对比汇总
     *
     * @param dto
     * @return class CompareCollectBO
     * @throws SerException
     */
    default List<CompareCollectBO> compareCollect(IncomeCollectDTO dto) throws SerException {
        return null;
    }
}