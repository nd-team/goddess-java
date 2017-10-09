package com.bjike.goddess.staffentry.service;

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
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.*;
import com.bjike.goddess.staffentry.entity.*;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                flag = checkDepartIdentity("2");
                break;
            case ADD:
                flag = checkDepartIdentity("7");
                break;
            case EDIT:
                flag = checkDepartIdentity("7");
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
        Long count = super.count(entryRegisterDTO);
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
    public List<EntryRegister> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        if (checkDepartIdentity("2")) {
            entryRegisterDTO.getSorts().add("createTime=desc");
        } else {
            UserBO userBO = userAPI.currentUser();
            String userName = userBO.getUsername();
            entryRegisterDTO.getConditions().add(Restrict.eq("username", userName));
        }
        List<EntryRegister> entryRegisters = super.findByPage(entryRegisterDTO);
        return entryRegisters;
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
        BeanTransform.copyProperties(entryRegister, temp, "id", "createTime", "birthday", "graduationDate");
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
        if (StringUtils.isBlank(entryRegisterTO.getEmpNumber())) {
            throw new SerException("员工编号不能为空");
        }
        try {
            StaffStatus staffStatus = positionDetailUserAPI.statusByName(entryRegister.getUsername());//查看员工状态
            entryRegister.setStaffStatus(staffStatus);
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
        StaffStatus status = positionDetailUserAPI.statusByName(entryRegister.getUsername());
        senioritySubsidiesTO.setStaffStatus(status);
        senioritySubsidiesAPI.saveSen(senioritySubsidiesTO);
        //添加电脑补助lijuntao
        ComputerSubsidiesAddTO computerSubsidiesAddTO = new ComputerSubsidiesAddTO();
        computerSubsidiesAddTO.setArea(entryRegister.getArea());
        computerSubsidiesAddTO.setDepartment(entryRegister.getDepartment());
        computerSubsidiesAddTO.setName(entryRegister.getUsername());
        computerSubsidiesAddTO.setEntryDate(entryRegister.getInductionDate().toString());
        computerSubsidiesAddTO.setSubsidiesStatus(SubsidiesStatus.NOSUBSIDIES);
        StaffStatus status1 = positionDetailUserAPI.statusByName(entryRegister.getUsername());
        computerSubsidiesAddTO.setStaffStatus(status1);
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
            return DateUtil.dateToString(entryRegisters.stream().map(EntryRegister::getInductionDate).distinct().collect(Collectors.toList()).get(0));
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
    public Integer findNumByEntryDate(String[] date,String area,String dep) throws SerException {
        Integer num = 0;
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.between("inductionDate", date));
        entryRegisterDTO.getConditions().add(Restrict.eq("area",area));
        entryRegisterDTO.getConditions().add(Restrict.eq("department",dep));
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
        entryRegisterDTO.getConditions().add(Restrict.eq("area",area));
        entryRegisterDTO.getConditions().add(Restrict.eq("department",dep));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if (entryRegisters != null && entryRegisters.size() > 0) {
            num = entryRegisters.size();
        }
        return num;
    }
    @Override
    public Set<String> names() throws SerException {
        List<EntryRegister> list = super.findAll();
        return list.stream().map(entryRegister -> entryRegister.getUsername()).collect(Collectors.toSet());

    }
}
