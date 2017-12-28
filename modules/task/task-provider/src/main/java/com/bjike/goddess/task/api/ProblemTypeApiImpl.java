package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.ProblemTypeBO;
import com.bjike.goddess.task.dto.ProblemDTO;
import com.bjike.goddess.task.dto.ProblemTypeDTO;
import com.bjike.goddess.task.entity.Problem;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.service.ProblemSer;
import com.bjike.goddess.task.service.ProblemTypeSer;
import com.bjike.goddess.task.to.ProblemTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@Service("problemTypeApiImpl")
public class ProblemTypeApiImpl implements ProblemTypeAPI {
    @Autowired
    private ProblemTypeSer problemTypeSer;
    @Autowired
    private ProblemSer problemSer;

    @Override
    public void add(ProblemTypeTO to) throws SerException {
        ProblemType type = BeanTransform.copyProperties(to, ProblemType.class);
        if (null == type.getEnable()) {
            type.setEnable(false);
        }
        problemTypeSer.save(type);
    }

    @Override
    public List<ProblemTypeBO> list(ProblemTypeDTO dto) throws SerException {
        if (null != dto.getEnable()) {
            dto.getConditions().add(Restrict.eq("enable", dto.getEnable()));
        }
        List<ProblemType> problemTypes = problemTypeSer.findByCis(dto);
        return BeanTransform.copyProperties(problemTypes, ProblemTypeBO.class);
    }

    @Override
    public ProblemTypeBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(problemTypeSer.findById(id), ProblemTypeBO.class);
    }

    @Override
    public void edit(ProblemTypeTO to) throws SerException {
        problemTypeSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        ProblemDTO dto=new ProblemDTO();
        dto.getConditions().add(Restrict.eq("type.id",id));
        List<Problem> problems=problemSer.findByCis(dto);
        problemSer.remove(problems);
        problemTypeSer.remove(id);
    }

    @Override
    public void enable(String id, Boolean enable) throws SerException {
        ProblemType type = problemTypeSer.findById(id);
        if (null != type) {
            type.setEnable(enable);
            problemTypeSer.update(type);
        } else {
            throw new SerException("问题类型不存在");
        }
    }

    @Override
    public List<ProblemTypeBO> types() throws SerException {
        return problemTypeSer.types();
    }

    @Override
    public Long count(ProblemTypeDTO dto) throws SerException {
        return problemTypeSer.count(dto);
    }
}
