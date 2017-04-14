package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
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
        bo.setType_id(entity.getType().getId());
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
        entity.setType(foreignStaffingSetSer.findById(to.getType_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ForeignStaffing entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setType(foreignStaffingSetSer.findById(to.getId()));
                super.update(entity);
                return this.transformBO(entity);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ForeignStaffingBO delete(String id) throws SerException {
        ForeignStaffing entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }
}