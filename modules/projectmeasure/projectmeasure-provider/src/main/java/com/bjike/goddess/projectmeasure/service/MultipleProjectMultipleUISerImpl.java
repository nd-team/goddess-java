package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 多项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectMultipleUISerImpl extends ServiceImpl<MultipleProjectMultipleUI, MultipleProjectMultipleUIDTO> implements MultipleProjectMultipleUISer {

    /**
     * 分页查询多项目多个界面
     *
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    public List<MultipleProjectMultipleUIBO> list(MultipleProjectMultipleUIDTO dto) throws SerException {
        List<MultipleProjectMultipleUI> list = super.findByPage(dto);
        List<MultipleProjectMultipleUIBO> listBO = BeanTransform.copyProperties(list, MultipleProjectMultipleUIBO.class);
        return listBO;
    }

    /**
     * 保存多项目多个界面
     *
     * @param to 多项目多个界面to
     * @return class MultipleProjectMultipleUIBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public MultipleProjectMultipleUIBO save(MultipleProjectMultipleUITO to) throws SerException {
        MultipleProjectMultipleUI entity = BeanTransform.copyProperties(to, MultipleProjectMultipleUI.class, true);
        verify(entity);//参数校验
        entity = super.save(entity);
        MultipleProjectMultipleUIBO bo = BeanTransform.copyProperties(entity, MultipleProjectMultipleUIBO.class);
        return bo;
    }

    /**
     * 参数校验
     *
     * @param entity
     * @throws SerException
     */
    private void verify(MultipleProjectMultipleUI entity) throws SerException {
        if (entity.getWorkload() < 1) {
            throw new SerException("参数工作量workload必须是大于0的整数");
        }
    }

    /**
     * 更新多项目多个界面
     *
     * @param to 多项目多个界面to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(MultipleProjectMultipleUITO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            MultipleProjectMultipleUI model = super.findById(to.getId());
            if (model != null) {
                updateMMUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新多项目多个界面
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMMUI(MultipleProjectMultipleUITO to, MultipleProjectMultipleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}