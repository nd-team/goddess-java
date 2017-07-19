package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityStaffListAPI;
import com.bjike.goddess.staffactivity.bo.ActivityApplyInforBO;
import com.bjike.goddess.staffactivity.bo.ActivityStaffListBO;
import com.bjike.goddess.staffactivity.dto.ActivityApplyInforDTO;
import com.bjike.goddess.staffactivity.dto.ActivityStaffListDTO;
import com.bjike.goddess.staffactivity.entity.ActivityApplyInfor;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;
import com.bjike.goddess.staffactivity.enums.GuideAddrStatus;
import com.bjike.goddess.staffactivity.to.ActivityApplyInforTO;
import com.bjike.goddess.staffactivity.to.ActivityStaffListTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.staffactivity.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ActivityDivisionSer activityDivisionSer;
    @Autowired
    private ActivityEvaluateSer activityEvaluateSer;
    @Autowired
    private ActivityExecuteInfoSer activityExecuteInfoSer;
    @Autowired
    private ActivitySchemeSer activitySchemeSer;
    @Autowired
    private ActivityStaffListSer activityStaffListSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("activityapplyinfor");
        obj.setDescribesion("活动申请信息");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = activityDivisionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("activitydivision");
        obj.setDescribesion("活动分工");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis1 = activityEvaluateSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("activityevaluate");
        obj.setDescribesion("活动评价");
        if (flagSeeDis1) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis2 = activityExecuteInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("activityexecuteinfo");
        obj.setDescribesion("活动执行信息");
        if (flagSeeDis2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis3 = activitySchemeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("activityscheme");
        obj.setDescribesion("活动方案");
        if (flagSeeDis3) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis4 = activityStaffListSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("activitystafflist");
        obj.setDescribesion("活动人员名单");
        if (flagSeeDis4) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case JOIN:
                flag = guideAddIdentity();
                break;
            case EXIT:
                flag = guideAddIdentity();
                break;
            case BUSINESSADVICE:
                flag = guideAddIdentity();
                break;
            case CARRY:
                flag = guideAddIdentity();
                break;
            case FUNDEVALUATE:
                flag = guideAddIdentity();
                break;
            case OVERSEERVALUATE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;
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
        checkSeeIdentity();
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
        checkAddIdentity();
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
        checkAddIdentity();
        List<ActivityStaffList> list = getStaffListByApplyId(id);
        activityStaffListSer.remove(list);//删除子类对象
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
        checkAddIdentity();
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
     * @param to    活动申请信息
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
     * @param id   活动申请信息唯一标识
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
        ActivityStaffListTO staffTo = BeanTransform.copyProperties(staff, ActivityStaffListTO.class);
        activityStaffListAPI.save(staffTo);
    }

    /**
     * 退出该活动
     *
     * @param id            活动申请信息唯一标识
     * @param abandonReason 放弃参与活动原因
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void exitActivity(String id, String abandonReason) throws SerException {
        String curUsername = userAPI.currentUser().getUsername();
        ActivityStaffListDTO staffDTO = new ActivityStaffListDTO();
        staffDTO.getConditions().add(Restrict.eq("applyInforId", id));
        staffDTO.getConditions().add(Restrict.eq("staffName", curUsername));
        List<ActivityStaffList> lists = activityStaffListSer.findByCis(staffDTO);
        if (lists != null && !lists.isEmpty()) {
            ActivityStaffList staff = lists.get(0);
            staff.setAbandonReason(abandonReason);
            staff.setIfAttend(Boolean.FALSE);//设置参与状态为FALSE
            activityStaffListSer.update(staff);//执行更新操作
        }
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
        checkSeeIdentity();
        List<ActivityStaffList> list = getStaffListByApplyId(id);//根据活动申请信息id查询活动人员列表
        List<ActivityStaffListBO> listBO = BeanTransform.copyProperties(list, ActivityStaffListBO.class);
        return listBO;
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
        checkSeeIdentity();
        ActivityStaffListDTO dto = new ActivityStaffListDTO();
        dto.getConditions().add(Restrict.eq("applyInforId", id));
        dto.getConditions().add(Restrict.eq("ifAttend", Boolean.TRUE));
        List<ActivityStaffList> list = activityStaffListSer.findByCis(dto);
        List<ActivityStaffListBO> listBO = BeanTransform.copyProperties(list, ActivityStaffListBO.class);
        return listBO;
    }
}