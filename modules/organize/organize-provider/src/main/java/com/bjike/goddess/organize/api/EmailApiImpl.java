package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.EmailBO;
import com.bjike.goddess.organize.dto.EmailDTO;
import com.bjike.goddess.organize.service.EmailSer;
import com.bjike.goddess.organize.to.EmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
@Service("emailApiImpl")
public class EmailApiImpl implements EmailAPI{
    @Autowired
    private EmailSer emailSer;

    @Override
    public void add(EmailTO to) throws SerException {
        emailSer.add(to);
    }

    @Override
    public void edit(EmailTO to) throws SerException {
        emailSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        emailSer.delete(id);
    }

    @Override
    public List<EmailBO> list(EmailDTO dto) throws SerException {
        return emailSer.list(dto);
    }

    @Override
    public EmailBO one(String id) throws SerException {
        return emailSer.one(id);
    }

    @Override
    public void send() throws SerException {
        emailSer.send();
    }

    @Override
    public Long count(EmailDTO dto) throws SerException {
        return emailSer.count(dto);
    }
}
