package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.CollectBO;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.entity.*;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.enums.PassStatus;
import com.bjike.goddess.moneyside.excel.SonPermissionObject;
import com.bjike.goddess.moneyside.to.CollectTO;
import com.bjike.goddess.moneyside.to.FundEntryTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
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
 * 资金进入申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class FundEntrySerImpl extends ServiceImpl<FundEntry, FundEntryDTO> implements FundEntrySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private FundEntryWrongRecordSer fundEntryWrongRecordSer;
    @Autowired
    private FundEntryConfirmedSer fundEntryConfirmedSer;
    @Autowired
    private AccrualAllotSer accrualAllotSer;
    @Autowired
    private CallInfoSer callInfoSer;
    @Autowired
    private InvestFormSer investFormSer;
    @Autowired
    private CreditorsInvestSer creditorsInvestSer;
    @Autowired
    private EquityInvestSer equityInvestSer;
    @Autowired
    private CashInvestSer cashInvestSer;
    @Autowired
    private CapitalInvestSer capitalInvestSer;
    @Autowired
    private InvestTransferSer investTransferSer;
    @Autowired
    private IncomeDistributionSer incomeDistributionSer;
    @Autowired
    private IncomeQuotaSer incomeQuotaSer;
    @Autowired
    private MoneyExitApplySer moneyExitApplySer;
    @Autowired
    private MoneyExitApplyWrongRecordSer moneyExitApplyWrongRecordSer;
    @Autowired
    private MoneyExitApplyConfirmedSer moneyExitApplyConfirmedSer;
    @Autowired
    private CustomerInfoSer customerInfoSer;
    @Autowired
    private CustomerInfoCollectSer customerInfoCollectSer;
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
            flag = cusPermissionSer.busCusPermission("2");
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("fundentry");
        obj.setDescribesion("资金进入申请");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeRecord = fundEntryWrongRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("fundentrywrongrecord");
        obj.setDescribesion("资金进入申请有误记录");
        if (flagSeeRecord) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeConfirmed = fundEntryConfirmedSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("fundentryconfirmed");
        obj.setDescribesion("资金进入申请已确认");
        if (flagSeeConfirmed) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeAllot = accrualAllotSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("accrualallot");
        obj.setDescribesion("权责分配");
        if (flagSeeAllot) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCall = callInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("callinfo");
        obj.setDescribesion("招投信息列表");
        if (flagSeeCall) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeForm = investFormSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("investform");
        obj.setDescribesion("投资形式");
        if (flagSeeForm) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCred = creditorsInvestSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("creditorsinvest");
        obj.setDescribesion("投资条件-债权投资 ");
        if (flagSeeCred) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEquity = equityInvestSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("equityinvest");
        obj.setDescribesion("投资条件-股权投资");
        if (flagSeeEquity) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCash = cashInvestSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("cashinvest");
        obj.setDescribesion("投资条件-现金投资");
        if (flagSeeCash) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCapital = capitalInvestSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("capitalinvest");
        obj.setDescribesion("资金投资");
        if (flagSeeCapital) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTrans = investTransferSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("investtransfer");
        obj.setDescribesion("投资转让");
        if (flagSeeTrans) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeDistr = incomeDistributionSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("incomedistribution");
        obj.setDescribesion("收益比例分配");
        if (flagSeeDistr) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeQuota = incomeQuotaSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("incomequota");
        obj.setDescribesion("收益分配额");
        if (flagSeeQuota) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeExit = moneyExitApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("moneyexitapply");
        obj.setDescribesion("资金退出申请表");
        if (flagSeeExit) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeWrong = moneyExitApplyWrongRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("moneyexitapplywrongrecord");
        obj.setDescribesion("资金退出申请有误记录");
        if (flagSeeWrong) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeExitCon = moneyExitApplyConfirmedSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("moneyexitapplyconfirmed");
        obj.setDescribesion("资金退出申请确认表");
        if (flagSeeExitCon) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCustomer = customerInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("customerinfo");
        obj.setDescribesion("客户信息");
        if (flagSeeCustomer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCustomerColl = customerInfoCollectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("customerinfocollect");
        obj.setDescribesion("客户信息汇总");
        if (flagSeeCustomerColl) {
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
    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(FundEntryTO fundEntryTO) throws SerException {
        try {
            DateUtil.parseDate(fundEntryTO.getFundEntryTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        Long count = super.count(fundEntryDTO);
        return count;
    }

    @Override
    public FundEntryBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        FundEntry fundEntry = super.findById(id);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Override
    public List<FundEntryBO> findListFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        List<FundEntry> fundEntries = super.findByPage(fundEntryDTO);
        List<FundEntryBO> fundEntryBOS = BeanTransform.copyProperties(fundEntries, FundEntryBO.class);
        return fundEntryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FundEntryBO insertFundEntry(FundEntryTO fundEntryTO) throws SerException {
        checkDate(fundEntryTO);
        FundEntry fundEntry = BeanTransform.copyProperties(fundEntryTO, FundEntry.class, true);
        fundEntry.setCreateTime(LocalDateTime.now());
        super.save(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FundEntryBO editFundEntry(FundEntryTO fundEntryTO) throws SerException {
        if (StringUtils.isBlank(fundEntryTO.getId())) {
            throw new SerException("id不能为空");
        }
        FundEntry fundEntry = super.findById(fundEntryTO.getId());
        checkDate(fundEntryTO);
        BeanUtils.copyProperties(fundEntryTO, fundEntry);
        fundEntry.setModifyTime(LocalDateTime.now());
        super.update(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeFundEntry(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public List<String> getInvestor() throws SerException {
        String[] fields = new String[]{"investor"};
        List<FundEntryBO> fundEntryBOS = super.findBySql("select investor from moneyside_fundentry group by investor order by investor asc ", FundEntryBO.class, fields);

        List<String> investorList = fundEntryBOS.stream().map(FundEntryBO::getInvestor)
                .filter(investor -> (investor != null || !"".equals(investor.trim()))).distinct().collect(Collectors.toList());


        return investorList;
    }

    @Override
    public FundEntryBO audit(FundEntryTO fundEntryTO) throws SerException {
        FundEntry fundEntry = super.findById(fundEntryTO.getId());
        BeanTransform.copyProperties(fundEntryTO, fundEntry, true);
        fundEntry.setModifyTime(LocalDateTime.now());
        fundEntry.setConfirmPeople(fundEntryTO.getConfirmPeople());
        fundEntry.setConfirmSituation(fundEntryTO.getConfirmSituation());
        //是否通过
        if (PassStatus.YES.equals(fundEntry.getPass())) {
            FundEntryConfirmed fundEntryConfirmed = new FundEntryConfirmed();
            BeanUtils.copyProperties(fundEntry, fundEntryConfirmed);
            fundEntryConfirmed.setPass(fundEntryTO.getPass());
            fundEntryConfirmedSer.save(fundEntryConfirmed);
            //BeanUtils.copyProperties(fundEntryConfirmed, FundEntryConfirmedBO.class);
        } else if (PassStatus.NO.equals(fundEntry.getPass())) {
            FundEntryWrongRecord fundEntryWrongRecord = new FundEntryWrongRecord();
            BeanUtils.copyProperties(fundEntry, fundEntryWrongRecord);
            fundEntryWrongRecord.setPass(fundEntryTO.getPass());
            fundEntryWrongRecordSer.save(fundEntryWrongRecord);
        }
        super.update(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        FundEntryDTO dto = new FundEntryDTO();
        String start = to.getStartTime();
        String end = to.getEndTime();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condition = new String[]{start, end};
            dto.getConditions().add(Restrict.between("fundEntryTime", condition));
        }

        if (null != to.getInvestor()) {
            dto.getConditions().add(Restrict.in("investor", to.getInvestor()));
        }
        if (null != to.getAccessToFund()) {
            dto.getConditions().add(Restrict.in("accessToFund", to.getAccessToFund()));
        }
        return fundCollect(dto);
    }

    public List<CollectBO> fundCollect(FundEntryDTO dto) throws SerException {
        List<FundEntry> list = super.findByCis(dto);
        List<CollectBO> boList = new ArrayList<>();
        for (FundEntry model : list) {
            CollectBO bo = new CollectBO();
            bo.setInvestor(model.getInvestor());
            bo.setAccessToFund(model.getAccessToFund());
            bo.setFundEntryTime(DateUtil.dateToString(model.getFundEntryTime()));
            bo.setMoney(model.getMoney());
            boList.add(bo);

        }
        Double totalMoney = 0.0;
        if (list != null) {
            totalMoney = list.stream().filter(p -> p.getMoney() != null).mapToDouble(p -> p.getMoney()).sum();

            CollectBO totalBO = new CollectBO("合计", "", "", totalMoney);
            boList.add(totalBO);
        } else {
            CollectBO totalBO = new CollectBO("合计", "", "", totalMoney);
            boList.add(totalBO);
        }
        return boList;
    }

}