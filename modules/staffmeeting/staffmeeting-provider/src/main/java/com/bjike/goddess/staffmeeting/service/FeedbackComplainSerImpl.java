package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.enums.GuideAddrStatus;
import com.bjike.goddess.staffmeeting.excel.SonPermissionObject;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通告反馈投诉业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmeetingSerCache")
@Service
public class FeedbackComplainSerImpl extends ServiceImpl<FeedbackComplain, FeedbackComplainDTO> implements FeedbackComplainSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private MeetingLaySer meetingLaySer;

    @Autowired
    private MeetingOrganizeSer meetingOrganizeSer;

    @Autowired
    private MeetingSummarySer meetingSummarySer;

    @Autowired
    private MeetingTopicSer meetingTopicSer;




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
        obj.setName("feedbackComplain");
        obj.setDescribesion("通告反馈投诉表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = meetingLaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("meetingLay");
        obj.setDescribesion("会议层面表");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = meetingOrganizeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("meetingOrganize");
        obj.setDescribesion("会议组织表");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = meetingSummarySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("meetingSummary");
        obj.setDescribesion("员工代表大会纪要表");
        if (flagSeeEmail) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeBase = meetingTopicSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("meetingTopic");
        obj.setDescribesion("会议主题表");
        if (flagSeeBase) {
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
        return flag;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FeedbackComplainBO insertModel(FeedbackComplainTO to) throws SerException {
        checkAddIdentity();
        UserBO userBO = userAPI.currentUser();
        String username = userBO.getUsername();
        String userNum = userBO.getEmployeeNumber();
        MeetingSummary summary = meetingSummarySer.findById(to.getSummaryId());
        if (summary != null) {
            if (isAttend(username, summary)) {
                FeedbackComplain model = BeanTransform.copyProperties(to, FeedbackComplain.class);
                model.setMeetingSummary(summary);
                model.setDissentUser(username);
                model.setDissentUserNum(userNum);
                super.save(model);
                return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
            } else {
                throw new SerException("只有该会议参会人员才能反馈!");
            }
        } else {
            throw new SerException("非法纪要Id,纪要对象不能为空!");
        }
    }

    //检查当前用户是否参与会议
    public Boolean isAttend(String username, MeetingSummary summary) throws SerException {
        String userStr = summary.getActualUsers();
        if (userStr.contains(",")) {
            String[] users = summary.getActualUsers().split(",");
            if (!Arrays.asList(users).contains(username)) {
                return false;
            }
        } else {
            if (!username.equals(userStr)) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public FeedbackComplainBO updateModel(FeedbackComplainTO to) throws SerException {
        checkAddIdentity();
        UserBO userBO = userAPI.currentUser();
        String username = userBO.getUsername();
        String userNum = userBO.getEmployeeNumber();
        FeedbackComplain model = super.findById(to.getId());
        if (model != null) {
            MeetingSummary summary = meetingSummarySer.findById(to.getSummaryId());
            if (summary != null) {
                if (isAttend(username, summary)) {
                    BeanTransform.copyProperties(to, model);
                    model.setDissentUser(username);
                    model.setDissentUserNum(userNum);
                    model.setModifyTime(LocalDateTime.now());
                    super.update(model);
                    return BeanTransform.copyProperties(model, FeedbackComplainBO.class);
                } else {
                    throw new SerException("只有该会议参会人员才能反馈!");
                }
            } else {
                throw new SerException("非法纪要Id,纪要对象不能为空!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        checkAddIdentity();
        FeedbackComplain model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setStatus(Status.CONGEAL);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("该记录无需冻结!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }

    @Override
    public List<FeedbackComplainBO> pageList(FeedbackComplainDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getSorts().add("dissentUserNum=asc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        return BeanTransform.copyProperties(super.findByPage(dto), FeedbackComplainBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unfreeze(String id) throws SerException {
        checkAddIdentity();
        FeedbackComplain model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setStatus(Status.THAW);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
            } else {
                throw new SerException("该记录无需解冻!");
            }
        } else {
            throw new SerException("非法Id，反馈对象不能为空");
        }
    }
}