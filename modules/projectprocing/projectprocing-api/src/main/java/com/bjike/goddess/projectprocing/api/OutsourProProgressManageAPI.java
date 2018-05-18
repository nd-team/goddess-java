package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.businessproject.entity.OutsourcBusinessContract;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.OutsourProProgressManageBO;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.OutsourProProgressManageTO;

import java.util.List;

/**
 * 外包,半外包项目结算进度管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:03 ]
 * @Description: [ 外包,半外包项目结算进度管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OutsourProProgressManageAPI {

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
     * 外包,半外包项目结算进度管理总条数
     */
    default Long countOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取外包,半外包项目结算进度管理
     *
     * @return class OutsourProProgressManageBO
     */
    default OutsourProProgressManageBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 外包,半外包项目结算进度管理列表
     *
     * @return class OutsourProProgressManageBO
     */
    default List<OutsourProProgressManageBO> listOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     * @return class OutsourProProgressManageBO
     */
    default OutsourProProgressManageBO addOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     * @return class OutsourProProgressManageBO
     */
    default OutsourProProgressManageBO editOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteOuts(String id) throws SerException {
        return;
    }
    /**
     * 项目经理意见填写
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     */
    default void manageOpinion(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return;
    }
    /**
     * 每天定时检测给项目经理发送邮件提醒他审核
     *
     */
    default void remindingEmail() throws SerException {
        return;
    }
    /**
     * 回款确认
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     */
    default void receivaConfir(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return;
    }
    /**
     * 增值税发票通报
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     */
    default void noticeInvoice(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return;
    }
    /**
     * 付款
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     */
    default void payMoney(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return;
    }
    /**
     * 进度确认
     *
     * @param outsourProProgressManageTO 外包,半外包项目结算进度管理
     */
    default void scheduleConfirm(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return;
    }
}