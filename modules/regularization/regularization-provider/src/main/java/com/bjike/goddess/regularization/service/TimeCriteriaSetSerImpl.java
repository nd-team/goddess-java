package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 时间条件设置业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class TimeCriteriaSetSerImpl extends ServiceImpl<TimeCriteriaSet, TimeCriteriaSetDTO> implements TimeCriteriaSetSer {

    /**
     * 分页查询时间条件设置
     *
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public List<TimeCriteriaSetBO> list(TimeCriteriaSetDTO dto) throws SerException {
        return null;
    }

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    public TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @throws SerException
     */
    @Override
    public void update(TimeCriteriaSetTO to) throws SerException {

    }
}