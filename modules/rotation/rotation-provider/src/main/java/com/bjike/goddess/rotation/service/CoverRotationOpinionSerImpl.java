package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationOpinionDTO;
import com.bjike.goddess.rotation.entity.CoverRotationOpinion;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位轮换自荐意见业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class CoverRotationOpinionSerImpl extends ServiceImpl<CoverRotationOpinion, CoverRotationOpinionDTO> implements CoverRotationOpinionSer {

    @Override
    public List<CoverRotationOpinionBO> findByCover(String id, CoverRotationOpinionDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("cover.id", id));
        return BeanTransform.copyProperties(super.findByPage(dto), CoverRotationOpinionBO.class);
    }

    @Override
    public Long getTotal(String id) throws SerException {
        CoverRotationOpinionDTO dto = new CoverRotationOpinionDTO();
        dto.getConditions().add(Restrict.eq("cover.id", id));
        return super.count(dto);
    }
}