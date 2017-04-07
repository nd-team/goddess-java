package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        bo.setInformation_id(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<CooperationSituationBO> findByInformation(String info_id) throws SerException {
        CooperationSituationDTO dto = new CooperationSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<CooperationSituation> list = super.findByCis(dto);
        List<CooperationSituationBO> bos = new ArrayList<>(0);
        for (CooperationSituation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO save(ContactSituationTO to) throws SerException {
        CooperationSituation entity = BeanTransform.copyProperties(to, CooperationSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO update(ContactSituationTO to) throws SerException {
        CooperationSituation entity = BeanTransform.copyProperties(to, CooperationSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO delete(String id) throws SerException {
        CooperationSituation entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }
}