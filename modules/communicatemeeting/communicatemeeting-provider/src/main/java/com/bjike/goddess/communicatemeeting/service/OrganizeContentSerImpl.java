package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.communicatemeeting.bo.OrganizeContentBO;
import com.bjike.goddess.communicatemeeting.dto.OrganizeContentDTO;
import com.bjike.goddess.communicatemeeting.entity.OrganizeContent;
import com.bjike.goddess.communicatemeeting.to.MeetingSummaryTO;
import com.bjike.goddess.communicatemeeting.to.OrganizeContentTO;
import com.bjike.goddess.communicatemeeting.utils.ChineseCharToEn;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "communicatemeetingSerCache")
@Service
public class OrganizeContentSerImpl extends ServiceImpl<OrganizeContent, OrganizeContentDTO> implements OrganizeContentSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MeetingSummarySer meetingSummarySer;
    @Autowired
    private DesginSer desginSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public OrganizeContentBO save(OrganizeContentTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        OrganizeContent entity = BeanTransform.copyProperties(to, OrganizeContent.class, true);
        entity.setMeetingType("交流会");
        entity.setOrganization(name);
        entity.setHost(name);
        //todo: entity.setHost(综合素养模块负责人);
        String meetingNumber = ChineseCharToEn.getAllFirstLetter(entity.getMeetingLevel());
        LocalDateTime planTime = entity.getPlanTime();
        String year = String.valueOf(planTime.getYear());
        String month = String.valueOf(planTime.getMonthValue());
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = String.valueOf(planTime.getDayOfMonth());
        if (day.length() < 2) {
            day = "0" + day;
        }
        String num = String.valueOf(findNum(planTime) + 1);
        meetingNumber = "JL-" + meetingNumber + "-" + year + month + day + "-" + num;
        entity.setMeetingNumber(meetingNumber);
        String[] jobs = to.getPlanJobs();
        String[] attends = to.getPlanPeoples();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < jobs.length; i++) {
            if (i != jobs.length - 1) {
                sb1.append(jobs[i] + "、");
            } else {
                sb1.append(jobs[i]);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < attends.length; i++) {
            if (i != attends.length - 1) {
                sb2.append(attends[i] + "、");
            } else {
                sb2.append(attends[i]);
            }
        }
        entity.setPlanJob(sb1.toString());
        entity.setPlanPeople(sb2.toString());
        super.save(entity);
        MeetingSummaryTO meetingSummaryTO = new MeetingSummaryTO();
        meetingSummaryTO.setOrganizeContentId(entity.getId());
        meetingSummaryTO.setActualTime(DateUtil.dateToString(entity.getPlanTime()));
        meetingSummarySer.save(meetingSummaryTO);
        return BeanTransform.copyProperties(entity, OrganizeContentBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(OrganizeContentTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        OrganizeContent entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        String number = entity.getMeetingNumber();
        String host = entity.getHost();
        entity = BeanTransform.copyProperties(to, OrganizeContent.class, true);
        String meetingNumber = ChineseCharToEn.getAllFirstLetter(entity.getMeetingLevel());
        LocalDateTime planTime = entity.getPlanTime();
        String year = String.valueOf(planTime.getYear());
        String month = String.valueOf(planTime.getMonthValue());
        if (month.length() < 2) {
            month = "0" + month;
        }
        String day = String.valueOf(planTime.getDayOfMonth());
        if (day.length() < 2) {
            day = "0" + day;
        }
        String num = number.substring(number.length() - 1);
        meetingNumber = "JL-" + meetingNumber + "-" + year + month + day + "-" + num;
        entity.setMeetingNumber(meetingNumber);
        entity.setCreateTime(a);
        entity.setMeetingType("交流会");
        entity.setOrganization(name);
        entity.setHost(host);
        String[] jobs = to.getPlanJobs();
        String[] attends = to.getPlanPeoples();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < jobs.length; i++) {
            if (i != jobs.length - 1) {
                sb1.append(jobs[i] + "、");
            } else {
                sb1.append(jobs[i]);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < attends.length; i++) {
            if (i != attends.length - 1) {
                sb2.append(attends[i] + "、");
            } else {
                sb2.append(attends[i]);
            }
        }
        entity.setPlanJob(sb1.toString());
        entity.setPlanPeople(sb2.toString());
        entity.setMeetingNumber(number);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<OrganizeContentBO> list(OrganizeContentDTO dto) throws SerException {
        List<OrganizeContent> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, OrganizeContentBO.class);
    }

    @Override
    public OrganizeContentBO findByID(String id) throws SerException {
        OrganizeContent entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, OrganizeContentBO.class);
    }

    @Override
    public Long countNum(OrganizeContentDTO dto) throws SerException {
        return super.count(new OrganizeContentDTO());
    }

    @Override
    public void updateHost(String id, String host) throws SerException {
        //修改为素养模块其余相关人员
        OrganizeContent entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setHost(host);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public Set<String> findPeoples(String... jobs) throws SerException {
        for (String job : jobs) {
            //通过查找组织结构中岗位对应的员工
        }
        Set<String> set = new HashSet<String>();
        return set;
    }

    @Override
    public void updateNumer(String id, String meetingNumber) throws SerException {
        OrganizeContent organizeContent = super.findById(id);
        organizeContent.setMeetingNumber(meetingNumber);
        super.update(organizeContent);
    }

    @Override
    public Set<String> allMeetingsNumber() throws SerException {
        List<OrganizeContent> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (OrganizeContent o : list) {
            set.add(o.getMeetingNumber());
        }
        return set;
    }

    @Override
    public Set<String> allMeetingFormats() throws SerException {
        Set<String> set = new HashSet<String>();
        set.add("线上");
        set.add("线下");
        return set;
    }

    /**
     * 查看计划会议当天有几个会议
     *
     * @param planTime 计划会议时间
     * @return
     * @throws SerException
     */
    private Long findNum(LocalDateTime planTime) throws SerException {
        OrganizeContentDTO dto = new OrganizeContentDTO();
        LocalDate time = DateUtil.parseDate(DateUtil.dateToString(planTime).substring(0, 10));
        LocalDate[] times = new LocalDate[]{time, time};
        dto.getConditions().add(Restrict.between("planTime", times));
        return super.count(dto);
    }
}