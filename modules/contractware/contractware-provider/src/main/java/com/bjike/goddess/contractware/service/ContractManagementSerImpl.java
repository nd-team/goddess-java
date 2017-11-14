package com.bjike.goddess.contractware.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.businessproject.api.DispatchSheetAPI;
import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceBouncesBO;
import com.bjike.goddess.contractware.bo.InvoiceManagementBO;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.entity.ContractManagement;
import com.bjike.goddess.contractware.entity.InvoiceManagement;
import com.bjike.goddess.contractware.enums.ContractStatus;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.to.ContractManagementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
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
* 合同保管业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-30 06:13 ]
* @Description:	[ 合同保管业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="contractwareSerCache")
@Service
public class ContractManagementSerImpl extends ServiceImpl<ContractManagement, ContractManagementDTO> implements ContractManagementSer {
    @Autowired
    private DispatchSheetAPI dispatchSheetAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    @Autowired
    private InvoiceManagementSer invoiceManagementSer;

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
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.busCusPermission("2");
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
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
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
    public void add(ContractManagementTO contractManagementTO) throws SerException {
        ContractManagement model = BeanTransform.copyProperties(contractManagementTO,ContractManagement.class,true);
        model.setStatus(ContractStatus.NORMAL);
        model.setIfHaveContract(false);
        model.setAddTime(LocalDate.now());
        super.save(model);
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractManagement model = super.findById(id);
            if (model != null){
                super.remove(model);
            }else {
                throw new SerException("数据库中没有该条数据");
            }
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void modify(ContractManagementTO contractManagementTO) throws SerException {
        ContractManagement model = super.findById(contractManagementTO.getId());
        ContractManagement contractManagement = new ContractManagement();
        BeanTransform.copyProperties(contractManagementTO,contractManagement,true,"createTime","modifyTime");
        contractManagement.setIfHaveContract(model.getIfHaveContract());
        contractManagement.setCreateTime(model.getCreateTime());
        contractManagement.setModifyTime(LocalDateTime.now());
        contractManagement.setStatus(model.getStatus());
        contractManagement.setAddTime(model.getAddTime());
        super.update(contractManagement);
    }

    @Override
    public List<ContractManagementBO> pageList(ContractManagementDTO contractManagementDTO) throws SerException{
        ContractManagementDTO dto = new ContractManagementDTO();
        if (StringUtils.isNotBlank(contractManagementDTO.getAddTime())){
            LocalDate addTime = DateUtil.parseDate(contractManagementDTO.getAddTime());
            dto.getConditions().add(Restrict.eq("addTime",addTime));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getArea())){
            dto.getConditions().add(Restrict.eq("area",contractManagementDTO.getArea()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getContractCharacter())){
            dto.getConditions().add(Restrict.eq("contractCharacter",contractManagementDTO.getContractCharacter()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getInnerProject())){
            dto.getConditions().add(Restrict.eq("innerProject",contractManagementDTO.getInnerProject()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getInternalContractNumber())){
            dto.getConditions().add(Restrict.eq("internalContractNumber",contractManagementDTO.getInternalContractNumber()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getMajor())){
            dto.getConditions().add(Restrict.eq("major",contractManagementDTO.getMajor()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getTaskNum())){
            dto.getConditions().add(Restrict.eq("taskNum",contractManagementDTO.getTaskNum()));
        }
        if (StringUtils.isNotBlank(contractManagementDTO.getTaskProjectName())){
            dto.getConditions().add(Restrict.eq("taskProjectName",contractManagementDTO.getTaskProjectName()));
        }
        List<ContractManagement> contractManagements = super.findByPage(dto);
        List<ContractManagementBO> contractManagementBOS = BeanTransform.copyProperties(contractManagements,ContractManagementBO.class,false);
        return contractManagementBOS;
    }

    @Override
    public ContractManagementBO findOne(String id) throws SerException {
        ContractManagement model = super.findById(id);
        ContractManagementBO contractManagementBO = BeanTransform.copyProperties(model,ContractManagementBO.class,false);
        return contractManagementBO;
    }

    @Override
    public void freeze(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractManagement model = super.findById(id);
            if (model != null) {
                model.setStatus(ContractStatus.CANCELLATION);
            }else {
                throw new SerException("数据库中没有该条数据了");
            }
            super.update(model);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractManagement model = super.findById(id);
            if (model != null) {
                model.setStatus(ContractStatus.NORMAL);
            }else {
                throw new SerException("数据库中没有该条数据了");
            }
            super.update(model);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<DispatchSheetBO> findInformation() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<DispatchSheetBO> list = new ArrayList<>();
        if (moduleAPI.isCheck("businessproject")) {
            RpcTransmit.transmitUserToken(userToken);
            DispatchSheetDTO dispatchSheetDTO = new DispatchSheetDTO();
            list = dispatchSheetAPI.listDispatchSheet(dispatchSheetDTO);
        }
        return list;
    }

    @Override
    public void updateContract(String id) throws SerException {
        ContractManagement model = super.findById(id);
        model.setIfHaveContract(true);
        super.update(model);
    }

    @Override
    public InvoiceBouncesBO findByNumber(String innerProjectNumber) throws SerException {
        InvoiceManagementDTO dto = new InvoiceManagementDTO();
        ContractManagementDTO contractManagementDTO = new ContractManagementDTO();
        InvoiceBouncesBO invoiceBouncesBO = new InvoiceBouncesBO();
        dto.getConditions().add(Restrict.eq("internalContractNumber", innerProjectNumber));
        contractManagementDTO.getConditions().add(Restrict.eq("internalContractNumber", innerProjectNumber));
        List<InvoiceManagement> invoiceManagements = invoiceManagementSer.findByCis(dto);
        List<ContractManagement> contractManagements = super.findByCis(contractManagementDTO);
        List<InvoiceManagementBO> invoiceManagementBOS = BeanTransform.copyProperties(invoiceManagements, InvoiceManagementBO.class, false);

        Double contractInvoiceMoney = 0.0;
        Double invoiceAmount = 0.0;
        Double engineeringAward = 0.0;

        contractInvoiceMoney = contractManagements.stream().filter(p -> p.getContractMoney() != null).mapToDouble(p -> p.getContractMoney()).sum();
        if (invoiceManagementBOS != null && invoiceManagementBOS.size() > 0){
            invoiceAmount = invoiceManagementBOS.stream().filter(p -> p.getInvoiceMoney() != null).mapToDouble(p -> p.getInvoiceMoney()).sum();
        }
        engineeringAward = contractManagements.stream().filter(p -> p.getEngineeringAwardFine() != null).mapToDouble(p -> p.getEngineeringAwardFine()).sum();

        Double waitMakeINvoiceMoney = invoiceAmount - contractInvoiceMoney - engineeringAward;

        invoiceBouncesBO.setContractInvoiceMoney(contractInvoiceMoney);
        invoiceBouncesBO.setInvoiceAmount(invoiceAmount);
        invoiceBouncesBO.setInvoiceManagementList(invoiceManagementBOS);
        invoiceBouncesBO.setEngineeringAward(engineeringAward);
        invoiceBouncesBO.setWaitMakeInvoiceMoney(waitMakeINvoiceMoney);
        return invoiceBouncesBO;
    }
}