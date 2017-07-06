package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.CommissionQuotaBO;
import com.bjike.goddess.businesscommission.dto.CommissionQuotaDTO;
import com.bjike.goddess.businesscommission.to.CommissionQuotaTO;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.vo.CommissionQuotaVO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 业务提成定额表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:49 ]
 * @Description: [ 业务提成定额表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommissionQuotaAPI {

    /**
     * 业务提成定额列表总条数
     */
    default Long countCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取业务提成定额列表
     *
     * @return class CommissionQuotaBO
     */
    default CommissionQuotaBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 业务提成定额信息列表
     *
     * @return class CommissionQuotaBO
     */
    default List<CommissionQuotaBO> listCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param commissionQuotaTO 业务提成定额信息
     * @return class CommissionQuotaBO
     */
    default CommissionQuotaBO addCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param commissionQuotaTO 业务提成定额信息
     * @return class CommissionQuotaBO
     */
    default CommissionQuotaBO editCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteCommissionQuota(String id) throws SerException {
        return;
    }


    /**
     * 导入
     *
     * @param commissionQuotaTO 业务提成定额信息
     * @return class CommissionQuotaBO
     */
    default CommissionQuotaBO importExcel(List<CommissionQuotaTO> commissionQuotaTO) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(CommissionQuotaDTO dto ) throws SerException;

    /**
     * 导出Excel导入模板
     * @throws SerException
     */
    byte[] templateExport(  ) throws SerException;

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
     * 获取信息提供人
     * @return class String
     */
    List<String> listInformationProvide() throws SerException;

    /**
     * 获取业务揽接人
     * @return class String
     */
    List<String> listBusinessContracting() throws SerException;

    /**
     * 获取业务洽谈人
     * @return class String
     */
    List<String> listBusinessNegotiation() throws SerException;

    /**
     * 获取业务维护人
     * @return class String
     */
    List<String> listMaintenance() throws SerException;

    /**
     *  获得所有的地区,项目名称,实际业务提成总额
     * @return class CommissionQuotaVO
     */
    List<CommissionQuotaBO> listAreas() throws SerException;
}