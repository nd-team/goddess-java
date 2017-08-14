package com.bjike.goddess.checkfunds.service;

import com.bjike.goddess.bankrecords.api.BankRecordAPI;
import com.bjike.goddess.bankrecords.beanlist.Detail;
import com.bjike.goddess.bankrecords.bo.BankRecordBO;
import com.bjike.goddess.bankrecords.bo.BankRecordCollectBO;
import com.bjike.goddess.bankrecords.bo.BankRecordPageListBO;
import com.bjike.goddess.checkfunds.bo.*;
import com.bjike.goddess.checkfunds.dto.BankReconciliationDTO;
import com.bjike.goddess.checkfunds.dto.NotPassAuditDTO;
import com.bjike.goddess.checkfunds.dto.PassAuditDTO;
import com.bjike.goddess.checkfunds.dto.RemainAdjustDTO;
import com.bjike.goddess.checkfunds.entity.BankReconciliation;
import com.bjike.goddess.checkfunds.enums.GuideAddrStatus;
import com.bjike.goddess.checkfunds.to.*;
import com.bjike.goddess.checkfunds.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.fundrecords.api.FundRecordAPI;
import com.bjike.goddess.fundrecords.bo.ConditionCollectBO;
import com.bjike.goddess.fundrecords.bo.MonthCollectBO;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 银企对账（核对）业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对）业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkfundsSerCache")
@Service
public class BankReconciliationSerImpl extends ServiceImpl<BankReconciliation, BankReconciliationDTO> implements BankReconciliationSer {
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BankRecordAPI bankRecordAPI;
    @Autowired
    private FundRecordAPI fundRecordAPI;
    @Autowired
    private RemainAdjustSer remainAdjustSer;
    @Autowired
    private PassAuditSer passAuditSer;
    @Autowired
    private NotPassAuditSer notPassAuditSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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
     * 审批权限
     */
    private void checkAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        List<PositionDetailBO> boList = positionDetailAPI.findStatus();
        List<UserBO> list = null;
        if ((boList != null) && (!boList.isEmpty())) {
            for (PositionDetailBO bo : boList) {
                if ("管理层".equals(bo.getArrangementName()) && "资金模块".equals(bo.getModuleName()) && "资金模块负责人".equals(bo.getPosition())) {
                    list = positionDetailUserAPI.findByPosition(bo.getId());
                }
            }
        }
        boolean b = false;
        if ((list != null) && (!list.isEmpty())) {
            for (UserBO bo : list) {
                if (userName.equals(bo.getUsername())) {
                    b = true;
                }
            }
        }
        if (!b) {
            RpcTransmit.transmitUserToken(userToken);
            flag = cusPermissionSer.getCusPermission("3");
            if (!flag) {
                throw new SerException("您不是资金模块负责人，不可以操作");
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
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("bankreconciliation");
        obj.setDescribesion("银企对账（核对）");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = notPassAuditSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("notpassaudit");
        obj.setDescribesion("审批不通过记录");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeCate = passAuditSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("passaudit");
        obj.setDescribesion("已完成核对记录");
        if (flagSeeCate) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeEmail = remainAdjustSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("remainadjust");
        obj.setDescribesion("余额调节");
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
            case HANDLE:
                flag = guideAddIdentity();
                break;
            case COMMIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case ADJUST:
                flag = guideAddIdentity();
                break;
            case DETAIL:
                flag = guideSeeIdentity();
                break;
            case DIFFER:
                flag = guideSeeIdentity();
                break;
            case CONFIRM:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public Set<String> allNames() throws SerException {
        AccountDTO dto = new AccountDTO();
        return accountAPI.allNames(dto);
    }

    private String findAccountByName(String name) throws SerException {
        return accountAPI.findByName(name);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BankReconciliationBO save(BankReconciliationTO to) throws SerException {
        checkAddIdentity();
        String name = userAPI.currentUser().getUsername();
        BankReconciliation entity = BeanTransform.copyProperties(to, BankReconciliation.class, true);
        entity.setAduitStatus("已经办，未提交");
        entity.setHandleTime(LocalDateTime.now());
        entity.setHandler(name);
        String account = findAccountByName(entity.getName());
        if (account == null) {
            throw new SerException("没有该用户名对应的账号");
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, BankReconciliationBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void commit(String id) throws SerException {
        checkAddIdentity();
        String name = userAPI.currentUser().getUsername();
        BankReconciliation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setHandler(name);
        entity.setHandleTime(LocalDateTime.now());
        entity.setAduitStatus("已提交，待审批");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void aduitPass(String id) throws SerException {
        aduit(id);
        String userToken = RpcTransmit.getUserToken();
        PassAuditDTO dto = new PassAuditDTO();
        dto.getConditions().add(Restrict.eq("bankReconciliationId", id));
        RpcTransmit.transmitUserToken(userToken);
        List<PassAuditBO> list = passAuditSer.list(dto);
        if ((list != null) && (!list.isEmpty())) {
            throw new SerException("您已审批过该记录");
        }
        PassAuditTO passAuditTO = new PassAuditTO();
        passAuditTO.setAuditStatus("已完成");
        passAuditTO.setBankReconciliationId(id);
        passAuditSer.save(passAuditTO);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void aduitNotPass(String id) throws SerException {
        aduit(id);
        String userToken = RpcTransmit.getUserToken();
        NotPassAuditDTO dto = new NotPassAuditDTO();
        dto.getConditions().add(Restrict.eq("bankReconciliationId", id));
        RpcTransmit.transmitUserToken(userToken);
        List<NotPassAuditBO> list = notPassAuditSer.list(dto);
        if ((list != null) && (!list.isEmpty())) {
            throw new SerException("您已审批过该记录");
        }
        NotPassAuditTO notPassAuditTO = new NotPassAuditTO();
        notPassAuditTO.setAuditStatus("审批不通过");
        notPassAuditTO.setBankReconciliationId(id);
        notPassAuditSer.save(notPassAuditTO);
    }

    @Transactional(rollbackFor = SerException.class)
    private void aduit(String id) throws SerException {
        checkAddIdentity();
        String userToken = RpcTransmit.getUserToken();
        String name = userAPI.currentUser().getUsername();
        BankReconciliation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        if ((entity.getHaveExamine() != null) && (entity.getHaveExamine())) {
            throw new SerException("您已审批过该记录");
        }
        if (!"已提交，待审批".equals(entity.getAduitStatus())) {
            throw new SerException("您还没提交，不能进行审批");
        }
        entity.setAduitStatus("已审批");
        entity.setHaveExamine(true);
        entity.setExamine(name);
        entity.setExamineTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        RpcTransmit.transmitUserToken(userToken);
    }

    @Override
    public List<RemainAdjustBO> adjust(String id) throws SerException {
        checkAddIdentity();
        BankReconciliationBO bo = findByID(id);
        Double bankBalance = bo.getBankBalance();
        Double fundBalance = bo.getFundBalance();
        Integer year = bo.getYear();
        Integer month = bo.getMonth();
        RemainAdjustDTO dto = new RemainAdjustDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        dto.getConditions().add(Restrict.eq("type", "1"));
        Long num = remainAdjustSer.count(dto);
        if (num == 0) {
            RemainAdjustTO to = new RemainAdjustTO();
            to.setBankWaterProject("银行流水余额");
            to.setBankWaterSum(bankBalance);
            to.setMoneyWaterProject("资金流水金额");
            to.setMoneyWaterSum(fundBalance);
            to.setYear(year);
            to.setMonth(month);
            to.setType("1");   //用于排序，1为识别第一列
            remainAdjustSer.save(to);
        }
        return boList(id);
    }

    @Override
    public List<RemainAdjustBO> boList(String id) throws SerException {
        BankReconciliation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        Integer year = entity.getYear();
        Integer month = entity.getMonth();
        RemainAdjustDTO dto = new RemainAdjustDTO();
        dto.getSorts().add("type=desc");
        dto.getSorts().add("bankType=asc");
        dto.getSorts().add("fundType=asc");
        dto.getConditions().add(Restrict.eq("year", year));
        dto.getConditions().add(Restrict.eq("month", month));
        List<RemainAdjustBO> list = remainAdjustSer.findByDTO(dto);
        RemainAdjustBO firstBO = list.get(0);
        Double bankBalance = firstBO.getBankWaterSum();
        Double fundBalance = firstBO.getMoneyWaterSum();
        double addBank = 0;
        double addFund = 0;
        double removeBank = 0;
        double removeFund = 0;
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
//        boolean b5 = true;
//        boolean b6 = true;
        List<RemainAdjustBO> boList = new ArrayList<RemainAdjustBO>();
        for (RemainAdjustBO remainAdjustBO : list) {
            if ("jia".equals(remainAdjustBO.getFundType())) {   //jia为识别资金流水加的项目
//                if ("jian".equals(remainAdjustBO.getBankType())) {
//                    if (b5) {
//                        RemainAdjustBO addBO = new RemainAdjustBO();
//                        addBO.setBankWaterProject("减：");
//                        boList.add(addBO);
//                        b5 = false;
//                    }
//                }
                if (b1) {
                    RemainAdjustBO addBO = new RemainAdjustBO();
                    addBO.setMoneyWaterProject("加：");
                    boList.add(addBO);
                    b1 = false;
                }
                addFund += remainAdjustBO.getMoneyWaterSum();
            } else if ("jian".equals(remainAdjustBO.getFundType())) {   //jian为识别资金流水减的项目
//                if ("jia".equals(remainAdjustBO.getBankType())) {
//                    if (b6) {
//                        RemainAdjustBO addBO = new RemainAdjustBO();
//                        addBO.setBankWaterProject("加：");
//                        boList.add(addBO);
//                        b6 = false;
//                    }
//                }
                if (b2) {
                    RemainAdjustBO addBO = new RemainAdjustBO();
                    addBO.setMoneyWaterProject("减：");
                    boList.add(addBO);
                    b2 = false;
                }
                removeFund += remainAdjustBO.getMoneyWaterSum();
            } else if ("jia".equals(remainAdjustBO.getBankType())) {  //jia为识别银行流水加的项目
                if (b3) {
                    RemainAdjustBO addBO = new RemainAdjustBO();
                    addBO.setBankWaterProject("加：");
                    boList.add(addBO);
                    b3 = false;
                }
                addBank += remainAdjustBO.getBankWaterSum();
            } else if ("jian".equals(remainAdjustBO.getBankType())) {  //jian为识别银行流水减的项目
                if (b4) {
                    RemainAdjustBO addBO = new RemainAdjustBO();
                    addBO.setBankWaterProject("减：");
                    boList.add(addBO);
                    b4 = false;

                }
                removeBank += remainAdjustBO.getBankWaterSum();
            }
            boList.add(remainAdjustBO);
        }
        RemainAdjustBO lastBO = new RemainAdjustBO();
        lastBO.setMoneyWaterProject("余额");
        Double fundSum = fundBalance + addFund - removeFund;
        lastBO.setMoneyWaterSum(fundSum);
        lastBO.setBankWaterProject("余额");
        Double bankSum = bankBalance + addBank - removeBank;
        lastBO.setBankWaterSum(bankSum);
        boList.add(lastBO);
        return boList;
    }

    @Override
    public List<BankReconciliationBO> list(BankReconciliationDTO dto) throws SerException {
        checkSeeIdentity();
        List<BankReconciliation> list = super.findByCis(dto);
        List<BankReconciliationBO> boList = new ArrayList<BankReconciliationBO>();
        for (BankReconciliation b : list) {
            BankReconciliationBO bo = BeanTransform.copyProperties(b, BankReconciliationBO.class);
            boList.add(showBO(bo));
        }
        return boList;
    }

    @Override
    public BankReconciliationBO findByID(String id) throws SerException {
        BankReconciliation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        BankReconciliationBO bo = BeanTransform.copyProperties(entity, BankReconciliationBO.class);
        return showBO(bo);
    }

    private BankReconciliationBO showBO(BankReconciliationBO bo) throws SerException {
        String account = findAccountByName(bo.getName());
        List<BankRecordCollectBO> boList = bankRecordAPI.collectByCondition(bo.getYear(), bo.getMonth(), account);
        double bankDebtor = 0;
        double bankCreditor = 0;
        double bankBalance = 0;
        if ((boList != null) && (!boList.isEmpty())) {
            BankRecordCollectBO bankRecordCollectBO = boList.get(boList.size() - 1);
            bankDebtor = bankRecordCollectBO.getDebtorCost();
            bankCreditor = bankRecordCollectBO.getCreditorCost();
            bankBalance = bankRecordCollectBO.getBalance();
        }
        MonthCollectBO monthCollectBO = fundRecordAPI.month(bo.getYear(), bo.getMonth());
        Double fundDebtor = monthCollectBO.getIncome();
        Double fundCreditor = monthCollectBO.getExpenditure();
        Double fundBalance = monthCollectBO.getCurrentBalance();
        Double debtorDiffer = fundDebtor - bankDebtor;
        Double creditorDiffer = fundCreditor - bankCreditor;
        double balanceDiffer = fundBalance - bankBalance;
        bo.setBankDebtor(bankDebtor);
        bo.setBankcreditor(bankCreditor);
        bo.setBankBalance(bankBalance);
        bo.setFundDebtor(fundDebtor);
        bo.setFundcreditor(fundCreditor);
        bo.setFundBalance(fundBalance);
        bo.setAccount(account);
        bo.setDebtorDiffer(debtorDiffer);
        bo.setCreditorDiffer(creditorDiffer);
        bo.setBalanceDiffer(balanceDiffer);
        if (bo.getRemainAduitStatus() == null) {
            if (balanceDiffer == 0) {
                bo.setRemainAduitStatus("余额相符");
            } else {
                bo.setRemainAduitStatus("余额不符");
            }
        }
        return bo;
    }

    @Override
    public Long countNum(BankReconciliationDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void confirmAdjust(String id, Double balance) throws SerException {
        checkAddIdentity();
        String name = userAPI.currentUser().getUsername();
        BankReconciliation entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        entity.setAfterReconciliation("余额相符，调整余额为" + balance + "元");
        entity.setRemainAduitStatus("余额调节");
        entity.setHandler(name);
        entity.setHandleTime(LocalDateTime.now());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<FundDetailBO> fundDetail(String id) throws SerException {
        checkSeeIdentity();
        BankReconciliationBO bo = findByID(id);
        CollectTO collectTO = new CollectTO();
        collectTO.setYear(bo.getYear());
        collectTO.setMonth(bo.getMonth());
        List<ConditionCollectBO> boList = fundRecordAPI.condition(collectTO);
        return BeanTransform.copyProperties(boList, FundDetailBO.class);
    }

    @Override
    public List<BankRecordPageListBO> bankDetail(String id) throws SerException {
        checkSeeIdentity();
        BankReconciliationBO bo = findByID(id);
        String account = bo.getAccount();
        Integer month = bo.getMonth();
        Integer year = bo.getYear();
        List<BankRecordPageListBO> bankRecordPageListBOs = new ArrayList<BankRecordPageListBO>();
        List<BankRecordBO> boList = bankRecordAPI.findByCondition(year, month, account);
        if ((boList != null) && (!boList.isEmpty())) {
            for (BankRecordBO bankRecordBO : boList) {
                BankRecordPageListBO bankRecordPageListBO = bankRecordAPI.findById(bankRecordBO.getId());
                bankRecordPageListBOs.add(bankRecordPageListBO);
            }
        }
        return bankRecordPageListBOs;
    }

    @Override
    public List<DebtorDifferBO> debtorDiffer(String id) throws SerException {
        checkSeeIdentity();
        BankReconciliationBO bo = findByID(id);
        CollectTO collectTO = new CollectTO();
        collectTO.setYear(bo.getYear());
        collectTO.setMonth(bo.getMonth());
        List<ConditionCollectBO> conditionCollectBOs = fundRecordAPI.condition(collectTO);
        List<DebtorDifferBO> debtorDifferBOs = BeanTransform.copyProperties(conditionCollectBOs, DebtorDifferBO.class);
        String account = bo.getAccount();
        Integer month = bo.getMonth();
        Integer year = bo.getYear();
        List<BankRecordPageListBO> bankRecordPageListBOs = new ArrayList<BankRecordPageListBO>();
        List<BankRecordBO> boList = bankRecordAPI.findByCondition(year, month, account);
        if ((boList != null) && (!boList.isEmpty())) {
            for (BankRecordBO bankRecordBO : boList) {
                BankRecordPageListBO bankRecordPageListBO = bankRecordAPI.findById(bankRecordBO.getId());
                bankRecordPageListBOs.add(bankRecordPageListBO);
            }
        }
        if ((debtorDifferBOs != null) && (!debtorDifferBOs.isEmpty())) {
            for (int i = 0; i < debtorDifferBOs.size(); i++) {
                BankRecordPageListBO bankRecordPageListBO = bankRecordPageListBOs.get(i);
                String bankRecorId = bankRecordPageListBO.getId();
                List<Detail> detailList = bankRecordPageListBO.getDetailList();
                DebtorDifferBO debtorDifferBO = debtorDifferBOs.get(i);
                debtorDifferBO.setId(bankRecorId);
                List<com.bjike.goddess.checkfunds.beanlist.Detail> details = debtorDifferBO.getDetailList();
                details = BeanTransform.copyProperties(detailList, com.bjike.goddess.checkfunds.beanlist.Detail.class);
                for (com.bjike.goddess.checkfunds.beanlist.Detail detail : details) {
                    if (detail.getTitle().contains("借方")) {
                        debtorDifferBO.setBankIncome(Double.parseDouble(detail.getVal()));
                    }
                }
                debtorDifferBO.setDetailList(details);
            }
        }
        return debtorDifferBOs;
    }

    @Override
    public List<CreditorDifferBO> creditorDiffer(String id) throws SerException {
        checkSeeIdentity();
        BankReconciliationBO bo = findByID(id);
        CollectTO collectTO = new CollectTO();
        collectTO.setYear(bo.getYear());
        collectTO.setMonth(bo.getMonth());
        List<ConditionCollectBO> conditionCollectBOs = fundRecordAPI.condition(collectTO);
        List<CreditorDifferBO> creditorDifferBOs = BeanTransform.copyProperties(conditionCollectBOs, CreditorDifferBO.class);
        String account = bo.getAccount();
        Integer month = bo.getMonth();
        Integer year = bo.getYear();
        List<BankRecordPageListBO> bankRecordPageListBOs = new ArrayList<BankRecordPageListBO>();
        List<BankRecordBO> boList = bankRecordAPI.findByCondition(year, month, account);
        if ((boList != null) && (!boList.isEmpty())) {
            for (BankRecordBO bankRecordBO : boList) {
                BankRecordPageListBO bankRecordPageListBO = bankRecordAPI.findById(bankRecordBO.getId());
                bankRecordPageListBOs.add(bankRecordPageListBO);
            }
        }
        if ((creditorDifferBOs != null) && (!creditorDifferBOs.isEmpty())) {
            for (int i = 0; i < creditorDifferBOs.size(); i++) {
                BankRecordPageListBO bankRecordPageListBO = bankRecordPageListBOs.get(i);
                String bankRecorId = bankRecordPageListBO.getId();
                List<Detail> detailList = bankRecordPageListBO.getDetailList();
                CreditorDifferBO creditorDifferBO = creditorDifferBOs.get(i);
                creditorDifferBO.setId(bankRecorId);
                List<com.bjike.goddess.checkfunds.beanlist.Detail> details = creditorDifferBO.getDetailList();
                details = BeanTransform.copyProperties(detailList, com.bjike.goddess.checkfunds.beanlist.Detail.class);
                for (com.bjike.goddess.checkfunds.beanlist.Detail detail : details) {
                    if (detail.getTitle().contains("贷方")) {
                        creditorDifferBO.setBankExpend(Double.parseDouble(detail.getVal()));
                    }
                }
                creditorDifferBO.setDetailList(details);
            }
        }
        return creditorDifferBOs;
    }
}