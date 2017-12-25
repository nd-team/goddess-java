package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.FinoddinforBO;
import com.bjike.goddess.lendreimbursement.dto.FinoddinforDTO;
import com.bjike.goddess.lendreimbursement.to.FinoddinforTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;

import java.util.List;

/**
 * 报销单号管理业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinoddinforAPI {

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
     * 报销单号列表总条数
     */
    default Long countFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        return null;
    }


    /**
     * 根据id获取报销单号
     *
     * @return class FinoddinforBO
     */
    default FinoddinforBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 报销单号列表
     *
     * @return class FinoddinforBO
     */
    default List<FinoddinforBO> listFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param finoddinforTO 报销单号信息
     * @return class FinoddinforBO
     */
    default FinoddinforBO addFinoddinfor(FinoddinforTO finoddinforTO) throws SerException {
        return null;
    }


    /**
     * 删除级别
     *
     * @param id id
     */
    default void deleteFinoddinfor(String id) throws SerException {
        return;
    }

    ;

    /**
     * 冻结报销单号
     *
     * @param id id
     */
    default void congealFinoddinfor(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻报销单号
     *
     * @param id id
     */
    default void thawFinoddinfor(String id) throws SerException {
        return;
    }

    ;

    /**
     * 获取最小报销单号
     */
    default String getMinRunNum() throws SerException {
        return null;
    }

    ;

}