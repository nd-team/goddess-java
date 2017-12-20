package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.reportmanagement.bo.CashAnalyse1BO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseCashBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseManaBO;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.dto.CashFlowProjectDTO;
import com.bjike.goddess.reportmanagement.entity.CashAnalyse;
import com.bjike.goddess.reportmanagement.enums.AnalyseType;
import com.bjike.goddess.reportmanagement.to.CashAnalyse1TO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 现金流量分析业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class CashAnalyseSerImpl extends ServiceImpl<CashAnalyse, CashAnalyseDTO> implements CashAnalyseSer {

    @Autowired
    private CashFlowProjectSer cashFlowProjectSer;

    @Transactional
    @Override
    public List<CashAnalyseBO> managerAnalyse1(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseBO> bos = new ArrayList<>(0);
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }
        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String endTime = dto.getTime();

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 1));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.MANAGEMENT));
        cashAnaDTO.getSorts().add("createTime=asc");
        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            bos = BeanTransform.copyProperties(cashAnalyseList, CashAnalyseBO.class);
            return bos;
        }

        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(1);
        entity1.setProject("商品、提供劳务收到的现金");
        entity1.setMoney(cashFlowProjectSer.findCashByProject(entity1.getProject(), cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.MANAGEMENT);
        entity1.setAnalyse("（将销售商品、提供劳务收到的现金与购进商品、接受劳务付出的现金进行比较。在企业经营正常、购销平衡的情况下，二者比较是有意义的。）比率大，说明企业的销售利润大，销售回款良好，创现能力强。");
        entity1.setAdvice(" ");

        CashAnalyse entity2 = new CashAnalyse();
        entity2.setStartTime(startTime);
        entity2.setEndTime(endTime);
        entity2.setNum(1);
        entity2.setProject("购买商品、接受劳务支付的现金");
        entity2.setMoney(cashFlowProjectSer.findCashByProject(entity2.getProject(), cashFlowProjectDTO).toString());
        entity2.setAnalyseType(AnalyseType.MANAGEMENT);
        entity2.setAnalyse(entity1.getAnalyse());
        entity2.setAdvice(entity1.getAdvice());

        CashAnalyse entity3 = new CashAnalyse();
        entity3.setStartTime(startTime);
        entity3.setEndTime(endTime);
        entity3.setNum(1);
        entity3.setProject("经营活动产生的现金流量净额");
        entity3.setMoney(cashFlowProjectSer.findCashByProject(entity3.getProject(), cashFlowProjectDTO).toString());
        entity3.setRate("100%");
        entity3.setAnalyseType(AnalyseType.MANAGEMENT);
        entity3.setAnalyse(entity1.getAnalyse());
        entity3.setAdvice(entity1.getAdvice());

        entity1.setRate(String.format("%.2f", Double.valueOf(entity1.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");
        entity2.setRate(String.format("%.2f", Double.valueOf(entity2.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");

        super.save(entity1);
        super.save(entity2);
        super.save(entity3);
        CashAnalyseBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseBO.class);
        CashAnalyseBO bo2 = BeanTransform.copyProperties(entity2, CashAnalyseBO.class);
        CashAnalyseBO bo3 = BeanTransform.copyProperties(entity3, CashAnalyseBO.class);

        bos.add(bo1);
        bos.add(bo2);
        bos.add(bo3);
        return bos;
    }

    @Override
    public List<CashAnalyseBO> managerAnalyse2(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseBO> bos = null;
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }

        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String endTime = dto.getTime();
        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 2));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.MANAGEMENT));
        cashAnaDTO.getSorts().add("createTime=asc");
        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            bos = BeanTransform.copyProperties(cashAnalyseList, CashAnalyseBO.class);
            return bos;
        }
        bos = new ArrayList<>(0);

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(2);
        entity1.setProject("销售商品、提供劳务收到的现金");
        entity1.setMoney(cashFlowProjectSer.findCashByProject(entity1.getProject(), cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.MANAGEMENT);
        entity1.setAnalyse("（将销售商品、提供劳务收到的现金与经营活动流入的现金总额比较，可大致说明企业产品销售现款占经营活动流入的现金的比重有多大）比重大，说明企业主营业务突出，营销状况良好。");
        entity1.setAdvice(" ");

        CashAnalyse entity3 = new CashAnalyse();
        entity3.setStartTime(startTime);
        entity3.setEndTime(endTime);
        entity3.setNum(2);
        entity3.setProject("经营活动流入的现金总额");
        entity3.setMoney(cashFlowProjectSer.findCashByProject("一、现金流入小计", cashFlowProjectDTO).toString());
        entity3.setRate("100%");
        entity3.setAnalyseType(AnalyseType.MANAGEMENT);
        entity3.setAnalyse(entity1.getAnalyse());
        entity3.setAdvice(entity1.getAdvice());

        entity1.setRate(String.format("%.2f", Double.valueOf(entity1.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");

        super.save(entity1);
        super.save(entity3);
        CashAnalyseBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseBO.class);
        CashAnalyseBO bo3 = BeanTransform.copyProperties(entity3, CashAnalyseBO.class);

        bos.add(bo1);
        bos.add(bo3);
        return bos;
    }

    @Override
    public List<CashAnalyseManaBO> managerAnalyse3(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseManaBO> bos = new ArrayList<>(0);
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }

        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String startTime1 = (Integer.valueOf(dto.getTime().substring(0, 4)) - 1) + "-01-01";
        String endTime = dto.getTime();
        String endTime1 = (Integer.valueOf(dto.getTime().substring(0, 4)) - 1) + dto.getTime().substring(5, dto.getTime().length() - 1);
        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 3));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.MANAGEMENT));
        cashAnaDTO.getSorts().add("createTime=asc");
        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            CashAnalyseManaBO cashAnalyseManaBO = new CashAnalyseManaBO();
            BeanTransform.copyProperties(cashAnalyseList.get(0), cashAnalyseManaBO);

            CashFlowProjectDTO cashFlowProjectDTO11 = new CashFlowProjectDTO();
            cashFlowProjectDTO11.setStartTime(startTime1);
            cashFlowProjectDTO11.setEndTime(endTime1);
            cashAnalyseManaBO.setMoney1(cashFlowProjectSer.findCashByProject(cashAnalyseManaBO.getProject(), cashFlowProjectDTO11).toString());
            cashAnalyseManaBO.setGrowthAmount(Double.valueOf(cashAnalyseManaBO.getMoney()) - Double.valueOf(cashAnalyseManaBO.getMoney1()));
            cashAnalyseManaBO.setGrowthRate(String.format("%.2f", Double.valueOf(cashAnalyseManaBO.getGrowthAmount()) / Double.valueOf(cashAnalyseManaBO.getMoney1()) * 100) + "%");

            bos.add(cashAnalyseManaBO);
            return bos;
        }

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(3);
        entity1.setProject("经营活动产生的现金流量净额");
        entity1.setMoney(cashFlowProjectSer.findCashByProject(entity1.getProject(), cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.MANAGEMENT);
        entity1.setAnalyse("（将本期经营活动现金净流量与上期比较）增长率越高，说明企业成长性越好。");
        entity1.setAdvice(" ");
//        entity1.setRate();

        super.save(entity1);
        CashAnalyseManaBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseManaBO.class);
        CashFlowProjectDTO dto1 = new CashFlowProjectDTO();
        dto1.setStartTime((Integer.valueOf(startTime.substring(0, 4)) - 1) + "-01-01");
        dto1.setEndTime((Integer.valueOf(endTime.substring(0, 4)) - 1) + endTime.substring(5, endTime.length() - 1));

        bo1.setMoney1(cashFlowProjectSer.findCashByProject(entity1.getProject(), dto1).toString());
        bo1.setGrowthAmount(Double.valueOf(bo1.getMoney()) - Double.valueOf(bo1.getMoney1()));
        bo1.setGrowthRate(String.format("%.2f", Double.valueOf(bo1.getGrowthAmount()) / Double.valueOf(bo1.getMoney1()) * 100) + "%");

        bos.add(bo1);
        return bos;
    }

    @Override
    public CashAnalyse1BO findByid(String id) throws SerException {
        CashAnalyse entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        if (!AnalyseType.CASH.equals(entity.getAnalyseType())) {
            CashAnalyse1BO bo = new CashAnalyse1BO();
            BeanTransform.copyProperties(entity, bo);
            return bo;
        } else {
            CashAnalyse1BO bo = new CashAnalyse1BO();
            BeanTransform.copyProperties(entity, bo);
            bo.setAnalyse(entity.getAnalyse().substring(0, entity.getAnalyse().indexOf("（一）")));
            bo.setAnalyse1(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（一）"), entity.getAnalyse().indexOf("（二）")));
            bo.setAnalyse2(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（二）"), entity.getAnalyse().indexOf("（三）")));
            bo.setAnalyse3(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（三）"), entity.getAnalyse().indexOf("（四）")));
            bo.setAnalyse4(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（四）"), entity.getAnalyse().indexOf("（五）")));
            bo.setAnalyse5(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（五）"), entity.getAnalyse().indexOf("（六）")));
            bo.setAnalyse6(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（六）"), entity.getAnalyse().indexOf("（七）")));
            bo.setAnalyse7(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（七）"), entity.getAnalyse().indexOf("（八）")));
            bo.setAnalyse8(entity.getAnalyse().substring(entity.getAnalyse().indexOf("（八）"), entity.getAnalyse().length() - 1));
            return bo;
        }
    }

    @Override
    public void edit(CashAnalyse1TO to) throws SerException {
        CashAnalyse entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        if (!AnalyseType.CASH.equals(entity.getAnalyseType())) {
            BeanUtils.copyProperties(to, entity, "id");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        } else {
            BeanUtils.copyProperties(to, entity, "id");
            entity.setAnalyse(to.getAnalyse() + to.getAnalyse1() + to.getAnalyse2() + to.getAnalyse3() + to.getAnalyse4() + to.getAnalyse5() + to.getAnalyse6() + to.getAnalyse7() + to.getAnalyse8());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
    }

    @Override
    public List<CashAnalyseBO> investmentAnalyse1(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseBO> bos = null;
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }
        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String endTime = dto.getTime();

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 1));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.INVESTMENT));
        cashAnaDTO.getSorts().add("createTime=asc");

        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            bos = BeanTransform.copyProperties(cashAnalyseList, CashAnalyseBO.class);
            return bos;
        }
        bos = new ArrayList<>(0);

        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(1);
        entity1.setProject("投资活动产生的现金流入量");
        entity1.setMoney(cashFlowProjectSer.findCashByProject("二、现金流入小计", cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.INVESTMENT);
        entity1.setAnalyse("当企业扩大规模或开发新的利润增长点时，需要大量的现金投入，投资活动产生的现金流入量补偿不了流出量，投资活动现金净流量为负数，但如果企业投资有效，将会在未来产生现金净流入用于偿还债务，创造收益，企业不会有偿债困难。因此，分析投资活动现金流量，应结合企业目前的投资项目进行，不能简单地以现金净流入还是净流出来论优劣");
        entity1.setAdvice(" ");

        CashAnalyse entity2 = new CashAnalyse();
        entity2.setStartTime(startTime);
        entity2.setEndTime(endTime);
        entity2.setNum(1);
        entity2.setProject("投资活动产生的现金流出量");
        entity2.setMoney(cashFlowProjectSer.findCashByProject("二、现金流出小计", cashFlowProjectDTO).toString());
        entity2.setAnalyseType(AnalyseType.INVESTMENT);
        entity2.setAnalyse(entity1.getAnalyse());
        entity2.setAdvice(entity1.getAdvice());

        CashAnalyse entity3 = new CashAnalyse();
        entity3.setStartTime(startTime);
        entity3.setEndTime(endTime);
        entity3.setNum(1);
        entity3.setProject("投资活动产生的现金流量净额");
        entity3.setMoney(cashFlowProjectSer.findCashByProject(entity3.getProject(), cashFlowProjectDTO).toString());
        entity3.setRate("100%");
        entity3.setAnalyseType(AnalyseType.INVESTMENT);
        entity3.setAnalyse(entity1.getAnalyse());
        entity3.setAdvice(entity1.getAdvice());

        entity1.setRate(String.format("%.2f", Double.valueOf(entity1.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");
        entity2.setRate(String.format("%.2f", Double.valueOf(entity2.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");

        super.save(entity1);
        super.save(entity2);
        super.save(entity3);
        CashAnalyseBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseBO.class);
        CashAnalyseBO bo2 = BeanTransform.copyProperties(entity2, CashAnalyseBO.class);
        CashAnalyseBO bo3 = BeanTransform.copyProperties(entity3, CashAnalyseBO.class);

        bos.add(bo1);
        bos.add(bo2);
        bos.add(bo3);
        return bos;
    }

    @Override
    public List<CashAnalyseBO> financingAnalyse(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseBO> bos = null;
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }
        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String endTime = dto.getTime();

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 1));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.FINANCING));
        cashAnaDTO.getSorts().add("createTime=asc");

        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            bos = BeanTransform.copyProperties(cashAnalyseList, CashAnalyseBO.class);
            return bos;
        }
        bos = new ArrayList<>(0);

        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(1);
        entity1.setProject("筹资活动产生的现金流入量");
        entity1.setMoney(cashFlowProjectSer.findCashByProject("三、现金流入小计", cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.FINANCING);
        entity1.setAnalyse("一般来说，筹资活动产生的现金净流量越大，企业面临的偿债压力也越大，但如果现金净流入量主要来自于企业吸收的权益性资本，则不仅不会面临偿债压力，资金实力反而增强。因此，在分析时，可将吸收投资所收到的现金与筹资活动现金总流入比较，所占比重大，说明企业资金实力增强，财务风险降低。");
        entity1.setAdvice(" ");


        CashAnalyse entity4 = new CashAnalyse();
        entity4.setStartTime(startTime);
        entity4.setEndTime(endTime);
        entity4.setNum(1);
        entity4.setProject("其中：吸收投资所收到的现金");
        entity4.setMoney(cashFlowProjectSer.findCashByProject("吸收投资所收到的现金", cashFlowProjectDTO).toString());
        entity4.setAnalyseType(AnalyseType.FINANCING);
        entity4.setAnalyse(entity1.getAnalyse());
        entity4.setAdvice(entity1.getAdvice());


        CashAnalyse entity2 = new CashAnalyse();
        entity2.setStartTime(startTime);
        entity2.setEndTime(endTime);
        entity2.setNum(1);
        entity2.setProject("筹资活动产生的现金流出量");
        entity2.setMoney(cashFlowProjectSer.findCashByProject("三、现金流出小计", cashFlowProjectDTO).toString());
        entity2.setAnalyseType(AnalyseType.FINANCING);
        entity2.setAnalyse(entity1.getAnalyse());
        entity2.setAdvice(entity1.getAdvice());

        CashAnalyse entity3 = new CashAnalyse();
        entity3.setStartTime(startTime);
        entity3.setEndTime(endTime);
        entity3.setNum(1);
        entity3.setProject("筹资活动产生的现金流量净额");
        entity3.setMoney(cashFlowProjectSer.findCashByProject(entity3.getProject(), cashFlowProjectDTO).toString());
        entity3.setRate("100%");
        entity3.setAnalyseType(AnalyseType.FINANCING);
        entity3.setAnalyse(entity1.getAnalyse());
        entity3.setAdvice(entity1.getAdvice());

        entity1.setRate(String.format("%.2f", Double.valueOf(entity1.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");
        entity2.setRate(String.format("%.2f", Double.valueOf(entity2.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");
        entity4.setRate(String.format("%.2f", Double.valueOf(entity4.getMoney()) / Double.valueOf(entity3.getMoney()) * 100) + "%");

        super.save(entity1);
        super.save(entity4);
        super.save(entity2);
        super.save(entity3);
        CashAnalyseBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseBO.class);
        CashAnalyseBO bo4 = BeanTransform.copyProperties(entity4, CashAnalyseBO.class);
        CashAnalyseBO bo2 = BeanTransform.copyProperties(entity2, CashAnalyseBO.class);
        CashAnalyseBO bo3 = BeanTransform.copyProperties(entity3, CashAnalyseBO.class);

        bos.add(bo1);
        bos.add(bo4);
        bos.add(bo2);
        bos.add(bo3);
        return bos;
    }

    @Override
    public List<CashAnalyseCashBO> cashAnalyse(CashAnalyseDTO dto) throws SerException {
        List<CashAnalyseCashBO> bos = new ArrayList<>(0);
        if (StringUtils.isBlank(dto.getTime())) {
            dto.setTime(DateUtil.dateToString(LocalDate.now()));
        }
        CashFlowProjectDTO cashFlowProjectDTO = new CashFlowProjectDTO();
        String startTime = dto.getTime().substring(0, 4) + "-01-01";
        String endTime = dto.getTime();

        //查询是否存在
        CashAnalyseDTO cashAnaDTO = new CashAnalyseDTO();
        cashAnaDTO.getConditions().add(Restrict.eq("num", 1));
        cashAnaDTO.getConditions().add(Restrict.eq("startTime", startTime));
        cashAnaDTO.getConditions().add(Restrict.eq("endTime", endTime));
        cashAnaDTO.getConditions().add(Restrict.eq("analyseType", AnalyseType.CASH));
        cashAnaDTO.getSorts().add("createTime=asc");

        List<CashAnalyse> cashAnalyseList = super.findByCis(cashAnaDTO);
        if (null != cashAnalyseList && cashAnalyseList.size() > 0) {
            int size = cashAnalyseList.size();
            for (int i = 0; i < size; i += 2) {
                CashAnalyseCashBO bo = BeanTransform.copyProperties(cashAnalyseList.get(i), CashAnalyseCashBO.class);
                setCashAnalyseCash(bo, cashAnalyseList.get(i), cashAnalyseList.get(i + 1));
                bos.add(bo);
            }
            return bos;
        }

        cashFlowProjectDTO.setStartTime(startTime);
        cashFlowProjectDTO.setEndTime(endTime);

        CashAnalyse entity1 = new CashAnalyse();
        entity1.setStartTime(startTime);
        entity1.setEndTime(endTime);
        entity1.setNum(1);
        entity1.setProject("经营活动现金流入量");
        entity1.setMoney(cashFlowProjectSer.findCashByProject("一、现金流入小计", cashFlowProjectDTO).toString());
        entity1.setAnalyseType(AnalyseType.CASH);
        entity1.setAnalyse("首先，分别计算经营活动现金流入、投资活动现金流入和筹资活动现金流入占现金总流入的比重，了解现金的主要来源。一般来说，经营活动现金流入占现金总流入比重大的企业，经营状况较好，财务风险较低，现金流入结构较为合理。其次，分别计算经营活动现金支出、投资活动现金支出和筹资活动现金支出占现金总流出的比重，它能具体反映企业的现金用于哪些方面。一般来说，经营活动现金支出比重大的企业，其生产经营状况正常，现金支出结构较为合理。（一）当经营活动现金流入量小于流出量，投资活动现金流入量大于流出量，筹资活动现金流入量大于流出量时，说明企业经营活动现金账流入不足，主要靠借贷维持经营；如果投资活动现金流入量净额是依靠收回投资或处置长期资产所得，财务状况较为严峻。（二）经营活动现金流入量小于流出量，投资活动现金流入量小于流出量，筹资活动现金流入量大于流出量时，说明企业界经营活动和投资活动均不能产生足够的现金流入，各项活动完全依赖借债维系，一旦举债困难，财务状况将十分危险。（三）经营活动现金流入量小于流出量，投资活动现金流入量大于流出量，筹资活动现金流入量小于流出量时，说明企业经营活动产生现金流入不足；筹集资金发生了困难，可能主要依靠收回投资或处置长期资产所得维持运营，说明企业财务状况已陷入了困境。（四）经营活动现金流入量小于流出量，投资活动现金流入量小于流出量，筹资活动现金流入量小于流出量时，说明企业三项活动均不能产生现金净流入，说明企业财务状况处于瘫痪状态，面临着破产或被兼并的危险。（五）经营活动现金流入量大于流出量，投资活动现金流入量大于流出量，筹资活动现金流入量大于流出量时，说明企业财务状况良好。但要注意对投资项目的可行性研究，否则增加投资会造成浪费。（六）经营活动现金流入量大于流出量，投资活动现金流入量小于流出量，筹资活动现金流入量小于流出量时，说明企业经营活动和借债都能产生现金净流入，说明财务状况较稳定；扩大投资出现投资活动负向净流入也属正常，但注意适度的投资规模。（七）经营活动现金流入量大于流出量，投资活动现金流入量大于流出量，筹资活动现金流入量小于流出量时，说明企业经营活动和投资活动均产生现金净流入；但筹资活动为现金净流出，说明有大量债务到期需现金偿还；如果净流入量大于净出量，说明财务状况较稳定；否则，财务状况不佳。（八）经营活动现金流入量大于流出量，投资活动现金流入量小于流出量，筹资活动现金流入量小于流出量时，说明主要依靠经营活动的现金流入运营，一旦经营状况陷入危机，财务状况将会恶化。");
        entity1.setAdvice(" ");

        CashAnalyse entity2 = new CashAnalyse();
        entity2.setStartTime(startTime);
        entity2.setEndTime(endTime);
        entity2.setNum(1);
        entity2.setProject("经营活动现金流出量");
        entity2.setMoney(cashFlowProjectSer.findCashByProject("一、现金流出小计", cashFlowProjectDTO).toString());
        entity2.setAnalyseType(AnalyseType.CASH);
        entity2.setAnalyse(entity1.getAnalyse());
        entity2.setAdvice(entity1.getAdvice());


        CashAnalyse entity3 = new CashAnalyse();
        entity3.setStartTime(startTime);
        entity3.setEndTime(endTime);
        entity3.setNum(1);
        entity3.setProject("投资活动现金流入量");
        entity3.setMoney(cashFlowProjectSer.findCashByProject("二、现金流入小计", cashFlowProjectDTO).toString());
        entity3.setAnalyseType(AnalyseType.CASH);
        entity3.setAnalyse(entity1.getAnalyse());
        entity3.setAdvice(entity1.getAdvice());


        CashAnalyse entity4 = new CashAnalyse();
        entity4.setStartTime(startTime);
        entity4.setEndTime(endTime);
        entity4.setNum(1);
        entity4.setProject("投资活动现金流出量");
        entity4.setMoney(cashFlowProjectSer.findCashByProject("二、现金流出小计", cashFlowProjectDTO).toString());
        entity4.setAnalyseType(AnalyseType.CASH);
        entity4.setAnalyse(entity1.getAnalyse());
        entity4.setAdvice(entity1.getAdvice());


        CashAnalyse entity5 = new CashAnalyse();
        entity5.setStartTime(startTime);
        entity5.setEndTime(endTime);
        entity5.setNum(1);
        entity5.setProject("筹资活动现金流入量");
        entity5.setMoney(cashFlowProjectSer.findCashByProject("三、现金流入小计", cashFlowProjectDTO).toString());
        entity5.setAnalyseType(AnalyseType.CASH);
        entity5.setAnalyse(entity1.getAnalyse());
        entity5.setAdvice(entity1.getAdvice());

        CashAnalyse entity6 = new CashAnalyse();
        entity6.setStartTime(startTime);
        entity6.setEndTime(endTime);
        entity6.setNum(1);
        entity6.setProject("筹资活动现金流出量");
        entity6.setMoney(cashFlowProjectSer.findCashByProject("三、现金流出小计", cashFlowProjectDTO).toString());
        entity6.setAnalyseType(AnalyseType.CASH);
        entity6.setAnalyse(entity1.getAnalyse());
        entity6.setAdvice(entity1.getAdvice());

        CashAnalyse entity7 = new CashAnalyse();
        entity7.setStartTime(startTime);
        entity7.setEndTime(endTime);
        entity7.setNum(1);
        entity7.setProject("现金总流入");
        entity7.setMoney(String.valueOf(Double.valueOf(entity1.getMoney()) + Double.valueOf(entity3.getMoney()) + Double.valueOf(entity5.getMoney())));
        entity7.setRate("100%");
        entity7.setAnalyseType(AnalyseType.CASH);
        entity7.setAnalyse(entity1.getAnalyse());
        entity7.setAdvice(entity1.getAdvice());

        CashAnalyse entity8 = new CashAnalyse();
        entity8.setStartTime(startTime);
        entity8.setEndTime(endTime);
        entity8.setNum(1);
        entity8.setProject("现金总流出");
        entity8.setMoney(String.valueOf(Double.valueOf(entity2.getMoney()) + Double.valueOf(entity4.getMoney()) + Double.valueOf(entity6.getMoney())));
        entity8.setRate("100%");
        entity8.setAnalyseType(AnalyseType.CASH);
        entity8.setAnalyse(entity1.getAnalyse());
        entity8.setAdvice(entity1.getAdvice());

        entity1.setRate(String.format("%.2f", Double.valueOf(entity1.getMoney()) / Double.valueOf(entity7.getMoney()) * 100) + "%");
        entity2.setRate(String.format("%.2f", Double.valueOf(entity2.getMoney()) / Double.valueOf(entity8.getMoney()) * 100) + "%");
        entity3.setRate(String.format("%.2f", Double.valueOf(entity3.getMoney()) / Double.valueOf(entity7.getMoney()) * 100) + "%");
        entity4.setRate(String.format("%.2f", Double.valueOf(entity4.getMoney()) / Double.valueOf(entity8.getMoney()) * 100) + "%");
        entity5.setRate(String.format("%.2f", Double.valueOf(entity5.getMoney()) / Double.valueOf(entity7.getMoney()) * 100) + "%");
        entity6.setRate(String.format("%.2f", Double.valueOf(entity6.getMoney()) / Double.valueOf(entity8.getMoney()) * 100) + "%");

        super.save(entity1);
        super.save(entity2);
        super.save(entity3);
        super.save(entity4);
        super.save(entity5);
        super.save(entity6);
        super.save(entity7);
        super.save(entity8);
        CashAnalyseCashBO bo1 = BeanTransform.copyProperties(entity1, CashAnalyseCashBO.class);
        bo1.setProject1(entity2.getProject());
        bo1.setMoney1(entity2.getMoney());
        bo1.setRate1(entity2.getRate());
        bo1.setCashFlow(Double.valueOf(entity1.getMoney()) - Double.valueOf(entity2.getMoney()));
        bo1.setAnalyse(entity1.getAnalyse().substring(0, entity1.getAnalyse().indexOf("（一）")));
        bo1.setAnalyse1(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（一）"), entity1.getAnalyse().indexOf("（二）")));
        bo1.setAnalyse2(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（二）"), entity1.getAnalyse().indexOf("（三）")));
        bo1.setAnalyse3(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（三）"), entity1.getAnalyse().indexOf("（四）")));
        bo1.setAnalyse4(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（四）"), entity1.getAnalyse().indexOf("（五）")));
        bo1.setAnalyse5(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（五）"), entity1.getAnalyse().indexOf("（六）")));
        bo1.setAnalyse6(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（六）"), entity1.getAnalyse().indexOf("（七）")));
        bo1.setAnalyse7(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（七）"), entity1.getAnalyse().indexOf("（八）")));
        bo1.setAnalyse8(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（八）"), entity1.getAnalyse().length() - 1));

        CashAnalyseCashBO bo2 = BeanTransform.copyProperties(entity3, CashAnalyseCashBO.class);
        setCashAnalyseCash(bo2, entity3, entity4);

        CashAnalyseCashBO bo3 = BeanTransform.copyProperties(entity5, CashAnalyseCashBO.class);
        setCashAnalyseCash(bo3, entity5, entity6);

        CashAnalyseCashBO bo4 = BeanTransform.copyProperties(entity7, CashAnalyseCashBO.class);
        setCashAnalyseCash(bo4, entity7, entity8);

        bos.add(bo1);
        bos.add(bo2);
        bos.add(bo3);
        bos.add(bo4);
        return bos;
    }

    private void setCashAnalyseCash(CashAnalyseCashBO bo, CashAnalyse entity1, CashAnalyse entity2) throws SerException {
        bo.setProject1(entity2.getProject());
        bo.setMoney1(entity2.getMoney());
        bo.setRate1(entity2.getRate());
        bo.setCashFlow(Double.valueOf(entity1.getMoney()) - Double.valueOf(entity2.getMoney()));
        bo.setAnalyse(entity1.getAnalyse().substring(0, entity1.getAnalyse().indexOf("（一）")));
        bo.setAnalyse1(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（一）"), entity1.getAnalyse().indexOf("（二）")));
        bo.setAnalyse2(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（二）"), entity1.getAnalyse().indexOf("（三）")));
        bo.setAnalyse3(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（三）"), entity1.getAnalyse().indexOf("（四）")));
        bo.setAnalyse4(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（四）"), entity1.getAnalyse().indexOf("（五）")));
        bo.setAnalyse5(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（五）"), entity1.getAnalyse().indexOf("（六）")));
        bo.setAnalyse6(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（六）"), entity1.getAnalyse().indexOf("（七）")));
        bo.setAnalyse7(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（七）"), entity1.getAnalyse().indexOf("（八）")));
        bo.setAnalyse8(entity1.getAnalyse().substring(entity1.getAnalyse().indexOf("（八）"), entity1.getAnalyse().length() - 1));
    }

}