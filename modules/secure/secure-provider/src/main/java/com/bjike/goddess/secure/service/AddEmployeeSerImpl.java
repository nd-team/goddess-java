package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.AddEmployee;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.BuyTO;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 社保增员信息名单业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AddEmployeeSerImpl extends ServiceImpl<AddEmployee, AddEmployeeDTO> implements AddEmployeeSer {
    @Autowired
    private BeforeAddSer beforeAddSer;
    @Autowired
    private EmployeeSecureSer employeeSecureSer;
    @Autowired
    private BuySer buySer;

    @Override
    public List<AddEmployeeBO> find(AddEmployeeDTO dto) throws SerException {
        List<AddEmployee> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AddEmployeeBO.class);
    }

    @Override
    public AddEmployeeBO findByID(String id) throws SerException {
        AddEmployee addEmployee = super.findById(id);
        return BeanTransform.copyProperties(addEmployee, AddEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(AddEmployeeTO to) throws SerException {
        AddEmployee addEmployee = super.findById(to.getId());
        if (addEmployee == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = addEmployee.getCreateTime();
        addEmployee = BeanTransform.copyProperties(to, AddEmployee.class, true);
        addEmployee.setCreateTime(a);
        LocalDate time = DateUtil.parseDate(to.getSecureTime());
        addEmployee.setSecureTime(time);
        addEmployee.setMonth(time.getMonthValue());
        addEmployee.setYear(time.getYear());
        addEmployee.setModifyTime(LocalDateTime.now());
        super.update(addEmployee);
        BeforeAdd beforeAdd = findByName(addEmployee.getName());
        if (beforeAdd != null) {
            BeforeAddBO beforeAddBO = beforeAddSer.findByID(beforeAdd.getId());
            //todo:增员前参考信息&增员信息基本内容,通知运营商务部、总经办给予意见
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AddEmployeeBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AddEmployeeBO save(AddEmployeeTO to) throws SerException {
        AddEmployee addEmployee = BeanTransform.copyProperties(to, AddEmployee.class, true);
        if (to.getSecureTime() == null) {
            addEmployee.setSecureTime(LocalDate.now());
        }
        addEmployee.setMonth(addEmployee.getSecureTime().getMonthValue());
        addEmployee.setYear(addEmployee.getSecureTime().getYear());
        super.save(addEmployee);
        BeforeAdd beforeAdd = findByName(addEmployee.getName());
        if (beforeAdd != null) {
            BeforeAddBO beforeAddBO = beforeAddSer.findByID(beforeAdd.getId());
            //todo:增员前参考信息&增员信息基本内容,通知运营商务部、总经办给予意见
        }
        return BeanTransform.copyProperties(addEmployee, AddEmployeeBO.class);
    }

    @Override
    public List<AddEmployeeBO> findALL() throws SerException {
        List<AddEmployee> list = super.findAll();
        return BeanTransform.copyProperties(list, AddEmployeeBO.class);
    }

    @Override
    public Long count(AddEmployeeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //运营商务部审核
    public void commerceAudit(AddEmployeeTO to) throws SerException {
        AddEmployee entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setBusinessAdvice(to.getBusinessAdvice());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        //todo:发送审核意见至总经办
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //总经办确认新增
    public void managerConfirmAdd(String id) throws SerException {
        AddEmployee entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        //todo:系统邮件通知社保管理负责人可新增人员名单
        EmployeeSecureTO employeeSecureTO = new EmployeeSecureTO();
        BeanUtils.copyProperties(entity, employeeSecureTO);
        LocalDate beforeTime = entity.getBeforeTime();
        LocalDate officialTime = entity.getOfficialTime();
        LocalDate secureTime = entity.getSecureTime();
        LocalDate startTime = entity.getStartTime();
        try {
            if (beforeTime != null) {
                employeeSecureTO.setBeforeTime(DateUtil.dateToString(beforeTime));
            }
            employeeSecureTO.setOfficialTime(DateUtil.dateToString(officialTime));
            employeeSecureTO.setSecureTime(DateUtil.dateToString(secureTime));
            employeeSecureTO.setStartTime(DateUtil.dateToString(startTime));
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        employeeSecureTO.setManagerAdvice("通过");
        employeeSecureSer.save(employeeSecureTO);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //社保管理负责人确认增员
    public void confirmAdd(String id) throws SerException {
        AddEmployee entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        BuyTO buyTO = new BuyTO();
        BeanUtils.copyProperties(entity, buyTO);
        if ((entity.getName() != null) && (entity.getEmployeeNum() != null) && (StringUtils.isNotBlank(entity.getEmployeeNum()))) {
            AddEmployee addEmployee = findByNameAndNum(entity.getName(), entity.getEmployeeNum());
            if (addEmployee != null) {
                buyTO.setCity(addEmployee.getSecureCity());
            }
        } else if ((entity.getEmployeeNum() == null) || (StringUtils.isBlank(entity.getEmployeeNum()))) {
            Attached attached = findByAttachedName(entity.getName());
            if (attached != null) {
                buyTO.setCity(attached.getAttachedArrival());
            }
        }
        buySer.save(buyTO);
        EmployeeSecureBO employeeSecureBO = employeeSecureSer.findBySql(entity.getName(), entity.getEmployeeNum());
        if (employeeSecureBO != null) {
            EmployeeSecureBO bo = employeeSecureSer.findByID(employeeSecureBO.getId());
            EmployeeSecureTO employeeSecureTO = new EmployeeSecureTO();
            BeanUtils.copyProperties(bo, employeeSecureTO);
            employeeSecureTO.setStatus("已增员成功");
            employeeSecureSer.edit(employeeSecureTO);
        }
        //todo:将本月所有社保所需表-购买社保人员名单、缴费比例明细发送运营商务部
        AddEmployeeDTO addEmployeeDTO = new AddEmployeeDTO();
        addEmployeeDTO.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
        addEmployeeDTO.getConditions().add(Restrict.eq("month", LocalDate.now().getMonthValue()));
        List<AddEmployee> list = super.findByCis(addEmployeeDTO);
        List<BuyBO> boList = new ArrayList<BuyBO>();
        for (AddEmployee a : list) {
            BuyDTO dto = new BuyDTO();
            dto.getConditions().add(Restrict.eq("name", a.getName()));
            List<BuyBO> buyBOs = buySer.findByDTO(dto);
            if (buyBOs != null && !buyBOs.isEmpty()) {
                boList.add(buyBOs.get(0));
            }
        }
        //boList为本月所有社保所需表-购买社保人员名单
    }

    /**
     * 通过姓名查找增员前信息
     *
     * @param name 姓名
     * @return
     * @throws SerException
     */
    private BeforeAdd findByName(String name) throws SerException {
        String[] names = new String[]{name};
        List<BeforeAdd> list = null;
        for (String s : names) {
            String sql = "SELECT id FROM " +
                    "secure_beforeadd " +
                    "WHERE name='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, BeforeAdd.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过姓名和员工编号查找增员信息
     *
     * @param name 姓名
     * @param num  员工编号
     * @return
     * @throws SerException
     */
    private AddEmployee findByNameAndNum(String name, String num) throws SerException {
        String[] names = new String[]{name};
        String[] nums = new String[]{num};
        List<AddEmployee> list = null;
        for (int i = 0; i < names.length; i++) {
            String sql = "SELECT secureCity FROM secure_addemployee " +
                    "WHERE name='" + names[i] + "' AND employeeNum='" + nums[i] + "'";
            String[] fileds = new String[]{"secureCity"};
            list = super.findBySql(sql, AddEmployee.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过挂靠姓名查找挂靠信息
     *
     * @param name 挂靠姓名
     * @return
     * @throws SerException
     */
    private Attached findByAttachedName(String name) throws SerException {
        String[] names = new String[]{name};
        List<Attached> list = null;
        for (String s : names) {
            String sql = "SELECT attachedArrival from secure_attached\n" +
                    "WHERE attachedName='" + s + "'";
            String[] fileds = new String[]{"attachedArrival"};
            list = super.findBySql(sql, Attached.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}