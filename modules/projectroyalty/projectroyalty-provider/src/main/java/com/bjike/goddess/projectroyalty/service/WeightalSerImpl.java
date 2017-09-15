package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.dto.ContractAmountDTO;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.dto.WeightalDTO;
import com.bjike.goddess.projectroyalty.entity.ContractAmount;
import com.bjike.goddess.projectroyalty.entity.ProjectFactors;
import com.bjike.goddess.projectroyalty.entity.Weightal;
import com.bjike.goddess.projectroyalty.to.WeightalTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 项目提成权重分配表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class WeightalSerImpl extends ServiceImpl<Weightal, WeightalDTO> implements WeightalSer {
    @Autowired
    private ProjectFactorsSer projectFactorsSer;
    @Autowired
    private ContractAmountSer contractAmountSer;

    @Override
    public void save(WeightalTO to) throws SerException {
        Weightal entity = BeanTransform.copyProperties(to, Weightal.class, "time", "totalRatio", "compreRatio", "adjCompreRatio", "amountProfit", "businessRatio", "busCompreRatio", "adjBusCompRatio", "business", "menageRatio", "meCompreRatio", "adjMeCompreRatio", "menage", "capitalRatio", "caCompreRatio", "adjCaCompreRatio", "capital", "allRatio");
        entity.setTime(LocalDateTime.now());
        ProjectFactorsDTO projectFactorsDTO = new ProjectFactorsDTO();
        projectFactorsDTO.getConditions().add(Restrict.eq("code", to.getProgram()));
        ProjectFactors projectFactors = projectFactorsSer.findOne(projectFactorsDTO);
        if (null == projectFactors) {
            throw new SerException("所选方案无数据");
        }
        Double contract = entity.getContract();
        Integer collection = entity.getCollection();
        Double importance = entity.getImportance();
        Double facility = entity.getFacility();
        Double ratio = entity.getRatio();

        ContractAmountDTO contractAmountDTO = new ContractAmountDTO();
        contractAmountDTO.getConditions().add(Restrict.eq("contract", contract));
        ContractAmount contractAmount = contractAmountSer.findOne(contractAmountDTO);


        Double businessFactors = entity.getBusinessFactors();
        Double menageFactors = entity.getMenageFactors();
        Double capitalFactors = entity.getCapitalFactors();

        entity.setAdjCompreRatio(0d);
        entity.setAdjBusCompRatio(0d);
        entity.setAdjMeCompreRatio(0d);
        entity.setAdjCaCompreRatio(0d);
        entity.setTotalRatio(projectFactors.getProfitsProportion());
        entity.setBusinessRatio(projectFactors.getBusiness());
        entity.setMenageRatio(projectFactors.getManage());
        entity.setCapitalRatio(projectFactors.getCapital());
        entity.setAmountProfit(entity.getProfit() * entity.getAdjCompreRatio());
        entity.setBusiness(entity.getProfit() * entity.getAdjBusCompRatio());
        entity.setMenage(entity.getProfit() * entity.getAdjMeCompreRatio());
        entity.setCapital(entity.getProfit() * entity.getAdjCaCompreRatio());
        entity.setCompreRatio(100 -);

        entity.setBusCompreRatio();
        entity.setMeCompreRatio();
        entity.setCaCompreRatio();
        entity.setAllRatio();
    }
}