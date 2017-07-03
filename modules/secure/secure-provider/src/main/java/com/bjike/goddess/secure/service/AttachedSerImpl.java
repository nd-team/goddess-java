package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.entity.Attached;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.AttachedTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 挂靠业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class AttachedSerImpl extends ServiceImpl<Attached, AttachedDTO> implements AttachedSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO save(AttachedTO to) throws SerException {
        Attached attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached = super.save(attached);
        //todo:将填写的内容发邮件到总经办
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public void pass(String id) throws SerException {
        Attached attached = super.findById(id);
        if (attached == null) {
            throw new SerException("该对象不存在");
        }
        attached.setAdvice("审批通过");
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("挂靠社保信息审批意见");
        messageTO.setContent("挂靠人：" + attached.getAttachedName() + "的挂靠信息" + attached.getAdvice());
        //todo:发邮件到福利模块负责人及抄送综合资源部
    }

    @Override
    public void notPass(String id) throws SerException {
        Attached attached = super.findById(id);
        if (attached == null) {
            throw new SerException("该对象不存在");
        }
        attached.setAdvice("审批不通过");
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("挂靠社保信息审批意见");
        messageTO.setContent("挂靠人：" + attached.getAttachedName() + "的挂靠信息" + attached.getAdvice());
        //todo:发邮件到福利模块负责人及抄送综合资源部
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO complete(AttachedTO to) throws SerException {
        Attached attached = super.findById(to.getId());
        attached.setAssureJob(to.getAssureJob());
        attached.setAssureArrival(to.getAssureArrival());
        attached.setAssureTel(to.getAssureTel());
        attached.setStartTime(DateUtil.parseDate(to.getStartTime()));
        attached.setEndTime(DateUtil.parseDate(to.getEndTime()));
        attached.setCompany(to.getCompany());
        attached.setMoney(to.getMoney());
        if ((to.getBeforeCity() != null) && (StringUtils.isNotBlank(to.getBeforeCity()))) {
            attached.setBeforeCity(to.getBeforeCity());
        }
        if ((to.getBeforeTime() != null) && (StringUtils.isNotBlank(to.getBeforeTime()))) {
            attached.setBeforeTime(DateUtil.parseDate(to.getBeforeTime()));
        }
        if ((to.getInsuredYear() != null) && (StringUtils.isNotBlank(to.getInsuredYear()))) {
            attached.setInsuredYear(to.getInsuredYear());
        }
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        //todo:系统将社保挂靠基本信息,通知运营商务部、总经办给予意见
        AddEmployeeTO addEmployeeTO = new AddEmployeeTO();
        BeanUtils.copyProperties(attached, addEmployeeTO);
        addEmployeeTO.setName(attached.getAttachedName());
        addEmployeeTO.setSecureTime(DateUtil.dateToString(attached.getStartTime()));
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<AttachedBO> find(AttachedDTO dto) throws SerException {
        List<Attached> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    public AttachedBO findByID(String id) throws SerException {
        Attached attached = super.findById(id);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public List<AttachedBO> findALL() throws SerException {
        List<Attached> list = super.findAll();
        return BeanTransform.copyProperties(list, AttachedBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public AttachedBO edit(AttachedTO to) throws SerException {
        Attached attached = super.findById(to.getId());
        LocalDateTime a = attached.getCreateTime();
        attached = BeanTransform.copyProperties(to, Attached.class, true);
        attached.setCreateTime(a);
        attached.setModifyTime(LocalDateTime.now());
        super.update(attached);
        return BeanTransform.copyProperties(attached, AttachedBO.class);
    }

    @Override
    public Long count(AttachedDTO dto) throws SerException {
        return super.count(dto);
    }
}