package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.*;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private FamilyMemberSer familyMemberAPI;
    @Autowired
    private StudyExperienceSer studyExperienceAPI;
    @Autowired
    private WorkExperienceSer workExperienceAPI;

    @Autowired
    private CredentialSer credentialAPI;

    @Cacheable
    @Override
    public List<EntryRegister> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        //TODO: tanghaixiang 2017-03-10 未做根据 entryRegisterdtO 分页查询所有
        List<EntryRegister> entryRegisters = super.findByPage( entryRegisterDTO );
        return entryRegisters;
    }

    @Cacheable
    @Override
    public EntryRegisterBO getEntryRegister(String id) throws SerException {

        EntryRegisterBO entryRegisterBO = BeanTransform.copyProperties(super.findById(id), EntryRegisterBO.class );

        /**
         *  根据入职登记查询子表
         */
        //TODO: tanghaixiang 2017-03-10 未做根据 entryRegisterBO 查询下边的子表
        List<FamilyMemberBO> familyMemberList = new ArrayList<>(1);
        List<StudyExperienceBO> studyExperienceList = new ArrayList<>(2);
        List<WorkExperienceBO> workExperienceList = new ArrayList<>(3);
        List<CredentialBO> credentialList = new ArrayList<>(4);

        entryRegisterBO.setFamilyMemberBOList( familyMemberList );
        entryRegisterBO.setStudyExperienceBOList( studyExperienceList );
        entryRegisterBO.setWorkExperienceBOList( workExperienceList );
        entryRegisterBO.setCredentialBOList( credentialList ) ;
        return entryRegisterBO;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeEntryRegister(String id) throws SerException {
        EntryRegister entryRegister = super.findById(id);
        /**
         * 删除子表中的数据
         */
        deleteSubTableRecords(entryRegister);

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
        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        try {
            entryRegister.setModifyTime(LocalDateTime.now() );
            super.update(entryRegister);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

        /**
         * 删除子表中的数据
         */
        deleteSubTableRecords(entryRegister);

        /**
         * 往子表中插入数据
         */
        insertSubTableRecords(entryRegister, familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);

        return BeanTransform.copyProperties(entryRegister , EntryRegisterBO.class );
    }

    private void insertSubTableRecords(EntryRegister entryRegister, FamilyMemberTO familyMemberTO,
                                       StudyExperienceTO studyExperienceTO, WorkExperienceTO workExperienceTO,
                                       CredentialTO credentialTO) throws SerException {
        insertFamilyMember(entryRegister, familyMemberTO);
        insertStudyExperience(entryRegister, studyExperienceTO);
        insertWorkExperience(entryRegister, workExperienceTO);
        insertCredential(entryRegister, credentialTO);
    }

    private void deleteSubTableRecords(EntryRegister entryRegister) {
        /**
         * 删除家庭成员
         */
        FamilyMemberDTO familyMemberDTO = new FamilyMemberDTO();

//        deleteFamily( familyMemberDTO );

        /**
         * 删除学习经历
         */
        StudyExperienceDTO studyExperienceDTO = new StudyExperienceDTO();
//        deleteStudy( studyExperienceDTO);

        /**
         * 删除工作经历
         */
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
//        deleteWork( workExperienceDTO);

        /**
         * 删除证书情况
         */
        CredentialDTO credentialDTO = new CredentialDTO();
//        deleteCredential( credentialDTO);
    }


    @Override
    @Transactional(rollbackFor = SerException.class)
    public EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                               WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        EntryRegister entryRegister = BeanTransform.copyProperties(entryRegisterTO, EntryRegister.class, true);
        //TODO:tanghaixiang 2017-03-03 获取当前用户工号
        entryRegister.setEmpNumber("IKE002530");
        try {
            entryRegister.setCreateTime( LocalDateTime.now());
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

        return BeanTransform.copyProperties(entryRegister , EntryRegisterBO.class );
    }

    private void insertFamilyMember(EntryRegister entryRegister, FamilyMemberTO familyMemberTO) throws SerException {

        List<FamilyMemberTO> familyMembers = new ArrayList<>(0);
        if (familyMemberTO != null) {
            int countTitle = familyMemberTO.getTitles().size();
            for (int i = 0; i < countTitle; i++) {
                FamilyMemberTO temp = new FamilyMemberTO();
                temp.setTitle(familyMemberTO.getTitles().get(i));
                temp.setName(familyMemberTO.getNames().get(i));
                temp.setAge(familyMemberTO.getAges().get(i));
                temp.setUnit(familyMemberTO.getUnits().get(i));
                temp.setPosition(familyMemberTO.getPositions().get(i));
                temp.setPhone(familyMemberTO.getPhones().get(i));
                temp.setEntryRegister(entryRegister);

                familyMembers.add(temp);
            }
            familyMemberAPI.insertFamilys(familyMembers);
        }

    }


    private void insertStudyExperience(EntryRegister entryRegister, StudyExperienceTO studyExperienceTO) throws SerException {

        List<StudyExperienceTO> studyExperienceTOS = new ArrayList<>(0);
        if (studyExperienceTO != null) {
            int countStartTime = studyExperienceTO.getStudyStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                StudyExperienceTO temp = new StudyExperienceTO();
                temp.setStartTime(studyExperienceTO.getStudyStartTimes().get(i));
                temp.setEndTime(studyExperienceTO.getStudyEndTimes().get(i));
                temp.setSchool(studyExperienceTO.getSchools().get(i));
                temp.setCertificate(studyExperienceTO.getCertificates().get(i));
                temp.setEntryRegister(entryRegister);

                studyExperienceTOS.add(temp);
            }
            studyExperienceAPI.insertStudyExperiences(studyExperienceTOS);
        }

    }

    private void insertWorkExperience(EntryRegister entryRegister, WorkExperienceTO workExperienceTO) throws SerException {

        List<WorkExperienceTO> workExperienceTOS = new ArrayList<>(0);
        if (workExperienceTO != null) {
            int countStartTime = workExperienceTO.getWorkStartTimes().size();
            for (int i = 0; i < countStartTime; i++) {
                WorkExperienceTO temp = new WorkExperienceTO();
                temp.setStartTime(workExperienceTO.getWorkStartTimes().get(i));
                temp.setEndTime(workExperienceTO.getWorkEndTimes().get(i));
                temp.setFirm(workExperienceTO.getFirms().get(i));
                temp.setJobDescription(workExperienceTO.getJobDescriptions().get(i));
                temp.setEntryRegister(entryRegister);

                workExperienceTOS.add(temp);
            }
            workExperienceAPI.insertWorkExperiences(workExperienceTOS);
        }

    }

    private void insertCredential(EntryRegister entryRegister, CredentialTO credentialTO) throws SerException {

        List<CredentialTO> credentialTOS = new ArrayList<>(0);
        if (credentialTO != null) {
            int countName = credentialTO.getNameses().size();
            for (int i = 0; i < countName; i++) {
                CredentialTO temp = new CredentialTO();
                temp.setName(credentialTO.getNameses().get(i));
                temp.setObtainTime(credentialTO.getObtainTimes().get(i));
                temp.setEntryRegister(entryRegister);

                credentialTOS.add(temp);
            }
            credentialAPI.insertCredentials(credentialTOS);
        }

    }
}
