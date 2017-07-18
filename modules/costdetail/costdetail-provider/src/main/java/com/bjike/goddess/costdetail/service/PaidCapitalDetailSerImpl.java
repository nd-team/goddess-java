package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.PaidCapitalDetailBO;
import com.bjike.goddess.costdetail.dto.PaidCapitalDetailDTO;
import com.bjike.goddess.costdetail.entity.PaidCapitalDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 实收资本明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 07:06 ]
 * @Description: [ 实收资本明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class PaidCapitalDetailSerImpl extends ServiceImpl<PaidCapitalDetail, PaidCapitalDetailDTO> implements PaidCapitalDetailSer {
    @Override
    public List<String> findTypeName() throws SerException {
        List<PaidCapitalDetail> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (PaidCapitalDetail model : list) {
            String typeName = model.getTypeName();
            if (StringUtils.isNotBlank(model.getTypeName())) {
                set.add(typeName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<PaidCapitalDetail> findByCostId(String id) throws SerException {
        PaidCapitalDetailDTO dto = new PaidCapitalDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", id));
        List<PaidCapitalDetail> list = super.findByCis(dto);
        return list;
    }
}