package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.ProblemResultBO;
import com.bjike.goddess.feedback.dto.ProblemResultDTO;
import com.bjike.goddess.feedback.entity.ProblemResult;
import com.bjike.goddess.feedback.service.ProblemResultSer;
import com.bjike.goddess.feedback.to.ProblemResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题处理结果业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("problemResultApiImpl")
public class ProblemResultApiImpl implements ProblemResultAPI {
    @Autowired
    private ProblemResultSer problemResultSer;

    @Override
    public Long count(ProblemResultDTO dto) throws SerException {
        return problemResultSer.count(dto);
    }
    @Override
    public ProblemResultBO getOne(String id) throws SerException {
        return problemResultSer.getOne(id);
    }
    @Override
    public List<ProblemResultBO> list(ProblemResultDTO dto) throws SerException {
        return problemResultSer.list(dto);
    }

    @Override
    public ProblemResultBO partyConfirm(ProblemResultTO to) throws SerException {
        return problemResultSer.partyConfirm(to);
    }

    @Override
    public ProblemResultBO coordinate(ProblemResultTO to) throws SerException {
        return problemResultSer.coordinate(to);
    }
    @Override
    public ProblemResultBO result(ProblemResultTO to) throws SerException {
        return problemResultSer.result(to);
    }
}