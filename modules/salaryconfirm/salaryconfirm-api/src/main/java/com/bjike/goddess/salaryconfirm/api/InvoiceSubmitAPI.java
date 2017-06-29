package com.bjike.goddess.salaryconfirm.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salaryconfirm.bo.InvoiceSubmitBO;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.excel.SonPermissionObject;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.to.InvoiceSubmitTO;

import java.util.List;

/**
 * 上交发票业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 上交发票业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvoiceSubmitAPI {

    /**
     * 新增上交发票
     *
     * @param to 上交发票
     * @return 上交发票
     */
    InvoiceSubmitBO add(InvoiceSubmitTO to) throws SerException;

    /**
     * 编辑上交发票
     *
     * @param to 上交发票
     * @return 上交发票
     */
    InvoiceSubmitBO edit(InvoiceSubmitTO to) throws SerException;

    /**
     * 删除上交发票
     *
     * @param id 上交发票Id
     */
    void delete(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 上交发票结果集
     */
    List<InvoiceSubmitBO> pageList(InvoiceSubmitDTO dto) throws SerException;

    /**
     * 查询指定用户指定月份前的发票数据
     *
     * @param name 分页条件
     * @param year 年份
     * @param month 月份
     * @return 上交发票结果集
     */
    List<InvoiceSubmitBO> findByCondition(String name,Integer year ,Integer month) throws SerException;

    /**
     * 根据id查询上交发票
     *
     * @param id 上交发票Id
     * @return 上交发票
     */
    InvoiceSubmitBO findById(String id) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     * @throws SerException
     */
    Long count(InvoiceSubmitDTO dto) throws SerException;

    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    List<SonPermissionObject> sonPermission() throws SerException;
}