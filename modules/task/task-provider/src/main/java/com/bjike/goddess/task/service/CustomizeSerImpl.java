package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.bo.CommonalityBO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.bo.CustomizeSonBO;
import com.bjike.goddess.task.dto.CollectDTO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.dto.CustomizeSonDTO;
import com.bjike.goddess.task.dto.ValDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.entity.CustomizeSon;
import com.bjike.goddess.task.enums.CollectSuitation;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.TimeType;
import com.bjike.goddess.task.to.CustomizeTO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TableAPI;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    @Override
    public List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        List<CustomizeBO> bos = new ArrayList<>();
        for (Customize customize : super.findByPage(dto)) {
            bos.add(tranBO(customize));
        }
        return bos;
    }

    private CustomizeBO tranBO(Customize customize) throws SerException {
        CustomizeBO bo = BeanTransform.copyProperties(customize, CustomizeBO.class);
        ProjectBO project = projectAPI.findByID(bo.getProjectId());
        if (null != project) {
            bo.setProject(project.getProject());
        }
        String[] tableIds = bo.getTablesId().split(",");
        bo.setTables(tableAPI.names(tableIds));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableIds.length; i++) {
            if (i == tableIds.length - 1) {
                sb.append(tableAPI.names(new String[]{tableIds[i]})[0]);
            } else {
                sb.append(tableAPI.names(new String[]{tableIds[i]})[0] + ",");
            }
        }
        bo.setTable(sb.toString());
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", customize.getId()));
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        List<CustomizeSonBO> sonBOS = BeanTransform.copyProperties(sons, CustomizeSonBO.class);
        bo.setSons(sonBOS);
        return bo;
    }

    @Override
    public Long count(CustomizeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(CustomizeTO to) throws SerException {
        String nickname = userAPI.currentUser().getUsername();
        Customize customize = BeanTransform.copyProperties(to, Customize.class, true, "tablesId");
        customize.setTablesId(StringUtils.join(to.getTablesId(), ","));
        customize.setUser(nickname);
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
                        sb.append(values.get(j) + "、");
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
//        if (customize.getEnable()) {
//            TaskSession.put(customize.getId(), customize);
//        }
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
        LocalDateTime sendTime = DateUtil.parseDateTime(to.getSendTime());
        entity.setSendTime(sendTime);
        LocalDateTime lastTime = null;
        switch (remindType) {
            case MINTUE:
                lastTime = sendTime.minusMinutes(to.getDateVal());
                break;
            case HOUR:
                lastTime = sendTime.minusHours(to.getDateVal());
                break;
            case DAY:
                lastTime = sendTime.minusDays(to.getDateVal());
                break;
            case WEEK:
                lastTime = sendTime.minusWeeks(to.getDateVal());
                break;
            case MONTH:
                lastTime = sendTime.minusMonths(to.getDateVal());
                break;
        }
        entity.setLastTime(lastTime);
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
        if (!sons.isEmpty()
        {
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
        return tranBO(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void edit(CustomizeTO to) throws SerException {
        Customize entity = super.findById(to.getId());
        Customize customize = BeanTransform.copyProperties(to, Customize.class, true, "tablesId");
        customize.setTablesId(StringUtils.join(to.getTablesId(), ","));
        List<String> fields = to.getFields();
        List<CollectSuitation> collectSuitations = to.getCollectSuitations();
        List<ValDTO> vals = to.getVals();
        List<String> tableFields = to.getTableFields();
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", entity.getId()));
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        if (!sons.isEmpty()) {
            customizeSonSer.remove(sons);
        }
        if (null != fields && null != collectSuitations && null != vals && null != tableFields) {
            for (int i = 0; i < fields.size(); i++) {
                CustomizeSon customizeSon = new CustomizeSon();
                customizeSon.setCustomizeId(entity.getId());
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
                        sb.append(values.get(j) + "、");
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
        BeanUtils.copyProperties(customize, entity, "id", "createTime", "user");
        if (null != to.getCollectObjecs()) {
            entity.setCollectObjec(StringUtils.join(to.getCollectObjecs(), ","));
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
//        if (entity.getEnable()) {
//            TaskSession.put(entity.getId(), entity);
//        }
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
                "FROM taskallotment_tasknode " +
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
        Customize entity = super.findById(id);
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        CustomizeSonDTO sonDTO = new CustomizeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("customizeId", entity.getId()));
        sonDTO.getSorts().add("titleIndex=asc");
        List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
        String valTr = "<tr style='height: 20px'>";
        String endTr = "</tr>";
        StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
        result.append(nameTr);
        result.append("<td>汇总表名称</td>");
        result.append("<td>是否需要固定表头</td>");
        result.append("<td>汇总项目</td>");
        result.append("<td>汇总任务</td>");
        result.append("<td>汇总字段</td>");
        result.append("<td>汇总条件</td>");
        result.append("<td>任务类型</td>");
        result.append("<td>汇总表头字段</td>");
        result.append(endTr);
        result.append(valTr);
        result.append("<td rowspan='" + sons.size() + "'>" + entity.getName() + "</td>");
        if (entity.getNeedFixed()) {
            result.append("<td rowspan='" + sons.size() + "'>是</td>");
        } else {
            result.append("<td rowspan='" + sons.size() + "'>否</td>");
        }
        result.append("<td rowspan='" + sons.size() + "'>" + projectAPI.findByID(entity.getProjectId()).getProject() + "</td>");
        StringBuilder tableNames = new StringBuilder();
        int a = 0;
        for (String tableId : entity.getTablesId().split(",")) {
            if (a == entity.getTablesId().split(",").length - 1) {
                tableNames.append(projectAPI.table(tableId).getName());
            } else {
                tableNames.append(projectAPI.table(tableId).getName() + "、");
            }
            a++;
        }
        result.append("<td rowspan='" + sons.size() + "'>" + tableNames.toString() + "</td>");
        for (int i = 0; i < sons.size(); i++) {
            if (0 != i) {
                result.append(valTr);
            }
            result.append("<td>" + sons.get(i).getTitle() + "</td>");
            result.append("<td>" + sons.get(i).getCollectSuitation().toString() + "</td>");
            result.append("<td>" + sons.get(i).getType() + "</td>");
            result.append("<td>" + sons.get(i).getTableTitle() + "</td>");
            result.append(endTr);
        }
        result.append("</table>");
        return result.toString();
    }
}
