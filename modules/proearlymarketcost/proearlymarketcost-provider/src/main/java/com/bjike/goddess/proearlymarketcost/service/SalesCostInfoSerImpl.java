package com.bjike.goddess.proearlymarketcost.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.proearlymarketcost.bo.SalesCostInfoBO;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.entity.SalesCostInfo;
import com.bjike.goddess.proearlymarketcost.to.SalesCostInfoTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售费用信息业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-23 03:36 ]
 * @Description: [ 销售费用信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "proearlymarketcostSerCache")
@Service
public class SalesCostInfoSerImpl extends ServiceImpl<SalesCostInfo, SalesCostInfoDTO> implements SalesCostInfoSer {
    @Transactional(rollbackFor = SerException.class)
    @Override
    public SalesCostInfoBO save(SalesCostInfoTO salesCostInfoTO) throws SerException {
        SalesCostInfo salesCostInfo = BeanTransform.copyProperties(salesCostInfoTO, SalesCostInfo.class, true);
        super.save(salesCostInfo);
        salesCostInfoTO.setId(salesCostInfoTO.getId());
        return BeanTransform.copyProperties(salesCostInfoTO, SalesCostInfoBO.class, true);
    }

    @Override
    public List<SalesCostInfoBO> list(SalesCostInfoDTO salesCostInfoDTO) throws SerException {
        List<SalesCostInfo> salesCostInfos = super.findByCis(salesCostInfoDTO);
        return BeanTransform.copyProperties(salesCostInfos, SalesCostInfoBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(SalesCostInfoTO salesCostInfoTO) throws SerException {
        SalesCostInfo salesCostInfo = super.findById(salesCostInfoTO.getId());
        BeanTransform.copyProperties(salesCostInfoTO, salesCostInfo, true);
        salesCostInfo.setModifyTime(LocalDateTime.now());
        super.update(salesCostInfo);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<SalesCostInfoBO> collect(SalesCostInfoBO salesCostInfoBO)throws SerException{
        SalesCostInfoDTO dto = new SalesCostInfoDTO();
        if(salesCostInfoBO.getTwoSubject() !=null && !salesCostInfoBO.getTwoSubject().equals("")){
                dto.getConditions().add(Restrict.eq("",salesCostInfoBO.getTwoSubject()));
        }
        if(salesCostInfoBO.getThreeSubject() !=null && !salesCostInfoBO.getThreeSubject().equals("")){
            dto.getConditions().add(Restrict.eq("",salesCostInfoBO.getThreeSubject()));
        }
        if(salesCostInfoBO.getArea() !=null && !salesCostInfoBO.getArea().equals("")){
            dto.getConditions().add(Restrict.eq("",salesCostInfoBO.getArea()));
        }
        if(salesCostInfoBO.getProjectName() !=null && !salesCostInfoBO.getProjectName().equals("")){
            dto.getConditions().add(Restrict.eq("",salesCostInfoBO.getProjectName()));
        }
        return BeanTransform.copyProperties(super.findByCis(dto),SalesCostInfoBO.class);
    }
}