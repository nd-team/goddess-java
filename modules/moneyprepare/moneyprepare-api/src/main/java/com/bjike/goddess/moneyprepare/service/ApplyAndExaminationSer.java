package com.bjike.goddess.moneyprepare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyprepare.bo.ApplyAndExaminationBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareApplyBO;
import com.bjike.goddess.moneyprepare.bo.FundPrepareBO;
import com.bjike.goddess.moneyprepare.dto.ApplyAndExaminationDTO;
import com.bjike.goddess.moneyprepare.entity.ApplyAndExamination;
import com.bjike.goddess.moneyprepare.excel.SonPermissionObject;
import com.bjike.goddess.moneyprepare.to.ApplyAndExaminationTO;
import com.bjike.goddess.moneyprepare.to.GuidePermissionTO;

import java.util.List;

/**
 * 申请和审批业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ApplyAndExaminationSer extends Ser<ApplyAndExamination, ApplyAndExaminationDTO> {

    /**
     * 申请资金准备表列表总条数
     */
    default Long countApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取申请资金准备表列表
     *
     * @return class ApplyAndExaminationBO
     */
    default ApplyAndExaminationBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 申请资金准备表信息列表
     *
     * @return class ApplyAndExaminationBO
     */
    default List<ApplyAndExaminationBO> listApplyAndExamination(ApplyAndExaminationDTO applyAndExaminationDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param applyAndExaminationTO 申请资金准备表信息
     * @return class ApplyAndExaminationBO
     */
    default ApplyAndExaminationBO addApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param applyAndExaminationTO 申请资金准备表信息
     * @return class ApplyAndExaminationBO
     */
    default ApplyAndExaminationBO editApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteApplyAndExamination(String id) throws SerException {
        return;
    }

    ;

    /**
     * 审核
     *
     * @param applyAndExaminationTO 审核申请资金准备表信息
     * @return class ApplyAndExaminationBO
     */
    default ApplyAndExaminationBO auditApplyAndExamination(ApplyAndExaminationTO applyAndExaminationTO) throws SerException {
        return null;
    }

    /**
     * 记录
     */
    default List<FundPrepareApplyBO> record(String id) throws SerException {
        return null;
    }

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
}