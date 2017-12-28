package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;
import com.bjike.goddess.staffactivity.service.ActivitySchemeSer;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 活动方案业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activitySchemeApiImpl")
public class ActivitySchemeApiImpl implements ActivitySchemeAPI {

    @Autowired
    private ActivitySchemeSer activitySchemeSer;

    /**
     * 根据id查询活动方案
     *
     * @param id 活动方案唯一标识
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    public ActivitySchemeBO findById(String id) throws SerException {
        ActivityScheme model = activitySchemeSer.findById(id);
        return BeanTransform.copyProperties(model, ActivitySchemeBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 活动方案dto
     * @throws SerException
     */
    @Override
    public Long count(ActivitySchemeDTO dto) throws SerException {
        return activitySchemeSer.count(dto);
    }

    /**
     * 分页查询活动方案
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    public List<ActivitySchemeBO> list(ActivitySchemeDTO dto) throws SerException {
        return activitySchemeSer.list(dto);
    }

    /**
     * 保存活动方案
     *
     * @param to 活动方案to
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    public ActivitySchemeBO save(ActivitySchemeTO to) throws SerException {
        return activitySchemeSer.save(to);
    }

    /**
     * 根据id删除活动方案
     *
     * @param id 活动方案唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activitySchemeSer.remove(id);
    }

    /**
     * 更新活动方案
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void update(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.update(to);
    }

    /**
     * 运营商务部意见
     *
     * @param id        活动方案id
     * @param yYOpinion 运营商务部意见
     * @throws SerException
     */
    @Override
    public void yYOpinion(String id, String yYOpinion) throws SerException {
        activitySchemeSer.yYOpinion(id, yYOpinion);
    }

    /**
     * 总经办意见
     *
     * @param id           活动方案唯一标识
     * @param ifSchemePass 方案是否通过
     * @param zjbOpinion   总经办意见
     * @throws SerException
     */
    @Override
    public void zjbOpinion(String id, Boolean ifSchemePass, String zjbOpinion) throws SerException {
        activitySchemeSer.zjbOpinion(id, ifSchemePass, zjbOpinion);
    }

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws SerException
     */
    /**
     * 是否持续开展
     *
     * @param id               活动方案唯一标识
     * @param ifNeedContinue   是否有必要持续开展
     * @param reasonAndOpinion 原因及意见
     * @throws SerException
     */
    @Override
    public void ifContinueLaunch(String id, Boolean ifNeedContinue, String reasonAndOpinion) throws SerException {
        activitySchemeSer.ifContinueLaunch(id, ifNeedContinue, reasonAndOpinion);
    }

    /**
     * 运营资金评价
     *
     * @param id                    活动方案唯一标识
     * @param ifTotalOutlayRational 活动总支出是否合理
     * @param fundProposal          经费建议
     * @throws SerException
     */
    @Override
    public void yYFundEvaluate(String id, Boolean ifTotalOutlayRational, String fundProposal) throws SerException {
        activitySchemeSer.yYFundEvaluate(id, ifTotalOutlayRational, fundProposal);
    }

    /**
     * 监督者评价
     *
     * @param id           活动方案id
     * @param ifFlowDefect 活动流程是否存在缺陷
     * @param flowProposal 活动流程建议
     * @throws SerException
     */
    @Override
    public void supervisorEvaluate(String id, Boolean ifFlowDefect, String flowProposal) throws SerException {
        activitySchemeSer.supervisorEvaluate(id, ifFlowDefect, flowProposal);
    }

    /**
     * 总经办评价
     *
     * @param id             活动方案唯一标识
     * @param activityEffect 活动效应
     * @param zjbEvaluate    总经办评价及建议
     * @throws SerException
     */
    @Override
    public void zjbEvaluate(String id, String activityEffect, String zjbEvaluate) throws SerException {
        activitySchemeSer.zjbEvaluate(id, activityEffect, zjbEvaluate);
    }

    /**
     * 公司各地区活动经费汇总
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return class ActivityFundSummaryBO
     * @throws SerException
     */
    @Override
    public List<ActivityFundSummaryBO> activityFundSummary(String startDate, String endDate) throws SerException {
        return activitySchemeSer.activityFundSummary(startDate, endDate);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return activitySchemeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return activitySchemeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Set<String> allTheme() throws SerException {
        return activitySchemeSer.allTheme();
    }

    @Override
    public String findIdByTheme(String theme) throws SerException {
        return activitySchemeSer.findIdByTheme(theme);
    }

    @Override
    public Set<String> themes() throws SerException {
        return activitySchemeSer.themes();
    }
}