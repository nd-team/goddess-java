package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.entity.Dimension;
import com.bjike.goddess.organize.to.DimensionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 维度业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:24]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("dimensionSer")

public class DimensionSerImpl extends ServiceImpl<Dimension, DimensionDTO> implements DimensionSer {


    @Override
    public List<DimensionBO> findStatus() throws SerException {
        DimensionDTO dto = new DimensionDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Dimension> list = super.findByCis(dto, false);
        List<DimensionBO> bos = BeanTransform.copyProperties(list, DimensionBO.class);
        return bos;
    }

    /**
     * 检测维度是否有重复
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(DimensionTO to) throws SerException {
        DimensionDTO dto = new DimensionDTO();
        dto.getConditions().add(Restrict.eq("name", to.getName()));
        if (super.findOne(dto) != null)
            throw new SerException(to.getName() + ":该维度已存在,无法保存");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DimensionBO save(DimensionTO to) throws SerException {
        this.checkUnique(to);
        Dimension dimension = BeanTransform.copyProperties(to, Dimension.class);
        dimension.setStatus(Status.THAW);
        super.save(dimension);
        return BeanTransform.copyProperties(dimension, DimensionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DimensionBO update(DimensionTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        Dimension entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (!entity.getName().equals(to.getName()))
            this.checkUnique(to);
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, DimensionBO.class);
    }

    @Override
    public DimensionBO delete(String id) throws SerException {
        Dimension entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return BeanTransform.copyProperties(entity, DimensionBO.class);
    }

    @Override
    public DimensionBO close(String id) throws SerException {
        Dimension entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, DimensionBO.class);
    }

    @Override
    public DimensionBO open(String id) throws SerException {
        Dimension entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, DimensionBO.class);
    }

    @Override
    public List<DimensionBO> maps(DimensionDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), DimensionBO.class);
    }
}
