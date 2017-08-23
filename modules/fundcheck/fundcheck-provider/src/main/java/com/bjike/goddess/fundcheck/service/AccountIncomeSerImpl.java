package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.fundcheck.bo.*;
import com.bjike.goddess.fundcheck.dto.AccountIncomeDTO;
import com.bjike.goddess.fundcheck.entity.AccountIncome;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 账务收入业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:52 ]
 * @Description: [ 账务收入业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "fundcheckSerCache")
@Service
public class AccountIncomeSerImpl extends ServiceImpl<AccountIncome, AccountIncomeDTO> implements AccountIncomeSer {
    @Autowired
    private BackSer backSer;
    @Autowired
    private StockMoneySer stockMoneySer;
    @Autowired
    private OtherIncomeSer otherIncomeSer;
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
    public List<AccountIncomeBO> collect(String startTime,String endTime) throws SerException {
        List<AccountIncomeBO> accountIncomeBOList = new ArrayList<>();
        AccountIncomeDTO dto = new AccountIncomeDTO();
//        String startTime = to.getStartTime();
//        String endTime = to.getEndTime();
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            String[] condi = new String[]{startTime, endTime};
            dto.getConditions().add(Restrict.between("date", condi));
        }
        //获取回款金额的合计
        List<BackBO> backBOS = backSer.backinfo(startTime,endTime);
        Double backMoney = backBOS.stream().filter(str -> null != str.getAccountMoney()).mapToDouble(BackBO::getAccountMoney).sum();

        //获取股东借款金额的合计
        String[] fields = new String[]{"money"};
        String sql = "select sum(money) AS money from  fundcheck_stockmoney where date between '" + startTime + "' and '" + endTime + "' ";
        List<StockMoneyBO> stockMoneyBOS = stockMoneySer.findBySql(sql, StockMoneyBO.class, fields);
        Double stockMoney = stockMoneyBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(StockMoneyBO::getMoney).sum();

        //获取其他收入金额的合计
        fields = new String[]{"money"};
        sql = "select sum(money) AS money from  fundcheck_otherincome where date between '" + startTime + "' and '" + endTime + "' ";
        List<OtherIncomeBO> otherIncomeBOS = otherIncomeSer.findBySql(sql, OtherIncomeBO.class, fields);
        Double otherIncomeMoney = otherIncomeBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(OtherIncomeBO::getMoney).sum();

        //账务收入合计
        Double accountImcomeMoney = backMoney+stockMoney+otherIncomeMoney;

        AccountIncomeBO bo = new AccountIncomeBO();
        bo.setDate(startTime+"-"+endTime);
        bo.setBack(backMoney);
        bo.setStockMoney(stockMoney);
        bo.setOtherIncome(otherIncomeMoney);
        bo.setMoney(accountImcomeMoney);
        accountIncomeBOList.add(bo);

        return accountIncomeBOList;
    }
}