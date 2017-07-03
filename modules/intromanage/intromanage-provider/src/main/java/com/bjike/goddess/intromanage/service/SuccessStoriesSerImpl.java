package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.SuccessStoriesBO;
import com.bjike.goddess.intromanage.dto.SuccessStoriesDTO;
import com.bjike.goddess.intromanage.entity.SuccessStories;
import com.bjike.goddess.intromanage.to.SuccessStoriesTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 成功案例业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:49 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class SuccessStoriesSerImpl extends ServiceImpl<SuccessStories, SuccessStoriesDTO> implements SuccessStoriesSer {

    /**
     * 分页查询成功案例
     *
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    @Override
    public List<SuccessStoriesBO> list(SuccessStoriesDTO dto) throws SerException {
        List<SuccessStories> list = super.findByPage(dto);
        List<SuccessStoriesBO> listBO = BeanTransform.copyProperties(list, SuccessStoriesBO.class);
        return listBO;
    }

    /**
     * 保存成功案例
     *
     * @param to 成功案例to
     * @return class SuccessStoriesBO
     * @throws SerException
     */
    @Override
    @Transactional
    public SuccessStoriesBO save(SuccessStoriesTO to) throws SerException {
        SuccessStories entity = BeanTransform.copyProperties(to, SuccessStories.class, true);
        entity = super.save(entity);
        SuccessStoriesBO bo = BeanTransform.copyProperties(entity, SuccessStoriesBO.class);
        return bo;
    }

    /**
     * 更新成功案例
     *
     * @param to 成功案例to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(SuccessStoriesTO to) throws SerException {
        SuccessStories entity = BeanTransform.copyProperties(to, SuccessStories.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除成功案例
     *
     * @param id 成功案例唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}