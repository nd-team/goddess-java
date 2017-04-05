package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.RewardSituationBO;
import com.bjike.goddess.supplier.dto.RewardSituationDTO;
import com.bjike.goddess.supplier.entity.RewardSituation;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 转换获奖情况传输对象
     *
     * @param entity 获奖情况实体对象
     * @return
     */
    private RewardSituationBO transformBO(RewardSituation entity) {
        RewardSituationBO bo = BeanTransform.copyProperties(entity, RewardSituationBO.class);
        bo.setInformation_id(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<RewardSituationBO> findByInformation(String info_id) throws SerException {
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
    public RewardSituationBO save(ContactSituationTO to) throws SerException {
        RewardSituation entity = BeanTransform.copyProperties(to, RewardSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RewardSituationBO update(ContactSituationTO to) throws SerException {
        RewardSituation entity = BeanTransform.copyProperties(to, RewardSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RewardSituationBO delete(String id) throws SerException {
        RewardSituation entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }
}