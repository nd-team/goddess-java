package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.OperateBO;
import com.bjike.goddess.organize.dto.OperateDTO;
import com.bjike.goddess.organize.entity.Operate;
import com.bjike.goddess.organize.to.OperateTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作类型业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class OperateSerImpl extends ServiceImpl<Operate, OperateDTO> implements OperateSer {

    @Override
    public List<OperateBO> findStatus() throws SerException {
        OperateDTO dto = new OperateDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Operate> list = super.findByCis(dto);
        List<OperateBO> bos = BeanTransform.copyProperties(list, OperateBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperateBO save(OperateTO to) throws SerException {
        Operate operate = BeanTransform.copyProperties(to, Operate.class);
        operate.setStatus(Status.THAW);
        super.save(operate);
        return BeanTransform.copyProperties(operate, OperateBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OperateBO update(OperateTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        Operate entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, OperateBO.class);
    }

    @Override
    public OperateBO delete(String id) throws SerException {
        Operate entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return BeanTransform.copyProperties(entity, OperateBO.class);
    }

    @Override
    public OperateBO close(String id) throws SerException {
        Operate entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, OperateBO.class);
    }

    @Override
    public OperateBO open(String id) throws SerException {
        Operate entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, OperateBO.class);
    }

    @Override
    public List<OperateBO> maps(OperateDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), OperateBO.class);
    }
}
