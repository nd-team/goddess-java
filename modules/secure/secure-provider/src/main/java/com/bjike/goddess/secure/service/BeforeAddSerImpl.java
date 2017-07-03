package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.quartz.api.ScheduleJobAPI;
import com.bjike.goddess.quartz.api.ScheduleJobGroupAPI;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.entity.BeforeAdd;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 增员前业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "secureSerCache")
@Service
public class BeforeAddSerImpl extends ServiceImpl<BeforeAdd, BeforeAddDTO> implements BeforeAddSer {
    @Autowired
    private BuySer buySer;
    @Autowired
    private AbandonSer abandonSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private RegularizationAPI regularizationAPI;
    @Autowired
    private ScheduleJobAPI scheduleJobAPI;
    @Autowired
    private ScheduleJobGroupAPI scheduleJobGroupAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void quartz() throws SerException {
        ScheduleJobTO scheduleJobTO = new ScheduleJobTO();
        scheduleJobTO.setClazz("com.bjike.goddess.secure.api.BeforeAddAPI");
        scheduleJobTO.setName("定时获取新转正员工");
        scheduleJobTO.setMethod("send");
        scheduleJobTO.setExpression("0 0 */12 * * ?");   //每12个小时执行一次
        scheduleJobTO.setDescription("获取新转正员工，并通知福利模块这些员工可购买社保");
        scheduleJobTO.setEnable(true);
        scheduleJobTO.setAddress("localhost:51101");
        scheduleJobTO.setScheduleJobGroupId("eb33c9b6-ed33-4596-be70-b3eb6aa81f1d");
        scheduleJobAPI.add(scheduleJobTO);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO save(BeforeAddTO to) throws SerException {
        BeforeAdd canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd = super.save(canAdd);
        MessageTO messageTO = new MessageTO("xxx", "sadasd");
        //     messageAPI.send(messageTO);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO complete(BeforeAddTO to) throws SerException {
        BeforeAdd canAdd = super.findById(to.getId());
        if (canAdd == null) {
            throw new SerException("该对象不存在");
        }
        canAdd.setName(to.getName());
        LocalDate time = null;
        try {
            time = DateUtil.parseDate(to.getAddTime());
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        canAdd.setAddTime(time);
        canAdd.setTime(to.getTime());
        canAdd.setRequest(to.getRequest());
        canAdd.setAddCity(to.getAddCity());
        canAdd.setDescription(to.getDescription());
        canAdd.setModifyTime(LocalDateTime.now());
        super.update(canAdd);
        //todo:发邮件，增员参考信息汇总发送通知至总经办邮箱
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(BeforeAddTO to) throws SerException {
        BeforeAdd canAdd = super.findById(to.getId());
        if (canAdd == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = canAdd.getCreateTime();
        canAdd = BeanTransform.copyProperties(to, BeforeAdd.class, true);
        canAdd.setCreateTime(a);
        canAdd.setModifyTime(LocalDateTime.now());
        super.update(canAdd);
    }

    @Override
    public void add(String id) throws SerException {
        BeforeAdd entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setIncrease(true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        //todo:将对应信息通知福利模块负责人,并抄送至zhzyb_aj
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public BeforeAddBO delete(String id) throws SerException {
        super.remove(id);
        return null;
    }

    @Override
    public List<BeforeAddBO> find(BeforeAddDTO dto) throws SerException {
        List<BeforeAdd> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, BeforeAddBO.class);
    }

    @Override
    public BeforeAddBO findByID(String id) throws SerException {
        BeforeAdd canAdd = super.findById(id);
        return BeanTransform.copyProperties(canAdd, BeforeAddBO.class);
    }

    @Override
    public void send() throws SerException {
        List<RegularizationBO> boList = regularizationAPI.list(new RegularizationDTO());
        LocalDate now = LocalDate.now();
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有转正员工可购买社保");
        boolean b = false;
        StringBuilder sb = new StringBuilder();
        if ((boList != null) && (!boList.isEmpty())) {
            for (RegularizationBO bo : boList) {
                if (StringUtils.isNotBlank(bo.getPositiveDate())) {
                    if (now == DateUtil.parseDate(bo.getPositiveDate())) {
                        b = true;
                        String name = bo.getName();
                        String empNo = bo.getEmpNo();
                        sb.append("员工编号为:" + empNo + "的" + name + "，");
                    }
                }
            }
        }
        if (b) {
            sb.append("已转正，可购买社保，请及时处理!");
            messageTO.setContent(sb.toString());
            //todo:发送邮件给福利模块
        }
    }

    @Override
    public Long count(BeforeAddDTO dto) throws SerException {
        return super.count(dto);
    }

//    private String getJobGroupId() throws SerException{
//        ScheduleJobGroupTO scheduleJobGroupTO = new ScheduleJobGroupTO();
//        scheduleJobGroupTO.setName("定时获取新转正员工工作组");
//        scheduleJobGroupTO.setEnable(true);
//        ScheduleJobGroupBO scheduleJobGroupBO = scheduleJobGroupAPI.add(null, scheduleJobGroupTO);
//        return scheduleJobGroupBO.getId();
//    }
}