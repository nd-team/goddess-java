package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;
import com.bjike.goddess.supplier.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 供应商基本信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.062 ]
 * @Description: [ 供应商基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierInformationSerImpl extends ServiceImpl<SupplierInformation, SupplierInformationDTO> implements SupplierInformationSer {

    @Autowired
    private ContactSituationSer contactSituationSer;
    @Autowired
    private CooperationSituationSer cooperationSituationSer;
    @Autowired
    private RewardSituationSer rewardSituationSer;
    @Autowired
    private EnterpriseQualificationSer enterpriseQualificationSer;

    @Override
    public SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        SupplierInformation entity = BeanTransform.copyProperties(to, SupplierInformation.class);
        entity.setExecution(entity.getExecution());
        super.save(entity);
        return BeanTransform.copyProperties(entity, SupplierInformationBO.class);
    }

    private void updateDetailed(SupplierInformation entity, ContactSituationTO contactSituation
            , CooperationSituationTO cooperationSituation, EnterpriseQualificationTO enterpriseQualification
            , RewardSituationTO rewardSituation) throws SerException {
        SupplierInformation information = super.findById(entity.getId());


    }

    /**
     * 计算完成度
     */
    private Double countExecution(SupplierInformation entity) {


        return 0d;
    }


    @Override
    public SupplierInformationBO update(SupplierInformationTO to, ContactSituationTO contactSituation
            , CooperationSituationTO cooperationSituation, EnterpriseQualificationTO enterpriseQualification
            , RewardSituationTO rewardSituation) throws SerException {
        return null;
    }
}