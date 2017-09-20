package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.service.ProblemSer;
import com.bjike.goddess.task.to.AcceptTO;
import com.bjike.goddess.task.to.ProblemTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(ProblemTO to) throws SerException {
        problemSer.add(to);
    }

    @Override
    public void accept(AcceptTO to) throws SerException {
        problemSer.accept(to);

    }
}
