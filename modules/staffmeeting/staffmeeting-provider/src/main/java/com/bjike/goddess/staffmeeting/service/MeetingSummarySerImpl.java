package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingOrganize;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.enums.GuideAddrStatus;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 员工代表大会纪要业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class MeetingSummarySerImpl extends ServiceImpl<MeetingSummary, MeetingSummaryDTO> implements MeetingSummarySer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ReferPermissionSer referPermissionSer;

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
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public MeetingSummaryBO findAndSet(String id) throws SerException {
        checkAddIdentity();
        MeetingSummary model = super.findById(id);
        if (model != null) {
            MeetingSummaryBO bo = setProperties(model);

            return bo;
        } else {
            throw new SerException("非法id,纪要对象不存在!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public MeetingSummaryBO updateModel(MeetingSummaryTO to) throws SerException {
        checkAddIdentity();
        MeetingSummary model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，纪要对象不能为空!");
        }
        return BeanTransform.copyProperties(model, MeetingSummaryBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        checkAddIdentity();
        MeetingSummary model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("该记录无需冻结!");
            }

        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));

        UserBO user = userAPI.currentUser();


        List<MeetingSummary> list = null;
        List<MeetingSummary> summaries = null;
        //没有申请权限的只能查看自己参与的会议数据
        if (!referPermissionSer.getPermission(user.getEmployeeNumber())) {
            dto.getConditions().add(Restrict.like("actualUsers", user.getUsername()));
            list = super.findByPage(dto);
            summaries = new ArrayList<MeetingSummary>();
            if (!CollectionUtils.isEmpty(list)) {
                for (MeetingSummary model : list) {
                    String actualUsers = model.getActualUsers();
                    List<String> users = Arrays.asList(actualUsers.split(","));
                    if (users.contains(user.getUsername())) {
                        summaries.add(model);
                    }
                }
            }
        } else {
            summaries = super.findByPage(dto);
        }

        if (!CollectionUtils.isEmpty(summaries)) {
            List<MeetingSummaryBO> boList = new ArrayList<MeetingSummaryBO>();
            for (MeetingSummary model : summaries) {
                MeetingSummaryBO bo = setProperties(model);
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unFreeze(String id) throws SerException {
        MeetingSummary model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需解冻!");
            }

        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    public MeetingSummaryBO setProperties(MeetingSummary model) {
        MeetingSummaryBO bo = BeanTransform.copyProperties(model, MeetingSummaryBO.class);
        MeetingOrganize organize = model.getMeetingOrganize();
        bo.setMeetingType(organize.getMeetingType());
        bo.setContent(organize.getContent());
        bo.setOrganizer(organize.getOrganizer());
        bo.setLay(organize.getMeetingLay().getName());
        bo.setMeetingPurpose(organize.getMeetingPurpose());
        bo.setTopic(organize.getMeetingLay().getMeetingTopic().getTopic());
        return bo;
    }

}