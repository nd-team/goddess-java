package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityApplyInforAPI;
import com.bjike.goddess.staffactivity.api.ActivityStaffListAPI;
import com.bjike.goddess.staffactivity.bo.ActivityApplyInforBO;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityApplyInforDTO;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.entity.ActivityApplyInfor;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;
import com.bjike.goddess.staffactivity.to.ActivityApplyInforTO;
import com.bjike.goddess.staffactivity.to.ActivityStaffListTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动申请信息业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityApplyInforSerImpl extends ServiceImpl<ActivityApplyInfor, ActivityApplyInforDTO> implements ActivityApplyInforSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private ActivityStaffListAPI activityStaffListAPI;

    @Autowired
    private ActivityStaffListSer activityStaffListSer;

    /**
     * 分页查询活动申请信息
     *
     * @param dto 活动申请信息dto
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    @Override
    public List<ActivityApplyInforBO> list(ActivityApplyInforDTO dto) throws SerException {
        List<ActivityApplyInfor> list = super.findByPage(dto);
        List<ActivityApplyInforBO> listBO = BeanTransform.copyProperties(list, ActivityApplyInforBO.class);
        return listBO;
    }

    /**
     * 保存活动申请信息
     *
     * @param to 活动申请信息to
     * @return class ActivityApplyInforBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ActivityApplyInforBO save(ActivityApplyInforTO to) throws SerException {
        ActivityApplyInfor entity = BeanTransform.copyProperties(to, ActivityApplyInfor.class, true);
        entity = super.save(entity);
        ActivityApplyInforBO bo = BeanTransform.copyProperties(entity, ActivityApplyInforBO.class);
        return bo;
    }

    /**
     * 根据id删除活动申请信息
     *
     * @param id 活动申请信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        List<ActivityStaffList> staffList = getStaffListByApplyId(id);
        activityStaffListSer.remove(staffList);//删除子类对象
        super.remove(id);//删除父类对象
    }

    /**
     * 根据活动申请信息id查询活动人员列表
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffList
     * @throws SerException
     */
    private List<ActivityStaffList> getStaffListByApplyId(String id) throws SerException {
        ActivityStaffListDTO dto = new ActivityStaffListDTO();
        dto.getConditions().add(Restrict.eq("applyInforId", id));
        return activityStaffListSer.findByCis(dto);
    }

    /**
     * 更新活动申请信息
     *
     * @param to 活动申请信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ActivityApplyInforTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityApplyInfor model = super.findById(to.getId());
            if (model != null) {
                updateActivityApplyInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动申请信息
     *
     * @param to 活动申请信息
     * @param model 活动申请信息实体
     */
    private void updateActivityApplyInfor(ActivityApplyInforTO to, ActivityApplyInfor model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 参与该活动
     *
     * @param id 活动申请信息唯一标识
     * @param area 地区
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void joinActivity(String id, String area) throws SerException {
        String currentUsername = userAPI.currentUser().getUsername();
        if (StringUtils.isBlank(area) || StringUtils.isBlank(id))
            throw new SerException("地区或者活动信息id为空,无法参与该活动.");
        ActivityStaffList staff = new ActivityStaffList();
        staff.setStaffName(currentUsername);
        staff.setArea(area);
        staff.setIfAttend(Boolean.TRUE);
        staff.setApplyInforId(id);//设置逻辑外键关联
        ActivityStaffListTO staffTo  = BeanTransform.copyProperties(staff, ActivityStaffListTO.class);
        activityStaffListAPI.save(staffTo);
    }

    /**
     * 退出该活动
     *
     * @param id 活动申请信息唯一标识
     * @param abandonReason 放弃参与活动原因
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void exitActivity(String id, String abandonReason) throws SerException {
        ActivityStaffListDTO staffDTO = new ActivityStaffListDTO();
        staffDTO.getConditions().add(Restrict.eq("applyInforId", id));
        ActivityStaffList staff = activityStaffListSer.findOne(staffDTO);
        staff.setAbandonReason(abandonReason);
        staff.setIfAttend(Boolean.FALSE);//设置参与状态为FALSE
        activityStaffListSer.update(staff);//执行更新操作
    }

    /**
     * 查看活动人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ActivityStaffListBO> checkStaffList(String id) throws SerException {
        List<ActivityStaffList> staffList = getStaffListByApplyId(id);//根据活动申请信息id查询活动人员列表
        List<ActivityStaffListBO> staffBOList = BeanTransform.copyProperties(staffList, ActivityStaffListBO.class);
        return staffBOList;
    }

    /**
     * 查看参与了活动并且没有退出活动的人员名单
     *
     * @param id 活动申请信息id
     * @return class ActivityStaffListBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ActivityStaffListBO> checkAttendList(String id) throws SerException {
        ActivityStaffListDTO dto = new ActivityStaffListDTO();
        dto.getConditions().add(Restrict.eq("applyInforId", id));
        dto.getConditions().add(Restrict.eq("ifAttend", Boolean.TRUE));
        List<ActivityStaffList> staffList = activityStaffListSer.findByCis(dto);
        List<ActivityStaffListBO> staffBOList = BeanTransform.copyProperties(staffList, ActivityStaffListBO.class);
        return staffBOList;
    }
}