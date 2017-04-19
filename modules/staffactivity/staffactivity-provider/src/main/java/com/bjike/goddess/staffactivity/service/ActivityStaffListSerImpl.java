package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;
import com.bjike.goddess.staffactivity.to.ActivityStaffListTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动人员名单业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityStaffListSerImpl extends ServiceImpl<ActivityStaffList, ActivityStaffListDTO> implements ActivityStaffListSer {

    /**
     * 分页查询活动人员名单
     *
     * @param dto 活动人员名单dto
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    public List<ActivityStaffListBO> list(ActivityStaffListDTO dto) throws SerException {
        List<ActivityStaffList> list = super.findByPage(dto);
        List<ActivityStaffListBO> listBO = BeanTransform.copyProperties(list, ActivityStaffListBO.class);
        return listBO;
    }

    /**
     * 保存活动人员名单
     *
     * @param to 人员活动名单to
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    @Transactional
    public ActivityStaffListBO save(ActivityStaffListTO to) throws SerException {
        ActivityStaffList entity = BeanTransform.copyProperties(to, ActivityStaffList.class, true);
        entity = super.save(entity);
        ActivityStaffListBO bo = BeanTransform.copyProperties(entity, ActivityStaffListBO.class);
        return bo;
    }

    /**
     * 根据id删除活动人员名单
     *
     * @param id 活动人员名单唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新活动人员名单
     *
     * @param to 更新活动人员名单to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(ActivityStaffListTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityStaffList model = super.findById(to.getId());
            if (model != null) {
                updateActivityStaffList(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动人员名单列表
     *
     * @param to 活动人员名单to
     * @param model 活动人员名单
     * @throws SerException
     */
    private void updateActivityStaffList(ActivityStaffListTO to, ActivityStaffList model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}