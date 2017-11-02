package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.intromanage.bo.*;
import com.bjike.goddess.intromanage.dto.*;
import com.bjike.goddess.intromanage.entity.*;
import com.bjike.goddess.intromanage.excel.FirmIntroExport;
import com.bjike.goddess.intromanage.excel.FirmIntroExportTemple;
import com.bjike.goddess.intromanage.to.*;
import com.bjike.goddess.intromanage.type.DemandType;
import com.bjike.goddess.intromanage.type.GuideAddrStatus;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    public void seachCond(FirmIntroDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getFirmName())) {
            dto.getConditions().add(Restrict.eq("firmName", dto.getFirmName()));
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
        entity.setStatus(Status.THAW);
        entity = super.save(entity);
        FirmIntroBO bo = BeanTransform.copyProperties(entity, FirmIntroBO.class);
        String firmId = bo.getId();
        saveSubObj(to, firmId);
        String userToken = RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(userToken);
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

    @Override
    public String getDate() throws SerException {
        String sql = "select min(updateDate) as updateDate from " + getTableName(FirmIntro.class);
        List<Object> objects = super.findBySql(sql);
        String startDate = "";
        if (objects != null && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
        }
        return startDate;
    }

    @Override
    public byte[] exportExcel() throws SerException {
        int maxs = 0;
        List<FirmIntro> list = super.findAll();
        List<FirmIntroExport> firmIntroExports = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            Integer seqNum = 1;//序号
            for (FirmIntro firmIntro : list) {
                //查询荣誉与资质
                HonorAndQualityDTO honorAndQualityDTO = new HonorAndQualityDTO();
                honorAndQualityDTO.getConditions().add(Restrict.eq("firmId", firmIntro.getId()));
                List<HonorAndQuality> honorAndQualities = honorAndQualitySer.findByCis(honorAndQualityDTO);
                int h = honorAndQualities == null ? 0 : honorAndQualities.size();
                //查询主业介绍
                MainBusinessIntroDTO mainBusinessIntroDTO = new MainBusinessIntroDTO();
                mainBusinessIntroDTO.getConditions().add(Restrict.eq("firmId", firmIntro.getId()));
                List<MainBusinessIntro> mainBusinessIntros = mainBusinessIntroSer.findByCis(mainBusinessIntroDTO);
                int m = mainBusinessIntros == null ? 0 : mainBusinessIntros.size();
                //查询成功案例
                SuccessStoriesDTO successStoriesDTO = new SuccessStoriesDTO();
                successStoriesDTO.getConditions().add(Restrict.eq("firmId", firmIntro.getId()));
                List<SuccessStories> successStories = successStoriesSer.findByCis(successStoriesDTO);
                int s = successStories == null ? 0 : successStories.size();
                //查询客户及合作伙伴
                CustomerAndPartnerDTO customerAndPartnerDTO = new CustomerAndPartnerDTO();
                successStoriesDTO.getConditions().add(Restrict.eq("firmId", firmIntro.getId()));
                List<CustomerAndPartner> customerAndPartners = customerAndPartnerSer.findByCis(customerAndPartnerDTO);
                int c = customerAndPartners == null ? 0 : customerAndPartners.size();
                //查询通讯途径
                CommunicationPathDTO communicationPathDTO = new CommunicationPathDTO();
                communicationPathDTO.getConditions().add(Restrict.eq("firmId", firmIntro.getId()));
                List<CommunicationPath> communicationPaths = communicationPathSer.findByCis(communicationPathDTO);
                int co = communicationPaths == null ? 0 : communicationPaths.size();
                //判断哪个list是最大的
                List<Integer> integers = new ArrayList<>();
                integers.add(h);
                integers.add(m);
                integers.add(s);
                integers.add(c);
                integers.add(co);
                maxs = integers.stream().max(Comparator.comparing(u -> u)).get();//每一个主表对应的子表的最大集合长度
                maxList.add(maxs);
                for (int i = 0; i < maxs; i++) {
                    FirmIntroExport excel = new FirmIntroExport();
                    HonorAndQuality honorAndQuality = h > i ? honorAndQualities.get(i) : new HonorAndQuality();
                    MainBusinessIntro mainBusinessIntro = m > i ? mainBusinessIntros.get(i) : new MainBusinessIntro();
                    SuccessStories successStories1 = s > i ? successStories.get(i) : new SuccessStories();
                    CustomerAndPartner customerAndPartner = c > i ? customerAndPartners.get(i) : new CustomerAndPartner();
                    CommunicationPath communicationPath = co > i ? communicationPaths.get(i) : new CommunicationPath();
                    BeanTransform.copyProperties(firmIntro, excel);
                    BeanTransform.copyProperties(honorAndQuality, excel);
                    BeanTransform.copyProperties(mainBusinessIntro, excel);
                    BeanTransform.copyProperties(successStories1, excel);
                    BeanTransform.copyProperties(customerAndPartner, excel);
                    BeanTransform.copyProperties(communicationPath, excel);
                    excel.setSeqNum(seqNum);
                    firmIntroExports.add(excel);
                }
                seqNum++;
            }
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(firmIntroExports, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            int rowSize = list.size();
            List<Field> fields = ClazzUtils.getFields(FirmIntroExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            int index =1;
            for (int j = 0; j < rowSize; j++) {
                int mergeRowCount = maxList.get(j);
                if(mergeRowCount!=1){
                    int firstRow = index;
                    int lastRow =0;
                    lastRow = firstRow+mergeRowCount-1;
                    for (int i = 0; i < 20; i++) {
                        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, i, i));
                    }
                    index =lastRow+1;
                }
                index++;
            }

            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return os.toByteArray();
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<FirmIntroExportTemple> firmIntroExportTemples = new ArrayList<>();
        FirmIntroExportTemple firmIntroExportTemple = new FirmIntroExportTemple();
        firmIntroExportTemple.setSeqNum(1);
        firmIntroExportTemple.setFirmName("北京艾佳天城");
        firmIntroExportTemple.setFirmNature("自营");
        firmIntroExportTemple.setRegisterMoney("10000万");
        firmIntroExportTemple.setRegisterDate("2017-01-01");
        firmIntroExportTemple.setFirmSpirit("积极向上");
        firmIntroExportTemple.setServiceAwareness("顾客是上帝");
        firmIntroExportTemple.setFirmTenet("创新");
        firmIntroExportTemple.setTalentView("不错过任何一个人才");
        firmIntroExportTemple.setOperationView("自营");
        firmIntroExportTemple.setQualityView("正品");
        firmIntroExportTemple.setOrganization("平台");
        firmIntroExportTemple.setManageModel("一对一");
        firmIntroExportTemple.setServiceTeamIntro("是一个很好的团体");
        firmIntroExportTemple.setStaffNo("231");
        firmIntroExportTemple.setIncludeArea("2321");
        firmIntroExportTemple.setSolvingScheme("解决方案");
        firmIntroExportTemple.setDemandType(DemandType.BIDDING);
        firmIntroExportTemple.setPositioning("战略定位战略定位战略定位战略定位战略定位战略定位");
        firmIntroExportTemple.setSoftwareCopyright("研发证");
        firmIntroExportTemple.setBusinessType("销售");
        firmIntroExportTemple.setProjectSubject("issp项目科目");
        firmIntroExportTemple.setCommunication("移动");
        firmIntroExportTemple.setSoftware("java issp");
        firmIntroExportTemple.setSystemIntegration("commit");
        firmIntroExportTemple.setMarketingPlanning("营销策划");
        firmIntroExportTemple.setOperators("北京艾佳");
        firmIntroExportTemple.setManufacturer("三星");
        firmIntroExportTemple.setGovernmentUnit("天河政府");
        firmIntroExportTemple.setPartner("三星");
        firmIntroExportTemple.setHeadOfficeAddress("北京");
        firmIntroExportTemple.setHeadOfficeContact("13698765824");
        firmIntroExportTemple.setBranchAddress("广州");
        firmIntroExportTemple.setBranchPhone("16987564533");
        firmIntroExportTemples.add(firmIntroExportTemple);

        FirmIntroExportTemple firmIntroExportTemple1 = new FirmIntroExportTemple();
        firmIntroExportTemple1.setSeqNum(1);
        firmIntroExportTemple1.setFirmName("北京艾佳天城");
        firmIntroExportTemple1.setFirmNature("自营");
        firmIntroExportTemple1.setRegisterMoney("10000万");
        firmIntroExportTemple1.setRegisterDate("2017-01-01");
        firmIntroExportTemple1.setFirmSpirit("积极向上");
        firmIntroExportTemple1.setServiceAwareness("顾客是上帝");
        firmIntroExportTemple1.setFirmTenet("创新");
        firmIntroExportTemple1.setTalentView("不错过任何一个人才");
        firmIntroExportTemple1.setOperationView("自营");
        firmIntroExportTemple1.setQualityView("正品");
        firmIntroExportTemple1.setOrganization("平台");
        firmIntroExportTemple1.setManageModel("一对一");
        firmIntroExportTemple1.setServiceTeamIntro("是一个很好的团体");
        firmIntroExportTemple1.setStaffNo("231");
        firmIntroExportTemple1.setIncludeArea("2321");
        firmIntroExportTemple1.setSolvingScheme("解决方案");
        firmIntroExportTemple1.setDemandType(DemandType.BIDDING);
        firmIntroExportTemple1.setPositioning("战略定位战略定位战略定位战略定位战略定位战略定位");
        firmIntroExportTemple1.setSoftwareCopyright("荣誉证书");
        firmIntroExportTemple1.setBusinessType("策划");
        firmIntroExportTemple1.setProjectSubject("社群联盟科目");
        firmIntroExportTemple1.setCommunication("联通");
        firmIntroExportTemple1.setSoftware("ios issp");
        firmIntroExportTemple1.setSystemIntegration("user");
        firmIntroExportTemple1.setMarketingPlanning("计划计划");
        firmIntroExportTemple1.setOperators("礼尚往来");
        firmIntroExportTemple1.setManufacturer("天秤");
        firmIntroExportTemple1.setGovernmentUnit("白云政府");
        firmIntroExportTemple1.setPartner("ipon");
        firmIntroExportTemple1.setHeadOfficeAddress("佛上");
        firmIntroExportTemple1.setHeadOfficeContact("19564856234");
        firmIntroExportTemple1.setBranchAddress("佛山");
        firmIntroExportTemple1.setBranchPhone("3697564523");
        firmIntroExportTemples.add(firmIntroExportTemple1);

        FirmIntroExportTemple firmIntroExportTemple2 = new FirmIntroExportTemple();
        firmIntroExportTemple2.setSeqNum(1);
        firmIntroExportTemple2.setFirmName("北京艾佳天城");
        firmIntroExportTemple2.setFirmNature("自营");
        firmIntroExportTemple2.setRegisterMoney("10000万");
        firmIntroExportTemple2.setRegisterDate("2017-01-01");
        firmIntroExportTemple2.setFirmSpirit("积极向上");
        firmIntroExportTemple2.setServiceAwareness("顾客是上帝");
        firmIntroExportTemple2.setFirmTenet("创新");
        firmIntroExportTemple2.setTalentView("不错过任何一个人才");
        firmIntroExportTemple2.setOperationView("自营");
        firmIntroExportTemple2.setQualityView("正品");
        firmIntroExportTemple2.setOrganization("平台");
        firmIntroExportTemple2.setManageModel("一对一");
        firmIntroExportTemple2.setServiceTeamIntro("是一个很好的团体");
        firmIntroExportTemple2.setStaffNo("231");
        firmIntroExportTemple2.setIncludeArea("2321");
        firmIntroExportTemple2.setSolvingScheme("解决方案");
        firmIntroExportTemple2.setDemandType(DemandType.BIDDING);
        firmIntroExportTemple2.setPositioning("战略定位战略定位战略定位战略定位战略定位战略定位");
        firmIntroExportTemple2.setSoftwareCopyright("利丰");
        firmIntroExportTemple2.setCommunication("联通");
        firmIntroExportTemple2.setSoftware("ios issp");
        firmIntroExportTemple2.setSystemIntegration("user");
        firmIntroExportTemple2.setMarketingPlanning("计划计划");
        firmIntroExportTemple2.setOperators("礼尚往来");
        firmIntroExportTemple2.setManufacturer("天秤");
        firmIntroExportTemple2.setGovernmentUnit("白云政府");
        firmIntroExportTemple2.setPartner("ipon");
        firmIntroExportTemples.add(firmIntroExportTemple2);

        FirmIntroExportTemple firmIntroExportTemple3 = new FirmIntroExportTemple();
        firmIntroExportTemple3.setSeqNum(2);
        firmIntroExportTemple3.setFirmName("粒上皇制作有限公司");
        firmIntroExportTemple3.setFirmNature("私立的");
        firmIntroExportTemple3.setRegisterMoney("600万");
        firmIntroExportTemple3.setRegisterDate("2013-12-12");
        firmIntroExportTemple3.setFirmSpirit("努力努力");
        firmIntroExportTemple3.setServiceAwareness("认真负责");
        firmIntroExportTemple3.setFirmTenet("取糟粕");
        firmIntroExportTemple3.setTalentView("巴拉巴拉");
        firmIntroExportTemple3.setOperationView("私立私立");
        firmIntroExportTemple3.setQualityView("质量号");
        firmIntroExportTemple3.setOrganization("尚峰形式");
        firmIntroExportTemple3.setManageModel("一对多");
        firmIntroExportTemple3.setServiceTeamIntro("团队能力号");
        firmIntroExportTemple3.setStaffNo("34323");
        firmIntroExportTemple3.setIncludeArea("53234");
        firmIntroExportTemple3.setSolvingScheme("不好解决");
        firmIntroExportTemple3.setDemandType(DemandType.INDUCTION_TRAINING);
        firmIntroExportTemple3.setPositioning("首先然后其次");
        firmIntroExportTemple3.setSoftwareCopyright("荣誉证书");
        firmIntroExportTemple3.setBusinessType("策划");
        firmIntroExportTemple3.setProjectSubject("社群联盟科目");
        firmIntroExportTemple3.setCommunication("联通");
        firmIntroExportTemple3.setSoftware("ios issp");
        firmIntroExportTemple3.setSystemIntegration("user");
        firmIntroExportTemple3.setMarketingPlanning("计划计划");
        firmIntroExportTemple3.setOperators("礼尚往来");
        firmIntroExportTemple3.setManufacturer("天秤");
        firmIntroExportTemple3.setGovernmentUnit("白云政府");
        firmIntroExportTemple3.setPartner("ipon");
        firmIntroExportTemple3.setHeadOfficeAddress("佛上");
        firmIntroExportTemple3.setHeadOfficeContact("19564856234");
        firmIntroExportTemple3.setBranchAddress("佛山");
        firmIntroExportTemple3.setBranchPhone("3697564523");
        firmIntroExportTemples.add(firmIntroExportTemple3);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(firmIntroExportTemples, excel);
        XSSFWorkbook wb = null;
        ByteArrayOutputStream os = null;
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            wb = new XSSFWorkbook(is);
            XSSFSheet sheet;
            sheet = wb.getSheetAt(0);
            List<Field> fields = ClazzUtils.getFields(FirmIntroExport.class); //获得列表对象属性
            List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
            for (int i = 0; i < 19; i++) {
                sheet.addMergedRegion(new CellRangeAddress(1, 3, i, i));
            }


            os = new ByteArrayOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return os.toByteArray();
    }

    @Override
    public void importExcel(List<FirmIntroTO> firmIntroTOS) throws SerException {

    }
}