package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.PersonalTasksSummBO;
import com.bjike.goddess.projectprocing.bo.SettleWorkProgreManageBO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.excel.SonPermissionObject;
import com.bjike.goddess.projectprocing.to.CompletionStatusTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;

import java.util.List;

/**
 * 结算工作进度管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SettleWorkProgreManageAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 结算工作进度管理总条数
     */
    default Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取结算工作进度管理
     *
     * @return class SettleWorkProgreManageBO
     */
    default SettleWorkProgreManageBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 结算工作进度管理列表
     *
     * @return class SettleWorkProgreManageBO
     */
    default List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param settleWorkProgreManageTO 结算工作进度管理
     * @return class SettleWorkProgreManageBO
     */
    default SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
        return null;
    }

    /**
     * 重新分配负责人
     *
     * @param responsible 责任人
     * @param id id
     */
    default void redistribution(String id,String responsible) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSettleWork(String id) throws SerException {
        return;
    }
    /**
     * 根据外包单位筛选列表
     *
     * @return class SettleWorkProgreManageBO
     */
    default List<SettleWorkProgreManageBO> listWorkByOutUnit(String outUnit) throws SerException {
        return null;
    }
    /**
     * 填写完成情况
     *
     * @param completionStatusTO 填写任务情况to
     */
    default void fullFinishStatus(CompletionStatusTO completionStatusTO) throws SerException {
        return;
    }
    /**
     * 结算进度日汇总
     *
     * @return class PersonalTasksSummBO
     */
    default PersonalTasksSummBO personalSummDay(String summDate) throws SerException {
        return null;
    }
    /**
     * 结算进度周汇总
     *
     * @return class PersonalTasksSummBO
     */
    default PersonalTasksSummBO personalSummWeek(Integer year,Integer month,Integer week) throws SerException {
        return null;
    }
    /**
     * 结算进度月汇总
     *
     * @return class PersonalTasksSummBO
     */
    default PersonalTasksSummBO personalSummMonth(Integer year,Integer month) throws SerException {
        return null;
    }
}