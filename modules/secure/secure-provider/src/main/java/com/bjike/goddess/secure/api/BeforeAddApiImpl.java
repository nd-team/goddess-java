package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.service.BeforeAddSer;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 增员前业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("beforeAddApiImpl")
public class BeforeAddApiImpl implements BeforeAddAPI {
    @Autowired
    private BeforeAddSer beforeAddSer;

    @Override
    public BeforeAddBO save(BeforeAddTO to) throws SerException {
        return beforeAddSer.save(to);
    }

    @Override
    public BeforeAddBO delete(String id) throws SerException {
        return beforeAddSer.delete(id);
    }

    @Override
    public List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        return beforeAddSer.find(dto);
    }

    @Override
    public BeforeAddBO findByID(String id) throws SerException {
        return beforeAddSer.findByID(id);
    }

    @Override
    public void send() throws SerException {
        beforeAddSer.send();
    }

//    @Override
//    public void quartz() throws SerException {
//        beforeAddSer.quartz();
//    }

    @Override
    public BeforeAddBO complete(BeforeAddTO to) throws SerException {
        return beforeAddSer.complete(to);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return beforeAddSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return beforeAddSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(AddEmployeeDTO dto, String id) throws SerException {
        beforeAddSer.add(dto, id);
    }

    @Override
    public Long count(BeforeAddDTO dto) throws SerException {
        return beforeAddSer.count(dto);
    }

    @Override
    public void edit(BeforeAddTO to) throws SerException {
        beforeAddSer.edit(to);
    }
}