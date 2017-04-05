package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.CommerceMemberBO;
import com.bjike.goddess.contacts.dto.CommerceMemberDTO;
import com.bjike.goddess.contacts.service.CommerceMemberSer;
import com.bjike.goddess.contacts.to.CommerceMemberTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务会员卡业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:38 ]
 * @Description: [ 商务会员卡业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("commerceMemberApiImpl")
public class CommerceMemberApiImpl implements CommerceMemberAPI {

    @Autowired
    private CommerceMemberSer commerceMemberSer;

    @Override
    public CommerceMemberBO save(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.save(to);
    }

    @Override
    public CommerceMemberBO update(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.update(to);
    }

    @Override
    public CommerceMemberBO delete(CommerceMemberTO to) throws SerException {
        return commerceMemberSer.delete(to);
    }

    @Override
    public List<CommerceMemberBO> maps(CommerceMemberDTO dto) throws SerException {
        return commerceMemberSer.maps(dto);
    }
}