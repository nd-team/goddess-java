package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.PledgeEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.PledgeEquity;
import com.bjike.goddess.shareholdersmanage.service.PledgeEquitySer;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.PledgeEquityTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 质押股权业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:32 ]
 * @Description: [ 质押股权业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("pledgeEquityApiImpl")
public class PledgeEquityApiImpl implements PledgeEquityAPI {
   @Autowired
   private PledgeEquitySer pledgeEquitySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return pledgeEquitySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return pledgeEquitySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countPledge(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        return pledgeEquitySer.countPledge(pledgeEquityDTO);
    }

    @Override
    public PledgeEquityBO getOne(String id) throws SerException {
        return pledgeEquitySer.getOne(id);
    }

    @Override
    public List<PledgeEquityBO> findList(PledgeEquityDTO pledgeEquityDTO) throws SerException {
        return pledgeEquitySer.findList(pledgeEquityDTO);
    }

    @Override
    public PledgeEquityBO save(PledgeEquityTO pledgeEquityTO) throws SerException {
        return pledgeEquitySer.save(pledgeEquityTO);
    }

    @Override
    public PledgeEquityBO edit(PledgeEquityTO pledgeEquityTO) throws SerException {
        return pledgeEquitySer.edit(pledgeEquityTO);
    }

    @Override
    public void delete(String id) throws SerException {
        pledgeEquitySer.delete(id);
    }
}