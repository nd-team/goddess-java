package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.entity.ResponsibleIdea;
import com.bjike.goddess.feedback.enums.AdoptStatus;
import com.bjike.goddess.feedback.enums.GuideAddrStatus;
import com.bjike.goddess.feedback.to.GuidePermissionTO;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 非责任相关人意见业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ResponsibleIdeaSerImpl extends ServiceImpl<ResponsibleIdea, ResponsibleIdeaDTO> implements ResponsibleIdeaSer {
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
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
            flag = cusPermissionSer.getCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("2");
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
    public Long count(ResponsibleIdeaDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public ResponsibleIdeaBO getId(String id) throws SerException {
        ResponsibleIdea responsibleIdea = super.findById(id);
        return BeanTransform.copyProperties(responsibleIdea, ResponsibleIdeaBO.class);
    }

    @Override
    public List<ResponsibleIdeaBO> list(ResponsibleIdeaDTO dto) throws SerException {
        checkSeeIdentity();
        List<ResponsibleIdea> responsibleIdeas = super.findByCis(dto);
        ResponsibleIdea responsibleIdea = new ResponsibleIdea();
        String name = responsibleIdea.getResponsibleIdea();
        List<PositionDetailBO> positionDetailBOS = positionDetailUserAPI.getPositionDetail(name);

        for (PositionDetailBO positionDetailBO : positionDetailBOS) {
            responsibleIdea.setArea(positionDetailBO.getArea());
            responsibleIdea.setProjectGroup(positionDetailBO.getDepartmentName());
        }
        List<ResponsibleIdeaBO> responsibleIdeaBOS = BeanTransform.copyProperties(responsibleIdeas, ResponsibleIdeaBO.class);
        return responsibleIdeaBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResponsibleIdeaBO insert(ResponsibleIdeaTO to) throws SerException {
        checkAddIdentity();
        ResponsibleIdea responsibleIdea = BeanTransform.copyProperties(to, ResponsibleIdea.class, true);
        responsibleIdea.setCreateTime(LocalDateTime.now());
        UserBO userBO = userAPI.currentUser();
        responsibleIdea.setResponsibleIdea(userBO.getUsername());
        responsibleIdea.setIdeaDate(LocalDateTime.now());
        super.save(responsibleIdea);
        return BeanTransform.copyProperties(responsibleIdea, ResponsibleIdeaBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResponsibleIdeaBO adopt(ResponsibleIdeaTO to) throws SerException {
        checkAddIdentity();
        ResponsibleIdea responsibleIdea = super.findById(to.getId());
        BeanTransform.copyProperties(to, responsibleIdea, true);
        responsibleIdea.setAdopt(AdoptStatus.ADOPT);
        super.update(responsibleIdea);
        return BeanTransform.copyProperties(responsibleIdea, ResponsibleIdeaBO.class);
    }
}