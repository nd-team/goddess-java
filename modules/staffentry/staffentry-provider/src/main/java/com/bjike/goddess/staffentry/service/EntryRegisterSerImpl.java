package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.*;
import com.bjike.goddess.staffentry.entity.*;
import com.bjike.goddess.staffentry.enums.GuideAddrStatus;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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



    /**
     * 检测部门
     * @param idFlag
     * @throws SerException
     */
    private Boolean checkDepartIdentity(String idFlag) throws SerException{
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
        }else{
            flag = true;
        }
        return flag;
    }



    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = checkDepartIdentity("2");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = checkDepartIdentity("7");
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagAdd ) {
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
        Long count = super.count( entryRegisterDTO);
        return count;
    }

    @Override
    public EntryRegisterBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        EntryRegister temp = super.findById(id);
        return BeanTransform.copyProperties(temp , EntryRegisterBO.class);
    }

    @Override
    public List<EntryRegister> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        entryRegisterDTO.getSorts().add("createTime=desc");
        List<EntryRegister> entryRegisters = super.findByPage( entryRegisterDTO );
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
        familyMemberDTO.getConditions().add(Restrict.eq("entryRegister.id",id));
        List<FamilyMember> family = familyMemberSer.findByCis( familyMemberDTO );
        familyMemberList = BeanTransform.copyProperties( family ,FamilyMemberBO.class);
        //学习经历
        StudyExperienceDTO studyExperienceDTO = new StudyExperienceDTO();
        studyExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id",id));
        List<StudyExperience> study = studyExperienceSer.findByCis( studyExperienceDTO );
        studyExperienceList = BeanTransform.copyProperties( study ,StudyExperienceBO.class);
        //工作经历
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        workExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id",id));
        List<WorkExperience> work = workExperienceSer.findByCis( workExperienceDTO );
        workExperienceList = BeanTransform.copyProperties( work ,WorkExperienceBO.class);
        //证书情况
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.getConditions().add(Restrict.eq("entryRegister.id",id));
        List<Credential> credentials = credentialSer.findByCis( credentialDTO );
        credentialList = BeanTransform.copyProperties( credentials ,CredentialBO.class);

        EntryRegisterBO bo = BeanTransform.copyProperties( temp, EntryRegisterBO.class );
        bo.setFamilyMemberBOList( familyMemberList );
        bo.setStudyExperienceBOList( studyExperienceList );
        bo.setWorkExperienceBOList( workExperienceList );
        bo.setCredentialBOList( credentialList ) ;
        return bo;
    }

    @Override
    public EntryRegister getByNumber(String number) throws SerException {
        EntryRegisterDTO entryRegisterDTO = new EntryRegisterDTO();
        entryRegisterDTO.getConditions().add(Restrict.eq("empNumber",number));
        List<EntryRegister> entryRegisters = super.findByCis(entryRegisterDTO);
        if(null != entryRegisters && entryRegisters.size() >0){
            EntryRegister entity = entryRegisters.get(0);
            return entity;
        }
        return null;
    }

    @Override
    public List<EntryRegister> list() throws SerException {
        return super.findAll();
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEntryRegister(String id) throws SerException {

        if(StringUtils.isBlank(id)){
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
        if(StringUtils.isBlank(entryRegisterTO.getId())){
            throw new SerException("Id不能为空哦");
        }
        EntryRegister temp = super.findById(entryRegisterTO.getId());
        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        BeanTransform.copyProperties(entryRegister , temp,"id","createTime","birthday","graduationDate");
        temp.setBirthday( entryRegister.getBirthday());
        temp.setGraduationDate(entryRegister.getGraduationDate());
        try {
            temp.setModifyTime(LocalDateTime.now() );
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
        entryRegister =  super.findById(entryRegister.getId());
        insertSubTableRecords(entryRegister, familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);

        return BeanTransform.copyProperties(temp , EntryRegisterBO.class );
    }

    private void insertSubTableRecords(EntryRegister entryRegister, FamilyMemberTO familyMemberTO,
                                       StudyExperienceTO studyExperienceTO, WorkExperienceTO workExperienceTO,
                                       CredentialTO credentialTO) throws SerException {
        insertFamilyMember(entryRegister, familyMemberTO);
        insertStudyExperience(entryRegister, studyExperienceTO);
        insertWorkExperience(entryRegister, workExperienceTO);
        insertCredential(entryRegister, credentialTO);
    }

    private void deleteSubTableRecords(String entryId) throws SerException{
        /**
         * 删除家庭成员
         */
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();
        familyMemberDTO.getConditions().add(Restrict.eq("entryRegister.id",entryId));
        List<FamilyMember> family = familyMemberSer.findByCis( familyMemberDTO );
        familyMemberSer.remove( family );

        /**
         * 删除学习经历
         */
        StudyExperienceDTO studyExperienceDTO = new StudyExperienceDTO();
        studyExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id",entryId));
        List<StudyExperience> study = studyExperienceSer.findByCis( studyExperienceDTO );
        studyExperienceSer.remove( study);

        /**
         * 删除工作经历
         */
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        workExperienceDTO.getConditions().add(Restrict.eq("entryRegister.id",entryId));
        List<WorkExperience> work = workExperienceSer.findByCis( workExperienceDTO );
        workExperienceSer.remove(work);

        /**
         * 删除证书情况
         */
        CredentialDTO credentialDTO = new CredentialDTO();
        credentialDTO.getConditions().add(Restrict.eq("entryRegister.id",entryId));
        List<Credential> credentials = credentialSer.findByCis( credentialDTO );
        credentialSer.remove( credentials );
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                               WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {

        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        if(StringUtils.isBlank(entryRegisterTO.getEmpNumber())){
            throw new SerException("员工编号不能为空");
        }
        try {
            super.save(entryRegister);
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

        EntryRegisterBO bo = new EntryRegisterBO();
                BeanUtils.copyProperties(entryRegister , bo );
        return bo;
    }

    private void insertFamilyMember(EntryRegister entryRegister, FamilyMemberTO familyMemberTO) throws SerException {

        List<FamilyMember> familyMembers = new ArrayList<>(0);
        if ( null != familyMemberTO && null != familyMemberTO.getTitles()) {
            int countTitle = familyMemberTO.getTitles().size();
            for (int i = 0; i < countTitle; i++) {
                FamilyMember temp = new FamilyMember();
                temp.setTitle(familyMemberTO.getTitles().get(i));
                temp.setName(null != familyMemberTO.getNames() ?familyMemberTO.getNames().get(i):"");
                temp.setAge(null != familyMemberTO.getAges() ?(null == familyMemberTO.getAges().get(i)?0:familyMemberTO.getAges().get(i)):0);
                temp.setUnit(null != familyMemberTO.getUnits() ?familyMemberTO.getUnits().get(i):"");
                temp.setPosition(null != familyMemberTO.getPositions() ?familyMemberTO.getPositions().get(i):"");
                temp.setPhone(null != familyMemberTO.getPhones() ?familyMemberTO.getPhones().get(i):"");
                temp.setEntryRegister(entryRegister);

                familyMembers.add(temp);
            }
            familyMemberSer.save(familyMembers);
        }

    }


    private void insertStudyExperience(EntryRegister entryRegister, StudyExperienceTO studyExperienceTO) throws SerException {

        List<StudyExperience> studyExperienceTOS = new ArrayList<>(0);
        if ( null != studyExperienceTO && null != studyExperienceTO.getStudyStartTimes()) {
            int countStartTime = studyExperienceTO.getStudyStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                StudyExperience temp = new StudyExperience();
                temp.setStartTime(StringUtils.isBlank(studyExperienceTO.getStudyStartTimes().get(i)) ? null:LocalDate.parse(studyExperienceTO.getStudyStartTimes().get(i), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                temp.setEndTime(null == studyExperienceTO.getStudyEndTimes() ? null:(StringUtils.isBlank(studyExperienceTO.getStudyEndTimes().get(i)) ? null:LocalDate.parse(studyExperienceTO.getStudyEndTimes().get(i), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                temp.setSchool(null == studyExperienceTO.getSchools()? "":studyExperienceTO.getSchools().get(i));
                temp.setCertificate(null == studyExperienceTO.getCertificates()? "":studyExperienceTO.getCertificates().get(i));
                temp.setEntryRegister(entryRegister);

                studyExperienceTOS.add(temp);
            }
            studyExperienceSer.save(studyExperienceTOS);
        }

    }

    private void insertWorkExperience(EntryRegister entryRegister, WorkExperienceTO workExperienceTO) throws SerException {

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<WorkExperience> workExperienceTOS = new ArrayList<>(0);
        if ( null!=workExperienceTO  && null != workExperienceTO.getWorkStartTimes()) {
            int countStartTime = workExperienceTO.getWorkStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                WorkExperience temp = new WorkExperience();
                temp.setStartTime(StringUtils.isBlank(workExperienceTO.getWorkStartTimes().get(i)) ? null:LocalDate.parse(workExperienceTO.getWorkStartTimes().get(i), formater));
                temp.setEndTime(null == workExperienceTO.getWorkEndTimes() ? null:(StringUtils.isBlank(workExperienceTO.getWorkEndTimes().get(i)) ? null:LocalDate.parse(workExperienceTO.getWorkEndTimes().get(i), formater)));
                temp.setFirm(null == workExperienceTO.getFirms()?"":workExperienceTO.getFirms().get(i));
                temp.setJobDescription(null == workExperienceTO.getJobDescriptions()?"":workExperienceTO.getJobDescriptions().get(i));
                temp.setEntryRegister(entryRegister);

                workExperienceTOS.add(temp);
            }
            workExperienceSer.save(workExperienceTOS);
        }

    }

    private void insertCredential(EntryRegister entryRegister, CredentialTO credentialTO) throws SerException {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Credential> credentialTOS = new ArrayList<>(0);
        if ( null!=credentialTO && null!= credentialTO.getNameses() ) {
            int countName = credentialTO.getNameses().size();
            for (int i = 0; i < countName; i++) {
                Credential temp = new Credential();
                temp.setName(credentialTO.getNameses().get(i));

                temp.setObtainTime(null == credentialTO.getObtainTimes()?null:(StringUtils.isBlank(credentialTO.getObtainTimes().get(i))?null:LocalDate.parse(credentialTO.getObtainTimes().get(i),formater)));
                temp.setEntryRegister(entryRegister);

                credentialTOS.add(temp);
            }
            credentialSer.save(credentialTOS);
        }

    }
}
