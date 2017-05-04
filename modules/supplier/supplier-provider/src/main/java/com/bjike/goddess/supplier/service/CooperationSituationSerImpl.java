package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;
import com.bjike.goddess.supplier.to.CooperationSituationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 合作情况业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.664 ]
 * @Description: [ 合作情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class CooperationSituationSerImpl extends ServiceImpl<CooperationSituation, CooperationSituationDTO> implements CooperationSituationSer {

    @Autowired
    private SupplierInformationSer supplierInformationSer;

    /**
     * 转换合作情况传输对象
     *
     * @param entity 合作情况实体对象
     * @return
     */
    private CooperationSituationBO transformBO(CooperationSituation entity) {
        CooperationSituationBO bo = BeanTransform.copyProperties(entity, CooperationSituationBO.class);
        bo.setInformationId(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<CooperationSituationBO> findByInformation(String infoId) throws SerException {
        CooperationSituationDTO dto = new CooperationSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", infoId));
        List<CooperationSituation> list = super.findByCis(dto);
        List<CooperationSituationBO> bos = new ArrayList<>(0);
        for (CooperationSituation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO save(CooperationSituationTO to) throws SerException {
        CooperationSituation entity = BeanTransform.copyProperties(to, CooperationSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == entity.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO update(CooperationSituationTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        CooperationSituation entity = super.findById(to.getId());
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
    public CooperationSituationBO delete(String id) throws SerException {
        CooperationSituation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public CooperationSituationBO getById(String id) throws SerException {
        CooperationSituation entity = super.findById(id);
        if (null == entity)
            return null;
        else
            return this.transformBO(entity);
    }
}