package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.entity.InvolvedProcessingTask;
import com.bjike.goddess.projectissuehandle.entity.ProblemAccept;
import com.bjike.goddess.projectissuehandle.entity.ProblemHandlingResult;
import com.bjike.goddess.projectissuehandle.enums.*;
import com.bjike.goddess.projectissuehandle.excel.ProblemAcceptExport;
import com.bjike.goddess.projectissuehandle.excel.SonPermissionObject;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目执行中的问题受理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:16 ]
 * @Description: [ 项目执行中的问题受理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProblemAcceptSerImpl extends ServiceImpl<ProblemAccept, ProblemAcceptDTO> implements ProblemAcceptSer {
    @Autowired
    private ProPermissionSer proPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private ProblemHandlingResultSer problemHandlingResultSer;
    @Autowired
    private InvolvedProcessingTaskSer involvedProcessingTaskSer;
    @Autowired
    private CollectEmailSer collectEmailSer;
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
     * 核对添加修改删除审核权限（岗位级别）
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
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
        obj.setName("problemaccept");
        obj.setDescribesion("项目执行中的问题受理");
        if (flagSeeProblem || flagAddProblem) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeResult = problemHandlingResultSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("problemhandlingresult");
        obj.setDescribesion("确认问题处理结果");
        if (flagSeeResult) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTask = involvedProcessingTaskSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("involvedprocessingtask");
        obj.setDescribesion("参与处理人员的任务分配");
        if (flagSeeTask) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("项目问题处理和受理邮件发送");
        if (flagSeeEmail) {
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
    public Long countProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        problemAcceptDTO.getSorts().add("createTime=desc");
        Long counts = super.count(problemAcceptDTO);
        return counts;
    }

    @Override
    public ProblemAcceptBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ProblemAccept problemAccept = super.findById(id);
        return BeanTransform.copyProperties(problemAccept, ProblemAcceptBO.class, true);
    }

    @Override
    public List<ProblemAcceptBO> findListProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        Boolean permission = proPermissionSer.getProPermission("1");
        if (!permission) {
            throw new SerException("您的帐号没有权限");
        }
        List<ProblemAccept> problemAccepts = super.findByCis(problemAcceptDTO, true);
        List<ProblemAcceptBO> problemAcceptBOS = BeanTransform.copyProperties(problemAccepts, ProblemAcceptBO.class);
        return problemAcceptBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemAcceptBO insertProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        Boolean permission = proPermissionSer.getProPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }

        ProblemAccept problemAccept = BeanTransform.copyProperties(problemAcceptTO, ProblemAccept.class, true);
        //String employeeNumber = userSer.findByMaxField("employeeNumber", User.class);
//        ProblemAcceptDTO dto = new ProblemAcceptDTO();
//        dto.getConditions().add(Restrict.eq("projectNum",ProblemAccept.class));
        String a = super.findByMaxField("projectNum",ProblemAccept.class);
        //项目问题编号
        problemAccept.setProjectNum(generateNum(a));
       // generateProjectNum(problemAcceptTO);
        problemAccept.setCreateTime(LocalDateTime.now());
        super.save(problemAccept);
//        ProblemHandlingResult problemHandlingResult = new ProblemHandlingResult();
//        problemHandlingResult.setProjectNum(problemAccept.getProjectNum());
//        problemHandlingResult.setYear(problemAccept.getYear());
//        problemHandlingResult.setArea(problemAccept.getArea());
//        problemHandlingResult.setExternalContractProjectName(problemAccept.getExternalContractProjectName());
//        problemHandlingResult.setInternalProjectName(problemAccept.getInternalProjectName());
//        problemHandlingResult.setProjectType(problemAccept.getProjectType());
//        problemHandlingResultSer.save(problemHandlingResult);
//        InvolvedProcessingTask involvedProcessingTask = new InvolvedProcessingTask();
//        involvedProcessingTask.setProjectNum(problemAccept.getProjectNum());
//        involvedProcessingTask.setInternalProjectName(problemAccept.getInternalProjectName());
//        involvedProcessingTaskSer.save(involvedProcessingTask);
        return BeanTransform.copyProperties(problemAccept, ProblemAcceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProblemAcceptBO editProblemAccept(ProblemAcceptTO problemAcceptTO) throws SerException {
        Boolean permission = proPermissionSer.getProPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        if (StringUtils.isBlank(problemAcceptTO.getId())) {
            throw new SerException("id不能为空");
        }
        ProblemAccept problemAccept = super.findById(problemAcceptTO.getId());
        BeanTransform.copyProperties(problemAcceptTO, problemAccept, true);
        problemAccept.setModifyTime(LocalDateTime.now());
        super.update(problemAccept);
        return BeanTransform.copyProperties(problemAcceptTO, ProblemAcceptBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProblemAccept(String id) throws SerException {
        Boolean permission = proPermissionSer.getProPermission("1");
        if (!permission) {
            throw new SerException("您不是商务人员，没有权限");
        }
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    //生成项目问题编号  格式 EQ-00001 依次递增
//    public ProblemAcceptTO generateProjectNum(ProblemAcceptTO problemAcceptTO) throws SerException {
//        //项目问题编号
//        String projectNum = getProject();
//
//        //设置合同编号  格式：  EQ-00001
//        StringBuffer tempCode = new StringBuffer();
//        tempCode.append("EQ-").append(projectNum);
//        problemAcceptTO.setProjectNum(tempCode.toString());
//
//        return problemAcceptTO;
//    }
//
//    public String getProject() throws SerException {
//        List<ProblemAccept> problemAccept = new ArrayList<>();
//        //for (int i = 0 ; i < problemAccept.size(); i++){
//        if (problemAccept != null) {
//
//            if (problemAccept.size() >= 99) {
//                return (problemAccept.size() + 1) + "";
//            }
//            if (problemAccept.size() >= 9 && problemAccept.size() < 99) {
//                return "000" + (problemAccept.size() + 1);
//            } else {
//                return "0000" + (problemAccept.size() + 1);
//            }
//        } else {
//            return "00001";
//        }
//        // }
//    }

    private static final String PRO_NUMBER = "EQ-"; // 员工编号格式
    private static final Integer PRO_NUMBER_LENGTH = 8; // 员工编号长度
    private static final String START_NUMBER = "00000"; // 编号开始
    private static final String ZERO_NUMBER = "000000"; // 员工编号0位数
    /**
     * 生成下一个编号
     *
     * @param num 最大员工编号
     */
    public static synchronized String generateNum(String num) throws SerException {
        if (StringUtils.isNotBlank(num)) {
            Integer number = Integer.parseInt(StringUtils.substringAfter(num, PRO_NUMBER)) + 1;
            Integer length = PRO_NUMBER_LENGTH - (String.valueOf(number).length());
            if (length > 0) {
                num = PRO_NUMBER + ZERO_NUMBER.substring(0, length - PRO_NUMBER.length());
            } else if (0 == length) {
                num = PRO_NUMBER + number;
            } else {
                throw new SerException("员工编号超出长度:" + length);
            }
            return num + number;
        } else {
            return generateNum(PRO_NUMBER + START_NUMBER); //假如为空,则从第一个开始EQ-00001
        }

    }



    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<ProblemAcceptBO> searchProblemAccept(ProblemAcceptDTO problemAcceptDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(problemAcceptDTO.getInternalProjectName())) {
            problemAcceptDTO.getConditions().add(Restrict.like("internalProjectName", problemAcceptDTO.getInternalProjectName()));
        }
        /**
         * 工程类型
         */
        if (StringUtils.isNotBlank(problemAcceptDTO.getProjectType())) {
            problemAcceptDTO.getConditions().add(Restrict.like("projectType", problemAcceptDTO.getProjectType()));
        }
        List<ProblemAccept> problemAccepts = super.findByCis(problemAcceptDTO, true);
        List<ProblemAcceptBO> problemAcceptBOS = BeanTransform.copyProperties(problemAccepts, ProblemAcceptBO.class);
        return problemAcceptBOS;
    }

    @Override
    public String degree(ProblemProcessingTime problemProcessingTime, AffectedDepartment affectedDepartment) throws SerException {
        String problemEmergencyDegree = "";
//        ProblemAcceptDTO dto = new ProblemAcceptDTO();
//        dto.getConditions().add(Restrict.eq("problemProcessingTime",problemProcessingTime));
//        dto.getConditions().add(Restrict.eq("affectedDepartment",affectedDepartment));
//
//        List<ProblemAccept> list = super.findByCis(dto);
//        if(list != null) {
        if (ProblemProcessingTime.FOURHOURS.equals(problemProcessingTime) && AffectedDepartment.ONEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "中级";
        }
        if (ProblemProcessingTime.FOURHOURS.equals(problemProcessingTime) && AffectedDepartment.TWOTOTHREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "紧急";
        }
        if (ProblemProcessingTime.FOURHOURS.equals(problemProcessingTime) && AffectedDepartment.THREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "紧急";
        }
        if (ProblemProcessingTime.FOURTOTWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.ONEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "初级";
        }
        if (ProblemProcessingTime.FOURTOTWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.TWOTOTHREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "中级";
        }
        if (ProblemProcessingTime.FOURTOTWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.THREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "紧急";
        }
        if (ProblemProcessingTime.TWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.ONEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "初级";
        }
        if (ProblemProcessingTime.TWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.TWOTOTHREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "初级";
        }
        if (ProblemProcessingTime.TWENTYFOURHOURS.equals(problemProcessingTime) && AffectedDepartment.THREEDEPARTMENT.equals(affectedDepartment)) {
            problemEmergencyDegree = "中级";
        }
//        }
        return problemEmergencyDegree;
    }
    @Override
    public ProblemAcceptBO getProjectNum(String projectNum) throws SerException {
        ProblemAccept problemAccept = new ProblemAccept();
        if(StringUtils.isNotBlank(projectNum)){
            ProblemAcceptDTO dto = new ProblemAcceptDTO();
            dto.getConditions().add(Restrict.eq("projectNum",projectNum));
            problemAccept = super.findOne(dto);
        }
        ProblemAcceptBO problemAcceptBO = BeanTransform.copyProperties(problemAccept,ProblemAcceptBO.class);
        return problemAcceptBO;
    }
    @Override
    public List<String> getProjectNum() throws SerException {
        String[] fields = new String[]{"projectNum"};
        List<ProblemAcceptBO> problemAcceptBOS = super.findBySql("select projectNum from projectissuehandle_problemaccept group by projectNum order by projectNum asc ", ProblemAcceptBO.class, fields);

        List<String> projectList = problemAcceptBOS.stream().map(ProblemAcceptBO::getProjectNum)
                .filter(projectNum -> (projectNum != null || !"".equals(projectNum.trim()))).distinct().collect(Collectors.toList());


        return projectList;
    }

    @Override
    public byte[] exportExcel(ProblemAcceptDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getInternalProjectName())) {
            dto.getConditions().add(Restrict.eq("internalProjectName", dto.getInternalProjectName()));
        }
        if (StringUtils.isNotBlank(dto.getProjectType())) {
            dto.getConditions().add(Restrict.eq("projectType", dto.getProjectType()));
        }
        List<ProblemAccept> list = super.findByCis(dto);

        List<ProblemAcceptExport> problemAcceptExports = new ArrayList<>();
        list.stream().forEach(str -> {
            ProblemAcceptExport excel = BeanTransform.copyProperties(str, ProblemAcceptExport.class, "noticeWay", "problemTypes", "problemProcessingTime","affectedDepartment");
            excel.setNoticeWay(NoticeWay.exportStrConvert(str.getNoticeWay()));
            excel.setProblemTypes(ProblemTypes.exportStrConvert(str.getProblemTypes()));
            excel.setProblemProcessingTime(ProblemProcessingTime.exportStrConvert(str.getProblemProcessingTime()));
            excel.setAffectedDepartment(AffectedDepartment.exportStrConvert(str.getAffectedDepartment()));
            problemAcceptExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(problemAcceptExports, excel);
        return bytes;

    }

}