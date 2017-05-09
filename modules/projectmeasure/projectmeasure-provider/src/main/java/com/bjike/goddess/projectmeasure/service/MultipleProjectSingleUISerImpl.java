package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.MultipleProjectSingleUITO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 多项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectSingleUISerImpl extends ServiceImpl<MultipleProjectSingleUI, MultipleProjectSingleUIDTO> implements MultipleProjectSingleUISer {

    /**
     * 分页查询多项目单个界面
     *
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectSingleUIBO> list(MultipleProjectSingleUIDTO dto) throws SerException {
        List<MultipleProjectSingleUI> list = super.findByPage(dto);
        List<MultipleProjectSingleUIBO> listBO = BeanTransform.copyProperties(list, MultipleProjectSingleUIBO.class);
        return listBO;
    }

    /**
     * 保存多项目单个界面
     *
     * @param to 多项目单个界面to
     * @return class MultipleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional
    public MultipleProjectSingleUIBO save(MultipleProjectSingleUITO to) throws SerException {
        MultipleProjectSingleUI entity = BeanTransform.copyProperties(to, MultipleProjectSingleUI.class, true);
        entity = super.save(entity);
        MultipleProjectSingleUIBO bo = BeanTransform.copyProperties(entity, MultipleProjectSingleUIBO.class);
        return bo;
    }

    /**
     * 更新多个项目单个界面
     *
     * @param to 多项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(MultipleProjectSingleUITO to) throws SerException {
        MultipleProjectSingleUI entity = BeanTransform.copyProperties(to, MultipleProjectSingleUI.class, true);
        super.update(entity);
    }

    /**
     * 根据id删除多项目单个界面
     *
     * @param id 多项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}