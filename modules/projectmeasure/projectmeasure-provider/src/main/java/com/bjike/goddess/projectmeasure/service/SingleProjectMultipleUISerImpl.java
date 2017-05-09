package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单个项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectMultipleUISerImpl extends ServiceImpl<SingleProjectMultipleUI, SingleProjectMultipleUIDTO> implements SingleProjectMultipleUISer {

    /**
     * 分页查询单个项目多个界面
     *
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectMultipleUIBO> list(SingleProjectMultipleUIDTO dto) throws SerException {
        List<SingleProjectMultipleUI> list = super.findByPage(dto);
        List<SingleProjectMultipleUIBO> listBO = BeanTransform.copyProperties(list, SingleProjectMultipleUI.class);
        return listBO;
    }

    /**
     * 单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @return class SingleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    @Transactional
    public SingleProjectMultipleUIBO save(SingleProjectMultipleUITO to) throws SerException {
        SingleProjectMultipleUI entity = BeanTransform.copyProperties(to, SingleProjectMultipleUI.class, true);
        entity = super.save(entity);
        SingleProjectMultipleUIBO bo = BeanTransform.copyProperties(entity, SingleProjectMultipleUIBO.class);
        return bo;
    }

    /**
     * 更新单个项目多个界面
     *
     * @param to 单个项目多个界面to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(SingleProjectMultipleUITO to) throws SerException {
        SingleProjectMultipleUI entity = BeanTransform.copyProperties(to, SingleProjectMultipleUI.class, true);
        super.update(entity);
    }

    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}