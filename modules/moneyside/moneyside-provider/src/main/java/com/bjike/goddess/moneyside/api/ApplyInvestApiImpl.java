package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.bo.CollectApplyInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.entity.ApplyInvest;
import com.bjike.goddess.moneyside.service.ApplyInvestSer;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CollectApplyInvestTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 申请投资业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("applyInvestApiImpl")
public class ApplyInvestApiImpl implements ApplyInvestAPI {
    @Autowired
    private ApplyInvestSer applyInvestSer;
    @Override
    public Long countApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        return applyInvestSer.countApplyInvest(applyInvestDTO);
    }


    @Override
    public ApplyInvestBO getOne(String id) throws SerException {
        return applyInvestSer.getOne(id);
    }

    @Override
    public List<ApplyInvestBO> findListApplyInvest(ApplyInvestDTO applyInvestDTO) throws SerException {
        return applyInvestSer.findListApplyInvest(applyInvestDTO);
    }

    @Override
    public ApplyInvestBO insertApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        return applyInvestSer.insertApplyInvest(applyInvestTO);
    }

    @Override
    public ApplyInvestBO editApplyInvest(ApplyInvestTO applyInvestTO) throws SerException {
        return applyInvestSer.editApplyInvest(applyInvestTO);
    }

    @Override
    public void removeApplyInvest(String id) throws SerException {
        applyInvestSer.removeApplyInvest(id);
    }
    @Override
    public List<CollectApplyInvestBO> collectApplyInvest(CollectApplyInvestTO to) throws SerException{
        return applyInvestSer.collectApplyInvest(to);
    }
}