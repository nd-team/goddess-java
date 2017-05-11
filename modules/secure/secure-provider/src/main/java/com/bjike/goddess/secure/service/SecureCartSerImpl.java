package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.entity.EmployeeSecure;
import com.bjike.goddess.secure.entity.SecureCart;
import com.bjike.goddess.secure.to.SecureCartTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 社保卡基本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class SecureCartSerImpl extends ServiceImpl<SecureCart, SecureCartDTO> implements SecureCartSer {
    @Autowired
    private EmployeeSecureSer employeeSecureSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(SecureCartTO to) throws SerException {
        SecureCart secureCart = super.findById(to.getId());
        LocalDateTime a = secureCart.getCreateTime();
        LocalDateTime b = secureCart.getModifyTime();
        secureCart = BeanTransform.copyProperties(to, SecureCart.class);
        secureCart.setCreateTime(a);
        secureCart.setModifyTime(b);
        super.update(secureCart);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<SecureCartBO> list(SecureCartDTO dto) throws SerException {
        Set<EmployeeSecure> set = allEmployeeSecures();
        List<EmployeeSecure> list = employeeSecureSer.findAll();
        for (EmployeeSecure employeeSecure : list) {
            if (set.size() != 0) {
                for (EmployeeSecure s : set) {
                    if ((employeeSecure.getStatus() != null) && (!employeeSecure.getStatus().equals("")) && (!employeeSecure.getStatus().equals("已减员成功"))) {
                        if (!(s.getId().equals(employeeSecure.getId()))) {
                            SecureCart secureCart = new SecureCart();
                            secureCart.setEmployeeSecure(employeeSecure);
                            super.save(secureCart);
                        }
                    }
                }
            } else {
                if ((employeeSecure.getStatus() != null) && (!employeeSecure.getStatus().equals("")) && (!employeeSecure.getStatus().equals("已减员成功"))) {
                    SecureCart secureCart = new SecureCart();
                    secureCart.setEmployeeSecure(employeeSecure);
                    super.save(secureCart);
                }
            }
        }
        Set<String> ss = allEmployeeSecureIds();
        List<EmployeeSecure> l = null;
        for (String s : ss) {
            String sql = "SELECT id,name,employeeNum,arrival from secure_employeesecure\n" +
                    "where id='" + s + "'";
            String[] fields = new String[]{"id", "name", "employeeNum", "arrival"};
            l = super.findBySql(sql, EmployeeSecure.class, fields);
        }
        if (l != null) {
            for (EmployeeSecure e : l) {
                for (String s : ss) {
                    if (e.getId().equals(s)) {
                        SecureCart secureCart = super.findById(find(new String[]{s}).getId());
                        secureCart.setName(e.getName());
                        secureCart.setEmployeeId(e.getEmployeeNum());
                        secureCart.setArrival(e.getArrival());
                        super.update(secureCart);
                    }
                }
            }
        }
        List<SecureCart> list1 = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list1, SecureCartBO.class);
    }

    @Override
    public SecureCartBO findByID(String id) throws SerException {
        SecureCart secureCart = super.findById(id);
        return BeanTransform.copyProperties(secureCart, SecureCartBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 查找所有员工社保信息id
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> allEmployeeSecureIds() throws SerException {
        Set<EmployeeSecure> s = allEmployeeSecures();
        Set<String> set = new HashSet<String>();
        for (EmployeeSecure e : s) {
            set.add(e.getId());
        }
        return set;
    }

    /**
     * 查找所有员工社保信息
     *
     * @return class String
     * @throws SerException
     */
    private Set<EmployeeSecure> allEmployeeSecures() throws SerException {
        List<SecureCart> list = super.findAll();
        Set<EmployeeSecure> set = new HashSet<EmployeeSecure>();
        for (SecureCart secureCart : list) {
            set.add(secureCart.getEmployeeSecure());
        }
        return set;
    }

    /**
     * 通过员工社保信息id查找社保卡信息
     *
     * @param employeeSecureIds 员工社保信息id数组
     * @return class SecureCart
     * @throws SerException
     */
    private SecureCart find(String[] employeeSecureIds) throws SerException {
        List<SecureCart> list = null;
        for (String id : employeeSecureIds) {
            String sql = "SELECT id,name from secure_securecart\n" +
                    "where employeeSecure_id='" + id + "'";
            list = super.findBySql(sql, SecureCart.class, new String[]{"id", "name"});
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }
}