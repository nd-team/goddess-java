package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.service.SecureCartSer;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCartTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社保卡基本信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("secureCartApiImpl")
public class SecureCartApiImpl implements SecureCartAPI {
    @Autowired
    private SecureCartSer secureCartSer;

    @Override
    public void edit(SecureCartTO to) throws SerException {
        secureCartSer.edit(to);
    }

    @Override
    public List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
        return secureCartSer.list(dto);
    }

    @Override
    public SecureCartBO findByID(String id) throws SerException {
        return secureCartSer.findByID(id);
    }

    @Override
    public void delete(String id) throws SerException {
        secureCartSer.delete(id);
    }

    @Override
    public void send() throws SerException {
        secureCartSer.send();
    }

    @Override
    public Long count(SecureCartDTO dto) throws SerException {
        return secureCartSer.count(dto);
    }

//    @Override
//    public void quartz() throws SerException {
//        secureCartSer.quartz();
//    }

    @Override
    public SecureCartBO save(SecureCartTO to) throws SerException {
        return secureCartSer.save(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return secureCartSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return secureCartSer.guidePermission(guidePermissionTO);
    }
}