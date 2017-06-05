package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.FinanceInfoBO;
import com.bjike.goddess.qualifications.dto.FinanceInfoDTO;
import com.bjike.goddess.qualifications.entity.FinanceInfo;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.FinanceInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 财务资料业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class FinanceInfoSerImpl extends ServiceImpl<FinanceInfo, FinanceInfoDTO> implements FinanceInfoSer {

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FinanceInfoBO save(FinanceInfoTO to) throws SerException {
        FinanceInfo entity = BeanTransform.copyProperties(to, FinanceInfo.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, FinanceInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FinanceInfoBO update(FinanceInfoTO to) throws SerException {
        FinanceInfo entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, FinanceInfoBO.class);
    }

    @Override
    public FinanceInfoBO delete(String id) throws SerException {
        FinanceInfo entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        List<QualificationsHandle> list = qualificationsHandleSer.findAll();
        for (QualificationsHandle handle : list)
            if (handle.getFinanceSet().stream().filter(m -> m.getId().equals(id)).count() != 0)
                throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, FinanceInfoBO.class);
    }


    @Override
    public List<FinanceInfoBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), FinanceInfoBO.class);
    }

    @Override
    public List<FinanceInfoBO> maps(FinanceInfoDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), FinanceInfoBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public FinanceInfoBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), FinanceInfoBO.class);
    }
}