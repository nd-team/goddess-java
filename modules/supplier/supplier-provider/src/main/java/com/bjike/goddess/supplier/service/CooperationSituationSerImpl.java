package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    @Override
    public List<CooperationSituationBO> findByInformation(String info_id) throws SerException {
        CooperationSituationDTO dto = new CooperationSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<CooperationSituation> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, CooperationSituationBO.class);
    }
}