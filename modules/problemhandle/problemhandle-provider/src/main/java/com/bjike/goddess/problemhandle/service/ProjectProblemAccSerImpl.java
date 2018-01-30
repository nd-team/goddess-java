package com.bjike.goddess.problemhandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.problemhandle.bo.*;
import com.bjike.goddess.problemhandle.dto.ProjectProblemAccDTO;
import com.bjike.goddess.problemhandle.entity.CommunicationFormwork;
import com.bjike.goddess.problemhandle.entity.ProjectProblemAcc;
import com.bjike.goddess.problemhandle.enums.GuideAddrStatus;
import com.bjike.goddess.problemhandle.excel.ProjectProblemAccExport;
import com.bjike.goddess.problemhandle.excel.ProjectProblemAccExportTemple;
import com.bjike.goddess.problemhandle.excel.SonPermissionObject;
import com.bjike.goddess.problemhandle.to.GuidePermissionTO;
import com.bjike.goddess.problemhandle.to.ProjectProblemAccTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.BinaryClient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目中问题受理和处理业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "problemhandleSerCache")
@Service
public class ProjectProblemAccSerImpl extends ServiceImpl<ProjectProblemAcc, ProjectProblemAccDTO> implements ProjectProblemAccSer {
    @Autowired
    private ProPermissionSer proPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CommunicationFormworkSer communicationFormworkSer;
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
            flag = proPermissionSer.getProPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（部门级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = proPermissionSer.busProPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = proPermissionSer.getProPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = proPermissionSer.busProPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeProblem = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddProblem = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("projectproblemacc");
        obj.setDescribesion("项目中问题受理和处理");
        if (flagSeeProblem || flagAddProblem) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeResult = communicationFormworkSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("communicationformwork");
        obj.setDescribesion("各类沟通模板");
        if (flagSeeResult) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
        return flag;
    }

    @Override
    public Long countProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        seachCondi(projectProblemAccDTO);
        Long count = super.count(projectProblemAccDTO);
        return count;
    }

    @Override
    public ProjectProblemAccBO getOne(String id) throws SerException {
        ProjectProblemAcc projectProblemAcc = super.findById(id);
        return BeanTransform.copyProperties(projectProblemAcc, ProjectProblemAccBO.class);
    }

    @Override
    public List<ProjectProblemAccBO> findListProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        checkSeeIdentity();
        seachCondi(projectProblemAccDTO);
        List<ProjectProblemAcc> projectProblemAccList = super.findByCis(projectProblemAccDTO, true);
        return BeanTransform.copyProperties(projectProblemAccList, ProjectProblemAccBO.class);
    }

    private void seachCondi(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        if (StringUtils.isNotBlank(projectProblemAccDTO.getQuestionNum())) {
            projectProblemAccDTO.getConditions().add(Restrict.eq("questionNum", projectProblemAccDTO.getQuestionNum()));
        }
    }


    @Override
    public ProjectProblemAccBO insertProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
     checkAddIdentity();
        ProjectProblemAcc projectProblemAcc = BeanTransform.copyProperties(projectProblemAccTO, ProjectProblemAcc.class, true);
        projectProblemAcc.setCreateTime(LocalDateTime.now());
        projectProblemAcc.setStatus(Status.THAW);
        super.save(projectProblemAcc);
        return BeanTransform.copyProperties(projectProblemAcc, ProjectProblemAccBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectProblemAccBO editProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
      checkAddIdentity();
        ProjectProblemAcc projectProblemAcc = super.findById(projectProblemAccTO.getId());
        LocalDateTime dateTime = projectProblemAcc.getCreateTime();
        Status status = projectProblemAcc.getStatus();
        projectProblemAcc = BeanTransform.copyProperties(projectProblemAccTO, ProjectProblemAcc.class, true);
        projectProblemAcc.setCreateTime(dateTime);
        projectProblemAcc.setModifyTime(LocalDateTime.now());
        projectProblemAcc.setStatus(status);
        super.update(projectProblemAcc);
        return BeanTransform.copyProperties(projectProblemAcc, ProjectProblemAccBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProjectProblem(String id) throws SerException {
      checkAddIdentity();
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congeal(String id) throws SerException {
       checkAddIdentity();
        ProjectProblemAcc projectProblemAcc = super.findById(id);
        projectProblemAcc.setStatus(Status.CONGEAL);
        super.update(projectProblemAcc);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thaw(String id) throws SerException {
     checkAddIdentity();
        ProjectProblemAcc projectProblemAcc = super.findById(id);
        projectProblemAcc.setStatus(Status.THAW);
        super.update(projectProblemAcc);
    }

    @Override
    public void importExcel(List<ProjectProblemAccTO> projectProblemAccTOS) throws SerException {
        List<ProjectProblemAcc> projectProblemAccList = new ArrayList<>();
        for (ProjectProblemAccTO to : projectProblemAccTOS) {
            ProjectProblemAcc projectProblemAcc = BeanTransform.copyProperties(to, ProjectProblemAcc.class, true);
            projectProblemAcc.setCreateTime(LocalDateTime.now());
            projectProblemAcc.setStatus(Status.THAW);
            projectProblemAccList.add(projectProblemAcc);
        }
        super.save(projectProblemAccList);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        List<ProjectProblemAcc> list = super.findAll();
        List<ProjectProblemAccExport> exports = new ArrayList<>();
        for (ProjectProblemAcc projectProblemAcc: list){
            ProjectProblemAccExport export = BeanTransform.copyProperties(projectProblemAcc, ProjectProblemAccExport.class,
                    "communicated", "isOutward","closeLoop","needCoordinate");
            export.setCommunicated(checkBool(projectProblemAcc.getCommunicated()));
            export.setIsOutward(checkBool(projectProblemAcc.getOutward()));
            export.setCloseLoop(checkBool(projectProblemAcc.getCloseLoop()));
            export.setNeedCoordinate(checkBool(projectProblemAcc.getNeedCoordinate()));
            exports.add(export);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }
    private String checkBool(Boolean bool) throws SerException{
        String type = "";
        if(bool!=null){
            if(bool){
                type = "是";
            }else{
                type = "否";
            }
        }
        return type;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ProjectProblemAccExportTemple> projectProblemAccExportTemples = new ArrayList<>();
        ProjectProblemAccExportTemple templateExcel = new ProjectProblemAccExportTemple();
        templateExcel.setEnterOne("text");
        templateExcel.setQuestionNum("text");
        templateExcel.setEachDistrict("text");
        templateExcel.setSubordinateDepartment("text");
        templateExcel.setSubordinateModule("text");
        templateExcel.setQuestion("text");
        templateExcel.setEventDescription("text");
        templateExcel.setProblemDescription("text");
        templateExcel.setGetTime("text");
        templateExcel.setExpectedTime("text");
        templateExcel.setTypeProblem("text");
        templateExcel.setAssistDept("text");
        templateExcel.setMainFunction("text");
        templateExcel.setCorrelativeModule("text");
        templateExcel.setProblemPerson("text");
        templateExcel.setSourceProblem("text");
        templateExcel.setInfluence("text");
        templateExcel.setCommunicated("text");
        templateExcel.setProblemAcceptanceNum("text");
        templateExcel.setProblemHandledDepartment("text");
        templateExcel.setProblemHandledMoudle("text");
        templateExcel.setProbleAdmissible("text");
        templateExcel.setProblemFollowComTime("text");
        templateExcel.setInvolvedAmount(100d);
        templateExcel.setIsOutward("text");
        templateExcel.setBusinessState("text");
        templateExcel.setMarketInfoNum("text");
        templateExcel.setInternalProjectName("text");
        templateExcel.setCustomerNum("text");
        templateExcel.setCompanyName("text");
        templateExcel.setDuty("text");
        templateExcel.setName("text");
        templateExcel.setProblemProcessResult("text");
        templateExcel.setPlanTime("text");
        templateExcel.setCloseLoop("text");
        templateExcel.setProblemActually("text");
        templateExcel.setNeedCoordinate("text");
        templateExcel.setCoordinate("text");
        templateExcel.setCoordModule("text");
        templateExcel.setCoordResult("text");
        projectProblemAccExportTemples.add(templateExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(projectProblemAccExportTemples, excel);
        return bytes;
    }

    private List<String> findAllProject()throws SerException{
        List<ProjectProblemAcc> projectProblemAccList = super.findAll();
        if(CollectionUtils.isEmpty(projectProblemAccList)){
            return Collections.emptyList();
        }
        return projectProblemAccList.stream().map(ProjectProblemAcc::getSubordinateDepartment).distinct().collect(Collectors.toList());
    }

    @Override
    public List<ProjectSummaryBO> summaDay(String summationDate) throws SerException {
        if (StringUtils.isBlank(summationDate)) {
            summationDate = LocalDate.now().toString();
        }
        String startDate = summationDate;
        String endDate = summationDate;
        return totalMethod(startDate, endDate);
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

    @Override
    public List<ProjectSummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startDate = date[0];
        String endDate = date[1];
        return totalMethod(startDate, endDate);
    }

    private String[] quarterChange(Integer year, Integer quarter) throws SerException {
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().toString();
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
                startDate = LocalDate.now().toString();
                endDate = LocalDate.now().toString();
                break;
        }
        return new String[]{startDate, endDate};
    }

    @Override
    public List<ProjectSummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return totalMethod(startDate, endDate);
    }

    @Override
    public List<ProjectSummaryBO> summaQuarter(Integer year, Integer quarter) throws SerException {
        if (year == null || quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarterChange(year, quarter);
        return totalMethod(date[0], date[1]);
    }

    @Override
    public List<ProjectSummaryBO> summaTotal(String endDate) throws SerException {
        if(StringUtils.isBlank(endDate)){
            endDate = LocalDate.now().toString();
        }
        List<ProjectSummaryBO> projectSummaryBOList = new ArrayList<>();
        List<String> projects = findAllProject();
        if(projects!=null && projects.size()>0){
            for (String project : projects){
                StringBuffer sql = new StringBuffer();
                sql.append(" SELECT * FROM ( ");
                sql.append(" (SELECT count(*) AS problemTotalNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND getTime <= '"+endDate+"') a, ");
                sql.append(" (SELECT count(*) AS finishNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  is_closeLoop=1 AND getTime  <= '"+endDate+"')  b, ");
                sql.append(" (SELECT count(*) AS unFinishNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  is_closeLoop=0 AND getTime <= '"+endDate+"') c, ");
                sql.append(" (SELECT count(*) AS dispatchingClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =0 AND getTime <= '"+endDate+"') d, ");
                sql.append(" (SELECT count(*) AS personnelClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =1 AND getTime <= '"+endDate+"') e, ");
                sql.append(" (SELECT count(*) AS progressClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =2 AND getTime <= '"+endDate+"') f, ");
                sql.append(" (SELECT count(*) AS deliverClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =3 AND getTime <= '"+endDate+"') g, ");
                sql.append(" (SELECT count(*) AS deviveClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =4 AND getTime <= '"+endDate+"') h, ");
                sql.append(" (SELECT count(*) AS trainingClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =5 AND getTime <= '"+endDate+"') i ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] obj = (Object[]) objectList.get(0);
                ProjectSummaryBO projectSummaryBO = new ProjectSummaryBO();
                projectSummaryBO.setProject(project);
                projectSummaryBO.setProblemTotalNum(Integer.parseInt(String.valueOf(obj[0])));
                projectSummaryBO.setFinishNum(Integer.parseInt(String.valueOf(obj[1])));
                projectSummaryBO.setUnFinishNum(Integer.parseInt(String.valueOf(obj[2])));
                projectSummaryBO.setDispatchingClassNum(Integer.parseInt(String.valueOf(obj[3])));
                projectSummaryBO.setPersonnelClassNum(Integer.parseInt(String.valueOf(obj[4])));
                projectSummaryBO.setProgressClassNum(Integer.parseInt(String.valueOf(obj[5])));
                projectSummaryBO.setDeliverClassNum(Integer.parseInt(String.valueOf(obj[6])));
                projectSummaryBO.setDeviveClassNum(Integer.parseInt(String.valueOf(obj[7])));
                projectSummaryBO.setTrainingClassNum(Integer.parseInt(String.valueOf(obj[8])));
                projectSummaryBOList.add(projectSummaryBO);
            }
        }
        return projectSummaryBOList;
    }

    /**
     * 汇总总方法
     * @param startDate
     * @param endDate
     * @return
     * @throws SerException
     */
    private List<ProjectSummaryBO> totalMethod(String startDate , String endDate)throws SerException{
        List<ProjectSummaryBO> projectSummaryBOList = new ArrayList<>();
        List<String> projects = findAllProject();
        if(projects!=null && projects.size()>0){
            for (String project : projects){
                StringBuffer sql = new StringBuffer();
                sql.append("SELECT * FROM ( ");
                sql.append("(SELECT count(*) AS problemTotalNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') a, ");
                sql.append("(SELECT count(*) AS finishNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  is_closeLoop=1 AND getTime  BETWEEN '"+startDate+"' AND '"+endDate+"')  b, ");
                sql.append("(SELECT count(*) AS unFinishNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  is_closeLoop=0 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') c, ");
                sql.append("(SELECT count(*) AS dispatchingClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =0 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') d, ");
                sql.append("(SELECT count(*) AS personnelClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =1 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') e, ");
                sql.append("(SELECT count(*) AS progressClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =2 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') f, ");
                sql.append("(SELECT count(*) AS deliverClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =3 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') g, ");
                sql.append("(SELECT count(*) AS deviveClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =4 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') h, ");
                sql.append("(SELECT count(*) AS trainingClassNum FROM projectissuehandle_projectproblemacc WHERE subordinateDepartment = '"+project+"' AND  typeProblem =5 AND getTime BETWEEN '"+startDate+"' AND '"+endDate+"') i ");
                sql.append(" )");
                List<Object> objectList = super.findBySql(sql.toString());
                Object[] obj = (Object[]) objectList.get(0);
                ProjectSummaryBO projectSummaryBO = new ProjectSummaryBO();
                projectSummaryBO.setProject(project);
                projectSummaryBO.setProblemTotalNum(Integer.parseInt(String.valueOf(obj[0])));
                projectSummaryBO.setFinishNum(Integer.parseInt(String.valueOf(obj[1])));
                projectSummaryBO.setUnFinishNum(Integer.parseInt(String.valueOf(obj[2])));
                projectSummaryBO.setDispatchingClassNum(Integer.parseInt(String.valueOf(obj[3])));
                projectSummaryBO.setPersonnelClassNum(Integer.parseInt(String.valueOf(obj[4])));
                projectSummaryBO.setProgressClassNum(Integer.parseInt(String.valueOf(obj[5])));
                projectSummaryBO.setDeliverClassNum(Integer.parseInt(String.valueOf(obj[6])));
                projectSummaryBO.setDeviveClassNum(Integer.parseInt(String.valueOf(obj[7])));
                projectSummaryBO.setTrainingClassNum(Integer.parseInt(String.valueOf(obj[8])));
                projectSummaryBOList.add(projectSummaryBO);
            }
        }
        return projectSummaryBOList;
    }

    @Override
    public OptionBO figureShowDay(String summDate) throws SerException {
        List<ProjectSummaryBO> projectSummaryBOList = summaDay(summDate);
        String text_1 = "项目问题受理与处理日汇总图形展示";
        return figureShow(projectSummaryBOList,text_1);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        List<ProjectSummaryBO> projectSummaryBOList = summaWeek(year,month,week);
        String text_1 = "项目问题受理与处理周汇总图形展示";
        return figureShow(projectSummaryBOList,text_1);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        List<ProjectSummaryBO> projectSummaryBOList = summaMonth(year,month);
        String text_1 = "项目问题受理与处理月汇总图形展示";
        return figureShow(projectSummaryBOList,text_1);
    }

    @Override
    public OptionBO figureShowQuarter(Integer year, Integer quarter) throws SerException {
        List<ProjectSummaryBO> projectSummaryBOList = summaMonth(year,quarter);
        String text_1 = "项目问题受理与处理季度汇总图形展示";
        return figureShow(projectSummaryBOList,text_1);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        List<ProjectSummaryBO> projectSummaryBOList = summaTotal(endDate);
        String text_1 = "项目问题受理与处理累计汇总图形展示";
        return figureShow(projectSummaryBOList,text_1);
    }

    /**
     * 图形展示总方法
     * @param projectSummaryBOList
     * @param text_1
     * @return
     * @throws SerException
     */
    public OptionBO figureShow(List<ProjectSummaryBO> projectSummaryBOList, String text_1) throws SerException {
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);

        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"问题总规模", "已完成",
                "未完成", "人员类", "设备类",
                "派工类", "交付类", "培训类", "进度类"};
        legendBO.setData(text_2);
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();

        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();//1

        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);

        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        //        tooltipBO.setTrigger("axis");

        List<SeriesBO> seriesBOList = new ArrayList<>();

        if (projectSummaryBOList != null && projectSummaryBOList.size() > 0) {
            List<Integer> problemTotalNums = new ArrayList<>();
            List<Integer> finishNums = new ArrayList<>();
            List<Integer> unFinishNums = new ArrayList<>();
            List<Integer> personnelClassNums = new ArrayList<>();
            List<Integer> deviveClassNums = new ArrayList<>();
            List<Integer> dispatchingClassNums = new ArrayList<>();
            List<Integer> deliverClassNums = new ArrayList<>();
            List<Integer> trainingClassNums = new ArrayList<>();
            List<Integer> progressClassNums = new ArrayList<>();

            for (ProjectSummaryBO projectSummaryBO : projectSummaryBOList) {
                text_list_3.add(projectSummaryBO.getProject());

                //柱状图数据
                problemTotalNums.add(projectSummaryBO.getProblemTotalNum());
                finishNums.add(projectSummaryBO.getFinishNum());
                unFinishNums.add(projectSummaryBO.getUnFinishNum());
                personnelClassNums.add(projectSummaryBO.getPersonnelClassNum());
                deviveClassNums.add(projectSummaryBO.getDeviveClassNum());
                dispatchingClassNums.add(projectSummaryBO.getDispatchingClassNum());
                deliverClassNums.add(projectSummaryBO.getDeliverClassNum());
                trainingClassNums.add(projectSummaryBO.getTrainingClassNum());
                progressClassNums.add(projectSummaryBO.getProgressClassNum());
            }
            String[] dataNames = new String[]{"问题总规模", "已完成",
                    "未完成", "人员类", "设备类",
                    "派工类", "交付类", "培训类", "进度类"};
            List<List<Integer>> datas = new ArrayList<>();
            datas.add(problemTotalNums);
            datas.add(finishNums);
            datas.add(unFinishNums);
            datas.add(personnelClassNums);
            datas.add(deviveClassNums);
            datas.add(dispatchingClassNums);
            datas.add(deliverClassNums);
            datas.add(trainingClassNums);
            datas.add(progressClassNums);
            for (int i = 0; i < datas.size(); i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(dataNames[i]);
                seriesBO.setType("bar");
                Integer[] nums = new Integer[]{datas.get(i).size()};
                nums = datas.get(i).toArray(nums);
                seriesBO.setData(nums);
                seriesBOList.add(seriesBO);
            }

        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOList.size()];
        text_4 = seriesBOList.toArray(text_4);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setLegend(legendBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }
}