package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.communicatemeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.communicatemeeting.bo.NoticeTemplateBO;
import com.bjike.goddess.communicatemeeting.bo.OrganizeContentBO;
import com.bjike.goddess.communicatemeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.communicatemeeting.entity.MeetingSummary;
import com.bjike.goddess.communicatemeeting.entity.OrganizeContent;
import com.bjike.goddess.communicatemeeting.excel.MeetingSummaryExcel;
import com.bjike.goddess.communicatemeeting.to.MeetingSummaryTO;
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
 * 交流会纪要业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "communicatemeetingSerCache")
@Service
public class MeetingSummarySerImpl extends ServiceImpl<MeetingSummary, MeetingSummaryDTO> implements MeetingSummarySer {
    @Autowired
    private OrganizeContentSer organizeContentSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public MeetingSummaryBO save(MeetingSummaryTO to) throws SerException {
        MeetingSummary entity = BeanTransform.copyProperties(to, MeetingSummary.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, MeetingSummaryBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(MeetingSummaryTO to) throws SerException {
        MeetingSummary entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
//        LocalDateTime time = null;
//        try {
//            time = DateUtil.parseDateTime(to.getActualTime());
//        } catch (Exception e) {
//            throw new SerException("输入的时间格式不正确");
//        }
        String id = entity.getOrganizeContentId();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, MeetingSummary.class, true);
        entity.setCreateTime(a);
        entity.setModifyTime(LocalDateTime.now());
        entity.setOrganizeContentId(id);
        String[] actualAttends = to.getActualPeoples();
        String[] addAttends = to.getAddPeoples();
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
        entity.setActualPeople(sb1.toString());
        entity.setAddPeople(sb2.toString());
        entity.setNotAttend(sb3.toString());
        super.update(entity);
        NoticeTemplateBO bo = new NoticeTemplateBO();
        BeanUtils.copyProperties(entity, bo);
        String organizeContentId = entity.getOrganizeContentId();
        OrganizeContent organizeContent = findByOrganizeContentId(organizeContentId);
//        String time1 = DateUtil.dateToString(entity.getActualTime());
//        String s = organizeContent.getMeetingNumber().substring(0, 6);
//        organizeContentSer.updateNumer(organizeContentId, s + time1);
//        bo.setMeetingType(organizeContent.getMeetingType());
        bo.setMeetingNumber(organizeContent.getMeetingNumber());
        OrganizeContentBO bo1 = BeanTransform.copyProperties(organizeContent, OrganizeContentBO.class);
        bo.setPlanTime(bo1.getPlanTime());
        bo.setActualTime(to.getActualTime());
        bo.setMeetingFormat(organizeContent.getMeetingFormat());
        bo.setMeetingArea(organizeContent.getMeetingArea());
        bo.setPlanPeople(organizeContent.getPlanPeople());
        bo.setMeetingTopic(organizeContent.getMeetingTopic());
        bo.setMeetingContent(organizeContent.getMeetingContent());
        bo.setHost(organizeContent.getHost());
        bo.setOrganization(organizeContent.getOrganization());
        //todo:根据公司层面发送邮件
    }

    @Override
    public List<MeetingSummaryBO> list(MeetingSummaryDTO dto) throws SerException {
        //todo:除参会人员外的人员查阅，需要经过总经办的同意才能调阅会议记录
        List<MeetingSummary> list = super.findByCis(dto, true);
        List<MeetingSummaryBO> boList = new ArrayList<MeetingSummaryBO>();
        for (MeetingSummary m : list) {
            MeetingSummaryBO bo = BeanTransform.copyProperties(m, MeetingSummaryBO.class);
            OrganizeContent organizeContent = findByOrganizeContentId(m.getOrganizeContentId());
            if (organizeContent != null) {
                bo.setMeetingNumber(organizeContent.getMeetingNumber());
                bo.setMeetingFormat(organizeContent.getMeetingFormat());
                bo.setMeetingArea(organizeContent.getMeetingArea());
                bo.setMeetingTopic(organizeContent.getMeetingTopic());
                bo.setMeetingContent(organizeContent.getMeetingContent());
                bo.setHost(organizeContent.getHost());
                bo.setOrganization(organizeContent.getOrganization());
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public MeetingSummaryBO findByID(String id) throws SerException {
        MeetingSummary entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, MeetingSummaryBO.class);
    }

    @Override
    public Long countNum(MeetingSummaryDTO dto) throws SerException {
        return super.count(new MeetingSummaryDTO());
    }

    @Override
    public Set<String> meetingNumbers() throws SerException {
        List<MeetingSummaryBO> list = list(new MeetingSummaryDTO());
        Set<String> set = new HashSet<String>();
        for (MeetingSummaryBO m : list) {
            set.add(m.getMeetingNumber());
        }
        return set;
    }

    @Override
    public byte[] exportExcel(String id) throws SerException {
        MeetingSummary meetingSummary = super.findById(id);
        MeetingSummaryBO bo = BeanTransform.copyProperties(meetingSummary, MeetingSummaryBO.class);
        OrganizeContent organizeContent = findByOrganizeContentId(meetingSummary.getOrganizeContentId());
        if (organizeContent != null) {
            bo.setMeetingNumber(organizeContent.getMeetingNumber());
        }
        MeetingSummaryExcel meetingSummaryExcel = new MeetingSummaryExcel();
        BeanUtils.copyProperties(bo, meetingSummaryExcel);
        List<MeetingSummaryExcel> toList = new ArrayList<MeetingSummaryExcel>();
        toList.add(meetingSummaryExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void leadExcel(List<MeetingSummaryExcel> toList) throws SerException {
        for (MeetingSummaryExcel to : toList) {
            OrganizeContent organizeContent = findByNumber(to.getMeetingNumber());
            if (organizeContent == null) {
                throw new SerException("没有该会议编号对应的会议信息");
            }
            String organizeContentId = organizeContent.getId();
            String id = findId(organizeContentId);
            if (id == null) {
                throw new SerException("没有该会议编号对应的会议信息");
            }
            MeetingSummary meetingSummary = new MeetingSummary();
            BeanUtils.copyProperties(to, meetingSummary);
            meetingSummary.setActualTime(DateUtil.parseDateTime(to.getActualTime()));
            meetingSummary.setOrganizeContentId(organizeContentId);
            meetingSummary.setId(id);
            meetingSummary.setCreateTime(super.findById(id).getCreateTime());
            meetingSummary.setModifyTime(LocalDateTime.now());
            super.update(meetingSummary);
            NoticeTemplateBO bo = new NoticeTemplateBO();
            BeanUtils.copyProperties(meetingSummary, bo);
            String organizeContentId1 = meetingSummary.getOrganizeContentId();
            OrganizeContent organizeContent1 = findByOrganizeContentId(organizeContentId1);
            String time1 = DateUtil.dateToString(meetingSummary.getActualTime());
            String s = organizeContent1.getMeetingNumber().substring(0, 6);
            organizeContentSer.updateNumer(organizeContentId, s + time1);
            bo.setMeetingType(organizeContent1.getMeetingType());
            bo.setMeetingNumber(s + time1);
            OrganizeContentBO bo1 = BeanTransform.copyProperties(organizeContent1, OrganizeContentBO.class);
            bo.setPlanTime(bo1.getPlanTime());
            bo.setActualTime(to.getActualTime());
            bo.setMeetingFormat(organizeContent1.getMeetingFormat());
            bo.setMeetingArea(organizeContent1.getMeetingArea());
            bo.setPlanPeople(organizeContent1.getPlanPeople());
            bo.setMeetingTopic(organizeContent1.getMeetingTopic());
            bo.setMeetingContent(organizeContent1.getMeetingContent());
            bo.setHost(organizeContent1.getHost());
            bo.setOrganization(organizeContent1.getOrganization());
            //todo:根据公司层面发送邮件
        }
    }

    /**
     * 通过会议组织id查找具体的会议信息
     *
     * @param id 会议组织id
     * @return
     * @throws SerException
     */
    private OrganizeContent findByOrganizeContentId(String id) throws SerException {
        String[] ids = new String[]{id};
        List<OrganizeContent> list = null;
        for (String s : ids) {
            String sql = "SELECT meetingNumber,meetingFormat,meetingArea,meetingTopic,meetingContent,host,organization\n" +
                    "FROM communicatemeeting_organizecontent\n" +
                    "WHERE id='" + s + "'";
            String[] fileds = new String[]{"meetingNumber", "meetingFormat", "meetingArea", "meetingTopic", "meetingContent", "host", "organization"};
            list = super.findBySql(sql, OrganizeContent.class, fileds);
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
    private OrganizeContent findByNumber(String meetingNumber) throws SerException {
        String[] meetingNumbers = new String[]{meetingNumber};
        List<OrganizeContent> list = null;
        for (String s : meetingNumbers) {
            String sql = "SELECT id,meetingFormat,meetingArea,meetingTopic,meetingContent,host,organization\n" +
                    "FROM communicatemeeting_organizecontent\n" +
                    "WHERE meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id", "meetingFormat", "meetingArea", "meetingTopic", "meetingContent", "host", "organization"};
            list = super.findBySql(sql, OrganizeContent.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过会议组织id查找会议纪要id
     *
     * @param organizeContentId 会议组织id
     * @return
     * @throws SerException
     */
    private String findId(String organizeContentId) throws SerException {
        String[] strings = new String[]{organizeContentId};
        List<MeetingSummary> list = null;
        for (String s : strings) {
            String sql = "SELECT id from communicatemeeting_meetingsummary\n" +
                    "where organizeContent_id='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, MeetingSummary.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0).getId();
        }
        return null;
    }
}