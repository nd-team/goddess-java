package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.bo.ProfitBO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.entity.Profit;
import com.bjike.goddess.reportmanagement.to.ProfitTO;
import com.bjike.goddess.reportmanagement.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 利润表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-22 06:03 ]
 * @Description: [ 利润表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class ProfitSerImpl extends ServiceImpl<Profit, ProfitDTO> implements ProfitSer {
    @Autowired
    private FormulaSer formulaSer;

    @Override
    public ProfitBO save(ProfitTO to) throws SerException {
        Profit entity = BeanTransform.copyProperties(to, Profit.class, true);
        return BeanTransform.copyProperties(entity, ProfitBO.class);
    }

    @Override
    public List<ProfitBO> list(ProfitDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        LocalDate a = Utils.tranTime(endTime);
        String startYear = a.getYear() + "-01" + "-01";
        int month = a.getMonthValue();
        List<Profit> list = super.findByCis(dto);
        List<ProfitBO> boList = new ArrayList<ProfitBO>();
        for (Profit profit : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(profit.getId(), startTime, endTime);
            if ((formulaBOs != null) && (!formulaBOs.isEmpty())) {
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                ProfitBO bo = BeanTransform.copyProperties(profit, ProfitBO.class);
                bo.setCurrentMonthAmount(formulaBO.getCurrent());
                List<FormulaBO> startYearBOs = formulaSer.findByFid(profit.getId(), startYear, endTime);
                if (startYearBOs != null) {
                    bo.setCurrentYearAmount(startYearBOs.get(startYearBOs.size() - 1).getCurrent());
                }
                List<FormulaBO> firstSeason = formulaSer.findByFid(profit.getId(), startYear, a.getYear() + "-03" + "-31");
                List<FormulaBO> secondSeason = formulaSer.findByFid(profit.getId(), a.getYear() + "-04" + "-01", a.getYear() + "-06" + "-30");
                List<FormulaBO> thirdSeason = formulaSer.findByFid(profit.getId(), a.getYear() + "-07" + "-01", a.getYear() + "-09" + "-30");
                List<FormulaBO> fourthSeason = formulaSer.findByFid(profit.getId(), a.getYear() + "-10" + "-01", a.getYear() + "-12" + "-30");
                if (month >= 10 && month <= 12) {
                    if (firstSeason != null) {
                        bo.setFirstSeason(firstSeason.get(firstSeason.size() - 1).getCurrent());
                    }
                    if (secondSeason != null) {
                        bo.setSecondSeason(secondSeason.get(secondSeason.size() - 1).getCurrent());
                    }
                    if (thirdSeason != null) {
                        bo.setThirdSeason(thirdSeason.get(thirdSeason.size() - 1).getCurrent());
                    }
                    if (fourthSeason != null) {
                        bo.setFourthSeason(fourthSeason.get(fourthSeason.size() - 1).getCurrent());
                    }
                } else if (month >= 7 && month <= 9) {
                    if (firstSeason != null) {
                        bo.setFirstSeason(firstSeason.get(firstSeason.size() - 1).getCurrent());
                    }
                    if (secondSeason != null) {
                        bo.setSecondSeason(secondSeason.get(secondSeason.size() - 1).getCurrent());
                    }
                    if (thirdSeason != null) {
                        bo.setThirdSeason(thirdSeason.get(thirdSeason.size() - 1).getCurrent());
                    }
                } else if (month >= 4 && month <= 6) {
                    if (firstSeason != null) {
                        bo.setFirstSeason(firstSeason.get(firstSeason.size() - 1).getCurrent());
                    }
                    if (secondSeason != null) {
                        bo.setSecondSeason(secondSeason.get(secondSeason.size() - 1).getCurrent());
                    }
                } else {
                    if (firstSeason != null) {
                        bo.setFirstSeason(firstSeason.get(firstSeason.size() - 1).getCurrent());
                    }
                }
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public

}