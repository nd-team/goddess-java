package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.bo.SummaryBO;
import com.bjike.goddess.stockholdermeeting.bo.SummarySonBO;
import com.bjike.goddess.stockholdermeeting.dto.ExcelDTO;
import com.bjike.goddess.stockholdermeeting.dto.SummaryDTO;
import com.bjike.goddess.stockholdermeeting.entity.Organization;
import com.bjike.goddess.stockholdermeeting.entity.Summary;
import com.bjike.goddess.stockholdermeeting.entity.SummarySon;
import com.bjike.goddess.stockholdermeeting.enums.SummaryStatus;
import com.bjike.goddess.stockholdermeeting.excel.SummaryExcel;
import com.bjike.goddess.stockholdermeeting.to.SummaryExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummarySonTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 股东大会纪要业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "stockholdermeetingSerCache")
@Service
public class SummarySerImpl extends ServiceImpl<Summary, SummaryDTO> implements SummarySer {
    @Autowired
    private OrganizationSer organizationSer;
    @Autowired
    private SummarySonSer summarySonSer;

    @Override
    public SummaryBO save(SummaryTO to) throws SerException {
        Organization o = findByNumber(to.getMeetingNumber());
        if (o == null) {
            throw new SerException("没有该会议编号对应的会议信息");
        }
        OrganizationBO organizationBO = organizationSer.findByID(o.getId());
        Summary entity = BeanTransform.copyProperties(to, Summary.class, true);
        entity.setActualTime(DateUtil.parseDateTime(organizationBO.getPlanTime()));
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
        entity.setSummaryStatus(SummaryStatus.NORMAL);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SummaryBO.class);
    }

    @Override
    public void edit(SummaryTO to) throws SerException {
        Summary entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Summary.class, true);
        entity.setCreateTime(a);
        LocalDateTime actualTime = null;
        try {
            actualTime = DateUtil.parseDateTime(to.getActualTime());
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
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
        entity.setSummaryStatus(SummaryStatus.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        String actualAttend = entity.getActualAttend();
        String[] names = actualAttend.split("、");
    }

    @Override
    public List<SummaryBO> list(SummaryDTO dto) throws SerException {
        dto.getSorts().add("actualTime=desc");
        dto.getSorts().add("meetingNumber=desc");
        List<Summary> list = super.findByCis(dto, true);
        List<SummaryBO> boList = new ArrayList<SummaryBO>();
        for (Summary m : list) {
            List<SummarySon> list1 = summarySonSer.findBySummaryId(m.getId());
            if (list1.isEmpty()) {
                SummaryBO bo = BeanTransform.copyProperties(m, SummaryBO.class);
                Organization organization = findByNumber(m.getMeetingNumber());
                if (organization != null) {
                    OrganizationBO organizationBO = organizationSer.findByID(organization.getId());
                    bo.setMeetingNumber(organizationBO.getMeetingNumber());
                    bo.setMeetingFormat(organizationBO.getMeetingFormat());
                    bo.setArea(organizationBO.getArea());
                    bo.setHost(organizationBO.getHost());
                    bo.setOrganization(organizationBO.getOrganization());
                    bo.setPurpose(organizationBO.getPurpose());
                    boList.add(bo);
                }
            } else {
                for (SummarySon s : list1) {
                    SummaryBO bo = BeanTransform.copyProperties(m, SummaryBO.class);
                    Organization organization = findByNumber(m.getMeetingNumber());
                    if (organization != null) {
                        OrganizationBO organizationBO = organizationSer.findByID(organization.getId());
                        bo.setSummarySonId(s.getId());
                        bo.setSpeakDepartment(s.getSpeakDepartment());
                        bo.setSpeakJob(s.getSpeakJob());
                        bo.setSpeaker(s.getSpeaker());
                        bo.setSpeakContent(s.getSpeakContent());
                        bo.setAlter(s.getAlter());
                        bo.setAlterContent(s.getAlterContent());
                        bo.setMeetingNumber(organizationBO.getMeetingNumber());
                        bo.setMeetingFormat(organizationBO.getMeetingFormat());
                        bo.setArea(organizationBO.getArea());
                        bo.setHost(organizationBO.getHost());
                        bo.setOrganization(organizationBO.getOrganization());
                        bo.setPurpose(organizationBO.getPurpose());
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
        Organization organization = findByNumber(entity.getMeetingNumber());
        if (organization != null) {
            bo.setMeetingNumber(organization.getMeetingNumber());
            bo.setMeetingFormat(organization.getMeetingFormat());
            bo.setArea(organization.getArea());
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
    public byte[] exportExcel(ExcelDTO excelDTO) throws SerException {
        Summary summary = super.findById(excelDTO.getId());
        SummaryBO bo = BeanTransform.copyProperties(summary, SummaryBO.class);
        if (excelDTO.getSummarySonId() != null) {
            SummarySonBO sonBO = summarySonSer.findByID(excelDTO.getSummarySonId());
            bo.setSpeakDepartment(sonBO.getSpeakDepartment());
            bo.setSpeakJob(sonBO.getSpeakJob());
            bo.setSpeaker(sonBO.getSpeaker());
            bo.setSpeakContent(sonBO.getSpeakContent());
            bo.setAlter(sonBO.getAlter());
            bo.setAlterContent(sonBO.getAlterContent());
        }
        SummaryExcel summaryExcel = new SummaryExcel();
        BeanUtils.copyProperties(bo, summaryExcel);
        summaryExcel.setActualTime(summary.getActualTime());
        List<SummaryExcel> toList = new ArrayList<SummaryExcel>();
        toList.add(summaryExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public void leadExcel(List<SummaryExcelTO> toList) throws SerException {
        for (SummaryExcelTO to : toList) {
            String id = findId(to.getMeetingNumber());
            if (id == null) {
                throw new SerException("没有该会议编号对应的会议信息");
            }
            String actualTime = to.getActualTime().replace('T', ' ');
            to.setActualTime(actualTime);
            Summary summary=BeanTransform.copyProperties(to,Summary.class,true);
            summary.setSummaryStatus(SummaryStatus.NORMAL);
            summary.setId(id);
            summary.setCreateTime(super.findById(id).getCreateTime());
            try {
                summary.setActualTime(DateUtil.parseDateTime(actualTime));
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            summary.setModifyTime(LocalDateTime.now());
            super.update(summary);
            SummarySon summarySon = find(id, to.getSpeaker());
            if (summarySon != null) {
                SummarySonTO summarySonTO = new SummarySonTO();
                summarySonTO.setId(summarySon.getId());
                summarySonTO.setSpeakDepartment(to.getSpeakDepartment());
                summarySonTO.setSpeakJob(to.getSpeakJob());
                summarySonTO.setSpeaker(to.getSpeaker());
                summarySonTO.setSpeakContent(to.getSpeakContent());
                summarySonTO.setAlter(to.getAlter());
                summarySonTO.setAlterContent(to.getAlterContent());
                summarySonSer.edit(summarySonTO);
            } else {
                SummarySonTO summarySonTO = new SummarySonTO();
                summarySonTO.setMeetingNumber(to.getMeetingNumber());
                summarySonTO.setSpeakDepartment(to.getSpeakDepartment());
                summarySonTO.setSpeakJob(to.getSpeakJob());
                summarySonTO.setSpeaker(to.getSpeaker());
                summarySonTO.setSpeakContent(to.getSpeakContent());
                summarySonTO.setAlter(to.getAlter());
                summarySonTO.setAlterContent(to.getAlterContent());
                summarySonSer.save(summarySonTO);
            }
            String actualAttend = summary.getActualAttend();
            String[] names = actualAttend.split("、");
        }
    }

    @Override
    public void freeze(String id) throws SerException {
        Summary entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setSummaryStatus(SummaryStatus.FREEZE);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void thaw(String id) throws SerException {
        Summary entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setSummaryStatus(SummaryStatus.NORMAL);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 通过会议编号查找会议组织内容信息
     *
     * @param meetingNumber 会议编号
     * @return
     * @throws SerException
     */
    private Organization findByNumber(String meetingNumber) throws SerException {
        String[] nums = new String[]{meetingNumber};
        List<Organization> list = null;
        for (String s : nums) {
            String sql = "select id from stockholdermeeting_organization where meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, Organization.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过会议编号查找会议纪要id
     *
     * @param meetingNumber 会议编号
     * @return
     * @throws SerException
     */
    private String findId(String meetingNumber) throws SerException {
        String[] strings = new String[]{meetingNumber};
        List<Summary> list = null;
        for (String s : strings) {
            String sql = "SELECT id from stockholdermeeting_summary\n" +
                    "where meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, Summary.class, fileds);
        }
        if ((list != null) && (list.size() != 0)) {
            return list.get(0).getId();
        }
        return null;
    }

    /**
     * 通过会议纪要id和发言人查找会议纪要子表信息
     *
     * @param summaryId 纪要id
     * @param speaker   发言人
     * @return
     * @throws SerException
     */
    private SummarySon find(String summaryId, String speaker) throws SerException {
        String[] strings = new String[]{summaryId};
        String[] speakers = new String[]{speaker};
        List<SummarySon> list = null;
        for (int i = 0; i < strings.length; i++) {
            String sql = "SELECT id\n" +
                    "FROM stockholdermeeting_summaryson\n" +
                    "where summary_id='" + strings[i] + "' and speaker='" + speakers[i] + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, SummarySon.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }
}