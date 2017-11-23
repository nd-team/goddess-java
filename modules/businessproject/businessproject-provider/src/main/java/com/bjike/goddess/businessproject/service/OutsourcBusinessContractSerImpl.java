package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.OutsourcBusinessContractBO;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.dto.OutsourcBusinessContractDTO;
import com.bjike.goddess.businessproject.entity.BusinessContract;
import com.bjike.goddess.businessproject.entity.OutsourcBusinessContract;
import com.bjike.goddess.businessproject.enums.GuideAddrStatus;
import com.bjike.goddess.businessproject.excel.OutsourcBusinessContractExport;
import com.bjike.goddess.businessproject.excel.OutsourcBusinessContractTemplateExcel;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.OutsourcBusinessContractTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.util.parsing.combinator.testing.Str;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 外包半外包项目合同管理业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-19 11:55 ]
 * @Description: [ 外包半外包项目合同管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class OutsourcBusinessContractSerImpl extends ServiceImpl<OutsourcBusinessContract, OutsourcBusinessContractDTO> implements OutsourcBusinessContractSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BusinessContractSer businessContractSer;

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
            flag = cusPermissionSer.getCusPermission("1",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = cusPermissionSer.getCusPermission("2",null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
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
            flag = cusPermissionSer.getCusPermission("2",null);
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
            flag = cusPermissionSer.getCusPermission("1",null);
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(OutsourcBusinessContractDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }


    @Override
    public OutsourcBusinessContractBO getOneById(String id) throws SerException {
        OutsourcBusinessContract contract = super.findById(id);
        return BeanTransform.copyProperties(contract, OutsourcBusinessContractBO.class);
    }

    @Override
    public List<OutsourcBusinessContractBO> list(OutsourcBusinessContractDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        checkSeeIdentity();
        search(dto);
        OutsourcBusinessContract outsourcBusinessContract = new OutsourcBusinessContract();
        BusinessContractDTO businessContractDTO = new BusinessContractDTO();
        List<BusinessContract> businessContracts = businessContractSer.findByCis(businessContractDTO);
        for(BusinessContract businessContract:businessContracts){
            if(businessContract.getMeasurePass().equals("外包") || businessContract.getMeasurePass().equals("半外包")
                    && (businessContract.getStatus()==null)){
                //测算分类
                outsourcBusinessContract.setMeasureClassify(businessContract.getMeasureClassify());
                //测算是否通过
                outsourcBusinessContract.setMeasurePass(businessContract.getMeasurePass());
                //签订时间
                outsourcBusinessContract.setSignedTime(businessContract.getSignedTime());
                //地区
                outsourcBusinessContract.setArea(businessContract.getArea());
                //业务类型
                outsourcBusinessContract.setBusinessType(businessContract.getBusinessType());
                //业务方向科目
                outsourcBusinessContract.setBusinessSubject(businessContract.getBusinessSubject());
                //总包单位名称
                outsourcBusinessContract.setMajorCompany(businessContract.getMajorCompany());
                //分包单位名称
                outsourcBusinessContract.setSubCompany(businessContract.getSubCompany());
                //所属项目组
                outsourcBusinessContract.setProjectGroup(businessContract.getProjectGroup());
                //是否有合同派工
                outsourcBusinessContract.setTaskContract(businessContract.getTaskContract());
                //市场编号
                outsourcBusinessContract.setMarketNum(businessContract.getMarketNum());
                //类型
                outsourcBusinessContract.setType(businessContract.getType());
                //专业/工期
                outsourcBusinessContract.setMajor(businessContract.getMajor());
                //是否有合同立项
                outsourcBusinessContract.setMakeContract(businessContract.getMakeContract());
                businessContract.setStatus(true);
                businessContractSer.update(businessContract);
                super.save(outsourcBusinessContract);
            }
        }
        List<OutsourcBusinessContract> contracts = super.findByCis(dto);
        List<OutsourcBusinessContractBO> contractBOS = BeanTransform.copyProperties(contracts, OutsourcBusinessContractBO.class);
        return contractBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutsourcBusinessContractBO add(OutsourcBusinessContractTO to) throws SerException {
        checkAddIdentity();
        OutsourcBusinessContract contract = BeanTransform.copyProperties(to,OutsourcBusinessContract.class);
        contract.setCreateTime(LocalDateTime.now());
        super.save(contract);
        OutsourcBusinessContractBO bo = BeanTransform.copyProperties(contract,OutsourcBusinessContractBO.class);
        return bo;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutsourcBusinessContractBO supplier(OutsourcBusinessContractTO to) throws SerException {
        checkAddIdentity();
        if(StringUtils.isNotBlank(to.getId())){
            OutsourcBusinessContract contract = super.findById(to.getId());
            LocalDateTime createTime = contract.getCreateTime();
            contract = BeanTransform.copyProperties(to,OutsourcBusinessContract.class,true);
            contract.setCreateTime(createTime);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            OutsourcBusinessContractBO bo = BeanTransform.copyProperties(contract,OutsourcBusinessContractBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OutsourcBusinessContractBO edit(OutsourcBusinessContractTO to) throws SerException {
        checkAddIdentity();
        if(StringUtils.isNotBlank(to.getId())){
            OutsourcBusinessContract contract = super.findById(to.getId());
            LocalDateTime createTime = contract.getCreateTime();
            contract = BeanTransform.copyProperties(to,OutsourcBusinessContract.class,true);
            contract.setCreateTime(createTime);
            contract.setModifyTime(LocalDateTime.now());
            super.update(contract);
            OutsourcBusinessContractBO bo = BeanTransform.copyProperties(contract,OutsourcBusinessContractBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkAddIdentity();
        if(StringUtils.isNotBlank(id)){
            super.remove(id);
        }else {
            throw new SerException("不能为空");
        }
    }
    @Override
    public Set<String> areas() throws SerException {
        Set<String>set = new HashSet<>();
        List<OutsourcBusinessContract> list = super.findAll();
        for (OutsourcBusinessContract contract:list){
            set.add(contract.getArea());
        }
        return set;
    }

    private void search(OutsourcBusinessContractDTO dto)throws SerException{
        //签订时间
        if(StringUtils.isNotBlank(dto.getSignedTime())){
            dto.getConditions().add(Restrict.eq("signedTime",dto.getSignedTime()));
        }
        //地区
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.like("area",dto.getArea()));
        }
        //业务类型
        if(StringUtils.isNotBlank(dto.getBusinessType())){
            dto.getConditions().add(Restrict.like("businessType",dto.getBusinessType()));
        }
        //总包单位名称
        if(StringUtils.isNotBlank(dto.getMajorCompany())){
            dto.getConditions().add(Restrict.like("majorCompany",dto.getMajorCompany()));
        }
        //所属项目组
        if(StringUtils.isNotBlank(dto.getProjectGroup())) {
            dto.getConditions().add(Restrict.like("projectGroup",dto.getProjectGroup()));
        }
        //验收是否通过
        if(null != dto.getAcceptorPass()){
            dto.getConditions().add(Restrict.eq("acceptorPass",dto.getAcceptorPass()));
        }
        //结算流程进度
        if(StringUtils.isNotBlank(dto.getSettlementProgress())){
            dto.getConditions().add(Restrict.like("settlementProgress",dto.getSettlementProgress()));
        }
    }

    @Override
    public OutsourcBusinessContractBO importExcel(List<OutsourcBusinessContractTO> contractTOS) throws SerException {
        List<OutsourcBusinessContract> contracts = new ArrayList<>(contractTOS.size());
        for(OutsourcBusinessContractTO to:contractTOS){
            OutsourcBusinessContract contract = BeanTransform.copyProperties(to,OutsourcBusinessContract.class,true);
            contracts.add(contract);
        }
        super.save(contracts);
        OutsourcBusinessContractBO bo = BeanTransform.copyProperties(new OutsourcBusinessContract(),OutsourcBusinessContractBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(OutsourcBusinessContractDTO dto) throws SerException {
        if(null != dto.getAreas()){
            dto.getConditions().add(Restrict.eq("area",dto.getAreas()));
        }
        List<OutsourcBusinessContract> list = super.findByCis(dto);
        List<OutsourcBusinessContractExport> exports = new ArrayList<>();
        list.stream().forEach(str->{
            OutsourcBusinessContractExport export = BeanTransform.copyProperties(str,OutsourcBusinessContractExport.class,
                    "measurePass","taskContract","makeContract","cooperation","complete",
                    "qualifiedGist","accept","acceptorPass","account","pay","closedLoop");
            //测算是否通过
            if(str.getMeasurePass().equals(true)){
                export.setMeasurePass("是");
            }else {
                export.setMeasurePass("否");
            }
            //是否有合同派工
            if(str.getTaskContract().equals(true)){
                export.setTaskContract("是");
            }else {
                export.setTaskContract("否");
            }
            //是否有合同立项
            if(str.getMakeContract().equals(true)){
                export.setMakeContract("是");
            }else {
                export.setMakeContract("否");
            }
            //是否确定合作
            if(str.getCooperation().equals(true)){
                export.setCooperation("是");
            }else {
                export.setCooperation("否");
            }
            //是否完工
            if (str.getComplete().equals(true)) {
                export.setComplete("是");
            }else {
                export.setComplete("否");
            }
            //是否提供完工合格依据
            if(str.getQualifiedGist().equals(true)){
                export.setQualifiedGist("是");
            }else {
                export.setQualifiedGist("否");
            }
            //是否验收
            if(str.getAccept().equals(true)){
                export.setAccept("是");
            }else {
                export.setAccept("否");
            }
            //验收是否通过
            if(str.getAcceptorPass().equals(true)){
                export.setAcceptorPass("是");
            }else {
                export.setAcceptorPass("否");
            }
            //是否到账
            if(str.getAccount().equals(true)){
                export.setAccount("是");
            }else {
                export.setAccount("否");
            }
            //是否付款成功
            if(str.getPay().equals(true)){
                export.setPay("是");
            }else {
                export.setPay("否");
            }
            //是否闭环
            if(str.getClosedLoop().equals(true)){
                export.setClosedLoop("是");
            }else {
                export.setClosedLoop("否");
            }
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<OutsourcBusinessContractTemplateExcel> templateExcels = new ArrayList<>();
        OutsourcBusinessContractTemplateExcel templateExcel = new OutsourcBusinessContractTemplateExcel();
        templateExcel.setMeasureClassify("内包");
        templateExcel.setMeasurePass("是");
        templateExcel.setSignedTime(LocalDate.now());
        templateExcel.setArea("test");
        templateExcel.setBusinessType("test");
        templateExcel.setBusinessSubject("test");
        templateExcel.setMajorCompany("test");
        templateExcel.setSubCompany("test");
        templateExcel.setProjectGroup("test");
        templateExcel.setTaskContract("是");
        templateExcel.setMarketNum("test");
        templateExcel.setType("test");
        templateExcel.setMajor("test");
        templateExcel.setMakeContract("预立项");
        templateExcel.setSupplierNum("test");
        templateExcel.setSupplierArea("test");
        templateExcel.setSupplierName("test");
        templateExcel.setSupplierType("test");
        templateExcel.setBusinessLiaison("test");
        templateExcel.setCooperation("是");
        templateExcel.setOutsourcingTime(LocalDate.now());
        templateExcel.setOutsourcingInterface("test");
        templateExcel.setOutsourcingMoney(0d);
        templateExcel.setTaxPoint("test");
        templateExcel.setInvoice(0d);
        templateExcel.setManagementFee(0d);
        templateExcel.setPaymentMethod("test");
        templateExcel.setSettlementBatch("test");
        templateExcel.setExpectStartDate(LocalDate.now());
        templateExcel.setRealityStartDate(LocalDate.now());
        templateExcel.setRealityCompleteTime(LocalDate.now());
        templateExcel.setExpectCompleteTime(LocalDate.now());
        templateExcel.setComplete("是");
        templateExcel.setQualifiedGist("是");
        templateExcel.setAccept("是");
        templateExcel.setAcceptor("test");
        templateExcel.setAcceptorPass("是");
        templateExcel.setAccount("是");
        templateExcel.setSettlementProgress("test");
        templateExcel.setPay("是");
        templateExcel.setSettlementTime(LocalDate.now());
        templateExcel.setClosedLoop("是");

        templateExcels.add(templateExcel);
        Excel excel = new Excel(0,2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels,excel);
        return bytes;
    }
}