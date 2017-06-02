package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ModuleTypeBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.ModuleTypeDTO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.entity.ModuleType;
import com.bjike.goddess.organize.to.ModuleTypeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 模块类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:19 ]
 * @Description: [ 模块类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "organizeSerCache")
@Service
public class ModuleTypeSerImpl extends ServiceImpl<ModuleType, ModuleTypeDTO> implements ModuleTypeSer {

    @Autowired
    private PositionDetailSer positionDetailSer;

    @Override
    public ModuleTypeBO save(ModuleTypeTO to) throws SerException {
        ModuleType entity = BeanTransform.copyProperties(to, ModuleType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ModuleTypeBO.class);
    }

    @Override
    public ModuleTypeBO update(ModuleTypeTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                ModuleType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, ModuleTypeBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public ModuleTypeBO delete(String id) throws SerException {
        ModuleType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("module.id", id));
        if (positionDetailSer.findByCis(dto).size() > 0)
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ModuleTypeBO.class);
    }

    @Override
    public ModuleTypeBO congeal(String id) throws SerException {
        ModuleType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ModuleTypeBO.class);
    }

    @Override
    public ModuleTypeBO thaw(String id) throws SerException {
        ModuleType entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ModuleTypeBO.class);
    }

    @Override
    public List<ModuleTypeBO> findByStatus(Status status) throws SerException {
        ModuleTypeDTO dto = new ModuleTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<ModuleType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ModuleTypeBO.class);
    }

    @Override
    public List<ModuleTypeBO> maps(ModuleTypeDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ModuleTypeBO.class);
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        List<ModuleTypeBO> list = this.findByStatus(Status.THAW);
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (ModuleTypeBO entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getModule()));
        return bos;
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        ModuleTypeDTO dto = new ModuleTypeDTO();
        dto.getConditions().add(Restrict.in(ID, ids));
        List<ModuleType> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (ModuleType entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getModule()));
        return bos;
    }

    @Override
    public List<OpinionBO> findAllOpinion() throws SerException {
        List<ModuleType> list = super.findAll();
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (ModuleType entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getModule()));
        return bos;
    }
}