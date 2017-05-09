package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 单个项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectSingleUISerImpl extends ServiceImpl<SingleProjectSingleUI, SingleProjectSingleUIDTO> implements SingleProjectSingleUISer {

    /**
     * 分页查询单个项目单个界面
     *
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    public List<SingleProjectSingleUIBO> list(SingleProjectSingleUIDTO dto) throws SerException {
        List<SingleProjectSingleUI> list = super.findByPage(dto);
        List<SingleProjectSingleUIBO> listBO = BeanTransform.copyProperties(list, SingleProjectSingleUIBO.class);
        return listBO;
    }

    /**
     * 保存单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @return class SingleProjectSingleUIBO
     * @throws SerException
     */
    @Override
    @Transactional
    public SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException {
        SingleProjectSingleUI entity = BeanTransform.copyProperties(to, SingleProjectSingleUI.class, true);
        entity = super.save(entity);
        SingleProjectSingleUIBO bo = BeanTransform.copyProperties(entity, SingleProjectSingleUIBO.class);
        return bo;
    }

    /**
     * 更新单个项目单个界面
     *
     * @param to 单个项目单个界面to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(SingleProjectSingleUITO to) throws SerException {
        SingleProjectSingleUI entity = BeanTransform.copyProperties(to, SingleProjectSingleUI.class, true);
        super.update(entity);
    }

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}