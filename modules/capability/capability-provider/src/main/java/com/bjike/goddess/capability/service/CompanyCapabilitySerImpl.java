package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.dto.ManageAuthenDTO;
import com.bjike.goddess.capability.enums.GuideAddrStatus;
import com.bjike.goddess.capability.excele.CompanyCapabilityExcele;
import com.bjike.goddess.capability.excele.SonPermissionObject;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.capability.to.ManageAuthenTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.entity.CompanyCapability;
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
import org.springframework.cache.annotation.Cacheable;
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

        return BeanTransform.copyProperties(list, CompanyCapabilityBO.class);
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
            dto.getConditions().add(Restrict.eq("companyName", companyName));
        }
        List<CompanyCapability> list = super.findByCis(dto);
        List<CompanyCapabilityExcele> toList = new ArrayList<CompanyCapabilityExcele>();
        for (CompanyCapability model : list) {
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