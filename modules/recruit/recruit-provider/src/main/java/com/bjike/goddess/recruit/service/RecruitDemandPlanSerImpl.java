package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.recruit.bo.*;
import com.bjike.goddess.recruit.dto.RecruitDemandPlanDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.entity.RecruitDemandPlan;
import com.bjike.goddess.recruit.to.ChannelTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitDemandPlanTO;
import com.bjike.goddess.recruit.type.GuideAddrStatus;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 招聘需求与计划业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘需求与计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class RecruitDemandPlanSerImpl extends ServiceImpl<RecruitDemandPlan, RecruitDemandPlanDTO> implements RecruitDemandPlanSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1",null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3",null);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(RecruitDemandPlanDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public RecruitDemandPlanBO getId(String id) throws SerException {
        RecruitDemandPlan recruitDemandPlan = super.findById(id);
        RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
        return bo;
    }

    @Override
    public List<RecruitDemandPlanBO> list(RecruitDemandPlanDTO dto) throws SerException {
        dto.getSorts().add("priority=asc");
        dto.getSorts().add("completeRecruit=desc");
        checkSeeIdentity();
        search(dto);
        List<RecruitDemandPlan> recruitDemandPlans = super.findByCis(dto,true);
        List<RecruitDemandPlanBO> recruitDemandPlanBOS = BeanTransform.copyProperties(recruitDemandPlans, RecruitDemandPlanBO.class);
        return recruitDemandPlanBOS;
    }

    private List<RecruitDemandPlanBO> search(RecruitDemandPlanDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.like("area", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            dto.getConditions().add(Restrict.like("projectGroup", dto.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(dto.getPosition())) {
            dto.getConditions().add(Restrict.like("position", dto.getPosition()));
        }
        if (null != dto.getPriority()) {
            dto.getConditions().add(Restrict.eq("priority", dto.getPriority()));
        }
        if (null != dto.getCompleteRecruit()) {
            dto.getConditions().add(Restrict.eq("completeRecruit", dto.getCompleteRecruit()));
        }
        List<RecruitDemandPlan> recruitDemandPlans = super.findByCis(dto);
        List<RecruitDemandPlanBO> recruitDemandPlanBOS = BeanTransform.copyProperties(recruitDemandPlans, RecruitDemandPlanBO.class);
        return recruitDemandPlanBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO save(RecruitDemandPlanTO to) throws SerException {
        checkAddIdentity();
        RecruitDemandPlan recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
        recruitDemandPlan.setCreateTime(LocalDateTime.now());
        super.save(recruitDemandPlan);
        RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO update(RecruitDemandPlanTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            RecruitDemandPlan recruitDemandPlan = super.findById(to.getId());
            LocalDateTime createTime = recruitDemandPlan.getCreateTime();
            recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
            recruitDemandPlan.setCreateTime(createTime);
            recruitDemandPlan.setModifyTime(LocalDateTime.now());
            super.update(recruitDemandPlan);
            RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RecruitDemandPlanBO makePlan(RecruitDemandPlanTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            RecruitDemandPlan recruitDemandPlan = super.findById(to.getId());
            LocalDateTime createTime = recruitDemandPlan.getCreateTime();
            recruitDemandPlan = BeanTransform.copyProperties(to, RecruitDemandPlan.class, true);
            recruitDemandPlan.setCreateTime(createTime);
            recruitDemandPlan.setModifyTime(LocalDateTime.now());
            super.update(recruitDemandPlan);
            RecruitDemandPlanBO bo = BeanTransform.copyProperties(recruitDemandPlan, RecruitDemandPlanBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<RecruitProgressBO> dayCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitProgressBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitProgressBO> monthCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitProgressBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitProgressBO> yearCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitProgressBO> totalCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select time as time from  " + getTableName(RecruitDemandPlan.class) + " where time<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<RecruitDetailsBO> dayRecruit(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startDate = time;
        String endDate = time;
        return recruitCollect(startDate, endDate);
    }

    @Override
    public List<RecruitDetailsBO> weekRecruit(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        return recruitCollect(startDate, endDate);
    }

    @Override
    public List<RecruitDetailsBO> monthRecruit(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return recruitCollect(startDate, endDate);
    }

    private List<RecruitDetailsBO> recruitCollect(String startDate, String endDate) throws SerException {
        List<RecruitDetailsBO> boList = new ArrayList<>();
        List<RecruitDetailsBO> recruitDetailsBOS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        //招聘地区 招聘部门/项目组 招聘岗位 实际筛选简历数 简历筛选数量  电访数量
        String[] fields = new String[]{"area", "projectGroup", "position", "realityScreenNum", "resumeNum", "phoneNum"};
        sb.append(" SELECT area AS area,projectGroup as projectGroup,position AS position, ");
        sb.append(" count(*) AS realityScreenNum,count(*) AS resumeNum,count(whetherPhoneSuccess) AS phoneNum ");
        sb.append(" FROM recruit_firstphonerecord WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "' ");
        sb.append(" GROUP BY area,projectGroup,position ");
        List<RecruitDetailsBO> detailsBOS = super.findBySql(sb.toString(), RecruitDetailsBO.class, fields);
        for (RecruitDetailsBO bo : detailsBOS) {
            //计划招聘人数 计划筛选简历数
            String[] planField = new String[]{"area", "projectGroup", "position", "planInterviewNum", "planScreenNum"};
            String sql = " SELECT area AS area,projectGroup as projectGroup,position AS position, " +
                    " sum(planRecruitNum) AS planInterviewNum,sum(planScreenNum) AS planScreenNum " +
                    " FROM recruit_recruitdemandplan " +
                    " WHERE area='" + bo.getArea() + "' AND projectGroup = '" + bo.getProjectGroup() + "' AND position = '" + bo.getPosition() + "'" +
                    " GROUP BY area,projectGroup,position ";
            recruitDetailsBOS = super.findBySql(sql, RecruitDetailsBO.class, planField);
            for (RecruitDetailsBO bo1 : recruitDetailsBOS) {
                bo.setPlanInterviewNum(bo1.getPlanInterviewNum());
                bo.setPlanScreenNum(bo1.getPlanScreenNum());
            }
            //下载简历数差异(实际筛选简历数-计划筛选简历数)
            if (bo.getRealityScreenNum() != null && bo.getPlanScreenNum() != null) {
                bo.setDownloadBalance(bo.getRealityScreenNum() - bo.getPlanScreenNum());
            }
            //预约面试量
            String[] orderField = new String[]{"area", "projectGroup", "position", "orderNum"};
            String orderSql = " SELECT area AS area,projectGroup as projectGroup,position AS position, " +
                    " count(whetherPhoneSuccess) AS orderNum FROM recruit_firstphonerecord " +
                    " WHERE area='" + bo.getArea() + "' AND projectGroup = '" + bo.getProjectGroup() + "' AND position = '" + bo.getPosition() + "' and whetherPhoneSuccess = 1 " +
                    " GROUP BY area,projectGroup,position ";
            recruitDetailsBOS = super.findBySql(orderSql, RecruitDetailsBO.class, orderField);
            for (RecruitDetailsBO bo1 : recruitDetailsBOS) {
                bo.setOrderNum(bo1.getOrderNum());
            }
            //成功入职数
            String[] successField = new String[]{"area", "projectGroup", "position", "successEntryNum"};
            String successSql = " SELECT area AS area,department as projectGroup,position AS position, " +
                    "  count(whetherEntry) AS successEntryNum FROM recruit_interviewinfor " +
                    " WHERE area='" + bo.getArea() + "' AND department = '" + bo.getProjectGroup() + "' AND position = '" + bo.getPosition() + "' and whetherEntry = 1 " +
                    " GROUP BY area,department,position ";
            recruitDetailsBOS = super.findBySql(successSql, RecruitDetailsBO.class, successField);
            for (RecruitDetailsBO bo1 : recruitDetailsBOS) {
                bo.setSuccessEntryNum(bo1.getSuccessEntryNum());
            }
            //实际成功通过面试量
            String[] passField = new String[]{"area", "projectGroup", "position", "successPassNum"};
            String passSql = " SELECT area AS area,department as projectGroup,position AS position, " +
                    "  count(agreedEmployed) AS successPassNum FROM recruit_interviewinfor " +
                    " WHERE area='" + bo.getArea() + "' AND department = '" + bo.getProjectGroup() + "' AND position = '" + bo.getPosition() + "' and agreedEmployed = 1 " +
                    " GROUP BY area,department,position ";
            recruitDetailsBOS = super.findBySql(passSql, RecruitDetailsBO.class, passField);
            for (RecruitDetailsBO bo1 : recruitDetailsBOS) {
                bo.setSuccessPassNum(bo1.getSuccessPassNum());
            }
            //还需招聘人数(成功入职数-计划招聘人数)
            if (bo.getSuccessEntryNum() != null && bo.getPlanInterviewNum() != null) {
                bo.setHireNum(bo.getSuccessEntryNum() - bo.getPlanInterviewNum());
            }
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public OptionBO channelCollect(ChannelTO to) throws SerException {
        Integer year = to.getYear();
        Integer month = to.getMonth();
        String position = to.getPosition();
        if (StringUtils.isBlank(position)) {
            throw new SerException("岗位不能为空");
        }
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String text_1 = position + "岗位各招聘渠道" + year + "年" + month + "月分析表";
        return channelCollectFigure(to, text_1);
    }

    private OptionBO channelCollectFigure(ChannelTO to, String text_1) throws SerException {
//        String[] positions = to.getPosition();
//        String[] positionTemp = new String[positions.length];
//        for (int i = 0; i < positions.length; i++) {
//            positionTemp[i] = "'" + positions[i] + "'";
//        }
//        String positionStr = StringUtils.join(positionTemp,",");
        List<RecruitChannelBO> boList = new ArrayList<>();
        List<RecruitChannelBO> recruitChannelBOS = new ArrayList<>();
        String[] fields = new String[]{"channel"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT resumeResource AS channel FROM recruit_firstphonerecord ");
        sb.append(" WHERE position = '" + to.getPosition() + "' AND year(date)=" + to.getYear() + " AND month(date)=" + to.getMonth() + " ");
        String sql = sb.toString();
//        sql = String.format(sql, positionStr);
        List<RecruitChannelBO> channelBOS = super.findBySql(sql, RecruitChannelBO.class, fields);
        for (RecruitChannelBO bo : channelBOS) {
            //邀约面试量
            String[] inviteFields = new String[]{"channel", "inviteNum"};
            String inviteSql = " SELECT resumeResource AS channel,count(whetherFirstInviteSuccess) AS inviteNum FROM recruit_firstphonerecord where resumeResource='" + bo.getChannel() + "' AND whetherFirstInviteSuccess = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(inviteSql, RecruitChannelBO.class, inviteFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setInviteNum(bo1.getInviteNum());
            }
            //初试面试量
            String[] firstFields = new String[]{"channel", "firstNum"};
            String firstSql = " SELECT resumeResource AS channel,count(whetherFaceTest) AS firstNum FROM recruit_interviewinfor where resumeResource='" + bo.getChannel() + "' and whetherFaceTest = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(firstSql, RecruitChannelBO.class, firstFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setFirstNum(bo1.getFirstNum());
            }
            //复试面试量
            String[] secondFields = new String[]{"channel", "secondNum"};
            String secondSql = " SELECT resumeResource AS channel,count(secondTestAdvice) AS secondNum FROM recruit_interviewinfor where resumeResource='" + bo.getChannel() + "' GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(secondSql, RecruitChannelBO.class, secondFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setSecondNum(bo1.getSecondNum());
            }
            //成功通过面试量
            String[] successFields = new String[]{"channel", "successPassNum"};
            String successSql = " SELECT resumeResource AS channel,count(agreedEmployed) AS successPassNum FROM recruit_interviewinfor where resumeResource='" + bo.getChannel() + "' AND agreedEmployed = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(successSql, RecruitChannelBO.class, successFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setSuccessPassNum(bo1.getSuccessPassNum());
            }
            //入职量
            String[] entryField = new String[]{"channel", "entryNum"};
            String entrySql = " SELECT resumeResource AS channel, count(whetherEntry) AS entryNum FROM recruit_interviewinfor WHERE resumeResource = '" + bo.getChannel() + "' and whetherEntry = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(entrySql, RecruitChannelBO.class, entryField);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setEntryNum(bo1.getEntryNum());
            }
            boList.add(bo);
        }

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_3 = new String[]{"邀请面试量", "初试面试量",
                "复试面试量", "成功通过面试量", "入职量"};
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (boList != null && boList.size() > 0) {
            for (RecruitChannelBO channelBO : boList) {
                text_list2.add(channelBO.getChannel());

                //柱状图数据
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(channelBO.getChannel());
                seriesBO.setType("bar");


                Integer[] number = new Integer[]{channelBO.getInviteNum(), channelBO.getFirstNum(),
                        channelBO.getSecondNum(), channelBO.getSuccessPassNum(), channelBO.getEntryNum()};
                seriesBO.setData(number);
                seriesBOList.add(seriesBO);
            }
        }

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    @Override
    public OptionPieBO faceFigure(ChannelTO to) throws SerException {
        String potion = to.getPosition();
        Integer year = to.getYear();
        Integer month = to.getMonth();
        String text_1 = potion + "岗位" + year + "年" + month + "月面试率分析表";
        return faceFigureCollect(to, text_1);
    }

    @Override
    public OptionPieBO entryFigure(ChannelTO to) throws SerException {
        String potion = to.getPosition();
        Integer year = to.getYear();
        Integer month = to.getMonth();
        String text_1 = potion + "岗位" + year + "年" + month + "月入职率分析表";
        return entryFigureCollect(to, text_1);
    }

    @Autowired
    private FirstPhoneRecordSer firstPhoneRecordSer;

    @Override
    public List<String> getPosition() throws SerException {
        Set<String> set = new HashSet<>();
        List<FirstPhoneRecord> list = firstPhoneRecordSer.findAll();
        for (FirstPhoneRecord record : list) {
            set.add(record.getPosition());
        }
        return new ArrayList<>(set);
    }

    private OptionPieBO entryFigureCollect(ChannelTO to, String text_1) throws SerException {
        List<RecruitChannelBO> boList = new ArrayList<>();
        List<RecruitChannelBO> recruitChannelBOS = new ArrayList<>();
        String[] fields = new String[]{"channel"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT resumeResource AS channel FROM recruit_firstphonerecord ");
        sb.append(" WHERE position = '" + to.getPosition() + "' AND year(date)=" + to.getYear() + " AND month(date)=" + to.getMonth() + " ");
        String sql = sb.toString();
        List<RecruitChannelBO> channelBOS = super.findBySql(sql, RecruitChannelBO.class, fields);
        for (RecruitChannelBO bo : channelBOS) {
            //成功通过面试量
            String[] successFields = new String[]{"channel", "successPassNum"};
            String successSql = " SELECT resumeResource AS channel,count(agreedEmployed) AS successPassNum FROM recruit_interviewinfor where resumeResource='" + bo.getChannel() + "' AND agreedEmployed = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(successSql, RecruitChannelBO.class, successFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setSuccessPassNum(bo1.getSuccessPassNum());
            }
            //入职量
            String[] entryField = new String[]{"channel", "entryNum"};
            String entrySql = " SELECT resumeResource AS channel, count(whetherEntry) AS entryNum FROM recruit_interviewinfor WHERE resumeResource = '" + bo.getChannel() + "' and whetherEntry = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(entrySql, RecruitChannelBO.class, entryField);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setEntryNum(bo1.getEntryNum());
            }
            //入职率(成功通过面试量/入职量*100%)
            if (bo.getSuccessPassNum() != null && bo.getEntryNum() != null) {
                Double entryRate = bo.getSuccessPassNum() / bo.getEntryNum() * 0.1;
                bo.setEntryRate(entryRate);
            }
            boList.add(bo);
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        titleBO.setX("center");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setOrient("vertical");
        legendBO.setLeft("left");
        List<String> text_list2 = new ArrayList<>();

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("item");
        tooltipBO.setFormatter("{a} <br/>{b} : {c} ({d}%)");

        List<SeriesBBO> seriesBOList = new ArrayList<>();
        //柱状图数据
        SeriesBBO seriesBO = new SeriesBBO();
        seriesBO.setName(to.getPosition());
        seriesBO.setType("pie");
        seriesBO.setRadius("55%");
        String[] center = new String[]{"50%", "60%"};
        seriesBO.setCenter(center);
        List<DataBO> dataBOS = new ArrayList<>();

        if (boList != null && boList.size() > 0) {
            for (RecruitChannelBO channelBO : boList) {
                text_list2.add(channelBO.getChannel());

                DataBO dataBO = new DataBO();
                dataBO.setName(channelBO.getChannel());
                dataBO.setValue(channelBO.getEntryRate());
                dataBOS.add(dataBO);
            }
        }
        seriesBO.setData(dataBOS);
        seriesBOList.add(seriesBO);

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionPieBO optionBO = new OptionPieBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

    private OptionPieBO faceFigureCollect(ChannelTO to, String text_1) throws SerException {
        List<RecruitChannelBO> boList = new ArrayList<>();
        List<RecruitChannelBO> recruitChannelBOS = new ArrayList<>();
        String[] fields = new String[]{"channel"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT resumeResource AS channel FROM recruit_firstphonerecord ");
        sb.append(" WHERE position = '" + to.getPosition() + "' AND year(date)=" + to.getYear() + " AND month(date)=" + to.getMonth() + " ");
        String sql = sb.toString();
        List<RecruitChannelBO> channelBOS = super.findBySql(sql, RecruitChannelBO.class, fields);
        for (RecruitChannelBO bo : channelBOS) {
            //初试面试量
            String[] firstFields = new String[]{"channel", "firstNum"};
            String firstSql = " SELECT resumeResource AS channel,count(whetherFaceTest) AS firstNum FROM recruit_interviewinfor where resumeResource='" + bo.getChannel() + "' and whetherFaceTest = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(firstSql, RecruitChannelBO.class, firstFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setFirstNum(bo1.getFirstNum());
            }
            //邀约面试量
            String[] inviteFields = new String[]{"channel", "inviteNum"};
            String inviteSql = " SELECT resumeResource AS channel,count(whetherFirstInviteSuccess) AS inviteNum FROM recruit_firstphonerecord where resumeResource='" + bo.getChannel() + "' AND whetherFirstInviteSuccess = 1 GROUP BY resumeResource ";
            recruitChannelBOS = super.findBySql(inviteSql, RecruitChannelBO.class, inviteFields);
            for (RecruitChannelBO bo1 : recruitChannelBOS) {
                bo.setInviteNum(bo1.getInviteNum());
            }
            //面试率(初试面试量/邀约面试量×100%)
            if (bo.getFirstNum() != null && bo.getInviteNum() != null) {
                Double faceRate = bo.getFirstNum() / bo.getInviteNum() * 0.1;
                bo.setFaceRate(faceRate);
            }
            boList.add(bo);
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        titleBO.setX("center");

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        legendBO.setOrient("vertical");
        legendBO.setLeft("left");
        List<String> text_list2 = new ArrayList<>();

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        tooltipBO.setTrigger("item");
        tooltipBO.setFormatter("{a} <br/>{b} : {c} ({d}%)");

        List<SeriesBBO> seriesBOList = new ArrayList<>();
        //柱状图数据
        SeriesBBO seriesBO = new SeriesBBO();
        seriesBO.setName(to.getPosition());
        seriesBO.setType("pie");
        seriesBO.setRadius("55%");
        String[] center = new String[]{"50%", "60%"};
        seriesBO.setCenter(center);
        List<DataBO> dataBOS = new ArrayList<>();

        if (boList != null && boList.size() > 0) {
            for (RecruitChannelBO channelBO : boList) {
                text_list2.add(channelBO.getChannel());

                DataBO dataBO = new DataBO();
                dataBO.setName(channelBO.getChannel());
                dataBO.setValue(channelBO.getFaceRate());
                dataBOS.add(dataBO);
            }
        }
        seriesBO.setData(dataBOS);
        seriesBOList.add(seriesBO);

        String[] text_2 = new String[text_list2.size()];
        text_2 = text_list2.toArray(text_2);

        SeriesBBO[] text_4 = new SeriesBBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        OptionPieBO optionBO = new OptionPieBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }


    //季度
    private String[] quarter(Integer year, Integer quarter) throws SerException {
        String startDate = null;
        String endDate = null;
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = DateUtil.dateToString(LocalDate.now());
                endDate = DateUtil.dateToString(LocalDate.now());
                break;
        }

        return new String[]{startDate, endDate};
    }

    private List<RecruitProgressBO> progressCollect(String startTime, String endTime) throws SerException {
        List<RecruitProgressBO> boList = new ArrayList<>();
        List<RecruitProgressBO> progressBOList = new ArrayList<>();
        //部门/项目组 人口缺口隐患(待离职人员) 已完成招聘人数 计划筛选简历数 计划邀约面试量 计划面试量 计划成功通过面试量 计划入职人数
        String[] fields = new String[]{"projectGroup", "populationGapNum", "completeNum", "planScreenNum",
                "planInviteNum", "planInterviewNum", "planPassNum", "planEntryNum"};
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT projectGroup AS projectGroup,count(populationGap) AS populationGapNum, ");
        sb.append(" sum(completeNum) AS  completeNum,sum(planScreenNum) AS planScreenNum, ");
        sb.append(" sum(planInterviewAmount) AS planInviteNum,sum(planInterviewNum) AS planInterviewNum, ");
        sb.append(" sum(planPassNum) AS planPassNum,sum(planEntryNum) AS planEntryNum");
        sb.append(" FROM recruit_recruitdemandplan WHERE time BETWEEN '" + startTime + "' AND '" + endTime + "' ");
        sb.append(" GROUP BY projectGroup ");
        List<RecruitProgressBO> progressBOS = super.findBySql(sb.toString(), RecruitProgressBO.class, fields);
        //下载简历数差异(实际筛选简历数-)
        Integer downloadBalance = 0;
        //实际筛选简历数
        Integer realityScreenNum = 0;
        if (progressBOS != null && progressBOS.size() > 0) {
            for (RecruitProgressBO progressBO : progressBOS) {
                //实际筛选简历数
                String[] realityFields = new String[]{"projectGroup", "realityScreenNum"};
                String realitySql = " SELECT projectGroup AS projectGroup,count(*) AS realityScreenNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' GROUP BY projectGroup ";
                progressBOList = super.findBySql(realitySql, RecruitProgressBO.class, realityFields);
                for (RecruitProgressBO bo : progressBOList) {
                    realityScreenNum = bo.getRealityScreenNum();
                    progressBO.setRealityScreenNum(realityScreenNum);
                    //下载简历数差异
                    downloadBalance = realityScreenNum - progressBO.getPlanScreenNum();
                    progressBO.setDownloadBalance(downloadBalance);
                }
                //电访数量
                String[] phoneFields = new String[]{"projectGroup", "phoneNum"};
                String phoneSql = " SELECT projectGroup AS projectGroup,count(whetherPhoneSuccess) AS phoneNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' GROUP BY projectGroup ";
                progressBOList = super.findBySql(phoneSql, RecruitProgressBO.class, phoneFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setRealityScreenNum(bo.getPhoneNum());
                }
                //预约面试量
                String[] orderFields = new String[]{"projectGroup", "orderNum"};
                String orderSql = " SELECT projectGroup AS projectGroup,count(whetherPhoneSuccess) AS orderNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' AND whetherPhoneSuccess = 1 GROUP BY projectGroup ";
                progressBOList = super.findBySql(orderSql, RecruitProgressBO.class, orderFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setOrderNum(bo.getOrderNum());
                }
                //邀约面试量
                String[] inviteFields = new String[]{"projectGroup", "inviteNum"};
                String inviteSql = " SELECT projectGroup AS projectGroup,count(whetherFirstInviteSuccess) AS inviteNum FROM recruit_firstphonerecord where projectGroup='" + progressBO.getProjectGroup() + "' AND whetherFirstInviteSuccess = 1 GROUP BY projectGroup ";
                progressBOList = super.findBySql(inviteSql, RecruitProgressBO.class, inviteFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setInviteNum(bo.getInviteNum());
                }
                //初试面试量
                String[] firstFields = new String[]{"projectGroup", "firstInterviewNum"};
                String firstSql = " SELECT department AS projectGroup,count(whetherFaceTest) AS firstInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' and whetherFaceTest = 1 GROUP BY department ";
                progressBOList = super.findBySql(firstSql, RecruitProgressBO.class, firstFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setFirstInterviewNum(bo.getFirstInterviewNum());
                }
                //复试面试量
                String[] secondFields = new String[]{"projectGroup", "secondInterviewNum"};
                String secondSql = " SELECT department AS department,count(secondTestAdvice) AS secondInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' GROUP BY department ";
                progressBOList = super.findBySql(secondSql, RecruitProgressBO.class, secondFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setSecondInterviewNum(bo.getSecondInterviewNum());
                }
                //薪资面谈量
                String[] accountFields = new String[]{"projectGroup", "accountInterviewNum"};
                String accountSql = " SELECT department AS projectGroup,count(faceAdvice) AS accountInterviewNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' GROUP BY department ";
                progressBOList = super.findBySql(accountSql, RecruitProgressBO.class, accountFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setAccountInterviewNum(bo.getAccountInterviewNum());
                }
                //成功通过面试量
                String[] successFields = new String[]{"projectGroup", "successPassNum"};
                String successSql = " SELECT department AS department,count(agreedEmployed) AS successPassNum FROM recruit_interviewinfor where department='" + progressBO.getProjectGroup() + "' AND agreedEmployed = 1 GROUP BY department ";
                progressBOList = super.findBySql(successSql, RecruitProgressBO.class, successFields);
                for (RecruitProgressBO bo : progressBOList) {
                    progressBO.setSuccessPassNum(bo.getSuccessPassNum());
                }
                //入职量
                Integer entryNum = entryRegisterAPI.findNumByEntryDate(progressBO.getProjectGroup());
                progressBO.setEntryNum(entryNum);
                //面试率(初试面试量/邀约面试量*100%)
                if (null == progressBO.getFirstInterviewNum() || null == progressBO.getInviteNum()) {
                    progressBO.setInterviewRate(0.0);

                } else {
                    Double interviewRate = progressBO.getFirstInterviewNum() / progressBO.getInviteNum() * 0.1;
                    progressBO.setInterviewRate(interviewRate);
                }
                //入职率(成功通过面试量/入职量*100%)
                if (progressBO.getEntryNum() == 0) {
                    progressBO.setInductionRate(0.0);

                } else {
                    Double inductionRate = progressBO.getSuccessPassNum() / progressBO.getEntryNum() * 0.1;
                    progressBO.setInductionRate(inductionRate);
                }
                boList.add(progressBO);
            }
        }
        return boList;
    }
//    public List<Object> countSituation(RecruitPlanDTO dto) throws SerException {
//        checkSeeIdentity();
//        List<Object> list = new ArrayList<>();
//        LocalDate time = DateUtil.parseDate(dto.getTime());
//        String area = dto.getArea();
//        String depart = dto.getDepart();
//        String position = dto.getPosition();
//        InterviewInforDTO interviewInforDTO = new InterviewInforDTO();
//        interviewInforDTO.getConditions().add(Restrict.eq("date", time));
//        interviewInforDTO.getConditions().add(Restrict.eq("area", area));
//        FirstPhoneRecordDTO firstPhoneRecordDTO = new FirstPhoneRecordDTO();
//        firstPhoneRecordDTO.getConditions().add(Restrict.eq("date", time));
//        RecruitPlanDTO recruitPlanDTO = new RecruitPlanDTO();
//        recruitPlanDTO.getConditions().add(Restrict.eq("recruitDate", time));
//        recruitPlanDTO.getConditions().add(Restrict.eq("recruitArea", area));
//        if (StringUtils.isNotBlank(depart)) {
//            recruitPlanDTO.getConditions().add(Restrict.eq("recruitDepart", depart));
//            interviewInforDTO.getConditions().add(Restrict.eq("department", depart));
//        }
//        if (StringUtils.isNotBlank(position)) {
//            recruitPlanDTO.getConditions().add(Restrict.eq("recruitPost", position));
//            firstPhoneRecordDTO.getConditions().add(Restrict.eq("position", position));
//            interviewInforDTO.getConditions().add(Restrict.eq("position", position));
//        }
//        List<RecruitPlan> recruitPlans = super.findByCis(recruitPlanDTO);
//        List<FirstPhoneRecord> firstPhoneRecords = firstPhoneRecordSer.findByCis(firstPhoneRecordDTO);
//        List<InterviewInfor> interviewInfors = interviewInforSer.findByCis(interviewInforDTO);
//        int planDayResumeNo = 0;    //计划日简历筛选量
//        int actualDayResumeNo = 0;   //实际日简历筛选量
//        int planDayInviteNo = 0;   //计划日邀约面试量
//        int actualDayInviteNo = 0;   //实际日邀约面试量
//        int planDayInterviewNo = 0;   //计划日面试量
//        int actualDayInterviewNo = 0;   //实际日面试量
//        int planDayPassInterviewNo = 0;   //计划日成功通过面试量
//        int actualDayPassInterviewNo = 0;   //实际日成功通过面试量
//        int failInterReasonNum = 0;    //未邀约成功原因类型出现量
//        int failInterNum = 0;     //未邀约成功量
//        int denyFirViewNum = 0;   //未应约初试量
//        int denyFirViewReasonNum = 0;  //未应约初试原因类型出现量
//        int denyAdmitNum = 0;   //未接受录取量
//        int denyAdmitReasonNum = 0;  //未接受录取原因类型出现量
//        if (StringUtils.isNotBlank(depart) && StringUtils.isNotBlank(position)) {
//            SituationTBO situationTBO = new SituationTBO();
//            situationTBO.setDate(dto.getTime());
//            situationTBO.setRecruitArea(area);
//            situationTBO.setRecruitDepart(depart);
//            situationTBO.setRecruitPosition(position);
//            situationTBO.setId("2");
//            for (String fi : fInviteReason()) {
//                for (String fd : fDenyFirViewReason()) {
//                    for (FirstPhoneRecord f : firstPhoneRecords) {
//                        actualDayResumeNo++;
//                        if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
//                            actualDayInviteNo++;
//                        }
//                        if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
//                            failInterReasonNum++;
//                            failInterNum++;
//                            situationTBO.setFailInterReason(fi);
//                            situationTBO.setFailInterReasonNum(failInterReasonNum);
//                            list.add(situationTBO);
//                            failInterReasonNum = 0;
//                        }
//                        if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
//                            denyFirViewReasonNum++;
//                            denyFirViewNum++;
//                            situationTBO.setDenyFirViewReason(fd);
//                            situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
//                            list.add(situationTBO);
//                            denyFirViewReasonNum = 0;
//                        }
//                    }
//                }
//            }
//            for (String id : iDenyAdmitReason()) {
//                for (InterviewInfor i : interviewInfors) {
//                    actualDayInterviewNo++;
//                    if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
//                        actualDayPassInterviewNo++;
//                    }
//                    if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
//                        denyAdmitNum++;
//                        denyAdmitReasonNum++;
//                        situationTBO.setDenyAdmitReason(id);
//                        situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
//                        list.add(situationTBO);
//                        denyAdmitReasonNum = 0;
//                    }
//                }
//            }
//            situationTBO.setFailInterNum(failInterNum);
//            situationTBO.setDenyFirViewNum(denyFirViewNum);
//            situationTBO.setDenyAdmitNum(denyAdmitNum);
//            list.add(situationTBO);
//            Set<String> set = allFirmPrincipal();
//            for (String s : set) {
//                for (RecruitPlan r : recruitPlans) {
//                    if (s.equals(r.getFirmPrincipal())) {
//                        planDayResumeNo += r.getPlanDayResumeNo();
//                        planDayInviteNo += r.getPlanDayInviteNo();
//                        planDayInterviewNo += r.getPlanDayInterviewNo();
//                        planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
//                        SituationBO situationBO = new SituationBO();
//                        situationBO.setDate(dto.getTime());
//                        situationBO.setRecruitArea(area);
//                        situationBO.setRecruitDepart(depart);
//                        situationBO.setRecruitPosition(position);
//                        situationBO.setPlanFilterCount(planDayResumeNo);
//                        situationBO.setActualFilterCount(actualDayResumeNo);
//                        situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
//                        situationBO.setPlanInviteCount(planDayInviteNo);
//                        situationBO.setActualInviteCount(actualDayInviteNo);
//                        situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
//                        situationBO.setPlanInterviewCount(planDayInterviewNo);
//                        situationBO.setActualInterviewCount(actualDayInterviewNo);
//                        situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
//                        situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
//                        situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
//                        situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
//                        situationBO.setPrincipalStaff(s);
//                        situationBO.setId("1");
//                        list.add(situationBO);
//                        planDayResumeNo = 0;
//                        planDayInviteNo = 0;
//                        planDayInterviewNo = 0;
//                        planDayPassInterviewNo = 0;   //置为0
//                    }
//                }
//            }
//            return list;
//        } else if (StringUtils.isNotBlank(depart)) {
//            return departCount(dto.getTime(), area, depart, interviewInfors, firstPhoneRecords, recruitPlans);
//        } else if (StringUtils.isNotBlank(position)) {
//            return positionCount(dto.getTime(), area, position, interviewInfors, firstPhoneRecords, recruitPlans);
//        }
//        SituationTBO situationTBO = new SituationTBO();
//        situationTBO.setDate(dto.getTime());
//        situationTBO.setRecruitArea(area);
//        situationTBO.setId("2");
//        for (String fi : fInviteReason()) {
//            for (String fd : fDenyFirViewReason()) {
//                for (FirstPhoneRecord f : firstPhoneRecords) {
//                    actualDayResumeNo++;
//                    if ((f.getWhetherFirstInviteSuccess() != null) && f.getWhetherFirstInviteSuccess()) {
//                        actualDayInviteNo++;
//                    }
//                    if ((f.getWhetherFirstInviteSuccess() != null) && (!f.getWhetherFirstInviteSuccess()) && fi.equals(f.getFailureInviteReason())) {
//                        failInterReasonNum++;
//                        failInterNum++;
//                        situationTBO.setFailInterReason(fi);
//                        situationTBO.setFailInterReasonNum(failInterReasonNum);
//                        list.add(situationTBO);
//                        failInterReasonNum = 0;
//                    }
//                    if ((f.getWhetherFirstInterview() != null) && (!f.getWhetherFirstInterview()) && fd.equals(f.getDenyFirViewReason())) {
//                        denyFirViewReasonNum++;
//                        denyFirViewNum++;
//                        situationTBO.setDenyFirViewReason(fd);
//                        situationTBO.setDenyFirViewReasonNum(denyFirViewReasonNum);
//                        list.add(situationTBO);
//                        denyFirViewReasonNum = 0;
//                    }
//                }
//            }
//        }
//        for (String id : iDenyAdmitReason()) {
//            for (InterviewInfor i : interviewInfors) {
//                actualDayInterviewNo++;
//                if ((i.getWhetherFirstTestPass() != null) && i.getWhetherFirstTestPass()) {
//                    actualDayPassInterviewNo++;
//                }
//                if ((i.getWhetherAcceptAdmit() != null) && (!i.getWhetherAcceptAdmit()) && id.equals(i.getDenyAdmitReason())) {
//                    denyAdmitNum++;
//                    denyAdmitReasonNum++;
//                    situationTBO.setDenyAdmitReason(id);
//                    situationTBO.setDenyAdmitReasonNum(denyAdmitReasonNum);
//                    list.add(situationTBO);
//                    denyAdmitReasonNum = 0;
//                }
//            }
//        }
//        situationTBO.setFailInterNum(failInterNum);
//        situationTBO.setDenyFirViewNum(denyFirViewNum);
//        situationTBO.setDenyAdmitNum(denyAdmitNum);
//        list.add(situationTBO);
//        Set<String> set = allFirmPrincipal();
//        for (String s : set) {
//            for (RecruitPlan r : recruitPlans) {
//                if (s.equals(r.getFirmPrincipal())) {
//                    planDayResumeNo += r.getPlanDayResumeNo();
//                    planDayInviteNo += r.getPlanDayInviteNo();
//                    planDayInterviewNo += r.getPlanDayInterviewNo();
//                    planDayPassInterviewNo += r.getPlanDayPassInterviewNo();
//                    SituationBO situationBO = new SituationBO();
//                    situationBO.setDate(dto.getTime());
//                    situationBO.setRecruitArea(area);
//                    situationBO.setPlanFilterCount(planDayResumeNo);
//                    situationBO.setActualFilterCount(actualDayResumeNo);
//                    situationBO.setDayFilterCountDiff(actualDayResumeNo - planDayResumeNo);
//                    situationBO.setPlanInviteCount(planDayInviteNo);
//                    situationBO.setActualInviteCount(actualDayInviteNo);
//                    situationBO.setDayInviteCountDiff(actualDayInviteNo - planDayInviteNo);
//                    situationBO.setPlanInterviewCount(planDayInterviewNo);
//                    situationBO.setActualInterviewCount(actualDayInterviewNo);
//                    situationBO.setDayInviteCountDiff(actualDayInterviewNo - planDayInterviewNo);
//                    situationBO.setPlanPassInterviewCount(planDayPassInterviewNo);
//                    situationBO.setActualPassInterviewCount(actualDayPassInterviewNo);
//                    situationBO.setDayPassInterviewCount(actualDayPassInterviewNo - planDayPassInterviewNo);
//                    situationBO.setPrincipalStaff(s);
//                    situationBO.setId("1");
//                    list.add(situationBO);
//                    planDayResumeNo = 0;
//                    planDayInviteNo = 0;
//                    planDayInterviewNo = 0;
//                    planDayPassInterviewNo = 0;   //置为0
//                }
//            }
//        }
//        return list;
//    }
}