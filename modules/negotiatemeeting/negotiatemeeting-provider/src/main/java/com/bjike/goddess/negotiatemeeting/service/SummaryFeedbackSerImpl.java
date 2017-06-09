package com.bjike.goddess.negotiatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.negotiatemeeting.bo.SummaryFeedbackBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.negotiatemeeting.entity.Organization;
import com.bjike.goddess.negotiatemeeting.entity.Summary;
import com.bjike.goddess.negotiatemeeting.entity.SummaryFeedback;
import com.bjike.goddess.negotiatemeeting.to.SummaryFeedbackTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 纪要反馈投诉业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:54 ]
 * @Description: [ 纪要反馈投诉业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "negotiatemeetingSerCache")
@Service
public class SummaryFeedbackSerImpl extends ServiceImpl<SummaryFeedback, SummaryFeedbackDTO> implements SummaryFeedbackSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public SummaryFeedbackBO save(SummaryFeedbackTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        SummaryFeedback entity = BeanTransform.copyProperties(to, SummaryFeedback.class, true);
        SummaryFeedbackBO bo = find(entity.getMeetingNumber());
        if (bo == null) {
            throw new SerException("该会议编号没有对应的会议信息");
        }
        entity.setObjection(name);
        super.save(entity);
        return BeanTransform.copyProperties(entity, SummaryFeedbackBO.class);
        //todo:发邮件到zhzybfk_aj@163.com
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(SummaryFeedbackTO to) throws SerException {
        //todo:只能编辑自己的反馈投诉
        SummaryFeedback entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setObjectionContent(to.getObjectionContent());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<SummaryFeedbackBO> list(SummaryFeedbackDTO dto) throws SerException {
        List<SummaryFeedback> list = super.findByCis(dto, true);
        List<SummaryFeedbackBO> boList = new ArrayList<SummaryFeedbackBO>();
        for (SummaryFeedback s : list) {
            SummaryFeedbackBO bo = find(s.getMeetingNumber());
            bo.setId(s.getId());
            bo.setObjection(s.getObjection());
            bo.setObjectionContent(s.getObjectionContent());
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public SummaryFeedbackBO findByID(String id) throws SerException {
        SummaryFeedback entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, SummaryFeedbackBO.class);
    }

    @Override
    public Long countNum(SummaryFeedbackDTO dto) throws SerException {
        return super.count(new SummaryFeedbackDTO());
    }

    /**
     * 根据会议编号获取会议的具体信息
     *
     * @param meetingNumber 会议编号
     * @return
     * @throws SerException
     */
    private SummaryFeedbackBO find(String meetingNumber) throws SerException {
        String[] meetingNumbers = new String[]{meetingNumber};
        List<Organization> organizations = null;
        List<Summary> summarys = null;
        SummaryFeedbackBO bo = new SummaryFeedbackBO();
        for (String s : meetingNumbers) {
            String sql = "SELECT id,meetingType,reason,purpose\n" +
                    "FROM negotiatemeeting_organization\n" +
                    "WHERE meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id", "meetingType", "reason", "purpose"};
            organizations = super.findBySql(sql, Organization.class, fileds);
        }
        if ((organizations != null) && (organizations.size() != 0)) {
            String id = organizations.get(0).getId();
            bo.setMeetingType(organizations.get(0).getMeetingType());
            bo.setReason(organizations.get(0).getReason());
            bo.setPurpose(organizations.get(0).getPurpose());
            String[] ids = new String[]{id};
            //DATE_FORMAT(actualTime,'%Y-%c-%d %H:%i:%s')
            for (String s : ids) {
                String sql = "SELECT actualTime,result\n" +
                        "FROM negotiatemeeting_summary\n" +
                        "WHERE organization_id='" + s + "'";
                String[] fileds = new String[]{"actualTime", "result"};
                summarys = super.findBySql(sql, Summary.class, fileds);
            }
            if ((summarys != null) && (summarys.size() != 0)) {
                bo.setActualTime(DateUtil.dateToString(summarys.get(0).getActualTime()));
                bo.setResult(summarys.get(0).getResult());
                bo.setMeetingNumber(meetingNumber);
                return bo;
            }
        }
        return null;
    }
}