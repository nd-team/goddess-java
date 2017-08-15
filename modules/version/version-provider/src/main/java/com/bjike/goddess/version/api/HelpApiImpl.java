package com.bjike.goddess.version.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.version.bo.HelpBO;
import com.bjike.goddess.version.bo.HelpBO1;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.service.HelpSer;
import com.bjike.goddess.version.to.AnswerTO;
import com.bjike.goddess.version.to.HelpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帮助与解答业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("helpApiImpl")
public class HelpApiImpl implements HelpAPI {
    @Autowired
    private HelpSer helpSer;

    @Override
    public List<HelpBO> list(HelpDTO dto) throws SerException {
        return helpSer.list(dto);
    }

    @Override
    public HelpBO save(HelpTO to) throws SerException {
        return helpSer.save(to);
    }

    @Override
    public void edit(HelpTO to) throws SerException {
        helpSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        helpSer.delete(id);
    }

    @Override
    public HelpBO findByID(String id) throws SerException {
        return helpSer.findByID(id);
    }

    @Override
    public Long count(HelpDTO dto) throws SerException {
        return helpSer.count(dto);
    }

    @Override
    public HelpBO1 findDetail(String id) throws SerException {
        return helpSer.findDetail(id);
    }

    @Override
    public void answer(String id, AnswerTO answerTO) throws SerException {
        helpSer.answer(id, answerTO);
    }
}