package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.communicatemeeting.bo.SummaryFeedbackBO;
import com.bjike.goddess.communicatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.communicatemeeting.entity.MeetingSummary;
import com.bjike.goddess.communicatemeeting.entity.OrganizeContent;
import com.bjike.goddess.communicatemeeting.entity.SummaryFeedback;
import com.bjike.goddess.communicatemeeting.to.SummaryFeedbackTO;
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
 * @Date: [ 2017-05-26 02:39 ]
 * @Description: [ 纪要反馈投诉业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "communicatemeetingSerCache")
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
        List<OrganizeContent> organizeContents = null;
        List<MeetingSummary> meetingSummarys = null;
        SummaryFeedbackBO bo = new SummaryFeedbackBO();
        for (String s : meetingNumbers) {
            String sql = "SELECT id,meetingType,meetingContent,meetingTopic\n" +
                    "FROM communicatemeeting_organizecontent\n" +
                    "WHERE meetingNumber='" + s + "'";
            String[] fileds = new String[]{"id", "meetingType", "meetingContent", "meetingTopic"};
            organizeContents = super.findBySql(sql, OrganizeContent.class, fileds);
        }
        if ((organizeContents != null) && (organizeContents.size() != 0)) {
            String id = organizeContents.get(0).getId();
            bo.setMeetingType(organizeContents.get(0).getMeetingType());
            bo.setMeetingContent(organizeContents.get(0).getMeetingContent());
            bo.setMeetingTopic(organizeContents.get(0).getMeetingTopic());
            String[] ids = new String[]{id};
            //DATE_FORMAT(actualTime,'%Y-%c-%d %H:%i:%s')
            for (String s : ids) {
                String sql = "SELECT actualTime,result\n" +
                        "FROM communicatemeeting_meetingsummary\n" +
                        "WHERE organizeContent_id='" + s + "'";
                String[] fileds = new String[]{"actualTime", "result"};
                meetingSummarys = super.findBySql(sql, MeetingSummary.class, fileds);
            }
            if ((meetingSummarys != null) && (meetingSummarys.size() != 0)) {
                bo.setActualTime(DateUtil.dateToString(meetingSummarys.get(0).getActualTime()));
                bo.setResult(meetingSummarys.get(0).getResult());
                bo.setMeetingNumber(meetingNumber);
                return bo;
            }
        }
        return null;
    }
}