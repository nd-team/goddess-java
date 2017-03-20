package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.RewardSituationBO;
import com.bjike.goddess.supplier.dto.RewardSituationDTO;
import com.bjike.goddess.supplier.entity.RewardSituation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    @Override
    public List<RewardSituationBO> findByInformation(String info_id) throws SerException {
        RewardSituationDTO dto = new RewardSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<RewardSituation> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, RewardSituationBO.class);
    }

}