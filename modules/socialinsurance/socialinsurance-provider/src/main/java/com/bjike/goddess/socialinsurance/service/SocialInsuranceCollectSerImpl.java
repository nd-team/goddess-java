package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceCollectBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceCollectDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2017-12-21 18:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@CacheConfig(cacheNames = "socialinsuranceSerCache")
@Service
public class SocialInsuranceCollectSerImpl extends ServiceImpl<SocialInsurance, SocialInsuranceCollectDTO> implements SocialInsuranceCollectSer{

    @Override
    public List<SocialInsuranceCollectBO> personalCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String name = "";
        if (dto != null && dto.getName() != null){
            name = dto.getName();
        }
        String[] fields = new String[]{"projectGroup", "scaleContract", "contractAmount", "contractMoney", "makeMoney", "forecastMoney"};
        String sql = "select name, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where name like '%"+ name +"%'group by name";
        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate());
            bo.setEndDate(dto.getEndDate());
        }
        return bos;
    }

    @Override
    public List<SocialInsuranceCollectBO> departmentCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String department = "";
        if (dto != null && dto.getDepartment() != null){
            department = dto.getDepartment();
        }
        String[] fields = new String[]{"projectGroup", "scaleContract", "contractAmount", "contractMoney", "makeMoney", "forecastMoney"};
        String sql = "select department, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where department like '%"+ department +"%'group by department";
        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate());
            bo.setEndDate(dto.getEndDate());
        }
        return bos;
    }

    @Override
    public List<SocialInsuranceCollectBO> areaCollect(SocialInsuranceCollectDTO dto) throws SerException {
        String area = "";
        if (dto != null && dto.getArea() != null){
            area = dto.getArea();
        }
        String[] fields = new String[]{"projectGroup", "scaleContract", "contractAmount", "contractMoney", "makeMoney", "forecastMoney"};
        String sql = "select area, sum(companyTotal) as companyTotal, sum(personalTotal) as personalTotal, sum(amountDue) as amountDue from socialinsurance_socialinsurance where area like '%"+ area +"%'group by area";
        List<SocialInsuranceCollectBO> bos = super.findBySql(sql, SocialInsuranceCollectBO.class, fields);
        for (SocialInsuranceCollectBO bo: bos) {
            bo.setStartDate(dto.getStartDate());
            bo.setEndDate(dto.getEndDate());
        }
        return bos;
    }
}
