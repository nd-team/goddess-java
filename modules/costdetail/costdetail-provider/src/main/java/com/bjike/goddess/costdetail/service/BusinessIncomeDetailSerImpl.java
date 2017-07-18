package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.BusinessIncomeDetailBO;
import com.bjike.goddess.costdetail.dto.BusinessIncomeDetailDTO;
import com.bjike.goddess.costdetail.entity.BusinessIncomeDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 主营业务收入明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:27 ]
 * @Description: [ 主营业务收入明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class BusinessIncomeDetailSerImpl extends ServiceImpl<BusinessIncomeDetail, BusinessIncomeDetailDTO> implements BusinessIncomeDetailSer {
    @Override
    public List<String> findTypeName() throws SerException {
        List<BusinessIncomeDetail> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusinessIncomeDetail model : list) {
            String typeName = model.getTypeName();
            if (StringUtils.isNotBlank(model.getTypeName())) {
                set.add(typeName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<BusinessIncomeDetail> findByCostId(String id) throws SerException {
        BusinessIncomeDetailDTO dto = new BusinessIncomeDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", id));
        List<BusinessIncomeDetail> list = super.findByCis(dto);
        return list;
    }
}