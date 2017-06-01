package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.entity.ActivityExecuteInfo;
import com.bjike.goddess.staffactivity.service.ActivityExecuteInfoSer;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动执行信息业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activityExecuteInfoApiImpl")
public class ActivityExecuteInfoApiImpl implements ActivityExecuteInfoAPI {

    @Autowired
    private ActivityExecuteInfoSer activityExecuteInfoSer;

    /**
     * 根据id查询活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    public ActivityExecuteInfoBO findById(String id) throws SerException {
        ActivityExecuteInfo model = activityExecuteInfoSer.findById(id);
        return BeanTransform.copyProperties(model, ActivityExecuteInfoBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 活动执行信息dto
     * @throws SerException
     */
    @Override
    public Long count(ActivityExecuteInfoDTO dto) throws SerException {
        return activityExecuteInfoSer.count(dto);
    }

    /**
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    public List<ActivityExecuteInfoBO> list(ActivityExecuteInfoDTO dto) throws SerException {
        return activityExecuteInfoSer.list(dto);
    }

    /**
     * 保存活动执行信息
     *
     * @param to 活动执行信息to
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    public ActivityExecuteInfoBO save(ActivityExecuteInfoTO to) throws SerException {
        return activityExecuteInfoSer.save(to);
    }

    /**
     * 根据id删除活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activityExecuteInfoSer.remove(id);
    }

    /**
     * 更新活动执行信息
     *
     * @param to 活动执行信息to
     * @throws SerException
     */
    @Override
    public void update(ActivityExecuteInfoTO to) throws SerException {
        activityExecuteInfoSer.update(to);
    }
}