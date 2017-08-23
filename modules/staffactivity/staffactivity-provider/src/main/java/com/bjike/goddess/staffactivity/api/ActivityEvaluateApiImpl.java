package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateBO;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateSummaryBO;
import com.bjike.goddess.staffactivity.bo.EvaluateScoreSummaryBO;
import com.bjike.goddess.staffactivity.dto.ActivityEvaluateDTO;
import com.bjike.goddess.staffactivity.entity.ActivityEvaluate;
import com.bjike.goddess.staffactivity.service.ActivityEvaluateSer;
import com.bjike.goddess.staffactivity.to.ActivityEvaluateTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 活动评价业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activityEvaluateApiImpl")
public class ActivityEvaluateApiImpl implements ActivityEvaluateAPI {

    @Autowired
    private ActivityEvaluateSer activityEvaluateSer;

    /**
     * 根据id查询活动评价
     *
     * @param id 活动评价唯一标识
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    @Override
    public ActivityEvaluateBO findById(String id) throws SerException {
        ActivityEvaluate model = activityEvaluateSer.findById(id);
        return BeanTransform.copyProperties(model, ActivityEvaluateBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 活动评价dto
     * @throws SerException
     */
    @Override
    public Long count(ActivityEvaluateDTO dto) throws SerException {
        return activityEvaluateSer.count(dto);
    }

    /**
     * 分页查询活动评价
     *
     * @param dto 活动评价dto
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    @Override
    public List<ActivityEvaluateBO> list(ActivityEvaluateDTO dto) throws SerException {
        return activityEvaluateSer.list(dto);
    }

    /**
     * 保存活动评价
     *
     * @param to 活动评价to
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    @Override
    public ActivityEvaluateBO save(ActivityEvaluateTO to) throws SerException {
        return activityEvaluateSer.save(to);
    }

    /**
     * 根据id删除活动评价
     *
     * @param id 活动评价唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activityEvaluateSer.remove(id);
    }

    /**
     * 更新活动评价
     *
     * @param to 活动评价to
     * @throws SerException
     */
    @Override
    public void update(ActivityEvaluateTO to) throws SerException {
        activityEvaluateSer.update(to);
    }

    @Override
    public List<EvaluateScoreSummaryBO> evaluateScoreSummary(ActivityEvaluateDTO dto) throws SerException {
        return activityEvaluateSer.evaluateScoreSummary(dto);
    }

    /**
     * 各活动评估汇总
     *
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return class ActivityEvaluateSummaryBO
     * @throws SerException
     */
    @Override
    public List<ActivityEvaluateSummaryBO> evaluateSummary(String startDate, String endDate) throws SerException {
        return activityEvaluateSer.evaluateSummary(startDate, endDate);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return activityEvaluateSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return activityEvaluateSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Set<String> allActivityScheme() throws SerException {
        return activityEvaluateSer.allActivityScheme();
    }
}