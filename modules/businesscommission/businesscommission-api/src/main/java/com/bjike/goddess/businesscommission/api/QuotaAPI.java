package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.QuotaBO;
import com.bjike.goddess.businesscommission.bo.QuotaCollectBO;
import com.bjike.goddess.businesscommission.dto.QuotaDTO;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.QuotaTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 业务提成权重分配表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QuotaAPI {

    /**
     * 添加项目业务提成权重分配
     *
     * @param to
     * @throws SerException
     */
    default void addQuota(QuotaTO to) throws SerException {
        return;
    }

    /**
     * 编辑项目业务提成权重分配
     *
     * @param to
     * @throws SerException
     */
    default void editQuota(QuotaTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void deleteQuota(String id) throws SerException {
        return;
    }

    /**
     * 列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long countQuota(QuotaDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个业务提成权重分配
     *
     * @param id
     * @return
     * @throws SerException
     */
    default QuotaBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 项目业务提成权重分配列表
     *
     * @param quotaDTO
     * @return
     * @throws SerException
     */
    default List<QuotaBO> listQuota(QuotaDTO quotaDTO) throws SerException {
        return null;
    }

    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    default byte[] exportExcel(QuotaDTO dto) throws SerException {
        return null;
    }

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
     * 业务提成管理日汇总
     *
     * @param day
     * @return
     * @throws SerException
     */
    default List<QuotaCollectBO> dayCollect(String day) throws SerException {
        return null;
    }

    /**
     * 业务提成管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default List<QuotaCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 业务提成管理月汇总
     *
     * @param month
     * @return
     * @throws SerException
     */
    default List<QuotaCollectBO> monthCollect(String month) throws SerException {
        return null;
    }

    /**
     * 业务提成管理累计汇总
     *
     * @return
     * @throws SerException
     */
    default List<QuotaCollectBO> totalCollect() throws SerException {
        return null;
    }
}