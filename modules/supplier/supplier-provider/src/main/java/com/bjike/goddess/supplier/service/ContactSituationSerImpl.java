package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.dto.ContactSituationDTO;
import com.bjike.goddess.supplier.entity.ContactSituation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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


    @Override
    public List<ContactSituationBO> findByInformation(String info_id) throws SerException {
        ContactSituationDTO dto = new ContactSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<ContactSituation> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, ContactSituationBO.class);
    }
}