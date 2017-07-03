package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.OpinionBO;
import com.bjike.goddess.projectroyalty.bo.RatioBO;
import com.bjike.goddess.projectroyalty.dto.RatioDTO;
import com.bjike.goddess.projectroyalty.entity.Ratio;
import com.bjike.goddess.projectroyalty.to.RatioTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 毛利率业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:48 ]
 * @Description: [ 毛利率业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class RatioSerImpl extends ServiceImpl<Ratio, RatioDTO> implements RatioSer {

    @Override
    public RatioBO save(RatioTO to) throws SerException {
        Ratio entity = BeanTransform.copyProperties(to, Ratio.class);
        RatioDTO dto = new RatioDTO();
        dto.getConditions().add(Restrict.eq("ratio", to.getRatio()));
        if (super.count(dto) != 0)
            throw new SerException(to.getRatio() + ":已存在");
        super.save(entity);
        return BeanTransform.copyProperties(entity, RatioBO.class);
    }

    @Override
    public RatioBO update(RatioTO to) throws SerException {
        Ratio entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        if (to.getRatio().doubleValue() != entity.getRatio().doubleValue()) {
            RatioDTO dto = new RatioDTO();
            dto.getConditions().add(Restrict.eq("ratio", to.getRatio()));
            if (super.count(dto) != 0)
                throw new SerException(to.getRatio() + ":已存在");
        }
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, RatioBO.class);
    }

    @Override
    public RatioBO delete(String id) throws SerException {
        Ratio entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, RatioBO.class);
    }

    @Override
    public RatioBO getById(String id) throws SerException {
        Ratio entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, RatioBO.class);
    }

    @Override
    public List<RatioBO> maps(RatioDTO dto) throws SerException {
        dto.getSorts().add("ratio=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), RatioBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        RatioDTO dto = new RatioDTO();
        return super.count(dto);
    }

    @Override
    public List<OpinionBO> findOpinion() throws SerException {
        List<OpinionBO> bos = new ArrayList<>(0);
        List<Ratio> list = super.findAll();
        for (Ratio entity : list)
            bos.add(new OpinionBO(entity.getId(), entity.getRatio().toString()));
        return bos;
    }
}