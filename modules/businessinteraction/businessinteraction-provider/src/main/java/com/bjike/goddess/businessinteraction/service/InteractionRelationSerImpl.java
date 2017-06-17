package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.LeavingMessageDTO;
import com.bjike.goddess.businessinteraction.entity.LeavingMessage;
import com.bjike.goddess.businessinteraction.enums.GuideAddrStatus;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商业能力互动联系业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class InteractionRelationSerImpl extends ServiceImpl<InteractionRelation, InteractionRelationDTO> implements InteractionRelationSer {

    @Autowired
    private LeavingMessageSer leavingMessageSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
        return flag;
    }

    @Override
    public Long countInter(InteractionRelationDTO interactionRelationDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken( userToken);
//        if ( !permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        if(StringUtils.isNoneBlank(interactionRelationDTO.getCompanyName())){
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        Long count = super.count( interactionRelationDTO );
        return count;
    }

    @Override
    public InteractionRelationBO getOneById(String id) throws SerException {



        if(StringUtils.isBlank(id)){
            throw new SerException("id部嫩嗯为空");
        }

        InteractionRelation interactionRelation = super.findById( id );
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class );
    }

    @Override
    public List<InteractionRelationBO> listInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        RpcTransmit.transmitUserToken( userToken);
//        if ( !permissionLevel) {
//            throw new SerException("您的帐号没有权限");
//        }
        checkSeeIdentity();
        if(StringUtils.isNoneBlank(interactionRelationDTO.getCompanyName())){
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        List<InteractionRelation> list = super.findByCis(interactionRelationDTO, true);

        return BeanTransform.copyProperties(list, InteractionRelationBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InteractionRelationBO addInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if ( !permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行添加操作");
//        }

        checkAddIdentity();
        InteractionRelation interactionRelation = null;
        try {
            interactionRelation = BeanTransform.copyProperties(interactionRelationTO,InteractionRelation.class,true);
            interactionRelation.setArea( interactionRelation.getArea().trim());
            interactionRelation.setCreateTime(LocalDateTime.now());
            super.save( interactionRelation );
        } catch (SerException e) {
            throw new SerException("互动联系添加失败");
        }
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InteractionRelationBO editInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        //商务模块添加权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if ( !permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行编辑操作");
//        }
        checkAddIdentity();
        InteractionRelation interact = null;
        try {
            InteractionRelation interactionRelation = BeanTransform.copyProperties(interactionRelationTO,InteractionRelation.class,true);

            interact = super.findById( interactionRelationTO.getId() );
            BeanUtils.copyProperties(interactionRelation,interact,"id","createTime");
            interact.setArea( interact.getArea().trim());

            interact.setModifyTime(LocalDateTime.now());
            super.save(interact );
        } catch (SerException e) {
            throw new SerException("互动联系更新失败");
        }
        return BeanTransform.copyProperties(interact,InteractionRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteInteractionRelation(String id) throws SerException {
//        //商务模块编辑权限
//        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
//        if ( !permissionLevel) {
//            throw new SerException("您不是相应的人员，不可以进行删除操作");
//        }

        checkAddIdentity();
        if (StringUtils.isNotBlank(id)){
            LeavingMessageDTO leavingMessageDTO = new LeavingMessageDTO();
            leavingMessageDTO.getConditions().add(Restrict.eq("interactionRelation.id",id));
            List<LeavingMessage> list = leavingMessageSer.findByCis( leavingMessageDTO);
            if( list!= null && list.size()>0){
                leavingMessageSer.remove( list );
            }
            super.remove(id);
        }else{
            throw new SerException("互动联系id不能为空");
        }
    }

    @Override
    public List<InteractionRelationBO> searchInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
        if (StringUtils.isNotBlank(interactionRelationDTO.getCompanyName())) {
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        List<InteractionRelation> list = super.findByCis(interactionRelationDTO, true);

        return BeanTransform.copyProperties(list, InteractionRelationBO.class );
    }
}