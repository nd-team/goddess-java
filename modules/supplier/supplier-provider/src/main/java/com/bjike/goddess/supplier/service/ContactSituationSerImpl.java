package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.dto.ContactSituationDTO;
import com.bjike.goddess.supplier.entity.ContactSituation;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 联系情况业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.710 ]
 * @Description: [ 联系情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class ContactSituationSerImpl extends ServiceImpl<ContactSituation, ContactSituationDTO> implements ContactSituationSer {

    @Autowired
    private SupplierInformationSer supplierInformationSer;
    @Autowired
    private SupCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

//    private static final String idFlag = "supplier-01";

    /**
     * 转换联系情况传输对象
     *
     * @param entity 联系情况实体对象
     * @return
     */
    private ContactSituationBO transformBO(ContactSituation entity) {
        ContactSituationBO bo = BeanTransform.copyProperties(entity, ContactSituationBO.class);
        bo.setInformationId(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<ContactSituationBO> findByInformation(String info_id) throws SerException {
        ContactSituationDTO dto = new ContactSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<ContactSituation> list = super.findByCis(dto);
        List<ContactSituationBO> bos = new ArrayList<>(0);
        for (ContactSituation entity : list) {
            bos.add(this.transformBO(entity));
        }
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContactSituationBO save(ContactSituationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        ContactSituation entity = BeanTransform.copyProperties(to, ContactSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == entity.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContactSituationBO update(ContactSituationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        ContactSituation contactSituation = BeanTransform.copyProperties(to, ContactSituation.class, true);
        contactSituation.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == contactSituation.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        contactSituation.setCreateTime(entity.getCreateTime());
        contactSituation.setModifyTime(LocalDateTime.now());
        super.update(contactSituation);
        return this.transformBO(contactSituation);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContactSituationBO delete(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContactSituationBO getById(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(id);
        if (null == entity)
            return null;
        else
            return this.transformBO(entity);
    }

//    @Override
//    public Boolean sonPermission() throws SerException {
//        return supPermissionSer.getSupPermission(idFlag);
//    }


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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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
}