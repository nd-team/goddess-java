package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.dimission.bo.DimissionInfoBO;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.enums.HandleStatus;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.quartz.api.ScheduleJobAPI;
import com.bjike.goddess.quartz.api.ScheduleJobGroupAPI;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.entity.BeforeRemoveEmployee;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private ScheduleJobAPI scheduleJobAPI;
    @Autowired
    private ScheduleJobGroupAPI scheduleJobGroupAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quartz() throws SerException {
//        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
//        scheduleJobGroupTO.setName("定时查找离职员工工作组");
//        scheduleJobGroupTO.setEnable(true);
//        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.BeforeRemoveEmployeeAPI");
        scheduleJobTO.setName("定时查找离职员工");
        scheduleJobTO.setMethod("send");
        scheduleJobTO.setExpression("0 0 */12 * * ?");   //每12个小时执行一次
        scheduleJobTO.setDescription("查找离职员工，并通知综合资源部");
        scheduleJobTO.setEnable(true);
        scheduleJobTO.setAddress("localhost:51101");
        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
        scheduleJobAPI.add(scheduleJobTO);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeRemoveEmployeeBO save(BeforeRemoveEmployeeTO to) throws SerException {
        BeforeRemoveEmployee beforeRemoveEmployee = BeanTransform.copyProperties(to, BeforeRemoveEmployee.class, true);
        super.save(beforeRemoveEmployee);
        //todo:社保减员前参考信息,发送并通知总经办审阅
        MessageTO messageTO = new MessageTO("减员审核", "有减员名单需您审核");
        messageTO.setReceivers(new String[]{"XXX"});
        messageTO.setRangeType(RangeType.SPECIFIED);
//        messageAPI.send(messageTO);
        return BeanTransform.copyProperties(beforeRemoveEmployee, BeforeRemoveEmployeeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        BeforeRemoveEmployee entity = super.findById(id);
        entity.setIs_remove(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        RemoveEmployeeTO removeEmployeeTO = new RemoveEmployeeTO();
        BeanUtils.copyProperties(entity, removeEmployeeTO);
        removeEmployeeSer.save(removeEmployeeTO);
        //todo：发邮件通知福利模块负责人，并抄送到综合资源部
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
    public void send() throws SerException {
        List<DimissionInfoBO> list = dimissionInfoAPI.all();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("今天的离职员工");
        LocalDate now = LocalDate.now();
        boolean b1 = false;
        StringBuilder sb1 = new StringBuilder();
        boolean b2 = false;
        StringBuilder sb2 = new StringBuilder();
        String s = "今天离职，请及时处理社保减员！";
        for (DimissionInfoBO bo : list) {
            if (DimissionType.NORMAL.equals(bo.getType()) && HandleStatus.AFFIRM.equals(bo.getHandle())) {
                LocalDate date = DateUtil.parseDate(bo.getDimissionDate());
                if (now == date) {
                    b1 = true;
                    String employeeNumber = bo.getEmployeeNumber();
                    String name = bo.getUsername();
                    sb1.append("员工编号为：" + employeeNumber + "的" + name + "，");
                }
            }
            if ((!DimissionType.NORMAL.equals(bo.getType())) && HandleStatus.AFFIRM.equals(bo.getHandle())) {
                LocalDate date = DateUtil.parseDate(bo.getAdvanceDate());
                if (now == date) {
                    b2 = true;
                    String employeeNumber = bo.getEmployeeNumber();
                    String name = bo.getUsername();
                    sb2.append("员工编号为：" + employeeNumber + "的" + name + "，");
                }
            }
        }
        if (b1) {
            sb1.append(s);
            messageTO.setContent(sb1.toString());
            //todo:发邮件到福利模块，综合资源部公油
        }
        if (b2) {
            sb2.append(s);
            messageTO.setContent(sb2.toString());
            //todo:发邮件到福利模块，综合资源部公油
        }
    }

    @Override
    public Long count(BeforeRemoveEmployeeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(BeforeRemoveEmployeeTO to) throws SerException {
        BeforeRemoveEmployee entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, BeforeRemoveEmployee.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }
}