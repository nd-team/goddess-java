package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.bo.VisitRecommSetBO;
import com.bjike.goddess.customer.dto.VisitRecommSetDTO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.CustomerBaseInfo;
import com.bjike.goddess.customer.entity.VisitRecommSet;
import com.bjike.goddess.customer.entity.VisitSchedule;
import com.bjike.goddess.customer.enums.RecommInfoUpdateFreq;
import com.bjike.goddess.customer.to.VisitRecommSetTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * 拜访推荐设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class VisitRecommSetSerImpl extends ServiceImpl<VisitRecommSet, VisitRecommSetDTO> implements VisitRecommSetSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CustomerBaseInfoSer customerBaseInfoSer;
    @Autowired
    private VisitScheduleSer visitScheduleSer;
    @Autowired
    private MessageAPI messageAPI;

    @Override
    public Long countVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        Long count = super.count(visitRecommSetDTO);
        return count;
    }

    @Override
    public List<VisitRecommSetBO> listVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        List<VisitRecommSet> visitRecommSetList = super.findByCis(visitRecommSetDTO, true);
        return BeanTransform.copyProperties(visitRecommSetList, VisitRecommSetBO.class);
    }

    @Override
    public VisitRecommSetBO getVisitRecoById(String id) throws SerException {
        VisitRecommSet visitRecommSet = super.findById(id);
        return BeanTransform.copyProperties(visitRecommSet, VisitRecommSetBO.class);
    }

    @Override
    public VisitRecommSetBO addVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {

        List<String> sendObjectList = visitRecommSetTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        VisitRecommSet visitRecommSet = BeanTransform.copyProperties(visitRecommSetTO, VisitRecommSet.class, true, "sendObject");
        visitRecommSet.setCreateTime(LocalDateTime.now());
        visitRecommSet.setStatus(Status.THAW);
        visitRecommSet.setUpdateDate(LocalDateTime.now());
        visitRecommSet.setCreatePersion(userAPI.currentUser().getUsername());
        //设置发送对象
        visitRecommSet.setSendObject(String.valueOf(emails));

        super.save(visitRecommSet);

        return BeanTransform.copyProperties(visitRecommSet, VisitRecommSetBO.class);
    }

    @Override
    public VisitRecommSetBO editVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {
        List<String> sendObjectList = visitRecommSetTO.getSendObject();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                if (!Validator.isEmail(emailStr)) {
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ",");
            }
        }
        VisitRecommSet temp = super.findById(visitRecommSetTO.getId());

        VisitRecommSet buySendEmail = BeanTransform.copyProperties(visitRecommSetTO, VisitRecommSet.class, true, "sendObject");
        //设置发送对象
        buySendEmail.setSendObject(String.valueOf(emails));

        BeanUtils.copyProperties(buySendEmail, temp, "id", "createTime", "createPersion", "status", "updateDate");
        temp.setModifyTime(LocalDateTime.now());
        temp.setUpdateDate(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());


        super.update(temp);
        return BeanTransform.copyProperties(temp, VisitRecommSetBO.class);
    }

    @Override
    public void deleteVisitReco(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void congealVisitReco(String id) throws SerException {
        VisitRecommSet visitRecommSet = super.findById(id);
        visitRecommSet.setStatus(Status.CONGEAL);
        super.update(visitRecommSet);
    }

    @Override
    public void thawVisitReco(String id) throws SerException {
        VisitRecommSet visitRecommSet = super.findById(id);
        visitRecommSet.setStatus(Status.THAW);
        super.update(visitRecommSet);
    }

    private void weekData() throws SerException {
        List<CustomerBaseInfoBO> customerBaseInfoBOList = customerBaseInfoSer.computations();
        LocalDate now = LocalDate.now();
        VisitScheduleDTO dto = new VisitScheduleDTO();
        dto.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
        dto.getConditions().add(Restrict.eq("month", LocalDate.now().getMonthValue()));
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        dto.getConditions().add(Restrict.eq("week", week));
        int maxWeek = DateUtil.getWeekNum(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        VisitSchedule visitSchedule = new VisitSchedule();
        VisitSchedule visitSchedule1 = visitScheduleSer.findOne(dto);
        int year1 = 0;
        int month1 = 0;
        int week1 = 0;
        if (week == maxWeek) {
            if (LocalDate.now().getMonthValue() == 12) {
                year1 = LocalDate.now().getYear() + 1;
                month1 = 1;
            } else {
                year1 = LocalDate.now().getYear();
                month1 = LocalDate.now().getMonthValue() + 1;
            }
            week1 = 1;
        } else {
            year1 = LocalDate.now().getYear();
            month1 = LocalDate.now().getMonthValue();
            week1 = week + 1;
        }
        VisitSchedule next = new VisitSchedule();   //下一周期的
        VisitScheduleDTO dto1 = new VisitScheduleDTO();
        dto1.getConditions().add(Restrict.eq("year", year1));
        dto1.getConditions().add(Restrict.eq("month", month1));
        dto1.getConditions().add(Restrict.eq("week", week1));
        VisitSchedule next1 = visitScheduleSer.findOne(dto1);
        int size = customerBaseInfoBOList.size() <= 36 ? customerBaseInfoBOList.size() : 36;
        int a = 0;
        int i = 0;
        switch (now.getDayOfWeek().getValue()) {
            case 1:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            visitSchedule.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 5:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            visitSchedule.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 5:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                    }
                }
                break;
            case 2:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 5:
                            next.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 5:
                            next.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                }
                break;
            case 3:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            next.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 4:
                            next.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                }
                break;
            case 4:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            next.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 3:
                            next.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                }
                break;
            case 5:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            next.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 3:
                            next.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(1)));
                            break;
                        case 2:
                            next.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 3:
                            next.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                }
                break;
            case 6:
                while (a + 5 < size) {
                    switch (i) {
                        case 0:
                            visitSchedule.setSaturday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            next.setMonday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 2:
                            next.setTuesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 3:
                            next.setWednesday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setThursday(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setFridays(get(a, a + 5, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                    a = a + 6;
                    i++;
                }
                if (size % 6 != 0) {
                    switch (i) {
                        case 0:
                            visitSchedule.setSaturday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now()));
                            break;
                        case 1:
                            next.setMonday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(2)));
                            break;
                        case 2:
                            next.setTuesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(3)));
                            break;
                        case 3:
                            next.setWednesday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(4)));
                            break;
                        case 4:
                            next.setThursday(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(5)));
                            break;
                        case 5:
                            next.setFridays(get(a, size - 1, customerBaseInfoBOList,LocalDateTime.now().plusDays(6)));
                            break;
                    }
                }
                break;
        }
        if (null != visitSchedule1) {
            BeanUtils.copyProperties(visitSchedule, visitSchedule1, "year", "month", "week");
            visitSchedule1.setRecommendDate(LocalDateTime.now());
            visitSchedule1.setModifyTime(LocalDateTime.now());
            visitScheduleSer.update(visitSchedule1);
        } else {
            visitSchedule.setRecommendDate(LocalDateTime.now());
            visitSchedule.setYear(LocalDate.now().getYear());
            visitSchedule.setMonth(LocalDate.now().getMonthValue());
            visitSchedule.setWeek(week);
            visitScheduleSer.save(visitSchedule);
        }
        if (null != next1) {
            BeanUtils.copyProperties(next, next1, "year", "month", "week");
            next1.setRecommendDate(LocalDateTime.now());
            next1.setModifyTime(LocalDateTime.now());
            visitScheduleSer.update(next1);
        } else {
            next.setRecommendDate(LocalDateTime.now());
            next.setYear(year1);
            next.setMonth(month1);
            next.setWeek(week1);
            visitScheduleSer.save(next);
        }
    }

    private void sendObject(String sendObject) throws SerException {
//        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
//        visitRecommSetDTO.getConditions().add(Restrict.eq("status",Status.THAW));
//        List<VisitRecommSet> visitRecommSetList = super.findByCis(visitRecommSetDTO);
//        if(){}
        //拜访日程表发送
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        VisitScheduleDTO visitScheduleDTO = new VisitScheduleDTO();
        visitScheduleDTO.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
        visitScheduleDTO.getConditions().add(Restrict.eq("month", LocalDate.now().getMonthValue()));
        visitScheduleDTO.getConditions().add(Restrict.eq("week", week));
        VisitSchedule visitSchedule = visitScheduleSer.findOne(visitScheduleDTO);
        //拼表格
        String content = htmlSign(visitSchedule);

        MessageTO messageTO = new MessageTO();
        messageTO.setContent(content);
        messageTO.setTitle("定时发送拜访日程表");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        //定时发送必须写
        messageTO.setSenderId("SYSTEM");
        messageTO.setSenderName("SYSTEM");

        messageTO.setReceivers(sendObject.split(";"));
        messageAPI.send(messageTO);
    }


    private String htmlSign(VisitSchedule visitSchedule) throws SerException {
        StringBuffer sb = new StringBuffer("");
        sb = new StringBuffer("<h4>本周拜访日程表:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");

        sb.append("<tr>");
        sb.append("<td>推荐时间</td>");
        sb.append("<td>年度</td>");
        sb.append("<td>月份</td>");
        sb.append("<td>周期</td>");
        sb.append("<td>周一</td>");
        sb.append("<td>周二</td>");
        sb.append("<td>周三</td>");
        sb.append("<td>周四</td>");
        sb.append("<td>周五</td>");
        sb.append("<td>周六</td>");

        sb.append("</tr>");

        //拼body部分
        sb.append("<tr>");
        sb.append("<td>" + visitSchedule.getRecommendDate() + "</td>");
        sb.append("<td>" + visitSchedule.getYear() + "</td>");
        sb.append("<td>" + visitSchedule.getMonth() + "</td>");
        sb.append("<td>" + visitSchedule.getWeek() + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getMonday()) ? " " : visitSchedule.getMonday()) + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getTuesday()) ? " " : visitSchedule.getTuesday()) + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getWednesday()) ? " " : visitSchedule.getWednesday()) + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getThursday()) ? " " : visitSchedule.getThursday()) + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getFridays()) ? " " : visitSchedule.getFridays()) + "</td>");
        sb.append("<td>" + (StringUtils.isBlank(visitSchedule.getSaturday()) ? " " : visitSchedule.getSaturday()) + "</td>");

        sb.append("</tr>");

        //结束
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public void checkUpdateWeightHour() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommInfoUpdateFreq", RecommInfoUpdateFreq.EVERYHOUR));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                int hour = visitRecommSet.getRecommInfoUpdateTime().getHour() - LocalDateTime.now().getHour();
                int minutes = visitRecommSet.getRecommInfoUpdateTime().getMinute() - LocalDateTime.now().getMinute();
                int ms = visitRecommSet.getRecommInfoUpdateTime().getSecond() - LocalDateTime.now().getSecond();
                if (hour <= 0 && minutes == 0 && ms == 0) {
                    weekData();
                }
            }
        }

    }

    @Override
    public void checkUpdateWeightDay() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommInfoUpdateFreq", RecommInfoUpdateFreq.EVERYDAY));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDate date = LocalDate.now();
                LocalDateTime dateTime = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommInfoUpdateTime().getHour(), visitRecommSet.getRecommInfoUpdateTime().getMinute(), visitRecommSet.getRecommInfoUpdateTime().getSecond());
                if (dateTime == LocalDateTime.now()) {
                    weekData();
                }
            }
        }
    }

    @Override
    public void checkUpdateWeightWeek() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommInfoUpdateFreq", RecommInfoUpdateFreq.EVERYWEEK));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDate date = LocalDate.now();
                if (visitRecommSet.getRecommInfoUpdateTime().getDayOfWeek().getValue()==date.getDayOfWeek().getValue()){
                    LocalDateTime dateTime = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommInfoUpdateTime().getHour(), visitRecommSet.getRecommInfoUpdateTime().getMinute(), visitRecommSet.getRecommInfoUpdateTime().getSecond());
                    if (dateTime==LocalDateTime.now()){
                        weekData();
                    }
                }
            }
        }
    }




    @Override
    public void checkUpdateWeightMonth() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommInfoUpdateFreq", RecommInfoUpdateFreq.EVERYMONTH));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDate date = LocalDate.now();
                if (visitRecommSet.getRecommInfoUpdateTime().getDayOfMonth()==date.getDayOfMonth()){
                    LocalDateTime dateTime = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommInfoUpdateTime().getHour(), visitRecommSet.getRecommInfoUpdateTime().getMinute(), visitRecommSet.getRecommInfoUpdateTime().getSecond());
                    if (dateTime==LocalDateTime.now()){
                        weekData();
                    }
                }
            }
        }
    }

    @Override
    public void checkSendObjectHour() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommendInterval", RecommInfoUpdateFreq.EVERYHOUR));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                int sendHour = visitRecommSet.getRecommendDate().getHour() - LocalDateTime.now().getHour();
                int sendMinutes = visitRecommSet.getRecommendDate().getMinute() - LocalDateTime.now().getMinute();
                int sendMs = visitRecommSet.getRecommendDate().getSecond() - LocalDateTime.now().getSecond();
                if (sendHour <= 0 && sendMinutes == 0 && sendMs == 0) {
                    sendObject(visitRecommSet.getSendObject());
                }
            }
        }
    }
    @Override
    public void checkSendObjectDay() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommendInterval", RecommInfoUpdateFreq.EVERYDAY));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        LocalDate date = LocalDate.now();
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDateTime dateTime2 = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommendDate().getHour(), visitRecommSet.getRecommendDate().getMinute(), visitRecommSet.getRecommendDate().getSecond());
                if (dateTime2 == LocalDateTime.now()) {
                    sendObject(visitRecommSet.getSendObject());
                }
            }
        }
    }
    @Override
    public void checkSendObjectWeek() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommendInterval", RecommInfoUpdateFreq.EVERYWEEK));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDate date = LocalDate.now();
                if(visitRecommSet.getRecommendDate().getDayOfWeek().getValue()==date.getDayOfWeek().getValue()){
                    LocalDateTime dateTime2 = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommendDate().getHour(), visitRecommSet.getRecommendDate().getMinute(), visitRecommSet.getRecommendDate().getSecond());
                    if (dateTime2 == LocalDateTime.now()) {
                        sendObject(visitRecommSet.getSendObject());
                    }
                }
            }
        }
    }
    @Override
    public void checkSendObjectMonth() throws SerException {
        VisitRecommSetDTO visitRecommSetDTO = new VisitRecommSetDTO();
        visitRecommSetDTO.getConditions().add(Restrict.eq("recommendInterval", RecommInfoUpdateFreq.EVERYMONTH));
        visitRecommSetDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> list = super.findByCis(visitRecommSetDTO);
        if (list != null && list.size() > 0) {
            for (VisitRecommSet visitRecommSet : list) {
                LocalDate date = LocalDate.now();
                if(visitRecommSet.getRecommendDate().getDayOfMonth()==date.getDayOfMonth()){
                    LocalDateTime dateTime2 = LocalDateTime.of(date.getYear(), date.getMonthValue(), visitRecommSet.getRecommendDate().getHour(), visitRecommSet.getRecommendDate().getMinute(), visitRecommSet.getRecommendDate().getSecond());
                    if (dateTime2 == LocalDateTime.now()) {
                        sendObject(visitRecommSet.getSendObject());
                    }
                }
            }
        }
    }

    private String get(int b, int a, List<CustomerBaseInfoBO> customerBaseInfoBOList,LocalDateTime dateTime) throws SerException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = b; i <= a; i++) {
            if (i == a) {
                stringBuilder.append(customerBaseInfoBOList.get(a).getCustomerName());
            } else {
                stringBuilder.append(customerBaseInfoBOList.get(a).getCustomerName() + ";");
            }
            CustomerBaseInfoBO customerBaseInfoBO = customerBaseInfoBOList.get(a);
            CustomerBaseInfo customerBaseInfo = customerBaseInfoSer.findById(customerBaseInfoBO.getId());
            customerBaseInfo.setRecommendVisitTime(dateTime);
            customerBaseInfo.setModifyTime(LocalDateTime.now());
            customerBaseInfoSer.update(customerBaseInfo);
        }
        return stringBuilder.toString();
    }

    //提醒
    private void remindSend() throws SerException{
        VisitRecommSetDTO dto = new VisitRecommSetDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<VisitRecommSet> sets = super.findByCis(dto); //规则
        String[] fields = new String[]{"customerNum","customerName","recommendVisitTime"};
        //查询6条
        String sql ="select customerNum,customerName,recommendVisitTime from where recommendVisitTime=(select max(recommendVisitTime) from customer_customerbaseinfo) limit 1,6";
        List<CustomerBaseInfo> infos = customerBaseInfoSer.findBySql(sql, CustomerBaseInfo.class, fields);
        LocalDateTime maxTime = infos.get(0).getRecommendVisitTime();
        String now = StringUtils.substringBefore(LocalDateTime.now().toString(),":");
//        List<String> mails = new ArrayList<String>(); //邮件列表
        StringBuffer content = new StringBuffer(); //内容
        for (CustomerBaseInfo customerBaseInfo : infos){
            content.append("编号为"+customerBaseInfo.getCustomerNum()+"的客户:"+customerBaseInfo.getCustomerName()+"拜访时间为:"+customerBaseInfo.getRecommendVisitTime()+";\n");
        }
        for(VisitRecommSet set: sets){
            int code = set.getReminderVisit().getCode();
            String strMax=""; //最大时间字符串
            switch (code){
                case 0:
                    strMax =  StringUtils.substringBefore(maxTime.minusHours(1).toString(),":");
                    break;
                case 1:
                    strMax =  StringUtils.substringBefore(maxTime.minusDays(1).toString(),":");
                    break;
                case 2:
                    strMax =  StringUtils.substringBefore(maxTime.minusMonths(1).toString(),":");
                    break;
                case 3:
                    strMax =  StringUtils.substringBefore(maxTime.minusYears(1).toString(),":");
                    break;
                    default: break;
            }
            if(strMax.equals(now)){

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content.toString());
                messageTO.setTitle("定时提醒发送邮件");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(set.getSendObject().split(";"));
                messageAPI.send(messageTO);
//                send(content,mails);
            }
        }
    }

}