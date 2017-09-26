package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.intromanage.bo.*;
import com.bjike.goddess.intromanage.dto.*;
import com.bjike.goddess.intromanage.entity.*;
import com.bjike.goddess.intromanage.excel.SonPermissionObject;
import com.bjike.goddess.intromanage.to.*;
import com.bjike.goddess.intromanage.type.DemandType;
import com.bjike.goddess.intromanage.type.GuideAddrStatus;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.enums.StaffStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 个人简介业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class IndividualResumeSerImpl extends ServiceImpl<IndividualResume, IndividualResumeDTO> implements IndividualResumeSer {

    @Autowired
    private StaffRewardSer staffRewardSer;

    @Autowired
    private StaffHonorSer staffHonorSer;

    @Autowired
    private EducateExperienceSer educateExperienceSer;

    @Autowired
    private WorkExperienceSer workExperienceSer;

    @Autowired
    private CredentialSituationSer credentialSituationSer;

    @Autowired
    private IndividualDisplayFieldSer individualDisplayFieldSer;

    @Autowired
    private IndividualDisplayUserSer individualDisplayUserSer;
    @Autowired
    private FirmIntroSer firmIntroSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagIndividual = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("individualresume");
        obj.setDescribesion("个人简介");
        if (flagIndividual) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFirm = firmIntroSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("firmintro");
        obj.setDescribesion("公司简介");
        if (flagFirm) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SETACCOR:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 根据id查询个人简介
     *
     * @param id 个人简介唯一标识
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public IndividualResumeBO findResumeById(String id) throws SerException {
        IndividualResume individualResume = super.findById(id);
        if (individualResume == null) {
            return null;
        }
        UserBO userBO = userAPI.currentUser();
        String currentUsername = userBO.getUsername();//获取当前用户姓名
        List<IndividualDisplayUser> users = individualDisplayUserSer.findAll();
        for (IndividualDisplayUser model : users) {
            String usernames = model.getUsernames();
            boolean containsUsername = StringUtils.isNotBlank(usernames) && (usernames.indexOf(currentUsername) >= 0);
            if (containsUsername) {
                String individualDisplayId = model.getDisplayId();
                IndividualDisplayField individualDisplayField = individualDisplayFieldSer.findById(individualDisplayId);
                //设置需要显示的字段
                checkShowFields(individualDisplayField, individualResume);
                break;
            }
        }


        //查询员工奖励
        StaffRewardDTO staffRewardDTO = new StaffRewardDTO();
        staffRewardDTO.getConditions().add(Restrict.eq("staffId", id));
        List<StaffReward> honorAndQualities = staffRewardSer.findByCis(staffRewardDTO);
        List<StaffRewardBO> honorAndQualitieBOs = BeanTransform.copyProperties(honorAndQualities, StaffRewardBO.class);
        //查询员工荣誉
        StaffHonorDTO staffHonorDTO = new StaffHonorDTO();
        staffHonorDTO.getConditions().add(Restrict.eq("staffId", id));
        List<StaffHonor> mainBusinessIntros = staffHonorSer.findByCis(staffHonorDTO);
        List<StaffHonorBO> mainBusinessIntroBOS = BeanTransform.copyProperties(mainBusinessIntros, StaffHonorBO.class);
        //查询教育经历
        EducateExperienceDTO educateExperienceDTO = new EducateExperienceDTO();
        educateExperienceDTO.getConditions().add(Restrict.eq("staffId", id));
        List<EducateExperience> successStories = educateExperienceSer.findByCis(educateExperienceDTO);
        List<EducateExperienceBO> successStoriesBOS = BeanTransform.copyProperties(successStories, EducateExperienceBO.class);
        //查询工作经历
        WorkExperienceDTO workExperienceDTO = new WorkExperienceDTO();
        workExperienceDTO.getConditions().add(Restrict.eq("staffId", id));
        List<WorkExperience> customerAndPartners = workExperienceSer.findByCis(workExperienceDTO);
        List<WorkExperienceBO> customerAndPartnerBOS = BeanTransform.copyProperties(customerAndPartners, WorkExperienceBO.class);
        //查询证书情况
        CredentialSituationDTO credentialSituationDTO = new CredentialSituationDTO();
        credentialSituationDTO.getConditions().add(Restrict.eq("staffId", id));
        List<CredentialSituation> communicationPaths = credentialSituationSer.findByCis(credentialSituationDTO);
        List<CredentialSituationBO> communicationPathBOS = BeanTransform.copyProperties(communicationPaths, CredentialSituationBO.class);

        IndividualResumeBO individualResumeBO = BeanTransform.copyProperties(individualResume, IndividualResumeBO.class);
        individualResumeBO.setStaffRewardBOS(honorAndQualitieBOs);
        individualResumeBO.setStaffHonorBOS(mainBusinessIntroBOS);
        individualResumeBO.setEducateExperienceBOS(successStoriesBOS);
        individualResumeBO.setWorkExperienceBOS(customerAndPartnerBOS);
        individualResumeBO.setCredentialSituationBOS(communicationPathBOS);
        return individualResumeBO;
    }

    /**
     * 分页查询个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public List<IndividualResumeBO> list(IndividualResumeDTO dto) throws SerException {
        checkPermission();
        seachCond(dto);
        List<IndividualResume> list = super.findByPage(dto);
        UserBO userBO = userAPI.currentUser();
        String currentUsername = userBO.getUsername();//获取当前用户姓名
        List<IndividualDisplayUser> users = individualDisplayUserSer.findAll();
        for (IndividualDisplayUser model : users) {
            String usernames = model.getUsernames();
            boolean containsUsername = StringUtils.isNotBlank(usernames) && (usernames.indexOf(currentUsername) >= 0);
            if (containsUsername) {
                String individualDisplayId = model.getDisplayId();
                IndividualDisplayField individualDisplayField = individualDisplayFieldSer.findById(individualDisplayId);
                //设置需要显示的字段
                for (IndividualResume individualResume : list) {
                    checkShowFields(individualDisplayField, individualResume);
                }
                break;
            }
        }
        List<IndividualResumeBO> boList = BeanTransform.copyProperties(list, IndividualResumeBO.class);
        return boList;
    }

    public void seachCond(IndividualResumeDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getName())) {
            dto.getConditions().add(Restrict.eq("name", dto.getName()));
        }
    }

    /**
     * 校验是否显示字段
     *
     * @param individualDisplayField 个人简介显示字段
     * @param individualResume       个人简介
     */
    private void checkShowFields(IndividualDisplayField individualDisplayField, IndividualResume individualResume) {
        if (individualDisplayField.getIfShowArea() != Boolean.TRUE) {
            individualResume.setArea(null);
        }
        if (individualDisplayField.getIfShowDepartment() != Boolean.TRUE) {
            individualResume.setDepartment(null);
        }
        if (individualDisplayField.getIfShowName() != Boolean.TRUE) {
            individualResume.setName(null);
        }
        if (individualDisplayField.getIfShowEmployeeId() != Boolean.TRUE) {
            individualResume.setEmployeeId(null);
        }
        if (individualDisplayField.getIfShowPost() != Boolean.TRUE) {
            individualResume.setPost(null);
        }
        if (individualDisplayField.getIfShowEmsil() != Boolean.TRUE) {
            individualResume.setEMsil(null);
        }
        if (individualDisplayField.getIfShowEntryDate() != Boolean.TRUE) {
            individualResume.setEntryDate(null);
        }
        if (individualDisplayField.getIfShowTenancyDuration() != Boolean.TRUE) {
            individualResume.setTenancyDuration(null);
        }
        if (individualDisplayField.getIfShowPositiveTime() != Boolean.TRUE) {
            individualResume.setPositiveTime(null);
        }
        if (individualDisplayField.getIfShowWhetherSocialSecurity() != Boolean.TRUE) {
            individualResume.setWhetherSocialSecurity(null);
        }
        if (individualDisplayField.getIfShowBuySocialSecurityTime() != Boolean.TRUE) {
            individualResume.setBuySocialSecurityTime(null);
        }
        if (individualDisplayField.getIfShowSkillRank() != Boolean.TRUE) {
            individualResume.setSkillRank(null);
        }
        if (individualDisplayField.getIfShowManageGrade() != Boolean.TRUE) {
            individualResume.setManageGrade(null);
        }
        if (individualDisplayField.getIfShowItemCommission() != Boolean.TRUE) {
            individualResume.setItemCommission(null);
        }
        if (individualDisplayField.getIfShowManageCommission() != Boolean.TRUE) {
            individualResume.setManageCommission(null);
        }
        if (individualDisplayField.getIfShowAwardScore() != Boolean.TRUE) {
            individualResume.setAwardScore(null);
        }
        if (individualDisplayField.getIfShowPenaltyScore() != Boolean.TRUE) {
            individualResume.setPenaltyScore(null);
        }
        if (individualDisplayField.getIfShowEmpiricalValue() != Boolean.TRUE) {
            individualResume.setEmpiricalValue(null);
        }
        if (individualDisplayField.getIfShowSubsidyAmount() != Boolean.TRUE) {
            individualResume.setSubsidyAmount(null);
        }
        if (individualDisplayField.getIfShowAnnualLeave() != Boolean.TRUE) {
            individualResume.setAnnualLeave(null);
        }
        if (individualDisplayField.getIfShowIndividualVision() != Boolean.TRUE) {
            individualResume.setIndividualVision(null);
        }
        if (individualDisplayField.getIfShowPicture() != Boolean.TRUE) {
            individualResume.setPicture(null);
        }
        if (individualDisplayField.getIfShowHobbies() != Boolean.TRUE) {
            individualResume.setHobbies(null);
        }
        if (individualDisplayField.getIfShowPersonalEmail() != Boolean.TRUE) {
            individualResume.setPersonalEmail(null);
        }
        if (individualDisplayField.getIfShowDateOfBirth() != Boolean.TRUE) {
            individualResume.setDateOfBirth(null);
        }
        if (individualDisplayField.getIfShowQqNumber() != Boolean.TRUE) {
            individualResume.setQqNumber(null);
        }
        if (individualDisplayField.getIfShowWechatId() != Boolean.TRUE) {
            individualResume.setWechatId(null);
        }
        if (individualDisplayField.getIfShowMobile() != Boolean.TRUE) {
            individualResume.setMobile(null);
        }
        if (individualDisplayField.getIfShowEmergencyContact() != Boolean.TRUE) {
            individualResume.setEmergencyContact(null);
        }
        if (individualDisplayField.getIfShowEmergencyContactPhone() != Boolean.TRUE) {
            individualResume.setEmergencyContactPhone(null);
        }
        if (individualDisplayField.getIfShowEducation() != Boolean.TRUE) {
            individualResume.setEducation(null);
        }
        if (individualDisplayField.getIfShowSpecialty() != Boolean.TRUE) {
            individualResume.setSpecialty(null);
        }
        if (individualDisplayField.getIfShowAcademy() != Boolean.TRUE) {
            individualResume.setAcademy(null);
        }
        if (individualDisplayField.getIfShowGraduationTime() != Boolean.TRUE) {
            individualResume.setGraduationTime(null);
        }
        if (individualDisplayField.getIfShowNowResidence() != Boolean.TRUE) {
            individualResume.setNowResidence(null);
        }
        if (individualDisplayField.getIfShowRegisteredAddress() != Boolean.TRUE) {
            individualResume.setRegisteredAddress(null);
        }
        if (individualDisplayField.getIfShowWorkExperience() != Boolean.TRUE) {
            individualResume.setWorkExperience(null);
        }
    }

    /**
     * 保存个人简介
     *
     * @param to 个人简介to
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    @Transactional
    public IndividualResumeBO save(IndividualResumeTO to) throws SerException {
        checkPermission();
        IndividualResume entity = BeanTransform.copyProperties(to, IndividualResume.class, true);
        entity.setUpdateDate(LocalDate.now());
        StaffStatus staffStatus = positionDetailUserAPI.statusByName(to.getName());//查看员工状态
        entity.setStaffStatus(staffStatus);
        entity.setStatus(Status.THAW);
        entity = super.save(entity);
        IndividualResumeBO bo = BeanTransform.copyProperties(entity, IndividualResumeBO.class);
        String staffId = entity.getId();//员工id
        saveSubObj(to, staffId);//保存所有的子对象
        return bo;
    }

    /**
     * 保存所有的子对象
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveSubObj(IndividualResumeTO to, String staffId) throws SerException {
        saveStaffRewards(to, staffId);//保存员工奖励
        saveStaffHonors(to, staffId); //保存员工荣誉
        saveEducateExperiences(to, staffId);//保存教育经历
        saveWorkExperiences(to, staffId);//保存工作经历
        saveCredentialSituation(to, staffId);//保存证书情况
    }

    /**
     * 保存证书情况
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveCredentialSituation(IndividualResumeTO to, String staffId) throws SerException {
        List<CredentialSituationTO> credentialSituationTOS = to.getCredentialSituationTOS();
        List<CredentialSituation> list = new ArrayList<>(0);
        if (credentialSituationTOS != null && credentialSituationTOS.size() > 0) {
            for (CredentialSituationTO temp : credentialSituationTOS) {
                CredentialSituation model = new CredentialSituation();
                model.setCertificateTitle(temp.getCertificateTitle());
                model.setCertificateTime(temp.getCertificateTime());
                model.setCertificateNo(temp.getCertificateNo());
                model.setStaffId(staffId);
                list.add(model);
            }

            credentialSituationSer.save(list);
        }

//        String[] certificateTitles = to.getCertificateTitles();//获取的专业证书
//        String[] certificateTimes = to.getCertificateTimes();  //获取证书的时间
//        String[] certificateNos = to.getCertificateNos();      //证书编号
//        boolean certificateTitlesNotEmpty = (certificateTitles != null) && (certificateTitles.length > 0);
//        if (certificateTitlesNotEmpty) {
//            List<CredentialSituation> list = new ArrayList<>(0);
//            int len = certificateTitles.length;
//            for (int i = 0; i < len; i++) {
//                CredentialSituation model = new CredentialSituation();
//                model.setCertificateTitle(certificateTitles[i]);
//                model.setCertificateTime(certificateTimes[i]);
//                model.setCertificateNo(certificateNos[i]);
//                model.setStaffId(staffId);
//                list.add(model);
//            }
//            credentialSituationSer.save(list);
//        }
    }

    /**
     * 保存工作经历
     *
     * @param to      个人简历to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveWorkExperiences(IndividualResumeTO to, String staffId) throws SerException {
        List<WorkExperienceTO> workExperienceTOS = to.getWorkExperienceTOS();
        List<WorkExperience> list = new ArrayList<>(0);
        if (workExperienceTOS != null && workExperienceTOS.size() > 0) {
            for (WorkExperienceTO temp : workExperienceTOS) {
                WorkExperience model = new WorkExperience();
                model.setParticipatedActivity(temp.getParticipatedActivity());
                model.setProjectExperience(temp.getProjectExperience());
                model.setStaffId(staffId);
                list.add(model);
            }

            workExperienceSer.save(list);
        }


//        String[] participatedActivities = to.getParticipatedActivities();//曾经参与的组织与活动
//        String[] projectExperiences = to.getProjectExperiences();//项目经历
//        boolean participatedActivitiesNotEmpty = (participatedActivities != null) && (participatedActivities.length > 0);
//        if (participatedActivitiesNotEmpty) {
//            List<WorkExperience> list = new ArrayList<>(0);
//            int len = participatedActivities.length;
//            for (int i = 0; i < len; i++) {
//                WorkExperience model = new WorkExperience();
//                model.setParticipatedActivity(participatedActivities[i]);
//                model.setProjectExperience(projectExperiences[i]);
//                model.setStaffId(staffId);
//                list.add(model);
//            }
//            workExperienceSer.save(list);
//        }
    }

    /**
     * 保存教育经历
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveEducateExperiences(IndividualResumeTO to, String staffId) throws SerException {
        List<EducateExperienceTO> educateExperienceTOS = to.getEducateExperienceTOS();
        List<EducateExperience> list = new ArrayList<>(0);
        if (educateExperienceTOS != null && educateExperienceTOS.size() > 0) {
            for (EducateExperienceTO temp : educateExperienceTOS) {
                EducateExperience model = new EducateExperience();
                model.setEducatAddress(temp.getEducatAddress());
                model.setTrainingExperience(temp.getTrainingExperience());
                model.setStaffId(staffId);
                list.add(model);
            }

            educateExperienceSer.save(list);
        }

//        String[] educatAddresses = to.getEducatAddresses();//教育地址
//        String[] trainingExperiences = to.getTrainingExperiences();//培训经历
//        boolean educatAddressesNotEmpty = (educatAddresses != null) && (educatAddresses.length > 0);
//        if (educatAddressesNotEmpty) {
//            List<EducateExperience> list = new ArrayList<>(0);
//            int len = educatAddresses.length;
//            for (int i = 0; i < len; i++) {
//                EducateExperience model = new EducateExperience();
//                model.setEducatAddress(educatAddresses[i]);
//                model.setTrainingExperience(trainingExperiences[i]);
//                model.setStaffId(staffId);
//                list.add(model);
//            }
//            educateExperienceSer.save(list);
//        }
    }

    /**
     * 保存员工荣誉
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveStaffHonors(IndividualResumeTO to, String staffId) throws SerException {
        List<StaffHonorTO> staffHonorTOS = to.getStaffHonorTOS();
        List<StaffHonor> list = new ArrayList<>(0);
        if (staffHonorTOS != null && staffHonorTOS.size() > 0) {
            for (StaffHonorTO temp : staffHonorTOS) {
                StaffHonor model = new StaffHonor();
                model.setHonorName(temp.getHonorName());
                model.setHonorGrade(temp.getHonorGrade());
                model.setFirmSubsidy(temp.getFirmSubsidy());
                model.setStaffId(staffId);
                list.add(model);
            }

            staffHonorSer.save(list);
        }


//        String[] honorNames = to.getHonorNames();//荣誉名称
//        String[] honorGrades = to.getHonorGrades();//荣誉等级
//        String[] firmSubsidies = to.getFirmSubsidies();//公司补助
//        boolean honorNamesNotEmpty = (honorNames != null) && (honorNames.length > 0);
//        if (honorNamesNotEmpty) {
//            List<StaffHonor> list = new ArrayList<>(0);
//            int len = honorNames.length;
//            for (int i = 0; i < len; i++) {
//                StaffHonor model = new StaffHonor();
//                model.setHonorName(honorNames[i]);
//                model.setHonorGrade(honorGrades[i]);
//                model.setFirmSubsidy(firmSubsidies[i]);
//                model.setStaffId(staffId);
//                list.add(model);
//            }
//            staffHonorSer.save(list);
//        }
    }

    /**
     * 保存员工奖励
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveStaffRewards(IndividualResumeTO to, String staffId) throws SerException {
        List<StaffRewardTO> staffRewardTOS = to.getStaffRewardTOS();
        List<StaffReward> list = new ArrayList<>(0);
        if (staffRewardTOS != null && staffRewardTOS.size() > 0) {
            for (StaffRewardTO temp : staffRewardTOS) {
                StaffReward model = new StaffReward();
                model.setRewardsName(temp.getRewardsName());
                model.setPrize(temp.getPrize());
                model.setBonus(temp.getBonus());
                model.setStaffId(staffId);
                list.add(model);
            }

            staffRewardSer.save(list);
        }

//        String[] rewardsNames = to.getRewardsNames();//奖励名称
//        String[] prizes = to.getPrizes();//奖品
//        String[] bonuses = to.getBonuses();//奖金
//        boolean rewardsNamesNotEmpty = (rewardsNames != null) && (rewardsNames.length > 0);
//        if (rewardsNamesNotEmpty) {
//            List<StaffReward> list = new ArrayList<>(0);
//            int len = rewardsNames.length;
//            for (int i = 0; i < len; i++) {
//                StaffReward model = new StaffReward();
//                model.setRewardsName(rewardsNames[i]);
//                model.setPrize(prizes[i]);
//                model.setBonus(bonuses[i]);
//                model.setStaffId(staffId);
//                list.add(model);
//            }
//            staffRewardSer.save(list);
//        }
    }

    /**
     * 更新个人简介
     *
     * @param to 个人简介to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(IndividualResumeTO to) throws SerException {
        checkPermission();
        String staffId = to.getId();
        if (StringUtils.isNotEmpty(staffId)) {
            IndividualResume model = super.findById(staffId);
            if (model != null) {
                updateIndividualResume(to, model);
                removeSubObj(staffId);//删除所有与个人简介相关的子对象
                saveSubObj(to, staffId);//保存子对象
            } else {
                throw new SerException("您好,更新对象为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新个人简介信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateIndividualResume(IndividualResumeTO to, IndividualResume model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除所有的子对象
     *
     * @param staffId 员工id
     * @throws SerException
     */
    private void removeSubObj(String staffId) throws SerException {
        removeStaffRewards(staffId);//删除员工奖励
        removeStaffHonors(staffId); //删除员工荣誉
        removeEducateExperiences(staffId);//删除教育经历
        removeWorkExperiences(staffId);   //删除工作经历
        removeCredentialSituation(staffId);//删除证书情况
    }

    /**
     * 删除证书情况
     *
     * @param staffId 　员工id
     * @throws SerException
     */
    private void removeCredentialSituation(String staffId) throws SerException {
        CredentialSituationDTO dto = new CredentialSituationDTO();
        dto.getConditions().add(Restrict.eq("staffId", staffId));
        List<CredentialSituation> list = credentialSituationSer.findByCis(dto);
        credentialSituationSer.remove(list);
    }

    /**
     * 删除工作经历
     *
     * @param staffId 员工id
     * @throws SerException
     */
    private void removeWorkExperiences(String staffId) throws SerException {
        WorkExperienceDTO dto = new WorkExperienceDTO();
        dto.getConditions().add(Restrict.eq("staffId", staffId));
        List<WorkExperience> list = workExperienceSer.findByCis(dto);
        workExperienceSer.remove(list);
    }

    /**
     * 删除教育经历
     *
     * @param staffId 员工id
     * @throws SerException
     */
    private void removeEducateExperiences(String staffId) throws SerException {
        EducateExperienceDTO dto = new EducateExperienceDTO();
        dto.getConditions().add(Restrict.eq("staffId", staffId));
        List<EducateExperience> list = educateExperienceSer.findByCis(dto);
        educateExperienceSer.remove(list);
    }

    /**
     * 删除员工奖励
     *
     * @param staffId 员工id
     * @throws SerException
     */
    private void removeStaffRewards(String staffId) throws SerException {
        StaffRewardDTO dto = new StaffRewardDTO();
        dto.getConditions().add(Restrict.eq("staffId", staffId));
        List<StaffReward> list = staffRewardSer.findByCis(dto);
        staffRewardSer.remove(list);
    }

    /**
     * 删除员工荣誉
     *
     * @param staffId 员工id
     * @throws SerException
     */
    private void removeStaffHonors(String staffId) throws SerException {
        StaffHonorDTO dto = new StaffHonorDTO();
        dto.getConditions().add(Restrict.eq("staffId", staffId));
        List<StaffHonor> list = staffHonorSer.findByCis(dto);
        staffHonorSer.remove(list);
    }

    /**
     * 根据id删除个人简介,同时删除所有的从表数据
     *
     * @param id 个人简介唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        checkPermission();
        removeSubObj(id);
        super.remove(id);
    }

    /**
     * 设置个人简介显示字段
     *
     * @param username 用户名称数组
     * @param to       个人简介显示字段
     * @throws SerException
     */
    @Override
    public void setIndividualDisplayField(String[] username, IndividualDisplayFieldTO to) throws SerException {
        Boolean usernameIsNotEmpty = (username != null) && (username.length > 0);
        if (usernameIsNotEmpty) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < username.length; i++) {
                if (i < username.length - 1) {
                    sb.append(username[i]).append(",");
                } else {
                    sb.append(username[i]);
                }
            }

            saveIndividualDisplayField(to, sb);
        } else {
            throw new SerException("您好,用户姓名为空,无法设置个人简介显示的字段");
        }
    }

    /**
     *
     *
     * @param to
     * @param sb
     */
    /**
     * 保存个人简介显示字段和显示的用户
     *
     * @param to 个人简介显示字段to
     * @param sb 用户姓名to
     * @throws SerException
     */
    private void saveIndividualDisplayField(IndividualDisplayFieldTO to, StringBuilder sb) throws SerException {
        IndividualDisplayField model = BeanTransform.copyProperties(to, IndividualDisplayField.class, true);
        model = individualDisplayFieldSer.save(model);
        String displayFieldId = model.getId();//获取个人简介显示字段
        String usernameStr = sb.toString();//用户字符串集合
        IndividualDisplayUser individualDisplayUser = new IndividualDisplayUser();
        individualDisplayUser.setUsernames(usernameStr);
        individualDisplayUser.setDisplayId(displayFieldId);
        individualDisplayUserSer.save(individualDisplayUser);//保存显示的用户
    }

    @Override
    public void congealFirmin(String id) throws SerException {
        IndividualResume individualResume = super.findById(id);
        individualResume.setStatus(Status.CONGEAL);
        super.update(individualResume);
    }

    @Override
    public void thawFirmin(String id) throws SerException {
        IndividualResume individualResume = super.findById(id);
        individualResume.setStatus(Status.THAW);
        super.update(individualResume);
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
    public SummationBO summaWeek(Integer year, Integer month, Integer week) throws SerException {
        checkPermission();
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);

        return totalMethod(date);
    }

    @Override
    public SummationBO summaMonth(Integer year, Integer month) throws SerException {
        checkPermission();
        if (year == null || month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String[] date = new String[]{startDate, endDate};
        return totalMethod(date);
    }

    @Override
    public SummationBO summaTotal(String endDate) throws SerException {
        checkPermission();
        endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        String sql = "select min(updateDate) as updateDate,min(entryDate) as entryDate from  " + getTableName(IndividualResume.class);
        List<Object> objects = super.findBySql(sql);
        //个人简历的最早时间
        String startDateInd = "";
        //个人简历入职的最早时间
        String startDateEntry = "";
        if (objects != null && objects.size() > 0) {
            Object[] object = (Object[]) objects.get(0);
            startDateInd = String.valueOf(object[0]);
            startDateEntry = String.valueOf(object[1]);
        }
        String startDateDimi = dimissionInfoAPI.getDate();
        String startDateFirm = firmIntroSer.getDate();
        //个人时间数组
        String[] gdates = new String[]{startDateInd, endDate};
        //入职时间数组
        String[] rdates = new String[]{startDateEntry, endDate};
        //离职时间数组
        String[] ddates = new String[]{startDateDimi, endDate};
        //公司简历时间数组
        String[] fdates = new String[]{startDateFirm, endDate};
        SummationBO summationBO = new SummationBO();
        //可用于招投标的数量
        summationBO.setAvailBiddingNum(availBiddingNum(fdates));
        //招投标类型简介数量
        summationBO.setBiddingNum(biddingNum(fdates));
        //入职培训所用简介数量
        summationBO.setOrientationNum(orientationNum(fdates));
        //公司官网所用简介数量
        summationBO.setOfficialWebsiteNum(officialWebsiteNum(fdates));
        //招聘所用简介数量
        summationBO.setRecruitNum(recruitNum(fdates));
        //入职人数
        summationBO.setInductionNum(inductionNum(rdates));
        //已有个人简介数
        summationBO.setExistingProfileNum(existingProfileNum(gdates));
        //离职人数
        summationBO.setLeaveOffice(leaveOffice(ddates));
        //已冻结有个人简介数
        summationBO.setHasFrozen(hasFrozen(gdates));
        return summationBO;
    }

    //可用于招投标的数量
    public Integer availBiddingNum(String[] date) throws SerException {
        Integer number = 0;
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.between("updateDate", date));
        firmIntroDTO.getConditions().add(Restrict.eq("demandType", DemandType.BIDDING));
        firmIntroDTO.getConditions().add(Restrict.eq("status", Status.THAW));
        List<FirmIntro> firmIntros = firmIntroSer.findByCis(firmIntroDTO);
        if (firmIntros != null && firmIntros.size() > 0) {
            number = firmIntros.size();
        }
        return number;
    }

    //更新（制作）招投标类型简介数量
    public Integer biddingNum(String[] date) throws SerException {
        Integer number = 0;
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.between("updateDate", date));
        firmIntroDTO.getConditions().add(Restrict.eq("demandType", DemandType.BIDDING));
        List<FirmIntro> firmIntros = firmIntroSer.findByCis(firmIntroDTO);
        if (firmIntros != null && firmIntros.size() > 0) {
            number = firmIntros.size();
        }
        return number;
    }

    //更新（制作）入职培训所用简介数量
    public Integer orientationNum(String[] date) throws SerException {
        Integer number = 0;
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.between("updateDate", date));
        firmIntroDTO.getConditions().add(Restrict.eq("demandType", DemandType.INDUCTION_TRAINING));
        List<FirmIntro> firmIntros = firmIntroSer.findByCis(firmIntroDTO);
        if (firmIntros != null && firmIntros.size() > 0) {
            number = firmIntros.size();
        }
        return number;
    }

    //更新（制作）公司官网所用简介数量
    public Integer officialWebsiteNum(String[] date) throws SerException {
        Integer number = 0;
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.between("updateDate", date));
        firmIntroDTO.getConditions().add(Restrict.eq("demandType", DemandType.COMPANY_WEBSITE));
        List<FirmIntro> firmIntros = firmIntroSer.findByCis(firmIntroDTO);
        if (firmIntros != null && firmIntros.size() > 0) {
            number = firmIntros.size();
        }
        return number;
    }

    //更新（制作）招聘所用简介数量
    public Integer recruitNum(String[] date) throws SerException {
        Integer number = 0;
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.between("updateDate", date));
        firmIntroDTO.getConditions().add(Restrict.eq("demandType", DemandType.RECRUIT));
        List<FirmIntro> firmIntros = firmIntroSer.findByCis(firmIntroDTO);
        if (firmIntros != null && firmIntros.size() > 0) {
            number = firmIntros.size();
        }
        return number;
    }

    //入职人数
    public Integer inductionNum(String[] date) throws SerException {
        Integer number = 0;
        IndividualResumeDTO individualResumeDTO = new IndividualResumeDTO();
        individualResumeDTO.getConditions().add(Restrict.between("entryDate", date));
        List<IndividualResume> individualResumes = super.findByCis(individualResumeDTO);
        if (individualResumes != null && individualResumes.size() > 0) {
            number = individualResumes.size();
        }
        return number;
    }

    //已有个人简介数（在职）
    public Integer existingProfileNum(String[] date) throws SerException {
        Integer number = 0;
        IndividualResumeDTO individualResumeDTO = new IndividualResumeDTO();
        individualResumeDTO.getConditions().add(Restrict.between("updateDate", date));
        individualResumeDTO.getConditions().add(Restrict.eq("staffStatus", StaffStatus.WORKING));
        List<IndividualResume> individualResumes = super.findByCis(individualResumeDTO);
        if (individualResumes != null && individualResumes.size() > 0) {
            number = individualResumes.size();
        }
        return number;
    }

    //离职人数
    public Integer leaveOffice(String[] date) throws SerException {
        return dimissionInfoAPI.getDimissionNum(date);
    }

    //已冻结有个人简介数
    public Integer hasFrozen(String[] date) throws SerException {
        Integer number = 0;
        IndividualResumeDTO individualResumeDTO = new IndividualResumeDTO();
        individualResumeDTO.getConditions().add(Restrict.between("updateDate", date));
        individualResumeDTO.getConditions().add(Restrict.eq("status", Status.CONGEAL));
        List<IndividualResume> individualResumes = super.findByCis(individualResumeDTO);
        if (individualResumes != null && individualResumes.size() > 0) {
            number = individualResumes.size();
        }
        return number;
    }

    //汇总总方法
    public SummationBO totalMethod(String[] date) throws SerException {
        SummationBO summationBO = new SummationBO();
        //可用于招投标的数量
        summationBO.setAvailBiddingNum(availBiddingNum(date));
        //招投标类型简介数量
        summationBO.setBiddingNum(biddingNum(date));
        //入职培训所用简介数量
        summationBO.setOrientationNum(orientationNum(date));
        //公司官网所用简介数量
        summationBO.setOfficialWebsiteNum(officialWebsiteNum(date));
        //招聘所用简介数量
        summationBO.setRecruitNum(recruitNum(date));
        //入职人数
        summationBO.setInductionNum(inductionNum(date));
        //已有个人简介数
        summationBO.setExistingProfileNum(existingProfileNum(date));
        //离职人数
        summationBO.setLeaveOffice(leaveOffice(date));
        //已冻结有个人简介数
        summationBO.setHasFrozen(hasFrozen(date));
        return summationBO;
    }
}