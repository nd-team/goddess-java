package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.ProblesClassifyPrepareBO;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.entity.CusPermission;
import com.bjike.goddess.allmeeting.entity.ProblesClassifyPrepare;
import com.bjike.goddess.allmeeting.enums.GuideAddrStatus;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 问题分类议题准备信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class ProblesClassifyPrepareSerImpl extends ServiceImpl<ProblesClassifyPrepare, ProblesClassifyPrepareDTO> implements ProblesClassifyPrepareSer {

    @Autowired
    private AllMeetingOrganizeSer allMeetingOrganizeSer;

    @Autowired
    private UserAPI userAPI;

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
    @Transactional(rollbackFor = SerException.class)
    public ProblesClassifyPrepareBO insertModel(ProblesClassifyPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesClassifyPrepare model = BeanTransform.copyProperties(to, ProblesClassifyPrepare.class, true);
        model.setStatus(Status.THAW);
        super.save(model);
        return BeanTransform.copyProperties(model, ProblesClassifyPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public ProblesClassifyPrepareBO editModel(ProblesClassifyPrepareTO to) throws SerException {
        //校验会议编号,防止非法数据
        allMeetingOrganizeSer.validNum(to.getMeetingNum());
        ProblesClassifyPrepare model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, ProblesClassifyPrepareBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        ProblesClassifyPrepare model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.CONGEAL) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.CONGEAL);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复冻结");
            }
        } else {
            throw new SerException("非法Id，冻结对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProblesClassifyPrepareBO> pageList(ProblesClassifyPrepareDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        return BeanTransform.copyProperties(super.findByPage(dto), ProblesClassifyPrepareBO.class);
    }

    @Override
    public void unfreeze(String id) throws SerException {
        ProblesClassifyPrepare model = super.findById(id);
        if (model != null) {
            if (model.getStatus() != Status.THAW) {
                model.setModifyTime(LocalDateTime.now());
                model.setStatus(Status.THAW);
                super.update(model);
            } else {
                throw new SerException("该记录无需重复解冻");
            }
        } else {
            throw new SerException("非法Id，解冻对象不能为空");
        }
    }
}