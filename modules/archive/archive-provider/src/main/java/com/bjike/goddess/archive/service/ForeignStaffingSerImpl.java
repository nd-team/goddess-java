package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 对外人员信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ForeignStaffingSerImpl extends ServiceImpl<ForeignStaffing, ForeignStaffingDTO> implements ForeignStaffingSer {

    @Autowired
    private ForeignStaffingSetSer foreignStaffingSetSer;

    private ForeignStaffingBO transformBO(ForeignStaffing entity) throws SerException {
        ForeignStaffingBO bo = BeanTransform.copyProperties(entity, ForeignStaffingBO.class);
        bo.setTypeId(entity.getType().getId());
        bo.setTypeName(entity.getType().getName());
        return bo;
    }

    private List<ForeignStaffingBO> transformBOList(List<ForeignStaffing> list) throws SerException {
        List<ForeignStaffingBO> bos = new ArrayList<>(list.size());
        for (ForeignStaffing entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingBO save(ForeignStaffingTO to) throws SerException {
        ForeignStaffing entity = BeanTransform.copyProperties(to, ForeignStaffing.class, true);
        entity.setType(foreignStaffingSetSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("使用类型不能为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        ForeignStaffing entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setType(foreignStaffingSetSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("使用类型不能为空");
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ForeignStaffingBO delete(String id) throws SerException {
        ForeignStaffing entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ForeignStaffingBO getById(String id) throws SerException {
        ForeignStaffing entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return BeanTransform.copyProperties(entity, ForeignStaffingBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        ForeignStaffingDTO dto = new ForeignStaffingDTO();
        return super.count(dto);
    }
}