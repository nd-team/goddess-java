package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.employeecontract.bo.ContractChangeInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeInformationDTO;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.entity.ContractChangeInformation;
import com.bjike.goddess.employeecontract.entity.ContractInformation;
import com.bjike.goddess.employeecontract.enums.ChangeWay;
import com.bjike.goddess.employeecontract.enums.ContractCharacter;
import com.bjike.goddess.employeecontract.enums.GuideAddStatus;
import com.bjike.goddess.employeecontract.excel.ContractChangeInformationSetExcel;
import com.bjike.goddess.employeecontract.to.ChangeEnsuerTO;
import com.bjike.goddess.employeecontract.to.ContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.ExportContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
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
* 合同变更信息业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="employeecontractSerCache")
@Service
public class ContractChangeInformationSerImpl extends ServiceImpl<ContractChangeInformation, ContractChangeInformationDTO> implements ContractChangeInformationSer {
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Override
    public void add(ContractChangeInformationTO contractChangeInformationTO) throws SerException {
        ContractChangeInformation model = BeanTransform.copyProperties(contractChangeInformationTO,ContractChangeInformation.class,true);
        Integer realityProbationTime = (int)(model.getConversionTime().toEpochDay() - model.getEntryDate().toEpochDay());
        Integer onJobTime = (int)(LocalDate.now().toEpochDay() - model.getEntryDate().toEpochDay());
        model.setRealityProbationTime(realityProbationTime);
        model.setOnJobTime(onJobTime);
        if (model.getFirstChangeWay() != null || StringUtils.isNotBlank(model.getFirstChangeReason()) || model.getSecondChangeWay() != null || StringUtils.isNotBlank(model.getSecondChangeReason())){
            String email = internalContactsAPI.getEmail(model.getName());
            String[] sendUsers = new String[]{email};
//                String[] sendUsers = new String[]{"tangshuhua_aj@163.com"};
            MessageTO messageTO = new MessageTO("合同变更消息通知", model.getName() + "你好，你续签的合同变更信息已完成填写，请上系统进行变更确认");
            messageTO.setSendType(SendType.EMAIL);
            messageTO.setMsgType(MsgType.SYS);//根据自己业务写
            messageTO.setSendType(SendType.EMAIL);//根据自己业务写
            messageTO.setRangeType(RangeType.SPECIFIED);//根据自己业务写
            messageTO.setSenderId("SYSTEM");
            messageTO.setSenderName("SYSTEM");
            messageTO.setReceivers(sendUsers);
            messageAPI.send(messageTO);
        }
        super.save(model);
    }

    @Override
    public void modify(ContractChangeInformationTO contractChangeInformationTO) throws SerException {
        ContractChangeInformation model = super.findById(contractChangeInformationTO.getId());
        ContractChangeInformation contractChangeInformation = new ContractChangeInformation();
        BeanTransform.copyProperties(contractChangeInformationTO,model,true,"createTime","modifyTime");
        contractChangeInformation.setCreateTime(model.getCreateTime());
        contractChangeInformation.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            ContractChangeInformation model = super.findById(id);
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
    public List<ContractChangeInformationBO> pageList(ContractChangeInformationDTO contractChangeInformationDTO) throws SerException {
        List<ContractChangeInformation> list = super.findByPage(contractChangeInformationDTO);
        List<ContractChangeInformationBO> boList = BeanTransform.copyProperties(list,ContractChangeInformationBO.class,false);
        return boList;
    }

    @Override
    public ContractChangeInformationBO findOne(String id) throws SerException {
        ContractChangeInformation model = super.findById(id);
        ContractChangeInformationBO bo = BeanTransform.copyProperties(model,ContractChangeInformationBO.class,false);
        return bo;
    }

    @Override
    public void leadExcel(List<ContractChangeInformationTO> toList) throws SerException {
        UserBO userBO = userAPI.currentUser();
        List<ContractChangeInformation> list = BeanTransform.copyProperties(toList,ContractChangeInformation.class,true);
        list.stream().forEach(str -> {
            str.setModifyTime(LocalDateTime.now());
            str.setCreateTime(LocalDateTime.now());
        });
        super.save(list);
    }

    @Override
    public byte[] exportExcel(ExportContractChangeInformationTO to) throws SerException {
        ContractChangeInformationDTO dto = new ContractChangeInformationDTO();
        List<ContractChangeInformation> list = super.findByCis(dto);
        List<ContractChangeInformationSetExcel> toList = new ArrayList<>();
        for (ContractChangeInformation model : list){
            ContractChangeInformationSetExcel excel = BeanTransform.copyProperties(model,ContractChangeInformationSetExcel.class);
            toList.add(excel);
        }
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList,excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<ContractChangeInformationSetExcel> contractChangeInformationSetExcels = new ArrayList<>();

        ContractChangeInformationSetExcel excel = new ContractChangeInformationSetExcel();

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
        excel.setIfNeedContractChangeFirst(false);
        excel.setFirstChangeWay(ChangeWay.ADDAGREEMENT);
        excel.setFirstChangeContent(true);
        excel.setFirstIfBringIntoUnifyChange(false);
        excel.setFirstChangeReason("第一次续签原因");
        excel.setFirstIfEnsureChange(false);
        excel.setFirstEnsureChanger("第一次续签确认变更人");
        excel.setIfNeedContractChangeSecond(false);
        excel.setSecondChangeWay(ChangeWay.ADDAGREEMENT);
        excel.setSecondChangeContent(false);
        excel.setSecondIfBringIntoUnifyChange(false);
        excel.setSecondChangeReason("第二系续签变更原因");
        excel.setSecondIfEnsureChange(false);
        excel.setSecondEnsureChanger("第二次续签确认变更人");
        contractChangeInformationSetExcels.add(excel);

        Excel exce = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(contractChangeInformationSetExcels, exce);
        return bytes;
    }

    @Override
    public void changeEnsure(ChangeEnsuerTO changeEnsuerTO) throws SerException {
        ContractChangeInformation model = super.findById(changeEnsuerTO.getId());
        if (model.getFirstIfEnsureChange() == null){
            model.setFirstIfEnsureChange(changeEnsuerTO.getIfEnsureChange());
            model.setFirstEnsureChanger(changeEnsuerTO.getEnsureChanger());
        }else {
            model.setSecondIfEnsureChange(changeEnsuerTO.getIfEnsureChange());
            model.setSecondEnsureChanger(changeEnsuerTO.getEnsureChanger());
        }
    }


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
}