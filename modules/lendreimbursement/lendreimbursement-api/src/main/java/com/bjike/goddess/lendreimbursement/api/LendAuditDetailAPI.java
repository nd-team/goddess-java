package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.LendAuditDetailDTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;

import java.util.List;

/**
 * 借款审核人员业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LendAuditDetailAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 分析情况记录列表总条数
     */
    default Long countDetail(LendAuditDetailDTO lendAuditDetailDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取分析情况记录
     *
     * @return class LendAuditDetailBO
     */
    default LendAuditDetailBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 分析情况记录
     *
     * @param lendAuditDetailDTO lendAuditDetailDTO
     * @return class LendAuditDetailBO
     */
    default List<LendAuditDetailBO> listLendAuditDetail(LendAuditDetailDTO lendAuditDetailDTO) throws SerException {
        return null;
    }


}