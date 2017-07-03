package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.CompanyInfoBO;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.entity.CompanyInfo;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.CompanyInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司基本信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:38 ]
 * @Description: [ 公司基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class CompanyInfoSerImpl extends ServiceImpl<CompanyInfo, CompanyInfoDTO> implements CompanyInfoSer {

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO save(CompanyInfoTO to) throws SerException {
        CompanyInfo entity = BeanTransform.copyProperties(to, CompanyInfo.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO update(CompanyInfoTO to) throws SerException {
        CompanyInfo entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO delete(String id) throws SerException {
        CompanyInfo entity = super.findById(id);
        if(null == entity)
            throw new SerException("该数据不存在");
        List<QualificationsHandle> list = qualificationsHandleSer.findAll();
        for (QualificationsHandle handle : list)
            if (handle.getCompanySet().stream().filter(m -> m.getId().equals(id)).count() != 0)
                throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Override
    public List<CompanyInfoBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), CompanyInfoBO.class);
    }

    @Override
    public List<CompanyInfoBO> maps(CompanyInfoDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), CompanyInfoBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public CompanyInfoBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), CompanyInfoBO.class);
    }
}