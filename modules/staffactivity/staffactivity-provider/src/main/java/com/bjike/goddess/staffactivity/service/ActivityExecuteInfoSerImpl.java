package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.entity.ActivityApplyInfor;
import com.bjike.goddess.staffactivity.entity.ActivityExecuteInfo;
import com.bjike.goddess.staffactivity.entity.ActivityStaffList;
import com.bjike.goddess.staffactivity.enums.GuideAddrStatus;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private ActivityApplyInforSer activityApplyInforSer;
    @Autowired
    private ActivityStaffListSer activityStaffListSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
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
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoBO
     * @throws SerException
     */
    @Override
    public List<ActivityExecuteInfoBO> list(ActivityExecuteInfoDTO dto) throws SerException {
        checkSeeIdentity();
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
        checkAddIdentity();
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
        checkAddIdentity();
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
        checkAddIdentity();
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
     * @param to    活动执行信息to
     * @param model 活动执行信息
     */
    private void updateActivityExecuteInfo(ActivityExecuteInfoTO to, ActivityExecuteInfo model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public void send() throws SerException {
        List<ActivityApplyInfor> applys = activityApplyInforSer.findAll();
        List<ActivityStaffList> lists = activityStaffListSer.findAll();
        List<ActivityExecuteInfo> executeInfos = super.findAll();
        for (ActivityApplyInfor apply : applys) {
            Set<String> names = new HashSet<>();
            Set<String> exNames = new HashSet<>();
            for (ActivityExecuteInfo executeInfo : executeInfos) {
                if (apply.getActivityType().equals(executeInfo.getActivityType())) {
                    for (ActivityStaffList list : lists) {
                        if (list.getApplyInforId().equals(apply.getId())) {
                            names.add(list.getStaffName());
                        }
                    }
                    exNames.add(executeInfo.getName());
                }
            }
            for (String name : names) {
                if (!exNames.contains(name)) {
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle("您有活动执行未填写");
                    messageTO.setContent("您有活动执行未填写，请上系统填写");
                    String mail = internalContactsAPI.getEmail(name);
                    if (null != mail) {
                        String[] r = new String[]{mail};
                        messageTO.setReceivers(r);
                        messageAPI.send(messageTO);
                    }
                }
            }
        }
    }

    @Override
    public Set<String> allActivityScheme() throws SerException {
        List<ActivityExecuteInfo> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (ActivityExecuteInfo a : list) {
            set.add(a.getActivityScheme());
        }
        return set;
    }
}