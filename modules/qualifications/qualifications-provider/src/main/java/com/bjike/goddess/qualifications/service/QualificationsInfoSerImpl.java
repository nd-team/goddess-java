package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsHandleBO;
import com.bjike.goddess.qualifications.bo.QualificationsInfoBO;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoStatusTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质信息管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsInfoSerImpl extends ServiceImpl<QualificationsInfo, QualificationsInfoDTO> implements QualificationsInfoSer {

    @Autowired
    private QualificationsHandleSer handleSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO save(QualificationsInfoTO to) throws SerException {
        QualificationsInfo entity = BeanTransform.copyProperties(to, QualificationsInfo.class, true);
        QualificationsHandleBO handle = handleSer.findByType(entity.getType());
        if (null == handle) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(entity.getType());
            handleSer.save(handleTO);
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO update(QualificationsInfoTO to) throws SerException {
        QualificationsInfo entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        QualificationsHandleBO handle = handleSer.findByType(entity.getType());
        if (null == handle) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(entity.getType());
            handleSer.save(handleTO);
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO delete(String id) throws SerException {
        QualificationsInfo entity = super.findById(id);
        if(null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO updateStatus(QualificationsInfoStatusTO to) throws SerException {
        QualificationsInfo entity = super.findById(to.getId());
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(to.getStatus());
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Override
    public List<QualificationsInfoBO> findByType(String type) throws SerException {
        QualificationsInfoDTO dto = new QualificationsInfoDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<QualificationsInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsInfoBO.class);
    }

    @Override
    public List<QualificationsInfoBO> maps(QualificationsInfoDTO dto) throws SerException {
        List<QualificationsInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsInfoBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsInfoBO getById(String id) throws SerException {
        QualificationsInfo entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
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