package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.negotiatemeeting.bo.NoticeTemplateBO;
import com.bjike.goddess.negotiatemeeting.bo.OrganizationBO;
import com.bjike.goddess.negotiatemeeting.bo.SummarySonBO;
import com.bjike.goddess.negotiatemeeting.dto.SummarySonDTO;
import com.bjike.goddess.negotiatemeeting.entity.Organization;
import com.bjike.goddess.negotiatemeeting.entity.Summary;
import com.bjike.goddess.negotiatemeeting.entity.SummarySon;
import com.bjike.goddess.negotiatemeeting.to.SummarySonTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 协商会议纪要子表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-01 05:14 ]
 * @Description: [ 协商会议纪要子表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
@Service
public class SummarySonSerImpl extends ServiceImpl<SummarySon, SummarySonDTO> implements SummarySonSer {
    @Autowired
    private SummarySer summarySer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public SummarySonBO save(SummarySonTO to) throws SerException {
//        String name = userAPI.currentUser().getUsername();
        SummarySon entity = BeanTransform.copyProperties(to, SummarySon.class, true);
        String summaryId = findId(to.getMeetingNumber());
        entity.setSummaryId(summaryId);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SummarySonBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(SummarySonTO to) throws SerException {
        SummarySon entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAttend(to.getAttend());
        entity.setOpinion(to.getOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //修改个人发言意见
    public void editPerson(SummarySonTO to) throws SerException {
        SummarySon entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAttend(to.getAttend());
        entity.setOpinion(to.getOpinion());
        super.update(entity);
        Summary summary = summarySer.findById(entity.getSummaryId());
        NoticeTemplateBO bo = new NoticeTemplateBO();
        BeanUtils.copyProperties(summary, bo);
        String organizationId = summary.getOrganizationId();
        Organization organization = findByOrganizationId(organizationId);
        bo.setMeetingType(organization.getMeetingType());
        OrganizationBO bo1 = BeanTransform.copyProperties(organization, OrganizationBO.class);
        bo.setPlanTime(bo1.getPlanTime());
        bo.setActualTime(DateUtil.dateToString(summary.getActualTime()));
        bo.setMeetingFormat(organization.getMeetingFormat());
        bo.setMeetingArea(organization.getMeetingArea());
        bo.setPlanAttend(organization.getPlanAttend());
        bo.setMeetingTopic(organization.getMeetingTopic());
        bo.setHost(organization.getHost());
        bo.setOrganization(organization.getOrganization());
        bo.setMeetingNumber(organization.getMeetingNumber());
        bo.setReason(organization.getReason());
        String meetingLevel = organization.getMeetingLevel();
        //todo:根据公司层面发送邮件
    }

    @Override
    public List<SummarySon> findBySummaryId(String summaryId) throws SerException {
        SummarySonDTO dto = new SummarySonDTO();
        dto.getConditions().add(Restrict.eq("summaryId", summaryId));
        return super.findByCis(dto);
    }

    @Override
    public Long count(SummarySonDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public SummarySonBO findByID(String id) throws SerException {
        SummarySon entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, SummarySonBO.class);
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

    private String findId(String meetingNumber) throws SerException {
        String[] meetingNumbers = new String[]{meetingNumber};
        List<Organization> list = null;
        List<Summary> list1 = null;
        for (String s : meetingNumbers) {
            String sql = "SELECT id from negotiatemeeting_organization\n" +
                    "where meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id"};
            list = super.findBySql(sql, Organization.class, fileds);
            if ((list != null) && (!list.isEmpty())) {
                String[] ids = new String[]{list.get(0).getId()};
                for (String id : ids) {
                    String sql1 = "SELECT id FROM negotiatemeeting_summary\n" +
                            "where organization_id='" + id + "'";
                    String[] fileds1 = new String[]{"id"};
                    list1 = super.findBySql(sql1, Summary.class, fileds1);
                }
                if ((list1 != null) && (!list1.isEmpty())) {
                    return list1.get(0).getId();
                }
            }
        }
        return null;
    }
}