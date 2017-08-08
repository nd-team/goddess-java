package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.costdetail.bo.*;
import com.bjike.goddess.costdetail.dto.*;
import com.bjike.goddess.costdetail.entity.*;
import com.bjike.goddess.costdetail.to.*;
import com.bjike.goddess.costdetail.type.GuideAddrStatus;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 成本明细业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-06 05:45 ]
 * @Description: [ 成本明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class CostDetailsSerImpl extends ServiceImpl<CostDetails, CostDetailsDTO> implements CostDetailsSer {

    @Autowired
    private BusinessIncomeDetailSer businessIncomeDetailSer;
    @Autowired
    private CompanyBorrowedDetailSer companyBorrowedDetailSer;
    @Autowired
    private CompanyLendDetailSer companyLendDetailSer;
    @Autowired
    private LaborCostDetailSer laborCostDetailSer;
    @Autowired
    private PaidCapitalDetailSer paidCapitalDetailSer;
    @Autowired
    private DetailTypeSer detailTypeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;


    /**
     * 检查权限
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
            throw new SerException("您不是财务部门人员,没有该操作权限");
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
            case SEE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(CostDetailsDTO costDetailsDTO) throws SerException {
        searchCondition(costDetailsDTO);
        Long count = super.count(costDetailsDTO);
        return count;
    }

    @Override
    public CostDetailsBO getOneById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        CostDetails costDetails = super.findById(id);
        return BeanTransform.copyProperties(costDetails, CostDetailsBO.class);
    }

    @Override
    public CostDetailsAddEditBO getAllById(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能呢为空");
        }
        CostDetails costDetails = super.findById(id);
        CostDetailsAddEditBO costDetailsAddEditBO = BeanTransform.copyProperties(costDetails, CostDetailsAddEditBO.class);
        //劳务成本明细
        List<LaborCostDetail> laborCostDetailList = laborCostDetailSer.findByCostId(id);
        List<String> laborTypeName = laborCostDetailSer.findTypeName();
        List<String> detailTypeName = detailTypeSer.findTypeName("劳务成本");
        List<String> TypeNameA = Arrays.asList();
        TypeNameA = detailTypeName.stream().filter(str -> !laborTypeName.contains(str)).collect(Collectors.toList());
        if (TypeNameA != null && TypeNameA.size() > 0) {
            TypeNameA.forEach(name -> {
                LaborCostDetail laborCostDetail = new LaborCostDetail();
                laborCostDetail.setTypeName(name);
                laborCostDetail.setLaborCostTen(0d);
                laborCostDetail.setLaborCostFift(0d);
                laborCostDetail.setLaborCostTwtenty(0d);
                laborCostDetail.setLaborCostThirty(0d);
                laborCostDetail.setLaborCostSum(0d);
                laborCostDetailList.add(laborCostDetail);
            });
        }
        //公司借入明细
        List<CompanyBorrowedDetail> companyBorrowedDetailList = companyBorrowedDetailSer.findByCostId(id);
        List<String> borrowTypeName = companyBorrowedDetailSer.findTypeName();
        List<String> borrDetTypeName = detailTypeSer.findTypeName("公司借入");
        List<String> TypeNameB = Arrays.asList();
        TypeNameB = borrDetTypeName.stream().filter(str -> !borrowTypeName.contains(str)).collect(Collectors.toList());
        if (TypeNameB != null && TypeNameB.size() > 0) {
            TypeNameB.forEach(name -> {
                CompanyBorrowedDetail companyBorrowedDetail = new CompanyBorrowedDetail();
                companyBorrowedDetail.setTypeName(name);
                companyBorrowedDetail.setCompanyBorrowedTen(0d);
                companyBorrowedDetail.setCompanyBorrowedFift(0d);
                companyBorrowedDetail.setCompanyBorrowedTwtenty(0d);
                companyBorrowedDetail.setCompanyBorrowedThirty(0d);
                companyBorrowedDetail.setCompanyBorrowedSum(0d);
                companyBorrowedDetailList.add(companyBorrowedDetail);
            });
        }
        //实收资本明细
        List<PaidCapitalDetail> paidCapitalDetailList = paidCapitalDetailSer.findByCostId(id);
        List<String> paiTypeName = paidCapitalDetailSer.findTypeName();
        List<String> paiDetTypeName = detailTypeSer.findTypeName("实收资本");
        List<String> TypeNameC = Arrays.asList();
        TypeNameC = paiDetTypeName.stream().filter(str -> !paiTypeName.contains(str)).collect(Collectors.toList());
        if (TypeNameC != null && TypeNameC.size() > 0) {
            TypeNameC.forEach(name -> {
                PaidCapitalDetail paidCapitalDetail = new PaidCapitalDetail();
                paidCapitalDetail.setTypeName(name);
                paidCapitalDetail.setPaidCapitalTen(0d);
                paidCapitalDetail.setPaidCapitalFift(0d);
                paidCapitalDetail.setPaidCapitalTwtenty(0d);
                paidCapitalDetail.setPaidCapitalThirty(0d);
                paidCapitalDetail.setPaidCapitalSum(0d);
                paidCapitalDetailList.add(paidCapitalDetail);
            });
        }
        //公司借出明细
        List<CompanyLendDetail> companyLendDetailList = companyLendDetailSer.findByCostId(id);
        List<String> lendTypeName = companyLendDetailSer.findTypeName();
        List<String> lendDetTypeName = detailTypeSer.findTypeName("公司借出");
        List<String> TypeNameD = Arrays.asList();
        TypeNameD = lendDetTypeName.stream().filter(str -> !lendTypeName.contains(str)).collect(Collectors.toList());
        if (TypeNameD != null && TypeNameD.size() > 0) {
            TypeNameD.forEach(name -> {
                CompanyLendDetail companyLendDetail = new CompanyLendDetail();
                companyLendDetail.setTypeName(name);
                companyLendDetail.setCompanyLendTen(0d);
                companyLendDetail.setCompanyLendFift(0d);
                companyLendDetail.setCompanyLendTwtenty(0d);
                companyLendDetail.setCompanyLendThirty(0d);
                companyLendDetail.setCompanyLendSum(0d);
                companyLendDetailList.add(companyLendDetail);
            });
        }
        //主营业务收入明细
        List<BusinessIncomeDetail> businessIncomeDetailList = businessIncomeDetailSer.findByCostId(id);
        List<String> businTypeName = businessIncomeDetailSer.findTypeName();
        List<String> businDetTypeName = detailTypeSer.findTypeName("主营业务收入");
        List<String> TypeNameE = Arrays.asList();
        TypeNameE = businDetTypeName.stream().filter(str -> !businTypeName.contains(str)).collect(Collectors.toList());
        if (TypeNameE != null && TypeNameE.size() > 0) {
            TypeNameE.forEach(name -> {
                BusinessIncomeDetail businessIncomeDetail = new BusinessIncomeDetail();
                businessIncomeDetail.setTypeName(name);
                businessIncomeDetail.setBusinessIncomeTen(0d);
                businessIncomeDetail.setBusinessIncomeFift(0d);
                businessIncomeDetail.setBusinessIncomeTwtenty(0d);
                businessIncomeDetail.setBusinessIncomeThirty(0d);
                businessIncomeDetail.setBusinessIncomeSum(0d);
                businessIncomeDetailList.add(businessIncomeDetail);
            });
        }

        costDetailsAddEditBO.setLaborCostDetailList(BeanTransform.copyProperties(laborCostDetailList, LaborCostDetailBO.class));
        costDetailsAddEditBO.setCompanyBorrowedDetailList(BeanTransform.copyProperties(companyBorrowedDetailList, CompanyBorrowedDetailBO.class));
        costDetailsAddEditBO.setPaidCapitalDetailList(BeanTransform.copyProperties(paidCapitalDetailList, PaidCapitalDetailBO.class));
        costDetailsAddEditBO.setCompanyLendDetailList(BeanTransform.copyProperties(companyLendDetailList, CompanyLendDetailBO.class));
        costDetailsAddEditBO.setBusinessIncomeDetailList(BeanTransform.copyProperties(businessIncomeDetailList, BusinessIncomeDetailBO.class));

        return costDetailsAddEditBO;
    }

    @Override
    public List<CostDetailsBO> list(CostDetailsDTO costDetailsDTO) throws SerException {
        checkPermission();
        searchCondition(costDetailsDTO);
        List<CostDetails> list = super.findByPage(costDetailsDTO);
        List<CostDetailsBO> baseInfoManageBOList = BeanTransform.copyProperties(list, CostDetailsBO.class);
        return baseInfoManageBOList;
    }

    @Override
    public CostDetailsBO add(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        checkPermission();
        //TODO lijuntao　需要从资金流水模块中读取数据进行添加未做
        //劳务成本明细
        List<LaborCostDetailTO> laborCostDetailTOList = costDetailsAddEditTO.getLaborCostDetailTOList();
        Double laborSum = 0d;
        Double laborTen = 0d;
        Double laborFift = 0d;
        Double laborTw = 0d;
        Double laborTh = 0d;
        if (laborCostDetailTOList != null && laborCostDetailTOList.size() > 0) {
            for (LaborCostDetailTO laborCostDetailTO : laborCostDetailTOList) {
                laborCostDetailTO.setLaborCostSum(laborCostDetailTO.getLaborCostTen() + laborCostDetailTO.getLaborCostFift() + laborCostDetailTO.getLaborCostTwtenty() + laborCostDetailTO.getLaborCostThirty());
                laborSum += laborCostDetailTO.getLaborCostSum();
                laborTen += laborCostDetailTO.getLaborCostTen();
                laborFift += laborCostDetailTO.getLaborCostFift();
                laborTw += laborCostDetailTO.getLaborCostTwtenty();
                laborTh += laborCostDetailTO.getLaborCostThirty();
            }
        }
        CostLaborMoneyTO costLaborMoneyTO = costDetailsAddEditTO.getCostLaborMoneyTO();
        costLaborMoneyTO.setLaborCostSum(laborSum);
        costLaborMoneyTO.setLaborCostTen(laborTen);
        costLaborMoneyTO.setLaborCostFift(laborFift);
        costLaborMoneyTO.setLaborCostTwtenty(laborTw);
        costLaborMoneyTO.setLaborCostThirty(laborTh);

        //公司借入明细
        List<CompanyBorrowedDetailTO> companyBorrowedDetailTOList = costDetailsAddEditTO.getCompanyBorrowedDetailTOList();
        Double borroSum = 0d;
        Double borroTen = 0d;
        Double borroFift = 0d;
        Double borroTw = 0d;
        Double borroTh = 0d;
        if (companyBorrowedDetailTOList != null && companyBorrowedDetailTOList.size() > 0) {

            for (CompanyBorrowedDetailTO companyBorrowedDetailTO : companyBorrowedDetailTOList) {
                companyBorrowedDetailTO.setCompanyBorrowedSum(companyBorrowedDetailTO.getCompanyBorrowedTen() + companyBorrowedDetailTO.getCompanyBorrowedFift() + companyBorrowedDetailTO.getCompanyBorrowedTwtenty() + companyBorrowedDetailTO.getCompanyBorrowedThirty());
                borroSum += companyBorrowedDetailTO.getCompanyBorrowedSum();
                borroTen += companyBorrowedDetailTO.getCompanyBorrowedTen();
                borroFift += companyBorrowedDetailTO.getCompanyBorrowedFift();
                borroTw += companyBorrowedDetailTO.getCompanyBorrowedTwtenty();
                borroTh += companyBorrowedDetailTO.getCompanyBorrowedThirty();
            }
        }
        CostBorrowMoneyTO costBorrowMoneyTO = costDetailsAddEditTO.getCostBorrowMoneyTO();
        costBorrowMoneyTO.setCompanyBorrowedSum(borroSum);
        costBorrowMoneyTO.setCompanyBorrowedTen(borroTen);
        costBorrowMoneyTO.setCompanyBorrowedFift(borroFift);
        costBorrowMoneyTO.setCompanyBorrowedTwtenty(borroTw);
        costBorrowMoneyTO.setCompanyBorrowedThirty(borroTh);

        //实收资本明细list
        List<PaidCapitalDetailTO> paidCapitalDetailTOList = costDetailsAddEditTO.getPaidCapitalDetailTOList();
        Double paidSum = 0d;
        Double paidTen = 0d;
        Double paidFift = 0d;
        Double paidTw = 0d;
        Double paidTh = 0d;
        if (paidCapitalDetailTOList != null && paidCapitalDetailTOList.size() > 0) {

            for (PaidCapitalDetailTO paidCapitalDetailTO : paidCapitalDetailTOList) {
                paidCapitalDetailTO.setPaidCapitalSum(paidCapitalDetailTO.getPaidCapitalTen() + paidCapitalDetailTO.getPaidCapitalFift() + paidCapitalDetailTO.getPaidCapitalTwtenty() + paidCapitalDetailTO.getPaidCapitalThirty());
                paidSum += paidCapitalDetailTO.getPaidCapitalSum();
                paidTen += paidCapitalDetailTO.getPaidCapitalTen();
                paidFift += paidCapitalDetailTO.getPaidCapitalFift();
                paidTw += paidCapitalDetailTO.getPaidCapitalTwtenty();
                paidTh += paidCapitalDetailTO.getPaidCapitalThirty();
            }
        }
        CostPaidMoneyTO paidMoneyTO = costDetailsAddEditTO.getCostPaidMoneyTO();
        paidMoneyTO.setPaidCapitalSum(paidSum);
        paidMoneyTO.setPaidCapitalTen(paidTen);
        paidMoneyTO.setPaidCapitalFift(paidFift);
        paidMoneyTO.setPaidCapitalTwtenty(paidTw);
        paidMoneyTO.setPaidCapitalThirty(paidTh);

        //公司借出明细list
        List<CompanyLendDetailTO> companyLendDetailTOList = costDetailsAddEditTO.getCompanyLendDetailTOList();
        Double lendSum = 0d;
        Double lendTen = 0d;
        Double lendFift = 0d;
        Double lendTw = 0d;
        Double lendTh = 0d;
        if (companyLendDetailTOList != null && companyLendDetailTOList.size() > 0) {

            for (CompanyLendDetailTO companyLendDetailTO : companyLendDetailTOList) {
                companyLendDetailTO.setCompanyLendSum(companyLendDetailTO.getCompanyLendTen() + companyLendDetailTO.getCompanyLendFift() + companyLendDetailTO.getCompanyLendTwtenty() + companyLendDetailTO.getCompanyLendThirty());
                lendSum += companyLendDetailTO.getCompanyLendSum();
                lendTen += companyLendDetailTO.getCompanyLendTen();
                lendFift += companyLendDetailTO.getCompanyLendFift();
                lendTw += companyLendDetailTO.getCompanyLendTwtenty();
                lendTh += companyLendDetailTO.getCompanyLendThirty();
            }
        }
        CostLendMoneyTO costLendMoneyTO = costDetailsAddEditTO.getCostLendMoneyTO();
        costLendMoneyTO.setCompanyLendSum(lendSum);
        costLendMoneyTO.setCompanyLendTen(lendTen);
        costLendMoneyTO.setCompanyLendFift(lendFift);
        costLendMoneyTO.setCompanyLendTwtenty(lendTw);
        costLendMoneyTO.setCompanyLendThirty(lendTh);

        //主营业务收入明细list
        List<BusinessIncomeDetailTO> businessIncomeDetailTOList = costDetailsAddEditTO.getBusinessIncomeDetailTOList();
        Double businSum = 0d;
        Double businTen = 0d;
        Double businFift = 0d;
        Double businTw = 0d;
        Double businTh = 0d;
        if (businessIncomeDetailTOList != null && businessIncomeDetailTOList.size() > 0) {

            for (BusinessIncomeDetailTO businessIncomeDetailTO : businessIncomeDetailTOList) {
                businessIncomeDetailTO.setBusinessIncomeSum(businessIncomeDetailTO.getBusinessIncomeTen() + businessIncomeDetailTO.getBusinessIncomeFift() + businessIncomeDetailTO.getBusinessIncomeTwtenty() + businessIncomeDetailTO.getBusinessIncomeThirty());
                businSum += businessIncomeDetailTO.getBusinessIncomeSum();
                businTen += businessIncomeDetailTO.getBusinessIncomeTen();
                businFift += businessIncomeDetailTO.getBusinessIncomeFift();
                businTw += businessIncomeDetailTO.getBusinessIncomeTwtenty();
                businTh += businessIncomeDetailTO.getBusinessIncomeThirty();
            }
        }
        CostIncomeMoneyTO costIncomeMoneyTO = costDetailsAddEditTO.getCostIncomeMoneyTO();
        costIncomeMoneyTO.setBusinessIncomeSum(businSum);
        costIncomeMoneyTO.setBusinessIncomeTen(businTen);
        costIncomeMoneyTO.setBusinessIncomeFift(businFift);
        costIncomeMoneyTO.setBusinessIncomeTwtenty(businTw);
        costIncomeMoneyTO.setBusinessIncomeThirty(businTh);

        //预估应收账款合计
        CostForeMoneyTO costForeMoneyTO = costDetailsAddEditTO.getCostForeMoneyTO();
        costForeMoneyTO.setForecastAccountSum(costForeMoneyTO.getForecastAccountTen() + costForeMoneyTO.getForecastAccountFift() + costForeMoneyTO.getForecastAccountTwtenty() + costForeMoneyTO.getForecastAccountThirty());

        //应交税金
        CostPayeMoneyTO costPayeMoneyTO = costDetailsAddEditTO.getCostPayeMoneyTO();
        costPayeMoneyTO.setPayableTaxSum(costPayeMoneyTO.getPayableTaxTen() + costPayeMoneyTO.getPayableTaxFift() + costPayeMoneyTO.getPayableTaxTwtenty() + costPayeMoneyTO.getPayableTaxThirty());

        //营业成本
        CostBusinessMoneyTO costBusinessMoneyTO = costDetailsAddEditTO.getCostBusinessMoneyTO();
        costBusinessMoneyTO.setOperatingSum(costLaborMoneyTO.getLaborCostSum() + costPayeMoneyTO.getPayableTaxSum());
        costBusinessMoneyTO.setOperatingTen(costLaborMoneyTO.getLaborCostTen() + costPayeMoneyTO.getPayableTaxTen());
        costBusinessMoneyTO.setOperatingFift(costLaborMoneyTO.getLaborCostFift() + costPayeMoneyTO.getPayableTaxFift());
        costBusinessMoneyTO.setOperatingTwtenty(costLaborMoneyTO.getLaborCostTwtenty() + costPayeMoneyTO.getPayableTaxTwtenty());
        costBusinessMoneyTO.setOperatingThirty(costLaborMoneyTO.getLaborCostThirty() + costPayeMoneyTO.getPayableTaxThirty());

//        //实际资金缺口合计
//        CostActualMoneyTO costActualMoneyTO = costDetailsAddEditTO.getCostActualMoneyTO();
//        costActualMoneyTO.setActualGapSum();
        //按时回款预估结余资金合计

        //初期余额
        CostBeginMoneyTO costBeginMoneyTO = costDetailsAddEditTO.getCostBeginMoneyTO();
        //实际资金缺口合计
        CostActualMoneyTO costActualMoneyTO = costDetailsAddEditTO.getCostActualMoneyTO();
        //期初余额-营业成本+公司借入合计+实收资本-公司借出合计+主营业务收入
        costActualMoneyTO.setActualGapSum(costBeginMoneyTO.getBeginBalanceSum() - costBusinessMoneyTO.getOperatingSum()
                + costBorrowMoneyTO.getCompanyBorrowedSum() + paidMoneyTO.getPaidCapitalSum() - costLendMoneyTO.getCompanyLendSum()
                + costIncomeMoneyTO.getBusinessIncomeSum());

        costBeginMoneyTO.setBeginBalanceTen(costBeginMoneyTO.getBeginBalanceSum());
        costActualMoneyTO.setActualGapTen(costBeginMoneyTO.getBeginBalanceTen() - costBusinessMoneyTO.getOperatingTen()
                + costBorrowMoneyTO.getCompanyBorrowedTen() + paidMoneyTO.getPaidCapitalTen() - costLendMoneyTO.getCompanyLendTen()
                + costIncomeMoneyTO.getBusinessIncomeTen());

        costBeginMoneyTO.setBeginBalanceFift(costActualMoneyTO.getActualGapTen());
        costActualMoneyTO.setActualGapFift(costBeginMoneyTO.getBeginBalanceFift() - costBusinessMoneyTO.getOperatingFift()
                + costBorrowMoneyTO.getCompanyBorrowedFift() + paidMoneyTO.getPaidCapitalFift() - costLendMoneyTO.getCompanyLendFift()
                + costIncomeMoneyTO.getBusinessIncomeFift());

        costBeginMoneyTO.setBeginBalanceTwtenty(costActualMoneyTO.getActualGapFift());
        costActualMoneyTO.setActualGapTwtenty(costBeginMoneyTO.getBeginBalanceTwtenty() - costBusinessMoneyTO.getOperatingTwtenty()
                + costBorrowMoneyTO.getCompanyBorrowedTwtenty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendTwtenty()
                + costIncomeMoneyTO.getBusinessIncomeTwtenty());

        costBeginMoneyTO.setBeginBalanceThirty(costActualMoneyTO.getActualGapTwtenty());
        costActualMoneyTO.setActualGapThirty(costBeginMoneyTO.getBeginBalanceThirty() - costBusinessMoneyTO.getOperatingThirty()
                + costBorrowMoneyTO.getCompanyBorrowedThirty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendThirty()
                + costIncomeMoneyTO.getBusinessIncomeThirty());

        //按时回款预估结余资金
        CostBalanceMoneyTO costBalanceMoneyTO = costDetailsAddEditTO.getCostBalanceMoneyTO();
        //期初余额-营业成本+公司借入合计+实收资本-公司借出合计+主营业务收入+实际资金缺口
        costBalanceMoneyTO.setForecastBalanceMoSum(costBeginMoneyTO.getBeginBalanceSum() - costBusinessMoneyTO.getOperatingSum()
                + costBorrowMoneyTO.getCompanyBorrowedSum() + paidMoneyTO.getPaidCapitalSum() - costLendMoneyTO.getCompanyLendSum()
                + costIncomeMoneyTO.getBusinessIncomeSum() + costActualMoneyTO.getActualGapSum());

        costBalanceMoneyTO.setForecastBalanceMoGapTen(costBeginMoneyTO.getBeginBalanceTen() - costBusinessMoneyTO.getOperatingTen()
                + costBorrowMoneyTO.getCompanyBorrowedTen() + paidMoneyTO.getPaidCapitalTen() - costLendMoneyTO.getCompanyLendTen()
                + costIncomeMoneyTO.getBusinessIncomeTen() + costActualMoneyTO.getActualGapTen());

        costBalanceMoneyTO.setForecastBalanceMoGapFift(costBeginMoneyTO.getBeginBalanceFift() - costBusinessMoneyTO.getOperatingFift()
                + costBorrowMoneyTO.getCompanyBorrowedFift() + paidMoneyTO.getPaidCapitalFift() - costLendMoneyTO.getCompanyLendFift()
                + costIncomeMoneyTO.getBusinessIncomeFift() + costActualMoneyTO.getActualGapFift());

        costBalanceMoneyTO.setForecastBalanceMoTwtenty(costBeginMoneyTO.getBeginBalanceTwtenty() - costBusinessMoneyTO.getOperatingTwtenty()
                + costBorrowMoneyTO.getCompanyBorrowedTwtenty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendTwtenty()
                + costIncomeMoneyTO.getBusinessIncomeTwtenty() + costActualMoneyTO.getActualGapTwtenty());

        costBalanceMoneyTO.setForecastBalanceMoThirty(costBeginMoneyTO.getBeginBalanceThirty() - costBusinessMoneyTO.getOperatingThirty()
                + costBorrowMoneyTO.getCompanyBorrowedThirty() + paidMoneyTO.getPaidCapitalThirty() - costLendMoneyTO.getCompanyLendThirty()
                + costIncomeMoneyTO.getBusinessIncomeThirty() + costActualMoneyTO.getActualGapThirty());

        CostDetails costDetails = new CostDetails();
        BeanTransform.copyProperties(costActualMoneyTO, costDetails);
        BeanTransform.copyProperties(costBalanceMoneyTO, costDetails);
        BeanTransform.copyProperties(costBeginMoneyTO, costDetails);
        BeanTransform.copyProperties(costBorrowMoneyTO, costDetails);
        BeanTransform.copyProperties(costBusinessMoneyTO, costDetails);
        BeanTransform.copyProperties(costForeMoneyTO, costDetails);
        BeanTransform.copyProperties(costIncomeMoneyTO, costDetails);
        BeanTransform.copyProperties(costLaborMoneyTO, costDetails);
        BeanTransform.copyProperties(costLendMoneyTO, costDetails);
        BeanTransform.copyProperties(paidMoneyTO, costDetails);
        BeanTransform.copyProperties(costPayeMoneyTO, costDetails);

        String date = StringUtils.substring(costDetailsAddEditTO.getCostTime(), 0, -2) + "01";
        costDetails.setCreateTime(LocalDateTime.now());
        costDetails.setCostTime(DateUtil.parseDate(date));
        costDetails.setTestTime(LocalDate.now());
        costDetails.setDepartment(costDetailsAddEditTO.getDepartment());
        costDetails = super.save(costDetails);
        //添加劳务成本明细
        if (costDetailsAddEditTO.getLaborCostDetailTOList() != null && costDetailsAddEditTO.getLaborCostDetailTOList().size() > 0) {
            for (LaborCostDetailTO laborCostDetailTO : costDetailsAddEditTO.getLaborCostDetailTOList()) {
                LaborCostDetail laborCostDetail = BeanTransform.copyProperties(laborCostDetailTO, LaborCostDetail.class, true);
                laborCostDetail.setLaborCostSum(laborCostDetail.getLaborCostTen() + laborCostDetail.getLaborCostFift() + laborCostDetail.getLaborCostTwtenty() + laborCostDetail.getLaborCostThirty());
                laborCostDetail.setCreateTime(LocalDateTime.now());
                laborCostDetail.setCostTime(costDetails.getCostTime());
                laborCostDetail.setDepartment(costDetails.getDepartment());
                laborCostDetail.setCostId(costDetails.getId());
                laborCostDetailSer.save(laborCostDetail);
            }
        }
        //添加公司借入明细
        if (costDetailsAddEditTO.getCompanyBorrowedDetailTOList() != null && costDetailsAddEditTO.getCompanyBorrowedDetailTOList().size() > 0) {
            for (CompanyBorrowedDetailTO companyBorrowedDetailTO : costDetailsAddEditTO.getCompanyBorrowedDetailTOList()) {
                CompanyBorrowedDetail companyBorrowedDetail = BeanTransform.copyProperties(companyBorrowedDetailTO, CompanyBorrowedDetail.class, "true");
                companyBorrowedDetail.setCompanyBorrowedSum(companyBorrowedDetail.getCompanyBorrowedTen() + companyBorrowedDetail.getCompanyBorrowedFift() + companyBorrowedDetail.getCompanyBorrowedTwtenty() + companyBorrowedDetail.getCompanyBorrowedThirty());
                companyBorrowedDetail.setCreateTime(LocalDateTime.now());
                companyBorrowedDetail.setCostTime(costDetails.getCostTime());
                companyBorrowedDetail.setDepartment(costDetails.getDepartment());
                companyBorrowedDetail.setCostId(costDetails.getId());
                companyBorrowedDetailSer.save(companyBorrowedDetail);
            }
        }
        //添加实收资本明细
        if (costDetailsAddEditTO.getPaidCapitalDetailTOList() != null && costDetailsAddEditTO.getPaidCapitalDetailTOList().size() > 0) {
            for (PaidCapitalDetailTO paidCapitalDetailTO : costDetailsAddEditTO.getPaidCapitalDetailTOList()) {
                PaidCapitalDetail paidCapitalDetail = BeanTransform.copyProperties(paidCapitalDetailTO, PaidCapitalDetail.class, "true");
                paidCapitalDetail.setPaidCapitalSum(paidCapitalDetail.getPaidCapitalTen() + paidCapitalDetail.getPaidCapitalFift() + paidCapitalDetail.getPaidCapitalTwtenty() + paidCapitalDetail.getPaidCapitalThirty());
                paidCapitalDetail.setCreateTime(LocalDateTime.now());
                paidCapitalDetail.setCostTime(costDetails.getCostTime());
                paidCapitalDetail.setDepartment(costDetails.getDepartment());
                paidCapitalDetail.setCostId(costDetails.getId());
                paidCapitalDetailSer.save(paidCapitalDetail);
            }
        }
        //添加公司借出明细
        if (costDetailsAddEditTO.getCompanyLendDetailTOList() != null && costDetailsAddEditTO.getCompanyLendDetailTOList().size() > 0) {
            for (CompanyLendDetailTO companyLendDetailTO : costDetailsAddEditTO.getCompanyLendDetailTOList()) {
                CompanyLendDetail companyLendDetail = BeanTransform.copyProperties(companyLendDetailTO, CompanyLendDetail.class, "true");
                companyLendDetail.setCompanyLendSum(companyLendDetail.getCompanyLendTen() + companyLendDetail.getCompanyLendFift() + companyLendDetail.getCompanyLendTwtenty() + companyLendDetail.getCompanyLendThirty());
                companyLendDetail.setCreateTime(LocalDateTime.now());
                companyLendDetail.setCostTime(costDetails.getCostTime());
                companyLendDetail.setDepartment(costDetails.getDepartment());
                companyLendDetail.setCostId(costDetails.getId());
                companyLendDetailSer.save(companyLendDetail);
            }
        }
        //添加主营业务明细
        if (costDetailsAddEditTO.getBusinessIncomeDetailTOList() != null && costDetailsAddEditTO.getBusinessIncomeDetailTOList().size() > 0) {
            for (BusinessIncomeDetailTO businessIncomeDetailTO : costDetailsAddEditTO.getBusinessIncomeDetailTOList()) {
                BusinessIncomeDetail businessIncomeDetail = BeanTransform.copyProperties(businessIncomeDetailTO, BusinessIncomeDetail.class, "true");
                businessIncomeDetail.setBusinessIncomeSum(businessIncomeDetail.getBusinessIncomeTen() + businessIncomeDetail.getBusinessIncomeFift() + businessIncomeDetail.getBusinessIncomeTwtenty() + businessIncomeDetail.getBusinessIncomeThirty());
                businessIncomeDetail.setCreateTime(LocalDateTime.now());
                businessIncomeDetail.setCostTime(costDetails.getCostTime());
                businessIncomeDetail.setDepartment(costDetails.getDepartment());
                businessIncomeDetail.setCostId(costDetails.getId());
                businessIncomeDetailSer.save(businessIncomeDetail);
            }
        }

        return BeanTransform.copyProperties(costDetails, CostDetailsBO.class);
    }

    @Override
    public CostDetailsBO edit(CostDetailsAddEditTO costDetailsAddEditTO) throws SerException {
        checkPermission();

        //劳务成本明细
        List<LaborCostDetailTO> laborCostDetailTOList = costDetailsAddEditTO.getLaborCostDetailTOList();
        Double laborSum = 0d;
        Double laborTen = 0d;
        Double laborFift = 0d;
        Double laborTw = 0d;
        Double laborTh = 0d;
        if (laborCostDetailTOList != null && laborCostDetailTOList.size() > 0) {
            for (LaborCostDetailTO laborCostDetailTO : laborCostDetailTOList) {
                laborCostDetailTO.setLaborCostSum(laborCostDetailTO.getLaborCostTen() + laborCostDetailTO.getLaborCostFift() + laborCostDetailTO.getLaborCostTwtenty() + laborCostDetailTO.getLaborCostThirty());
                laborSum += laborCostDetailTO.getLaborCostSum();
                laborTen += laborCostDetailTO.getLaborCostTen();
                laborFift += laborCostDetailTO.getLaborCostFift();
                laborTw += laborCostDetailTO.getLaborCostTwtenty();
                laborTh += laborCostDetailTO.getLaborCostThirty();
            }
        }
        CostLaborMoneyTO costLaborMoneyTO = costDetailsAddEditTO.getCostLaborMoneyTO();
        costLaborMoneyTO.setLaborCostSum(laborSum);
        costLaborMoneyTO.setLaborCostTen(laborTen);
        costLaborMoneyTO.setLaborCostFift(laborFift);
        costLaborMoneyTO.setLaborCostTwtenty(laborTw);
        costLaborMoneyTO.setLaborCostThirty(laborTh);

        //公司借入明细
        List<CompanyBorrowedDetailTO> companyBorrowedDetailTOList = costDetailsAddEditTO.getCompanyBorrowedDetailTOList();
        Double borroSum = 0d;
        Double borroTen = 0d;
        Double borroFift = 0d;
        Double borroTw = 0d;
        Double borroTh = 0d;
        if (companyBorrowedDetailTOList != null && companyBorrowedDetailTOList.size() > 0) {

            for (CompanyBorrowedDetailTO companyBorrowedDetailTO : companyBorrowedDetailTOList) {
                companyBorrowedDetailTO.setCompanyBorrowedSum(companyBorrowedDetailTO.getCompanyBorrowedTen() + companyBorrowedDetailTO.getCompanyBorrowedFift() + companyBorrowedDetailTO.getCompanyBorrowedTwtenty() + companyBorrowedDetailTO.getCompanyBorrowedThirty());
                borroSum += companyBorrowedDetailTO.getCompanyBorrowedSum();
                borroTen += companyBorrowedDetailTO.getCompanyBorrowedTen();
                borroFift += companyBorrowedDetailTO.getCompanyBorrowedFift();
                borroTw += companyBorrowedDetailTO.getCompanyBorrowedTwtenty();
                borroTh += companyBorrowedDetailTO.getCompanyBorrowedThirty();
            }
        }
        CostBorrowMoneyTO costBorrowMoneyTO = costDetailsAddEditTO.getCostBorrowMoneyTO();
        costBorrowMoneyTO.setCompanyBorrowedSum(borroSum);
        costBorrowMoneyTO.setCompanyBorrowedTen(borroTen);
        costBorrowMoneyTO.setCompanyBorrowedFift(borroFift);
        costBorrowMoneyTO.setCompanyBorrowedTwtenty(borroTw);
        costBorrowMoneyTO.setCompanyBorrowedThirty(borroTh);

        //实收资本明细list
        List<PaidCapitalDetailTO> paidCapitalDetailTOList = costDetailsAddEditTO.getPaidCapitalDetailTOList();
        Double paidSum = 0d;
        Double paidTen = 0d;
        Double paidFift = 0d;
        Double paidTw = 0d;
        Double paidTh = 0d;
        if (paidCapitalDetailTOList != null && paidCapitalDetailTOList.size() > 0) {

            for (PaidCapitalDetailTO paidCapitalDetailTO : paidCapitalDetailTOList) {
                paidCapitalDetailTO.setPaidCapitalSum(paidCapitalDetailTO.getPaidCapitalTen() + paidCapitalDetailTO.getPaidCapitalFift() + paidCapitalDetailTO.getPaidCapitalTwtenty() + paidCapitalDetailTO.getPaidCapitalThirty());
                paidSum += paidCapitalDetailTO.getPaidCapitalSum();
                paidTen += paidCapitalDetailTO.getPaidCapitalTen();
                paidFift += paidCapitalDetailTO.getPaidCapitalFift();
                paidTw += paidCapitalDetailTO.getPaidCapitalTwtenty();
                paidTh += paidCapitalDetailTO.getPaidCapitalThirty();
            }
        }
        CostPaidMoneyTO paidMoneyTO = costDetailsAddEditTO.getCostPaidMoneyTO();
        paidMoneyTO.setPaidCapitalSum(paidSum);
        paidMoneyTO.setPaidCapitalTen(paidTen);
        paidMoneyTO.setPaidCapitalFift(paidFift);
        paidMoneyTO.setPaidCapitalTwtenty(paidTw);
        paidMoneyTO.setPaidCapitalThirty(paidTh);

        //公司借出明细list
        List<CompanyLendDetailTO> companyLendDetailTOList = costDetailsAddEditTO.getCompanyLendDetailTOList();
        Double lendSum = 0d;
        Double lendTen = 0d;
        Double lendFift = 0d;
        Double lendTw = 0d;
        Double lendTh = 0d;
        if (companyLendDetailTOList != null && companyLendDetailTOList.size() > 0) {

            for (CompanyLendDetailTO companyLendDetailTO : companyLendDetailTOList) {
                companyLendDetailTO.setCompanyLendSum(companyLendDetailTO.getCompanyLendTen() + companyLendDetailTO.getCompanyLendFift() + companyLendDetailTO.getCompanyLendTwtenty() + companyLendDetailTO.getCompanyLendThirty());
                lendSum += companyLendDetailTO.getCompanyLendSum();
                lendTen += companyLendDetailTO.getCompanyLendTen();
                lendFift += companyLendDetailTO.getCompanyLendFift();
                lendTw += companyLendDetailTO.getCompanyLendTwtenty();
                lendTh += companyLendDetailTO.getCompanyLendThirty();
            }
        }
        CostLendMoneyTO costLendMoneyTO = costDetailsAddEditTO.getCostLendMoneyTO();
        costLendMoneyTO.setCompanyLendSum(lendSum);
        costLendMoneyTO.setCompanyLendTen(lendTen);
        costLendMoneyTO.setCompanyLendFift(lendFift);
        costLendMoneyTO.setCompanyLendTwtenty(lendTw);
        costLendMoneyTO.setCompanyLendThirty(lendTh);

        //主营业务收入明细list
        List<BusinessIncomeDetailTO> businessIncomeDetailTOList = costDetailsAddEditTO.getBusinessIncomeDetailTOList();
        Double businSum = 0d;
        Double businTen = 0d;
        Double businFift = 0d;
        Double businTw = 0d;
        Double businTh = 0d;
        if (businessIncomeDetailTOList != null && businessIncomeDetailTOList.size() > 0) {

            for (BusinessIncomeDetailTO businessIncomeDetailTO : businessIncomeDetailTOList) {
                businessIncomeDetailTO.setBusinessIncomeSum(businessIncomeDetailTO.getBusinessIncomeTen() + businessIncomeDetailTO.getBusinessIncomeFift() + businessIncomeDetailTO.getBusinessIncomeTwtenty() + businessIncomeDetailTO.getBusinessIncomeThirty());
                businSum += businessIncomeDetailTO.getBusinessIncomeSum();
                businTen += businessIncomeDetailTO.getBusinessIncomeTen();
                businFift += businessIncomeDetailTO.getBusinessIncomeFift();
                businTw += businessIncomeDetailTO.getBusinessIncomeTwtenty();
                businTh += businessIncomeDetailTO.getBusinessIncomeThirty();
            }
        }
        CostIncomeMoneyTO costIncomeMoneyTO = costDetailsAddEditTO.getCostIncomeMoneyTO();
        costIncomeMoneyTO.setBusinessIncomeSum(businSum);
        costIncomeMoneyTO.setBusinessIncomeTen(businTen);
        costIncomeMoneyTO.setBusinessIncomeFift(businFift);
        costIncomeMoneyTO.setBusinessIncomeTwtenty(businTw);
        costIncomeMoneyTO.setBusinessIncomeThirty(businTh);

        //预估应收账款合计
        CostForeMoneyTO costForeMoneyTO = costDetailsAddEditTO.getCostForeMoneyTO();
        costForeMoneyTO.setForecastAccountSum(costForeMoneyTO.getForecastAccountTen() + costForeMoneyTO.getForecastAccountFift() + costForeMoneyTO.getForecastAccountTwtenty() + costForeMoneyTO.getForecastAccountThirty());

        //应交税金
        CostPayeMoneyTO costPayeMoneyTO = costDetailsAddEditTO.getCostPayeMoneyTO();
        costPayeMoneyTO.setPayableTaxSum(costPayeMoneyTO.getPayableTaxTen() + costPayeMoneyTO.getPayableTaxFift() + costPayeMoneyTO.getPayableTaxTwtenty() + costPayeMoneyTO.getPayableTaxThirty());

        //营业成本
        CostBusinessMoneyTO costBusinessMoneyTO = costDetailsAddEditTO.getCostBusinessMoneyTO();
        costBusinessMoneyTO.setOperatingSum(costLaborMoneyTO.getLaborCostSum() + costPayeMoneyTO.getPayableTaxSum());
        costBusinessMoneyTO.setOperatingTen(costLaborMoneyTO.getLaborCostTen() + costPayeMoneyTO.getPayableTaxTen());
        costBusinessMoneyTO.setOperatingFift(costLaborMoneyTO.getLaborCostFift() + costPayeMoneyTO.getPayableTaxFift());
        costBusinessMoneyTO.setOperatingTwtenty(costLaborMoneyTO.getLaborCostTwtenty() + costPayeMoneyTO.getPayableTaxTwtenty());
        costBusinessMoneyTO.setOperatingThirty(costLaborMoneyTO.getLaborCostThirty() + costPayeMoneyTO.getPayableTaxThirty());

//        //实际资金缺口合计
//        CostActualMoneyTO costActualMoneyTO = costDetailsAddEditTO.getCostActualMoneyTO();
//        costActualMoneyTO.setActualGapSum();
        //按时回款预估结余资金合计

        //初期余额
        CostBeginMoneyTO costBeginMoneyTO = costDetailsAddEditTO.getCostBeginMoneyTO();
        //实际资金缺口合计
        CostActualMoneyTO costActualMoneyTO = costDetailsAddEditTO.getCostActualMoneyTO();
        //期初余额-营业成本+公司借入合计+实收资本-公司借出合计+主营业务收入
        costActualMoneyTO.setActualGapSum(costBeginMoneyTO.getBeginBalanceSum() - costBusinessMoneyTO.getOperatingSum()
                + costBorrowMoneyTO.getCompanyBorrowedSum() + paidMoneyTO.getPaidCapitalSum() - costLendMoneyTO.getCompanyLendSum()
                + costIncomeMoneyTO.getBusinessIncomeSum());

        costBeginMoneyTO.setBeginBalanceTen(costBeginMoneyTO.getBeginBalanceSum());
        costActualMoneyTO.setActualGapTen(costBeginMoneyTO.getBeginBalanceTen() - costBusinessMoneyTO.getOperatingTen()
                + costBorrowMoneyTO.getCompanyBorrowedTen() + paidMoneyTO.getPaidCapitalTen() - costLendMoneyTO.getCompanyLendTen()
                + costIncomeMoneyTO.getBusinessIncomeTen());

        costBeginMoneyTO.setBeginBalanceFift(costActualMoneyTO.getActualGapTen());
        costActualMoneyTO.setActualGapFift(costBeginMoneyTO.getBeginBalanceFift() - costBusinessMoneyTO.getOperatingFift()
                + costBorrowMoneyTO.getCompanyBorrowedFift() + paidMoneyTO.getPaidCapitalFift() - costLendMoneyTO.getCompanyLendFift()
                + costIncomeMoneyTO.getBusinessIncomeFift());

        costBeginMoneyTO.setBeginBalanceTwtenty(costActualMoneyTO.getActualGapFift());
        costActualMoneyTO.setActualGapTwtenty(costBeginMoneyTO.getBeginBalanceTwtenty() - costBusinessMoneyTO.getOperatingTwtenty()
                + costBorrowMoneyTO.getCompanyBorrowedTwtenty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendTwtenty()
                + costIncomeMoneyTO.getBusinessIncomeTwtenty());

        costBeginMoneyTO.setBeginBalanceThirty(costActualMoneyTO.getActualGapTwtenty());
        costActualMoneyTO.setActualGapThirty(costBeginMoneyTO.getBeginBalanceThirty() - costBusinessMoneyTO.getOperatingThirty()
                + costBorrowMoneyTO.getCompanyBorrowedThirty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendThirty()
                + costIncomeMoneyTO.getBusinessIncomeThirty());

        //按时回款预估结余资金
        CostBalanceMoneyTO costBalanceMoneyTO = costDetailsAddEditTO.getCostBalanceMoneyTO();
        //期初余额-营业成本+公司借入合计+实收资本-公司借出合计+主营业务收入+实际资金缺口
        costBalanceMoneyTO.setForecastBalanceMoSum(costBeginMoneyTO.getBeginBalanceSum() - costBusinessMoneyTO.getOperatingSum()
                + costBorrowMoneyTO.getCompanyBorrowedSum() + paidMoneyTO.getPaidCapitalSum() - costLendMoneyTO.getCompanyLendSum()
                + costIncomeMoneyTO.getBusinessIncomeSum() + costActualMoneyTO.getActualGapSum());

        costBalanceMoneyTO.setForecastBalanceMoGapTen(costBeginMoneyTO.getBeginBalanceTen() - costBusinessMoneyTO.getOperatingTen()
                + costBorrowMoneyTO.getCompanyBorrowedTen() + paidMoneyTO.getPaidCapitalTen() - costLendMoneyTO.getCompanyLendTen()
                + costIncomeMoneyTO.getBusinessIncomeTen() + costActualMoneyTO.getActualGapTen());

        costBalanceMoneyTO.setForecastBalanceMoGapFift(costBeginMoneyTO.getBeginBalanceFift() - costBusinessMoneyTO.getOperatingFift()
                + costBorrowMoneyTO.getCompanyBorrowedFift() + paidMoneyTO.getPaidCapitalFift() - costLendMoneyTO.getCompanyLendFift()
                + costIncomeMoneyTO.getBusinessIncomeFift() + costActualMoneyTO.getActualGapFift());

        costBalanceMoneyTO.setForecastBalanceMoTwtenty(costBeginMoneyTO.getBeginBalanceTwtenty() - costBusinessMoneyTO.getOperatingTwtenty()
                + costBorrowMoneyTO.getCompanyBorrowedTwtenty() + paidMoneyTO.getPaidCapitalTwtenty() - costLendMoneyTO.getCompanyLendTwtenty()
                + costIncomeMoneyTO.getBusinessIncomeTwtenty() + costActualMoneyTO.getActualGapTwtenty());

        costBalanceMoneyTO.setForecastBalanceMoThirty(costBeginMoneyTO.getBeginBalanceThirty() - costBusinessMoneyTO.getOperatingThirty()
                + costBorrowMoneyTO.getCompanyBorrowedThirty() + paidMoneyTO.getPaidCapitalThirty() - costLendMoneyTO.getCompanyLendThirty()
                + costIncomeMoneyTO.getBusinessIncomeThirty() + costActualMoneyTO.getActualGapThirty());


        CostDetails costDetails = super.findById(costDetailsAddEditTO.getId());
        BeanTransform.copyProperties(costActualMoneyTO, costDetails);
        BeanTransform.copyProperties(costBalanceMoneyTO, costDetails);
        BeanTransform.copyProperties(costBeginMoneyTO, costDetails);
        BeanTransform.copyProperties(costBorrowMoneyTO, costDetails);
        BeanTransform.copyProperties(costBusinessMoneyTO, costDetails);
        BeanTransform.copyProperties(costForeMoneyTO, costDetails);
        BeanTransform.copyProperties(costIncomeMoneyTO, costDetails);
        BeanTransform.copyProperties(costLaborMoneyTO, costDetails);
        BeanTransform.copyProperties(costLendMoneyTO, costDetails);
        BeanTransform.copyProperties(paidMoneyTO, costDetails);
        BeanTransform.copyProperties(costPayeMoneyTO, costDetails);
        costDetails.setModifyTime(LocalDateTime.now());
        super.update(costDetails);

        //编辑劳务成本明细
        if (costDetailsAddEditTO.getLaborCostDetailTOList() != null && costDetailsAddEditTO.getLaborCostDetailTOList().size() > 0) {
            List<LaborCostDetail> laborCostDetails = laborCostDetailSer.findByCostId(costDetails.getId());
            for (LaborCostDetailTO laborCostDetailTO : costDetailsAddEditTO.getLaborCostDetailTOList()) {
                boolean exist = false;
                for (LaborCostDetail laborCostDetail : laborCostDetails) {
                    if (laborCostDetailTO.getTypeName().equals(laborCostDetail.getTypeName())) {
                        BeanTransform.copyProperties(laborCostDetailTO, laborCostDetail, true);
                        laborCostDetail.setLaborCostSum(laborCostDetail.getLaborCostTen() + laborCostDetail.getLaborCostFift() + laborCostDetail.getLaborCostTwtenty() + laborCostDetail.getLaborCostThirty());
                        laborCostDetail.setModifyTime(LocalDateTime.now());
                        exist = true;
                    }
                }
                if (!exist) {
                    LaborCostDetail detail = BeanTransform.copyProperties(laborCostDetailTO, LaborCostDetail.class, true);
                    detail.setLaborCostSum(detail.getLaborCostTen() + detail.getLaborCostFift() + detail.getLaborCostTwtenty() + detail.getLaborCostThirty());
                    detail.setCostId(costDetails.getId());
                    detail.setCostTime(costDetails.getCostTime());
                    detail.setDepartment(costDetails.getDepartment());
                    detail.setCreateTime(LocalDateTime.now());
                    laborCostDetailSer.save(detail);
                }

            }
            laborCostDetailSer.update(laborCostDetails);

        }
        //编辑公司借入明细
        if (costDetailsAddEditTO.getCompanyBorrowedDetailTOList() != null && costDetailsAddEditTO.getCompanyBorrowedDetailTOList().size() > 0) {
            List<CompanyBorrowedDetail> companyBorrowedDetails = companyBorrowedDetailSer.findByCostId(costDetailsAddEditTO.getId());
            for (CompanyBorrowedDetailTO companyBorrowedDetailTO : costDetailsAddEditTO.getCompanyBorrowedDetailTOList()) {
                boolean exist = false;
                for (CompanyBorrowedDetail companyBorrowedDetail : companyBorrowedDetails) {
                    if (companyBorrowedDetailTO.getTypeName().equals(companyBorrowedDetail.getTypeName())) {
                        BeanTransform.copyProperties(companyBorrowedDetailTO, companyBorrowedDetail, true);
                        companyBorrowedDetail.setCompanyBorrowedSum(companyBorrowedDetail.getCompanyBorrowedTen() + companyBorrowedDetail.getCompanyBorrowedFift() + companyBorrowedDetail.getCompanyBorrowedTwtenty() + companyBorrowedDetail.getCompanyBorrowedThirty());
                        companyBorrowedDetail.setModifyTime(LocalDateTime.now());
                        exist = true;
                    }
                }
                if (!exist) {
                    CompanyBorrowedDetail detail = BeanTransform.copyProperties(companyBorrowedDetailTO, CompanyBorrowedDetail.class, true);
                    detail.setCostId(costDetails.getId());
                    detail.setCreateTime(LocalDateTime.now());
                    detail.setCostTime(costDetails.getCostTime());
                    detail.setDepartment(costDetails.getDepartment());
                    detail.setCompanyBorrowedSum(detail.getCompanyBorrowedTen() + detail.getCompanyBorrowedFift() + detail.getCompanyBorrowedTwtenty() + detail.getCompanyBorrowedThirty());
                    companyBorrowedDetailSer.save(detail);
                }
            }
            companyBorrowedDetailSer.update(companyBorrowedDetails);
        }
        //编辑实收资本明细
        if (costDetailsAddEditTO.getPaidCapitalDetailTOList() != null && costDetailsAddEditTO.getPaidCapitalDetailTOList().size() > 0) {
            List<PaidCapitalDetail> paidCapitalDetails = paidCapitalDetailSer.findByCostId(costDetails.getId());
            for (PaidCapitalDetailTO paidCapitalDetailTO : costDetailsAddEditTO.getPaidCapitalDetailTOList()) {
                boolean exist = false;
                for (PaidCapitalDetail paidCapitalDetail : paidCapitalDetails) {
                    if (paidCapitalDetailTO.getTypeName().equals(paidCapitalDetail.getTypeName())) {
                        BeanTransform.copyProperties(paidCapitalDetailTO, paidCapitalDetail, true);
                        paidCapitalDetail.setPaidCapitalSum(paidCapitalDetail.getPaidCapitalTen() + paidCapitalDetail.getPaidCapitalFift() + paidCapitalDetail.getPaidCapitalTwtenty() + paidCapitalDetail.getPaidCapitalThirty());
                        paidCapitalDetail.setModifyTime(LocalDateTime.now());
                        exist = true;
                    }
                }
                if (!exist) {
                    PaidCapitalDetail detail = BeanTransform.copyProperties(paidCapitalDetailTO, PaidCapitalDetail.class, true);
                    detail.setCostId(costDetails.getId());
                    detail.setCreateTime(LocalDateTime.now());
                    detail.setCostTime(costDetails.getCostTime());
                    detail.setDepartment(costDetails.getDepartment());
                    detail.setPaidCapitalSum(detail.getPaidCapitalTen() + detail.getPaidCapitalFift() + detail.getPaidCapitalTwtenty() + detail.getPaidCapitalThirty());
                    paidCapitalDetailSer.save(detail);
                }

            }
            paidCapitalDetailSer.update(paidCapitalDetails);

        }
        //编辑公司借出明细
        if (costDetailsAddEditTO.getCompanyLendDetailTOList() != null && costDetailsAddEditTO.getCompanyLendDetailTOList().size() > 0) {
            List<CompanyLendDetail> companyLendDetails = companyLendDetailSer.findByCostId(costDetails.getId());
            for (CompanyLendDetailTO companyLendDetailTO : costDetailsAddEditTO.getCompanyLendDetailTOList()) {
                boolean exist = false;
                for (CompanyLendDetail companyLendDetail : companyLendDetails) {
                    if (companyLendDetailTO.getTypeName().equals(companyLendDetail.getTypeName())) {
                        BeanTransform.copyProperties(companyLendDetailTO, companyLendDetail, true);
                        companyLendDetail.setCompanyLendSum(companyLendDetail.getCompanyLendTen() + companyLendDetail.getCompanyLendFift() + companyLendDetail.getCompanyLendTwtenty() + companyLendDetail.getCompanyLendThirty());
                        companyLendDetail.setModifyTime(LocalDateTime.now());
                        exist = true;
                    }
                }
                if (!exist) {
                    CompanyLendDetail detail = BeanTransform.copyProperties(companyLendDetailTO, CompanyLendDetail.class, true);
                    detail.setCostId(costDetails.getId());
                    detail.setCreateTime(LocalDateTime.now());
                    detail.setCostTime(costDetails.getCostTime());
                    detail.setDepartment(costDetails.getDepartment());
                    detail.setCompanyLendSum(detail.getCompanyLendTen() + detail.getCompanyLendFift() + detail.getCompanyLendTwtenty() + detail.getCompanyLendThirty());
                    companyLendDetailSer.save(detail);
                }

            }
            companyLendDetailSer.update(companyLendDetails);

        }
        //编辑主营业务明细

        if (costDetailsAddEditTO.getBusinessIncomeDetailTOList() != null && costDetailsAddEditTO.getBusinessIncomeDetailTOList().size() > 0) {
            List<BusinessIncomeDetail> businessIncomeDetails = businessIncomeDetailSer.findByCostId(costDetails.getId());
            for (BusinessIncomeDetailTO businessIncomeDetailTO : costDetailsAddEditTO.getBusinessIncomeDetailTOList()) {
                boolean exist = false;
                for (BusinessIncomeDetail businessIncomeDetail : businessIncomeDetails) {
                    if (businessIncomeDetailTO.getTypeName().equals(businessIncomeDetail.getTypeName())) {
                        BeanTransform.copyProperties(businessIncomeDetailTO, businessIncomeDetail, true);
                        businessIncomeDetail.setBusinessIncomeSum(businessIncomeDetail.getBusinessIncomeTen() + businessIncomeDetail.getBusinessIncomeFift() + businessIncomeDetail.getBusinessIncomeTwtenty() + businessIncomeDetail.getBusinessIncomeThirty());
                        businessIncomeDetail.setModifyTime(LocalDateTime.now());
                        exist = true;
                    }
                }
                if (!exist) {
                    BusinessIncomeDetail detail = BeanTransform.copyProperties(businessIncomeDetailTO, BusinessIncomeDetail.class, true);
                    detail.setBusinessIncomeSum(detail.getBusinessIncomeTen() + detail.getBusinessIncomeFift() + detail.getBusinessIncomeTwtenty() + detail.getBusinessIncomeThirty());
                    detail.setCostId(costDetails.getId());
                    detail.setCreateTime(LocalDateTime.now());
                    detail.setCostTime(costDetails.getCostTime());
                    detail.setDepartment(costDetails.getDepartment());
                    businessIncomeDetailSer.save(detail);
                }

            }
            businessIncomeDetailSer.update(businessIncomeDetails);

        }
        return BeanTransform.copyProperties(costDetails, CostDetailsBO.class);
    }

    @Override
    public void delete(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id 不能为空");
        }
        deleteLaborCost(id);
        deleteCompanyBorrowed(id);
        deletePaidCapital(id);
        deleteCompanyLend(id);
        deleteBusinessIncome(id);
        super.remove(id);
    }

    //根据成本id删除劳务成本明细
    public void deleteLaborCost(String costId) throws SerException {
        LaborCostDetailDTO dto = new LaborCostDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", costId));
        List<LaborCostDetail> laborCostDetails = laborCostDetailSer.findByCis(dto);
        laborCostDetailSer.remove(laborCostDetails);
    }

    //根据成本id删除公司借入明细
    public void deleteCompanyBorrowed(String costId) throws SerException {
        CompanyBorrowedDetailDTO dto = new CompanyBorrowedDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", costId));
        List<CompanyBorrowedDetail> companyBorrowedDetails = companyBorrowedDetailSer.findByCis(dto);
        companyBorrowedDetailSer.remove(companyBorrowedDetails);
    }

    //根据成本id删除实收资本明细
    public void deletePaidCapital(String costId) throws SerException {
        PaidCapitalDetailDTO dto = new PaidCapitalDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", costId));
        List<PaidCapitalDetail> paidCapitalDetails = paidCapitalDetailSer.findByCis(dto);
        paidCapitalDetailSer.remove(paidCapitalDetails);
    }

    //根据成本id删除公司借出明细
    public void deleteCompanyLend(String costId) throws SerException {
        CompanyLendDetailDTO dto = new CompanyLendDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", costId));
        List<CompanyLendDetail> companyLendDetails = companyLendDetailSer.findByCis(dto);
        companyLendDetailSer.remove(companyLendDetails);
    }

    //根据成本id删除主营业务明细
    public void deleteBusinessIncome(String costId) throws SerException {
        BusinessIncomeDetailDTO dto = new BusinessIncomeDetailDTO();
        dto.getConditions().add(Restrict.eq("costId", costId));
        List<BusinessIncomeDetail> businessIncomeDetails = businessIncomeDetailSer.findByCis(dto);
        businessIncomeDetailSer.remove(businessIncomeDetails);
    }

    public void searchCondition(CostDetailsDTO costDetailsDTO) throws SerException {
        if (StringUtils.isNotBlank(costDetailsDTO.getCostTime())) {
            String date = StringUtils.substring(costDetailsDTO.getCostTime(), 0, -2) + "01";
            costDetailsDTO.getConditions().add(Restrict.eq("costTime", date));
        }
        if (StringUtils.isNotBlank(costDetailsDTO.getDepartment())) {
            costDetailsDTO.getConditions().add(Restrict.eq("department", costDetailsDTO.getDepartment()));
        }
    }

    @Override
    public CostDetailsAddEditBO seeDetail(String id) throws SerException {
        checkPermission();
        CostDetails costDetails = super.findById(id);


        List<LaborCostDetailBO> laborCostDetailBOList = new ArrayList<>();
        List<CompanyBorrowedDetailBO> companyBorrowedDetailBOList = new ArrayList<>();
        List<PaidCapitalDetailBO> paidCapitalDetailBOList = new ArrayList<>();
        List<CompanyLendDetailBO> companyLendDetailBOList = new ArrayList<>();
        List<BusinessIncomeDetailBO> businessIncomeDetailBOList = new ArrayList<>();

        CostDetailsAddEditBO costDetailsAddEditBO = BeanTransform.copyProperties(costDetails, CostDetailsAddEditBO.class);
        List<LaborCostDetail> laborCostDetails = laborCostDetailSer.findByCostId(id);
        for (LaborCostDetail laborCostDetail : laborCostDetails) {
            LaborCostDetailBO laborCostDetailBO = BeanTransform.copyProperties(laborCostDetail, LaborCostDetailBO.class);
            laborCostDetailBOList.add(laborCostDetailBO);
        }
        List<CompanyBorrowedDetail> companyBorrowedDetails = companyBorrowedDetailSer.findByCostId(id);
        for (CompanyBorrowedDetail companyBorrowedDetail : companyBorrowedDetails) {
            CompanyBorrowedDetailBO companyBorrowedDetailBO = BeanTransform.copyProperties(companyBorrowedDetail, CompanyBorrowedDetailBO.class);
            companyBorrowedDetailBOList.add(companyBorrowedDetailBO);
        }
        List<PaidCapitalDetail> paidCapitalDetails = paidCapitalDetailSer.findByCostId(id);
        for (PaidCapitalDetail paidCapitalDetail : paidCapitalDetails) {
            PaidCapitalDetailBO paidCapitalDetailBO = BeanTransform.copyProperties(paidCapitalDetail, PaidCapitalDetailBO.class);
            paidCapitalDetailBOList.add(paidCapitalDetailBO);
        }
        List<CompanyLendDetail> companyLendDetails = companyLendDetailSer.findByCostId(id);
        for (CompanyLendDetail companyLendDetail : companyLendDetails) {
            CompanyLendDetailBO companyLendDetailBO = BeanTransform.copyProperties(companyLendDetail, CompanyLendDetailBO.class);
            companyLendDetailBOList.add(companyLendDetailBO);
        }
        List<BusinessIncomeDetail> businessIncomeDetails = businessIncomeDetailSer.findByCostId(id);
        for (BusinessIncomeDetail businessIncomeDetail : businessIncomeDetails) {
            BusinessIncomeDetailBO businessIncomeDetailBO = BeanTransform.copyProperties(businessIncomeDetail, BusinessIncomeDetailBO.class);
            businessIncomeDetailBOList.add(businessIncomeDetailBO);
        }
        costDetailsAddEditBO.setLaborCostDetailList(laborCostDetailBOList);
        costDetailsAddEditBO.setCompanyBorrowedDetailList(companyBorrowedDetailBOList);
        costDetailsAddEditBO.setPaidCapitalDetailList(paidCapitalDetailBOList);
        costDetailsAddEditBO.setCompanyLendDetailList(companyLendDetailBOList);
        costDetailsAddEditBO.setBusinessIncomeDetailList(businessIncomeDetailBOList);

        return costDetailsAddEditBO;
    }

    @Override
    public CostDetailsAddEditBO listDetail(CostDetailsDTO costDetailsDTO) throws SerException {
        checkPermission();
        CostDetails costDetails = new CostDetails();
        if(StringUtils.isBlank(costDetailsDTO.getCostTime()) && StringUtils.isBlank(costDetailsDTO.getDepartment())){
            costDetailsDTO.getSorts().add("createTime=desc");
            List<CostDetails> costDetailsList = super.findByCis(costDetailsDTO);
            if(costDetailsList != null && costDetailsList.size()>0){
                costDetails = costDetailsList.get(0);
            }else{
                return new CostDetailsAddEditBO();
            }
        }else{
            if(StringUtils.isBlank(costDetailsDTO.getCostTime())){
                throw new SerException("日期不能为空");
            }
            if(StringUtils.isBlank(costDetailsDTO.getDepartment())){
                throw new SerException("部门不能为空");
            }
            searchCondition(costDetailsDTO);
            List<CostDetails> costDetailsList = super.findByCis(costDetailsDTO);
            if(costDetailsList != null && costDetailsList.size()>0){
                costDetails = costDetailsList.get(0);
            }else{
                return new CostDetailsAddEditBO();
            }
        }

        List<LaborCostDetailBO> laborCostDetailBOList = new ArrayList<>();
        List<CompanyBorrowedDetailBO> companyBorrowedDetailBOList = new ArrayList<>();
        List<PaidCapitalDetailBO> paidCapitalDetailBOList = new ArrayList<>();
        List<CompanyLendDetailBO> companyLendDetailBOList = new ArrayList<>();
        List<BusinessIncomeDetailBO> businessIncomeDetailBOList = new ArrayList<>();

        CostDetailsAddEditBO costDetailsAddEditBO = BeanTransform.copyProperties(costDetails, CostDetailsAddEditBO.class);
        List<LaborCostDetail> laborCostDetails = laborCostDetailSer.findByCostId(costDetails.getId());
        for (LaborCostDetail laborCostDetail : laborCostDetails) {
            LaborCostDetailBO laborCostDetailBO = BeanTransform.copyProperties(laborCostDetail, LaborCostDetailBO.class);
            laborCostDetailBOList.add(laborCostDetailBO);
        }
        List<CompanyBorrowedDetail> companyBorrowedDetails = companyBorrowedDetailSer.findByCostId(costDetails.getId());
        for (CompanyBorrowedDetail companyBorrowedDetail : companyBorrowedDetails) {
            CompanyBorrowedDetailBO companyBorrowedDetailBO = BeanTransform.copyProperties(companyBorrowedDetail, CompanyBorrowedDetailBO.class);
            companyBorrowedDetailBOList.add(companyBorrowedDetailBO);
        }
        List<PaidCapitalDetail> paidCapitalDetails = paidCapitalDetailSer.findByCostId(costDetails.getId());
        for (PaidCapitalDetail paidCapitalDetail : paidCapitalDetails) {
            PaidCapitalDetailBO paidCapitalDetailBO = BeanTransform.copyProperties(paidCapitalDetail, PaidCapitalDetailBO.class);
            paidCapitalDetailBOList.add(paidCapitalDetailBO);
        }
        List<CompanyLendDetail> companyLendDetails = companyLendDetailSer.findByCostId(costDetails.getId());
        for (CompanyLendDetail companyLendDetail : companyLendDetails) {
            CompanyLendDetailBO companyLendDetailBO = BeanTransform.copyProperties(companyLendDetail, CompanyLendDetailBO.class);
            companyLendDetailBOList.add(companyLendDetailBO);
        }
        List<BusinessIncomeDetail> businessIncomeDetails = businessIncomeDetailSer.findByCostId(costDetails.getId());
        for (BusinessIncomeDetail businessIncomeDetail : businessIncomeDetails) {
            BusinessIncomeDetailBO businessIncomeDetailBO = BeanTransform.copyProperties(businessIncomeDetail, BusinessIncomeDetailBO.class);
            businessIncomeDetailBOList.add(businessIncomeDetailBO);
        }
        costDetailsAddEditBO.setLaborCostDetailList(laborCostDetailBOList);
        costDetailsAddEditBO.setCompanyBorrowedDetailList(companyBorrowedDetailBOList);
        costDetailsAddEditBO.setPaidCapitalDetailList(paidCapitalDetailBOList);
        costDetailsAddEditBO.setCompanyLendDetailList(companyLendDetailBOList);
        costDetailsAddEditBO.setBusinessIncomeDetailList(businessIncomeDetailBOList);

        return costDetailsAddEditBO;
    }

    @Override
    public CostDetailsAddEditBO monthCollect(String costTime, String[] department) throws SerException {
        checkPermission();
        CostDetailsDTO dto = new CostDetailsDTO();
        if (StringUtils.isBlank(costTime)) {
            throw new SerException("时间不能为空");
        }
        if (department == null || department.length == 0) {
            throw new SerException("部门不能为空");
        }
        String date = StringUtils.substring(costTime, 0, -2) + "01";
        StringBuffer sql = new StringBuffer();
        String[] field = {"beginBalanceSum", "beginBalanceTen", "beginBalanceFift", "beginBalanceTwtenty", "beginBalanceThirty","operatingSum","operatingTen","operatingFift","operatingTwtenty","operatingThirty","laborCostSum", "laborCostTen",
                "laborCostFift", "laborCostTwtenty", "laborCostThirty", "payableTaxSum", "payableTaxTen", "payableTaxFift", "payableTaxTwtenty", "payableTaxThirty",
                "companyBorrowedSum", "companyBorrowedTen", "companyBorrowedFift", "companyBorrowedTwtenty", "companyBorrowedThirty", "paidCapitalSum", "paidCapitalTen",
                "paidCapitalFift", "paidCapitalTwtenty", "paidCapitalThirty", "companyLendSum", "companyLendTen", "companyLendFift", "companyLendTwtenty", "companyLendThirty",
                "businessIncomeSum", "businessIncomeTen", "businessIncomeFift", "businessIncomeTwtenty", "businessIncomeThirty", "forecastAccountSum", "forecastAccountTen",
                "forecastAccountFift", "forecastAccountTwtenty", "forecastAccountThirty", "actualGapSum", "actualGapTen", "actualGapFift", "actualGapTwtenty",
                "actualGapThirty", "forecastBalanceMoSum", "forecastBalanceMoGapTen", "forecastBalanceMoGapFift", "forecastBalanceMoTwtenty", "forecastBalanceMoThirty"};
        sql.append("SELECT " +
                "  sum(beginBalanceSum)          AS beginBalanceSum," +
                "  sum(beginBalanceTen)          AS beginBalanceTen," +
                "  sum(beginBalanceFift)         AS beginBalanceFift," +
                "  sum(beginBalanceTwtenty)      AS beginBalanceTwtenty," +
                "  sum(beginBalanceThirty)       AS beginBalanceThirty," +
                "  sum(operatingSum)             AS operatingSum," +
                "  sum(operatingTen)             AS operatingTen," +
                "  sum(operatingFift)            AS operatingFift," +
                "  sum(operatingTwtenty)         AS operatingTwtenty," +
                "  sum(operatingThirty)          AS operatingThirty," +
                "  sum(laborCostSum)             AS laborCostSum," +
                "  sum(laborCostTen)             AS laborCostTen," +
                "  sum(laborCostFift)            AS laborCostFift," +
                "  sum(laborCostTwtenty)         AS laborCostTwtenty," +
                "  sum(laborCostThirty)          AS laborCostThirty," +
                "  sum(payableTaxSum)            AS payableTaxSum," +
                "  sum(payableTaxTen)            AS payableTaxTen," +
                "  sum(payableTaxFift)           AS payableTaxFift," +
                "  sum(payableTaxTwtenty)        AS payableTaxTwtenty," +
                "  sum(payableTaxThirty)         AS payableTaxThirty," +
                "  sum(companyBorrowedSum)       AS companyBorrowedSum," +
                "  sum(companyBorrowedTen)       AS companyBorrowedTen," +
                "  sum(companyBorrowedFift)      as companyBorrowedFift," +
                "  sum(companyBorrowedTwtenty)   AS companyBorrowedTwtenty," +
                "  sum(companyBorrowedThirty)    AS companyBorrowedThirty," +
                "  sum(paidCapitalSum)           AS paidCapitalSum," +
                "  sum(paidCapitalTen)           AS paidCapitalTen," +
                "  sum(paidCapitalFift)          AS paidCapitalFift," +
                "  sum(paidCapitalTwtenty)       AS paidCapitalTwtenty," +
                "  sum(paidCapitalThirty)        AS paidCapitalThirty," +
                "  sum(companyLendSum)           AS companyLendSum," +
                "  sum(companyLendTen)           AS companyLendTen," +
                "  sum(companyLendFift)          AS companyLendFift," +
                "  sum(companyLendTwtenty)       AS companyLendTwtenty," +
                "  sum(companyLendThirty)        AS companyLendThirty," +
                "  sum(businessIncomeSum)        AS businessIncomeSum," +
                "  sum(businessIncomeTen)        AS businessIncomeTen," +
                "  sum(businessIncomeFift)       AS businessIncomeFift," +
                "  sum(businessIncomeTwtenty)    AS businessIncomeTwtenty," +
                "  sum(businessIncomeThirty)     AS businessIncomeThirty," +
                "  sum(forecastAccountSum)       AS forecastAccountSum," +
                "  sum(forecastAccountTen)       AS forecastAccountTen," +
                "  sum(forecastAccountFift)      AS forecastAccountFift," +
                "  sum(forecastAccountTwtenty)   AS forecastAccountTwtenty," +
                "  sum(forecastAccountThirty)    AS forecastAccountThirty," +
                "  sum(actualGapSum)             AS actualGapSum," +
                "  sum(actualGapTen)             AS actualGapTen," +
                "  sum(actualGapFift)            AS actualGapFift," +
                "  sum(actualGapTwtenty)         AS actualGapTwtenty," +
                "  sum(actualGapThirty)          AS actualGapThirty," +
                "  sum(forecastBalanceMoSum)     AS forecastBalanceMoSum," +
                "  sum(forecastBalanceMoGapTen)  AS forecastBalanceMoGapTen," +
                "  sum(forecastBalanceMoGapFift) AS forecastBalanceMoGapFift," +
                "  sum(forecastBalanceMoTwtenty) AS forecastBalanceMoTwtenty," +
                "  sum(forecastBalanceMoThirty)  AS forecastBalanceMoThirty," +
                "  costTime " +
                " FROM costdetail_costdetails " +
                " WHERE costTime = '" + date + "' and department in ('" + StringUtils.join(department, "','") + "') " +
                " GROUP BY costTime"
        );
        CostDetailsAddEditBO costDetailsAddEditBO = new CostDetailsAddEditBO();
        List<CostDetailsAddEditBO> costDetailsAddEditBOS = super.findBySql(sql.toString(), CostDetailsAddEditBO.class, field);
        if (costDetailsAddEditBOS != null && costDetailsAddEditBOS.size() > 0) {
            costDetailsAddEditBO = costDetailsAddEditBOS.get(0);

            StringBuffer laborSql = new StringBuffer();
            String[] laborField = {"laborCostSum", "laborCostTen", "laborCostFift", "laborCostTwtenty", "laborCostThirty", "typeName"};
            laborSql.append("SELECT" +
                    "  sum(ifnull(laborCostSum, 0))     AS laborCostSum," +
                    "  sum(ifnull(laborCostTen, 0))     AS laborCostTen," +
                    "  sum(ifnull(laborCostFift, 0))    AS laborCostFift," +
                    "  sum(ifnull(laborCostTwtenty, 0)) AS laborCostTwtenty," +
                    "  sum(ifnull(laborCostThirty, 0))  AS laborCostThirty," +
                    "  typeName " +
                    " FROM costdetail_laborcostdetail " +
                    " WHERE costTime = '" + date + "' and department in ('" + StringUtils.join(department, "','") + "') " +
                    " GROUP BY typeName");
            List<LaborCostDetailBO> laborCostDetailBOList = laborCostDetailSer.findBySql(laborSql.toString(), LaborCostDetailBO.class, laborField);

            StringBuffer compBorrSql = new StringBuffer();
            String[] compBorrField = {"companyBorrowedSum", "companyBorrowedTen", "companyBorrowedFift", "companyBorrowedTwtenty", "companyBorrowedThirty", "typeName"};
            compBorrSql.append("SELECT" +
                    "  sum(ifnull(companyBorrowedSum, 0))     AS companyBorrowedSum," +
                    "  sum(ifnull(companyBorrowedTen, 0))     AS companyBorrowedTen," +
                    "  sum(ifnull(companyBorrowedFift, 0))    AS companyBorrowedFift," +
                    "  sum(ifnull(companyBorrowedTwtenty, 0)) AS companyBorrowedTwtenty," +
                    "  sum(ifnull(companyBorrowedThirty, 0))  AS companyBorrowedThirty," +
                    "  typeName " +
                    " FROM costdetail_companyborroweddetail" +
                    " WHERE costTime = '" + date + "' and department in ('" + StringUtils.join(department, "','") + "') " +
                    " GROUP BY typeName");
            List<CompanyBorrowedDetailBO> companyBorrowedDetailBOList = companyBorrowedDetailSer.findBySql(compBorrSql.toString(), CompanyBorrowedDetailBO.class, compBorrField);

            StringBuffer paidSql = new StringBuffer();
            String[] paidField = {"paidCapitalSum", "paidCapitalTen", "paidCapitalFift", "paidCapitalTwtenty", "paidCapitalThirty", "typeName"};
            paidSql.append("SELECT" +
                    "  sum(ifnull(paidCapitalSum, 0))     AS paidCapitalSum," +
                    "  sum(ifnull(paidCapitalTen, 0))     AS paidCapitalTen," +
                    "  sum(ifnull(paidCapitalFift, 0))    AS paidCapitalFift," +
                    "  sum(ifnull(paidCapitalTwtenty, 0)) AS paidCapitalTwtenty," +
                    "  sum(ifnull(paidCapitalThirty, 0))  AS paidCapitalThirty," +
                    "  typeName " +
                    " FROM costdetail_paidcapitaldetail" +
                    " WHERE costTime = '" + date + "' and department in ('" + StringUtils.join(department, "','") + "') " +
                    " GROUP BY typeName");
            List<PaidCapitalDetailBO> paidCapitalDetailBOList = paidCapitalDetailSer.findBySql(paidSql.toString(), PaidCapitalDetailBO.class, paidField);

            StringBuffer compLendSql = new StringBuffer();
            String[] compLendField = {"companyLendSum", "companyLendTen", "companyLendFift", "companyLendTwtenty", "companyLendThirty", "typeName"};
            compLendSql.append("SELECT" +
                    "  sum(ifnull(companyLendSum, 0))     AS companyLendSum," +
                    "  sum(ifnull(companyLendTen, 0))     AS companyLendTen," +
                    "  sum(ifnull(companyLendFift, 0))    AS companyLendFift," +
                    "  sum(ifnull(companyLendTwtenty, 0)) AS companyLendTwtenty," +
                    "  sum(ifnull(companyLendThirty, 0))  AS companyLendThirty," +
                    "  typeName " +
                    " FROM costdetail_companylenddetail" +
                    " WHERE costTime = '" + date + "' and department in ('" + StringUtils.join(department, "','") + "') " +
                    " GROUP BY typeName");
            List<CompanyLendDetailBO> companyLendDetailBOList = companyLendDetailSer.findBySql(compLendSql.toString(), CompanyLendDetailBO.class, compLendField);

            StringBuffer bussSql = new StringBuffer();
            String[] bussField = {"businessIncomeSum", "businessIncomeTen", "businessIncomeFift", "businessIncomeTwtenty", "businessIncomeThirty", "typeName"};
            bussSql.append("SELECT" +
                    "  sum(ifnull(businessIncomeSum, 0))     AS businessIncomeSum," +
                    "  sum(ifnull(businessIncomeTen, 0))     AS businessIncomeTen," +
                    "  sum(ifnull(businessIncomeFift, 0))    AS businessIncomeFift," +
                    "  sum(ifnull(businessIncomeTwtenty, 0)) AS businessIncomeTwtenty," +
                    "  sum(ifnull(businessIncomeThirty, 0))  AS businessIncomeThirty," +
                    "  typeName " +
                    " FROM costdetail_businessincomedetail" +
                    " WHERE costTime = '" + date + "' AND department in ('" + StringUtils.join(department, "','") + "')" +
                    " GROUP BY typeName");
            List<BusinessIncomeDetailBO> businessIncomeDetailBOList = businessIncomeDetailSer.findBySql(bussSql.toString(), BusinessIncomeDetailBO.class, bussField);

            costDetailsAddEditBO.setBusinessIncomeDetailList(businessIncomeDetailBOList);
            costDetailsAddEditBO.setCompanyLendDetailList(companyLendDetailBOList);
            costDetailsAddEditBO.setPaidCapitalDetailList(paidCapitalDetailBOList);
            costDetailsAddEditBO.setCompanyBorrowedDetailList(companyBorrowedDetailBOList);
            costDetailsAddEditBO.setLaborCostDetailList(laborCostDetailBOList);
        }

        return costDetailsAddEditBO;
    }

    @Override
    public List<String> findAllDetails() throws SerException {
        List<CostDetails> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CostDetails model : list) {
            String details = model.getDepartment();
            if (StringUtils.isNotBlank(model.getDepartment())) {
                set.add(details);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<CostDetailsYeCollBO> yearCollect(Integer years) throws SerException {
        checkPermission();
        if (years == null) {
            throw new SerException("汇总年份不能为空");
        }
        List<CostDetailsYeCollBO> costDetailsYeCollBOList = new CopyOnWriteArrayList<>();
        String start = years + "-01-01";
        String end = years + "-12-12";

        //主营业务收入
        StringBuffer busSql = new StringBuffer();
        String[] busField = {"subject", "monthColl", "costTime"};
        busSql.append("SELECT '主营业务收入' as subject ,SUM(A.businessIncomeSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  businessIncomeSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearColloBOS = super.findBySql(busSql.toString(), YearColloBO.class, busField);
        if (yearColloBOS != null && yearColloBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearColloBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //公司借入合计
        StringBuffer comBorrSql = new StringBuffer();
        String[] comBorrField = {"subject", "monthColl", "costTime"};
        comBorrSql.append("SELECT '公司借入合计' as subject ,SUM(A.companyBorrowedSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  companyBorrowedSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearCompBOS = super.findBySql(comBorrSql.toString(), YearColloBO.class, comBorrField);
        if (yearCompBOS != null && yearCompBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearCompBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //公司借出合计
        StringBuffer comLenSql = new StringBuffer();
        String[] comLenField = {"subject", "monthColl", "costTime"};
        comLenSql.append("SELECT '公司借出合计' as subject ,SUM(A.companyLendSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  companyLendSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearLenBOS = super.findBySql(comLenSql.toString(), YearColloBO.class, comLenField);
        if (yearLenBOS != null && yearLenBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearLenBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //实收资本
        StringBuffer paidSql = new StringBuffer();
        String[] paidField = {"subject", "monthColl", "costTime"};
        paidSql.append("SELECT '实收资本' as subject ,SUM(A.paidCapitalSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  paidCapitalSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearPaidBOS = super.findBySql(paidSql.toString(), YearColloBO.class, paidField);
        if (yearPaidBOS != null && yearPaidBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearPaidBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //营业成本
        StringBuffer operaSql = new StringBuffer();
        String[] operaField = {"subject", "monthColl", "costTime"};
        operaSql.append("SELECT '营业成本' as subject ,SUM(A.operatingSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  operatingSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearOperaBOS = super.findBySql(operaSql.toString(), YearColloBO.class, operaField);
        if (yearOperaBOS != null && yearOperaBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearOperaBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //劳务成本
        StringBuffer laborSql = new StringBuffer();
        String[] laborField = {"subject", "monthColl", "costTime"};
        laborSql.append("SELECT '劳务成本' as subject ,SUM(A.laborCostSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  laborCostSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearLaborBOS = super.findBySql(laborSql.toString(), YearColloBO.class, laborField);
        if (yearLaborBOS != null && yearLaborBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearLaborBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //应交税金
        StringBuffer payaSql = new StringBuffer();
        String[] payaField = {"subject", "monthColl", "costTime"};
        payaSql.append("SELECT '应交税金' as subject ,SUM(A.payableTaxSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  payableTaxSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearPayaBOS = super.findBySql(payaSql.toString(), YearColloBO.class, payaField);
        if (yearPayaBOS != null && yearPayaBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearPayaBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //应交税金
        StringBuffer foreSql = new StringBuffer();
        String[] foreField = {"subject", "monthColl", "costTime"};
        foreSql.append("SELECT '应交税金' as subject ,SUM(A.forecastAccountSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  forecastAccountSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearForeBOS = super.findBySql(foreSql.toString(), YearColloBO.class, foreField);
        if (yearForeBOS != null && yearForeBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearForeBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //实际资金缺口
        StringBuffer actuSql = new StringBuffer();
        String[] actuField = {"subject", "monthColl", "costTime"};
        actuSql.append("SELECT '实际资金缺口' as subject ,SUM(A.actualGapSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  actualGapSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearActuBOS = super.findBySql(actuSql.toString(), YearColloBO.class, actuField);
        if (yearActuBOS != null && yearActuBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearActuBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }

        //按时回款预估结余资金
        StringBuffer balanSql = new StringBuffer();
        String[] balanField = {"subject", "monthColl", "costTime"};
        balanSql.append("SELECT '按时回款预估结余资金' as subject ,SUM(A.forecastBalanceMoSum) as monthColl,A.costTime as costTime " +
                "  from (SELECT  forecastBalanceMoSum,substring(costTime,7,1)as costTime from costdetail_costdetails where " +
                " costTime BETWEEN '" + start + "' and '" + end + "')A  GROUP BY A.costTime");
        List<YearColloBO> yearBalanBOS = super.findBySql(balanSql.toString(), YearColloBO.class, balanField);
        if (yearBalanBOS != null && yearBalanBOS.size() > 0) {
            CostDetailsYeCollBO bo = new CostDetailsYeCollBO();
            yearBalanBOS.stream().forEach(str -> {
                Double money = str.getMonthColl();
                switch (str.getCostTime()) {
                    case "1":
                        bo.setJanuaryValue(money);
                        break;
                    case "2":
                        bo.setFebruaryValue(money);
                        break;
                    case "3":
                        bo.setMarchValue(money);
                        break;
                    case "4":
                        bo.setAprilValue(money);
                        break;
                    case "5":
                        bo.setMayValue(money);
                        break;
                    case "6":
                        bo.setJuneValue(money);
                        break;
                    case "7":
                        bo.setJulyValue(money);
                        break;
                    case "8":
                        bo.setAugustValue(money);
                        break;
                    case "9":
                        bo.setSeptemberValue(money);
                        break;
                    case "10":
                        bo.setOctoberValue(money);
                        break;
                    case "11":
                        bo.setNovemberValue(money);
                        break;
                    case "12":
                        bo.setDecemberValue(money);
                        break;
                    default:
                        break;

                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuaryValue() == null) {
                    bo.setJanuaryValue(0d);
                }
                if (bo.getFebruaryValue() == null) {
                    bo.setFebruaryValue(0d);
                }
                if (bo.getMarchValue() == null) {
                    bo.setMarchValue(0d);
                }
                if (bo.getAprilValue() == null) {
                    bo.setAprilValue(0d);
                }
                if (bo.getMayValue() == null) {
                    bo.setMayValue(0d);
                }
                if (bo.getJuneValue() == null) {
                    bo.setJuneValue(0d);
                }
                if (bo.getJulyValue() == null) {
                    bo.setJulyValue(0d);
                }
                if (bo.getAugustValue() == null) {
                    bo.setAugustValue(0d);
                }
                if (bo.getSeptemberValue() == null) {
                    bo.setSeptemberValue(0d);
                }
                if (bo.getOctoberValue() == null) {
                    bo.setOctoberValue(0d);
                }
                if (bo.getNovemberValue() == null) {
                    bo.setNovemberValue(0d);
                }
                if (bo.getDecemberValue() == null) {
                    bo.setDecemberValue(0d);
                }
                bo.setSubject(str.getSubject());
            });
            Double collectMoney = bo.getJanuaryValue() + bo.getFebruaryValue() + bo.getMarchValue() + bo.getAprilValue() +
                    bo.getMayValue() + bo.getJuneValue() + bo.getJulyValue() + bo.getAugustValue() + bo.getSeptemberValue() +
                    bo.getOctoberValue() + bo.getNovemberValue() + bo.getDecemberValue();
            bo.setCombined(collectMoney);

            costDetailsYeCollBOList.add(bo);
        }


        return costDetailsYeCollBOList;
    }

    @Override
    public List<CostDetailsAddReturnBO> returnAddResult() throws SerException {
        List<CostDetailsAddReturnBO> costDetailsAddReturnBOS = new ArrayList<>();
        List<CostDetailsAddReturnBO> labAddReturnBOS = new ArrayList<>();
        List<CostDetailsAddReturnBO> borrAddReturnBOS = new ArrayList<>();
        List<CostDetailsAddReturnBO> paiAddReturnBOS = new ArrayList<>();
        List<CostDetailsAddReturnBO> lenAddReturnBOS = new ArrayList<>();
        List<CostDetailsAddReturnBO> bussAddReturnBOS = new ArrayList<>();

        CostDetailsAddReturnBO costDetailsAddReturnBO1 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO1.setCategoryName("期初余额");
        costDetailsAddReturnBO1.setSum(0d);
        costDetailsAddReturnBO1.setTenMoney(0d);
        costDetailsAddReturnBO1.setFifMoney(0d);
        costDetailsAddReturnBO1.setThityMoney(0d);
        costDetailsAddReturnBO1.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO1);

        CostDetailsAddReturnBO costDetailsAddReturnBO2 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO2.setCategoryName("营业成本");
        costDetailsAddReturnBO2.setSum(0d);
        costDetailsAddReturnBO2.setTenMoney(0d);
        costDetailsAddReturnBO2.setFifMoney(0d);
        costDetailsAddReturnBO2.setThityMoney(0d);
        costDetailsAddReturnBO2.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO2);

        CostDetailsAddReturnBO costDetailsAddReturnBO3 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO3.setCategoryName("劳务成本");
        costDetailsAddReturnBO3.setSum(0d);
        costDetailsAddReturnBO3.setTenMoney(0d);
        costDetailsAddReturnBO3.setFifMoney(0d);
        costDetailsAddReturnBO3.setThityMoney(0d);
        costDetailsAddReturnBO3.setTwethMoney(0d);
        List<String> typeNameList = detailTypeSer.findTypeName("劳务成本");
        for (String typeName : typeNameList) {
            CostDetailsAddReturnBO labAddReturnBO = new CostDetailsAddReturnBO();
            labAddReturnBO.setCategoryName(typeName);
            labAddReturnBO.setSum(0d);
            labAddReturnBO.setTenMoney(0d);
            labAddReturnBO.setFifMoney(0d);
            labAddReturnBO.setThityMoney(0d);
            labAddReturnBO.setTwethMoney(0d);
            labAddReturnBOS.add(labAddReturnBO);
        }
        costDetailsAddReturnBO3.setCostDetailsAddReturnBOS(labAddReturnBOS);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO3);

        CostDetailsAddReturnBO costDetailsAddReturnBO4 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO4.setCategoryName("应交税金");
        costDetailsAddReturnBO4.setSum(0d);
        costDetailsAddReturnBO4.setTenMoney(0d);
        costDetailsAddReturnBO4.setFifMoney(0d);
        costDetailsAddReturnBO4.setThityMoney(0d);
        costDetailsAddReturnBO4.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO4);

        CostDetailsAddReturnBO costDetailsAddReturnBO5 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO5.setCategoryName("公司借入");
        costDetailsAddReturnBO5.setSum(0d);
        costDetailsAddReturnBO5.setTenMoney(0d);
        costDetailsAddReturnBO5.setFifMoney(0d);
        costDetailsAddReturnBO5.setThityMoney(0d);
        costDetailsAddReturnBO5.setTwethMoney(0d);
        List<String> borrTypeNameList = detailTypeSer.findTypeName("公司借入");
        for (String typeName : borrTypeNameList) {
            CostDetailsAddReturnBO borrAddReturnBO = new CostDetailsAddReturnBO();
            borrAddReturnBO.setCategoryName(typeName);
            borrAddReturnBO.setSum(0d);
            borrAddReturnBO.setTenMoney(0d);
            borrAddReturnBO.setFifMoney(0d);
            borrAddReturnBO.setThityMoney(0d);
            borrAddReturnBO.setTwethMoney(0d);
            borrAddReturnBOS.add(borrAddReturnBO);
        }
        costDetailsAddReturnBO5.setCostDetailsAddReturnBOS(borrAddReturnBOS);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO5);

        CostDetailsAddReturnBO costDetailsAddReturnBO6 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO6.setCategoryName("实收资本");
        costDetailsAddReturnBO6.setSum(0d);
        costDetailsAddReturnBO6.setTenMoney(0d);
        costDetailsAddReturnBO6.setFifMoney(0d);
        costDetailsAddReturnBO6.setThityMoney(0d);
        costDetailsAddReturnBO6.setTwethMoney(0d);
        List<String> paiTypeNameList = detailTypeSer.findTypeName("实收资本");
        for (String typeName : paiTypeNameList) {
            CostDetailsAddReturnBO paiAddReturnBO = new CostDetailsAddReturnBO();
            paiAddReturnBO.setCategoryName(typeName);
            paiAddReturnBO.setSum(0d);
            paiAddReturnBO.setTenMoney(0d);
            paiAddReturnBO.setFifMoney(0d);
            paiAddReturnBO.setThityMoney(0d);
            paiAddReturnBO.setTwethMoney(0d);
            paiAddReturnBOS.add(paiAddReturnBO);
        }
        costDetailsAddReturnBO6.setCostDetailsAddReturnBOS(paiAddReturnBOS);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO6);

        CostDetailsAddReturnBO costDetailsAddReturnBO7 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO7.setCategoryName("公司借出");
        costDetailsAddReturnBO7.setSum(0d);
        costDetailsAddReturnBO7.setTenMoney(0d);
        costDetailsAddReturnBO7.setFifMoney(0d);
        costDetailsAddReturnBO7.setThityMoney(0d);
        costDetailsAddReturnBO7.setTwethMoney(0d);
        List<String> lenTypeNameList = detailTypeSer.findTypeName("公司借出");
        for (String typeName : lenTypeNameList) {
            CostDetailsAddReturnBO lenAddReturnBO = new CostDetailsAddReturnBO();
            lenAddReturnBO.setCategoryName(typeName);
            lenAddReturnBO.setSum(0d);
            lenAddReturnBO.setTenMoney(0d);
            lenAddReturnBO.setFifMoney(0d);
            lenAddReturnBO.setThityMoney(0d);
            lenAddReturnBO.setTwethMoney(0d);
            lenAddReturnBOS.add(lenAddReturnBO);
        }
        costDetailsAddReturnBO7.setCostDetailsAddReturnBOS(lenAddReturnBOS);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO7);

        CostDetailsAddReturnBO costDetailsAddReturnBO8 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO8.setCategoryName("主营业务收入");
        costDetailsAddReturnBO8.setSum(0d);
        costDetailsAddReturnBO8.setTenMoney(0d);
        costDetailsAddReturnBO8.setFifMoney(0d);
        costDetailsAddReturnBO8.setThityMoney(0d);
        costDetailsAddReturnBO8.setTwethMoney(0d);
        List<String> bussTypeNameList = detailTypeSer.findTypeName("主营业务收入");
        for (String typeName : bussTypeNameList) {
            CostDetailsAddReturnBO bussAddReturnBO = new CostDetailsAddReturnBO();
            bussAddReturnBO.setCategoryName(typeName);
            bussAddReturnBO.setSum(0d);
            bussAddReturnBO.setTenMoney(0d);
            bussAddReturnBO.setFifMoney(0d);
            bussAddReturnBO.setThityMoney(0d);
            bussAddReturnBO.setTwethMoney(0d);
            bussAddReturnBOS.add(bussAddReturnBO);
        }
        costDetailsAddReturnBO8.setCostDetailsAddReturnBOS(bussAddReturnBOS);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO8);

        CostDetailsAddReturnBO costDetailsAddReturnBO9 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO9.setCategoryName("预估应收账款");
        costDetailsAddReturnBO9.setSum(0d);
        costDetailsAddReturnBO9.setTenMoney(0d);
        costDetailsAddReturnBO9.setFifMoney(0d);
        costDetailsAddReturnBO9.setThityMoney(0d);
        costDetailsAddReturnBO9.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO9);

        CostDetailsAddReturnBO costDetailsAddReturnBO10 = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO10.setCategoryName("实际资金缺口");
        costDetailsAddReturnBO10.setSum(0d);
        costDetailsAddReturnBO10.setTenMoney(0d);
        costDetailsAddReturnBO10.setFifMoney(0d);
        costDetailsAddReturnBO10.setThityMoney(0d);
        costDetailsAddReturnBO10.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO10);

        CostDetailsAddReturnBO costDetailsAddReturnBO = new CostDetailsAddReturnBO();
        costDetailsAddReturnBO.setCategoryName("按时回款预估结余资金");
        costDetailsAddReturnBO.setSum(0d);
        costDetailsAddReturnBO.setTenMoney(0d);
        costDetailsAddReturnBO.setFifMoney(0d);
        costDetailsAddReturnBO.setThityMoney(0d);
        costDetailsAddReturnBO.setTwethMoney(0d);
        costDetailsAddReturnBOS.add(costDetailsAddReturnBO);

        return costDetailsAddReturnBOS;
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        List<DepartmentDetailBO> departmentDetailBOS = departmentDetailAPI.findStatus();
        if (CollectionUtils.isEmpty(departmentDetailBOS)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (DepartmentDetailBO departmentDetailBO : departmentDetailBOS) {
            String details = departmentDetailBO.getDepartment();
            if (StringUtils.isNotBlank(departmentDetailBO.getDepartment())) {
                set.add(details);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<String> findDate() throws SerException {
        List<CostDetails> costDetailsList = super.findAll();
        if (CollectionUtils.isEmpty(costDetailsList)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (CostDetails costDetails : costDetailsList) {
            String costTime = costDetails.getCostTime().toString();
            if (StringUtils.isNotBlank(costTime)) {
                set.add(costTime);
            }
        }
        return new ArrayList<>(set);
    }
}


