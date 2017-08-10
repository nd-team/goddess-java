package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.CallInfoBO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.entity.CallInfo;
import com.bjike.goddess.moneyside.to.CallInfoTO;
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
import java.util.Set;

/**
 * 招投信息列表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CallInfoSerImpl extends ServiceImpl<CallInfo, CallInfoDTO> implements CallInfoSer {
    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;

    @Override
    public Long countCallInfo(CallInfoDTO callInfoDTO) throws SerException {
        Long count = super.count(callInfoDTO);
        return count;
    }

    @Override
    public CallInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CallInfo callInfo = super.findById(id);
        return BeanTransform.copyProperties(callInfo, CallInfoBO.class);
    }

    @Override
    public List<CallInfoBO> findListCallInfo(CallInfoDTO CallInfoDTO) throws SerException {
        List<CallInfo> callInfos = super.findByPage(CallInfoDTO);
        List<CallInfoBO> callInfoBOS = BeanTransform.copyProperties(callInfos, CallInfoBO.class);
        return callInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CallInfoBO insertCallInfo(CallInfoTO callInfoTO) throws SerException {
        try {
            DateUtil.parseDate(callInfoTO.getForecastArriveTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
        CallInfo callInfo = BeanTransform.copyProperties(callInfoTO, CallInfo.class, true);
        callInfo.setCreateTime(LocalDateTime.now());
        //还需筹资总额(筹资总额-已筹资金额)
//        callInfo.setNeedTotalFund(callInfoTO.getTotalFund() - callInfoTO.getHasBeenRaised());
        super.save(callInfo);
        return BeanTransform.copyProperties(callInfo, CallInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CallInfoBO editCallInfo(CallInfoTO callInfoTO) throws SerException {
        if (StringUtils.isBlank(callInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        CallInfo callInfo = super.findById(callInfoTO.getId());
        try {
            DateUtil.parseDate(callInfoTO.getForecastArriveTime());
            DateUtil.parseDate(callInfoTO.getForecastAllotTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
        BeanUtils.copyProperties(callInfoTO, callInfo);
        //还需筹资总额(筹资总额-已筹资金额)
        callInfo.setNeedTotalFund(callInfoTO.getTotalFund() - callInfoTO.getHasBeenRaised());
        callInfo.setModifyTime(LocalDateTime.now());
        super.update(callInfo);
        return BeanTransform.copyProperties(callInfo, CallInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCallInfo(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CallInfoBO applyInvest(CallInfoTO callInfoTO) throws SerException {
        CallInfo callInfo = BeanTransform.copyProperties(callInfoTO, CallInfo.class, true);
        callInfo.setModifyTime(LocalDateTime.now());
        //投资占比（投资总额/筹资总额）
        callInfo.setInvestProportion(callInfoTO.getInvestTotal() / callInfoTO.getTotalFund());
        callInfo.setCreateTime(LocalDateTime.now());
        super.save(callInfo);
        //累计投资额
        CallInfoDTO callInfoDTO = new CallInfoDTO();
        callInfoDTO.getSorts().add("modifyTime=asc");
        callInfoDTO.getSorts().add("investor=asc");
        callInfoDTO.getSorts().add("investObject=asc");
        List<CallInfo> callInfos = super.findByCis(callInfoDTO);

        List<CallInfo> final_callInfos = new ArrayList<>();
        if (callInfos != null && callInfos.size() > 0) {
            CallInfo model = callInfos.get(0);
            String aName = model.getInvestor();
            String bName = model.getInvestObject();
            Double accMoney = 0d;
            for (int i = 0; i < callInfos.size(); i++) {
                CallInfo temp = callInfos.get(i);
                if (temp != null) {
                    String temp_aName = temp.getInvestor();
                    String temp_bName = temp.getInvestObject();
                    if (aName.equals(temp_aName) && bName.equals(temp_bName) && i == 0) {
                        accMoney = temp.getThisInvestMoney() == null ? 0d : temp.getThisInvestMoney();
                    } else if (aName.equals(temp_aName) && bName.equals(temp_bName) && i != 0) {
                        accMoney = accMoney + (temp.getThisInvestMoney() == null ? 0d : temp.getThisInvestMoney());
                    } else if (!aName.equals(temp_aName) && !bName.equals(temp_bName) && i != 0) {
                        accMoney = 0d;
                        aName = temp_aName;
                        bName = temp_bName;
                    } else if (!aName.equals(temp_aName) && !bName.equals(temp_bName) && i == 0) {
                        accMoney = (temp.getThisInvestMoney() == null ? 0d : temp.getThisInvestMoney());
                        aName = temp_aName;
                        bName = temp_bName;
                    }
                    temp.setAccumulativeInvestMoney(accMoney);


                    //累计投资比（累计投资额/投资总额）
                    temp.setAccumulativeInvestProportion(accMoney / temp.getInvestTotal());
                    //提取风险控制保证金（等于本次投资额*提取风险控制保证金率）
                    temp.setExtractRiskControlMargin(temp.getThisInvestMoney() * temp.getExtractRiskRserveRatio());
                }

                final_callInfos.add(temp);
            }
        }

        //累计投资比（累计投资额/投资总额）
//        Double accumulativeInvestProportion = applyInvestTO.getAccumulativeInvestMoney() / applyInvestTO.getInvestTotal();
//        applyInvest.setAccumulativeInvestProportion(accumulativeInvestProportion);
//        提取风险准备金率
        // Double extractRiskRserveRatio = callInfo.getExtractRiskRserveRatio();
        //提取风险控制保证金（等于本次投资额*提取风险控制保证金率）
//        Double extractRiskControlMargin = applyInvestTO.getThisInvestMoney() / extractRiskRserveRatio;
//        applyInvest.setExtractRiskControlMargin(extractRiskControlMargin);
        if (final_callInfos != null && final_callInfos.size() > 0) {
            super.save(final_callInfos);
        }
        return BeanTransform.copyProperties(new CallInfo(), CallInfoBO.class);

    }

    @Override
    public CallInfoBO getInnerProject(String innerProject) throws SerException {
        BaseInfoManageDTO dto = new BaseInfoManageDTO();
        dto.getConditions().add(Restrict.eq("innerProject", innerProject));
        CallInfo callInfo = new CallInfo();
        List<BaseInfoManageBO> list = baseInfoManageAPI.searchSiginManage(dto);
        if (StringUtils.isNotBlank(innerProject)) {
            for (BaseInfoManageBO baseInfoManageBO : list) {
                //callInfo.setInnerProject(baseInfoManageBO.getInnerProject());
                callInfo.setArea(baseInfoManageBO.getArea());
                callInfo.setStartProjectTime(LocalDate.parse(baseInfoManageBO.getStartProjectTime()));
                callInfo.setEndProjectTime(LocalDate.parse(baseInfoManageBO.getEndProjectTime()));
                callInfo.setMoney(baseInfoManageBO.getMoney());
                callInfo.setProjectCharge(baseInfoManageBO.getProjectCharge());
            }
        } else {
            throw new SerException("项目名称不能为空");
        }

        CallInfoBO callInfoBO = BeanTransform.copyProperties(callInfo, CallInfoBO.class);
        return callInfoBO;
    }

    @Override
    public Set<String> getInnerProject() throws SerException {
        Set<String> callInfoBOS = baseInfoManageAPI.allInnerProjects();
        return callInfoBOS;
    }


}