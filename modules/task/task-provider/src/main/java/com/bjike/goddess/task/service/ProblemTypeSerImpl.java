package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.ProblemTypeBO;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.to.ProblemTypeTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 问题业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class ProblemTypeSerImpl extends ServiceImpl<ProblemType, ProblemTypeDTO> implements ProblemTypeSer {
    @Transactional
    @Override
    public void edit(ProblemTypeTO to) throws SerException {
        ProblemType problemType = super.findById(to.getId());
        if (null != problemType) {
            BeanTransform.copyProperties(to, problemType, "id");
        }
        super.update(problemType);
    }

    @Override
    public List<ProblemTypeBO> types() throws SerException {
        ProblemTypeDTO dto = new ProblemTypeDTO();
        dto.getConditions().add(Restrict.eq("enable", Boolean.TRUE));
        List<ProblemType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list,ProblemTypeBO.class);
    }
}
