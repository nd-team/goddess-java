package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectSingleUI;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional(rollbackFor = {SerException.class})
    public SingleProjectSingleUIBO save(SingleProjectSingleUITO to) throws SerException {
        SingleProjectSingleUI entity = BeanTransform.copyProperties(to, SingleProjectSingleUI.class, true);
        verify(entity);//校验参数
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
    @Transactional(rollbackFor = {SerException.class})
    public void update(SingleProjectSingleUITO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            SingleProjectSingleUI model = super.findById(to.getId());
            if (model != null) {
                updateSSUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    private void updateSSUI(SingleProjectSingleUITO to, SingleProjectSingleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 参数校验
     * @param model
     */
    private void verify(SingleProjectSingleUI model) throws SerException {

        if ((model.getWorkload() != null) && (model.getWorkload() < 0)) {
            throw new SerException("参数工作量workload必须是大于等于0的整数");
        }

    }

    /**
     * 根据id删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}