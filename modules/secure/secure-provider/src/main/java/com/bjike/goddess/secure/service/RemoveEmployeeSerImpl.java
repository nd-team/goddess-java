package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 减员名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class RemoveEmployeeSerImpl extends ServiceImpl<RemoveEmployee, RemoveEmployeeDTO> implements RemoveEmployeeSer {
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO save(RemoveEmployeeTO to) throws SerException {
        RemoveEmployee removeEmployee = BeanTransform.copyProperties(to, RemoveEmployee.class, true);
        super.save(removeEmployee);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO edit(RemoveEmployeeTO to) throws SerException {
        RemoveEmployee removeEmployee = super.findById(to.getId());
        LocalDateTime a = removeEmployee.getCreateTime();
        LocalDateTime b = removeEmployee.getModifyTime();
        removeEmployee = BeanTransform.copyProperties(to, RemoveEmployee.class);
        removeEmployee.setCreateTime(a);
        removeEmployee.setModifyTime(b);
        super.update(removeEmployee);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    public List<RemoveEmployeeBO> find(RemoveEmployeeDTO dto) throws SerException {
        List<RemoveEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, RemoveEmployeeBO.class);
    }

    @Override
    public RemoveEmployeeBO findByID(String id) throws SerException {
        RemoveEmployee removeEmployee = super.findById(id);
        return BeanTransform.copyProperties(removeEmployee, RemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RemoveEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public RemoveEmployeeBO findByNameAndId(String[] removeName, String[] employeeId) throws SerException {
        List<String> names = Arrays.asList(removeName);
        List<String> emploeeIds = Arrays.asList(employeeId);
        List<RemoveEmployeeBO> list = null;
        for (int i = 0; i < names.size() && i < emploeeIds.size(); i++) {
            String[] fields = new String[]{"countCompany", "countCity", "removeType", "company", "removeCity", "quantityName", "secureTime", "removeCount", "description"};
            String sql = "select countCompany,countCity,removeType,company,removeCity,quantityName,secureTime,removeCount,description from " +
                    "secure_removeemployee where removeName='" + names.get(i) + "' and employeeId='" + emploeeIds.get(i) + "'";
            list = this.findBySql(sql, RemoveEmployeeBO.class, fields);
        }
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void confirm(RemoveEmployeeTO to) throws SerException {
        if (userDetailAPI.findByUserId(userAPI.currentUser().getId()).getDepartmentName().equals("运营商务部")) {
            RemoveEmployee removeEmployee = super.findById(to.getId());
            to.setConfirmRemove(true);
            super.update(removeEmployee);
            EmployeeSecure employeeSecure = new EmployeeSecure();
            BeanUtils.copyProperties(removeEmployee, employeeSecure);
            employeeSecure.setName(removeEmployee.getRemoveName());
            employeeSecure.setEmployeeNum(removeEmployee.getEmployeeId());
            employeeSecure.setDescription(removeEmployee.getDescription());
            employeeSecure.setBeforeCity(removeEmployee.getRemoveCity());
            employeeSecure.setStatus("已减员成功");
            employeeSecureSer.save(employeeSecure);
            //        String[] employeeIds=new String[]{to.getEmployeeId()};
            //        List<EmployeeSecureBO> list=employeeSecureSer.findBySql(employeeIds);
            //        for (EmployeeSecureBO bo:list){
            //            EmployeeSecureTO employeeSecureTO=new EmployeeSecureTO();
            //            BeanUtils.copyProperties(bo,employeeSecureTO);
            //            employeeSecureSer.remove(employeeSecureTO);
            //        }
        }
    }

    @Override
    public List<RemoveEmployeeBO> findALL() throws SerException {
        List<RemoveEmployee> list = super.findAll();
        return BeanTransform.copyProperties(list, RemoveEmployeeBO.class);
    }
}