package com.bjike.goddess.version.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.version.bo.AnswerBO;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.service.AnswerSer;
import com.bjike.goddess.version.to.AnswerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:10 ]
 * @Description: [ 答案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("answerApiImpl")
public class AnswerApiImpl implements AnswerAPI {
    @Autowired
    private AnswerSer answerSer;

    @Override
    public List<AnswerBO> list(AnswerDTO dto) throws SerException {
        return answerSer.list(dto);
    }

    @Override
    public AnswerBO save(AnswerTO to) throws SerException {
        return answerSer.save(to);
    }

    @Override
    public void edit(AnswerTO to) throws SerException {
        answerSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        answerSer.delete(id);
    }

    @Override
    public AnswerBO findByID(String id) throws SerException {
        return answerSer.findByID(id);
    }

    @Override
    public Long count(AnswerDTO dto) throws SerException {
        return answerSer.count(dto);
    }
}