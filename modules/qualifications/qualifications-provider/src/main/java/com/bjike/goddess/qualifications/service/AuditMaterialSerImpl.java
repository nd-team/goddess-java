package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.entity.AuditMaterial;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 审核资料业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class AuditMaterialSerImpl extends ServiceImpl<AuditMaterial, AuditMaterialDTO> implements AuditMaterialSer {

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO save(AuditMaterialTO to) throws SerException {
        AuditMaterial entity = BeanTransform.copyProperties(to, AuditMaterial.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO update(AuditMaterialTO to) throws SerException {
        AuditMaterial entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO delete(String id) throws SerException {
        AuditMaterial entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        List<QualificationsHandle> list = qualificationsHandleSer.findAll();
        for (QualificationsHandle handle : list)
            if (handle.getMaterialSet().stream().filter(m -> m.getId().equals(id)).count() != 0)
                throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Override
    public List<AuditMaterialBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), AuditMaterialBO.class);
    }

    @Override
    public List<AuditMaterialBO> maps(AuditMaterialDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), AuditMaterialBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public AuditMaterialBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), AuditMaterialBO.class);
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