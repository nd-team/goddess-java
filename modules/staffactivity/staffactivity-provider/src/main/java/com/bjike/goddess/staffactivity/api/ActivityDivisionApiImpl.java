package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityDivisionBO;
import com.bjike.goddess.staffactivity.dto.ActivityDivisionDTO;
import com.bjike.goddess.staffactivity.entity.ActivityDivision;
import com.bjike.goddess.staffactivity.service.ActivityDivisionSer;
import com.bjike.goddess.staffactivity.to.ActivityDivisionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动分工业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activityDivisionApiImpl")
public class ActivityDivisionApiImpl implements ActivityDivisionAPI {

    @Autowired
    private ActivityDivisionSer activityDivisionSer;

    /**
     * 根据id查询活动分工
     *
     * @param id 活动分工唯一标识
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    public ActivityDivisionBO findById(String id) throws SerException {
        ActivityDivision model = activityDivisionSer.findById(id);
        return BeanTransform.copyProperties(model, ActivityDivisionBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 活动分工dto
     * @throws SerException
     */
    @Override
    public Long count(ActivityDivisionDTO dto) throws SerException {
        return activityDivisionSer.count(dto);
    }

    /**
     * 分页查询活动分工
     *
     * @param dto 活动分工dto
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    public List<ActivityDivisionBO> list(ActivityDivisionDTO dto) throws SerException {
        return activityDivisionSer.list(dto);
    }

    /**
     * 保存活动分工
     *
     * @param to 活动分工to
     * @return class ActivityDivisionBO
     * @throws SerException
     */
    @Override
    public ActivityDivisionBO save(ActivityDivisionTO to) throws SerException {
        return activityDivisionSer.save(to);
    }

    /**
     * 根据id删除活动分工
     *
     * @param id 活动分工唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activityDivisionSer.remove(id);
    }

    /**
     * 更新活动分工
     *
     * @param to 活动分工to
     * @throws SerException
     */
    @Override
    public void update(ActivityDivisionTO to) throws SerException {
        activityDivisionSer.update(to);
    }
}