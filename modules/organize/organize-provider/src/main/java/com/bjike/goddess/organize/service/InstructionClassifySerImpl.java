package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.InstructionClassifyBO;
import com.bjike.goddess.organize.dto.InstructionClassifyDTO;
import com.bjike.goddess.organize.entity.InstructionClassify;
import com.bjike.goddess.organize.to.InstructionClassifyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位说明书 - 分类业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class InstructionClassifySerImpl extends ServiceImpl<InstructionClassify, InstructionClassifyDTO> implements InstructionClassifySer {

    @Autowired
    private PositionInstructionSer positionInstructionSer;

    @Override
    public List<InstructionClassifyBO> findStatus() throws SerException {
        InstructionClassifyDTO dto = new InstructionClassifyDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<InstructionClassify> list = super.findByCis(dto);
        List<InstructionClassifyBO> bos = BeanTransform.copyProperties(list, InstructionClassifyBO.class);
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InstructionClassifyBO save(InstructionClassifyTO to) throws SerException {
        InstructionClassify classify = BeanTransform.copyProperties(to, InstructionClassify.class);
        classify.setStatus(Status.THAW);
        super.save(classify);
        return BeanTransform.copyProperties(classify, InstructionClassifyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InstructionClassifyBO update(InstructionClassifyTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        InstructionClassify entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());

        super.update(entity);
        return BeanTransform.copyProperties(entity, InstructionClassifyBO.class);
    }

    @Override
    public InstructionClassifyBO delete(String id) throws SerException {
        InstructionClassify entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (null!=positionInstructionSer.findByClassify(id)&&!positionInstructionSer.findByClassify(id).isEmpty())
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, InstructionClassifyBO.class);
    }

    @Override
    public InstructionClassifyBO close(String id) throws SerException {
        InstructionClassify entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, InstructionClassifyBO.class);
    }

    @Override
    public InstructionClassifyBO open(String id) throws SerException {
        InstructionClassify entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, InstructionClassifyBO.class);
    }

    @Override
    public List<InstructionClassifyBO> maps(InstructionClassifyDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), InstructionClassifyBO.class);
    }
}
