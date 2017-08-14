package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.bo.QuestionBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.service.QuestionSer;
import com.bjike.goddess.system.to.AuswerTO;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("questionApiImpl")
public class QuestionApiImpl implements QuestionAPI {
    @Autowired
    private QuestionSer questionSer;
    @Override
    public Long count(QuestionDTO dto) throws SerException {
        return questionSer.count(dto);
    }

    @Override
    public QuestionBO getOne(String id) throws SerException {
        return questionSer.getOne(id);
    }

    @Override
    public List<QuestionBO> list(QuestionDTO dto) throws SerException {
        return questionSer.list(dto);
    }

    @Override
    public QuestionBO insert(QuestionTO to) throws SerException {
        return questionSer.insert(to);
    }

    @Override
    public QuestionBO edit(QuestionTO to) throws SerException {
        return questionSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        questionSer.remove(id);
    }
    @Override
    public String detail(String id) throws SerException{
        return questionSer.detail(id);
    }

    @Override
    public void answer(String id, AuswerTO auswerTO) throws SerException{
        questionSer.answer(id,auswerTO);
    }
}