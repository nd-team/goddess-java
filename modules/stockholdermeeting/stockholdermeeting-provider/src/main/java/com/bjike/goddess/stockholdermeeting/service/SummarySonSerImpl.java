package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.stockholdermeeting.bo.NoticeTemplateBO;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.bo.SummarySonBO;
import com.bjike.goddess.stockholdermeeting.dto.SummarySonDTO;
import com.bjike.goddess.stockholdermeeting.entity.Organization;
import com.bjike.goddess.stockholdermeeting.entity.Summary;
import com.bjike.goddess.stockholdermeeting.entity.SummarySon;
import com.bjike.goddess.stockholdermeeting.to.SummarySonTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 股东大会纪要子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "stockholdermeetingSerCache")
@Service
public class SummarySonSerImpl extends ServiceImpl<SummarySon, SummarySonDTO> implements SummarySonSer {
    @Autowired
    private SummarySer summarySer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SummarySonBO save(SummarySonTO to) throws SerException {
        SummarySon entity = BeanTransform.copyProperties(to, SummarySon.class, true);
        Summary summary = findByNumber(to.getMeetingNumber());
        if (summary == null) {
            throw new SerException("没有该会议编号对应的会议信息");
        }
        entity.setSummaryId(summary.getId());
        super.save(entity);
        return BeanTransform.copyProperties(entity, SummarySonBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(SummarySonTO to) throws SerException {
        SummarySon entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String id = entity.getSummaryId();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, SummarySon.class, true);
        entity.setCreateTime(a);
        entity.setSummaryId(id);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public SummarySonBO findByID(String id) throws SerException {
        SummarySon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, SummarySonBO.class);
    }

    @Override
    public List<SummarySonBO> list(SummarySonDTO dto) throws SerException {
        return null;
    }

    @Override
    public Long countNum(SummarySonDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<SummarySon> findBySummaryId(String summaryId) throws SerException {
        SummarySonDTO dto = new SummarySonDTO();
        dto.getConditions().add(Restrict.eq("summaryId", summaryId));
        return super.findByCis(dto);
    }

    @Override
    //修改发言内容
    public void editSpeak(SummarySonTO to) throws SerException {
        SummarySon entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAlter(to.getAlter());
        if (to.getAlter()) {
            entity.setAlterContent(to.getAlterContent());
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        Summary summary = summarySer.findById(entity.getSummaryId());
        NoticeTemplateBO bo = new NoticeTemplateBO();
        BeanUtils.copyProperties(summary, bo);
        Organization organization = findByNumber1(summary.getMeetingNumber());
        bo.setMeetingType(organization.getMeetingType());
        OrganizationBO bo1 = BeanTransform.copyProperties(organization, OrganizationBO.class);
        bo.setActualTime(DateUtil.dateToString(summary.getActualTime()));
        bo.setMeetingFormat(bo1.getMeetingFormat());
        bo.setArea(bo1.getArea());
        bo.setPlanAttend(bo1.getPlanAttend());
        bo.setMeetingTopic(bo1.getMeetingTopic());
        bo.setHost(bo1.getHost());
        bo.setOrganization(bo1.getOrganization());
        bo.setMeetingNumber(bo1.getMeetingNumber());
        bo.setMeetingType(bo1.getMeetingType());
        bo.setMeetingLevel(bo1.getMeetingLevel());
        //1、主送：公司各大部门公邮、公司总公邮、股东个人公邮；
        // 2、抄送：会议最终决议涉及的相关的模块负责人的个人邮箱
    }

    /**
     * 通过会议编号查找会议组织内容信息
     *
     * @param meetingNumber
     * @return
     * @throws SerException
     */
    private Summary findByNumber(String meetingNumber) throws SerException {
        String[] nums = new String[]{meetingNumber};
        List<Summary> list = null;
        for (String s : nums) {
            String sql = "SELECT id from stockholdermeeting_summary WHERE meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, Summary.class, fileds);
        }
        if ((list != null) && (!list.isEmpty())) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过会议编号查找会议组织内容信息
     *
     * @param meetingNumber 会议编号
     * @return
     * @throws SerException
     */
    private Organization findByNumber1(String meetingNumber) throws SerException {
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
}