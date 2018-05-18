package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectprocing.bo.NotificationFormulaBO;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.entity.NotificationFormula;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NotificationFormulaTO;

import java.util.List;

/**
 * 通报机制制定业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NotificationFormulaSer extends Ser<NotificationFormula, NotificationFormulaDTO> {

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
     * 通报机制制定总条数
     */
    default Long countNotifi(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        return null;
    }

    /**
     * 一个通报机制制定
     *
     * @return class NotificationFormulaBO
     */
    default NotificationFormulaBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 通报机制制定列表
     *
     * @return class NotificationFormulaBO
     */
    default List<NotificationFormulaBO> listCollectEmail(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param notificationFormulaTO 通报机制制定
     * @return class NotificationFormulaBO
     */
    default NotificationFormulaBO addCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param notificationFormulaTO 通报机制制定
     * @return class NotificationFormulaBO
     */
    default NotificationFormulaBO editCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        return null;
    }

    /**
     * 删除通报机制制定
     *
     * @param id id
     */
    default void deleteCollectEmail(String id) throws SerException {
        return;
    }


    /**
     * 冻结通报机制制定
     *
     * @param id id
     */
    default void congealCollectEmail(String id) throws SerException {
        return;
    }


    /**
     * 解冻通报机制制定
     *
     * @param id id
     */
    default void thawCollectEmail(String id) throws SerException {
        return;
    }
    /**
     * 定时发送邮件
     *
     */
    default void checkEmail( ) throws SerException {
        return;
    }
}