package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateBO;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateSummaryBO;
import com.bjike.goddess.staffactivity.bo.EvaluateScoreSummaryBO;
import com.bjike.goddess.staffactivity.dto.ActivityEvaluateDTO;
import com.bjike.goddess.staffactivity.to.ActivityEvaluateTO;

import java.util.List;

/**
 * 活动评价业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivityEvaluateAPI {

    /**
     * 根据id查询活动评价
     *
     * @param id 活动评价唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    ActivityEvaluateBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 活动评价dto
     * @throws SerException
     */
    Long count(ActivityEvaluateDTO dto) throws SerException;

    /**
     * 分页查询活动评价
     *
     * @param dto 活动评价dto
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    List<ActivityEvaluateBO> list(ActivityEvaluateDTO dto) throws SerException;

    /**
     * 保存活动评价
     *
     * @param to 活动评价to
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    ActivityEvaluateBO save(ActivityEvaluateTO to) throws SerException;

    /**
     * 根据id删除活动评价
     *
     * @param id 活动评价唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新活动评价
     *
     * @param to 活动评价to
     * @throws SerException
     */
    void update(ActivityEvaluateTO to) throws SerException;

    /**
     * 活动评价得分汇总
     *
     * @param schemes 活动方案名称
     * @return class EvaluateScoreSummaryBO
     * @throws SerException
     */
    List<EvaluateScoreSummaryBO> evaluateScoreSummary(String[] schemes) throws SerException;

    /**
     * 各活动评估汇总
     *
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return class ActivityEvaluateSummaryBO
     * @throws SerException
     */
    List<ActivityEvaluateSummaryBO> evaluateSummary(String startDate, String endDate) throws SerException;
}