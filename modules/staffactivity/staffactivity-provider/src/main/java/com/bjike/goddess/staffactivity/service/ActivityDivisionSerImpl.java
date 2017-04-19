package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.entity.ActivityDivision;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动分工业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityDivisionSerImpl extends ServiceImpl<ActivityDivision, ActivityDivisionDTO> implements ActivityDivisionSer {

    /**
     * 分页查询活动分工
     *
     * @param dto 活动分工dto
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    public List<ActivityDivisionBO> list(ActivityDivisionDTO dto) throws SerException {
        List<ActivityDivision> list = super.findByPage(dto);
        List<ActivityDivisionBO> boList = BeanTransform.copyProperties(list, ActivityDivisionBO.class);
        return boList;
    }

    /**
     * 保存活动分工
     *
     * @param to 活动分工to
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    @Transactional
    public ActivityDivisionBO save(ActivityDivisionTO to) throws SerException {
        ActivityDivision entity = BeanTransform.copyProperties(to, ActivityDivision.class, true);
        entity = super.save(entity);
        ActivityDivisionBO bo = BeanTransform.copyProperties(entity, ActivityDivisionBO.class);
        return bo;
    }

    /**
     * 根据id删除活动分工
     *
     * @param id 活动分工唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(ActivityDivisionTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ActivityDivision model = super.findById(to.getId());
            if (model != null) {
                updateActivityDivision(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @param model 活动分工
     */
    private void updateActivityDivision(ActivityDivisionTO to, ActivityDivision model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}