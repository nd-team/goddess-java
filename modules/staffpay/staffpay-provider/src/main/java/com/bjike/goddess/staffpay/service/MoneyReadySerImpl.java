package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.CollectCompareBO;
import com.bjike.goddess.staffpay.bo.MoneyReadyBO;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.entity.MoneyReady;
import com.bjike.goddess.staffpay.to.MoneyReadyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资金准备审核表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {

    @Override
    public Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        Long count = super.count(moneyReadyDTO);
        return count;
    }

    @Override
    public MoneyReadyBO getOne(String id) throws SerException {
        MoneyReady moneyReady = super.findById(id);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        List<MoneyReady> moneyReadies = super.findByPage(moneyReadyDTO);
        List<MoneyReadyBO> moneyReadyBOS = BeanTransform.copyProperties(moneyReadies,MoneyReadyBO.class);
        return moneyReadyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        MoneyReady moneyReady = BeanTransform.copyProperties(moneyReadyTO,MoneyReady.class,true);
        moneyReady.setCreateTime(LocalDateTime.now());
        super.save(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        MoneyReady moneyReady = super.findById(moneyReadyTO.getId());
        BeanTransform.copyProperties(moneyReadyTO,moneyReady,true);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
        return BeanTransform.copyProperties(moneyReady,MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyReady(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public List<CollectCompareBO> collectCompare(Integer month) throws SerException {
        Integer year = LocalDateTime.now().getYear();
        Set<String> projectGroups = findAllProjectGroup();
        MoneyReadyDTO dto = new MoneyReadyDTO();
        List<MoneyReady> list = super.findByCis(dto);
        List<CollectCompareBO> boList = new ArrayList<>();
        Double reserveSum = 0.0;
        Double lastReserveSum = 0.0;
        if (month != 1) {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month - 1)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum-lastReserveSum);
                    bo.setIncrease((reserveSum-lastReserveSum)/lastReserveSum*100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        } else {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year - 1) && m.getMonth().equals(12)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum-lastReserveSum);
                    bo.setIncrease((reserveSum-lastReserveSum)/lastReserveSum*100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        }
    }
    private Set<String> findAllProjectGroup() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getProjectGroup());
        }
        return set;
    }
}