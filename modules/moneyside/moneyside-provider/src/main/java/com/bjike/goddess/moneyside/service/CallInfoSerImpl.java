package com.bjike.goddess.moneyside.service;

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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 核对日期格式(年月日)
     */
    private void checkDate(CallInfoTO callInfoTO)throws SerException{
        try {
            DateUtil.parseDate(callInfoTO.getForecastArriveTime());
            DateUtil.parseDate(callInfoTO.getForecastAllotTime());
        }catch (Exception e){
            throw new SerException("输入的日期格式有误");
        }
    }
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
        //todo: 项目合同基本信息管理
        List<CallInfo> callInfos = super.findByPage(CallInfoDTO);
        List<CallInfoBO> callInfoBOS = BeanTransform.copyProperties(callInfos,CallInfoBO.class);
        return callInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CallInfoBO insertCallInfo(CallInfoTO callInfoTO) throws SerException {
        checkDate(callInfoTO);
        CallInfo callInfo = BeanTransform.copyProperties(callInfoTO, CallInfo.class, true);
        callInfo.setCreateTime(LocalDateTime.now());
        //还需筹资总额(筹资总额-已筹资金额)
        callInfo.setNeedTotalFund(callInfoTO.getTotalFund()-callInfoTO.getHasBeenRaised());
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
        checkDate(callInfoTO);
        BeanUtils.copyProperties(callInfoTO, callInfo);
        //还需筹资总额(筹资总额-已筹资金额)
        callInfo.setNeedTotalFund(callInfoTO.getTotalFund()-callInfoTO.getHasBeenRaised());
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
}