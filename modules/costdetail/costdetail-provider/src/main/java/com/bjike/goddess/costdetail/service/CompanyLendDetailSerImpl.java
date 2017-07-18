package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.CompanyLendDetailBO;
import com.bjike.goddess.costdetail.dto.CompanyLendDetailDTO;
import com.bjike.goddess.costdetail.entity.CompanyLendDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 公司借出明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 06:18 ]
 * @Description: [ 公司借出明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class CompanyLendDetailSerImpl extends ServiceImpl<CompanyLendDetail, CompanyLendDetailDTO> implements CompanyLendDetailSer {
    @Override
    public List<String> findTypeName() throws SerException {
        List<CompanyLendDetail> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CompanyLendDetail model : list) {
            String typeName = model.getTypeName();
            if (StringUtils.isNotBlank(model.getTypeName())) {
                set.add(typeName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<CompanyLendDetail> findByCostId(String id) throws SerException {
        CompanyLendDetailDTO dto = new CompanyLendDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", id));
        List<CompanyLendDetail> list = super.findByCis(dto);
        return list;

    }
}