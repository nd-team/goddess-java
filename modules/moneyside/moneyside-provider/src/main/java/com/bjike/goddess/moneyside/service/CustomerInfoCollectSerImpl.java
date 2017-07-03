package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.moneyside.bo.CustomerInfoCollectBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfoCollect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息汇总业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CustomerInfoCollectSerImpl extends ServiceImpl<CustomerInfoCollect, CustomerInfoCollectDTO> implements CustomerInfoCollectSer {
    @Override
    public List<CustomerInfoCollectBO> collect(CustomerInfoCollectDTO dto) throws SerException {
        if(null != dto.getInvestor()){
            dto.getConditions().add(Restrict.eq("investor",dto.getInvestor()));
        }
        String sql = "SELECT a.investor,a.accessToFund,a.fundEntryTime," +
                " b.thisInvestMoney,b.investObject,b.investTotal,b.investProportion," +
                " c.exitTime,c.exitMoney,c.exitInterest,d.transferee,d.needInvestAmount," +
                " e.incomeDistributionTime,e.proportionInvestment,f.totalQuota " +
                " FROM moneyside_fundentry a,moneyside_callinfo b,moneyside_moneyexitapply c," +
                " moneyside_investtransfer d,moneyside_incomedistribution e," +
                " moneyside_incomequota f WHERE a.investor = b.investor and " +
                " a.investor = c.investor and a.investor = d.investor and " +
                " a.investor = e.investor AND a.investor=f.investor";
        String [] fields = new String[]{"investor","accessToFund","fundEntryTime","thisInvestMoney",
            "investObject","investTotal","investProportion","exitTime","exitMoney",
            "exitInterest","transferee","needInvestAmount","incomeDistributionTime",
            "proportionInvestment","totalQuota"};
        List<CustomerInfoCollectBO> customerInfoCollectBOS = super.findBySql(sql ,CustomerInfoCollectBO.class,fields);
        return customerInfoCollectBOS;
    }
}