package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;

import java.util.List;
import java.util.Set;

/**
 * 活动方案业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivitySchemeSer extends Ser<ActivityScheme, ActivitySchemeDTO> {
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
     * 分页查询活动方案
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    List<ActivitySchemeBO> list(ActivitySchemeDTO dto) throws SerException;

    /**
     * 保存活动方案
     *
     * @param to 活动方案to
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    ActivitySchemeBO save(ActivitySchemeTO to) throws SerException;

    /**
     * 根据id删除活动方案
     *
     * @param id 活动方案唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动方案
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void update(ActivitySchemeTO to) throws SerException;

    /**
     * 运营商务部意见
     *
     * @param id        活动方案id
     * @param yYOpinion 运营商务部意见
     * @throws SerException
     */
    void yYOpinion(String id, String yYOpinion) throws SerException;

    /**
     * 总经办意见
     *
     * @param id           活动方案唯一标识
     * @param ifSchemePass 方案是否通过
     * @param zjbOpinion   总经办意见
     * @throws SerException
     */
    void zjbOpinion(String id, Boolean ifSchemePass, String zjbOpinion) throws SerException;

    /**
     * 是否持续开展
     *
     * @param id               活动方案唯一标识
     * @param ifNeedContinue   是否有必要持续开展
     * @param reasonAndOpinion 原因及意见
     * @throws SerException
     */
    void ifContinueLaunch(String id, Boolean ifNeedContinue, String reasonAndOpinion) throws SerException;

    /**
     * 运营资金评价
     *
     * @param id                    活动方案唯一标识
     * @param ifTotalOutlayRational 活动总支出是否合理
     * @param fundProposal          经费建议
     * @throws SerException
     */
    void yYFundEvaluate(String id, Boolean ifTotalOutlayRational, String fundProposal) throws SerException;

    /**
     * 监督者评价
     *
     * @param id           活动方案id
     * @param ifFlowDefect 活动流程是否存在缺陷
     * @param flowProposal 活动流程建议
     * @throws SerException
     */
    void supervisorEvaluate(String id, Boolean ifFlowDefect, String flowProposal) throws SerException;

    /**
     * 总经办评价
     *
     * @param id             活动方案唯一标识
     * @param activityEffect 活动效应
     * @param zjbEvaluate    总经办评价及建议
     * @throws SerException
     */
    void zjbEvaluate(String id, String activityEffect, String zjbEvaluate) throws SerException;

    /**
     * 公司各地区活动经费汇总
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return class ActivityFundSummaryBO
     * @throws SerException
     */
    List<ActivityFundSummaryBO> activityFundSummary(String startDate, String endDate) throws SerException;

    /**
     * 查找所有活动主题
     *
     * @return
     * @throws SerException
     */
    Set<String> allTheme() throws SerException;
}