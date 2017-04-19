package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.service.ActivityStaffListSer;
import com.bjike.goddess.staffactivity.to.ActivityStaffListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动人员名单业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 11:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activityStaffListApiImpl")
public class ActivityStaffListApiImpl implements ActivityStaffListAPI {

    @Autowired
    private ActivityStaffListSer activityStaffListSer;

    /**
     * 分页查询活动人员名单
     *
     * @param dto 活动人员名单dto
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    public List<ActivityStaffListBO> list(ActivityStaffListDTO dto) throws SerException {
        return activityStaffListSer.list(dto);
    }

    /**
     * 保存活动人员名单
     *
     * @param to 人员活动名单to
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    public ActivityStaffListBO save(ActivityStaffListTO to) throws SerException {
        return activityStaffListSer.save(to);
    }

    /**
     * 根据id删除活动人员名单
     *
     * @param id 活动人员名单唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activityStaffListSer.remove(id);
    }

    /**
     * 更新活动人员名单
     *
     * @param to 更新活动人员名单to
     * @throws SerException
     */
    @Override
    public void update(ActivityStaffListTO to) throws SerException {
        activityStaffListSer.update(to);
    }
}