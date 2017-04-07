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
        classify.setCreateTime(LocalDateTime.now());
        classify.setStatus(Status.THAW);
        super.save(classify);
        return BeanTransform.copyProperties(classify, InstructionClassifyBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InstructionClassifyBO update(InstructionClassifyTO to) throws SerException {
        InstructionClassify classify = super.findById(to.getId());
        classify.setName(to.getName());
        classify.setDescription(to.getDescription());
        super.save(classify);
        return BeanTransform.copyProperties(classify, InstructionClassifyBO.class, true);
    }
}
