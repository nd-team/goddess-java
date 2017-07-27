package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.service.AbandonSer;
import com.bjike.goddess.secure.to.AbandonTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 放弃购买名单业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("abandonApiImpl")
public class AbandonApiImpl implements AbandonAPI {
    @Autowired
    private AbandonSer abandonSer;

    @Override
    public AbandonBO save(AbandonTO to) throws SerException {
        return abandonSer.save(to);
    }

    @Override
    public AbandonBO delete(String id) throws SerException {
        return abandonSer.delete(id);
    }

    @Override
    public AbandonBO edit(AbandonTO to) throws SerException {
        return abandonSer.edit(to);
    }

    @Override
    public List<AbandonBO> find(AbandonDTO dto) throws SerException {
        return abandonSer.find(dto);
    }

    @Override
    public AbandonBO findByID(String id) throws SerException {
        return abandonSer.findByID(id);
    }

    @Override
    public List<AbandonBO> findALL() throws SerException {
        return abandonSer.findALL();
    }

    @Override
    public Long count(AbandonDTO dto) throws SerException {
        return abandonSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return abandonSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return abandonSer.guidePermission(guidePermissionTO);
    }
}