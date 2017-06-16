package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.CollectNameBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 员工住宿水电费业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentutilitiespaySerCache")
@Service
public class StayUtilitiesSerImpl extends ServiceImpl<StayUtilities, StayUtilitiesDTO> implements StayUtilitiesSer {
    @Override
    public Long countStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        Long count = super.count(stayUtilitiesDTO);
        return count;
    }

    @Override
    public StayUtilitiesBO getOne(String id) throws SerException {
        StayUtilities stayUtilities = super.findById(id);
        return BeanTransform.copyProperties(stayUtilities,StayUtilitiesBO.class);
    }

    @Override
    public List<StayUtilitiesBO> findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        List<StayUtilities> stayUtilities = super.findByPage(stayUtilitiesDTO);
        List<StayUtilitiesBO> stayUtilitiesBOS = BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
        return stayUtilitiesBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO insertStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        StayUtilities stayUtilities = BeanTransform.copyProperties(stayUtilitiesTO, StayUtilities.class, true);
//        //是否需要修改
//        if (stayUtilities.getComprehensiveVerifySituation()) {
//            stayUtilities.setStaffVerify(StaffVerify.ERROR);
//        } else {
//            stayUtilities.setComprehensiveVerifySituation(true);
//            stayUtilities.setStaffVerify(StaffVerify.CONFIRM);
//            stayUtilities.setCreateTime(LocalDateTime.now());
//            stayUtilities = count(stayUtilities);
//            super.save(stayUtilities);
//        }
        stayUtilities = count(stayUtilities);
        super.save(stayUtilities);
        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO editStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        StayUtilities stayUtilities = super.findById(stayUtilitiesTO.getId());
        BeanTransform.copyProperties(stayUtilitiesTO, stayUtilities, true);
        stayUtilities.setModifyTime(LocalDateTime.now());
        stayUtilities = count(stayUtilities);
        //是否需要修改
        if (stayUtilities.getStaffVerify().equals(StaffVerify.ERROR)) {
            stayUtilities.setComprehensiveVerifySituation(true);
        } else if(stayUtilities.getStaffVerify().equals(StaffVerify.CONFIRM)){
            stayUtilities.setComprehensiveVerifySituation(false);
            stayUtilities.setCreateTime(LocalDateTime.now());
            super.update(stayUtilities);
        }
        return BeanTransform.copyProperties(stayUtilitiesTO, StayUtilitiesBO.class);
    }

    /**
     * 计算方法
     */
    public StayUtilities count(StayUtilities stayUtilities) throws SerException {
        //同一住宿地址员工住宿天数总和
        String[] fields = new String[]{"sumDays", "address"};
        String sql = " select cast(sum(stayDay)as SIGNED )as sumDays ,address from rentutilitiespay_stayutilities group by address ";
        List<StayUtilities> sum = super.findBySql(sql, StayUtilities.class, fields);
        Integer sumDays = sum.get(0).getSumDays();

        //个人员工住宿天数
        fields = new String[]{"personalDays","name"};
        //String name = stayUtilities.getName();
        //sql = "select cast(sum(stayDay)as SIGNED ) as personalDays from rentutilitiespay_stayutilities where name = '"+name+"' ";
        sql = "select cast(sum(stayDay)as SIGNED ) as personalDays,name from rentutilitiespay_stayutilities group by name ";
        List<StayUtilities> num = super.findBySql(sql, StayUtilities.class, fields);
        Integer personalDays = num.get(0).getPersonalDays();
        //水费员工缴纳（(当月应缴水费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double waterStaffPay = (stayUtilities.getWaterAmount() / sumDays) * personalDays;
        stayUtilities.setWaterStaffPay(waterStaffPay);
        //电费员工缴纳（(当月应缴电费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double energyStaffPay = (stayUtilities.getEnergyAmount() / sumDays) * personalDays;
        stayUtilities.setEnergyStaffPay(energyStaffPay);
        //燃气费员工缴纳（(管道燃气费充值额度/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double gasStaffPay = (stayUtilities.getGasRechargeLines() / sumDays) * personalDays;
        stayUtilities.setGasStaffPay(gasStaffPay);

        //员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）
        Double staffPayCollect = waterStaffPay + energyStaffPay + gasStaffPay;
        stayUtilities.setStaffPayCollect(staffPayCollect);
        return stayUtilities;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayUtilities(String id) throws SerException {
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);

    }

    @Override
    public List<CollectNameBO> collectName(String[] names) throws SerException {
        String[] namesTemp = new String[names.length];
        for(int i = 0;i<names.length;i++){
            namesTemp[i] = "'"+names[i]+"'";
        }
        String namesStr = StringUtils.join(namesTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT name,num AS num,area AS area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark ");
        sb.append(" FROM rentutilitiespay_stayutilities WHERE name IN (%s) GROUP BY name,num,area,projectGroup,projectName,address, ");
        sb.append(" name ORDER BY name)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS name,NULL as num,NULL as area,NULL as projectGroup,NULL as projectName,NULL as address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark FROM ");
        sb.append(" (SELECT name,num AS num,area AS area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark ");
        sb.append(" FROM rentutilitiespay_stayutilities WHERE name IN (%s) GROUP BY name,num,area,projectGroup,projectName,address, ");
        sb.append(" name ORDER BY name)A ");
        String sql = sb.toString();
        sql = String.format(sql, namesStr,namesStr);
        String [] fields = new String[]{"name","num","area","projectGroup","projectName","address",
               "waterStaffPay","energyStaffPay","gasStaffPay","remark"};
        List<CollectNameBO> collectNameBOS = super.findBySql(sql,CollectNameBO.class,fields);
        return collectNameBOS;
    }

    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"name"};
        List<StayUtilitiesBO> stayUtilitiesBOS = super.findBySql("select name from rentutilitiespay_stayutilities order by name asc ", StayUtilitiesBO.class, fields);

        List<String> nameList = stayUtilitiesBOS.stream().map(StayUtilitiesBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }

}