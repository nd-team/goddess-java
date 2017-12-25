package com.bjike.goddess.staffactivity.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.bo.ActivityApplyInforBO;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityApplyInforDTO;
import com.bjike.goddess.staffactivity.entity.ActivityApplyInfor;
import com.bjike.goddess.staffactivity.service.ActivityApplyInforSer;
import com.bjike.goddess.staffactivity.to.ActivityApplyInforTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.SonPermissionObject;
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
     * 根据id查询活动申请信息
     *
     * @param id 活动申请信息唯一标识
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    @Override
    public ActivityApplyInforBO findById(String id) throws SerException {
        ActivityApplyInfor model = activityApplyInforSer.findById(id);
        return BeanTransform.copyProperties(model, ActivityApplyInforBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 活动申请信息dto
     * @throws SerException
     */
    @Override
    public Long count(ActivityApplyInforDTO dto) throws SerException {
        return activityApplyInforSer.count(dto);
    }

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
     * @param id 活动申请信息唯一标识
     * @param area 地区
     * @throws SerException
     */
    @Override
    public void joinActivity(String id, String area) throws SerException {
        activityApplyInforSer.joinActivity(id, area);
    }

    /**
     * 退出该活动
     *
     * @param id 活动申请信息唯一标识
     * @param abandonReason 放弃原因
     * @throws SerException
     */
    @Override
    public void exitActivity(String id, String abandonReason) throws SerException {
        activityApplyInforSer.exitActivity(id, abandonReason);
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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return activityApplyInforSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return activityApplyInforSer.guidePermission(guidePermissionTO);
    }
}