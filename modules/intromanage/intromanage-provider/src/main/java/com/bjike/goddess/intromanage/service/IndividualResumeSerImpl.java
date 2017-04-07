package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.dto.*;
import com.bjike.goddess.intromanage.entity.*;
import com.bjike.goddess.intromanage.to.IndividualDisplayFieldTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private UserAPI userAPI;

    /**
     * 分页查询个人简介
     *
     * @return class IndividualResumeBO
     * @throws SerException
     */
    @Override
    public List<IndividualResumeBO> list(IndividualResumeDTO dto) throws SerException {
        List<IndividualResume> list = super.findByPage(dto);
        UserBO userBO = userAPI.currentUser();
        String currentUsername = userBO.getUsername();//获取当前用户姓名
        List<IndividualDisplayUser> users = individualDisplayUserSer.findAll();
        for (IndividualDisplayUser model : users) {
            String usernames = model.getUsernames();
            boolean containsUsername = StringUtils.isNotBlank(usernames) && (usernames.indexOf(currentUsername) > 0);
            if (containsUsername) {
                String individualDisplayId = model.getDisplayId();
                IndividualDisplayField individualDisplayField = individualDisplayFieldSer.findById(individualDisplayId);
                //设置需要显示的字段
                for (IndividualResume individualResume : list) {
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
                break;
            }
        }
        List<IndividualResumeBO> boList = BeanTransform.copyProperties(list, IndividualResume.class, true);
        return boList;
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
        IndividualResume entity = BeanTransform.copyProperties(to, IndividualResume.class, true);
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
        String[] certificateTitles = to.getCertificateTitles();//获取的专业证书
        String[] certificateTimes = to.getCertificateTimes();  //获取证书的时间
        String[] certificateNos = to.getCertificateNos();      //证书编号
        boolean certificateTitlesNotEmpty = (certificateTitles != null) && (certificateTitles.length > 0);
        if (certificateTitlesNotEmpty) {
            List<CredentialSituation> list = new ArrayList<>(0);
            int len = certificateTitles.length;
            for (int i = 0; i < len; i++) {
                CredentialSituation model = new CredentialSituation();
                model.setCertificateTitle(certificateTitles[i]);
                model.setCertificateTime(certificateTimes[i]);
                model.setCertificateNo(certificateNos[i]);
                model.setStaffId(staffId);
                list.add(model);
            }
            credentialSituationSer.save(list);
        }
    }

    /**
     * 保存工作经历
     *
     * @param to      个人简历to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveWorkExperiences(IndividualResumeTO to, String staffId) throws SerException {
        String[] participatedActivities = to.getParticipatedActivities();//曾经参与的组织与活动
        String[] projectExperiences = to.getProjectExperiences();//项目经历
        boolean participatedActivitiesNotEmpty = (participatedActivities != null) && (participatedActivities.length > 0);
        if (participatedActivitiesNotEmpty) {
            List<WorkExperience> list = new ArrayList<>(0);
            int len = participatedActivities.length;
            for (int i = 0; i < len; i++) {
                WorkExperience model = new WorkExperience();
                model.setParticipatedActivity(participatedActivities[i]);
                model.setProjectExperience(projectExperiences[i]);
                model.setStaffId(staffId);
                list.add(model);
            }
            workExperienceSer.save(list);
        }
    }

    /**
     * 保存教育经历
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveEducateExperiences(IndividualResumeTO to, String staffId) throws SerException {
        String[] educatAddresses = to.getEducatAddresses();//教育地址
        String[] trainingExperiences = to.getTrainingExperiences();//培训经历
        boolean educatAddressesNotEmpty = (educatAddresses != null) && (educatAddresses.length > 0);
        if (educatAddressesNotEmpty) {
            List<EducateExperience> list = new ArrayList<>(0);
            int len = educatAddresses.length;
            for (int i = 0; i < len; i++) {
                EducateExperience model = new EducateExperience();
                model.setEducatAddress(educatAddresses[i]);
                model.setTrainingExperience(trainingExperiences[i]);
                model.setStaffId(staffId);
                list.add(model);
            }
            educateExperienceSer.save(list);
        }
    }

    /**
     * 保存员工荣誉
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveStaffHonors(IndividualResumeTO to, String staffId) throws SerException {
        String[] honorNames = to.getHonorNames();//荣誉名称
        String[] honorGrades = to.getHonorGrades();//荣誉等级
        String[] firmSubsidies = to.getFirmSubsidies();//公司补助
        boolean honorNamesNotEmpty = (honorNames != null) && (honorNames.length > 0);
        if (honorNamesNotEmpty) {
            List<StaffHonor> list = new ArrayList<>(0);
            int len = honorNames.length;
            for (int i = 0; i < len; i++) {
                StaffHonor model = new StaffHonor();
                model.setHonorName(honorNames[i]);
                model.setHonorGrade(honorGrades[i]);
                model.setFirmSubsidy(firmSubsidies[i]);
                model.setStaffId(staffId);
                list.add(model);
            }
            staffHonorSer.save(list);
        }
    }

    /**
     * 保存员工奖励
     *
     * @param to      个人简介to
     * @param staffId 员工id
     * @throws SerException
     */
    private void saveStaffRewards(IndividualResumeTO to, String staffId) throws SerException {
        String[] rewardsNames = to.getRewardsNames();//奖励名称
        String[] prizes = to.getPrizes();//奖品
        String[] bonuses = to.getBonuses();//奖金
        boolean rewardsNamesNotEmpty = (rewardsNames != null) && (rewardsNames.length > 0);
        if (rewardsNamesNotEmpty) {
            List<StaffReward> list = new ArrayList<>(0);
            int len = rewardsNames.length;
            for (int i = 0; i < len; i++) {
                StaffReward model = new StaffReward();
                model.setRewardsName(rewardsNames[i]);
                model.setPrize(prizes[i]);
                model.setBonus(bonuses[i]);
                model.setStaffId(staffId);
                list.add(model);
            }
            staffRewardSer.save(list);
        }
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
        IndividualResume entity = BeanTransform.copyProperties(to, IndividualResume.class, true);
        super.update(entity);
        String staffId = to.getId();//员工id
        removeSubObj(staffId); //删除所有的子对象
        saveSubObj(to, staffId); //保存所有的子对象
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
}