package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.*;
import com.bjike.goddess.capability.entity.*;
import com.bjike.goddess.capability.enums.GuideAddrStatus;
import com.bjike.goddess.capability.excele.CompanyCapabilityExcele;
import com.bjike.goddess.capability.excele.CompanyCapabilityTemplateExcele;
import com.bjike.goddess.capability.excele.SonPermissionObject;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司能力展示业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CompanyCapabilitySerImpl extends ServiceImpl<CompanyCapability, CompanyCapabilityDTO> implements CompanyCapabilitySer {


    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ManageAuthenSer manageAuthenSer;
    @Autowired
    private ProfessionAuthenSer professionAuthenSer;
    @Autowired
    private CompanyProjectSer companyProjectSer;
    @Autowired
    private CompanyCertificateSer companyCertificateSer;
    @Autowired
    private InProjctSer inProjctSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SelfCapabilitySer selfCapabilitySer;
    @Autowired
    private CooperCapabilitySer cooperCapabilitySer;
    @Autowired
    private CollectEmailSer collectEmailSer;
    @Autowired
    private CooperateSer cooperateSer;

    @Override
    public Long counts(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        if (StringUtils.isNotBlank(companyCapabilityDTO.getCompany())) {
            companyCapabilityDTO.getConditions().add(Restrict.like("company", companyCapabilityDTO.getCompany()));
        }
        Long count = super.count(companyCapabilityDTO);
        return count;
    }

    @Override
    public CompanyCapabilityBO getOne(String id) throws SerException {


        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        CompanyCapability selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, CompanyCapabilityBO.class);

    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您的帐号没有权限");
        }

        companyCapabilityDTO.getSorts().add("createTime=desc");
        if (StringUtils.isNotBlank(companyCapabilityDTO.getCompany())) {
            companyCapabilityDTO.getConditions().add(Restrict.like("company", companyCapabilityDTO.getCompany()));
        }
        List<CompanyCapability> list = super.findByPage(companyCapabilityDTO);

        String[] manageAuthens = null;
        String[] professionAuthens = null;
        String[] companyCertificates = null;
        String[] inProjcts = null;
        String[] companyProjects = null;
        String[] cooperates = null;

        List<CompanyCapabilityBO> allList = new ArrayList<>();

        for (CompanyCapability module : list) {

            List<String> manageAuthenList1 = new ArrayList<String>();
            List<String> professionAuthenList1 = new ArrayList<>();
            List<String> companyCertificateList1 = new ArrayList<>();
            List<String> inProjctList1 = new ArrayList<>();
            List<String> companyProjectList1 = new ArrayList<>();
            List<String> cooperateList1 = new ArrayList<>();


            ManageAuthenDTO manageAuthenDTO = new ManageAuthenDTO();
            ProfessionAuthenDTO professionAuthenDTO = new ProfessionAuthenDTO();
            CompanyCertificateDTO companyCertificateDTO = new CompanyCertificateDTO();
            InProjctDTO inProjctDTO = new InProjctDTO();
            CompanyProjectDTO companyProjectDTO = new CompanyProjectDTO();
            CooperateDTO cooperateDTO = new CooperateDTO();

            manageAuthenDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            professionAuthenDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            companyCertificateDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            inProjctDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            companyProjectDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            cooperateDTO.getConditions().add(Restrict.eq("baseId", module.getId()));

            List<ManageAuthen> manageAuthenList = manageAuthenSer.findByCis(manageAuthenDTO);
            List<ProfessionAuthen> professionAuthenList = professionAuthenSer.findByCis(professionAuthenDTO);
            List<CompanyCertificate> companyCertificateList = companyCertificateSer.findByCis(companyCertificateDTO);
            List<InProjct> inProjctList = inProjctSer.findByCis(inProjctDTO);
            List<CompanyProject> companyProjectList = companyProjectSer.findByCis(companyProjectDTO);
            List<Cooperate> cooperateList = cooperateSer.findByCis(cooperateDTO);

            for (ManageAuthen manageAuthen : manageAuthenList) {

                manageAuthenList1.add(manageAuthen.getName());
            }
            for (ProfessionAuthen professionAuthen : professionAuthenList) {
                professionAuthenList1.add(professionAuthen.getName());
            }
            for (CompanyCertificate companyCertificate : companyCertificateList) {
                companyCertificateList1.add(companyCertificate.getName());
            }
            for (InProjct inProjct : inProjctList) {
                inProjctList1.add(inProjct.getName());
            }
            for (CompanyProject companyProject : companyProjectList) {
                companyProjectList1.add(companyProject.getName());
            }
            for (Cooperate cooperate : cooperateList) {
                cooperateList1.add(cooperate.getName());
            }

            manageAuthens = (String[]) manageAuthenList1.toArray(new String[manageAuthenList1.size()]);
            professionAuthens = (String[]) professionAuthenList1.toArray(new String[professionAuthenList1.size()]);
            companyCertificates = (String[]) companyCertificateList1.toArray(new String[companyCertificateList1.size()]);
            inProjcts = (String[]) inProjctList1.toArray(new String[inProjctList1.size()]);
            companyProjects = (String[]) companyProjectList1.toArray(new String[companyProjectList1.size()]);
            cooperates = (String[]) cooperateList1.toArray(new String[cooperateList1.size()]);

            CompanyCapabilityBO companyCapabilityBO = BeanTransform.copyProperties(module, CompanyCapabilityBO.class);

            companyCapabilityBO.setManageAuthen(String.join(",", manageAuthens));
            companyCapabilityBO.setProfessionAuthen(String.join(",", professionAuthens));
            companyCapabilityBO.setCompanyCertificate(String.join(",", companyCertificates));
            companyCapabilityBO.setInProjct(String.join(",", inProjcts));
            companyCapabilityBO.setCompanyProject(String.join(",", companyProjects));
            companyCapabilityBO.setCooperate(String.join(",", cooperates));

            allList.add(companyCapabilityBO);
        }
//        manageAuthens =(String[]) manageAuthenList1.toArray(new String[manageAuthenList1.size()]);
//        professionAuthens = (String[])professionAuthenList1.toArray(new String[professionAuthenList1.size()]);
//        companyCertificates =  (String[])companyCertificateList1.toArray(new String[companyCertificateList1.size()]);
//        inProjcts = (String[])inProjctList1.toArray(new String[inProjctList1.size()]);
//        companyProjects =  (String[])companyProjectList1.toArray(new String[companyProjectList1.size()]);
//        cooperates =(String[]) cooperateList1.toArray(new String[cooperateList1.size()]);
//
//        List<CompanyCapabilityBO> allList = BeanTransform.copyProperties(list, CompanyCapabilityBO.class);
//
//        for(CompanyCapabilityBO companyCapabilityBO : allList){
//            companyCapabilityBO.setManageAuthen(String.join(",",manageAuthens));
//            companyCapabilityBO.setProfessionAuthen(String.join(",",professionAuthens));
//            companyCapabilityBO.setCompanyCertificate(String.join(",",companyCertificates));
//            companyCapabilityBO.setInProjct(String.join(",",inProjcts));
////            companyCapabilityBO.setCompanyProject(String.join(",",companyProjects));
//            companyCapabilityBO.setCooperate(String.join(",",cooperates));
//        }
        return allList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCapabilityBO addCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        //商务模块添加权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行添加公司能力操作");
        }

        CompanyCapability companyCapability = BeanTransform.copyProperties(companyCapabilityTO, CompanyCapability.class, true);
        companyCapability.setCreateTime(LocalDateTime.now());
        super.save(companyCapability);

        //添加管理资质
        String[] managerAuths = companyCapabilityTO.getManageAuthens();
        manageAuthenSer.addManageAuthen(managerAuths, companyCapability.getId());

        //添加专业资质认证
        String[] professionAuths = companyCapabilityTO.getProfessionAuthens();
        professionAuthenSer.addProfessionAuthen(professionAuths, companyCapability.getId());

        //添加公司荣誉证书
        String[] companyCertificates = companyCapabilityTO.getCompanyCertificates();
        companyCertificateSer.addCompanyCertificate(companyCertificates, companyCapability.getId());

        //添加公司合作对象
        String[] cooperates = companyCapabilityTO.getCooperates();
        cooperateSer.addCooperate(companyCertificates, companyCapability.getId());

        //添加公司参与项目
        String[] companyProjects = companyCapabilityTO.getCompanyProjects();
        companyProjectSer.addCompanyProject(companyProjects, companyCapability.getId());

        //添加公司尚在进行中项目
        String[] inProjcts = companyCapabilityTO.getInProjcts();
        inProjctSer.addInProjct(inProjcts, companyCapability.getId());

        return BeanTransform.copyProperties(companyCapability, CompanyCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCapabilityBO editCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行编辑公司能力操作");
        }

        if (StringUtils.isBlank(companyCapabilityTO.getId())) {
            throw new SerException("id不能为空");
        }
        CompanyCapability temp = super.findById(companyCapabilityTO.getId());
        CompanyCapability companyCapability = BeanTransform.copyProperties(companyCapabilityTO, CompanyCapability.class, true);
        BeanUtils.copyProperties(companyCapability, temp, "id", "createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //编辑管理资质
        String[] managerAuths = companyCapabilityTO.getManageAuthens();
        manageAuthenSer.editManageAuthen(managerAuths, companyCapability.getId());

        //编辑专业资质认证
        String[] professionAuths = companyCapabilityTO.getProfessionAuthens();
        professionAuthenSer.editProfessionAuthen(professionAuths, companyCapability.getId());

        //编辑公司荣誉证书
        String[] companyCertificates = companyCapabilityTO.getCompanyCertificates();
        companyCertificateSer.editCompanyCertificate(companyCertificates, companyCapability.getId());

        //编辑公司参与项目
        String[] companyProjects = companyCapabilityTO.getCompanyProjects();
        companyProjectSer.editCompanyProject(companyProjects, companyCapability.getId());

        //编辑公司尚在进行中项目
        String[] inProjcts = companyCapabilityTO.getInProjcts();
        inProjctSer.editInProjct(inProjcts, companyCapability.getId());

        //编辑公司合作对象
        String[] cooperates = companyCapabilityTO.getCooperates();
        cooperateSer.editCooperate(companyCertificates, companyCapability.getId());


        return BeanTransform.copyProperties(companyCapability, CompanyCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyCapability(String id) throws SerException {
        //商务模块编辑权限
        Boolean permissionLevel = cusPermissionSer.getCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是相应的人员，不可以进行删除公司能力操作");
        }

        super.remove(id);


        //删除管理资质
        manageAuthenSer.deleteManageAuthen(id);

        //删除专业资质认证
        professionAuthenSer.deleteProfessionAuthen(id);

        //删除公司荣誉证书
        companyCertificateSer.deleteCompanyCertificate(id);

        //删除公司参与项目数
        companyProjectSer.deleteCompanyProject(id);

        //删除公司尚在进行中项目
        inProjctSer.deleteInProjct(id);

        //删除公司合作对象
        cooperateSer.deleteCooperate(id);
    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapabilityByName(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        List<CompanyCapabilityBO> companyCapabilityBOS = new ArrayList<>();
        if (StringUtils.isNotBlank(companyCapabilityDTO.getCompany())) {
            companyCapabilityDTO.getConditions().add(Restrict.like("company", companyCapabilityDTO.getCompany()));
            List<CompanyCapability> companyCapabilityList = super.findByPage(companyCapabilityDTO);
            companyCapabilityBOS = BeanTransform.copyProperties(companyCapabilityList, CompanyCapabilityBO.class);
        }
        return companyCapabilityBOS;
    }

    @Override
    public List<String> listAllCompanyName() throws SerException {
        String[] fields = new String[]{"company"};
        List<CompanyCapabilityBO> cooperBOS = super.findBySql("select company  from capability_companycapability group by company ", CompanyCapabilityBO.class, fields);

        List<String> name = cooperBOS.stream().map(CompanyCapabilityBO::getCompany).collect(Collectors.toList());
        return name;
    }

    @Override
    public byte[] exportExcel(String companyName) throws SerException {
        CompanyCapabilityDTO dto = new CompanyCapabilityDTO();
        if (!org.springframework.util.StringUtils.isEmpty(companyName)) {
            dto.getConditions().add(Restrict.eq("company", companyName));
        }
        List<CompanyCapability> list = super.findByCis(dto);

        String[] manageAuthens = null;
        String[] professionAuthens = null;
        String[] companyCertificates = null;
        String[] inProjcts = null;
        String[] companyProjects = null;
        String[] cooperates = null;


        List<CompanyCapabilityBO> allList = new ArrayList<>();

        for (CompanyCapability module : list) {
            List<String> manageAuthenList1 = new ArrayList<String>();
            List<String> professionAuthenList1 = new ArrayList<>();
            List<String> companyCertificateList1 = new ArrayList<>();
            List<String> inProjctList1 = new ArrayList<>();
            List<String> companyProjectList1 = new ArrayList<>();
            List<String> cooperateList1 = new ArrayList<>();

            ManageAuthenDTO manageAuthenDTO = new ManageAuthenDTO();
            ProfessionAuthenDTO professionAuthenDTO = new ProfessionAuthenDTO();
            CompanyCertificateDTO companyCertificateDTO = new CompanyCertificateDTO();
            InProjctDTO inProjctDTO = new InProjctDTO();
            CompanyProjectDTO companyProjectDTO = new CompanyProjectDTO();
            CooperateDTO cooperateDTO = new CooperateDTO();

            manageAuthenDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            professionAuthenDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            companyCertificateDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            inProjctDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            companyProjectDTO.getConditions().add(Restrict.eq("baseId", module.getId()));
            cooperateDTO.getConditions().add(Restrict.eq("baseId", module.getId()));

            List<ManageAuthen> manageAuthenList = manageAuthenSer.findByCis(manageAuthenDTO);
            List<ProfessionAuthen> professionAuthenList = professionAuthenSer.findByCis(professionAuthenDTO);
            List<CompanyCertificate> companyCertificateList = companyCertificateSer.findByCis(companyCertificateDTO);
            List<InProjct> inProjctList = inProjctSer.findByCis(inProjctDTO);
            List<CompanyProject> companyProjectList = companyProjectSer.findByCis(companyProjectDTO);
            List<Cooperate> cooperateList = cooperateSer.findByCis(cooperateDTO);

            for (ManageAuthen manageAuthen : manageAuthenList) {

                manageAuthenList1.add(manageAuthen.getName());
            }
            for (ProfessionAuthen professionAuthen : professionAuthenList) {
                professionAuthenList1.add(professionAuthen.getName());
            }
            for (CompanyCertificate companyCertificate : companyCertificateList) {
                companyCertificateList1.add(companyCertificate.getName());
            }
            for (InProjct inProjct : inProjctList) {
                inProjctList1.add(inProjct.getName());
            }
            for (CompanyProject companyProject : companyProjectList) {
                companyProjectList1.add(companyProject.getName());
            }
            for (Cooperate cooperate : cooperateList) {
                cooperateList1.add(cooperate.getName());
            }

            manageAuthens = (String[]) manageAuthenList1.toArray(new String[manageAuthenList1.size()]);
            professionAuthens = (String[]) professionAuthenList1.toArray(new String[professionAuthenList1.size()]);
            companyCertificates = (String[]) companyCertificateList1.toArray(new String[companyCertificateList1.size()]);
            inProjcts = (String[]) inProjctList1.toArray(new String[inProjctList1.size()]);
            companyProjects = (String[]) companyProjectList1.toArray(new String[companyProjectList1.size()]);
            cooperates = (String[]) cooperateList1.toArray(new String[cooperateList1.size()]);

            CompanyCapabilityBO companyCapabilityBO = BeanTransform.copyProperties(module, CompanyCapabilityBO.class);

            companyCapabilityBO.setManageAuthen(String.join(",", manageAuthens));
            companyCapabilityBO.setProfessionAuthen(String.join(",", professionAuthens));
            companyCapabilityBO.setCompanyCertificate(String.join(",", companyCertificates));
            companyCapabilityBO.setInProjct(String.join(",", inProjcts));
            companyCapabilityBO.setCompanyProject(String.join(",", companyProjects));
            companyCapabilityBO.setCooperate(String.join(",", cooperates));

            allList.add(companyCapabilityBO);
        }

        List<CompanyCapabilityExcele> toList = new ArrayList<CompanyCapabilityExcele>();
        for (CompanyCapabilityBO model : allList) {
            CompanyCapabilityExcele excel = new CompanyCapabilityExcele();
            BeanUtils.copyProperties(model, excel);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("companyCapability");
        obj.setDescribesion("公司能力展示");
        if (flagSeeSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = selfCapabilitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("selfCapability");
        obj.setDescribesion("个人能力展示");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = cooperCapabilitySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("cooperCapability");
        obj.setDescribesion("合作对象商务展示");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = collectEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collectemail");
        obj.setDescribesion("商业能力展示邮件发送");
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case AUDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case CONGEL:
                flag = guideSeeIdentity();
                break;
            case THAW:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideSeeIdentity();
                RpcTransmit.transmitUserToken(userToken);
                break;
            case DOWNLOAD:
                flag = guideSeeIdentity();
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
    public byte[] templateExport() throws SerException {
//        getCusPermission();

        List<CompanyCapabilityTemplateExcele> companyCapabilityExports = new ArrayList<>();

        CompanyCapabilityTemplateExcele excel = new CompanyCapabilityTemplateExcele();
        excel.setCompany("beijing");
        excel.setProfessionAuthen("企鹅,房顶上");
        excel.setManageAuthen("专业");
        excel.setCompanyCertificate("qq");
        excel.setCompanyDevelop("ds");
        excel.setProjectDevelop("asd");
        excel.setArea("广州");
        excel.setMoney("12");
        excel.setPersonForm("人员组成");
        excel.setConfig("配置");
        excel.setDevice("设备");
        excel.setCompanyArea("公司占地面积");
        excel.setCompanyBusiness("fds");
        excel.setCooperate("test");
        excel.setCompanyProject("asf,dfsf");
        excel.setInProjct("尚在进行中项目");
        excel.setCulture("公司文化体系交流");
        excel.setHolidayActive("员工节假日活动");
        excel.setBulletinBoard("公司公告栏");

        companyCapabilityExports.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(companyCapabilityExports, exce);
        return bytes;
    }

    @Override
    public CompanyCapabilityBO importExcel(List<CompanyCapabilityTO> companyCapabilityTOList) throws SerException {

        List<CompanyCapability> companyCapabilityList = BeanTransform.copyProperties(companyCapabilityTOList, CompanyCapability.class, true);
        companyCapabilityList.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(companyCapabilityList);
        for (CompanyCapability companyCapability : companyCapabilityList) {
            for (CompanyCapabilityTO companyCapabilityTO : companyCapabilityTOList) {
                //添加管理资质
                String[] managerAuths = companyCapabilityTO.getManageAuthen().split(",");
                manageAuthenSer.addManageAuthen(managerAuths, companyCapability.getId());

                //添加专业资质认证
                String[] professionAuths = companyCapabilityTO.getProfessionAuthen().split(",");
                professionAuthenSer.addProfessionAuthen(professionAuths, companyCapability.getId());

                //添加公司荣誉证书
                String[] companyCertificates = companyCapabilityTO.getCompanyCertificate().split(",");
                companyCertificateSer.addCompanyCertificate(companyCertificates, companyCapability.getId());

                //添加公司合作对象
                String[] cooperates = companyCapabilityTO.getCooperate().split(",");
                cooperateSer.addCooperate(companyCertificates, companyCapability.getId());

                //添加公司参与项目
                String[] companyProjects = companyCapabilityTO.getCompanyProject().split(",");
                companyProjectSer.addCompanyProject(companyProjects, companyCapability.getId());

                //添加公司尚在进行中项目
                String[] inProjcts = companyCapabilityTO.getInProjct().split(",");
                inProjctSer.addInProjct(inProjcts, companyCapability.getId());
            }
        }
        CompanyCapabilityBO companyCapabilityBO = BeanTransform.copyProperties(new CompanyCapability(), CompanyCapabilityBO.class);
        return companyCapabilityBO;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


}