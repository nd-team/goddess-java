package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUI;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional(rollbackFor = {SerException.class})
    public SingleProjectMultipleUIBO save(SingleProjectMultipleUITO to) throws SerException {
        SingleProjectMultipleUI entity = BeanTransform.copyProperties(to, SingleProjectMultipleUI.class, true);
        verify(entity);//参数校验
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
    @Transactional(rollbackFor = {SerException.class})
    public void update(SingleProjectMultipleUITO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            SingleProjectMultipleUI model = super.findById(to.getId());
            if (model != null) {
                updateSMUI(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新单个项目多个界面
     *
     * @param to
     * @param model
     */
    private void updateSMUI(SingleProjectMultipleUITO to, SingleProjectMultipleUI model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 参数校验
     *
     * @param model
     */
    private void verify(SingleProjectMultipleUI model) throws SerException {

        if ((model.getWorkload() != null) && (model.getWorkload() < 0)) {
            throw new SerException("参数工作量workload必须是大于等于0的整数");
        }

    }

    /**
     * 根据id删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}