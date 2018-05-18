package com.bjike.goddess.dimission.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dimission.bo.*;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;
import com.bjike.goddess.dimission.entity.Situation;
import com.bjike.goddess.dimission.enums.DimissionType;
import com.bjike.goddess.dimission.enums.GuideAddrStatus;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.SituationTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 离职办理节点情况业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:23 ]
 * @Description: [ 离职办理节点情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "dimissionSerCache")
@Service
public class SituationSerImpl extends ServiceImpl<Situation, SituationDTO> implements SituationSer {
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DimissionInfoSer dimissionInfoSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1");
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
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
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
            flag = cusPermissionSer.busCusPermission("2");
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
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
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
    public SituationBO save(SituationTO to) throws SerException {
        Situation entity = BeanTransform.copyProperties(to, Situation.class);
        entity = getDataByName(entity);
        super.save(entity);
        SituationBO situationBO = BeanTransform.copyProperties(entity, SituationBO.class, false);
        return situationBO;
    }

    @Override
    public SituationBO update(SituationTO to) throws SerException {
        Situation entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanUtils.copyProperties(to, entity);
        entity = getDataByName(entity);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        SituationBO situationBO = BeanTransform.copyProperties(entity, SituationBO.class, false);
        return situationBO;
    }

    @Override
    public SituationBO delete(String id) throws SerException {
        Situation entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        SituationBO situationBO = BeanTransform.copyProperties(entity, SituationBO.class, false);
        super.remove(entity);
        return situationBO;
    }

    @Override
    public List<SituationBO> list(SituationDTO dto) throws SerException {
        searchCondiction(dto);
        List<Situation> situations = super.findByPage(dto);
        List<SituationBO> situationbos = BeanTransform.copyProperties(situations, SituationBO.class, false);
        return situationbos;
    }

    @Override
    public SituationBO getById(String id) throws SerException {
        Situation entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        SituationBO situationbo = BeanTransform.copyProperties(entity, SituationBO.class, false);
        return situationbo;
    }

    @Override
    public List<String> getName() throws SerException {
        List<DimissionInfo> dimissionInfos = dimissionInfoSer.findAll();
        if (null != dimissionInfos && dimissionInfos.size() > 0) {
            return dimissionInfos.stream().map(DimissionInfo::getUsername).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Boolean isSelf(String name) throws SerException {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        DimissionInfoDTO dimissionInfoDTO = new DimissionInfoDTO();
        dimissionInfoDTO.getConditions().add(Restrict.eq("username", name));
        dimissionInfoDTO.getConditions().add(Restrict.eq("type", DimissionType.PRESUME));
        List<DimissionInfo> dimissionInfos = dimissionInfoSer.findByCis(dimissionInfoDTO);
        if (dimissionInfos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DimissionCollectBO> collect(String startTime, String endTime) throws SerException {
        //获取地区,部门,岗位层级
        String[] fields = new String[]{"area", "department", "positionLever"};
        StringBuilder sql = new StringBuilder(" select area, department, positionLever from dimission_situation ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "'");
            sql.append(" and '" + endTime + "'");
        }
        List<DistinctCollectConditionBO> distinctCollectConditionBOs = super.findBySql(sql.toString(), DistinctCollectConditionBO.class, fields);

        TreeSet<DistinctCollectConditionBO> treeSet = this.filter();
        for (DistinctCollectConditionBO distinctCollectConditionBO : distinctCollectConditionBOs) {
            treeSet.add(distinctCollectConditionBO);
        }

        List<DimissionCollectBO> dimissionCollectBOs = new ArrayList<>(0);
        for (DistinctCollectConditionBO distinctCollectConditionBO : treeSet) {
            dimissionCollectBOs.add(getDimissionCollectBO(distinctCollectConditionBO, startTime, endTime));
        }
        return dimissionCollectBOs;
    }

    @Override
    public Long getTotal() throws SerException {
        SituationDTO situationDTO = new SituationDTO();
        return super.count(situationDTO);
    }

    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        //获得部门汇总数据
        List<DimissionCollectBO> dimissionCollectBOs = findDataByDepartment(startTime, endTime);

        String text_1 = "离职管理月汇总" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, dimissionCollectBOs);
    }

    private List<DimissionCollectBO> findDataByDepartment(String startTime, String endTime) throws SerException {
        //获取部门
        String[] fields = new String[]{"department"};
        StringBuilder sql = new StringBuilder(" select department from dimission_situation ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" where createTime between '" + startTime + "'");
            sql.append(" and '" + endTime + "'");
        }
        List<DistinctCollectConditionBO> distinctCollectConditionBOs = super.findBySql(sql.toString(), DistinctCollectConditionBO.class, fields);

        TreeSet<DistinctCollectConditionBO> treeSet = this.filter();
        for (DistinctCollectConditionBO distinctCollectConditionBO : distinctCollectConditionBOs) {
            treeSet.add(distinctCollectConditionBO);
        }

        List<DimissionCollectBO> dimissionCollectBOs = new ArrayList<>(0);
        for (DistinctCollectConditionBO distinctCollectConditionBO : treeSet) {
            dimissionCollectBOs.add(getDimissionCollectBOByDepartment(distinctCollectConditionBO, startTime, endTime));
        }
        return dimissionCollectBOs;
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        if (StringUtils.isBlank(day)) {
            day = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = day;
        String endTime = getSpecifiedDayAfter(day);
        //获得部门汇总数据
        List<DimissionCollectBO> dimissionCollectBOs = findDataByDepartment(startTime, endTime);

        String text_1 = "离职管理日汇总" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, dimissionCollectBOs);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];

        //获得部门汇总数据
        List<DimissionCollectBO> dimissionCollectBOs = findDataByDepartment(startTime, endTime);

        String text_1 = "离职管理周汇总" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, dimissionCollectBOs);
    }

    @Override
    public OptionBO figureShowAll() throws SerException {
        String startTime = "";
        String endTime = "";

        //获得部门汇总数据
        List<DimissionCollectBO> dimissionCollectBOs = findDataByDepartment(startTime, endTime);

        String text_1 = "离职管理累计汇总柱状图" + "(" + startTime + "-" + endTime + ")";
        return getOptionBO(text_1, dimissionCollectBOs);
    }

    //柱状图数据
    private OptionBO getOptionBO(String text_1, List<DimissionCollectBO> dimissionCollectBOs) throws SerException {
//        //增加合计
//        DimissionCollectBO bo = new DimissionCollectBO();
//        bo.setDepartments("合计");
//        bo.setProjectNum(list.stream().mapToLong(ManagerBO::getProjectNum).sum());
//        bo.setPositionNum(list.stream().mapToLong(ManagerBO::getPositionNum).sum());
//        bo.setNoEmployeesNum(list.stream().mapToLong(ManagerBO::getNoEmployeesNum).sum());
//        bo.setPartjobNum(list.stream().mapToLong(ManagerBO::getPartjobNum).sum());
//        bo.setJobDescriptionNum(list.stream().mapToLong(ManagerBO::getJobDescriptionNum).sum());
//        bo.setNoJobNum(list.stream().mapToLong(ManagerBO::getNoJobNum).sum());
//        bo.setMoveNum(list.stream().mapToLong(ManagerBO::getMoveNum).sum());
//        bo.setRotationNum(list.stream().mapToLong(ManagerBO::getRotationNum).sum());
//        bo.setWaitEntryNum(list.stream().mapToLong(ManagerBO::getWaitEntryNum).sum());
//        bo.setEntryNum(list.stream().mapToLong(ManagerBO::getEntryNum).sum());
//        bo.setDimissionNum(list.stream().mapToLong(ManagerBO::getDimissionNum).sum());
//        bo.setWaitDimissionNum(list.stream().mapToLong(ManagerBO::getWaitDimissionNum).sum());
//        list.add(bo);

        List<String> departList = dimissionCollectBOs.stream().map(DimissionCollectBO::getDepartment).collect(Collectors.toList());
        String[] text_3 = departList.toArray(new String[departList.size()]);

        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        List<String> text_list2 = new ArrayList<>();

        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        String[] text_2 = new String[]{"申请离职人数", "待离职人数",
                "离职人数", "自离人数", "辞退人数"};
        text_list2 = Arrays.stream(text_2).collect(Collectors.toList());
        xAxisBO.setData(text_3);
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (dimissionCollectBOs != null && dimissionCollectBOs.size() > 0) {
//            for (ManagerBO managerBO : list) {
//
////                text_list2.add(String.valueOf(managerBO.getDepartments()));
//
//                //柱状图数据
//                SeriesBO seriesBO = new SeriesBO();
//                seriesBO.setName(managerBO.());
//                seriesBO.setType("bar");
//
//                Integer[] number = new Integer[]{managerBO.getProjectNum().intValue(), managerBO.getPositionNum().intValue(),
//                        managerBO.getNoEmployeesNum().intValue(), managerBO.getPartjobNum().intValue(), managerBO.getJobDescriptionNum().intValue(),
//                        managerBO.getNoJobNum().intValue(), managerBO.getMoveNum().intValue(), managerBO.getRotationNum().intValue(),
//                        managerBO.getWaitEntryNum().intValue(), managerBO.getEntryNum().intValue(), managerBO.getDimissionNum().intValue(), managerBO.getWaitDimissionNum().intValue()};
//                seriesBO.setData(number);
//                seriesBOList.add(seriesBO);
//            }
            for (String str : text_list2) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(str);
                seriesBO.setType("bar");
                List<Integer> number = new ArrayList<>(0);
                for (DimissionCollectBO bo : dimissionCollectBOs) {
                    Integer i = 0;
                    if (str.equals("申请离职人数")) {
                        Integer j = bo.getWriteApplyNum();
                        i = j.intValue();
                    }
                    if (str.equals("待离职人数")) {
                        Integer j = bo.getWaitDimissionNum();
                        i = j.intValue();
                    }
                    if (str.equals("离职人数")) {
                        Integer j = bo.getLeftNum();
                        i = j.intValue();
                    }
                    if (str.equals("自离人数")) {
                        Integer j = bo.getSelfNum();
                        i = j.intValue();
                    }
                    if (str.equals("辞退人数")) {
                        Integer j = bo.getDismissNum();
                        i = j.intValue();
                    }
                    number.add(i);
                }
                //柱状图数据
                Integer[] numbers = number.toArray(new Integer[number.size()]);
                seriesBO.setData(numbers);
                seriesBOList.add(seriesBO);
            }
        }

//        String[] text_2 = new String[text_list2.size()];
//        text_2 = text_list2.toArray(text_2);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        legendBO.setData(text_2);
        TooltipBO tooltipBO = new TooltipBO();
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }




    //根据名字获取地区,部门,岗位,岗位层级
    private Situation getDataByName(Situation entity) throws SerException {
        List<PositionDetailBO> positionDetailBOs = new ArrayList<>(0);
        if (moduleAPI.isCheck("organize")) {
            positionDetailBOs = positionDetailUserAPI.getPositionDetail(entity.getName());
        }
        if (null != positionDetailBOs && positionDetailBOs.size() > 0) {
            entity.setArea(positionDetailBOs.get(0).getArea());
            entity.setDepartment(positionDetailBOs.get(0).getDepartmentName());
            entity.setPosition(positionDetailBOs.get(0).getPosition());
            entity.setPositionLever(positionDetailBOs.get(0).getArrangementName());
        } else {
            entity.setArea(" ");
            entity.setDepartment(" ");
            entity.setPosition(" ");
            entity.setPositionLever(" ");
        }
        return entity;
    }

    private void searchCondiction(SituationDTO dto) {
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }
    }

    private DimissionCollectBO getDimissionCollectBO(DistinctCollectConditionBO distinctCollectConditionBO, String startTime, String endTime) throws SerException {
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime) && startTime.equals(endTime)) {
            endTime = this.getSpecifiedDayAfter(startTime);
        }
        CollectConditionBO collectConditionBO = new CollectConditionBO();
        collectConditionBO.setArea(distinctCollectConditionBO.getArea());
        collectConditionBO.setDepartment(distinctCollectConditionBO.getDepartment());
        collectConditionBO.setPositionLever(distinctCollectConditionBO.getPositionLever());
        collectConditionBO.setStartTime(startTime);
        collectConditionBO.setEndTime(endTime);
        String value = "";
        String table = "";
        DimissionCollectBO dimissionCollectBO = new DimissionCollectBO();
        dimissionCollectBO.setArea(collectConditionBO.getArea());
        dimissionCollectBO.setDepartment(collectConditionBO.getDepartment());
        dimissionCollectBO.setPositionLever(collectConditionBO.getPositionLever());
        value = "is_writeApply";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWriteApplyNum(getData(collectConditionBO));

        value = "is_waitDimission";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWaitDimissionNum(getData(collectConditionBO));

        value = "is_left";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setLeftNum(getData(collectConditionBO));

        value = "is_selfLeave";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setSelfNum(getData(collectConditionBO));

        value = "is_freeze";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setFreezeNum(getData(collectConditionBO));

        value = "is_dismiss";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setDismissNum(getData(collectConditionBO));

        value = "is_retained";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setRetainedNum(getData(collectConditionBO));

        value = "is_managerInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setManagerInterviewNum(getData(collectConditionBO));

        value = "is_principalInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPrincipalInterviewNum(getData(collectConditionBO));

        value = "is_welfareInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWelfareInterviewNum(getData(collectConditionBO));

        value = "is_officeInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setOfficeInterviewNum(getData(collectConditionBO));

        value = "is_leaveEarly";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setLeaveEarlyNum(getData(collectConditionBO));

        value = "is_managerAudit";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setManagerAuditNum(getData(collectConditionBO));

        value = "is_principalAudit";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPrincipalAuditNum(getData(collectConditionBO));

        value = "is_postponement";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPostponementNum(getData(collectConditionBO));

        value = "is_workTransfer";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWorkTransferNum(getData(collectConditionBO));

        value = "is_handover";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setHandoverNum(getData(collectConditionBO));
        return dimissionCollectBO;
    }

    private DimissionCollectBO getDimissionCollectBOByDepartment(DistinctCollectConditionBO distinctCollectConditionBO, String startTime, String endTime) throws SerException {
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime) && startTime.equals(endTime)) {
            endTime = this.getSpecifiedDayAfter(startTime);
        }
        CollectConditionBO collectConditionBO = new CollectConditionBO();
        collectConditionBO.setDepartment(distinctCollectConditionBO.getDepartment());
        collectConditionBO.setStartTime(startTime);
        collectConditionBO.setEndTime(endTime);
        String value = "";
        String table = "";
        DimissionCollectBO dimissionCollectBO = new DimissionCollectBO();
        dimissionCollectBO.setDepartment(collectConditionBO.getDepartment());
        value = "is_writeApply";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWriteApplyNum(getDataByDepartment(collectConditionBO));

        value = "is_waitDimission";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWaitDimissionNum(getDataByDepartment(collectConditionBO));

        value = "is_left";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setLeftNum(getDataByDepartment(collectConditionBO));

        value = "is_selfLeave";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setSelfNum(getDataByDepartment(collectConditionBO));

        value = "is_freeze";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setFreezeNum(getDataByDepartment(collectConditionBO));

        value = "is_dismiss";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setDismissNum(getDataByDepartment(collectConditionBO));

        value = "is_retained";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setRetainedNum(getDataByDepartment(collectConditionBO));

        value = "is_managerInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setManagerInterviewNum(getDataByDepartment(collectConditionBO));

        value = "is_principalInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPrincipalInterviewNum(getDataByDepartment(collectConditionBO));

        value = "is_welfareInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWelfareInterviewNum(getDataByDepartment(collectConditionBO));

        value = "is_officeInterview";
        table = "dimission_interview";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setOfficeInterviewNum(getDataByDepartment(collectConditionBO));

        value = "is_leaveEarly";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setLeaveEarlyNum(getDataByDepartment(collectConditionBO));

        value = "is_managerAudit";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setManagerAuditNum(getDataByDepartment(collectConditionBO));

        value = "is_principalAudit";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPrincipalAuditNum(getDataByDepartment(collectConditionBO));

        value = "is_postponement";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setPostponementNum(getDataByDepartment(collectConditionBO));

        value = "is_workTransfer";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setWorkTransferNum(getDataByDepartment(collectConditionBO));

        value = "is_handover";
        table = "dimission_situation";
        collectConditionBO.setValue(value);
        collectConditionBO.setTable(table);
        dimissionCollectBO.setHandoverNum(getDataByDepartment(collectConditionBO));
        return dimissionCollectBO;
    }

    private Integer getData(CollectConditionBO collectConditionBO) throws SerException {
        String value1 = collectConditionBO.getValue().substring(collectConditionBO.getValue().indexOf("_") + 1, collectConditionBO.getValue().length());
        if ("left".equals(value1)) {
            value1 = "left1";
        }
        StringBuilder sql = new StringBuilder(" SELECT count(" + collectConditionBO.getValue() + ") as " + value1 + " ");
        sql.append(" FROM " + collectConditionBO.getTable() + " ");
        sql.append(" WHERE " + collectConditionBO.getValue() + " = 1 ");
        sql.append(" and area = '" + collectConditionBO.getArea() + "' AND department = '" + collectConditionBO.getDepartment() + "' AND positionLever = '" + collectConditionBO.getPositionLever() + "' ");
        if (StringUtils.isNotBlank(collectConditionBO.getStartTime()) && StringUtils.isNotBlank(collectConditionBO.getEndTime())) {
            sql.append(" AND createTime BETWEEN '" + collectConditionBO.getStartTime() + "' AND '" + collectConditionBO.getEndTime() + "' ");
        }
        String fields[] = new String[]{"writeApplyNum"};
        List<DimissionCollectBO> dimissionCollectBOs = super.findBySql(sql.toString(), DimissionCollectBO.class, fields);
        if (null != dimissionCollectBOs && dimissionCollectBOs.size() > 0) {
            return dimissionCollectBOs.get(0).getWriteApplyNum();
        }
        return 0;
    }

    private Integer getDataByDepartment(CollectConditionBO collectConditionBO) throws SerException {
        String value1 = collectConditionBO.getValue().substring(collectConditionBO.getValue().indexOf("_") + 1, collectConditionBO.getValue().length());
        if ("left".equals(value1)) {
            value1 = "left1";
        }
        StringBuilder sql = new StringBuilder(" SELECT count(" + collectConditionBO.getValue() + ") as " + value1 + " ");
        sql.append(" FROM " + collectConditionBO.getTable() + " ");
        sql.append(" WHERE " + collectConditionBO.getValue() + " = 1 ");
        sql.append(" AND department = '" + collectConditionBO.getDepartment() + "' ");
        if (StringUtils.isNotBlank(collectConditionBO.getStartTime()) && StringUtils.isNotBlank(collectConditionBO.getEndTime())) {
            sql.append(" AND createTime BETWEEN '" + collectConditionBO.getStartTime() + "' AND '" + collectConditionBO.getEndTime() + "' ");
        }
        String fields[] = new String[]{"writeApplyNum"};
        List<DimissionCollectBO> dimissionCollectBOs = super.findBySql(sql.toString(), DimissionCollectBO.class, fields);
        if (null != dimissionCollectBOs && dimissionCollectBOs.size() > 0) {
            return dimissionCollectBOs.get(0).getWriteApplyNum();
        }
        return 0;
    }


    private <T> TreeSet<T> filter() throws SerException {
        TreeSet<T> treeSet = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Field[] field = o1.getClass().getDeclaredFields();//获取实体类的所有属性，返回field数组
                int num = 0;   //用于识别属性相同的个数
                int sum = 0;    //用于识别该对象除了集合的属性值个数
                for (Field f : field) {//遍历所有的属性
                    String type = f.getGenericType().toString();//获取属性的类型
                    if (type.indexOf("java.util.List") < 0) {
                        sum++;
                        String name = f.getName(); // 获取属性的名字
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
                        try {
                            Method m = o1.getClass().getMethod("get" + name);
                            Object value = m.invoke(o1);// 调用getter方法获取属性值
                            Method m1 = o2.getClass().getMethod("get" + name);
                            Object value1 = m1.invoke(o2);
                            if (value.equals(value1)) {    //判断该属性值是否相同
                                num++;
                            }
                        } catch (Exception e) {

                        }
                    }
                }
                if (num == sum) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return treeSet;
    }

    public static void main(String[] aadfs) {
        SituationSerImpl aa = new SituationSerImpl();
        List<CollectConditionBO> list = new ArrayList<>(0);
        CollectConditionBO collectConditionBO = new CollectConditionBO();
        collectConditionBO.setArea("123");
        collectConditionBO.setDepartment("456");
        collectConditionBO.setPositionLever("123");
        collectConditionBO.setEndTime("0");
        collectConditionBO.setStartTime("0");
        collectConditionBO.setValue("0");
        CollectConditionBO collectConditionBO1 = new CollectConditionBO();
        collectConditionBO1.setArea("123");
        collectConditionBO1.setDepartment("456");
        collectConditionBO1.setPositionLever("123");
        collectConditionBO1.setEndTime("0");
        collectConditionBO1.setStartTime("0");
        collectConditionBO1.setValue("0");
        list.add(collectConditionBO);
        list.add(collectConditionBO1);
        try {
            TreeSet<CollectConditionBO> treeSet = aa.filter();
            for (CollectConditionBO bo : list) {
                treeSet.add(bo);
            }
            System.out.print(treeSet);
        } catch (Exception e) {

        }
    }

    //获取指定日期的后一天
    public String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }
    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }
}
