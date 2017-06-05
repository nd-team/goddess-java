package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        List<TimeCriteriaSet> list = super.findByPage(dto);
        List<TimeCriteriaSetBO> listBO = BeanTransform.copyProperties(list, TimeCriteriaSetBO.class);
        return listBO;
    }

    /**
     * 保存时间条件设置
     *
     * @param to 时间条件设置to
     * @return class TimeCriteriaSetBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TimeCriteriaSetBO save(TimeCriteriaSetTO to) throws SerException {
        TimeCriteriaSet entity = BeanTransform.copyProperties(to, TimeCriteriaSet.class, true);
        entity = super.save(entity);
        TimeCriteriaSetBO bo = BeanTransform.copyProperties(entity, TimeCriteriaSetBO.class);
        return bo;
    }

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
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
    @Transactional(rollbackFor = {SerException.class})
    public void update(TimeCriteriaSetTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            TimeCriteriaSet model = super.findById(to.getId());
            if (model != null) {
                updateTimeCriteriaSet(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新时间条件设置
     *
     * @param to 时间条件设置to
     * @param model
     * @throws SerException
     */
    private void updateTimeCriteriaSet(TimeCriteriaSetTO to, TimeCriteriaSet model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}