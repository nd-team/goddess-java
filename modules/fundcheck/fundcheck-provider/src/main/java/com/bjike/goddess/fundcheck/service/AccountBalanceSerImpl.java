package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.bankrecords.api.BankRecordAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.fundcheck.bo.AccountBalanceBO;
import com.bjike.goddess.fundcheck.bo.AccountIncomeBO;
import com.bjike.goddess.fundcheck.bo.AccountSpendBO;
import com.bjike.goddess.fundcheck.bo.BeginBalanceBO;
import com.bjike.goddess.fundcheck.dto.AccountBalanceDTO;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.entity.AccountBalance;
import com.bjike.goddess.fundcheck.entity.AccountIncome;
import com.bjike.goddess.fundcheck.entity.AccountSpend;
import com.bjike.goddess.fundcheck.entity.BeginBalance;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.AccountBalanceCollectTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * 账上余额业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class AccountBalanceSerImpl extends ServiceImpl<AccountBalance, AccountBalanceDTO> implements AccountBalanceSer {
    @Autowired
    private BeginBalanceSer beginBalanceSer;
    @Autowired
    private AccountIncomeSer accountIncomeSer;
    @Autowired
    private AccountSpendSer accountSpendSer;
    @Autowired
    private BankRecordAPI bankRecordAPI;
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
    public List<AccountBalanceBO> collect(AccountBalanceCollectTO to) throws SerException {
        List<AccountBalanceBO> accountBalanceBOList = new ArrayList<>();
        AccountBalanceDTO dto = new AccountBalanceDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            String[] condi = new String[]{startTime,endTime};
            dto.getConditions().add(Restrict.between("date",condi));
        }
        //获取期初余额
        List<BeginBalanceBO> beginBalanceBOS = beginBalanceSer.getBeginBalace(startTime,endTime);
        Double beginBalanceMoney = beginBalanceBOS.stream().filter(str -> null != str.getBeginBalance()).mapToDouble(BeginBalanceBO::getBeginBalance).sum();

        //获取账务收入的合计
        List<AccountIncomeBO> accountIncomeBOS = accountIncomeSer.collect(startTime,endTime);
        Double accountIncomeMoney = accountIncomeBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(AccountIncomeBO::getMoney).sum();

        //获取账务支出的合计
        List<AccountSpendBO> accountSpendBOS = accountSpendSer.collect(startTime,endTime);
        Double accountSpendMoney = accountSpendBOS.stream().filter(str->null!=str.getTotal()).mapToDouble(AccountSpendBO::getTotal).sum();

        //获取银行流水的账上余额
        Double bank = bankRecordAPI.balanceByMonth(startTime,endTime);
        //资金差异(期初余额+账务收入-财务支出)
        Double fundsDifference = beginBalanceMoney+accountIncomeMoney-accountSpendMoney;

        AccountBalanceBO bo = new AccountBalanceBO();
        bo.setDate(startTime+"-"+endTime);
        bo.setBeginningBalance(beginBalanceMoney);
        bo.setAccountIncome(accountIncomeMoney);
        bo.setAccountBalance(bank);
        bo.setAccountSpend(accountSpendMoney);
        bo.setFundsDifference(fundsDifference);
        accountBalanceBOList.add(bo);
        return accountBalanceBOList;
    }
}