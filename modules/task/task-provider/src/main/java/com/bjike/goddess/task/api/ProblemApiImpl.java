package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.ProblemBO;
import com.bjike.goddess.task.dto.ProblemDTO;
import com.bjike.goddess.task.service.ProblemSer;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemEditTO;
import com.bjike.goddess.task.to.ProblemTO;
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

@Service("problemApiImpl")
public class ProblemApiImpl implements ProblemAPI {
    @Autowired
    private ProblemSer problemSer;

    @Override
    public List<ProblemBO> list(ProblemDTO dto) throws SerException {
        return problemSer.list(dto);
    }

    @Override
    public Long count(ProblemDTO dto) throws SerException {
        return problemSer.count(dto);
    }

    @Override
    public void edit(ProblemEditTO to) throws SerException {
        problemSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        problemSer.remove(id);
    }

    @Override
    public void add(ProblemTO to) throws SerException {
        problemSer.add(to);
    }

    @Override
    public void accept(AcceptTO to) throws SerException {
        problemSer.accept(to);

    }

    @Override
    public ProblemBO findByID(String id) throws SerException {
        return problemSer.findByID(id);
    }
}
