package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.outcarfare.bo.MoneyReadyBO;
import com.bjike.goddess.outcarfare.bo.MoneyReadyCountBO;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.entity.MoneyReady;
import com.bjike.goddess.outcarfare.to.MoneyReadyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资金准备审核业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "outcarfareSerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
    @Override
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        MoneyReady m = BeanTransform.copyProperties(to, MoneyReady.class, true);
        super.save(m);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    public void edit(MoneyReadyTO to) throws SerException {
        MoneyReady moneyReady = super.findById(to.getId());
        LocalDateTime a = moneyReady.getCreateTime();
        LocalDateTime b = moneyReady.getModifyTime();
        moneyReady = BeanTransform.copyProperties(to, MoneyReady.class, true);
        moneyReady.setCreateTime(a);
        moneyReady.setModifyTime(b);
        super.update(moneyReady);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        List<MoneyReady> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, MoneyReadyBO.class);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        MoneyReady m = super.findById(id);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyCountBO> count(Integer month) throws SerException {
        Integer year = LocalDateTime.now().getYear();
        Set<String> groupTeams = findAllGroupTeams();
        MoneyReadyDTO dto = new MoneyReadyDTO();
        List<MoneyReady> list = super.findByCis(dto);
        List<MoneyReadyCountBO> boList = new ArrayList<MoneyReadyCountBO>();
        Double currentMonthReserveSum = 0.00;
        Double lastMonthReserveSum = 0.00;
        if (month != 1) {
            for (String groupTeam : groupTeams) {
                for (MoneyReady m : list) {
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        currentMonthReserveSum += m.getReserve();
                    }
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month - 1)) {
                        lastMonthReserveSum += m.getReserve();
                    }
                }
                if ((currentMonthReserveSum != 0) || (lastMonthReserveSum != 0)) {
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(groupTeam);
                    bo.setMonth(month);
                    bo.setCurrentMonthReserveSum(currentMonthReserveSum);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    bo.setDifferences(currentMonthReserveSum - lastMonthReserveSum);
                    if (lastMonthReserveSum != 0) {
                        bo.setGrowth((currentMonthReserveSum - lastMonthReserveSum) / lastMonthReserveSum);
                    } else {
                        bo.setGrowth(1.00);
                    }
                    boList.add(bo);
                    currentMonthReserveSum = 0.00;
                    lastMonthReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        } else {
            for (String groupTeam : groupTeams) {
                for (MoneyReady m : list) {
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        currentMonthReserveSum += m.getReserve();
                    }
                    if (m.getGroupTeam().equals(groupTeam) && m.getYear().equals(year - 1) && m.getMonth().equals(12)) {
                        lastMonthReserveSum += m.getReserve();
                    }
                }
                if ((currentMonthReserveSum != 0) || (lastMonthReserveSum != 0)) {
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setGroupTeam(groupTeam);
                    bo.setMonth(month);
                    bo.setCurrentMonthReserveSum(currentMonthReserveSum);
                    bo.setLastMonthReserveSum(lastMonthReserveSum);
                    bo.setDifferences(currentMonthReserveSum - lastMonthReserveSum);
                    if (lastMonthReserveSum != 0) {
                        bo.setGrowth((currentMonthReserveSum - lastMonthReserveSum) / lastMonthReserveSum);
                    } else {
                        bo.setGrowth(1.00);
                    }
                    boList.add(bo);
                    currentMonthReserveSum = 0.00;
                    lastMonthReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        }
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllGroupTeams() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getGroupTeam());
        }
        return set;
    }

//    /**
//     * 获取所有年份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> findAllYears() throws SerException {
//        List<MoneyReady> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (MoneyReady m : list) {
//            set.add(m.getYear());
//        }
//        return set;
//    }
//
//    /**
//     * 获取所有月份
//     *
//     * @return class Integer
//     * @throws SerException
//     */
//    private Set<Integer> findAllMonths() throws SerException {
//        List<MoneyReady> list = super.findAll();
//        Set<Integer> set = new HashSet<Integer>();
//        for (MoneyReady m : list) {
//            set.add(m.getMonth());
//        }
//        return set;
//    }
}