package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.negotiatemeeting.bo.SummaryBO;
import com.bjike.goddess.negotiatemeeting.bo.SummarySonBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryDTO;
import com.bjike.goddess.negotiatemeeting.entity.Organization;
import com.bjike.goddess.negotiatemeeting.entity.Summary;
import com.bjike.goddess.negotiatemeeting.entity.SummarySon;
import com.bjike.goddess.negotiatemeeting.excel.SummaryExcel;
import com.bjike.goddess.negotiatemeeting.to.SummarySonTO;
import com.bjike.goddess.negotiatemeeting.to.SummaryTO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 协商会议纪要业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:49 ]
 * @Description: [ 协商会议纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
@Service
public class SummarySerImpl extends ServiceImpl<Summary, SummaryDTO> implements SummarySer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private SummarySonSer summarySonSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public SummaryBO save(SummaryTO to) throws SerException {
        Summary entity = BeanTransform.copyProperties(to, Summary.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SummaryBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(SummaryTO to) throws SerException {
        Summary entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String id = entity.getOrganizationId();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Summary.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        entity.setOrganizationId(id);
        String[] actualAttends = to.getActualAttends();
        String[] addAttends = to.getAddAttends();
        String[] notAttends = to.getNotAttends();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (int i = 0; i < actualAttends.length; i++) {
            if (i != actualAttends.length - 1) {
                sb1.append(actualAttends[i] + "、");
            }
            sb1.append(actualAttends[i]);
        }
        for (int i = 0; i < addAttends.length; i++) {
            if (i != actualAttends.length - 1) {
                sb2.append(addAttends[i] + "、");
            }
            sb2.append(addAttends[i]);
        }
        for (int i = 0; i < notAttends.length; i++) {
            if (i != notAttends.length - 1) {
                sb3.append(notAttends[i] + "、");
            }
            sb3.append(notAttends[i]);
        }
        entity.setActualAttend(sb1.toString());
        entity.setAddAttend(sb2.toString());
        entity.setNotAttend(sb3.toString());
        super.update(entity);
        String actualAttend = entity.getActualAttend();
        String[] names = actualAttend.split("、");
        String[] userIds = findUserId(names);
        int year = entity.getActualTime().getYear();
        int month = entity.getActualTime().getMonthValue();
        int day = entity.getActualTime().getDayOfMonth();
        String content = "各位同事，你们好！以下是" + year + "年\n" +
                "" + month + "月" + day + "日的员工代表大会会议纪要，请查看\n" +
                "相关发言内容是否有误，如有需要修改可\n" +
                "在后天上午12:00前到系统修改发言内\n" +
                "容";
//        sendPerson(content, "会议纪要", userIds);
    }

    @Override
    public List<SummaryBO> list(SummaryDTO dto) throws SerException {
        //todo:除参会人员外的人员查阅，需要经过总经办的同意才能调阅会议记录
        dto.getSorts().add("actualTime=desc");
        List<Summary> list = super.findByCis(dto, true);
        List<SummaryBO> boList = new ArrayList<SummaryBO>();
        for (Summary m : list) {
            List<SummarySon> list1 = summarySonSer.findBySummaryId(m.getId());
            if (list1.isEmpty()) {
                SummaryBO bo = BeanTransform.copyProperties(m, SummaryBO.class);
                Organization organization = findByOrganizationId(m.getOrganizationId());
                if (organization != null) {
                    bo.setMeetingType(organization.getMeetingType());
                    bo.setMeetingNumber(organization.getMeetingNumber());
                    bo.setMeetingFormat(organization.getMeetingFormat());
                    bo.setMeetingArea(organization.getMeetingArea());
                    bo.setPlanAttend(organization.getPlanAttend());
                    bo.setPurpose(organization.getPurpose());
                    bo.setReason(organization.getReason());
                    bo.setHost(organization.getHost());
                    bo.setOrganization(organization.getOrganization());
                    boList.add(bo);
                }
            } else {
                for (SummarySon s : list1) {
                    SummaryBO bo = BeanTransform.copyProperties(m, SummaryBO.class);
                    Organization organization = findByOrganizationId(m.getOrganizationId());
                    if (organization != null) {
                        bo.setSummarySonId(s.getId());
                        bo.setAttend(s.getAttend());
                        bo.setOpinion(s.getOpinion());
                        bo.setMeetingType(organization.getMeetingType());
                        bo.setMeetingNumber(organization.getMeetingNumber());
                        bo.setMeetingFormat(organization.getMeetingFormat());
                        bo.setMeetingArea(organization.getMeetingArea());
                        bo.setPlanAttend(organization.getPlanAttend());
                        bo.setPurpose(organization.getPurpose());
                        bo.setReason(organization.getReason());
                        bo.setHost(organization.getHost());
                        bo.setOrganization(organization.getOrganization());
                        boList.add(bo);
                    }
                }
            }
        }
        return boList;
    }

    @Override
    public SummaryBO findByID(String id) throws SerException {
        Summary entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        SummaryBO bo = BeanTransform.copyProperties(entity, SummaryBO.class);
        Organization organization = findByOrganizationId(entity.getOrganizationId());
        if (organization != null) {
            bo.setMeetingType(organization.getMeetingType());
            bo.setMeetingNumber(organization.getMeetingNumber());
            bo.setMeetingFormat(organization.getMeetingFormat());
            bo.setMeetingArea(organization.getMeetingArea());
            bo.setPlanAttend(organization.getPlanAttend());
            bo.setPurpose(organization.getPurpose());
            bo.setReason(organization.getReason());
            bo.setHost(organization.getHost());
            bo.setOrganization(organization.getOrganization());
        }
        return bo;
    }

    @Override
    public Long countNum(SummaryDTO dto) throws SerException {
        return super.count(new SummaryDTO());
    }

    @Override
    public byte[] exportExcel(String id, String summaryId) throws SerException {
        Summary summary = super.findById(id);
        SummaryBO bo = BeanTransform.copyProperties(summary, SummaryBO.class);
        if (summaryId != null) {
            SummarySonBO sonBO = summarySonSer.findByID(summaryId);
            bo.setAttend(sonBO.getAttend());
            bo.setOpinion(sonBO.getOpinion());
        }
        Organization organization = findByOrganizationId(summary.getOrganizationId());
        if (organization != null) {
            bo.setMeetingNumber(organization.getMeetingNumber());
        }
        SummaryExcel summaryExcel = new SummaryExcel();
        BeanUtils.copyProperties(bo, summaryExcel);
        summaryExcel.setActualTime(DateUtil.dateToString(summary.getActualTime()));
        List<SummaryExcel> toList = new ArrayList<SummaryExcel>();
        toList.add(summaryExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void leadExcel(List<SummaryExcel> toList) throws SerException {
        for (SummaryExcel to : toList) {
            Organization organization = findByNumber(to.getMeetingNumber());
            if (organization == null) {
                throw new SerException("没有该会议编号对应的会议信息");
            }
            String organizationId = organization.getId();
            String id = findId(organizationId);
            if (id == null) {
                throw new SerException("没有该会议编号对应的会议信息");
            }
            Summary summary = new Summary();
            BeanUtils.copyProperties(to, summary);
            summary.setActualTime(DateUtil.parseDateTime(to.getActualTime()));
            summary.setOrganizationId(organizationId);
            summary.setId(id);
            summary.setCreateTime(super.findById(id).getCreateTime());
            summary.setModifyTime(LocalDateTime.now());
            super.update(summary);
            SummarySon summarySon = find(id, to.getAttend());
            if (summarySon != null) {
                SummarySonTO summarySonTO = new SummarySonTO();
                summarySonTO.setId(summarySon.getId());
                summarySonTO.setAttend(to.getAttend());
                summarySonTO.setOpinion(to.getOpinion());
                summarySonSer.edit(summarySonTO);
            } else {
                SummarySonTO summarySonTO = new SummarySonTO();
                summarySonTO.setMeetingNumber(to.getMeetingNumber());
                summarySonTO.setAttend(to.getAttend());
                summarySonTO.setOpinion(to.getOpinion());
                summarySonSer.save(summarySonTO);
            }
            String actualAttend = summary.getActualAttend();
            String[] names = actualAttend.split("、");
            String[] userIds = findUserId(names);
            int year = summary.getActualTime().getYear();
            int month = summary.getActualTime().getMonthValue();
            int day = summary.getActualTime().getDayOfMonth();
            String content = "各位同事，你们好！以下是" + year + "年\n" +
                    "" + month + "月" + day + "日的员工代表大会会议纪要，请查看\n" +
                    "相关发言内容是否有误，如有需要修改可\n" +
                    "在后天上午12:00前到系统修改发言内\n" +
                    "容";
//            sendPerson(content, "会议纪要", userIds);
        }
    }

    @Override
    public Set<String> meetingNumbers() throws SerException {
        List<SummaryBO> list = list(new SummaryDTO());
        Set<String> set = new HashSet<String>();
        for (SummaryBO m : list) {
            set.add(m.getMeetingNumber());
        }
        return set;
    }

    /**
     * 通过会议组织id查找具体的会议信息
     *
     * @param id 会议组织id
     * @return
     * @throws SerException
     */
    private Organization findByOrganizationId(String id) throws SerException {
        String[] ids = new String[]{id};
        List<Organization> list = null;
        for (String s : ids) {
            String sql = "SELECT meetingLevel,meetingNumber,meetingFormat,meetingArea,meetingTopic,content,host,organization\n" +
                    "FROM negotiatemeeting_organization\n" +
                    "WHERE id='" + s + "'";
            String[] fileds = new String[]{"meetingLevel", "meetingNumber", "meetingFormat", "meetingArea", "meetingTopic", "content", "host", "organization"};
            list = super.findBySql(sql, Organization.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过会议编号查找具体的会议信息
     *
     * @param meetingNumber 会议编号
     * @return
     * @throws SerException
     */
    private Organization findByNumber(String meetingNumber) throws SerException {
        String[] meetingNumbers = new String[]{meetingNumber};
        List<Organization> list = null;
        for (String s : meetingNumbers) {
            String sql = "SELECT id,meetingFormat,meetingArea,meetingTopic,content,host,organization\n" +
                    "FROM negotiatemeeting_organization\n" +
                    "WHERE meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id", "meetingFormat", "meetingArea", "meetingTopic", "content", "host", "organization"};
            list = super.findBySql(sql, Organization.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过会议组织id查找会议纪要id
     *
     * @param organizationId 会议组织id
     * @return
     * @throws SerException
     */
    private String findId(String organizationId) throws SerException {
        String[] strings = new String[]{organizationId};
        List<Summary> list = null;
        for (String s : strings) {
            String sql = "SELECT id from negotiatemeeting_summary\n" +
                    "where organization_id='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, Summary.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0).getId();
        }
        return null;
    }

    /**
     * 发邮件给个人
     *
     * @param content   内容
     * @param title     标题
     * @param receivers 接收者
     * @throws SerException
     */
    private void sendPerson(String content, String title, String[] receivers) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setContent(content);
        messageTO.setTitle(title);
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }

    private String[] findUserId(String[] names) throws SerException {
        List<User> list = null;
        for (String name : names) {
            String sql = "SELECT id FROM user\n" +
                    "where username='" + name + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, User.class, fileds);
        }
        List<String> list1 = new ArrayList<String>();
        for (User u : list) {
            list1.add(u.getId());
        }
        return list1.toArray(new String[list1.size()]);
    }

    private SummarySon find(String summaryId, String attend) throws SerException {
        String[] strings = new String[]{summaryId};
        String[] attends = new String[]{attend};
        List<SummarySon> list = null;
        for (int i = 0; i < strings.length; i++) {
            String sql = "SELECT id,attend,opinion\n" +
                    "FROM negotiatemeeting_summaryson\n" +
                    "where summary_id='" + strings[i] + "' and attend='" + attends[i] + "'";
            String[] fileds = new String[]{"id", "attend", "opinion"};
            list = super.findBySql(sql, SummarySon.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}