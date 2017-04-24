package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.bo.InstructionClassifyBO;
import com.bjike.goddess.organize.dto.InstructionClassifyDTO;
import com.bjike.goddess.organize.service.InstructionClassifySer;
import com.bjike.goddess.organize.to.InstructionClassifyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("instructionClassifyApiImpl")
public class InstructionClassifyApiImpl implements InstructionClassifyAPI {

    @Autowired
    private InstructionClassifySer instructionClassifySer;

    @Override
    public List<InstructionClassifyBO> findStatus() throws SerException {
        return instructionClassifySer.findStatus();
    }

    @Override
    public InstructionClassifyBO save(InstructionClassifyTO to) throws SerException {
        return instructionClassifySer.save(to);
    }

    @Override
    public InstructionClassifyBO update(InstructionClassifyTO to) throws SerException {
        return instructionClassifySer.update(to);
    }

    @Override
    public InstructionClassifyBO delete(String id) throws SerException {
        return instructionClassifySer.delete(id);
    }

    @Override
    public InstructionClassifyBO close(String id) throws SerException {
        return instructionClassifySer.close(id);
    }

    @Override
    public InstructionClassifyBO open(String id) throws SerException {
        return instructionClassifySer.open(id);
    }

    @Override
    public List<InstructionClassifyBO> maps(InstructionClassifyDTO dto) throws SerException {
        return instructionClassifySer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        InstructionClassifyDTO dto = new InstructionClassifyDTO();
        return instructionClassifySer.count(dto);
    }

    @Override
    public InstructionClassifyBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(instructionClassifySer.findById(id), DesignNumberInfoBO.class);
    }
}
