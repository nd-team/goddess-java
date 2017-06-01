package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.entity.ActivityExecuteInfo;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 活动执行信息业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityExecuteInfoSerImpl extends ServiceImpl<ActivityExecuteInfo, ActivityExecuteInfoDTO> implements ActivityExecuteInfoSer {

    /**
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    public List<ActivityExecuteInfoBO> list(ActivityExecuteInfoDTO dto) throws SerException {
        List<ActivityExecuteInfo> list = super.findByPage(dto);
        List<ActivityExecuteInfoBO> listBO = BeanTransform.copyProperties(list, ActivityExecuteInfoBO.class);
        return listBO;
    }

    /**
     * 保存活动执行信息
     *
     * @param to 活动执行信息to
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ActivityExecuteInfoBO save(ActivityExecuteInfoTO to) throws SerException {
        ActivityExecuteInfo entity = BeanTransform.copyProperties(to, ActivityExecuteInfo.class, true);
        entity = super.save(entity);
        ActivityExecuteInfoBO bo = BeanTransform.copyProperties(entity, ActivityExecuteInfoBO.class);
        return bo;
    }

    /**
     * 根据id删除活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新活动执行信息
     *
     * @param to 活动执行信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ActivityExecuteInfoTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityExecuteInfo model = super.findById(to.getId());
            if (model != null) {
                updateActivityExecuteInfo(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动执行信息
     *
     * @param to 活动执行信息to
     * @param model 活动执行信息
     */
    private void updateActivityExecuteInfo(ActivityExecuteInfoTO to, ActivityExecuteInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}