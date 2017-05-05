package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
<<<<<<< HEAD
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
=======
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.entity.Abandon;
import com.bjike.goddess.secure.entity.AddEmployee;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.RemoveEmployee;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import org.springframework.beans.BeanUtils;
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
import java.util.Arrays;
import java.util.List;

/**
 * 员工社保基本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class EmployeeSecureSerImpl extends ServiceImpl<EmployeeSecure, EmployeeSecureDTO> implements EmployeeSecureSer {
    @Autowired
    private AddEmployeeSer addEmployeeSer;
    @Autowired
    private AbandonSer abandonSer;
    @Autowired
    private RemoveEmployeeSer removeEmployeeSer;

    @Override
    @Transactional
    public EmployeeSecureBO save(EmployeeSecureTO to) throws SerException {
<<<<<<< HEAD
        EmployeeSecure employeeSecure = BeanTransform.copyProperties(to, EmployeeSecure.class, true);
        super.save(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
=======
        EmployeeSecure employeeSecure=BeanTransform.copyProperties(to,EmployeeSecure.class,true);
        super.save(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure,EmployeeSecureBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
//        List<AddEmployeeBO> list=addEmployeeSer.findALL();
//        for(AddEmployeeBO bo:list){
//            EmployeeSecure employeeSecure=new EmployeeSecure();
//            BeanUtils.copyProperties(bo,employeeSecure);
//            employeeSecure.setGroup1(bo.getGroup());
//            employeeSecure.setStatus("购买中");
//            super.save(employeeSecure);
//        }
////        List<AbandonBO> lis1=abandonSer.findALL();
////        for(AbandonBO bo:lis1){
////            EmployeeSecure employeeSecure=new EmployeeSecure();
////            BeanUtils.copyProperties(bo,employeeSecure);
////            employeeSecure.setStatus("放弃购买");
////            super.save(employeeSecure);
////        }
//        List<RemoveEmployeeBO> list2=removeEmployeeSer.findALL();
//        for (RemoveEmployeeBO bo:list2){
//            if(bo.isConfirm_remove()){
////                RemoveEmployee removeEmployee=new RemoveEmployee();
////                removeEmployee=BeanTransform.copyProperties(bo,RemoveEmployee.class,true);
////                String id=removeEmployee.getEmployeeId();
////                List<EmployeeSecureBO> l=this.findBySql(new String[]{id});
////                for(EmployeeSecureBO bo1:l){
////                    EmployeeSecureTO employeeSecureTO=new EmployeeSecureTO();
////                    BeanUtils.copyProperties(bo1,employeeSecureTO);
////                    this.remove(employeeSecureTO);
////                    EmployeeSecure employeeSecure=BeanTransform.copyProperties(employeeSecureTO,EmployeeSecure.class,true);
////                    super.save(employeeSecure);
////                }
//                EmployeeSecure employeeSecure=new EmployeeSecure();
//                BeanUtils.copyProperties(bo,employeeSecure);
//                employeeSecure.setName(bo.getRemoveName());
//                employeeSecure.setEmployeeNum(bo.getEmployeeId());
//                employeeSecure.setStatus("已减员成功");
//                super.save(employeeSecure);
//            }

    }

    @Override
    @Transactional
    public EmployeeSecureBO edit(EmployeeSecureTO to) throws SerException {
<<<<<<< HEAD
        EmployeeSecure employeeSecure = super.findById(to.getId());
        LocalDateTime a = employeeSecure.getCreateTime();
        LocalDateTime b = employeeSecure.getModifyTime();
        employeeSecure = BeanTransform.copyProperties(to, EmployeeSecure.class);
        employeeSecure.setCreateTime(a);
        employeeSecure.setModifyTime(b);
        super.update(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
=======
        EmployeeSecure employeeSecure= super.findById(to.getId());
        employeeSecure=BeanTransform.copyProperties(to,EmployeeSecure.class);
        super.update(employeeSecure);
        return BeanTransform.copyProperties(employeeSecure,EmployeeSecureBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public void remove(EmployeeSecureTO to) throws SerException {
//        EmployeeSecure employeeSecure= super.findById(to.getId());
//        employeeSecure.setStatus("已减员成功");
//        super.update(employeeSecure);
    }

    @Override
    public List<EmployeeSecureBO> find(EmployeeSecureDTO dto) throws SerException {
<<<<<<< HEAD
        List<EmployeeSecure> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, EmployeeSecureBO.class);
=======
        List<EmployeeSecure> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,EmployeeSecureBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    public EmployeeSecureBO findByID(String id) throws SerException {
<<<<<<< HEAD
        EmployeeSecure employeeSecure = super.findById(id);
        return BeanTransform.copyProperties(employeeSecure, EmployeeSecureBO.class);
=======
        EmployeeSecure employeeSecure=super.findById(id);
        return BeanTransform.copyProperties(employeeSecure,EmployeeSecureBO.class);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
    }

    @Override
    @Transactional
    public EmployeeSecureBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<EmployeeSecureBO> findBySql(String[] employeeId) throws SerException {
<<<<<<< HEAD
        List<String> emploeeIds = Arrays.asList(employeeId);
        List<EmployeeSecureBO> list = null;
        for (int i = 0; i < emploeeIds.size(); i++) {
            String[] fields = new String[]{"id", "status"};
            String sql = "select id,status from secure_employee_secure where employeeNum='" + emploeeIds.get(i) + "'";
            list = this.findBySql(sql, EmployeeSecureBO.class, fields);
=======
        List<String> emploeeIds=Arrays.asList(employeeId);
        List<EmployeeSecureBO> list=null;
        for(int i=0;i<emploeeIds.size();i++) {
            String[] fields=new String[]{"id","status"};
            String sql="select id,status from secure_employee_secure where employeeNum='"+emploeeIds.get(i)+"'";
            list=this.findBySql(sql,EmployeeSecureBO.class,fields);
>>>>>>> 3ed38c3bf2ddd8e993ac3765a483612fd6e8516e
        }
        return list;
    }
}