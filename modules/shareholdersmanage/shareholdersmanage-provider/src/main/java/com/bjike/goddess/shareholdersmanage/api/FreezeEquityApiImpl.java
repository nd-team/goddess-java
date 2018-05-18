package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.FreezeEquity;
import com.bjike.goddess.shareholdersmanage.service.FreezeEquitySer;
import com.bjike.goddess.shareholdersmanage.to.FreezeEquityTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 冻结股权业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("freezeEquityApiImpl")
public class FreezeEquityApiImpl implements FreezeEquityAPI {
    @Autowired
    private FreezeEquitySer freezeEquitySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return freezeEquitySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return freezeEquitySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countFreeze(FreezeEquityDTO freezeEquityDTO) throws SerException {
        return freezeEquitySer.count(freezeEquityDTO);
    }

    @Override
    public FreezeEquityBO getOne(String id) throws SerException {
        return freezeEquitySer.getOne(id);
    }

    @Override
    public List<FreezeEquityBO> findList(FreezeEquityDTO freezeEquityDTO) throws SerException {
        return freezeEquitySer.findList(freezeEquityDTO);
    }

    @Override
    public FreezeEquityBO save(FreezeEquityTO freezeEquityTO) throws SerException {
        return freezeEquitySer.save(freezeEquityTO);
    }

    @Override
    public FreezeEquityBO edit(FreezeEquityTO freezeEquityTO) throws SerException {
        return freezeEquitySer.edit(freezeEquityTO);
    }

    @Override
    public void delete(String id) throws SerException {
        freezeEquitySer.delete(id);
    }

    @Override
    public FreezeEquityLinkDateBO totalNum(String equityType) throws SerException {
        return freezeEquitySer.totalNum(equityType);
    }
}