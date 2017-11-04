package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceManagementBO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.InvoiceManagementTO;

import java.util.List;

/**
* 发票管理业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceManagementAPI  {
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
     * 新增
     */
    void add(InvoiceManagementTO invoiceManagementTO) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void modify(InvoiceManagementTO invoiceManagementTO) throws SerException;

    /**
     * 列表
     */
    List<InvoiceManagementBO> pageList(InvoiceManagementDTO invoiceManagementDTO) throws SerException;

    /**
     * 总条数
     */
    Long count(InvoiceManagementDTO invoiceManagementDTO) throws SerException;

    /**
     * 根据id查询单条数据
     */
    InvoiceManagementBO findOne(String id) throws SerException;

    /**
     * 根据内部合同编号获取地区－合作单位－项目内部名称
     */
    ContractManagementBO findByNumber(String number) throws SerException;

    /**
     * 上传附件更新电子版这个字段为有
     */
    void updateElectronic(String id) throws SerException;
}