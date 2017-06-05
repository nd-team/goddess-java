package com.bjike.goddess.regularization.api;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.entity.PerformanceScore;
import com.bjike.goddess.regularization.service.PerformanceScoreSer;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作表现评分业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("performanceScoreApiImpl")
public class PerformanceScoreApiImpl implements PerformanceScoreAPI {

    @Autowired
    private PerformanceScoreSer performanceScoreSer;

    /**
     * 根据id查询工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    public PerformanceScoreBO findById(String id) throws SerException {
        PerformanceScore model = performanceScoreSer.findById(id);
        return BeanTransform.copyProperties(model, PerformanceScoreBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 工作表现评分dto
     * @throws SerException
     */
    @Override
    public Long count(PerformanceScoreDTO dto) throws SerException {
        return performanceScoreSer.count(dto);
    }

    /**
     * 分页查询工作表现评分
     *
     * @return class PerformanceScoreBO
     * @throws SerException
     */
    @Override
    public List<PerformanceScoreBO> list(PerformanceScoreDTO dto) throws SerException {
        return performanceScoreSer.list(dto);
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
        return performanceScoreSer.save(to);
    }

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        performanceScoreSer.remove(id);
    }

    /**
     * 更新工作表现评分
     *
     * @param to 工作表现评分to
     * @throws SerException
     */
    @Override
    public void update(PerformanceScoreTO to) throws SerException {
        performanceScoreSer.update(to);
    }
}