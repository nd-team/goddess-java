package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        removeEmployee = BeanTransform.copyProperties(to, RemoveEmployee.class);
        removeEmployee.setCreateTime(a);
        removeEmployee.setModifyTime(LocalDateTime.now());
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
    public RemoveEmployeeBO findByNameAndId(RemoveEmployeeTO to) throws SerException {
        String removeName=to.getRemoveName();
        String employeeId=to.getEmployeeId();
        String[] names = new String[]{removeName};
        List<RemoveEmployeeBO> list = null;
        if ((removeName != null) && (employeeId != null) && (StringUtils.isNotBlank(employeeId))) {
            String[] ids = new String[]{employeeId};
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "removeName", "employeeId", "countCompany", "countCity", "removeType", "company", "removeCity", "quantityName", "secureTime", "removeCount", "description", "confirmRemove"};
                String sql = "select id,removeName,employeeId,countCompany,countCity,removeType,company,removeCity,quantityName,secureTime,removeCount,description,confirmRemove from " +
                        "secure_removeemployee where removeName='" + names[i] + "' and employeeId='" + ids[i] + "'";
                list = this.findBySql(sql, RemoveEmployeeBO.class, fields);
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                String[] fields = new String[]{"id", "removeName", "countCompany", "countCity", "removeType", "company", "removeCity", "quantityName", "secureTime", "removeCount", "description", "confirmRemove"};
                String sql = "select id,removeName,countCompany,countCity,removeType,company,removeCity,quantityName,secureTime,removeCount,description,confirmRemove from " +
                        "secure_removeemployee where removeName='" + names[i] + "'";
                list = this.findBySql(sql, RemoveEmployeeBO.class, fields);
            }
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void confirmRemove(String id) throws SerException {
        RemoveEmployee removeEmployee = super.findById(id);
        removeEmployee.setConfirmRemove(true);
        removeEmployee.setModifyTime(LocalDateTime.now());
        super.update(removeEmployee);
        //todo:发邮件通知运营商务部
        EmployeeSecure entity = findByNumAndName(removeEmployee.getEmployeeId(), removeEmployee.getRemoveName());
        if (entity != null) {
//            EmployeeSecure employeeSecure = new EmployeeSecure();
//            BeanUtils.copyProperties(removeEmployee, employeeSecure);
//            employeeSecure.setName(removeEmployee.getRemoveName());
//            employeeSecure.setEmployeeNum(removeEmployee.getEmployeeId());
//            employeeSecure.setStatus("已减员成功");
//            employeeSecureSer.save(employeeSecure);
            EmployeeSecureBO bo = employeeSecureSer.findByID(entity.getId());
            EmployeeSecureTO employeeSecureTO = BeanTransform.copyProperties(bo, EmployeeSecureTO.class);
            employeeSecureTO.setStatus("已减员成功");
            employeeSecureSer.edit(employeeSecureTO);
        }
    }

    @Override
    public List<RemoveEmployeeBO> findALL() throws SerException {
        List<RemoveEmployee> list = super.findAll();
        return BeanTransform.copyProperties(list, RemoveEmployeeBO.class);
    }

    @Override
    public Long count(RemoveEmployeeDTO dto) throws SerException {
        return super.count(dto);
    }

    /**
     * 通过员工编号和姓名查找员工社保信息
     *
     * @param employeeNum 员工编号
     * @param name        姓名
     * @return
     * @throws SerException
     */
    private EmployeeSecure findByNumAndName(String employeeNum, String name) throws SerException {
        String[] names = new String[]{name};
        List<EmployeeSecure> list = null;
        if ((employeeNum != null) && (name != null) && (StringUtils.isNotBlank(employeeNum))) {
            String[] nums = new String[]{employeeNum};
            for (int i = 0; i < names.length; i++) {
                String sql = "select id from secure_employeesecure " +
                        "where employeeNum='" + nums[i] + "' AND name='" + names[i] + "'";
                String[] fileds = new String[]{"id"};
                list = super.findBySql(sql, EmployeeSecure.class, fileds);
            }
        } else if ((employeeNum == null) || (StringUtils.isBlank(employeeNum))) {
            for (int i = 0; i < names.length; i++) {
                String sql = "select id from secure_employeesecure " +
                        "where name='" + names[i] + "'";
                String[] fileds = new String[]{"id"};
                list = super.findBySql(sql, EmployeeSecure.class, fileds);
            }
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}