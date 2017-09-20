package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.to.ProblemTypeTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
