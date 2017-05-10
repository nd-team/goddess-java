package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.bo.DismissionEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.BeforeRemoveEmployee;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 减员前业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:45 ]
 * @Description: [ 减员前业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BeforeRemoveEmployeeSerImpl extends ServiceImpl<BeforeRemoveEmployee, BeforeRemoveEmployeeDTO> implements BeforeRemoveEmployeeSer {
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
        BeforeRemoveEmployee beforeRemoveEmployee = BeanTransform.copyProperties(to, BeforeRemoveEmployee.class, true);
        super.save(beforeRemoveEmployee);
        //TODO:发邮件
        MessageTO messageTO = new MessageTO("减员审核", "有减员名单需您审核");
        messageTO.setReceivers(new String[]{"XXX"});
        messageTO.setRangeType(RangeType.SPECIFIED);
        messageAPI.send(messageTO);
        return BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO exam(BeforeRemoveEmployeeTO to) throws SerException {
        if (userDetailAPI.findByUserId(userAPI.currentUser().getId()).getDepartmentName().equals("总经办")) {
            BeforeRemoveEmployee beforeRemoveEmployee = super.findById(to.getId());
            beforeRemoveEmployee.setIs_remove(to.getIs_remove());
            super.update(beforeRemoveEmployee);
            if (beforeRemoveEmployee.getIs_remove()) {
                RemoveEmployeeTO removeEmployeeTO = new RemoveEmployeeTO();
                BeanUtils.copyProperties(beforeRemoveEmployee, removeEmployeeTO);
                removeEmployeeSer.save(removeEmployeeTO);
            }
            return BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
        }
        return null;
    }

    @Override
    public List<BeforeRemoveEmployeeBO> find(BeforeRemoveEmployeeDTO dto) throws SerException {
        List<BeforeRemoveEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BeforeRemoveEmployeeBO.class);
    }

    @Override
    public BeforeRemoveEmployeeBO findByID(String id) throws SerException {
        BeforeRemoveEmployee beforeRemoveEmployee = super.findById(id);
        return BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<DismissionEmployeeBO> all() throws SerException {
        List<DimissionInfoBO> list = dimissionInfoAPI.all();
        List<DismissionEmployeeBO> boList = new ArrayList<DismissionEmployeeBO>();
        for (DimissionInfoBO d : list) {
            DismissionEmployeeBO bo = new DismissionEmployeeBO();
            bo.setName(d.getUsername());
            bo.setDimissionId(d.getId());
            bo.setEndTime(d.getDimissionDate());
            boList.add(bo);
        }
        return boList;
    }
}