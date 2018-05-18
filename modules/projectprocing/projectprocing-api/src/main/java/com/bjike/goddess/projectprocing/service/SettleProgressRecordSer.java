package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectprocing.bo.SettleProgressRecordBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.SettleProgressRecordTO;

import java.util.List;

/**
 * 结算进度调整记录&结算问题汇总业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SettleProgressRecordSer extends Ser<SettleProgressRecord, SettleProgressRecordDTO> {
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
     * 结算进度调整记录总条数
     */
    default Long countManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取结算进度调整记录
     *
     * @return class SettleProgressRecordBO
     */
    default SettleProgressRecordBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 结算进度调整记录列表
     *
     * @return class SettleProgressRecordBO
     */
    default List<SettleProgressRecordBO> listManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        return null;
    }


    /**
     * 添加
     *
     * @param settleProgressRecordTO 结算进度调整记录
     * @return class SettleProgressRecordBO
     */
    default SettleProgressRecordBO addManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param settleProgressRecordTO 结算进度调整记录
     * @return class SettleProgressRecordBO
     */
    default SettleProgressRecordBO editManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteManage(String id) throws SerException {
        return;
    }
    /**
     * 核对分析
     *
     * @param id id
     * @param moneyModule 资金模块
     * @param moneyModuleOpinion 资金模块意见
     */
    default void checkAnalysis(String id, String moneyModule,String moneyModuleOpinion) throws SerException {
        return;
    }
    /**
     * 确认
     * @param id id
     * @param generalManager 总经理
     * @param approvalExam 审批意见
     * @param confirm 是否确认
     */
    default void confirm(String id,String generalManager, String approvalExam, Boolean confirm) throws SerException {
        return;
    }
}