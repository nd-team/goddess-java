package com.bjike.goddess.contractware.api;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceBouncesBO;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.to.ContractManagementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;

import java.util.List;

/**
* 合同保管业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 合同保管业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractManagementAPI  {
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
    void add(ContractManagementTO contractManagementTO) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 修改
     */
    void modify(ContractManagementTO contractManagementTO) throws SerException;

    /**
     * 列表
     */
    List<ContractManagementBO> pageList(ContractManagementDTO contractManagementDTO) throws SerException;

    /**
     * 总条数
     */
    Long count(ContractManagementDTO contractManagementDTO) throws SerException;

    /**
     * 根据id查询单条数据
     */
    ContractManagementBO findOne(String id) throws SerException;

    /**
     * 冻结
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻
     */
    void breakFreeze(String id) throws SerException;

    /**
     * 查询地区-专业－派工单号－内部项目名称－派工项目名称
     */
    List<DispatchSheetBO> findInformation() throws SerException;

    /**
     * 上传附件更新是否有合同这个字段为是
     */
    void updateContract(String id) throws SerException;

    /**
     * 根据内部合同编号查询弹框信息
     */
    InvoiceBouncesBO findByNumber(String innerProjectNumber) throws SerException;

}