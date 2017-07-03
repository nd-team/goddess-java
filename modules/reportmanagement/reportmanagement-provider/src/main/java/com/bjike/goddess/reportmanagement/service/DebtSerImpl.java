package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.bo.DebtBO;
import com.bjike.goddess.reportmanagement.bo.FormulaBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.DebtDTO;
import com.bjike.goddess.reportmanagement.entity.Debt;
import com.bjike.goddess.reportmanagement.enums.DebtType;
import com.bjike.goddess.reportmanagement.to.DebtTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 负债表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:21 ]
 * @Description: [ 负债表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class DebtSerImpl extends ServiceImpl<Debt, DebtDTO> implements DebtSer {
    @Autowired
    private FormulaSer formulaSer;

    @Override
    public DebtBO save(DebtTO to) throws SerException {
        Debt entity = BeanTransform.copyProperties(to, Debt.class, true);
        return BeanTransform.copyProperties(entity, DebtBO.class);
    }

    @Override
    public DebtBO find(String id, String startTime, String endTime) throws SerException {
        Debt entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        DebtBO bo = BeanTransform.copyProperties(entity, DebtBO.class);
        bo.setStartTime(startTime);
        bo.setEndTime(endTime);
        return bo;
    }

    @Override
    public List<DebtBO> list(DebtDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        dto.getSorts().add("debtType=ASC");
        List<Debt> list = super.findByCis(dto);
        List<DebtBO> boList = new ArrayList<DebtBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        boolean b4 = true;
        double beginSum = 0;
        double endSum = 0;
        double debtBegin = 0;
        double debtEnd = 0;    //负债合计
        double countBegin = 0;
        double countEnd = 0;       //负债和所有权益合计
        for (Debt debt : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(debt.getId(), startTime, endTime);
            if (formulaBOs != null) {
                if (DebtType.AFLOW.equals(debt.getDebtType()) && b1) {
                    DebtBO debtBO = new DebtBO();
                    debtBO.setDebt("流动负债：");
                    boList.add(debtBO);
                    b1 = false;
                } else if (DebtType.BLONG.equals(debt.getDebtType()) && b2) {
                    DebtBO sumBO = new DebtBO();
                    sumBO.setDebt("流动负债合计");
                    sumBO.setBeginDebt(beginSum);
                    sumBO.setEndDebt(endSum);
                    boList.add(sumBO);
                    debtBegin += beginSum;
                    debtEnd += endSum;
                    beginSum = 0;
                    endSum = 0;    //置为0
                    DebtBO debtBO = new DebtBO();
                    debtBO.setDebt("长期负债：");
                    boList.add(debtBO);
                    b2 = false;
                } else if (DebtType.CTAX.equals(debt.getDebtType()) && b3) {
                    DebtBO sumBO = new DebtBO();
                    sumBO.setDebt("长期负债合计");
                    sumBO.setBeginDebt(beginSum);
                    sumBO.setEndDebt(endSum);
                    boList.add(sumBO);
                    debtBegin += beginSum;
                    debtEnd += endSum;
                    beginSum = 0;
                    endSum = 0;    //置为0
                    DebtBO debtBO = new DebtBO();
                    debtBO.setDebt("递延税项：");
                    boList.add(debtBO);
                    b3 = false;
                } else if (DebtType.DALL.equals(debt.getDebtType()) && b4) {
                    DebtBO sumBO = new DebtBO();
                    sumBO.setDebt("负债合计");
                    debtBegin += beginSum;
                    debtEnd += endSum;
                    sumBO.setBeginDebt(debtBegin);
                    sumBO.setEndDebt(debtEnd);
                    boList.add(sumBO);
                    beginSum = 0;
                    endSum = 0;    //置为0
                    DebtBO debtBO = new DebtBO();
                    debtBO.setDebt("所有者权益(或股东权益)：");
                    boList.add(debtBO);
                    b4 = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                DebtBO bo = BeanTransform.copyProperties(debt, DebtBO.class);
                bo.setBeginDebt(formulaBO.getBegin());
                bo.setEndDebt(formulaBO.getEnd());
                beginSum += bo.getBeginDebt();
                endSum += bo.getEndDebt();
                countBegin += bo.getBeginDebt();
                countEnd += bo.getEndDebt();
                boList.add(bo);
            }
        }
        DebtBO lastTwo = new DebtBO();
        lastTwo.setDebt("所有者权益(或股东权益)合计");
        lastTwo.setBeginDebt(beginSum);
        lastTwo.setEndDebt(endSum);
        boList.add(lastTwo);
        DebtBO lastBO = new DebtBO();
        lastBO.setDebt("负债和所有者权益(或股东权益)总计");
        lastBO.setBeginDebt(countBegin);
        lastBO.setEndDebt(countEnd);
        boList.add(lastBO);
        return boList;
    }

    @Override
    public List<StructureBO> debtStructure(DebtDTO dto) throws SerException {
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        dto.getSorts().add("debtType=ASC");
        List<Debt> list = super.findByCis(dto);
        List<StructureBO> boList = new ArrayList<StructureBO>();
        boolean b1 = true;
        boolean b2 = true;
        boolean b3 = true;
        double flowSum = 0;
        double longSum = 0;
        double allSum = 0;
        double endSum = 0;
        double countEnd = 0;       //负债和所有权益合计
        for (Debt debt : list) {
            List<FormulaBO> formulaBOs = formulaSer.findByFid(debt.getId(), startTime, endTime);
            if (formulaBOs != null) {
                if (DebtType.BLONG.equals(debt.getDebtType()) && b1) {
                    flowSum = endSum;
                    endSum = 0;    //置为0
                    b1 = false;
                } else if (DebtType.CTAX.equals(debt.getDebtType()) && b2) {
                    longSum = endSum;
                    endSum = 0;    //置为0
                    b2 = false;
                } else if (DebtType.DALL.equals(debt.getDebtType()) && b3) {
                    endSum = 0;    //置为0
                    b3 = false;
                }
                FormulaBO formulaBO = formulaBOs.get(formulaBOs.size() - 1);
                DebtBO bo = BeanTransform.copyProperties(debt, DebtBO.class);
                bo.setEndDebt(formulaBO.getEnd());
                endSum += bo.getEndDebt();
                countEnd += bo.getEndDebt();
            }
        }
        allSum = endSum;
        StructureBO flowBO = new StructureBO();
        flowBO.setProject("流动负债合计");
        flowBO.setFee(flowSum);
        flowBO.setScale(String.format("%.2f",(flowSum / countEnd) * 100) + "%");
        boList.add(flowBO);
        StructureBO longBO = new StructureBO();
        longBO.setProject("长期负债合计");
        longBO.setFee(longSum);
        longBO.setScale(String.format("%.2f",(longSum / countEnd) * 100) + "%");
        boList.add(longBO);
        StructureBO allBO = new StructureBO();
        allBO.setProject("所有者权益合计");
        allBO.setFee(longSum);
        allBO.setScale(String.format("%.2f",(allSum / countEnd) * 100) + "%");
        boList.add(allBO);
        StructureBO sumBO = new StructureBO();
        sumBO.setProject("负债与所有者权益总计");
        sumBO.setFee(countEnd);
        allBO.setScale("100%");
        boList.add(allBO);
        return boList;
    }
}