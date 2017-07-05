package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsGatherBO;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.entity.QualificationsGather;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsGatherTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质办理信息采集业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsGatherSerImpl extends ServiceImpl<QualificationsGather, QualificationsGatherDTO> implements QualificationsGatherSer {

    @Autowired
    private QualificationsHandleSer handleSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO save(QualificationsGatherTO to) throws SerException {
        QualificationsGather entity = BeanTransform.copyProperties(to, QualificationsGather.class, true);
        if (null == handleSer.findByType(to.getType())) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(to.getType());
            handleTO.setCost(to.getCost());
            handleSer.save(handleTO);
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO update(QualificationsGatherTO to) throws SerException {
        QualificationsGather entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        if (null == handleSer.findByType(to.getType())) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(to.getType());
            handleTO.setCost(to.getCost());
            handleSer.save(handleTO);
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO delete(String id) throws SerException {
        QualificationsGather entity = super.findById(id);
        if(null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Override
    public List<QualificationsGatherBO> findByType(String type) throws SerException {
        QualificationsGatherDTO dto = new QualificationsGatherDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<QualificationsGather> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsGatherBO.class);
    }

    @Override
    public List<QualificationsGatherBO> maps(QualificationsGatherDTO dto) throws SerException {
        List<QualificationsGather> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsGatherBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsGatherBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), QualificationsGatherBO.class);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
}