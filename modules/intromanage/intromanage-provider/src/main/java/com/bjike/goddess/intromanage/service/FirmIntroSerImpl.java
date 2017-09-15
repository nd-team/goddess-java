package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.*;
import com.bjike.goddess.intromanage.dto.*;
import com.bjike.goddess.intromanage.entity.*;
import com.bjike.goddess.intromanage.to.*;
import com.bjike.goddess.intromanage.type.GuideAddrStatus;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 根据id查询公司简介
     *
     * @param id 公司简介唯一标识
     * @return class FirmIntro
     * @throws SerException
     */
    @Override
    public FirmIntroBO findByFirmId(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
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

        //查询荣誉与资质
        HonorAndQualityDTO honorAndQualityDTO = new HonorAndQualityDTO();
        honorAndQualityDTO.getConditions().add(Restrict.eq("firmId", id));
        List<HonorAndQuality> honorAndQualities = honorAndQualitySer.findByCis(honorAndQualityDTO);
        List<HonorAndQualityBO> honorAndQualitieBOs = BeanTransform.copyProperties(honorAndQualities, HonorAndQualityBO.class);
        //查询主业介绍
        MainBusinessIntroDTO mainBusinessIntroDTO = new MainBusinessIntroDTO();
        mainBusinessIntroDTO.getConditions().add(Restrict.eq("firmId", id));
        List<MainBusinessIntro> mainBusinessIntros = mainBusinessIntroSer.findByCis(mainBusinessIntroDTO);
        List<MainBusinessIntroBO> mainBusinessIntroBOS = BeanTransform.copyProperties(mainBusinessIntros, MainBusinessIntroBO.class);
        //查询成功案例
        SuccessStoriesDTO successStoriesDTO = new SuccessStoriesDTO();
        successStoriesDTO.getConditions().add(Restrict.eq("firmId", id));
        List<SuccessStories> successStories = successStoriesSer.findByCis(successStoriesDTO);
        List<SuccessStoriesBO> successStoriesBOS = BeanTransform.copyProperties(successStories, SuccessStoriesBO.class);
        //查询客户及合作伙伴
        CustomerAndPartnerDTO customerAndPartnerDTO = new CustomerAndPartnerDTO();
        successStoriesDTO.getConditions().add(Restrict.eq("firmId", id));
        List<CustomerAndPartner> customerAndPartners = customerAndPartnerSer.findByCis(customerAndPartnerDTO);
        List<CustomerAndPartnerBO> customerAndPartnerBOS = BeanTransform.copyProperties(customerAndPartners, CustomerAndPartnerBO.class);
        //查询通讯途径
        CommunicationPathDTO communicationPathDTO = new CommunicationPathDTO();
        communicationPathDTO.getConditions().add(Restrict.eq("firmId", id));
        List<CommunicationPath> communicationPaths = communicationPathSer.findByCis(communicationPathDTO);
        List<CommunicationPathBO> communicationPathBOS = BeanTransform.copyProperties(communicationPaths, CommunicationPathBO.class);

        FirmIntroBO firmIntroBO = BeanTransform.copyProperties(firmIntro, FirmIntroBO.class);
        firmIntroBO.setHonorAndQualityBOS(honorAndQualitieBOs);
        firmIntroBO.setMainBusinessIntroBOS(mainBusinessIntroBOS);
        firmIntroBO.setSuccessStoriesBOS(successStoriesBOS);
        firmIntroBO.setCustomerAndPartnerBOS(customerAndPartnerBOS);
        firmIntroBO.setCommunicationPathBOS(communicationPathBOS);
        return firmIntroBO;
    }

    /**
     * 分页查询公司简介
     *
     * @return class FirmIntroBO
     * @throws SerException
     */
    @Override
    public List<FirmIntroBO> list(FirmIntroDTO dto) throws SerException {
        checkPermission();
        seachCond(dto);
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

    public void seachCond(FirmIntroDTO dto) throws SerException{
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        if(StringUtils.isNotBlank(dto.getFirmName())){
            firmIntroDTO.getConditions().add(Restrict.eq("firmName",dto.getFirmName()));
        }
    }

    /**
     * 检查是否显示字段
     *
     * @param firmDisplayField 公司是否显示字段
     * @param firmIntro        公司简介信息
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
        checkPermission();
        FirmIntro entity = BeanTransform.copyProperties(to, FirmIntro.class, true);
        entity.setUpdateDate(LocalDate.now());
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
        List<CommunicationPathTO> communicationPathTOS = to.getCommunicationPathTOS();
        List<CommunicationPath> list = new ArrayList<>(0);
        if (communicationPathTOS != null && communicationPathTOS.size() > 0) {
            for (CommunicationPathTO temp : communicationPathTOS) {
                CommunicationPath model = new CommunicationPath();
                model.setHeadOfficeAddress(temp.getHeadOfficeAddress());
                model.setHeadOfficeContact(temp.getHeadOfficeContact());
                model.setBranchAddress(temp.getBranchAddress());
                model.setBranchPhone(temp.getBranchPhone());
                model.setFirmId(firmId);
                list.add(model);
            }

            communicationPathSer.save(list);
        }

//        String[] headOfficeAddresses = to.getHeadOfficeAddresses();//获取总公司地址
//        String[] headOfficeContactes = to.getHeadOfficeContactes();//获取总公司联系方式
//        String[] branchAddresses = to.getBranchAddresses();//获取分公司地址
//        String[] branchPhones = to.getBranchPhones();//获取分公司联系方式
//        boolean headOfficeAddressesNotEmpty = (headOfficeAddresses != null) && (headOfficeAddresses.length > 0);
//        if (headOfficeAddressesNotEmpty) {
//            List<CommunicationPath> list = new ArrayList<>(0);
//            int len = headOfficeAddresses.length;
//            for (int i = 0; i < len; i++) {
//                CommunicationPath model = new CommunicationPath();
//                model.setHeadOfficeAddress(headOfficeAddresses[i]);
//                model.setHeadOfficeContact(headOfficeContactes[i]);
//                model.setBranchAddress(branchAddresses[i]);
//                model.setBranchPhone(branchPhones[i]);
//                model.setFirmId(firmId);
//                list.add(model);
//            }
//            communicationPathSer.save(list);
//        }
    }

    /**
     * 保存客户及合作伙伴
     *
     * @param to     公司简介
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveCustomerAndPartners(FirmIntroTO to, String firmId) throws SerException {
        List<CustomerAndPartnerTO> customerAndPartnerTOS = to.getCustomerAndPartnerTOS();
        List<CustomerAndPartner> list = new ArrayList<>(0);
        if (customerAndPartnerTOS != null && customerAndPartnerTOS.size() > 0) {
            for (CustomerAndPartnerTO temp : customerAndPartnerTOS) {
                CustomerAndPartner model = new CustomerAndPartner();
                model.setOperators(temp.getOperators());
                model.setManufacturer(temp.getManufacturer());
                model.setGovernmentUnit(temp.getGovernmentUnit());
                model.setPartner(temp.getPartner());
                model.setFirmId(firmId);
                list.add(model);
            }

            customerAndPartnerSer.save(list);
        }

//        String[] operators = to.getOperators();//运营商
//        String[] manufacturers = to.getManufacturers();//厂家
//        String[] governmentUnits = to.getGovernmentUnits();//各政府单位
//        String[] partners = to.getPartners();//合作伙伴
//        boolean operatorsNotEmpty = (operators != null) && (operators.length > 0);
//        if (operatorsNotEmpty) {
//            List<CustomerAndPartner> list = new ArrayList<>(0);
//            int len = operators.length;
//            for (int i = 0; i < len; i++) {
//                CustomerAndPartner model = new CustomerAndPartner();
//                model.setOperators(operators[i]);
//                model.setManufacturer(manufacturers[i]);
//                model.setGovernmentUnit(governmentUnits[i]);
//                model.setPartner(partners[i]);
//                model.setFirmId(firmId);
//                list.add(model);
//            }
//            customerAndPartnerSer.save(list);
//        }
    }

    /**
     * 保存成功案例
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveSuccessStories(FirmIntroTO to, String firmId) throws SerException {
        List<SuccessStoriesTO> successStoriesTOS = to.getSuccessStoriesTOS();
        List<SuccessStories> list = new ArrayList<>(0);
        if (successStoriesTOS != null && successStoriesTOS.size() > 0) {
            for (SuccessStoriesTO temp : successStoriesTOS) {
                SuccessStories model = new SuccessStories();
                model.setCommunication(temp.getCommunication());
                model.setSoftware(temp.getSoftware());
                model.setSystemIntegration(temp.getSystemIntegration());
                model.setMarketingPlanning(temp.getMarketingPlanning());
                model.setFirmId(firmId);
                list.add(model);
            }

            successStoriesSer.save(list);
        }

//        String[] communications = to.getCommunications();//通信类
//        String[] softwares = to.getSoftwares();//软件类
//        String[] systemIntegrations = to.getSystemIntegrations();//系统集成类
//        String[] marketingPlannings = to.getMarketingPlannings();//营销策划类
//        boolean communicationsNotEmpty = (communications != null) && (communications.length > 0);
//        if (communicationsNotEmpty) {
//            List<SuccessStories> list = new ArrayList<>(0);
//            int len = communications.length;
//            for (int i = 0; i < len; i++) {
//                SuccessStories model = new SuccessStories();
//                model.setCommunication(communications[i]);
//                model.setSoftware(softwares[i]);
//                model.setSystemIntegration(systemIntegrations[i]);
//                model.setMarketingPlanning(marketingPlannings[i]);
//                model.setFirmId(firmId);
//                list.add(model);
//            }
//            successStoriesSer.save(list);
//        }
    }

    /**
     * 保存主业介绍
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveMainBusinessIntros(FirmIntroTO to, String firmId) throws SerException {
        List<MainBusinessIntroTO> mainBusinessIntroTOS = to.getMainBusinessIntroTOS();
        List<MainBusinessIntro> list = new ArrayList<>(0);
        if (mainBusinessIntroTOS != null && mainBusinessIntroTOS.size() > 0) {
            for (MainBusinessIntroTO temp : mainBusinessIntroTOS) {
                MainBusinessIntro model = new MainBusinessIntro();
                model.setBusinessType(temp.getBusinessType());
                model.setProjectSubject(temp.getProjectSubject());
                model.setFirmId(firmId);
                list.add(model);
            }

            mainBusinessIntroSer.save(list);
        }

//        String[] businessTypes = to.getBusinessTypes();//获取业务类型
//        String[] projectSubjects = to.getProjectSubjects();//获取项目科目
//        boolean businessTypesNotEmpty = (businessTypes != null) && (businessTypes.length > 0);
//        if (businessTypesNotEmpty) {
//            List<MainBusinessIntro> list = new ArrayList<>(0);
//            int len = businessTypes.length;
//            for (int i = 0; i < len; i++) {
//                MainBusinessIntro model = new MainBusinessIntro();
//                model.setBusinessType(businessTypes[i]);
//                model.setProjectSubject(projectSubjects[i]);
//                model.setFirmId(firmId);
//                list.add(model);
//            }
//            mainBusinessIntroSer.save(list);
//        }
    }

    /**
     * 保存荣誉与资质
     *
     * @param to     公司简介to
     * @param firmId 公司记录id
     * @throws SerException
     */
    private void saveHonorAndQualities(FirmIntroTO to, String firmId) throws SerException {
        List<HonorAndQualityTO> honorAndQualityTOS = to.getHonorAndQualityTOS();
        List<HonorAndQuality> list = new ArrayList<>(0);
        if (honorAndQualityTOS != null && honorAndQualityTOS.size() > 0) {
            for (HonorAndQualityTO temp : honorAndQualityTOS) {
                HonorAndQuality model = new HonorAndQuality();//荣誉与资质
                model.setCertificates(temp.getCertificates());
                model.setSoftwareCopyright(temp.getSoftwareCopyright());
                model.setFirmId(firmId);//设置逻辑外键
                list.add(model);
            }

            honorAndQualitySer.save(list);
        }
//        String[] certificates = to.getCertificates();
//        String[] softwareCopyrights = to.getSoftwareCopyrights();
//        boolean certificatesNotEmpty = (certificates != null) && (certificates.length > 0);
//        if (certificatesNotEmpty) {
//            List<HonorAndQuality> list = new ArrayList<>(0);
//            int len = certificates.length;
//            for (int i = 0; i < len; i++) {
//                HonorAndQuality model = new HonorAndQuality();//荣誉与资质
//                model.setCertificates(certificates[i]);
//                model.setSoftwareCopyright(softwareCopyrights[i]);
//                model.setFirmId(firmId);//设置逻辑外键
//                list.add(model);
//            }
//            honorAndQualitySer.save(list);
//        }
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
        checkPermission();
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
        checkPermission();
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

    @Override
    public List<String> findallMonUser() throws SerException {
        List<UserBO> userBOS = positionDetailUserAPI.findUserList();
        if (CollectionUtils.isEmpty(userBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (UserBO userBO : userBOS) {
            String userName = userBO.getUsername();
            if (StringUtils.isNotBlank(userBO.getUsername())) {
                set.add(userName);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    //chenjunhao
    public Set<String> firmNames() throws SerException {
        List<FirmIntro> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (FirmIntro f : list) {
            set.add(f.getFirmName());
        }
        return set;
    }

    @Override
    //chenjunhao
    public Set<BussinesBO> moneyByName(String name) throws SerException {
        FirmIntroDTO firmIntroDTO = new FirmIntroDTO();
        firmIntroDTO.getConditions().add(Restrict.eq("firmName", name));
        List<FirmIntro> list = super.findByCis(firmIntroDTO);
        Set<BussinesBO> set = new HashSet<>();
        for (FirmIntro firmIntro : list) {
            BussinesBO bussinesBO = new BussinesBO();
            bussinesBO.setRegisterMoney(firmIntro.getRegisterMoney());
            bussinesBO.setStaffNo(firmIntro.getStaffNo());
            set.add(bussinesBO);
        }
        return set;
    }

    @Override
    public void congealFirmin(String id) throws SerException {
        FirmIntro firmIntro = super.findById(id);
        firmIntro.setStatus(Status.CONGEAL);
        super.update(firmIntro);
    }

    @Override
    public void thawFirmin(String id) throws SerException {
        FirmIntro firmIntro = super.findById(id);
        firmIntro.setStatus(Status.THAW);
        super.update(firmIntro);
    }
}