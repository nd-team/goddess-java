package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.projectcalculation.api.InterfaceCalculationDecisionAPI;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;
import com.bjike.goddess.projectcalculation.entity.InterfaceCalculationDetail;
import com.bjike.goddess.projectcalculation.enums.GuideAddrStatus;
import com.bjike.goddess.projectcalculation.excel.CalculationDetailExport;
import com.bjike.goddess.projectcalculation.excel.InterfaceCalculationDetailExport;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDecisionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 界面测算明细业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 界面测算明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcalculationSerCache")
@Service
public class InterfaceCalculationDetailSerImpl extends ServiceImpl<InterfaceCalculationDetail, InterfaceCalculationDetailDTO> implements InterfaceCalculationDetailSer {

    @Autowired
    private InterfaceCalculationDecisionAPI decisionAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
        return flag;
    }

    @Override
    public List<InterfaceCalculationDetailBO> getList() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), InterfaceCalculationDetailBO.class);
    }

    @Override
    public void add(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        InterfaceCalculationDetail interfaceCalculationDetail = BeanTransform.copyProperties(interfaceCalculationDetailTO, InterfaceCalculationDetail.class, true);
        super.save(interfaceCalculationDetail);
    }

    @Override
    public InterfaceCalculationDetailBO editor(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        InterfaceCalculationDetail interfaceCalculationDetail = super.findById(interfaceCalculationDetailTO.getId());
        return BeanTransform.copyProperties(interfaceCalculationDetail,InterfaceCalculationDetailBO.class);
    }

    @Override
    public void upDate(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        InterfaceCalculationDetail interfaceCalculationDetail = BeanTransform.copyProperties(interfaceCalculationDetailTO, InterfaceCalculationDetail.class, true);
        super.update(interfaceCalculationDetail);
    }

    @Override
    public byte[] exportExcel(InterfaceCalculationDetailDTO dto) throws SerException {
//        if (StringUtils.isNotBlank(dto.getInnerProject())) {
//            dto.getConditions().add(Restrict.eq("innerProject", dto.getInnerProject()));
//        }

        List<InterfaceCalculationDetail> list = super.findByCis(dto);

        List<InterfaceCalculationDetailExport> siginManageExports = new ArrayList<>();
        list.stream().forEach(str -> {
            InterfaceCalculationDetailExport excel = BeanTransform.copyProperties(str, InterfaceCalculationDetailExport.class);
            /*excel.setBusinessType(BusinessType.exportStrConvert(str.getBusinessType()));
            excel.setBusinessCooperate(BusinessCooperate.exportStrConvert(str.getBusinessCooperate()));
            excel.setContractProperty(ContractProperty.exportStrConvert(str.getContractProperty()));
            excel.setProjectStatus(ProjectStatus.exportStrConvert(str.getProjectStatus()));
            excel.setMakeProject(MakeContract.exportStrConvert(str.getMakeProject()));*/
            siginManageExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, excel);
        return bytes;
    }

    @Override
    public void importExcel(List<InterfaceCalculationDetailTO> tos) throws SerException {
        List<InterfaceCalculationDetail> calculationDetails = BeanTransform.copyProperties(tos, InterfaceCalculationDetail.class, true);
        calculationDetails.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(calculationDetails);

        /*SiginManageBO siginManageBO = BeanTransform.copyProperties(new SiginManage(), SiginManageBO.class);
        return siginManageBO;*/
    }

    @Override
    public List<InterfaceCalculationDetailBO> searchByArea(String area) throws SerException {
        InterfaceCalculationDetailDTO dto = new InterfaceCalculationDetailDTO();
        dto.getConditions().add(Restrict.like("area", area));
        return BeanTransform.copyProperties(super.findByCis(dto), InterfaceCalculationDetailBO.class);
    }

    @Override
    public List<InterfaceCalculationDetailBO> searchByInternalProjectNum(String internalProjectNum) throws SerException {
        InterfaceCalculationDetailDTO dto = new InterfaceCalculationDetailDTO();
        dto.getConditions().add(Restrict.like("internalProjectNum", internalProjectNum));
        return BeanTransform.copyProperties(super.findByCis(dto), InterfaceCalculationDetailBO.class);
    }

    @Override
    public void submit(InterfaceCalculationDetailTO to) throws SerException {
        InterfaceCalculationDetail detail = BeanTransform.copyProperties(to, InterfaceCalculationDetail.class, true);
        super.update(detail);
        decisionAPI.save(BeanTransform.copyProperties(to, InterfaceCalculationDecisionTO.class,"scale"));
        MessageTO messageTO = new MessageTO();
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.MSG);
        messageTO.setRangeType(RangeType.SPECIFIED);
        messageTO.setContent("hello");
        messageTO.setTitle("world");
        String[] strings = {"wanyi_aj@163.com"};
        messageTO.setReceivers(strings);
        messageAPI.send(messageTO);
    }

    @Override
    public List<InterfaceCalculationDetailBO> search(String condition) throws SerException {
        String sql = "SELECT *\n" +
                "FROM projectcalculation_interfacecalculationdetail\n" +
                "WHERE area LIKE '%a%' OR internalProject LIKE '%b%'\n";
        String[] fields = {"id","createTime","modifyTime","area","businessSubject","businessType","calculationProgress","calculationTime","decisionTime","dispatch" +
                "InterfaceA","entry","entryMoney","epiboly","expectCompleteTime","expectStartDate","forecastMoney","implementingParty","interfaceAssignment","interface" +
                "Content","internalProjectNum","invoice","major","majorCompany","makeContract","note","outsourcedUnitPrice","paymentMethod","projectCharge","project" +
                "Group","scale","settlementBatch","subCompany","taskContract","taskMoney","taxPoint","totalAmountOfOutsourced","type","status","internalProject"};
        return super.findBySql(sql, InterfaceCalculationDetailBO.class, fields);
    }

}