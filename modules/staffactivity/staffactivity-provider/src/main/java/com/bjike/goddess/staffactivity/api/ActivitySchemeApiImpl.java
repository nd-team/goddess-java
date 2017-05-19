package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;
import com.bjike.goddess.staffactivity.service.ActivitySchemeSer;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @return class CustomerInfoBO
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
     * 上传文件
     *
     * @param maps 文件名，文件字节
     * @param path 上传路径
     * @throws SerException
     */
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        activitySchemeSer.upload(maps, path);
    }

    /**
     * 运营商务部意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void yYOpinion(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.yYOpinion(to);
    }

    /**
     * 总经办意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void zjbOpinion(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.zjbOpinion(to);
    }

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void ifContinueLaunch(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.ifContinueLaunch(to);
    }

    /**
     * 运营资金评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void yYFundEvaluate(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.yYFundEvaluate(to);
    }

    /**
     * 监督者评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void supervisorEvaluate(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.supervisorEvaluate(to);
    }

    /**
     * 总经办评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    public void zjbEvaluate(ActivitySchemeTO to) throws SerException {
        activitySchemeSer.zjbEvaluate(to);
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
}