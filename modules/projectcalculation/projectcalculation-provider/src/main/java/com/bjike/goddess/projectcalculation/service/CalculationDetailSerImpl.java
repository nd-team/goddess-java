package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.projectcalculation.enums.GuideAddrStatus;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.excel.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.market.api.MarketInfoPreAnalysisAPI;
import com.bjike.goddess.market.bo.MarketInfoPreAnalysisBO;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.projectcalculation.bo.CalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;
import com.bjike.goddess.projectcalculation.excel.CalculationDetailExport;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目测算明细业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcalculationSerCache")
@Service
public class CalculationDetailSerImpl extends ServiceImpl<CalculationDetail, CalculationDetailDTO> implements CalculationDetailSer {

    @Autowired
    private MarketInfoPreAnalysisAPI marketInfoPreAnalysisAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 导航栏核对查看权限（部门级别）
     *//*
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
    }*/

    /*@Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagButget = guideButgetIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPlan = guidePlanIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagButget || flagPlan) {
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSeeSign = guideSeeIdentity();
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAddSign = guideAddIdentity();
        Boolean flag1 = false;
        Boolean flag2 = false;
        Boolean flag3 = false;
        Boolean flag4 = false;
        Boolean flag5 = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        /*if (!"admin".equals(userName.toLowerCase())) {
            flag1 = cusPermissionSer.getCusPermission("1",userBO);
            flag2 = cusPermissionSer.getCusPermission("2",userBO);
            flag3 = cusPermissionSer.busCusPermission("3",userBO);
            flag4 = cusPermissionSer.modCusPermission("4",userBO);
            flag5 = cusPermissionSer.modCusPermission("5",userBO);
        } else {
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            flag5 = true;
        }*/
        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("calculationDetail");
        obj.setDescribesion("项目测算明细");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("calculationDecisions");
        obj.setDescribesion("测算决策");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("interfaceCalculationDetail");
        obj.setDescribesion("界面测算明细");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("interfaceCalculationDecision");
        obj.setDescribesion("界面测算决策");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        /*obj = new SonPermissionObject();
        obj.setName("baseinfomanage");
        obj.setDescribesion("商务项目合同基本信息");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("businesscontract");
        obj.setDescribesion("商务项目合同");
        if (flag1 || flag2 || flag3 || flag4 || flag5) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        obj = new SonPermissionObject();
        obj.setName("outsourcbusinesscontract");
        obj.setDescribesion("外包半外包项目合同管理");
        if (flag1 || flag2) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);*/

        return list;
    }

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
    public List<CalculationDetailBO> getList() throws SerException {
        List<CalculationDetail> list = super.findAll();
        List<CalculationDetailBO> boList = BeanTransform.copyProperties(list, CalculationDetailBO.class);
        return boList;
    }

    @Override
    public void add(CalculationDetailTO calculationDetailTO) throws SerException {
        CalculationDetail calculationDetail = BeanTransform.copyProperties(calculationDetailTO, CalculationDetail.class, true);
        super.save(calculationDetail);
    }

    @Override
    public void editor(CalculationDetailTO calculationDetailTO) throws SerException {
        CalculationDetail calculationDetail = BeanTransform.copyProperties(calculationDetailTO, CalculationDetail.class, true);
        super.update(calculationDetail);
    }

    @Override
    public byte[] exportExcel(CalculationDetailDTO dto) throws SerException {
//        if (StringUtils.isNotBlank(dto.getInnerProject())) {
//            dto.getConditions().add(Restrict.eq("innerProject", dto.getInnerProject()));
//        }

        List<CalculationDetail> list = super.findByCis(dto);

        List<CalculationDetailExport> siginManageExports = new ArrayList<>();
        list.stream().forEach(str -> {
            CalculationDetailExport excel = BeanTransform.copyProperties(str, CalculationDetailExport.class);
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
    public void importExcel(List<CalculationDetailTO> tos) throws SerException {
        List<CalculationDetail> calculationDetails = BeanTransform.copyProperties(tos, CalculationDetail.class, true);
        calculationDetails.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(calculationDetails);

        /*SiginManageBO siginManageBO = BeanTransform.copyProperties(new SiginManage(), SiginManageBO.class);
        return siginManageBO;*/
    }

    @Override
    public List<CalculationDetailBO> getSearchList(String marketInfoNum) throws SerException {
        CalculationDetailDTO dto = new CalculationDetailDTO();
        dto.getConditions().add(Restrict.like("marketInfoNum",marketInfoNum));
        List<CalculationDetail> list = super.findByCis(dto);
        List<CalculationDetailBO> bos = BeanTransform.copyProperties(list, CalculationDetailBO.class);
        /*String sql = "select * from projectcalculation_calculationdetail where marketInfoNum like '%" + marketInfoNum + "%'";
        List<Object> objects = super.findBySql(sql);
        List<CalculationDetailBO> bos = BeanTransform.copyProperties(list, CalculationDetailBO.class);*/
        return bos;
    }

    @Override
    public void saveMarket(MarketInfoPreAnalysisTO to) throws SerException {
//        List<MarketInfoPreAnalysisBO> bos = marketInfoPreAnalysisAPI.getconversionBuissOpp();
        CalculationDetail calculationDetail = BeanTransform.copyProperties(to, CalculationDetail.class, "infoCollectDate", "areaFrames", "serviceCost", "conversionMarketFor" +
                "conversionBussNegotia", "remark", "preliminaryAnaly", "riskDescribe", "partnerRisk", "qualificationRequi", "agingStartTime", "agingEndTime", "invoiceType" +
                "receivableDate");
//        List<CalculationDetail> list = BeanTransform.copyProperties(bos,CalculationDetail.class,true);
        super.save(calculationDetail);
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

}