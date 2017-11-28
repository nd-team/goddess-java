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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.task.bo.CollectSchemeBO;
import com.bjike.goddess.task.dto.*;
import com.bjike.goddess.task.entity.CollectScheme;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.entity.CustomizeSon;
import com.bjike.goddess.task.entity.SchemeSon;
import com.bjike.goddess.task.enums.CollectSuitation;
import com.bjike.goddess.task.enums.SortType;
import com.bjike.goddess.task.enums.TimeType;
import com.bjike.goddess.task.to.CollectSchemeTO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 汇总方案业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskSerCache")
@Service
public class CollectSchemeSerImpl extends ServiceImpl<CollectScheme, CollectSchemeDTO> implements CollectSchemeSer {
    @Autowired
    private SchemeSonSer schemeSonSer;
    @Autowired
    private CustomizeSer customizeSer;
    @Autowired
    private CustomizeSonSer customizeSonSer;
    @Autowired
    private ScheduleSer scheduleSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(CollectSchemeTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        CollectScheme entity = BeanTransform.copyProperties(to, CollectScheme.class, true);
        LocalDateTime sendTime = entity.getSendTime();
        int remindVal = entity.getRemindVal();
        LocalDateTime lastTime = null;
        switch (entity.getRemindType()) {
            case MINTUE:
                lastTime = sendTime.minusMinutes(remindVal);
                break;
            case HOUR:
                lastTime = sendTime.minusHours(remindVal);
                break;
            case DAY:
                lastTime = sendTime.minusDays(remindVal);
                break;
            case WEEK:
                lastTime = sendTime.minusWeeks(remindVal);
                break;
            case MONTH:
                lastTime = sendTime.minusMonths(remindVal);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setCollectObject(StringUtils.join(to.getCollectObjects(), ","));
        entity.setTables(StringUtils.join(to.getTabless(), ","));
        entity.setCreater(name);
        super.save(entity);
        SortType sortType = to.getSortType();
        List<String> fileds = new ArrayList<>();
        switch (sortType) {
            case ACQUIESCENCE:
                fileds = fileds(to);
                break;
            case CUSTOM:
                fileds = to.getFileds();
                break;
        }
        for (int i = 0; i < fileds.size(); i++) {
            SchemeSon son = new SchemeSon();
            son.setTitle(fileds.get(i));
            son.setSchemeId(entity.getId());
            son.setTitleIndex(i);
            schemeSonSer.save(son);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(CollectSchemeTO to) throws SerException {
        CollectScheme entity = super.findById(to.getId());
        CollectScheme collectScheme = BeanTransform.copyProperties(to, CollectScheme.class, true);
        BeanUtils.copyProperties(collectScheme, entity, "id", "createTime", "creater");
        LocalDateTime sendTime = entity.getSendTime();
        int remindVal = entity.getRemindVal();
        LocalDateTime lastTime = null;
        switch (entity.getRemindType()) {
            case MINTUE:
                lastTime = sendTime.minusMinutes(remindVal);
                break;
            case HOUR:
                lastTime = sendTime.minusHours(remindVal);
                break;
            case DAY:
                lastTime = sendTime.minusDays(remindVal);
                break;
            case WEEK:
                lastTime = sendTime.minusWeeks(remindVal);
                break;
            case MONTH:
                lastTime = sendTime.minusMonths(remindVal);
                break;
        }
        entity.setLastTime(lastTime);
        entity.setCollectObject(StringUtils.join(to.getCollectObjects(), ","));
        entity.setTables(StringUtils.join(to.getTabless(), ","));
        super.update(entity);
        SchemeSonDTO sonDTO = new SchemeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("schemeId", entity.getId()));
        List<SchemeSon> sons = schemeSonSer.findByCis(sonDTO);
        if (!sons.isEmpty()) {
            schemeSonSer.remove(sons);
        }
        SortType sortType = to.getSortType();
        List<String> fileds = new ArrayList<>();
        switch (sortType) {
            case ACQUIESCENCE:
                fileds = fileds(to);
                break;
            case CUSTOM:
                fileds = to.getFileds();
                break;
        }
        for (int i = 0; i < fileds.size(); i++) {
            SchemeSon son = new SchemeSon();
            son.setTitle(fileds.get(i));
            son.setSchemeId(entity.getId());
            son.setTitleIndex(i);
            schemeSonSer.save(son);
        }
    }

    private CollectSchemeBO tranBO(CollectScheme collectScheme) throws SerException {
        CollectSchemeBO bo = BeanTransform.copyProperties(collectScheme, CollectSchemeBO.class, "tables");
        String[] tableIds = collectScheme.getTables().split(",");
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < tableIds.length; i++) {
            if (i == tableIds.length - 1) {
                sb.append(customizeSer.findById(tableIds[i]).getName());
            } else {
                sb.append(customizeSer.findById(tableIds[i]).getName() + "、");
            }
            list.add(customizeSer.findById(tableIds[i]).getName());
        }
        String[] tables = new String[list.size()];
        tables = list.toArray(tables);
        bo.setTables(sb.toString());
        bo.setTable(tables);
        return bo;
    }

    @Override
    public List<CollectSchemeBO> list(CollectSchemeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<CollectScheme> list = super.findByCis(dto, true);
        List<CollectSchemeBO> bos = new ArrayList<>();
        for (CollectScheme c : list) {
            bos.add(tranBO(c));
        }
        return bos;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        CollectScheme entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        SchemeSonDTO sonDTO = new SchemeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("schemeId", entity.getId()));
        List<SchemeSon> sons = schemeSonSer.findByCis(sonDTO);
        if (!sons.isEmpty()) {
            schemeSonSer.remove(sons);
        }
        super.remove(id);
    }

    @Override
    public CollectSchemeBO findByID(String id) throws SerException {
        CollectScheme entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return tranBO(entity);
    }

    @Override
    public Long count(CollectSchemeDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void quartz() throws SerException {
        CollectSchemeDTO dto = new CollectSchemeDTO();
        dto.getConditions().add(Restrict.eq("enable", Boolean.TRUE));
        List<CollectScheme> list = super.findByCis(dto);
        for (CollectScheme collectScheme : list) {
            Set<String> departEmails = new HashSet<>();
            if (collectScheme.getSendDepart()) {
                String[] cutomizeIds = collectScheme.getTables().split(",");
                for (String cutomizeId : cutomizeIds) {
                    Customize customize = customizeSer.findById(cutomizeId);
                    String projectId = customize.getProjectId();
                    String depart = projectAPI.findByID(projectId).getDepart();
                    List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.departByName(new String[]{depart});
                    if (null != departmentDetailBOS) {
                        CommonalityBO commonalityBO = commonalityAPI.findByDepartment(departmentDetailBOS.get(0).getId());
                        if (null != commonalityBO) {
                            departEmails.add(commonalityBO.getEmail());
                        }
                    }
                }
            }
            TimeType remindType = collectScheme.getRemindType();
            int remidVal = collectScheme.getRemindVal();
            LocalDateTime lastTime = collectScheme.getLastTime();
            LocalDateTime time = null;
            switch (remindType) {
                case MINTUE:
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
                String[] recivers = collectScheme.getCollectObject().split(",");
                if (null != recivers && recivers.length > 0) {
                    List<String> set = Arrays.asList(recivers);
                    MessageTO messageTO = new MessageTO();
                    messageTO.setTitle("汇总方案汇总");
                    messageTO.setContent(html(collectScheme));
                    messageTO.setMsgType(MsgType.SYS);
                    messageTO.setSendType(SendType.EMAIL);
                    messageTO.setRangeType(RangeType.SPECIFIED);
                    //定时发送必须写
                    messageTO.setSenderId("SYSTEM");
                    messageTO.setSenderName("SYSTEM");
                    if (!departEmails.isEmpty()) {
                        for (String s : departEmails) {
                            if (!set.contains(s)) {
                                set.add(s);
                            }
                        }
                        String[] strings = new String[set.size()];
                        strings = set.toArray(strings);
                        messageTO.setReceivers(strings);
                    } else {
                        messageTO.setReceivers(recivers);
                    }
                    messageAPI.send(messageTO);
                }
                collectScheme.setLastTime(LocalDateTime.now());
                collectScheme.setModifyTime(LocalDateTime.now());
                super.update(collectScheme);
            }
        }
    }

    @Override
    public String collect(String id) throws SerException {
        CollectScheme collectScheme = super.findById(id);
        if (null == collectScheme) {
            throw new SerException("该对象不存在");
        }
        return html(collectScheme);
    }

    private String html(CollectScheme collectScheme) throws SerException {
        String[] cutomizeIds = collectScheme.getTables().split(",");
        SchemeSonDTO sonDTO = new SchemeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("schemeId", collectScheme.getId()));
        sonDTO.getSorts().add("titleIndex=asc");
        StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
        String valTr = "<tr style='height: 20px'>";
        String endTr = "</tr>";
        result.append(nameTr);
        scheduleSer.fixTitleScheme(result);
        List<String> titles = schemeSonSer.findByCis(sonDTO).stream().map(SchemeSon::getTitle).collect(Collectors.toList());
        List<Long> count = new ArrayList<>();
        for (int i = 0; i < titles.size() + 1; i++) {
            Long l = 0l;
            count.add(l);
            if (i < titles.size()) {
                result.append("<td>" + titles.get(i) + "</td>");
            }
        }
        result.append(endTr);
        Set<String> initas = new HashSet<>();
        Set<String> charges = new HashSet<>();
        Set<String> excutes = new HashSet<>();
        LocalDate startTime = null;
        LocalDate endTime = null;
        switch (collectScheme.getCollectType()) {
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
        for (String cutomizeId : cutomizeIds) {
            List<String> fields = new ArrayList<>();
            List<CollectSuitation> collectSuitations = new ArrayList<>();
            List<ValDTO> vals = new ArrayList<>();
            Customize customize = customizeSer.findById(cutomizeId);
            String projectId = customize.getProjectId();
            String[] tablesId = customize.getTablesId().split(",");
            for (String title : titles) {
                CustomizeSonDTO customizeSonDTO = new CustomizeSonDTO();
                customizeSonDTO.getConditions().add(Restrict.eq("customizeId", cutomizeId));
                customizeSonDTO.getConditions().add(Restrict.eq("tableTitle", title));
                List<CustomizeSon> customizeSons = customizeSonSer.findByCis(customizeSonDTO);
                if (!customizeSons.isEmpty()) {
                    CustomizeSon son = customizeSons.get(0);
                    fields.add(son.getTitle());
                    collectSuitations.add(son.getCollectSuitation());
                    ValDTO valDTO = new ValDTO();
                    String[] types = son.getType().split("、");
                    List<String> values = new ArrayList<>();
                    for (String s : types) {
                        values.add(s);
                    }
                    valDTO.setValues(values);
                    vals.add(valDTO);
                } else {
                    fields.add("null");   //用于标识
                    collectSuitations.add(CollectSuitation.EQ);
                    vals.add(new ValDTO());
                }
            }
            CollectDTO collectDTO = new CollectDTO();
            collectDTO.setFields(fields);
            collectDTO.setCollectSuitations(collectSuitations);
            collectDTO.setVals(vals);
            collectDTO.setTablesId(tablesId);
            collectDTO.setTableFields(titles);
            collectDTO.setNeedFixed(true);
            collectDTO.setProjectId(projectId);
            if (null != startTime && null != endTime) {
                String start = DateUtil.dateToString(startTime);
                String end = DateUtil.dateToString(endTime);
                collectDTO.setStartTime(start);
                collectDTO.setEndTime(end);
            }
            scheduleSer.htmlScheme(result, collectDTO, cutomizeId, count, initas, charges, excutes);
        }
        result.append(valTr);
        result.append("<td colspan='7'>合计</td>");
        for (int i = 0; i < count.size(); i++) {
            result.append("<td>" + count.get(i) + "</td>");
        }
        result.append(endTr);
        result.append("</table>");
        return result.toString();
    }

    @Override
    public List<String> fileds(CollectSchemeTO to) throws SerException {
        List<String> list = new ArrayList<>();
        String[] tabless = to.getTabless();
        for (String s : tabless) {
            CustomizeSonDTO sonDTO = new CustomizeSonDTO();
            sonDTO.getConditions().add(Restrict.eq("customizeId", s));
            sonDTO.getSorts().add("titleIndex=asc");
            List<CustomizeSon> sons = customizeSonSer.findByCis(sonDTO);
            for (CustomizeSon customizeSon : sons) {
                if (!list.contains(customizeSon.getTableTitle())) {
                    list.add(customizeSon.getTableTitle());
                }
            }
        }
        return list;
    }

    @Override
    public void notice(CollectSchemeTO to) throws SerException {
        CollectScheme entity = super.findById(to.getId());
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        Set<String> departEmails = new HashSet<>();
        if (to.getSendDepart()) {   //抄送给本部门
            String[] cutomizeIds = entity.getTables().split(",");
            for (String cutomizeId : cutomizeIds) {
                Customize customize = customizeSer.findById(cutomizeId);
                String projectId = customize.getProjectId();
                String depart = projectAPI.findByID(projectId).getDepart();
                List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.departByName(new String[]{depart});
                if (null != departmentDetailBOS) {
                    CommonalityBO commonalityBO = commonalityAPI.findByDepartment(departmentDetailBOS.get(0).getId());
                    if (null != commonalityBO) {
                        departEmails.add(commonalityBO.getEmail());
                    }
                }
            }
        }
        String[] recivers = to.getCollectObjects();
        if (null != recivers && recivers.length > 0) {
            List<String> set = Arrays.asList(recivers);
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("汇总方案汇总");
            messageTO.setContent(html(entity));
            messageTO.setMsgType(MsgType.SYS);
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setRangeType(RangeType.SPECIFIED);
            //定时发送必须写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            if (!departEmails.isEmpty()) {
                for (String s : departEmails) {
                    if (!set.contains(s)) {
                        set.add(s);
                    }
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

    @Override
    public String detail(String id) throws SerException {
        CollectScheme entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        String[] tableIds = entity.getTables().split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableIds.length; i++) {
            if (i == tableIds.length - 1) {
                sb.append(customizeSer.findById(tableIds[i]).getName());
            } else {
                sb.append(customizeSer.findById(tableIds[i]).getName() + "、");
            }
        }
        String nameTr = "<tr style='width: 350px ;height:30px;background-color: #00aaee'>";
        String valTr = "<tr style='height: 20px'>";
        String endTr = "</tr>";
        StringBuilder result = new StringBuilder("<table style='text-align:center;' border=1 width='95%' height='30px'  cellspacing='0'>");
        result.append(nameTr);
        result.append("<td>汇总方案名称</td>");
        result.append("<td>选用汇总表</td>");
        result.append("<td>汇总表头字段</td>");
        result.append(endTr);
        result.append(valTr);
        SchemeSonDTO sonDTO = new SchemeSonDTO();
        sonDTO.getConditions().add(Restrict.eq("schemeId", entity.getId()));
        sonDTO.getSorts().add("titleIndex=asc");
        List<SchemeSon> sons = schemeSonSer.findByCis(sonDTO);
        result.append("<td rowspan='" + sons.size() + "'>" + entity.getName() + "</td>");
        result.append("<td rowspan='" + sons.size() + "'>" + sb.toString() + "</td>");
        for (int i=0;i<sons.size();i++){
            if (0!=i){
                result.append(valTr);
            }
            result.append("<td>"+sons.get(i).getTitle()+"</td>");
            result.append(endTr);
        }
        result.append("</table>");
        return result.toString();
    }
}