package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.api.MeetingLayAPI;
import com.bjike.goddess.allmeeting.bo.AllMeetingOrganizeBO;
import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.entity.*;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.util.ChineseCharToEn;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有工作内容汇总会议组织内容业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "allmeetingSerCache")
@Service
public class AllMeetingOrganizeSerImpl extends ServiceImpl<AllMeetingOrganize, AllMeetingOrganizeDTO> implements AllMeetingOrganizeSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MeetingLayAPI meetingLayAPI;
    @Autowired
    private MeetingLaySer meetingLaySer;
    @Autowired
    private ConciseSummarySer conciseSummarySer;
    @Autowired
    private MultiwheelSummarySer multiwheelSummarySer;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AllMeetingOrganizeBO insertModel(AllMeetingOrganizeTO to) throws SerException {

        MeetingLay meetingLay = meetingLaySer.findById(to.getLayId());
        if (meetingLay != null) {
            AllMeetingOrganize model = BeanTransform.copyProperties(to, AllMeetingOrganize.class, true);
            model.setStatus(Status.THAW);
            model.setOrganizer(userAPI.currentUser().getUsername());
            model.setMeetingLay(meetingLay);
            model.setMeetingNum(getNumber(model));
            super.save(model);

            insertSummary(model, meetingLay.getMeetingTopic());

            return BeanTransform.copyProperties(model, AllMeetingOrganizeBO.class);
        } else {
            throw new SerException("非法层面ID,层面对象不能为空!");
        }

    }

    //根据议题生成对应的纪要模板--(简洁讨论纪要或三轮讨论纪要)
    public void insertSummary(AllMeetingOrganize model, MeetingTopic topic) throws SerException {
        if (topic != null) {
            //简洁讨论纪要模板
            if (topic.getTopic().equals("财务报表通报") ||
                    topic.getTopic().equals("问题分配责任模块【仅福利模块召开】") ||
                    topic.getTopic().equals("工作汇总和计划") ||
                    topic.getTopic().equals("问题分类【仅福利模块召开】") ||
                    topic.getTopic().equals("组织结构调整【人事任命】") ||
                    topic.getTopic().equals("组织结构调整【人事调整】") ||
                    topic.getTopic().equals("动员--统一思想") ||
                    topic.getTopic().equals("制度推行") ||
                    topic.getTopic().equals("每月资金准备") ||
                    topic.getTopic().equals("节点标准") ||
                    topic.getTopic().equals("费用标准")) {

                ConciseSummary conciseSummary = new ConciseSummary();
                conciseSummary.setMeetingNum(model.getMeetingNum());
                //默认实际时间为计划时间
                conciseSummary.setActualTime(model.getPlanTime());
                //默认会议主持人
                conciseSummary.setCompere(model.getCompere());
                conciseSummary.setStatus(Status.THAW);
                conciseSummarySer.save(conciseSummary);

            } else if (topic.getTopic().equals("临时事件") ||
                    topic.getTopic().equals("制度制定") ||
                    topic.getTopic().equals("集思广益") ||
                    topic.getTopic().equals("定指标") ||
                    topic.getTopic().equals("处罚方案、奖励方案讨论")) {
                MultiwheelSummary multiwheelSummary = new MultiwheelSummary();
                multiwheelSummary.setMeetingNum(model.getMeetingNum());
                //默认实际时间为计划时间
                multiwheelSummary.setActualTime(model.getPlanTime());
                //默认会议主持人
                multiwheelSummary.setCompere(model.getCompere());
                multiwheelSummary.setStatus(Status.THAW);
                multiwheelSummarySer.save(multiwheelSummary);
            } else {
                throw new SerException("议题不符合流程图规则，无法生成对应的会议纪要，请联系管理员!");
            }
        } else {
            throw new SerException("层面对应的议题不存在，请联系管理员!");
        }
    }

    public String getNumber(AllMeetingOrganize model) throws SerException {
        StringBuilder number = new StringBuilder("ALL-");
        MeetingLayBO meetingLayBO = meetingLayAPI.findById(model.getMeetingLay().getId());
        //获取层面名称首字母大写
        String layName = ChineseCharToEn.getAllFirstLetter(meetingLayBO.getName());
        number.append(layName);
        number.append("-");
        number.append(LocalDate.now().toString().replace("-", ""));
        number.append("-");
        //查询最新的编号
        AllMeetingOrganizeDTO dto = new AllMeetingOrganizeDTO();
        dto.getSorts().add("createTime=desc");
        dto.setLimit(1);
        List<AllMeetingOrganize> list = super.findByPage(dto);
        if (list != null && !list.isEmpty()) {
            String lastNumStr = list.get(0).getMeetingNum();
            //截取最新编号
            Integer lastNum = Integer.parseInt(lastNumStr.substring(lastNumStr.lastIndexOf("-") + 1));
            if (lastNum < 9) {
                number.append("000");
            } else if (lastNum < 99) {
                number.append("00");
            } else if (lastNum < 999) {
                number.append("0");
            } else if (lastNum < 9999) {

            } else {
                throw new SerException("编号溢出，请联系管理员");
            }
            number.append(++lastNum);
        } else {
            number.append("0001");
        }
        return number.toString();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public AllMeetingOrganizeBO updateModel(AllMeetingOrganizeTO to) throws SerException {
        //编辑修改层面不考虑编号变化，编号只于新增是初始化，如果编号不正确，请先考虑是否修改过层面
        AllMeetingOrganize model = super.findById(to.getId());
        if (model != null) {
            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
        return BeanTransform.copyProperties(model, AllMeetingOrganizeBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void freeze(String id) throws SerException {
        AllMeetingOrganize model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.CONGEAL);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void unfreeze(String id) throws SerException {
        AllMeetingOrganize model = super.findById(id);
        if (model != null) {
            model.setStatus(Status.THAW);
            model.setModifyTime(LocalDateTime.now());
            super.update(model);
        } else {
            throw new SerException("非法Id，更新对象不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<AllMeetingOrganizeBO> pageList(AllMeetingOrganizeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        if (dto.getStatus() != null) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        List<AllMeetingOrganize> list = super.findByPage(dto);
        List<AllMeetingOrganizeBO> boList = null;
        if (!CollectionUtils.isEmpty(list)) {
            boList = new ArrayList<AllMeetingOrganizeBO>();
            for (AllMeetingOrganize model : list) {
                AllMeetingOrganizeBO bo = BeanTransform.copyProperties(model, AllMeetingOrganizeBO.class);
                bo.setTopic(model.getMeetingLay().getMeetingTopic().getTopic());
                bo.setTopicContent(model.getMeetingLay().getMeetingTopic().getTopicContent());
                bo.setLayName(model.getMeetingLay().getName());
                bo.setPosition(model.getMeetingLay().getPosition());
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public void validNum(String meetingNum) throws SerException {
        AllMeetingOrganizeDTO dto = new AllMeetingOrganizeDTO();
        dto.getConditions().add(Restrict.eq("meetingNum", meetingNum));
        List<AllMeetingOrganize> organizeList = super.findByPage(dto);

        if (organizeList != null && !organizeList.isEmpty()) {

        } else {
            throw new SerException("会议编号不存在!");
        }
    }
}