package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.dto.ContactSituationDTO;
import com.bjike.goddess.supplier.entity.ContactSituation;
import com.bjike.goddess.supplier.to.ContactSituationTO;
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
    private SupPermissionSer supPermissionSer;

    private static final String idFlag = "supplier-01";

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
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
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
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == entity.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContactSituationBO delete(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContactSituationBO getById(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        ContactSituation entity = super.findById(id);
        if (null == entity)
            return null;
        else
            return this.transformBO(entity);
    }
}