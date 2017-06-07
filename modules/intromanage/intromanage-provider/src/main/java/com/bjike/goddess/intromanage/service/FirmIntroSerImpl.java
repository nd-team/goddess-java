package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.FirmIntroBO;
import com.bjike.goddess.intromanage.dto.*;
import com.bjike.goddess.intromanage.entity.*;
import com.bjike.goddess.intromanage.to.FirmDisplayFieldTO;
import com.bjike.goddess.intromanage.to.FirmIntroTO;
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

/**
 * 公司简介业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class FirmIntroSerImpl extends ServiceImpl<FirmIntro, FirmIntroDTO> implements FirmIntroSer {

    @Autowired
    private HonorAndQualitySer honorAndQualitySer;

    @Autowired
    private MainBusinessIntroSer mainBusinessIntroSer;

    @Autowired
    private SuccessStoriesSer successStoriesSer;

    @Autowired
    private CustomerAndPartnerSer customerAndPartnerSer;

    @Autowired
    private CommunicationPathSer communicationPathSer;

    @Autowired
    private FirmDisplayFieldSer firmDisplayFieldSer;

    @Autowired
    private FirmDisplayUserSer firmDisplayUserSer;

    @Autowired
    private UserAPI userAPI;


    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntro
     * @throws SerException
     */
    @Override
    public FirmIntro findById(String id) throws SerException {
        FirmIntro firmIntro = super.findById(id);
        UserBO userBO = userAPI.currentUser();
        String currentUsername = userBO.getUsername();//获取当前用户姓名
        List<FirmDisplayUser> users = firmDisplayUserSer.findAll();
        for (FirmDisplayUser model : users) {
            String usernames = model.getUsernames();
            boolean containsUsername = StringUtils.isNotBlank(usernames) && (usernames.indexOf(currentUsername) >= 0);
            if (containsUsername) {
                String firmDisplayId = model.getDisplayId();
                FirmDisplayField firmDisplayField = firmDisplayFieldSer.findById(firmDisplayId);
                //设置需要显示的字段
                checkShowFields(firmDisplayField, firmIntro);
                break;
            }
        }

        return firmIntro;
    }

    /**
     * 分页查询公司简介
     *
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    public List<FirmIntroBO> list(FirmIntroDTO dto) throws SerException {
        List<FirmIntro> list = super.findByPage(dto);
        UserBO userBO = userAPI.currentUser();
        String currentUsername = userBO.getUsername();//获取当前用户姓名
        List<FirmDisplayUser> users = firmDisplayUserSer.findAll();
        for (FirmDisplayUser model : users) {
            String usernames = model.getUsernames();
            boolean containsUsername = StringUtils.isNotBlank(usernames) && (usernames.indexOf(currentUsername) >= 0);
            if (containsUsername) {
                String firmDisplayId = model.getDisplayId();
                FirmDisplayField firmDisplayField = firmDisplayFieldSer.findById(firmDisplayId);
                //设置需要显示的字段
                for (FirmIntro firmIntro : list) {
                    checkShowFields(firmDisplayField, firmIntro);
                }
                break;
            }
        }

        List<FirmIntroBO> boList = BeanTransform.copyProperties(list, FirmIntroBO.class);
        return boList;
    }

    /**
     * 检查是否显示字段
     *
     * @param firmDisplayField 公司是否显示字段
     * @param firmIntro 公司简介信息
     */
    private void checkShowFields(FirmDisplayField firmDisplayField, FirmIntro firmIntro) {
        if (firmDisplayField.getIfShowFirmName() != Boolean.TRUE) {
            firmIntro.setFirmName(null);
        }
        if (firmDisplayField.getIfShowFirmNature() != Boolean.TRUE) {
            firmIntro.setFirmNature(null);
        }
        if (firmDisplayField.getIfShowRegisterMoney() != Boolean.TRUE) {
            firmIntro.setRegisterMoney(null);
        }
        if (firmDisplayField.getIfShowRegisterDate() != Boolean.TRUE) {
            firmIntro.setRegisterDate(null);
        }
        if (firmDisplayField.getIfShowFirmSpirit() != Boolean.TRUE) {
            firmIntro.setFirmSpirit(null);
        }
        if (firmDisplayField.getIfShowServiceAwareness() != Boolean.TRUE) {
            firmIntro.setServiceAwareness(null);
        }
        if (firmDisplayField.getIfShowFirmTenet() != Boolean.TRUE) {
            firmIntro.setFirmTenet(null);
        }
        if (firmDisplayField.getIfShowTalentView() != Boolean.TRUE) {
            firmIntro.setTalentView(null);
        }
        if (firmDisplayField.getIfShowOperationView() != Boolean.TRUE) {
            firmIntro.setOperationView(null);
        }
        if (firmDisplayField.getIfShowQualityView() != Boolean.TRUE) {
            firmIntro.setQualityView(null);
        }
        if (firmDisplayField.getIfShowOrganization() != Boolean.TRUE) {
            firmIntro.setOrganization(null);
        }
        if (firmDisplayField.getIfShowManageModel() != Boolean.TRUE) {
            firmIntro.setManageModel(null);
        }
        if (firmDisplayField.getIfShowServiceTeamIntro() != Boolean.TRUE) {
            firmIntro.setServiceTeamIntro(null);
        }
        if (firmDisplayField.getIfShowStaffNo() != Boolean.TRUE) {
            firmIntro.setStaffNo(null);
        }
        if (firmDisplayField.getIfShowIncludeArea() != Boolean.TRUE) {
            firmIntro.setIncludeArea(null);
        }
        if (firmDisplayField.getIfShowSolvingScheme() != Boolean.TRUE) {
            firmIntro.setSolvingScheme(null);
        }
        if (firmDisplayField.getIfShowDemandType() != Boolean.TRUE) {
            firmIntro.setDemandType(null);
        }
    }

    /**
     * 保存公司简介
     *
     * @param to 公司简介to
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FirmIntroBO save(FirmIntroTO to) throws SerException {
        FirmIntro entity = BeanTransform.copyProperties(to, FirmIntro.class, true);
        entity = super.save(entity);
        FirmIntroBO bo = BeanTransform.copyProperties(entity, FirmIntroBO.class);
        String firmId = bo.getId();
        saveSubObj(to, firmId);
        return bo;
    }

    /**
     * 保存所有的子对象
     *
     * @param to     公司简介to
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void saveSubObj(FirmIntroTO to, String firmId) throws SerException {
        saveHonorAndQualities(to, firmId);//保存荣誉与资质
        saveMainBusinessIntros(to, firmId);//保存主业介绍
        saveSuccessStories(to, firmId);//保存成功案例
        saveCustomerAndPartners(to, firmId);//保存客户及合作伙伴
        saveCommunicationPaths(to, firmId); //保存通讯途径
    }

    /**
     * 保存通讯途径
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveCommunicationPaths(FirmIntroTO to, String firmId) throws SerException {
        String[] headOfficeAddresses = to.getHeadOfficeAddresses();//获取总公司地址
        String[] headOfficeContactes = to.getHeadOfficeContactes();//获取总公司联系方式
        String[] branchAddresses = to.getBranchAddresses();//获取分公司地址
        String[] branchPhones = to.getBranchPhones();//获取分公司联系方式
        boolean headOfficeAddressesNotEmpty = (headOfficeAddresses != null) && (headOfficeAddresses.length > 0);
        if (headOfficeAddressesNotEmpty) {
            List<CommunicationPath> list = new ArrayList<>(0);
            int len = headOfficeAddresses.length;
            for (int i = 0; i < len; i++) {
                CommunicationPath model = new CommunicationPath();
                model.setHeadOfficeAddress(headOfficeAddresses[i]);
                model.setHeadOfficeContact(headOfficeContactes[i]);
                model.setBranchAddress(branchAddresses[i]);
                model.setBranchPhone(branchPhones[i]);
                model.setFirmId(firmId);
                list.add(model);
            }
            communicationPathSer.save(list);
        }
    }

    /**
     * 保存客户及合作伙伴
     *
     * @param to     公司简介
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveCustomerAndPartners(FirmIntroTO to, String firmId) throws SerException {
        String[] operators = to.getOperators();//运营商
        String[] manufacturers = to.getManufacturers();//厂家
        String[] governmentUnits = to.getGovernmentUnits();//各政府单位
        String[] partners = to.getPartners();//合作伙伴
        boolean operatorsNotEmpty = (operators != null) && (operators.length > 0);
        if (operatorsNotEmpty) {
            List<CustomerAndPartner> list = new ArrayList<>(0);
            int len = operators.length;
            for (int i = 0; i < len; i++) {
                CustomerAndPartner model = new CustomerAndPartner();
                model.setOperators(operators[i]);
                model.setManufacturer(manufacturers[i]);
                model.setGovernmentUnit(governmentUnits[i]);
                model.setPartner(partners[i]);
                model.setFirmId(firmId);
                list.add(model);
            }
            customerAndPartnerSer.save(list);
        }
    }

    /**
     * 保存成功案例
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveSuccessStories(FirmIntroTO to, String firmId) throws SerException {
        String[] communications = to.getCommunications();//通信类
        String[] softwares = to.getSoftwares();//软件类
        String[] systemIntegrations = to.getSystemIntegrations();//系统集成类
        String[] marketingPlannings = to.getMarketingPlannings();//营销策划类
        boolean communicationsNotEmpty = (communications != null) && (communications.length > 0);
        if (communicationsNotEmpty) {
            List<SuccessStories> list = new ArrayList<>(0);
            int len = communications.length;
            for (int i = 0; i < len; i++) {
                SuccessStories model = new SuccessStories();
                model.setCommunication(communications[i]);
                model.setSoftware(softwares[i]);
                model.setSystemIntegration(systemIntegrations[i]);
                model.setMarketingPlanning(marketingPlannings[i]);
                model.setFirmId(firmId);
                list.add(model);
            }
            successStoriesSer.save(list);
        }
    }

    /**
     * 保存主业介绍
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveMainBusinessIntros(FirmIntroTO to, String firmId) throws SerException {
        String[] businessTypes = to.getBusinessTypes();//获取业务类型
        String[] projectSubjects = to.getProjectSubjects();//获取项目科目
        boolean businessTypesNotEmpty = (businessTypes != null) && (businessTypes.length > 0);
        if (businessTypesNotEmpty) {
            List<MainBusinessIntro> list = new ArrayList<>(0);
            int len = businessTypes.length;
            for (int i = 0; i < len; i++) {
                MainBusinessIntro model = new MainBusinessIntro();
                model.setBusinessType(businessTypes[i]);
                model.setProjectSubject(projectSubjects[i]);
                model.setFirmId(firmId);
                list.add(model);
            }
            mainBusinessIntroSer.save(list);
        }
    }

    /**
     * 保存荣誉与资质
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveHonorAndQualities(FirmIntroTO to, String firmId) throws SerException {
        String[] certificates = to.getCertificates();
        String[] softwareCopyrights = to.getSoftwareCopyrights();
        boolean certificatesNotEmpty = (certificates != null) && (certificates.length > 0);
        if (certificatesNotEmpty) {
            List<HonorAndQuality> list = new ArrayList<>(0);
            int len = certificates.length;
            for (int i = 0; i < len; i++) {
                HonorAndQuality model = new HonorAndQuality();//荣誉与资质
                model.setCertificates(certificates[i]);
                model.setSoftwareCopyright(softwareCopyrights[i]);
                model.setFirmId(firmId);//设置逻辑外键
                list.add(model);
            }
            honorAndQualitySer.save(list);
        }
    }

    /**
     * 更新公司简介
     *
     * @param to 公司简介to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(FirmIntroTO to) throws SerException {
        String firmId = to.getId();
        if (StringUtils.isNotEmpty(firmId)) {
            FirmIntro model = super.findById(firmId);
            if (model != null) {
                updateFirmIntro(to, model);
                removeSubObj(firmId);//删除所有与公司简介相关的子对象
                saveSubObj(to, firmId);//保存子对象
            } else {
                throw new SerException("您好,更新对象为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新公司简介信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateFirmIntro(FirmIntroTO to, FirmIntro model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除所有的子对象
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeSubObj(String firmId) throws SerException {
        removeHonorAndQualities(firmId);//删除荣誉与资质
        removeMainBusinessIntro(firmId);//删除主业介绍
        removeSuccessStories(firmId);   //删除成功案例
        removeCustomerAndPartners(firmId);//删除客户及合作伙伴
        removeCommunicationPaths(firmId); //删除通讯途径
    }

    /**
     * 删除通讯途径
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeCommunicationPaths(String firmId) throws SerException {
        CommunicationPathDTO dto = new CommunicationPathDTO();
        dto.getConditions().add(Restrict.eq("firmId", firmId));
        List<CommunicationPath> list = communicationPathSer.findByCis(dto);
        communicationPathSer.remove(list);
    }

    /**
     * 删除客户及合作伙伴
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeCustomerAndPartners(String firmId) throws SerException {
        CustomerAndPartnerDTO dto = new CustomerAndPartnerDTO();
        dto.getConditions().add(Restrict.eq("firmId", firmId));
        List<CustomerAndPartner> list = customerAndPartnerSer.findByCis(dto);
        customerAndPartnerSer.remove(list);
    }

    /**
     * 删除成功案例
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeSuccessStories(String firmId) throws SerException {
        SuccessStoriesDTO dto = new SuccessStoriesDTO();
        dto.getConditions().add(Restrict.eq("firmId", firmId));
        List<SuccessStories> list = successStoriesSer.findByCis(dto);
        successStoriesSer.remove(list);
    }

    /**
     * 删除主业介绍
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeMainBusinessIntro(String firmId) throws SerException {
        MainBusinessIntroDTO dto = new MainBusinessIntroDTO();
        dto.getConditions().add(Restrict.eq("firmId", firmId));
        List<MainBusinessIntro> list = mainBusinessIntroSer.findByCis(dto);
        mainBusinessIntroSer.remove(list);
    }

    /**
     * 删除荣誉与资质
     *
     * @param firmId 公司简介记录id
     * @throws SerException
     */
    private void removeHonorAndQualities(String firmId) throws SerException {
        HonorAndQualityDTO dto = new HonorAndQualityDTO();
        dto.getConditions().add(Restrict.eq("firmId", firmId));
        List<HonorAndQuality> list = honorAndQualitySer.findByCis(dto);
        honorAndQualitySer.remove(list);//删除所有的荣誉与资质
    }

    /**
     * 根据id删除公司简介
     *
     * @param id 公司简介唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(String id) throws SerException {
        removeSubObj(id);//删除所有子对象
        super.remove(id);//删除公司简介
    }

    /**
     * 设置哪些用户可以查看哪些字段
     *
     * @param username 用户名集合
     * @param to       公司简介需要显示的字段
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void setFirmDisplayField(String[] username, FirmDisplayFieldTO to) throws SerException {
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

            saveFirmDisplayField(to, sb);
        } else {
            throw new SerException("您好,用户姓名为空,无法设置公司简介显示的字段");
        }
    }

    /**
     * 保存公司简介显示字段和显示的用户
     *
     * @param to 公司简介显示字段to
     * @param sb 用户名称集合
     * @throws SerException
     */
    private void saveFirmDisplayField(FirmDisplayFieldTO to, StringBuilder sb) throws SerException {
        FirmDisplayField model = BeanTransform.copyProperties(to, FirmDisplayField.class, true);
        model = firmDisplayFieldSer.save(model);//保存公司简介显示字段
        String displayFieldId = model.getId();//获取公司显示字段ID
        String usernameStr = sb.toString();//用户名字符串集合
        FirmDisplayUser firmDisplayUser = new FirmDisplayUser();
        firmDisplayUser.setUsernames(usernameStr);
        firmDisplayUser.setDisplayId(displayFieldId);
        firmDisplayUserSer.save(firmDisplayUser);
    }
}