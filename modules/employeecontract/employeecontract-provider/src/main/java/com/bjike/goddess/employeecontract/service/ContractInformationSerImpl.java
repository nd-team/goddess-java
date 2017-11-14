package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.dimission.api.DimissionInfoAPI;
import com.bjike.goddess.employeecontract.bo.ContractInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.entity.ContractInformation;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;
import com.bjike.goddess.employeecontract.enums.ContractStatus;
import com.bjike.goddess.employeecontract.enums.GuideAddStatus;
import com.bjike.goddess.employeecontract.excel.ContractInformationSetExcel;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.to.*;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 员工合同信息业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="employeecontractSerCache")
@Service
public class ContractInformationSerImpl extends ServiceImpl<ContractInformation, ContractInformationDTO> implements ContractInformationSer {
    @Autowired
    private RegularizationAPI regularizationAPI;

    @Autowired
    private DimissionInfoAPI dimissionInfoAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }


    /**
     * 核对查看权限（部门级别）
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

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public void add(ContractInformationTO contractInformationTO) throws SerException {
        ContractInformation model = BeanTransform.copyProperties(contractInformationTO,ContractInformation.class,true);
        Integer realityProbationTime = (int)(model.getConversionTime().toEpochDay() - model.getEntryDate().toEpochDay());
        Integer onJobTime = (int)(LocalDate.now().toEpochDay() - model.getEntryDate().toEpochDay());
        Integer contractDeadline = (int)(model.getSignContractEndDate().toEpochDay() - model.getSignContractStartDate().toEpochDay());
        LocalDate shouldSignContractDate = model.getEntryDate().plusMonths(1);
        int timeDifference = (int)(LocalDate.now().toEpochDay() - shouldSignContractDate.toEpochDay());
        if (timeDifference >=0){
            model.setContractStatus(ContractStatus.WAITSIGN);
        }else {
            model.setContractStatus(ContractStatus.ALREADYDEADLINENOTRENEW);
        }
        model.setRealityProbationTime(realityProbationTime);
        model.setOnJobTime(onJobTime);
        model.setContractDeadline(contractDeadline);
        super.save(model);
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractInformation model = super.findById(id);
            if (model != null){
                super.remove(model);
            }else {
                throw new SerException("数据库中没有该数据");
            }
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void modify(ContractInformationTO contractInformationTO) throws SerException {
        ContractInformation model = super.findById(contractInformationTO.getId());
        ContractInformation contractInformation = new ContractInformation();
        BeanTransform.copyProperties(contractInformationTO,contractInformation,true,"createTime","modifyTime");

        Integer realityProbationTime = (int)(contractInformation.getConversionTime().toEpochDay() - contractInformation.getEntryDate().toEpochDay());
        Integer onJobTime = (int)(LocalDate.now().toEpochDay() - contractInformation.getEntryDate().toEpochDay());
        Integer contractDeadline = (int)(contractInformation.getSignContractEndDate().toEpochDay() - contractInformation.getSignContractStartDate().toEpochDay());
        LocalDate shouldSignContractDate = contractInformation.getEntryDate().plusMonths(1);
        int timeDifference = (int)(LocalDate.now().toEpochDay() - shouldSignContractDate.toEpochDay());
        if (timeDifference > 0){
            contractInformation.setContractStatus(ContractStatus.ALREADYDEADLINENOTRENEW);
        }else {
            contractInformation.setContractStatus(ContractStatus.WAITSIGN);
        }
        contractInformation.setRealityProbationTime(realityProbationTime);
        contractInformation.setOnJobTime(onJobTime);
        contractInformation.setContractDeadline(contractDeadline);
        contractInformation.setModifyTime(LocalDateTime.now());
        contractInformation.setCreateTime(model.getCreateTime());
        super.update(contractInformation);
    }

    @Override
    public List<ContractInformationBO> pageList(ContractInformationDTO contractInformationDTO) throws SerException {
        ContractInformationDTO dto = new ContractInformationDTO();
        if (StringUtils.isNotBlank(contractInformationDTO.getName())){
            dto.getConditions().add(Restrict.eq("name",contractInformationDTO.getName()));
        }
        if (contractInformationDTO.getContractStatus() != null){
            dto.getConditions().add(Restrict.eq("contractStatus",contractInformationDTO.getContractStatus()));
        }
        List<ContractInformation> list = super.findByPage(dto);
        List<ContractInformationBO> boList = BeanTransform.copyProperties(list,ContractInformationBO.class);
        return boList;
    }

    @Override
    public ContractInformationBO findOne(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractInformation model = super.findById(id);
            ContractInformationBO contractInformationBO = BeanTransform.copyProperties(model,ContractInformationBO.class,false);
            return contractInformationBO;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void renewEnsure(RenewEnsureTO renewEnsureTO) throws SerException {
        ContractInformation model = super.findById(renewEnsureTO.getId());
        model.setIfRenew(renewEnsureTO.getIfRenew());
        if (renewEnsureTO.getIfRenew() == true) {
            if (model.getIfNeedContractChangeFirst() != null) {
                model.setIfNeedContractChangeSecond(renewEnsureTO.getIfNeedContractChange());
                model.setFirstRenewStartDate(DateUtil.parseDate(renewEnsureTO.getRenewStartDate()));
                model.setFirstRenewEndDate(DateUtil.parseDate(renewEnsureTO.getRenewEndDate()));
                model.setShouldSignContractDate(DateUtil.parseDate(renewEnsureTO.getRenewEndDate()));
            } else {
                model.setIfNeedContractChangeFirst(renewEnsureTO.getIfNeedContractChange());
                model.setSecondRenewStartDate(DateUtil.parseDate(renewEnsureTO.getRenewStartDate()));
                model.setSecondRenewEndDate(DateUtil.parseDate(renewEnsureTO.getRenewEndDate()));
                model.setShouldSignContractDate(DateUtil.parseDate(renewEnsureTO.getRenewEndDate()));
            }
            model.setContractStatus(ContractStatus.ALREADYSIGN);
        }else {
            LocalDate shouldSignContractDate = model.getEntryDate().plusMonths(1);
            int timeDifference = (int)(LocalDate.now().toEpochDay() - shouldSignContractDate.toEpochDay());
            if (timeDifference > 0){
                model.setContractStatus(ContractStatus.ALREADYDEADLINENOTRENEW);
            }
        }
        super.update(model);
    }

    @Override
    public void relieveContract(RelieveContractTO relieveContractTO) throws SerException {
        ContractInformation model = super.findById(relieveContractTO.getId());
        model.setIfRelieveContract(relieveContractTO.getIfRelieveContract());
        if (relieveContractTO.getIfRelieveContract() == true){
            model.setContractStatus(ContractStatus.RELIEVE);
        }
        model.setRelieveContractDate(DateUtil.parseDate(relieveContractTO.getRelieveDate()));
        super.update(model);
    }

    @Override
    public void leadExcel(List<ContractInformationTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser();
        List<ContractInformation> list = BeanTransform.copyProperties(toList, ContractInformation.class, true);
        list.stream().forEach(str -> {
            str.setModifyTime(LocalDateTime.now());
            str.setCreateTime(LocalDateTime.now());
        });
        super.save(list);
    }

    @Override
    public byte[] exportExcel(ExportContractInformationTO to) throws SerException {
        ContractInformationDTO dto = new ContractInformationDTO();
        List<ContractInformation> list = super.findByCis(dto);
        List<ContractInformationSetExcel> toList = new ArrayList<ContractInformationSetExcel>();
        for (ContractInformation model : list) {
            ContractInformationSetExcel excel = BeanTransform.copyProperties(model, ContractInformationSetExcel.class);
            toList.add(excel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ContractInformationSetExcel> contractInformationSetExcels = new ArrayList<>();

        ContractInformationSetExcel excel = new ContractInformationSetExcel();

        excel.setContractCharacter(ContractCharacter.EXTERNALSTAFF);
        excel.setContractNumber("合同编号");
        excel.setArea("地区");
        excel.setName("姓名");
        excel.setEmployeeNumber("员工编号");
        excel.setDepartment("部门");
        excel.setPosition("岗位");
        excel.setEntryDate("入职日期");
        excel.setAgreeProbationTime(10);
        excel.setRealityProbationTime(12);
        excel.setOnJobTime(12);
        excel.setConversionTime("2017-01-01");
        excel.setDimissionDate("2017-01-01");
        excel.setShouldSignContractDate("2017-01-01");
        excel.setIfSignContract(false);
        excel.setSignBelongCompany("签订所属公司");
        excel.setSignContractStartDate("2017-01-01");
        excel.setSignContractEndDate("2017-01-01");
        excel.setContractDeadline(12);
        excel.setIfRenew(false);
        excel.setIfNeedContractChangeFirst(false);
        excel.setFirstRenewStartDate("2017-01-01");
        excel.setFirstRenewEndDate("2017-01-01");
        excel.setFirstReneweContractDeadline(12);
        excel.setIfSecondRenew(false);
        excel.setIfNeedContractChangeSecond(false);
        excel.setSecondRenewStartDate("2017-01-01");
        excel.setSecondRenewEndDate("2017-01-01");
        excel.setIfRelieveContract(false);
        excel.setRelieveContractDate("2017-01-01");
        excel.setIfAttachment(false);
        excel.setAttachmentStorageLocation("原件存储位置");
        excel.setContractStatus(ContractStatus.WAITSIGN);

        contractInformationSetExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(contractInformationSetExcels, exce);
        return bytes;
    }

    @Override
    public String getRegularDate(String userName) throws SerException {
        String regularDate = null;
        String userToken = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("regularization")){
            RpcTransmit.transmitUserToken(userToken);
            regularDate = regularizationAPI.getTime(userName);
        }
        return regularDate;
    }

    @Override
    public String getDimissionDate(String userName) throws SerException {
        String dimissionDate = null;
        String userToken = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("dimission")){
            RpcTransmit.transmitUserToken(userToken);
            dimissionDate = dimissionInfoAPI.getTime(userName);
        }
        return dimissionDate;
    }
}