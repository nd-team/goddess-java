package com.bjike.goddess.task.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.bo.CustomizeSonBO;
import com.bjike.goddess.task.bo.DataBO;
import com.bjike.goddess.task.bo.collect.Collect;
import com.bjike.goddess.task.dto.*;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.entity.CustomizeSon;
import com.bjike.goddess.task.enums.CollectSuitation;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.TimeType;
import com.bjike.goddess.task.quartz.TaskSession;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.task.to.TaskNodeExcel;
import com.bjike.goddess.task.vo.CustomizeVO;
import com.bjike.goddess.taskallotment.api.CustomTitleAPI;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TableAPI;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.CustomTitleBO;
import com.bjike.goddess.taskallotment.bo.NodeBO;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.CustomTitleDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.enums.FinishStatus;
import com.bjike.goddess.taskallotment.enums.TaskStatus;
import com.bjike.goddess.taskallotment.enums.TaskType;
import com.bjike.goddess.user.api.UserAPI;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.ArrayRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class CustomizeSerImpl extends ServiceImpl<Customize, CustomizeDTO> implements CustomizeSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ScheduleSer scheduleSer;
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private TableAPI tableAPI;
    @Autowired
    private CustomizeSonSer customizeSonSer;
    @Autowired
    private MessageAPI messageAPI;
    //    @Autowired
//    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;

    @Autowired
    private TaskNodeAPI taskNodeAPI;
    @Autowired
    private CustomTitleAPI customTitleAPI;

    @Override
    public List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        List<CustomizeBO> bos = new ArrayList<>();
        dto.getSorts().add("createTime=desc");
        for (Customize customize : super.findByPage(dto)) {
            bos.add(tranBO(customize));
        }
        return bos;
    }

    private CustomizeBO tranBO(Customize customize) throws SerException {
        CustomizeBO bo = BeanTransform.copyProperties(customize, CustomizeBO.class);
        if ("1".equals(bo.getType())){
            bo.setType("明细汇总");
            if ("1".equals(bo.getTypeExplain())){
                bo.setTypeExplain("今日汇总");
            }else {
                bo.setTypeExplain("明日汇总");
            }
        }else {
            bo.setType("数量汇总");
            if ("1".equals(bo.getTypeExplain())){
                bo.setTypeExplain("日周月");
            }else {
                bo.setTypeExplain("自定义");
            }
        }
        return bo;
    }

    @Override
    public Long count(CustomizeDTO dto) throws SerException {
        return super.count(dto);
    }

    /**
     * 2018-01-05
     *      进度同步新修改
     *          以前的方法已注释
     *
     *      自定义汇总保存
     *          第一部:保存自定义主表 基本属性（汇总表名称;项目id,多个;表id,多个；创建人(非id);汇总类型;汇总类型说明）
     *          第二部:保存该主表的全部字段;（用到的子表方式）
     * @param to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(CustomizeTO to) throws SerException {
        String nickname = userAPI.currentUser().getUsername();
        Customize customize = BeanTransform.copyProperties(to, Customize.class, true, "tablesId");
        customize.setTablesId(StringUtils.join(to.getTablesId(), ","));
        customize.setProjectId(StringUtils.join(to.getProjectIds(), ","));
        customize.setUser(nickname);
        customize.setEnable(false);
        super.save(customize);

        List<String> fields = to.getFields();
        List<CollectSuitation> collectSuitations = to.getCollectSuitations();
        List<ValDTO> vals = to.getVals();
        List<String> tableFields = to.getTableFields();
        if (null != fields && null != collectSuitations && null != vals && null != tableFields) {
            for (int i = 0; i < fields.size(); i++) {
                CustomizeSon customizeSon = new CustomizeSon();
                customizeSon.setCustomizeId(customize.getId());
                customizeSon.setTitle(fields.get(i));
                customizeSon.setCollectSuitation(collectSuitations.get(i));
                List<String> values = vals.get(i).getValues();
                if (null == values) {
                    throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                }
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < values.size(); j++) {
                    if (j == values.size() - 1) {
                        sb.append(values.get(j));
                    } else {
                        sb.append(values.get(j) + ",");
                    }
                }
                customizeSon.setType(sb.toString());
                customizeSon.setTableTitle(tableFields.get(i));
                customizeSon.setTitleIndex(i);
                customizeSonSer.save(customizeSon);
            }
        } else {
            throw new SerException("请传入正确的自定义条件");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void notice(CustomizeTO to) throws SerException {
        String id = to.getId();
        if (null == id) {
            throw new SerException("id不能为空");
        }
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        TimeType remindType = to.getRemindType();
        entity.setCollectType(to.getCollectType());
        entity.setRemindType(remindType);
        entity.setDateVal(to.getDateVal());
        entity.setSendDepart(to.getSendDepart());
        entity.setCollectObjec(StringUtils.join(to.getCollectObjecs(), ","));
        entity.setEnable(to.getEnable());
//        LocalDateTime sendTime = DateUtil.parseDateTime(to.getSendTime());
//        entity.setSendTime(sendTime);
//        LocalDateTime lastTime = null;
//        switch (remindType) {
//            case MINTUE:
//                lastTime = sendTime.minusMinutes(to.getDateVal());
//                break;
//            case HOUR:
//                lastTime = sendTime.minusHours(to.getDateVal());
//                break;
//            case DAY:
//                lastTime = sendTime.minusDays(to.getDateVal());
//                break;
//            case WEEK:
//                lastTime = sendTime.minusWeeks(to.getDateVal());
//                break;
//            case MONTH:
//                lastTime = sendTime.minusMonths(to.getDateVal());
//                break;
//        }
//        entity.setLastTime(lastTime);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }

        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().

                add(Restrict.eq("customizeId", id));
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        if (!sons.isEmpty()){
            customizeSonSer.remove(sons);
        }
        super.remove(id);
    }

    @Override
    public CustomizeBO one(String id) throws SerException {
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        CustomizeBO bo = BeanTransform.copyProperties(entity, CustomizeBO.class);
        CustomizeSonDTO customizeSonDTO = new CustomizeSonDTO();
        customizeSonDTO.getConditions().add(Restrict.eq("customizeId",entity.getId()));
        List<CustomizeSon> customizeSons = customizeSonSer.findByCis(customizeSonDTO);
        bo.setSons(BeanTransform.copyProperties ( customizeSons, CustomizeSonBO.class ));
        return bo;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(CustomizeTO to) throws SerException {
        /**
         * 更新父级表
         */
        Customize entity = super.findById(to.getId());
        Customize customize = BeanTransform.copyProperties(to, Customize.class, true, "tablesId");
        customize.setTablesId(StringUtils.join(to.getTablesId(), ","));
        customize.setProjectId(StringUtils.join(to.getProjectIds(), ","));
        BeanUtils.copyProperties(customize, entity, "id", "createTime", "user");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);

        /**
         * 更新子集表的时候 要先删除对应的之前的消息
         */
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", entity.getId()));
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        if (!sons.isEmpty()) {
            customizeSonSer.remove(sons);
        }

        /**
         * 在保存新的对象
         */
        List<String> fields = to.getFields();
        List<CollectSuitation> collectSuitations = to.getCollectSuitations();
        List<ValDTO> vals = to.getVals();
        List<String> tableFields = to.getTableFields();
        if (null != fields && null != collectSuitations && null != vals && null != tableFields) {
            for (int i = 0; i < fields.size(); i++) {
                CustomizeSon customizeSon = new CustomizeSon();
                customizeSon.setCustomizeId(customize.getId());
                customizeSon.setTitle(fields.get(i));
                customizeSon.setCollectSuitation(collectSuitations.get(i));
                List<String> values = vals.get(i).getValues();
                if (null == values) {
                    throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                }
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < values.size(); j++) {
                    if (j == values.size() - 1) {
                        sb.append(values.get(j));
                    } else {
                        sb.append(values.get(j) + ",");
                    }
                }
                customizeSon.setType(sb.toString());
                customizeSon.setTableTitle(tableFields.get(i));
                customizeSon.setTitleIndex(i);
                customizeSonSer.save(customizeSon);
            }
        } else {
            throw new SerException("请传入正确的自定义条件");
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void quartz() throws SerException {
        CustomizeDTO dto = new CustomizeDTO();
        dto.getConditions().add(Restrict.eq("enable", Boolean.TRUE));
        List<Customize> list = super.findByCis(dto);
        for (Customize customize : list) {
            String departEmial = null;
            if (customize.getSendDepart()) {
                String projectId = customize.getProjectId();
                String depart = projectAPI.findByID(projectId).getDepart();
//                List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.departByName(new String[]{depart});
//                if (null != departmentDetailBOS) {
                CommonalityBO commonalityBO = commonalityAPI.findByDepartment(depart);
                if (null != commonalityBO) {
                    departEmial = commonalityBO.getEmail();
                }
//                }
            }
            TimeType remindType = customize.getRemindType();
            int remidVal = customize.getDateVal();
            LocalDateTime lastTime = customize.getLastTime();
            LocalDateTime time = null;
            switch (remindType) {
                case MINTUE:   //每天
                    time = lastTime.plusMinutes(remidVal);
                    break;
                case HOUR:
                    time = lastTime.plusHours(remidVal);
                    break;
                case DAY:
                    time = lastTime.plusDays(remidVal);
                    break;
                case WEEK:
                    time = lastTime.plusWeeks(remidVal);
                    break;
                case MONTH:
                    time = lastTime.plusMonths(remidVal);
                    break;
            }
            if (null != time && DateUtil.mis(LocalDateTime.now(), time) >= 0) {
                String[] recivers = customize.getCollectObjec().split(",");
                if (null != recivers && recivers.length > 0) {
                    List<String> set = Arrays.asList(recivers);
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle("自定义汇总");
                    messageTO.setContent(html(customize));
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);
                    //定时发送必须写
                    messageTO.setSenderId("SYSTEM");
                    messageTO.setSenderName("SYSTEM");
                    if (null != departEmial) {
                        if (!set.contains(departEmial)) {
                            set.add(departEmial);
                        }
                        String[] strings = new String[set.size()];
                        strings = set.toArray(strings);
                        messageTO.setReceivers(strings);
                    } else {
                        messageTO.setReceivers(recivers);
                    }
                    messageAPI.send(messageTO);
                }
                customize.setLastTime(LocalDateTime.now());
                customize.setModifyTime(LocalDateTime.now());
                super.update(customize);
            }
            remindType = customize.getRemindType ();
            remidVal = customize.getDateVal ();
            lastTime = customize.getLastTime ();
            time = null;
            switch (remindType) {
                case MINTUE:   //每天
                    time = lastTime.plusMinutes(remidVal);
                    break;
                case HOUR:
                    time = lastTime.plusHours(remidVal);
                    break;
                case DAY:
                    time = lastTime.plusDays(remidVal);
                    break;
                case WEEK:
                    time = lastTime.plusWeeks(remidVal);
                    break;
                case MONTH:
                    time = lastTime.plusMonths(remidVal);
                    break;
            }
            if (null != time && DateUtil.mis(LocalDateTime.now(), time) >= 0) {
                String[] recivers = customize.getCollectObjec().split(",");
                if (null != recivers && recivers.length > 0) {
                    List<String> set = Arrays.asList(recivers);
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle("自定义汇总");
                    messageTO.setContent(html(customize));
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);
                    //定时发送必须写
                    messageTO.setSenderId("SYSTEM");
                    messageTO.setSenderName("SYSTEM");
                    if (null != departEmial) {
                        if (!set.contains(departEmial)) {
                            set.add(departEmial);
                        }
                        String[] strings = new String[set.size()];
                        strings = set.toArray(strings);
                        messageTO.setReceivers(strings);
                    } else {
                        messageTO.setReceivers(recivers);
                    }
                    messageAPI.send(messageTO);
                }
                customize.setLastTime(LocalDateTime.now());
                customize.setModifyTime(LocalDateTime.now());
                super.update(customize);
            }
        }
    }

    private String html(Customize customize) throws SerException {
        CollectDTO collectDTO = new CollectDTO();
        collectDTO.setProjectId(customize.getProjectId());
        collectDTO.setTablesId(customize.getTablesId().split(","));
        collectDTO.setNeedFixed(customize.getNeedFixed());
        DateType collectType = customize.getCollectType();
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", customize.getId()));
        sonDTO.getSorts().add("titleIndex=asc");
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        setSituation(collectDTO, sons);
        LocalDate startTime = null;
        LocalDate endTime = null;
        switch (collectType) {
            case DAY:   //每天
                startTime = LocalDate.now();
                endTime = LocalDate.now();
                break;
            case WEEK:
                startTime = DateUtil.getStartWeek();
                endTime = DateUtil.getEndWeek();
                break;
            case MONTH:
                startTime = DateUtil.getStartMonth();
                endTime = DateUtil.getEndMonth();
                break;
            case QUARTER:
                String s = DateUtil.dateToString(DateUtil.getStartQuart());
                startTime = DateUtil.parseDate(s.substring(0, s.indexOf(" ")));
                String e = DateUtil.dateToString(DateUtil.getEndQuart());
                endTime = DateUtil.parseDate(e.substring(0, e.indexOf(" ")));
                break;
            case YEAR:
                startTime = DateUtil.getStartYear();
                endTime = DateUtil.getEndYear();
                break;
        }
        if (null != startTime && null != endTime) {
            String start = DateUtil.dateToString(startTime);
            String end = DateUtil.dateToString(endTime);
            collectDTO.setStartTime(start);
            collectDTO.setEndTime(end);
        }
        String html = scheduleSer.html(collectDTO);
        return html;
    }

    private void setSituation(CollectDTO collectDTO, List<CustomizeSon> sons) throws SerException {
        List<String> fields = new ArrayList<>();
        List<CollectSuitation> collectSuitations = new ArrayList<>();
        List<ValDTO> vals = new ArrayList<>();
        List<String> tableFields = new ArrayList<>();
        for (CustomizeSon son : sons) {
            fields.add(son.getTitle());
            collectSuitations.add(son.getCollectSuitation());
            String[] type = son.getType().split("、");
            List<String> values = Arrays.asList(type);
            ValDTO valDTO = new ValDTO();
            valDTO.setValues(values);
            vals.add(valDTO);
            tableFields.add(son.getTableTitle());
        }
        collectDTO.setFields(fields);
        collectDTO.setCollectSuitations(collectSuitations);
        collectDTO.setVals(vals);
        collectDTO.setTableFields(tableFields);
    }

//    @Override
//    public void enable(String id, boolean enable) throws SerException {
//        Customize customize = super.findById(id);
//        if (null != customize) {
//            if (enable) {
//                Customize c = TaskSession.get(customize.getId());
//                if (null == c) {
//                    TaskSession.put(customize.getId(), customize);
//                }
//            } else {
//                TaskSession.remove(customize.getId());
//            }
//            customize.setEnable(enable);
//            super.update(customize);
//        } else {
//            throw new SerException("找不到该记录");
//        }
//
//    }

//    private void validated(CustomizeTO to) throws SerException {
//        if (!to.getSummaryType().equals(SummaryType.ALL)) {
//            if (StringUtils.isBlank(to.getSummaryTarget())) {
//                throw new SerException("请指定汇总部门或者人员");
//            }
//        }
//        if (!to.getNoticeType().equals(NoticeType.ALL.ALL)) {
//            if (StringUtils.isBlank(to.getNoticeTarget())) {
//                throw new SerException("请指定提醒部门或者人员");
//            }
//        }
//        try {
//            Integer.parseInt(to.getTimeVal());
//        } catch (Exception e) {
//            throw new SerException("定时时间间隔值必须为整数数字");
//        }
//    }

//    @Transactional
//    @Override
//    public void executeTask() throws SerException {
//        List<Customize> customizes = queryTask(); //任务查询
//        for (Customize customize : customizes) {
//            if (isInvoking(customize)) { //是否可调用
//                //查询调用
//                try {
//                    scheduleSer.customizeCollect(customize);
//                } catch (Exception e) { //不进行异常处理
//                    e.printStackTrace();
//                }
//                LocalDateTime now = LocalDateTime.now();
//                String sql = "UPDATE task_customize SET lastTime='%s' WHERE id='%s'";
//                super.executeSql(String.format(sql, DateUtil.dateToString(now), customize.getId()));
//                customize.setLastTime(now);
//                TaskSession.put(customize.getId(), customize);
//            }
//        }
//    }


    /**
     * 查询任务
     *
     * @return
     * @throws SerException
     */
    private boolean first = true;

    private List<Customize> queryTask() throws SerException {
        List<Customize> customizes = new ArrayList<>();
        if (null != TaskSession.sessions()) {
            Map<String, Customize> map = TaskSession.sessions().asMap();
            for (Map.Entry<String, Customize> entry : map.entrySet()) {
                customizes.add(entry.getValue());
            }
        } else if (first) { //仅查询一次
            first = false;
            CustomizeDTO dto = new CustomizeDTO();
            dto.getConditions().add(Restrict.eq("enable", true));
            customizes = super.findByCis(dto);
            for (Customize customize : customizes) {
                TaskSession.put(customize.getId(), customize);
            }
        }
        return customizes;
    }

//    /**
//     * 是否可调用
//     *
//     * @param customize
//     * @return
//     */
//    private boolean isInvoking(Customize customize) throws SerException {
//        TimeType type = customize.getTimeType();
//        String timeVal = customize.getTimeVal();
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime last = customize.getLastTime();
//        if (!type.equals(TimeType.EVERYDAY)) { //每隔时间段
//            int second = 0;
//            try {
//                Integer.parseInt(timeVal);
//                if (type.equals(TimeType.MINUTE)) {
//                    second = Integer.parseInt(timeVal) * 60;
//                } else if (type.equals(TimeType.HOUR)) {
//                    second = Integer.parseInt(timeVal) * 60 * 60;
//                }
//                if (second < ChronoUnit.SECONDS.between(last, now)) {
//                    return true;
//                }
//            } catch (Exception e) {
//                return false;
//            }
//        } else { //每天
//            String time = DateUtil.dateToString(LocalTime.now());//截取到分钟
//            time = StringUtils.substringBeforeLast(time, ":");
//            //每天某个时间调用一次,TaskDaySession为空则表示没执行过
//            if (time.equals(timeVal) && TaskDaySession.get(customize.getId()) == null) {
//                //缓存两分钟后销毁(time与timeVal会在一分钟内多次匹配,TaskDaySession只要保存有该id,表示已经执行过)
//                TaskDaySession.put(customize.getId(), time);
//                return true;
//            }
//        }
//        return false;
//    }


    @Override
    public Double get(String[] tableIds) throws SerException {
        String tableId = "'" + StringUtils.join(tableIds, "','") + "'";
        String sql = "SELECT count(*) count,taskStatus " +
                "FROM goddess_taskallotment.taskallotment_tasknode " +
                "WHERE table_id IN (" + tableId + ")" +
                "      AND taskStatus=0 group by taskStatus";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && !objects.isEmpty()) {
            Object o = objects.get(0);
            Object[] obj = (Object[]) o;
            return Double.parseDouble(obj[0] + "");
        }
        return 0d;
    }

    @Override
    public String detail(String id) throws SerException {
//        Customize entity = super.findById(id);
//        if (null == entity) {
//            throw new SerException("该对象不存在");
//        }
//        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
//        sonDTO.getConditions().add(Restrict.eq("customizeId", entity.getId()));
//        sonDTO.getSorts().add("titleIndex=asc");
//        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
//        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
//        String valTr = "<tr style='height: 20px'>";
//        String endTr = "</tr>";
//        StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
//        result.append(nameTr);
//        result.append("<td>汇总表名称</td>");
//        result.append("<td>是否需要固定表头</td>");
//        result.append("<td>汇总项目</td>");
//        result.append("<td>汇总任务</td>");
//        result.append("<td>汇总字段</td>");
//        result.append("<td>汇总条件</td>");
//        result.append("<td>任务类型</td>");
//        result.append("<td>汇总表头字段</td>");
//        result.append(endTr);
//        result.append(valTr);
//        result.append("<td rowspan='" + sons.size() + "'>" + entity.getName() + "</td>");
//        if (entity.getNeedFixed()) {
//            result.append("<td rowspan='" + sons.size() + "'>是</td>");
//        } else {
//            result.append("<td rowspan='" + sons.size() + "'>否</td>");
//        }
//        result.append("<td rowspan='" + sons.size() + "'>" + projectAPI.findByID(entity.getProjectId()).getProject() + "</td>");
//        StringBuilder tableNames = new StringBuilder();
//        int a = 0;
//        for (String tableId : entity.getTablesId().split(",")) {
//            if (a == entity.getTablesId().split(",").length - 1) {
//                tableNames.append(projectAPI.table(tableId).getName());
//            } else {
//                tableNames.append(projectAPI.table(tableId).getName() + "、");
//            }
//            a++;CollectDTO
//        }
//        result.append("<td rowspan='" + sons.size() + "'>" + tableNames.toString() + "</td>");
//        for (int i = 0; i < sons.size(); i++) {
//            if (0 != i) {
//                result.append(valTr);
//            }
//            result.append("<td>" + sons.get(i).getTitle() + "</td>");
//            result.append("<td>" + sons.get(i).getCollectSuitation().toString() + "</td>");
//            result.append("<td>" + sons.get(i).getType() + "</td>");
//            result.append("<td>" + sons.get(i).getTableTitle() + "</td>");
//            result.append(endTr);
//        }
//        result.append("</table>");
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", entity.getId()));
        sonDTO.getSorts().add("titleIndex=asc");
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);

        CollectDTO dto = new CollectDTO();
        dto.setType(entity.getType());
        dto.setTypeExplain(entity.getTypeExplain());

        String[] Projects = entity.getProjectId().split(",");
        List<String> ProjectIds = Arrays.asList(Projects);
        /** * 项目ids */
        dto.setProjectIds(ProjectIds);

        String[] tablesId = entity.getTablesId().split(",");
        List<TableDTO> tabs = new ArrayList<>();
        for (int i = 0;i<ProjectIds.size();i++){
            String projects = ProjectIds.get(i);
            TableDTO tableDTO = new TableDTO();
            List<String> tables = new ArrayList<>();
            for (String str:tablesId){
                String sql = "SELECT * FROM goddess_taskallotment.taskallotment_table b " +
                        " WHERE " +
                        "   b.project_id = '"+projects+"' AND " +
                        "   b.id = '"+str+"'";
                if (customizeSonSer.findBySql(sql).size() > 0){
                    tables.add(str);
                }
            }
            tableDTO.setTables(tables);
            tabs.add(tableDTO);
        }
        /** * 任务ids */
        dto.setTabs(tabs);

        List<String> fields = new ArrayList<>();
        List<CollectSuitation> collectSuitations = new ArrayList<>();
        List<ValDTO> vals = new ArrayList<>();
        List<String> tableFields= new ArrayList<>();

        for (int i = 0;i < sons.size();i++){
            CustomizeSon customizeSon = sons.get(i);
            fields.add(customizeSon.getTitle());
            collectSuitations.add(customizeSon.getCollectSuitation());
            tableFields.add(customizeSon.getTableTitle());

            String[] context =  customizeSon.getType().split(",");
            List<String> values = new ArrayList<>();
            for (String str : context){
                values.add(str);
            }
            ValDTO valDTO = new ValDTO();
            valDTO.setValues(values);
            vals.add(valDTO);
        }

        /** * 汇总字段 */
        dto.setFields(fields);
        /** * 汇总条件 */
        dto.setCollectSuitations(collectSuitations);
        /**  * 类型值 */
        dto.setVals(vals);
        /** * 汇总表头字段 */
        dto.setTableFields(tableFields);

        return this.gather(dto);
    }

    /**
     * 自定义汇总
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public String gather(CollectDTO dto) throws SerException {
        /**
         * 新增汇总类型
         * 新增汇总说明
         * 2018-01-05  目前只有2中汇总类型
         *                  1.明细汇总
         *              --今日完成情况 （1）
         *              --明日完成情况 （2）
         *                  2.数量汇总
         *              --日周月      （1）
         *              --自定义      （2）
         */
        String type = dto.getType();
        String typeExplain = dto.getTypeExplain();


        if ("1".equals(type)){  //明细汇总
            if ("1".equals(typeExplain)){ //明细汇总--今日完成情况

                String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
                String valTr = "<tr style='height: 20px'>";
                String endTr = "</tr>";
                StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
                result.append("<caption><b>今日明细汇总</b></caption>");
                result.append(nameTr);

                result.append("<td>项目</td>");
                result.append("<td>任务</td>");
                List<String> fields = dto.getFields();      //字段列表
                List<CollectSuitation> collectSuitations = dto.getCollectSuitations(); //条件列表
                List<ValDTO> vals = dto.getVals();          //字段对应内容
                List<String> tableFields = dto.getTableFields();
//                String[] tableIds = dto.getTablesId();
                List<String> projectIds = dto.getProjectIds();
                List<TableDTO> tabs = dto.getTabs();
                if (null != fields && null != collectSuitations && null != vals && null != tableFields && null != tableFields && null != tabs) {
                    for (int i = 0; i < fields.size(); i++) {
                        String title = getString(fields.get(i));    //映射的表字段
                        if (null != title) {
                            result.append("<td>" + tableFields.get(i) + "</td>");
                        }
                    }
                    result.append(endTr);
                    /** * 表头构造结束  */

                    for (int x = 0;x<projectIds.size();x++){
                        TableDTO tableDTO = tabs.get(x);              //
                        List<String> tables = tableDTO.getTables();   //获取内容里面的全部数据 list

                        /** * 1.定义好查询条件
                         *      项目id
                         *      任务id 多个 ‘xxx’，‘xxxx’
                         * */
                        StringBuffer tablesId = new StringBuffer();
                        for (String tableId : tables) {
                            if (tablesId.length() > 0){
                                tablesId.append(",");
                                tablesId.append("'"+ tableId +"'");
                            }else {
                                tablesId.append("'"+ tableId +"'");
                            }
                        }

                        List<String> fieldsTxt = new ArrayList<>();
                        List<String> titles = new ArrayList<>();
                        titles.add("project");
                        titles.add("name");

                        /** 2.定义自定义查询条件
                         *      前端传输过来的自定义字段中
                         *      构建好查询条件
                         */
                        for (int i = 0; i < fields.size(); i++) {
                            String title = getString(fields.get(i));    //映射的表字段
                            CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                            ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                            List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                            if (null == values) {
                                throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                            }

                            /** * 如果表头字段为空表示无法查询该字段 */
                            if (null != title) {
                                titles.add(title);
                                /** * 替换要查询的参数  */
                                if ("taskType".equals(title)){
                                    for(int j=0;j<values.size();j++){
                                        switch (values.get(j)){
                                            case "行政任务":
                                                values.set(j,"0");
                                                break;
                                            case "工程任务":
                                                values.set(j,"1");
                                                break;
                                            case "培训任务":
                                                values.set(j,"2");
                                                break;
                                        }
                                    }
                                }

                                StringBuffer valContext = new StringBuffer();//用于保存字段的条件 ('1','2')
                                StringBuffer fieldContext = new StringBuffer();//用于保存整个字段的内容 id in ('1','2')
                                /** *前端传输过来，EQ表示相等，NULL表示为空，NOTNULL表示不为空 */
                                if (CollectSuitation.EQ.equals(collectSuitation)) {
                                    for (int j = 0; j<values.size() ;j++){
                                        if (valContext.length()>0){
                                            valContext.append(",");
                                            valContext.append("'"+values.get(j)+"'");
                                        }else {
                                            valContext.append("'"+values.get(j)+"'");
                                        }
                                    }
                                    if(valContext.indexOf("全部")!=-1){
                                        fieldContext.append(title + " IS NOT NULL "  ) ;
                                    }else{
                                        fieldContext.append(title + " IN (" + valContext +")");
                                    }
                                } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                    fieldContext.append(title + " IS NULL "  ) ;
                                } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                    fieldContext.append(title + " IS NOT NULL "  ) ;
                                }
                                fieldsTxt.add(fieldContext.toString());
                            }else {
                            }
                        }
                        String sql =  getSql(titles,tablesId.toString(),fieldsTxt,"1");
                        List<Object> datas = customizeSonSer.findBySql(sql);

                        System.out.println(sql);

                        for (int i = 0;i<datas.size();i++){
                            result.append(valTr);
                            Object o = datas.get(i);
                            for (int j = 0;j<titles.size();j++){
                                String title = titles.get(j);    //映射的表字段
                                Object[] arrays = (Object[]) o;
                                result.append("<td>" + getContextByTitle(title,arrays[j]+"") + "</td>");
                            }
                            result.append(endTr);
                        }

                    }

                    result.append("</table>");
                    return result.toString();
                }

            }else if ("2".equals(typeExplain)){ //明细汇总--明日完成情况
                String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
                String valTr = "<tr style='height: 20px'>";
                String endTr = "</tr>";
                StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
                result.append("<caption><b>明日明细汇总</b></caption>");
                result.append(nameTr);

                result.append("<td>项目</td>");
                result.append("<td>任务</td>");
                List<String> fields = dto.getFields();      //字段列表
                List<CollectSuitation> collectSuitations = dto.getCollectSuitations(); //条件列表
                List<ValDTO> vals = dto.getVals();          //字段对应内容
                List<String> tableFields = dto.getTableFields();
                List<String> projectIds = dto.getProjectIds();
                List<TableDTO> tabs = dto.getTabs();
                if (null != fields && null != collectSuitations && null != vals && null != tableFields && null != tableFields && null != tabs) {
                    for (int i = 0; i < fields.size(); i++) {
                        String title = getString(fields.get(i));    //映射的表字段
                        if (null != title) {
                            result.append("<td>" + tableFields.get(i) + "</td>");
                        }
                    }
                    result.append(endTr);
                    /** * 表头构造结束  */

                    for (int x = 0;x<projectIds.size();x++){
                        TableDTO tableDTO = tabs.get(x);              //
                        List<String> tables = tableDTO.getTables();   //获取内容里面的全部数据 list

                        /** * 1.定义好查询条件
                         *      项目id
                         *      任务id 多个 ‘xxx’，‘xxxx’
                         * */
                        StringBuffer tablesId = new StringBuffer();
                        for (String tableId : tables) {
                            if (tablesId.length() > 0){
                                tablesId.append(",");
                                tablesId.append("'"+ tableId +"'");
                            }else {
                                tablesId.append("'"+ tableId +"'");
                            }
                        }

                        List<String> fieldsTxt = new ArrayList<>();
                        List<String> titles = new ArrayList<>();
                        titles.add("project");
                        titles.add("name");

                        /** 2.定义自定义查询条件
                         *      前端传输过来的自定义字段中
                         *      构建好查询条件
                         */
                        for (int i = 0; i < fields.size(); i++) {
                            String title = getString(fields.get(i));    //映射的表字段
                            CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                            ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                            List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                            if (null == values) {
                                throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                            }

                            /** * 如果表头字段为空表示无法查询该字段 */
                            if (null != title) {
                                titles.add(title);
                                /** * 替换要查询的参数  */
                                if ("taskType".equals(title)){
                                    for(int j=0;j<values.size();j++){
                                        switch (values.get(j)){
                                            case "行政任务":
                                                values.set(j,"0");
                                                break;
                                            case "工程任务":
                                                values.set(j,"1");
                                                break;
                                            case "培训任务":
                                                values.set(j,"2");
                                                break;
                                        }
                                    }
                                }

                                StringBuffer valContext = new StringBuffer();//用于保存字段的条件 ('1','2')
                                StringBuffer fieldContext = new StringBuffer();//用于保存整个字段的内容 id in ('1','2')
                                /** *前端传输过来，EQ表示相等，NULL表示为空，NOTNULL表示不为空 */
                                if (CollectSuitation.EQ.equals(collectSuitation)) {
                                    for (int j = 0; j<values.size() ;j++){
                                        if (valContext.length()>0){
                                            valContext.append(",");
                                            valContext.append("'"+values.get(j)+"'");
                                        }else {
                                            valContext.append("'"+values.get(j)+"'");
                                        }
                                    }
                                    if(valContext.indexOf("全部")!=-1){
                                        fieldContext.append(title + " IS NOT NULL "  ) ;
                                    }else{
                                        fieldContext.append(title + " IN (" + valContext +")");
                                    }
                                } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                    fieldContext.append(title + " IS NULL "  ) ;
                                } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                    fieldContext.append(title + " IS NOT NULL "  ) ;
                                }
                                fieldsTxt.add(fieldContext.toString());
                            }else {
                            }
                        }
                        String sql =  getSql(titles,tablesId.toString(),fieldsTxt,"2");
                        List<Object> datas = customizeSonSer.findBySql(sql);
                        System.out.println(sql);
                        for (int i = 0;i<datas.size();i++){
                            result.append(valTr);
                            Object o = datas.get(i);
                            for (int j = 0;j<titles.size();j++){
                                String title = titles.get(j);    //映射的表字段
                                Object[] arrays = (Object[]) o;
                                result.append("<td>" + getContextByTitle(title,arrays[j]+"") + "</td>");
                            }
                            result.append(endTr);
                        }

                    }

                    result.append("</table>");
                    return result.toString();
                }
            }

        }else if ("2".equals(type)){ //数量汇总
            if ("1".equals(typeExplain)){ //数量汇总-周月日
                StringBuffer sumbuffer = new StringBuffer();
                String[] Datetype = new String[]{"day","week","month"};

                for (String t : Datetype) {
                    String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
                    String valTr = "<tr style='height: 20px'>";
                    String endTr = "</tr>";
                    StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
                    if ("day".equals(t)){
                        result.append("<caption><b>日汇总</b></caption>");
                    }else if ("week".equals(t)){
                        result.append("<caption><b>周汇总</b></caption>");
                    }else if ("month".equals(t)){
                        result.append("<caption><b>月汇总</b></caption>");
                    }
                    result.append(nameTr);

                    result.append("<td>项目</td>");
                    result.append("<td>任务</td>");
                    List<String> fields = dto.getFields();      //字段列表
                    List<CollectSuitation> collectSuitations = dto.getCollectSuitations(); //条件列表
                    List<ValDTO> vals = dto.getVals();          //字段对应内容
                    List<String> tableFields = dto.getTableFields();//表头字段
//                    String[] tableIds = dto.getTablesId();//任务id
                    List<String> projectIds = dto.getProjectIds();
                    List<TableDTO> tabs = dto.getTabs();
                    if (null != fields && null != collectSuitations && null != vals && null != tableFields && null !=projectIds && null != tabs) {
                        List<String> fieldsTxt = new ArrayList<>();
                        for (int i = 0; i < fields.size(); i++) {
                            String title = getString(fields.get(i));    //映射的表字段
                            CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                            ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                            List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                            if (null != title) {
                                result.append("<td>" + tableFields.get(i) + "</td>");
                                if ("peopleNum".equals(title)){         //人工==执行人
                                    StringBuffer valContext = new StringBuffer();//用于保存字段的条件 ('1','2')
                                    StringBuffer fieldContext = new StringBuffer();//用于保存整个字段的内容 id in ('1','2')
                                    /** *前端传输过来，EQ表示相等，NULL表示为空，NOTNULL表示不为空 */
                                    if (CollectSuitation.EQ.equals(collectSuitation)) {
                                        for (int j = 0; j<values.size() ;j++){
                                            if (valContext.length()>0){
                                                valContext.append(",");
                                                valContext.append("'"+values.get(j)+"'");
                                            }else {
                                                valContext.append("'"+values.get(j)+"'");
                                            }
                                        }
                                        fieldContext.append("execute IN (" + valContext +")");
                                    } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                        fieldContext.append("execute IS NULL");
                                    } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                        fieldContext.append("execute IS NOT NULL");
                                    }
                                    fieldsTxt.add(fieldContext.toString());
                                }
                            }
                        }
                        result.append(endTr);
                        /** * 表头构造 结束  */

                        /** * 构造数据列 */
                        for (int x=0;x<projectIds.size();x++){
                            String project = projectAPI.findByID(projectIds.get(x)).getProject();

                            TableDTO tableDTO = tabs.get(x);              //
                            List<String> tables = tableDTO.getTables();   //获取内容里面的全部数据 list

                            StringBuilder tableNames = new StringBuilder();
                            for (int i=0;i<tables.size();i++){
                                if (tableNames.length()>0){
                                    tableNames.append(",");
                                    tableNames.append(projectAPI.table(tables.get(i)).getName());
                                }else {
                                    tableNames.append(projectAPI.table(tables.get(i)).getName());
                                }
                            }

                            String sql = getSql(tables,t,fieldsTxt);
                            System.out.println(sql);
                            List<Object> datas = customizeSonSer.findBySql(sql);

                            double taskVolume = 0;             //总任务量
                            double fiTaskVolume = 0;           //完成任务量
                            double noTaskVolume = 0;           //未完成任务量
                            for (int i = 0;i<datas.size();i++){
                                Object o = datas.get(i);
                                Object[] arrays = (Object[]) o;
                                taskVolume += Double.parseDouble( arrays[0]+"");
                                if ("0".equals(arrays[1]+"")){
                                    fiTaskVolume += Double.parseDouble( arrays[0]+"");
                                }else {
                                    noTaskVolume += Double.parseDouble( arrays[0]+"");
                                }
                            }

                            result.append(valTr);
                            result.append("<td>" + project + "</td>");
                            result.append("<td>" + tableNames + "</td>");
                            for (int i = 0;i<fields.size();i++){
                                String title = getString(fields.get(i));    //映射的表字段
                                CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                                ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                                List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                                if (null == values) {
                                    throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                                }

                                if (null != title) {
                                    if("taskVolume".equals(title)){
                                        result.append("<td>" + taskVolume + "</td>");
                                    }else if ("fiTaskVolume".equals(title)){
                                        result.append("<td>" + fiTaskVolume + "</td>");
                                    }else if ("noTaskVolume".equals(title)){
                                        result.append("<td>" + noTaskVolume + "</td>");
                                    }else if ("peopleNum".equals(title)){
                                        if (CollectSuitation.EQ.equals(collectSuitation)) {
                                            result.append("<td>" + getPeople(values,tables).size() + "</td>");
                                        } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                            result.append("<td>0</td>");
                                        } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                            result.append("<td>" + getPeople(values,tables).size() + "</td>");
                                        }
                                    }
                                }
                            }
                        }
                        result.append(endTr);
                        result.append("</table>");
                        sumbuffer.append(result);
                    }
                }
                return sumbuffer.toString();


            }else if ("2".equals(typeExplain)){ //数量汇总-自定义

                String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
                String valTr = "<tr style='height: 20px'>";
                String endTr = "</tr>";
                StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
                result.append("<caption><b>自定义数量汇总</b></caption>");
                result.append(nameTr);

                result.append("<td>项目</td>");
                result.append("<td>任务</td>");
                List<String> fields = dto.getFields();      //字段列表
                List<CollectSuitation> collectSuitations = dto.getCollectSuitations(); //条件列表
                List<ValDTO> vals = dto.getVals();          //字段对应内容
                List<String> tableFields = dto.getTableFields();//表头字段
//                String[] tableIds = dto.getTablesId();//任务id
                List<String> projectIds = dto.getProjectIds();
                List<TableDTO> tabs = dto.getTabs();

                if (null != fields && null != collectSuitations && null != vals && null != tableFields && null !=projectIds && null != tabs) {
                    List<String> fieldsTxt = new ArrayList<>();
                    for (int i = 0; i < fields.size(); i++) {
                        String title = getString(fields.get(i));    //映射的表字段
                        CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                        ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                        List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                        if (null != title) {
                            result.append("<td>" + tableFields.get(i) + "</td>");
                            if ("peopleNum".equals(title)) {         //人工==执行人
                                StringBuffer valContext = new StringBuffer();//用于保存字段的条件 ('1','2')
                                StringBuffer fieldContext = new StringBuffer();//用于保存整个字段的内容 id in ('1','2')
                                /** *前端传输过来，EQ表示相等，NULL表示为空，NOTNULL表示不为空 */
                                if (CollectSuitation.EQ.equals(collectSuitation)) {
                                    for (int j = 0; j < values.size(); j++) {
                                        if (valContext.length() > 0) {
                                            valContext.append(",");
                                            valContext.append("'" + values.get(j) + "'");
                                        } else {
                                            valContext.append("'" + values.get(j) + "'");
                                        }
                                    }
                                    fieldContext.append("execute IN (" + valContext + ")");
                                } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                    fieldContext.append("execute IS NULL ");
                                } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                    fieldContext.append("execute IS NOT NULL");
                                }
                                fieldsTxt.add(fieldContext.toString());
                            }
                        }
                    }
                    result.append(endTr);
                    /** * 表头构造 结束  */

                    /** * 构造数据列 */
                    for (int x=0;x<projectIds.size();x++){
                        String project = projectAPI.findByID(projectIds.get(x)).getProject();

                        TableDTO tableDTO = tabs.get(x);              //
                        List<String> tables = tableDTO.getTables();   //获取内容里面的全部数据 list

                        StringBuilder tableNames = new StringBuilder();
                        for (int i=0;i<tables.size();i++){
                            if (tableNames.length()>0){
                                tableNames.append(",");
                                tableNames.append(projectAPI.table(tables.get(i)).getName());
                            }else {
                                tableNames.append(projectAPI.table(tables.get(i)).getName());
                            }
                        }

                        String sql = getSql(tables, "", fieldsTxt);
                        System.out.println(sql);
                        List<Object> datas = customizeSonSer.findBySql(sql);
                        double taskVolume = 0;             //总任务量
                        double fiTaskVolume = 0;           //完成任务量
                        double noTaskVolume = 0;           //未完成任务量
                        for (int i = 0; i < datas.size(); i++) {
                            Object o = datas.get(i);
                            Object[] arrays = (Object[]) o;
                            taskVolume += Double.parseDouble(arrays[0] + "");
                            if ("0".equals(arrays[1] + "")) {
                                fiTaskVolume += Double.parseDouble(arrays[0] + "");
                            } else {
                                noTaskVolume += Double.parseDouble(arrays[0] + "");
                            }
                        }

                        result.append(valTr);
                        result.append("<td>" + project + "</td>");
                        result.append("<td>" + tableNames + "</td>");
                        for (int i = 0; i < fields.size(); i++) {
                            String title = getString(fields.get(i));    //映射的表字段
                            CollectSuitation collectSuitation = collectSuitations.get(i);  //获取当前字段对应的条件
                            ValDTO valDTO = vals.get(i);//获取当前字段对应的内容
                            List<String> values = valDTO.getValues();   //获取内容里面的全部数据 list
                            if (null == values) {
                                throw new SerException("" + fields.get(i) + "字段的类型不能为空");
                            }

                            if (null != title) {
                                if ("taskVolume".equals(title)) {
                                    result.append("<td>" + taskVolume + "</td>");
                                } else if ("fiTaskVolume".equals(title)) {
                                    result.append("<td>" + fiTaskVolume + "</td>");
                                } else if ("noTaskVolume".equals(title)) {
                                    result.append("<td>" + noTaskVolume + "</td>");
                                } else if ("peopleNum".equals(title)) {
                                    if (CollectSuitation.EQ.equals(collectSuitation)) {
                                        result.append("<td>" + getPeople(values,tables).size() + "</td>");
                                    } else if (CollectSuitation.NULL.equals(collectSuitation)) {
                                        result.append("<td>0</td>");
                                    } else if (CollectSuitation.NOTNULL.equals(collectSuitation)) {
                                        result.append("<td>" + getPeople(values,tables).size() + "</td>");
                                    }
                                }
                            }
                        }
                    }
                    result.append(endTr);
                    result.append("</table>");
                }
                return result.toString();
            }


        }
        return null;
    }

    /**
     * 根据字段名称获取对应的field
     * @param field
     * @return
     * @throws SerException
     */
    private String getString(String field) throws SerException {
        List<Field> fields = ClazzUtils.getFields(TaskNodeExcel.class);
        List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
        for (int i = 0; i < headers.size(); i++) {
            if (field.equals(headers.get(i).name())) {
                return fields.get(i).getName();
            }
        }
        return null;
    }

    /**
     * 根据fields构造sql
     *      1,包括查询的字段
     *      2，包括查询的条件
     *  type 1.今日明细汇总
     *       2.明日明细汇总
     *
     * @return
     * @throws SerException
     */
    private String getSql(List<String> fields,String tablesId,List<String> fieldsTxt,String type) throws SerException{
        StringBuffer sql = new StringBuffer();
        StringBuffer field = new StringBuffer();
        StringBuffer txt = new StringBuffer();
        StringBuffer GroupByTxt = new StringBuffer();

        for (int i = 0; i < fields.size(); i++) {
            if (field.length() > 0 ){
                field.append(",");
                field.append(fields.get(i));
            }else {
                field.append(fields.get(i));
            }
            if (!"count".equals(fields.get(i))){
                if (GroupByTxt.length() > 0 ){
                    GroupByTxt.append(",");
                    GroupByTxt.append(fields.get(i));
                }else {
                    GroupByTxt.append(fields.get(i));
                }
            }
        }
        for (int i = 0; i < fieldsTxt.size(); i++) {
            if (txt.length() > 0 ){
                txt.append(" AND ");
                txt.append(fieldsTxt.get(i));
            }else {
                txt.append(fieldsTxt.get(i));
            }
        }
        String str = "";
        String startTime = "";
        String endTime = "";
        if ("1".equals(type)){
            startTime = DateUtil.dateToString(LocalDate.now())+" 00:00:00";
            endTime = DateUtil.dateToString(LocalDate.now())+" 23:59:59";
        }else {
            LocalDate to = LocalDate.now().plusDays(1); // +1
            startTime = DateUtil.dateToString(to)+" 00:00:00";
            endTime = DateUtil.dateToString(to)+" 23:59:59";
        }

        //goddess_taskallotment.

        str = "(SELECT any_value(ifnull(a.project,'无')) AS project,any_value(ifnull(b.name,'无')) AS name, " +
                "       any_value(ifnull(c.initiate,'无')) AS initiate,any_value(ifnull(c.taskName,'无')) AS taskName, " +
                "       any_value(ifnull(c.charge,'无')) AS charge,any_value(ifnull(c.execute,'无')) AS execute, " +
                "       any_value(ifnull(c.taskType,'无')) AS taskType,any_value(ifnull(c.type,'无')) AS type,any_value(ifnull(c.content,'无')) AS content, " +
                "       any_value(sum(c.planNum)) AS planNum,any_value(sum(c.actualNum)) AS actualNum, " +
                "       any_value(sum(c.actualTime)) AS actualTime,any_value(c.actualType) AS actualType, " +
                "       any_value(sum(c.needTime)) AS needTime,any_value(c.needType) AS needType, " +
                "       any_value(sum(c.executeTime)) AS executeTime,any_value(c.executeType) AS executeType, " +
                "       any_value(ifnull(c.remark,'无')) AS remark,any_value(ifnull(c.executeArea,'无')) AS executeArea, " +
                "       any_value(ifnull(c.executeDepart,'无')) AS executeDepart,any_value(ifnull(c.finishStatus,'无')) AS finishStatus, " +
                "       any_value(ifnull(c.priority,'无')) AS priority,any_value(ifnull(c.taskStatus,'无')) AS taskStatus, " +
                "       any_value(count(*)) AS count FROM goddess_taskallotment.taskallotment_project a " +
                "      LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                "      LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id" +
                "   WHERE b.id IN ("+tablesId+") " +
                "   AND c.startTime BETWEEN '"+startTime+"' AND '"+endTime+"'" +
                "   GROUP BY "+GroupByTxt+" ) m";
        sql.append(" SELECT " + field +" FROM " + str + " WHERE " + txt );

        return sql.toString();
    }

    /**
     *  根据title与对应的value 替换中文意思
     * @param title
     * @return
     * @throws SerException
     */
    private String getContextByTitle(String title,String value) throws SerException{
        StringBuffer context = new StringBuffer();

        if ("taskType".equals(title)){
            switch (value){
                case "0":
                    context.append("行政任务");
                    break;
                case "1":
                    context.append("工程任务");
                    break;
                case "2":
                    context.append("培训任务");
                    break;
            }
        }else if ("finishStatus".equals(title)){
            switch (value){
                case "0":
                    context.append("已完成");
                    break;
                case "1":
                    context.append("未完成");
                    break;
            }
        }else if ("confirm".equals(title)){
            switch (value){
                case "1":
                    context.append("是");
                    break;
                case "0":
                    context.append("否");
                    break;
            }
        }else if ("reimbursement".equals(title)){
            switch (value){
                case "1":
                    context.append("是");
                    break;
                case "0":
                    context.append("否");
                    break;
            }
        }else if ("report".equals(title)){
            switch (value){
                case "1":
                    context.append("是");
                    break;
                case "0":
                    context.append("否");
                    break;
            }
        }else if ("delay".equals(title)){
            switch (value){
                case "1":
                    context.append("是");
                    break;
                case "0":
                    context.append("否");
                    break;
            }
        }else if ("taskStatus".equals(title)){
            switch (value){
                case "0":
                    context.append("已完成");
                    break;
                case "1":
                    context.append("正在执行");
                    break;
                case "2":
                    context.append("未完成");
                    break;
                case "3":
                    context.append("待接收");
                    break;
                case "4":
                    context.append("不接收");
                    break;
                case "5":
                    context.append("上报待审核");
                    break;
            }
        }else if ("executeType".equals(title)){
            switch (value){
                case "0":
                    context.append("分钟");
                    break;
                case "1":
                    context.append("小时");
                    break;
                case "2":
                    context.append("天");
                    break;
                default:context.append("无");break;
            }
        }else if ("needType".equals(title)){
            switch (value){
                case "0":
                    context.append("分钟");
                    break;
                case "1":
                    context.append("小时");
                    break;
                case "2":
                    context.append("天");
                    break;
                default:context.append("无");break;
            }
        }else if ("actualType".equals(title)){
            switch (value){
                case "0":
                    context.append("分钟");
                    break;
                case "1":
                    context.append("小时");
                    break;
                case "2":
                    context.append("天");
                    break;
                default:context.append("无");break;
            }
        } else {
            context.append(value);
        }
        return context.toString();
    }

    /**
     * II.根据对应的月周日获取当前sql
     * @return
     * @throws SerException
     */
    private String getSql(List<String> tableIds, String Type,List<String> fieldsTxt) throws SerException {
        StringBuffer tablesId = new StringBuffer();
        for (String tableId : tableIds) {
            if (tablesId.length() > 0){
                tablesId.append(",");
                tablesId.append("'"+ tableId +"'");
            }else {
                tablesId.append("'"+ tableId +"'");
            }
        }
        String startTime = "";
        String endTime = "";
        if ("day".equals(Type)){
            startTime = DateUtil.dateToString(LocalDate.now())+" 00:00:00";
            endTime = DateUtil.dateToString(LocalDate.now())+" 23:59:59";
        }else if ("week".equals(Type)){
            startTime = DateUtil.dateToString( DateUtil.getStartWeek() )+" 00:00:00";
            endTime =  DateUtil.dateToString(  DateUtil.getEndWeek()  )+" 23:59:59";
        }else if ("month".equals(Type)){
            startTime = DateUtil.dateToString( DateUtil.getStartMonth() )+" 00:00:00";
            endTime = DateUtil.dateToString( DateUtil.getEndMonth() )+" 23:59:59";
        }else {

        }
        String sql = "";
        if (startTime.length()>0&&endTime.length()>0){
            if (fieldsTxt.size()>0){
                sql = "SELECT planNum,finishStatus " +
                        "FROM  goddess_taskallotment.taskallotment_project a " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id " +
                        "WHERE b.id IN ( "+tablesId+" ) " +
                        " AND "+ fieldsTxt.get(0) +
                        " AND c.startTime BETWEEN '"+startTime+"' AND '"+endTime+"'";
            }else {
                sql = "SELECT planNum,finishStatus " +
                        "FROM  goddess_taskallotment.taskallotment_project a " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id " +
                        "WHERE b.id IN ( "+tablesId+" ) " +
                        "AND c.startTime BETWEEN '"+startTime+"' AND '"+endTime+"'";
            }
        }else {
            if (fieldsTxt.size()>0){
                sql = "SELECT planNum,finishStatus " +
                        "FROM  goddess_taskallotment.taskallotment_project a " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id " +
                        "WHERE b.id IN ( "+tablesId+" ) " +
                        " AND "+ fieldsTxt.get(0) ;
            }else {
                sql = "SELECT planNum,finishStatus " +
                        "FROM  goddess_taskallotment.taskallotment_project a " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                        "LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id " +
                        "WHERE b.id IN ( "+tablesId+" ) ";
            }

        }
        return sql;
    }

    /**
     * 根据字段获取数据库最新的数据
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    public List<String> values(CustomizeTO to) throws SerException {
        List<String> dataLists = new ArrayList<>();
        String title = getString(to.getField());
        String[] tablesId = to.getTablesId();
        StringBuffer val = new StringBuffer();

        if ("count".equals(title)
            || "taskVolume".equals(title)
            || "fiTaskVolume".equals(title)
            || "noTaskVolume".equals(title)){
            dataLists.add("全部");
        }else {
            if (null != title){
                dataLists.add("全部");
                StringBuffer tables = new StringBuffer();
                for (String table: tablesId) {
                    if (tables.length()>0){
                        tables.append(",");
                        tables.append("'"+table+"'");
                    }else {
                        tables.append("'"+table+"'");
                    }
                }
                StringBuffer sql = new StringBuffer();
                if ("peopleNum".equals(title)){
                    sql.append("SELECT execute FROM goddess_taskallotment.taskallotment_tasknode WHERE table_id in ("+tables+") AND initiate IS NOT NULL GROUP BY execute " );
                }else {
                    sql.append("SELECT " + title +" FROM goddess_taskallotment.taskallotment_tasknode WHERE table_id in ("+tables+") AND "+title+" IS NOT NULL GROUP BY " + title );
                }

                List<Object> datas = customizeSonSer.findBySql(sql.toString());

                for (int i=0;i<datas.size();i++){
                    if ("taskType".equals(title)){
                        switch (datas.get(i)+""){
                            case "0":
                                val.append("行政任务");
                                break;
                            case "1":
                                val.append("工程任务");
                                break;
                            case "2":
                                val.append("培训任务");
                                break;
                        }
                    }else if ("planNum".equals(title)){
                    }else if ("actualNum".equals(title)){
                    }else if ("remark".equals(title)){
                    }else if ("finishStatus".equals(title)){
                    }else if ("taskStatus".equals(title)){
                    }else if ("priority".equals(title)){
                    }else if ("count".equals(title)){
                    }else if ("executeTime".equals(title)){
                    }else if ("needTime".equals(title)){
                    }else if ("actualTime".equals(title)){
                    }else if ("executeType".equals(title)){
                    }else if ("needType".equals(title)){
                    }else if ("actualType".equals(title)){
                    }else if ("taskVolume".equals(title)){
                    }else if ("fiTaskVolume".equals(title)){
                    }else if ("noTaskVolume".equals(title)){
                    }else {
                        val.append(datas.get(i)+"");
                    }
                    if (!"".equals(val.toString())){
                        dataLists.add(val.toString());
                    }
                    val.setLength(0);
                }
            }else {
                throw new SerException("title不为空");
            }
        }

        return dataLists;
    }


    /**
     * 根据名字，任务id 获取人数
     *
     */
    public List<Object> getPeople(List<String> vals,List<String> tables) throws SerException{
        StringBuffer nameVal = new StringBuffer();

        for (int i = 0;i<vals.size();i++){
            if (nameVal.length()>0){
                nameVal.append(",");
                nameVal.append("'"+vals.get(i)+"'");
            }else {
                nameVal.append("'"+vals.get(i)+"'");
            }
        }
        StringBuffer tablesId = new StringBuffer();
        for (String tableId : tables) {
            if (tablesId.length() > 0){
                tablesId.append(",");
                tablesId.append("'"+ tableId +"'");
            }else {
                tablesId.append("'"+ tableId +"'");
            }
        }

        String sql = "SELECT execute FROM  goddess_taskallotment.taskallotment_project a LEFT JOIN goddess_taskallotment.taskallotment_table b ON a.id = b.project_id " +
                "  LEFT JOIN goddess_taskallotment.taskallotment_tasknode c ON  b.id = c.table_id WHERE " +
                "  b.id IN ("+tablesId+")  AND execute IN ("+nameVal+") " +
                " GROUP BY execute";
        List<Object> datas = customizeSonSer.findBySql(sql);
        return datas;
    }

    @Override
    public void send(String id) throws SerException {
        /** * 内容 */
        String context = "";
        /** * 标题 */
        String title = "";
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        /** * 内容 */
        context = detail(id);
        if ("1".equals(entity.getType())){
            if ("1".equals(entity.getTypeExplain())){
                title = "今日汇总明细";
            }else {
                title = "明日汇总明细";
            }
        }else{
            if ("1".equals(entity.getTypeExplain())){
                title = "日周月数量汇总";
            }else {
                title = "自定义数量汇总";
            }
        }

        String departEmial = null;
        //是否抄送本部门
        if (entity.getSendDepart()) {
            String projectId = entity.getProjectId();
            String depart = projectAPI.findByID(projectId).getDepart();
            CommonalityBO commonalityBO = commonalityAPI.findByDepartment(depart);
            if (null != commonalityBO) {
                departEmial = commonalityBO.getEmail();
            }
        }

        MessageTO messageTO = new MessageTO();
        messageTO.setTitle(title);
        messageTO.setContent(context);
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        //定时发送必须写
        messageTO.setSenderId("SYSTEM");
        messageTO.setSenderName("SYSTEM");

        String[] recivers = entity.getCollectObjec().split(",");
        if (null != recivers && recivers.length > 0) {
            List<String> set = Arrays.asList(recivers);
            if (null != departEmial) {
                if (!set.contains(departEmial)) {
                    set.add(departEmial);
                }
                String[] strings = new String[set.size()];
                strings = set.toArray(strings);
                messageTO.setReceivers(strings);
            } else {
                messageTO.setReceivers(recivers);
            }
            messageAPI.send(messageTO);
        }
    }

}
