package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.VisitRecommSetBO;
import com.bjike.goddess.customer.dto.VisitRecommSetDTO;
import com.bjike.goddess.customer.entity.VisitRecommSet;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.VisitRecommSetTO;

import java.util.List;

/**
 * 拜访推荐设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface VisitRecommSetSer extends Ser<VisitRecommSet, VisitRecommSetDTO> {
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
     * 拜访推荐设置列表总条数
     */
    default Long countVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        return null;
    }

    /**
     * 拜访推荐设置列表
     *
     * @return class VisitRecommSetBO
     */
    default List<VisitRecommSetBO> listVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param visitRecommSetTO 拜访推荐设置
     * @return class VisitRecommSetBO
     */
    default VisitRecommSetBO addVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param visitRecommSetTO 拜访推荐设置
     * @return class VisitRecommSetBO
     */
    default VisitRecommSetBO editVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {
        return null;
    }

    /**
     * 删除拜访推荐设置
     *
     * @param id id
     */
    default void deleteVisitReco(String id) throws SerException {
        return;
    }

    ;

    /**
     * 冻结拜访推荐设置
     *
     * @param id id
     */
    default void congealVisitReco(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻拜访推荐设置
     *
     * @param id id
     */
    default void thawVisitReco(String id) throws SerException {
        return;
    }



    /**
     * 一个
     *
     * @param id id
     * @return class VisitRecommSetBO
     */
    default VisitRecommSetBO getVisitRecoById(String id) throws SerException {
        return null;
    }

    /**
     * 定时器检测每小时要更新的权重
     */
    default void checkUpdateWeightHour() throws SerException {
        return;
    }
    /**
     * 定时器检测每天要更新的权重
     */
    default void checkUpdateWeightDay() throws SerException {
        return;
    }
    /**
     * 定时器检测每周要更新的权重
     */
    default void checkUpdateWeightWeek() throws SerException {
        return;
    }
    /**
     * 定时器检测每月要更新的权重
     */
    default void checkUpdateWeightMonth() throws SerException {
        return;
    }
    /**
     * 定时器检测每小时要发送拜访课程表
     */
    default void checkSendObjectHour() throws SerException {
        return;
    }
    /**
     * 定时器检测每天要发送拜访课程表
     */
    default void checkSendObjectDay() throws SerException {
        return;
    }
    /**
     * 定时器检测每周要发送拜访课程表
     */
    default void checkSendObjectWeek() throws SerException {
        return;
    }
    /**
     * 定时器检测每月要发送拜访课程表
     */
    default void checkSendObjectMonth() throws SerException {
        return;
    }
    /**
     * 定时器检测提醒发送
     */
    default void remindSend() throws SerException{return;}
}