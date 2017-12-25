package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.negotiatemeeting.bo.OrganizationBO;
import com.bjike.goddess.negotiatemeeting.dto.OrganizationDTO;
import com.bjike.goddess.negotiatemeeting.entity.Organization;
import com.bjike.goddess.negotiatemeeting.to.OrganizationTO;
import com.bjike.goddess.negotiatemeeting.to.ReadyTO;
import com.bjike.goddess.negotiatemeeting.to.SummaryTO;
import com.bjike.goddess.negotiatemeeting.utils.ChineseCharToEn;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
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
 * 协商会议组织内容业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:30 ]
 * @Description: [ 协商会议组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
@Service
public class OrganizationSerImpl extends ServiceImpl<Organization, OrganizationDTO> implements OrganizationSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SummarySer summarySer;
    @Autowired
    private ReadySer readySer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private DesginSer desginSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public OrganizationBO save(OrganizationTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Organization entity = BeanTransform.copyProperties(to, Organization.class, true);
        entity.setMeetingType("协商会议");
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
        meetingNumber = "XS-" + meetingNumber + "-" + year + month + day + "-" + num;
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
        String content = desginSer.content(entity.getMeetingTopic(), entity.getMeetingLevel());
        if (content == null) {
            throw new SerException("没有该会议层面对应的议题包含内容");
        }
        entity.setContent(content);
        super.save(entity);
        SummaryTO summaryTO = new SummaryTO();
        summaryTO.setOrganizationId(entity.getId());
        summaryTO.setActualTime(DateUtil.dateToString(entity.getPlanTime()));
        summarySer.save(summaryTO);
        String planAttend = entity.getPlanAttend();
        String[] names = planAttend.split("、");
        List<UserBO> list = findUserId(names);
        if (list != null) {
            for (UserBO u : list) {
                String id = u.getId();
                ReadyTO readyTO = new ReadyTO();
                readyTO.setOrganizationId(entity.getId());
                readyTO.setUserId(id);
                readySer.save(readyTO);
//                MessageTO messageTO = new MessageTO();
//                messageTO.setReceivers(new String[]{id});
//                messageTO.setTitle("有一个协商会议需要您出席");
//                String content = "会议类型为" + entity.getMeetingType() + "，会议议题为" + entity.getMeetingTopic() + "，议题产生的原因为" + entity.getReason() + "，" +
//                        "议题目的为" + entity.getPurpose() + "，计划参会人员为" + entity.getPlanAttend() + "，计划会议时间为" + entity.getPlanTime() + "，会议形式为" + entity.getMeetingFormat() + "，" +
//                        "会议地点为" + entity.getMeetingArea() + "，请您登陆issp系统填写协商前准备信息，请您准时参加会议!";
//                messageTO.setContent(content);
//                messageAPI.send(messageTO);
            }
        }
        return BeanTransform.copyProperties(entity, OrganizationBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(OrganizationTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        Organization entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
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
        String num = String.valueOf(findNum(planTime) + 1);
        meetingNumber = "XS-" + meetingNumber + "-" + year + month + day + "-" + num;
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
        String content = desginSer.content(entity.getMeetingTopic(), entity.getMeetingLevel());
        if (content == null) {
            throw new SerException("没有该会议层面对应的议题包含内容");
        }
        entity.setContent(content);
        entity.setCreateTime(a);
        entity.setMeetingType("协商会议");
        entity.setOrganization(name);
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
    public Set<String> allMeetingsNumber() throws SerException {
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
        set.add("线上和线下");
        return set;
    }

    @Override
    public List<String> findPeople(String[] positionId) throws SerException {
        List<String> list = new ArrayList<String>();
        for (String s : positionId) {
            List<UserBO> list1 = positionDetailUserAPI.findByPosition(s);
            for (UserBO u : list1) {
                list.add(u.getUsername());
            }
        }
        return list;
    }

    @Override
    public Set<String> findHosts(String meetingLevel) throws SerException {
        List<PositionDetailBO> list = positionDetailAPI.findStatus();
        Set<String> set = new HashSet<String>();
        if ("公司".equals(meetingLevel)) {
            for (PositionDetailBO p : list) {
                if ("福利模块".equals(p.getModuleName())) {
                    List<UserBO> userBOs = positionDetailUserAPI.findByPosition(p.getId());
                    for (UserBO u : userBOs) {
                        set.add(u.getUsername());
                    }
                }
            }
            return set;
        } else if ("地区".equals(meetingLevel)) {
            for (PositionDetailBO p : list) {
                if ("决策层".equals(p.getArrangementName())) {
                    List<UserBO> userBOs = positionDetailUserAPI.findByPosition(p.getId());
                    for (UserBO u : userBOs) {
                        set.add(u.getUsername());
                    }
                }
            }
            return set;
        } else if ("项目组/部门".equals(meetingLevel)) {
            for (PositionDetailBO p : list) {
                if ("管理层".equals(p.getArrangementName())) {
                    List<UserBO> userBOs = positionDetailUserAPI.findByPosition(p.getId());
                    for (UserBO u : userBOs) {
                        set.add(u.getUsername());
                    }
                }
            }
            return set;
        } else if ("模块".equals(meetingLevel)) {
            for (PositionDetailBO p : list) {
                if ("执行层".equals(p.getArrangementName())) {
                    List<UserBO> userBOs = positionDetailUserAPI.findByPosition(p.getId());
                    for (UserBO u : userBOs) {
                        set.add(u.getUsername());
                    }
                }
            }
            return set;
        } else {
            return set;
        }
    }

    private List<UserBO> findUserId(String[] names) throws SerException {
        List<UserBO> list = new ArrayList<UserBO>();
        List<UserBO> list1 = null;
        for (String name : names) {
            String sql = "SELECT id,username FROM user " +
                    "where username='" + name + "'";
            String[] fileds = new String[]{"id", "username"};
            list1 = super.findBySql(sql, UserBO.class, fileds);
            if (list1 != null && !list1.isEmpty()) {
                list.add(list1.get(0));
            }
        }
        return list;
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