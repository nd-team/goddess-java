package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.dto.OrganizationDTO;
import com.bjike.goddess.stockholdermeeting.entity.Organization;
import com.bjike.goddess.stockholdermeeting.enums.OrganizationStauts;
import com.bjike.goddess.stockholdermeeting.to.OrganizationTO;
import com.bjike.goddess.stockholdermeeting.utils.ChineseCharToEn;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:46 ]
 * @Description: [ 股东大会组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "stockholdermeetingSerCache")
@Service
public class OrganizationSerImpl extends ServiceImpl<Organization, OrganizationDTO> implements OrganizationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SummarySer summarySer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private DesginSer desginSer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OrganizationBO save(OrganizationTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Organization entity = BeanTransform.copyProperties(to, Organization.class, true);
        entity.setMeetingType("股东大会");
        entity.setOrganization(name);
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
        meetingNumber = "GDDH-" + meetingNumber + "-" + year + month + day + "-" + num;
        entity.setMeetingNumber(meetingNumber);
        String[] jobs = to.getPlanJobs();
        String[] attends = to.getPlanAttends();
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
        entity.setPlanAttend(sb2.toString());
        String topicContent = desginSer.topicContent(entity.getMeetingTopic());
        if (topicContent == null) {
            throw new SerException("没有该会议议题对应的议题包含内容");
        }
        String function = desginSer.function(entity.getMeetingTopic());
        if (function == null) {
            throw new SerException("没有该会议议题对应的会议议题关联的功能");
        }
        entity.setFunction(function);
        entity.setTopicContent(topicContent);
        entity.setOrganizationStauts(OrganizationStauts.NORMAL);
        List<PositionDetailBO> boList = positionDetailAPI.findStatus();
        for (PositionDetailBO bo : boList) {
            if ("规划模块".equals(bo.getModuleName()) && "管理层".equals(bo.getArrangementName()) && "规划模块负责人".equals(bo.getPosition())) {
                List<UserBO> list = positionDetailUserAPI.findByPosition(bo.getId());
                if ((list != null) && (!list.isEmpty())) {
                    entity.setHost(list.get(0).getUsername());
                }
            }
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, OrganizationBO.class);
    }

    @Override
    public void edit(OrganizationTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Organization entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        String number=entity.getMeetingNumber();
        entity = BeanTransform.copyProperties(to, Organization.class, true);
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
        String num=number.substring(number.length()-1);
        meetingNumber = "GDDH-" + meetingNumber + "-" + year + month + day + "-" + num;
        entity.setMeetingNumber(meetingNumber);
        String[] jobs = to.getPlanJobs();
        String[] attends = to.getPlanAttends();
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
        entity.setPlanAttend(sb2.toString());
        String topicContent = desginSer.topicContent(entity.getMeetingTopic());
        if (topicContent == null) {
            throw new SerException("没有该会议议题对应的议题包含内容");
        }
        String function = desginSer.function(entity.getMeetingTopic());
        if (function == null) {
            throw new SerException("没有该会议议题对应的会议议题关联的功能");
        }
        entity.setFunction(function);
        entity.setTopicContent(topicContent);
        entity.setCreateTime(a);
        entity.setMeetingType("股东大会");
        entity.setOrganization(name);
        entity.setOrganizationStauts(OrganizationStauts.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<OrganizationBO> list(OrganizationDTO dto) throws SerException {
        List<Organization> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, OrganizationBO.class);
    }

    @Override
    public OrganizationBO findByID(String id) throws SerException {
        Organization entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, OrganizationBO.class);
    }

    @Override
    public Long countNum(OrganizationDTO dto) throws SerException {
        return super.count(new OrganizationDTO());
    }

    @Override
    public List<String> findPeople(String[] positionIds) throws SerException {
        List<String> list = new ArrayList<String>();
        for (String s : positionIds) {
            List<UserBO> list1 = positionDetailUserAPI.findByPosition(s);
            for (UserBO u : list1) {
                list.add(u.getUsername());
            }
        }
        return list;
    }

    @Override
    public Set<String> allMeetingsNumbers() throws SerException {
        List<Organization> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (Organization o : list) {
            set.add(o.getMeetingNumber());
        }
        return set;
    }

    @Override
    public Set<String> allMeetingFormats() throws SerException {
        Set<String> set = new HashSet<String>();
        set.add("线下");
        return set;
    }

    @Override
    public void freeze(String id) throws SerException {
        Organization entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setOrganizationStauts(OrganizationStauts.FREEZE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(String id) throws SerException {
        Organization entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setOrganizationStauts(OrganizationStauts.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 查看计划会议当天有几个会议
     *
     * @param planTime 计划会议时间
     * @return
     * @throws SerException
     */
    private Long findNum(LocalDateTime planTime) throws SerException {
        OrganizationDTO dto = new OrganizationDTO();
        LocalDate time = DateUtil.parseDate(DateUtil.dateToString(planTime).substring(0, 10));
        LocalDate[] times = new LocalDate[]{time, time};
        dto.getConditions().add(Restrict.between("planTime", times));
        return super.count(dto);
    }
}