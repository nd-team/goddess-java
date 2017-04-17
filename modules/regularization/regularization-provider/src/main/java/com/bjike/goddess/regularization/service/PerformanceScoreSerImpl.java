package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.entity.PerformanceScore;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作表现评分业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class PerformanceScoreSerImpl extends ServiceImpl<PerformanceScore, PerformanceScoreDTO> implements PerformanceScoreSer {

    /**
     * 分页查询工作表现评分
     *
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    public List<PerformanceScoreBO> list(PerformanceScoreDTO dto) throws SerException {
        return null;
    }

    /**
     * 保存工作表现评分
     *
     * @param to 工作表现评分to
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    public PerformanceScoreBO save(PerformanceScoreTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新工作表现评分
     *
     * @param to 工作表现评分to
     * @throws SerException
     */
    @Override
    public void update(PerformanceScoreTO to) throws SerException {

    }
}