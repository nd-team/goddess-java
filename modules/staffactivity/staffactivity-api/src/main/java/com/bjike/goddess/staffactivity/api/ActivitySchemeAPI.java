package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;

import java.util.List;
import java.util.Map;

/**
 * 活动方案业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ActivitySchemeAPI {

    /**
     * 根据id查询活动方案
     *
     * @param id 活动方案唯一标识
     * @return class CustomerInfoBO
     * @throws SerException
     */
    ActivitySchemeBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 活动方案dto
     * @throws SerException
     */
    Long count(ActivitySchemeDTO dto) throws SerException;

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
     * 上传文件
     *
     * @param maps 文件名，文件字节
     * @param path 上传路径
     * @throws SerException
     */
    void upload(Map<String, byte[]> maps, String path) throws SerException;

    /**
     * 运营商务部意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void yYOpinion(ActivitySchemeTO to) throws SerException;

    /**
     * 总经办意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void zjbOpinion(ActivitySchemeTO to) throws SerException;

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void ifContinueLaunch(ActivitySchemeTO to) throws SerException;

    /**
     * 运营资金评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void yYFundEvaluate(ActivitySchemeTO to) throws SerException;

    /**
     * 监督者评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void supervisorEvaluate(ActivitySchemeTO to) throws SerException;

    /**
     * 总经办评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    void zjbEvaluate(ActivitySchemeTO to) throws SerException;

    /**
     * 公司各地区活动经费汇总
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return class ActivityFundSummaryBO
     * @throws SerException
     */
    List<ActivityFundSummaryBO> activityFundSummary(String startDate, String endDate) throws SerException;
}