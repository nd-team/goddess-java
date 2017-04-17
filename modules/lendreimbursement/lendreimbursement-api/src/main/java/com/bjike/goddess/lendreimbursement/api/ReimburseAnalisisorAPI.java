package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAnalisisorBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAnalisisorDTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseAnalisisorTO;

import java.util.List;

/**
 * 报销分析人员表业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReimburseAnalisisorAPI {

    /**
     * 报销分析人员列表总条数
     */
    default Long countReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        return null;
    }

    /**
     * 报销分析人员列表
     *
     * @return class ReimburseAnalisisorBO
     */
    default List<ReimburseAnalisisorBO> listReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param reimburseAnalisisorTO 报销分析人员信息
     * @return class ReimburseAnalisisorBO
     */
    default ReimburseAnalisisorBO addReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param reimburseAnalisisorTO 报销分析人员信息
     * @return class ReimburseAnalisisorBO
     */
    default ReimburseAnalisisorBO editReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteReimburseAnalisisor(String id) throws SerException {
        return;
    }

    ;
    
    
}