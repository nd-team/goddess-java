package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffactivity.bo.ActivityApplyInforBO;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityApplyInforDTO;
import com.bjike.goddess.staffactivity.service.ActivityApplyInforSer;
import com.bjike.goddess.staffactivity.to.ActivityApplyInforTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动申请信息业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("activityApplyInforApiImpl")
public class ActivityApplyInforApiImpl implements ActivityApplyInforAPI {

    @Autowired
    private ActivityApplyInforSer activityApplyInforSer;

    /**
     * 分页查询活动申请信息
     *
     * @param dto 活动申请信息dto
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    @Override
    public List<ActivityApplyInforBO> list(ActivityApplyInforDTO dto) throws SerException {
        return activityApplyInforSer.list(dto);
    }

    /**
     * 保存活动申请信息
     *
     * @param to 活动申请信息to
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    @Override
    public ActivityApplyInforBO save(ActivityApplyInforTO to) throws SerException {
        return activityApplyInforSer.save(to);
    }

    /**
     * 根据id删除活动申请信息
     *
     * @param id 活动申请信息唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        activityApplyInforSer.remove(id);
    }

    /**
     * 更新活动申请信息
     *
     * @param to 活动申请信息to
     * @throws SerException
     */
    @Override
    public void update(ActivityApplyInforTO to) throws SerException {
        activityApplyInforSer.update(to);
    }

    /**
     * 参与该活动
     *
     * @param to 活动申请信息to
     * @throws SerException
     */
    @Override
    public void joinActivity(ActivityApplyInforTO to) throws SerException {
        activityApplyInforSer.joinActivity(to);
    }

    /**
     * 退出该活动
     *
     * @param to 活动申请信息to
     * @throws SerException
     */
    @Override
    public void exitActivity(ActivityApplyInforTO to) throws SerException {
        activityApplyInforSer.exitActivity(to);
    }

    /**
     * 查看活动人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    public List<ActivityStaffListBO> checkStaffList(String id) throws SerException {
        return activityApplyInforSer.checkStaffList(id);
    }

    /**
     * 查看参与了活动并且没有退出活动的人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    public List<ActivityStaffListBO> checkAttendList(String id) throws SerException {
        return activityApplyInforSer.checkAttendList(id);
    }

}