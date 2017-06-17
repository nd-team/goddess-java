package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.RewardSituationBO;
import com.bjike.goddess.supplier.dto.RewardSituationDTO;
import com.bjike.goddess.supplier.entity.RewardSituation;
import com.bjike.goddess.supplier.to.RewardSituationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 获奖情况业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.061 ]
 * @Description: [ 获奖情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class RewardSituationSerImpl extends ServiceImpl<RewardSituation, RewardSituationDTO> implements RewardSituationSer {

    @Autowired
    private SupplierInformationSer supplierInformationSer;
    @Autowired
    private SupPermissionSer supPermissionSer;

    private static final String idFlag = "supplier-01";

    /**
     * 转换获奖情况传输对象
     *
     * @param entity 获奖情况实体对象
     * @return
     */
    private RewardSituationBO transformBO(RewardSituation entity) {
        RewardSituationBO bo = BeanTransform.copyProperties(entity, RewardSituationBO.class);
        bo.setInformationId(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<RewardSituationBO> findByInformation(String info_id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        RewardSituationDTO dto = new RewardSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<RewardSituation> list = super.findByCis(dto);
        List<RewardSituationBO> bos = new ArrayList<>(0);
        for (RewardSituation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RewardSituationBO save(RewardSituationTO to) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        RewardSituation entity = BeanTransform.copyProperties(to, RewardSituation.class, true);
        entity.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == entity.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RewardSituationBO update(RewardSituationTO to) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        RewardSituation entity = super.findById(to.getId());
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
    public RewardSituationBO delete(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        RewardSituation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public RewardSituationBO getById(String id) throws SerException {
        if (!supPermissionSer.getSupPermission(idFlag))
            throw new SerException("您的帐号没有权限");
        RewardSituation entity = super.findById(id);
        if (null == entity)
            return null;
        else
            return this.transformBO(entity);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return supPermissionSer.getSupPermission(idFlag);
    }

}