package com.bjike.goddess.staffentry.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.assistance.api.ComputerSubsidiesAPI;
import com.bjike.goddess.assistance.api.SenioritySubsidiesAPI;
import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.to.ComputerSubsidiesAddTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.*;
import com.bjike.goddess.staffentry.entity.*;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.excel.EntryRegisterExpTemplate;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 入职登记业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:50]
 * @Description: [入职登记业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class EntryRegisterSerImpl extends ServiceImpl<EntryRegister, EntryRegisterDTO> implements EntryRegisterSer {

    private static Logger log = Logger.getLogger(EntryRegisterSerImpl.class);
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FamilyMemberSer familyMemberSer;
    @Autowired
    private StudyExperienceSer studyExperienceSer;
    @Autowired
    private WorkExperienceSer workExperienceSer;

    @Autowired
    private CredentialSer credentialSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SenioritySubsidiesAPI senioritySubsidiesAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ComputerSubsidiesAPI computerSubsidiesAPI;

    /**
     * 检测部门
     *
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkDepartIdentity(String idFlag) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        Boolean flag = true;
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(idFlag);
//            if( !flag){
//                throw new SerException("你不是相应部门的人员，不能进行操作");
//            }
        } else {
            flag = true;
        }
        return flag;
    }

    //所有人都有权限
    private Boolean checkAllTrue() throws SerException {
        return true;

    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkDepartIdentity("2");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkDepartIdentity("7");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAllTrue = checkAllTrue();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAdd || flagAllTrue) {
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
                flag = true;
                break;
            case ADD:
                flag = true;
                break;
            case EDIT:
                flag = true;
                break;
            case DELETE:
                flag = checkDepartIdentity("7");
                break;
            case COLLECT:
                flag = checkDepartIdentity("2");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        searchCondi(entryRegisterDTO);
        String userToken = RpcTransmit.getUserToken();
        Long count = null;
        if (checkDepartIdentity("2")) {
            RpcTransmit.transmitUserToken(userToken);
            count = super.count(entryRegisterDTO);
        } else {
            RpcTransmit.transmitUserToken(userToken);
            UserBO userBO = userAPI.currentUser();
            String userName = userBO.getUsername();
            entryRegisterDTO.getConditions().add(Restrict.eq("username", userName));
            count = super.count(entryRegisterDTO);
        }
        return count;
    }

    @Override
    public EntryRegisterBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        EntryRegister temp = super.findById(id);
        return BeanTransform.copyProperties(temp, EntryRegisterBO.class);
    }

    @Override
    public List<EntryRegisterBO> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        searchCondi(entryRegisterDTO);
        log.info("打印日志:" + JSON.toJSONString(entryRegisterDTO));
        if (checkDepartIdentity("2")) {
            RpcTransmit.transmitUserToken(userToken);
            entryRegisterDTO.getSorts().add("createTime=desc");
        } else {
            log.info("打印日志2:" + JSON.toJSONString(entryRegisterDTO));
            RpcTransmit.transmitUserToken(userToken);
            UserBO userBO = userAPI.currentUser();
            String userName = userBO.getUsername();
            entryRegisterDTO.getConditions().add(Restrict.eq("username", userName));
            log.info("打印日志3:" + userName + " 发你的:" + JSON.toJSONString(entryRegisterDTO));
        }
        List<EntryRegister> entryRegisters = super.findByPage(entryRegisterDTO);
        List<EntryRegisterBO> entryRegisterBOS = BeanTransform.copyProperties(entryRegisters, EntryRegisterBO.class);
        if (entryRegisterBOS != null && entryRegisterBOS.size() > 0) {
            for (EntryRegisterBO entryRegisterBO : entryRegisterBOS) {
                RpcTransmit.transmitUserToken(userToken);
                StaffStatus staffStatus = positionDetailUserAPI.statusByName(entryRegisterBO.getUsername());//查看员工状态
                if (staffStatus == null) {
                    entryRegisterBO.setStaffStatus("未获取到数据");
                } else
                    entryRegisterBO.setStaffStatus(staffStatus.toString());
            }
        }
        return entryRegisterBOS;
    }

    public void searchCondi(EntryRegisterDTO entryRegisterDTO) throws SerException {
        if (StringUtils.isNotBlank(entryRegisterDTO.getUsername())) {
            entryRegisterDTO.getConditions().add(Restrict.eq("username", entryRegisterDTO.getUsername()));
        }
    }

    @Override
    public EntryRegisterBO getEntryRegisterDetail(String id) throws SerException {

        EntryRegister temp = super.findById(id);


        /**
         *  根据入职登记查询子表
         */
        List<FamilyMemberBO> familyMemberList = new ArrayList<>(1);
        List<StudyExperienceBO> studyExperienceList = new ArrayList<>(2);
        List<WorkExperienceBO> workExperienceList = new ArrayList<>(3);
        List<CredentialBO> credentialList = new ArrayList<>(4);

        //家庭
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();
        familyMemberDTO.getConditions().add(Restrict.eq("entryRegister.id", id));
        List<FamilyMember> family = familyMemberSer.findByCis(familyMemberDTO);
        familyMemberList = BeanTransform.copyProperties(family, FamilyMemberBO.class);
        //学习经历
        StudyExperienceDTO studyExperienceDTO = new StudyExperienceDTO();
        studyExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id", id));
        List<StudyExperience> study = studyExperienceSer.findByCis(studyExperienceDTO);
        studyExperienceList = BeanTransform.copyProperties(study, StudyExperienceBO.class);
        //工作经历
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        workExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id", id));
        List<WorkExperience> work = workExperienceSer.findByCis(workExperienceDTO);
        workExperienceList = BeanTransform.copyProperties(work, WorkExperienceBO.class);
        //证书情况
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.getConditions().add(Restrict.eq("entryRegister.id", id));
        List<Credential> credentials = credentialSer.findByCis(credentialDTO);
        credentialList = BeanTransform.copyProperties(credentials, CredentialBO.class);

        EntryRegisterBO bo = BeanTransform.copyProperties(temp, EntryRegisterBO.class);
        bo.setFamilyMemberBOList(familyMemberList);
        bo.setStudyExperienceBOList(studyExperienceList);
        bo.setWorkExperienceBOList(workExperienceList);
        bo.setCredentialBOList(credentialList);
        return bo;
    }

    @Override
    public EntryRegisterBO getByNumber(String number) throws SerException {
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.eq("empNumber", number));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if (null != entryRegisters && entryRegisters.size() > 0) {
            EntryRegister entity = entryRegisters.get(0);
            EntryRegisterBO bo = BeanTransform.copyProperties(entity, EntryRegisterBO.class, false);
            return bo;
        }
        return null;
    }

    @Override
    public List<EntryRegisterBO> list() throws SerException {
        List<EntryRegister> entryRegisters = super.findAll();
        return BeanTransform.copyProperties(entryRegisters, EntryRegisterBO.class, false);
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEntryRegister(String id) throws SerException {

        if (StringUtils.isBlank(id)) {
            throw new SerException("Id不能为空哦");
        }
        EntryRegister entryRegister = super.findById(id);
        /**
         * 删除子表中的数据
         */
        deleteSubTableRecords(id);

        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryRegisterBO editEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                             WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {

        /**
         * 编辑主表中的数据
         */
        if (StringUtils.isBlank(entryRegisterTO.getId())) {
            throw new SerException("Id不能为空哦");
        }
        EntryRegister temp = super.findById(entryRegisterTO.getId());
        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        BeanTransform.copyProperties(entryRegister, temp, true, "id", "createTime", "birthday", "graduationDate");
        temp.setBirthday(entryRegister.getBirthday());
        temp.setGraduationDate(entryRegister.getGraduationDate());
        try {
            temp.setModifyTime(LocalDateTime.now());
            super.update(temp);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        /**
         * 删除子表中的数据
         */
        deleteSubTableRecords(entryRegisterTO.getId());

        /**
         * 往子表中插入数据
         */
        entryRegister = super.findById(entryRegister.getId());
        insertSubTableRecords(entryRegister, familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);

        return BeanTransform.copyProperties(temp, EntryRegisterBO.class);
    }

    private void insertSubTableRecords(EntryRegister entryRegister, FamilyMemberTO familyMemberTO,
                                       StudyExperienceTO studyExperienceTO, WorkExperienceTO workExperienceTO,
                                       CredentialTO credentialTO) throws SerException {
        insertFamilyMember(entryRegister, familyMemberTO);
        insertStudyExperience(entryRegister, studyExperienceTO);
        insertWorkExperience(entryRegister, workExperienceTO);
        insertCredential(entryRegister, credentialTO);
    }

    private void deleteSubTableRecords(String entryId) throws SerException {
        /**
         * 删除家庭成员
         */
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();
        familyMemberDTO.getConditions().add(Restrict.eq("entryRegister.id", entryId));
        List<FamilyMember> family = familyMemberSer.findByCis(familyMemberDTO);
        familyMemberSer.remove(family);

        /**
         * 删除学习经历
         */
        StudyExperienceDTO studyExperienceDTO = new StudyExperienceDTO();
        studyExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id", entryId));
        List<StudyExperience> study = studyExperienceSer.findByCis(studyExperienceDTO);
        studyExperienceSer.remove(study);

        /**
         * 删除工作经历
         */
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        workExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id", entryId));
        List<WorkExperience> work = workExperienceSer.findByCis(workExperienceDTO);
        workExperienceSer.remove(work);

        /**
         * 删除证书情况
         */
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.getConditions().add(Restrict.eq("entryRegister.id", entryId));
        List<Credential> credentials = credentialSer.findByCis(credentialDTO);
        credentialSer.remove(credentials);
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                               WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {

        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        entryRegister.setUpdateTime(LocalDate.now());
        if (StringUtils.isBlank(entryRegisterTO.getEmpNumber())) {
            throw new SerException("员工编号不能为空");
        }
        try {
            entryRegister = super.save(entryRegister);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        /**
         * 插入家庭成员
         */
        insertFamilyMember(entryRegister, familyMemberTO);
        /**
         * 插入学习经历
         */
        insertStudyExperience(entryRegister, studyExperienceTO);
        /**
         * 插入工作经历
         */
        insertWorkExperience(entryRegister, workExperienceTO);
        /**
         * 插入证书情况
         */
        insertCredential(entryRegister, credentialTO);

        //TODO 添加转正人员信息lijuntao

        //添加公司补助中的工龄补助lijuntao
        SenioritySubsidiesTO senioritySubsidiesTO = new SenioritySubsidiesTO();
        senioritySubsidiesTO.setArea(entryRegister.getArea());
        senioritySubsidiesTO.setName(entryRegister.getUsername());
        senioritySubsidiesTO.setEmpNo(entryRegister.getEmpNumber());
        senioritySubsidiesTO.setDepartment(entryRegister.getDepartment());
        senioritySubsidiesTO.setJobs(entryRegister.getPosition());
        senioritySubsidiesTO.setEntryDate(entryRegister.getInductionDate().toString());
        senioritySubsidiesTO.setStartIssueDate(entryRegister.getInductionDate().plusMonths(13).toString());
        senioritySubsidiesTO.setSubsidiesStatus(SubsidiesStatus.NOSUBSIDIES);
//        StaffStatus status = positionDetailUserAPI.statusByName(entryRegister.getUsername());
//        senioritySubsidiesTO.setStaffStatus(status);
        senioritySubsidiesAPI.saveSen(senioritySubsidiesTO);
        //添加电脑补助lijuntao
        ComputerSubsidiesAddTO computerSubsidiesAddTO = new ComputerSubsidiesAddTO();
        computerSubsidiesAddTO.setArea(entryRegister.getArea());
        computerSubsidiesAddTO.setDepartment(entryRegister.getDepartment());
        computerSubsidiesAddTO.setName(entryRegister.getUsername());
        computerSubsidiesAddTO.setEntryDate(entryRegister.getInductionDate().toString());
        computerSubsidiesAddTO.setSubsidiesStatus(SubsidiesStatus.NOSUBSIDIES);
//        StaffStatus status1 = positionDetailUserAPI.statusByName(entryRegister.getUsername());
//        computerSubsidiesAddTO.setStaffStatus(status1);
        computerSubsidiesAPI.saveComputer(computerSubsidiesAddTO);


        EntryRegisterBO bo = new EntryRegisterBO();
        BeanUtils.copyProperties(entryRegister, bo);
        return bo;
    }

    private void insertFamilyMember(EntryRegister entryRegister, FamilyMemberTO familyMemberTO) throws SerException {

        List<FamilyMember> familyMembers = new ArrayList<>(0);
        if (null != familyMemberTO && null != familyMemberTO.getTitles()) {
            int countTitle = familyMemberTO.getTitles().size();
            for (int i = 0; i < countTitle; i++) {
                FamilyMember temp = new FamilyMember();
                temp.setTitle(familyMemberTO.getTitles().get(i));
                temp.setName(null != familyMemberTO.getNames() ? familyMemberTO.getNames().get(i) : "");
                temp.setAge(null != familyMemberTO.getAges() ? (null == familyMemberTO.getAges().get(i) ? 0 : familyMemberTO.getAges().get(i)) : 0);
                temp.setUnit(null != familyMemberTO.getUnits() ? familyMemberTO.getUnits().get(i) : "");
                temp.setPosition(null != familyMemberTO.getPositions() ? familyMemberTO.getPositions().get(i) : "");
                temp.setPhone(null != familyMemberTO.getPhones() ? familyMemberTO.getPhones().get(i) : "");
                temp.setEntryRegister(entryRegister);

                familyMembers.add(temp);
            }
            familyMemberSer.save(familyMembers);
        }

    }


    private void insertStudyExperience(EntryRegister entryRegister, StudyExperienceTO studyExperienceTO) throws SerException {

        List<StudyExperience> studyExperienceTOS = new ArrayList<>(0);
        if (null != studyExperienceTO && null != studyExperienceTO.getStudyStartTimes()) {
            int countStartTime = studyExperienceTO.getStudyStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                StudyExperience temp = new StudyExperience();
                temp.setStartTime(StringUtils.isBlank(studyExperienceTO.getStudyStartTimes().get(i)) ? null : LocalDate.parse(studyExperienceTO.getStudyStartTimes().get(i), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                temp.setEndTime(null == studyExperienceTO.getStudyEndTimes() ? null : (StringUtils.isBlank(studyExperienceTO.getStudyEndTimes().get(i)) ? null : LocalDate.parse(studyExperienceTO.getStudyEndTimes().get(i), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                temp.setSchool(null == studyExperienceTO.getSchools() ? "" : studyExperienceTO.getSchools().get(i));
                temp.setCertificate(null == studyExperienceTO.getCertificates() ? "" : studyExperienceTO.getCertificates().get(i));
                temp.setEntryRegister(entryRegister);

                studyExperienceTOS.add(temp);
            }
            studyExperienceSer.save(studyExperienceTOS);
        }

    }

    private void insertWorkExperience(EntryRegister entryRegister, WorkExperienceTO workExperienceTO) throws SerException {

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<WorkExperience> workExperienceTOS = new ArrayList<>(0);
        if (null != workExperienceTO && null != workExperienceTO.getWorkStartTimes()) {
            int countStartTime = workExperienceTO.getWorkStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                WorkExperience temp = new WorkExperience();
                temp.setStartTime(StringUtils.isBlank(workExperienceTO.getWorkStartTimes().get(i)) ? null : LocalDate.parse(workExperienceTO.getWorkStartTimes().get(i), formater));
                temp.setEndTime(null == workExperienceTO.getWorkEndTimes() ? null : (StringUtils.isBlank(workExperienceTO.getWorkEndTimes().get(i)) ? null : LocalDate.parse(workExperienceTO.getWorkEndTimes().get(i), formater)));
                temp.setFirm(null == workExperienceTO.getFirms() ? "" : workExperienceTO.getFirms().get(i));
                temp.setJobDescription(null == workExperienceTO.getJobDescriptions() ? "" : workExperienceTO.getJobDescriptions().get(i));
                temp.setEntryRegister(entryRegister);

                workExperienceTOS.add(temp);
            }
            workExperienceSer.save(workExperienceTOS);
        }

    }

    private void insertCredential(EntryRegister entryRegister, CredentialTO credentialTO) throws SerException {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Credential> credentialTOS = new ArrayList<>(0);
        if (null != credentialTO && null != credentialTO.getNameses()) {
            int countName = credentialTO.getNameses().size();
            for (int i = 0; i < countName; i++) {
                Credential temp = new Credential();
                temp.setName(credentialTO.getNameses().get(i));

                temp.setObtainTime(null == credentialTO.getObtainTimes() ? null : (StringUtils.isBlank(credentialTO.getObtainTimes().get(i)) ? null : LocalDate.parse(credentialTO.getObtainTimes().get(i), formater)));
                temp.setEntryRegister(entryRegister);

                credentialTOS.add(temp);
            }
            credentialSer.save(credentialTOS);
        }

    }

    @Override
    public List<String> findQQ() throws SerException {
        List<EntryRegister> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EntryRegister model : list) {
            String qq = model.getQq();
            if (StringUtils.isNotBlank(model.getQq())) {
                set.add(qq);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findSchoolTag() throws SerException {
        List<EntryRegister> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EntryRegister model : list) {
            String schoolTag = model.getSchoolTag();
            if (StringUtils.isNotBlank(model.getSchoolTag())) {
                set.add(schoolTag);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findGraduationDate() throws SerException {
        List<EntryRegister> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EntryRegister model : list) {
            String date = model.getGraduationDate().toString();
            if (model.getGraduationDate() != null) {
                set.add(date);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public String findEmpNum(String name) throws SerException {
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.eq("username", name));
        List<EntryRegister> list = super.findByCis(entryRegisterDTO);
        String empNum = "";
        if (list != null && list.size() > 0) {
            EntryRegister entryRegister = list.get(0);
            empNum = entryRegister.getEmpNumber();
        }
        return empNum;
    }

    public EntryOptionBO getEntryOptionByEmpNum(String empNumer) throws SerException {
        EntryOptionBO entryOptionBO = new EntryOptionBO();

        EntryRegisterDTO dto = new EntryRegisterDTO();
        dto.getConditions().add(Restrict.eq("empNumber", empNumer));
        EntryRegister entryRegister = super.findOne(dto);
        if (entryRegister != null) {
            entryOptionBO.setName(entryRegister.getUsername());
            entryOptionBO.setEntryTime(entryRegister.getInductionDate().toString());
            entryOptionBO.setProfession(entryRegister.getProfession());
            entryOptionBO.setEducation(entryRegister.getEducation());
            entryOptionBO.setEmployeeID(empNumer);
            entryOptionBO.setDepartment(entryRegister.getDepartment());
            entryOptionBO.setArea(entryRegister.getArea());
            entryOptionBO.setPosition(entryOptionBO.getPosition());
        }
        return entryOptionBO;
    }

    @Override
    public String getGender(String username) throws SerException {
        EntryRegisterDTO dto = new EntryRegisterDTO();
        dto.getConditions().add(Restrict.eq("username", username));
        List<EntryRegister> entryRegisters = super.findByCis(dto);
        if (null != entryRegisters && entryRegisters.size() > 0) {
            if (entryRegisters.get(0).getGender() == 1) {
                return "男";
            } else {
                return "女";
            }
        }
        return null;
    }

    @Override
    public List<EntryRegisterBO> map(EntryRegisterDTO dto) throws SerException {
        List<EntryRegister> entryRegisters = super.findByCis(dto);
        return BeanTransform.copyProperties(entryRegisters, EntryRegisterBO.class, false);
    }

    @Override
    public List<EntryRegisterBO> getEntryRegisterByName(String name) throws SerException {
        EntryRegisterDTO dto = new EntryRegisterDTO();
        dto.getConditions().add(Restrict.eq("username", name));
        List<EntryRegister> entryRegisters = super.findByCis(dto);
        return BeanTransform.copyProperties(entryRegisters, EntryRegisterBO.class, false);
    }

    @Override
    public String getEntryTime(String username) throws SerException {
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.eq("username", username));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if (null != entryRegisters && entryRegisters.size() > 0) {
            LocalDate time = entryRegisters.stream().map(EntryRegister::getInductionDate).distinct().collect(Collectors.toList()).get(0);
            return DateUtil.dateToString(time);
        }
        return null;
    }

    public List<String> findWorkingEmpNum() throws SerException {
        List<String> empNums = new ArrayList<>();
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.ne("staffStatus", StaffStatus.HAVELEAVE));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        for (EntryRegister entryRegister : entryRegisters) {
            empNums.add(entryRegister.getEmpNumber());
        }
        return empNums;
    }

    @Override
    public List<EntryOptionBO> findEmpDate() throws SerException {
        List<EntryRegister> entryRegisters = super.findAll();
        List<EntryOptionBO> entryOptionBOS = new ArrayList<>();
        if (entryRegisters != null && entryRegisters.size() > 0) {
            for (EntryRegister entryRegister : entryRegisters) {
                EntryOptionBO entryOptionBO = new EntryOptionBO();
                entryOptionBO.setArea(entryRegister.getArea());
                entryOptionBO.setName(entryRegister.getUsername());
                entryOptionBO.setEmployeeID(entryRegister.getEmpNumber());
                entryOptionBO.setDepartment(entryRegister.getDepartment());
                entryOptionBO.setEntryTime(entryRegister.getInductionDate().toString());
                entryOptionBOS.add(entryOptionBO);
            }
        }
        return entryOptionBOS;
    }

    @Override
    public Integer findNumByEntryDate(String[] date, String area, String dep) throws SerException {
        Integer num = 0;
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.between("inductionDate", date));
        entryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        entryRegisterDTO.getConditions().add(Restrict.eq("department", dep));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if (entryRegisters != null && entryRegisters.size() > 0) {
            num = entryRegisters.size();
        }
        return num;
    }

    @Override
    public Integer findNumByEntryDate(String endDate, String area, String dep) throws SerException {
        Integer num = 0;
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.lt_eq("inductionDate", endDate));
        entryRegisterDTO.getConditions().add(Restrict.eq("area", area));
        entryRegisterDTO.getConditions().add(Restrict.eq("department", dep));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if (entryRegisters != null && entryRegisters.size() > 0) {
            num = entryRegisters.size();
        }
        return num;
    }

    @Override
    public List<UserNameSexBO> findSexByUserName(String[] userNames) throws SerException {
        List<UserNameSexBO> userNameSexBOList = new ArrayList<>();
        if (userNames != null && userNames.length > 0) {
            for (String userName : userNames) {
                EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
                entryRegisterDTO.getConditions().add(Restrict.eq("username", userName));
                EntryRegister entryRegister = super.findOne(entryRegisterDTO);
                if(entryRegister!=null){
                    UserNameSexBO userNameSexBO = new UserNameSexBO();
                    userNameSexBO.setUsername(entryRegister.getUsername());
                    userNameSexBO.setGender(entryRegister.getGender());
                    userNameSexBOList.add(userNameSexBO);
                }
            }
        }
        return userNameSexBOList;
    }

    @Override
    public Set<String> names() throws SerException {
        List<EntryRegister> list = super.findAll();
        return list.stream().map(entryRegister -> entryRegister.getUsername()).collect(Collectors.toSet());

    }

    @Override
    public byte[] templateExport() throws SerException {
        List<EntryRegisterExpTemplate> entryRegisterExpTemplateList = new ArrayList<>();
        EntryRegisterExpTemplate entryRegisterExpTemplate = new EntryRegisterExpTemplate();
        entryRegisterExpTemplate.setEmpNumber("test");
        entryRegisterExpTemplate.setUsername("test");
        entryRegisterExpTemplate.setGender(0);
        entryRegisterExpTemplate.setBirthday("2017/12/12");
        entryRegisterExpTemplate.setNation("test");
        entryRegisterExpTemplate.setMarriage("test");
        entryRegisterExpTemplate.setPoliticsStatus("test");
        entryRegisterExpTemplate.setNativePlace("test");
        entryRegisterExpTemplate.setStature("test");
        entryRegisterExpTemplate.setProfession("test");
        entryRegisterExpTemplate.setEducation("test");
        entryRegisterExpTemplate.setSchoolTag("test");
        entryRegisterExpTemplate.setGraduationDate("test");
        entryRegisterExpTemplate.setHealthStatus("test");
        entryRegisterExpTemplate.setQq("test");
        entryRegisterExpTemplate.setPhone("test");
        entryRegisterExpTemplate.setEmail("test");
        entryRegisterExpTemplate.setEmergencyContact("test");
        entryRegisterExpTemplate.setPhoneNumber("test");
        entryRegisterExpTemplate.setIdCard("test");
        entryRegisterExpTemplate.setRegisteredAddress("test");
        entryRegisterExpTemplate.setLocation("test");
        entryRegisterExpTemplate.setHobbies("test");
        entryRegisterExpTemplate.setArea("test");
        entryRegisterExpTemplate.setDepartment("test");
        entryRegisterExpTemplate.setPosition("test");
        entryRegisterExpTemplate.setInductionDate("test");
        entryRegisterExpTemplate.setTitle("test");
        entryRegisterExpTemplate.setName("test");
        entryRegisterExpTemplate.setAge(19);
        entryRegisterExpTemplate.setUnit("test");
        entryRegisterExpTemplate.setPositionf("test");
        entryRegisterExpTemplate.setPhonef("test");
        entryRegisterExpTemplate.setStartTime("2017/01/01");
        entryRegisterExpTemplate.setEndTime("2017/12/12");
        entryRegisterExpTemplate.setSchool("test");
        entryRegisterExpTemplate.setCertificate("test");
        entryRegisterExpTemplate.setWorkStartTime("2017/01/01");
        entryRegisterExpTemplate.setWorkEndTime("2017/12/12");
        entryRegisterExpTemplate.setFirm("test");
        entryRegisterExpTemplate.setJobDescription("test");
        entryRegisterExpTemplate.setName1("test");
        entryRegisterExpTemplate.setObtainTime("2017/12/12");
        entryRegisterExpTemplateList.add(entryRegisterExpTemplate);

        EntryRegisterExpTemplate entryRegisterExpTemplate2 = new EntryRegisterExpTemplate();
        entryRegisterExpTemplate2.setEmpNumber("test");
        entryRegisterExpTemplate2.setUsername("test");
        entryRegisterExpTemplate2.setGender(0);
        entryRegisterExpTemplate2.setBirthday("2017/12/12");
        entryRegisterExpTemplate2.setNation("test");
        entryRegisterExpTemplate2.setMarriage("test");
        entryRegisterExpTemplate2.setPoliticsStatus("test");
        entryRegisterExpTemplate2.setNativePlace("test");
        entryRegisterExpTemplate2.setStature("test");
        entryRegisterExpTemplate2.setProfession("test");
        entryRegisterExpTemplate2.setEducation("test");
        entryRegisterExpTemplate2.setSchoolTag("test");
        entryRegisterExpTemplate2.setGraduationDate("test");
        entryRegisterExpTemplate2.setHealthStatus("test");
        entryRegisterExpTemplate2.setQq("test");
        entryRegisterExpTemplate2.setPhone("test");
        entryRegisterExpTemplate2.setEmail("test");
        entryRegisterExpTemplate2.setEmergencyContact("test");
        entryRegisterExpTemplate2.setPhoneNumber("test");
        entryRegisterExpTemplate2.setIdCard("test");
        entryRegisterExpTemplate2.setRegisteredAddress("test");
        entryRegisterExpTemplate2.setLocation("test");
        entryRegisterExpTemplate2.setHobbies("test");
        entryRegisterExpTemplate2.setArea("test");
        entryRegisterExpTemplate2.setDepartment("test");
        entryRegisterExpTemplate2.setPosition("test");
        entryRegisterExpTemplate2.setInductionDate("test");
        entryRegisterExpTemplate2.setTitle("test");
        entryRegisterExpTemplate2.setName("test");
        entryRegisterExpTemplate2.setAge(19);
        entryRegisterExpTemplate2.setUnit("test");
        entryRegisterExpTemplate2.setPositionf("test");
        entryRegisterExpTemplate2.setPhonef("test");
        entryRegisterExpTemplate2.setStartTime("2017/01/01");
        entryRegisterExpTemplate2.setEndTime("2017/12/12");
        entryRegisterExpTemplate2.setSchool("test");
        entryRegisterExpTemplate2.setCertificate("test");
        entryRegisterExpTemplate2.setWorkStartTime("2017/01/01");
        entryRegisterExpTemplate2.setWorkEndTime("2017/12/12");
        entryRegisterExpTemplate2.setFirm("test");
        entryRegisterExpTemplate2.setJobDescription("test");
        entryRegisterExpTemplate2.setName1("test");
        entryRegisterExpTemplate2.setObtainTime("2017/12/12");
        entryRegisterExpTemplateList.add(entryRegisterExpTemplate2);

        EntryRegisterExpTemplate entryRegisterExpTemplate3 = new EntryRegisterExpTemplate();
        entryRegisterExpTemplate3.setEmpNumber("test");
        entryRegisterExpTemplate3.setUsername("test");
        entryRegisterExpTemplate3.setGender(0);
        entryRegisterExpTemplate3.setBirthday("2017/12/12");
        entryRegisterExpTemplate3.setNation("test");
        entryRegisterExpTemplate3.setMarriage("test");
        entryRegisterExpTemplate3.setPoliticsStatus("test");
        entryRegisterExpTemplate3.setNativePlace("test");
        entryRegisterExpTemplate3.setStature("test");
        entryRegisterExpTemplate3.setProfession("test");
        entryRegisterExpTemplate3.setEducation("test");
        entryRegisterExpTemplate3.setSchoolTag("test");
        entryRegisterExpTemplate3.setGraduationDate("test");
        entryRegisterExpTemplate3.setHealthStatus("test");
        entryRegisterExpTemplate3.setQq("test");
        entryRegisterExpTemplate3.setPhone("test");
        entryRegisterExpTemplate3.setEmail("test");
        entryRegisterExpTemplate3.setEmergencyContact("test");
        entryRegisterExpTemplate3.setPhoneNumber("test");
        entryRegisterExpTemplate3.setIdCard("test");
        entryRegisterExpTemplate3.setRegisteredAddress("test");
        entryRegisterExpTemplate3.setLocation("test");
        entryRegisterExpTemplate3.setHobbies("test");
        entryRegisterExpTemplate3.setArea("test");
        entryRegisterExpTemplate3.setDepartment("test");
        entryRegisterExpTemplate3.setPosition("test");
        entryRegisterExpTemplate3.setInductionDate("test");
        entryRegisterExpTemplate3.setTitle("test");
        entryRegisterExpTemplate3.setName("test");
        entryRegisterExpTemplate3.setAge(19);
        entryRegisterExpTemplate3.setUnit("test");
        entryRegisterExpTemplate3.setPositionf("test");
        entryRegisterExpTemplate3.setPhonef("test");
        entryRegisterExpTemplate3.setStartTime("2017/01/01");
        entryRegisterExpTemplate3.setEndTime("2017/12/12");
        entryRegisterExpTemplate3.setSchool("test");
        entryRegisterExpTemplate3.setCertificate("test");
        entryRegisterExpTemplate3.setWorkStartTime("2017/01/01");
        entryRegisterExpTemplate3.setWorkEndTime("2017/12/12");
        entryRegisterExpTemplate3.setFirm("test");
        entryRegisterExpTemplate3.setJobDescription("test");
        entryRegisterExpTemplate3.setName1("test");
        entryRegisterExpTemplate3.setObtainTime("2017/12/12");
        entryRegisterExpTemplateList.add(entryRegisterExpTemplate3);

        EntryRegisterExpTemplate entryRegisterExpTemplate4 = new EntryRegisterExpTemplate();
        entryRegisterExpTemplate4.setEmpNumber("test");
        entryRegisterExpTemplate4.setUsername("test");
        entryRegisterExpTemplate4.setGender(0);
        entryRegisterExpTemplate4.setBirthday("2017/12/12");
        entryRegisterExpTemplate4.setNation("test");
        entryRegisterExpTemplate4.setMarriage("test");
        entryRegisterExpTemplate4.setPoliticsStatus("test");
        entryRegisterExpTemplate4.setNativePlace("test");
        entryRegisterExpTemplate4.setStature("test");
        entryRegisterExpTemplate4.setProfession("test");
        entryRegisterExpTemplate4.setEducation("test");
        entryRegisterExpTemplate4.setSchoolTag("test");
        entryRegisterExpTemplate4.setGraduationDate("test");
        entryRegisterExpTemplate4.setHealthStatus("test");
        entryRegisterExpTemplate4.setQq("test");
        entryRegisterExpTemplate4.setPhone("test");
        entryRegisterExpTemplate4.setEmail("test");
        entryRegisterExpTemplate4.setEmergencyContact("test");
        entryRegisterExpTemplate4.setPhoneNumber("test");
        entryRegisterExpTemplate4.setIdCard("test");
        entryRegisterExpTemplate4.setRegisteredAddress("test");
        entryRegisterExpTemplate4.setLocation("test");
        entryRegisterExpTemplate4.setHobbies("test");
        entryRegisterExpTemplate4.setArea("test");
        entryRegisterExpTemplate4.setDepartment("test");
        entryRegisterExpTemplate4.setPosition("test");
        entryRegisterExpTemplate4.setInductionDate("test");
        entryRegisterExpTemplate4.setTitle("test");
        entryRegisterExpTemplate4.setName("test");
        entryRegisterExpTemplate4.setAge(19);
        entryRegisterExpTemplate4.setUnit("test");
        entryRegisterExpTemplate4.setPositionf("test");
        entryRegisterExpTemplate4.setPhonef("test");
        entryRegisterExpTemplate4.setStartTime("2017/01/01");
        entryRegisterExpTemplate4.setEndTime("2017/12/12");
        entryRegisterExpTemplate4.setSchool("test");
        entryRegisterExpTemplate4.setCertificate("test");
        entryRegisterExpTemplate4.setWorkStartTime("2017/01/01");
        entryRegisterExpTemplate4.setWorkEndTime("2017/12/12");
        entryRegisterExpTemplate4.setFirm("test");
        entryRegisterExpTemplate4.setJobDescription("test");
        entryRegisterExpTemplate4.setName1("test");
        entryRegisterExpTemplate4.setObtainTime("2017/12/12");
        entryRegisterExpTemplateList.add(entryRegisterExpTemplate4);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(entryRegisterExpTemplateList, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            List<Field> fields = ClazzUtils.getFields(EntryRegisterExpTemplate.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            for (int i = 0; i < 27; i++) {
                sheet.addMergedRegion(new CellRangeAddress(1, 4, i, i));
            }


            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }
}
