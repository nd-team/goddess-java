package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.CapitalInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.dto.CapitalInvestDTO;
import com.bjike.goddess.moneyside.entity.ApplyInvest;
import com.bjike.goddess.moneyside.entity.CallInfo;
import com.bjike.goddess.moneyside.entity.CapitalInvest;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CapitalInvestTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资金投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CapitalInvestSerImpl extends ServiceImpl<CapitalInvest, CapitalInvestDTO> implements CapitalInvestSer {
    @Autowired
    private CallInfoSer callInfoSer;
    @Autowired
    private ApplyInvestSer applyInvestSer;

    private void checkDate(CapitalInvestTO capitalInvestTO)throws SerException {
        try {
            DateUtil.parseDate(capitalInvestTO.getArriveTime());
        }catch (Exception e){
            throw new SerException("输入的日期格式有误");
        }
    }
    @Override
    public Long countCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        Long count = super.count(capitalInvestDTO);
        return count;
    }

    @Override
    public CapitalInvestBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CapitalInvest capitalInvest = super.findById(id);
        return BeanTransform.copyProperties(capitalInvest, CapitalInvestBO.class);
    }

    @Override
    public List<CapitalInvestBO> findListCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
//        List<CallInfoBO> callinfos = callInfoSer.findListCallInfo(null);
//        for(CallInfoBO c: callinfos){
//            BeanTransform.copyProperties(c,capitalInvestBOS);
//        }
        List<CapitalInvest> capitalInvests = super.findByPage(capitalInvestDTO);
        List<CapitalInvestBO> capitalInvestBOS = BeanTransform.copyProperties(capitalInvests, CapitalInvestBO.class);
        return capitalInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapitalInvestBO insertCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        checkDate(capitalInvestTO);
        CapitalInvest capitalInvest = BeanTransform.copyProperties(capitalInvestTO,CapitalInvest.class,true);

        CallInfoDTO dto = new CallInfoDTO();
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        //筹资总额
        Double totalFund = 0.0;
        for(CallInfo callInfo : callInfos){
            totalFund = callInfo.getTotalFund();
        }
        ApplyInvestDTO applyInvestDTO = new ApplyInvestDTO();
        List<ApplyInvest> applyInvests = applyInvestSer.findByCis(applyInvestDTO);
        //项目风控总金额(提取风险控制保证金)
        Double extractRiskRserveRatio = 0.0;
        for(ApplyInvest applyInvest : applyInvests){
            extractRiskRserveRatio = applyInvest.getExtractRiskControlMargin();
        }
        //投资占比（本次投资额/筹资总额）
        Double investProportion = capitalInvestTO.getThisInvestMoney()/totalFund;
        capitalInvest.setInvestProportion(investProportion);
        //风险控制准备金（投资占比*项目风控总金额）
        Double riskControlReserves = investProportion*extractRiskRserveRatio;
        capitalInvest.setRiskControlReserves(riskControlReserves);
        //预估分配额（投资占比*预估到账金额）
        Double allocationForecast = investProportion*capitalInvestTO.getForecastArriveMoney();
        capitalInvest.setAllocationForecast(allocationForecast);
        capitalInvest.setCreateTime(LocalDateTime.now());
        super.save(capitalInvest);
        return BeanTransform.copyProperties(capitalInvest,CapitalInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CapitalInvestBO editCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        if(StringUtils.isBlank(capitalInvestTO.getId())){
            throw new SerException("id不能为空");
        }
        CapitalInvest capitalInvest = super.findById(capitalInvestTO.getId());
        checkDate(capitalInvestTO);
        BeanUtils.copyProperties(capitalInvestTO,capitalInvest);
        CallInfoDTO dto = new CallInfoDTO();
        List<CallInfo> callInfos = callInfoSer.findByCis(dto);
        //筹资总额
        Double totalFund = 0.0;
        for(CallInfo callInfo : callInfos){
            totalFund = callInfo.getTotalFund();
        }
        ApplyInvestDTO applyInvestDTO = new ApplyInvestDTO();
        List<ApplyInvest> applyInvests = applyInvestSer.findByCis(applyInvestDTO);
        //项目风控总金额(提取风险控制保证金)
        Double extractRiskRserveRatio = 0.0;
        for(ApplyInvest applyInvest : applyInvests){
            extractRiskRserveRatio = applyInvest.getExtractRiskControlMargin();
        }
        //投资占比（本次投资额/筹资总额）
        Double investProportion = capitalInvestTO.getThisInvestMoney()/totalFund;
        capitalInvest.setInvestProportion(investProportion);
        //风险控制准备金（投资占比*项目风控总金额）
        Double riskControlReserves = investProportion*extractRiskRserveRatio;
        capitalInvest.setRiskControlReserves(riskControlReserves);
        //预估分配额（投资占比*预估到账金额）
        Double allocationForecast = investProportion*capitalInvestTO.getForecastArriveMoney();
        capitalInvest.setAllocationForecast(allocationForecast);
        capitalInvest.setModifyTime(LocalDateTime.now());
        super.update(capitalInvest);
        return BeanTransform.copyProperties(capitalInvest,CapitalInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCapitalInvest(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}