package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.costdetail.bo.LaborCostDetailBO;
import com.bjike.goddess.costdetail.dto.LaborCostDetailDTO;
import com.bjike.goddess.costdetail.entity.LaborCostDetail;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 劳务成本明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:52 ]
 * @Description: [ 劳务成本明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class LaborCostDetailSerImpl extends ServiceImpl<LaborCostDetail, LaborCostDetailDTO> implements LaborCostDetailSer {
    @Override
    public List<String> findTypeName() throws SerException {
        List<LaborCostDetail> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (LaborCostDetail model : list) {
            String typeName = model.getTypeName();
            if (StringUtils.isNotBlank(model.getTypeName())) {
                set.add(typeName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<LaborCostDetail> findByCostId(String id) throws SerException {
        LaborCostDetailDTO dto = new LaborCostDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", id));
        List<LaborCostDetail> list = super.findByCis(dto);
        return list;
    }
}