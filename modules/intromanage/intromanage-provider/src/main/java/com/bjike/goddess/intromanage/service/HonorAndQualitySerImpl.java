package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.HonorAndQualityBO;
import com.bjike.goddess.intromanage.dto.HonorAndQualityDTO;
import com.bjike.goddess.intromanage.entity.HonorAndQuality;
import com.bjike.goddess.intromanage.to.HonorAndQualityTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 荣誉与资质业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:28 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class HonorAndQualitySerImpl extends ServiceImpl<HonorAndQuality, HonorAndQualityDTO> implements HonorAndQualitySer {

    /**
     * 分页查询荣誉与资质
     *
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    @Override
    public List<HonorAndQualityBO> list(HonorAndQualityDTO dto) throws SerException {
        List<HonorAndQuality> list = super.findByPage(dto);
        List<HonorAndQualityBO> listBO = BeanTransform.copyProperties(list, HonorAndQualityBO.class);
        return listBO;
    }

    /**
     * 保存荣誉与资质
     *
     * @param to 荣誉与资质to
     * @return class HonorAndQualityBO
     * @throws SerException
     */
    @Override
    @Transactional
    public HonorAndQualityBO save(HonorAndQualityTO to) throws SerException {
        HonorAndQuality entity = BeanTransform.copyProperties(to, HonorAndQuality.class, true);
        entity = super.save(entity);
        HonorAndQualityBO bo = BeanTransform.copyProperties(entity, HonorAndQualityBO.class);
        return bo;
    }

    /**
     * 更新荣誉与资质
     *
     * @param to 荣誉与资质to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(HonorAndQualityTO to) throws SerException {
        HonorAndQuality entity = BeanTransform.copyProperties(to, HonorAndQuality.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除荣誉与资质
     *
     * @param id 荣誉与资质唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}