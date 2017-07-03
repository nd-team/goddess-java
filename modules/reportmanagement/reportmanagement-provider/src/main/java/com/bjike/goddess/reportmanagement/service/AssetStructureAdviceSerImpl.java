package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.AssetStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.AssetStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.entity.AssetStructureAdvice;
import com.bjike.goddess.reportmanagement.to.AssetStructureAdviceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资产结构管理建议设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class AssetStructureAdviceSerImpl extends ServiceImpl<AssetStructureAdvice, AssetStructureAdviceDTO> implements AssetStructureAdviceSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AssetStructureAdviceBO save(AssetStructureAdviceTO to) throws SerException {
        AssetStructureAdvice entity = BeanTransform.copyProperties(to, AssetStructureAdvice.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AssetStructureAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(AssetStructureAdviceTO to) throws SerException {
        AssetStructureAdvice entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, AssetStructureAdvice.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<AssetStructureAdviceBO> list(AssetStructureAdviceDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<AssetStructureAdvice> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AssetStructureAdviceBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        AssetStructureAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public AssetStructureAdviceBO findByID(String id) throws SerException {
        AssetStructureAdvice entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, AssetStructureAdviceBO.class);
    }

    @Override
    public Long count(AssetStructureAdviceDTO dto) throws SerException {
        return super.count(dto);
    }
}