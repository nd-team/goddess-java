package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.BeginBalanceBO;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.entity.BeginBalance;
import com.bjike.goddess.fundcheck.service.BeginBalanceSer;
import com.bjike.goddess.fundcheck.to.BeginBalanceTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 期初余额业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("beginBalanceApiImpl")
public class BeginBalanceApiImpl implements BeginBalanceAPI {
    @Autowired
    private BeginBalanceSer beginBalanceSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return beginBalanceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return beginBalanceSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(BeginBalanceDTO beginBalanceDTO) throws SerException {
        return beginBalanceSer.count(beginBalanceDTO);
    }

    @Override
    public BeginBalanceBO getOne(String id) throws SerException {
        return beginBalanceSer.getOne(id);
    }

    @Override
    public List<BeginBalanceBO> findList(BeginBalanceDTO beginBalanceDTO) throws SerException {
        return beginBalanceSer.findList(beginBalanceDTO);
    }

    @Override
    public BeginBalanceBO insert(BeginBalanceTO beginBalanceTO) throws SerException {
        return beginBalanceSer.insert(beginBalanceTO);
    }

    @Override
    public BeginBalanceBO edit(BeginBalanceTO beginBalanceTO) throws SerException {
        return beginBalanceSer.edit(beginBalanceTO);
    }

    @Override
    public void remove(String id) throws SerException {
        beginBalanceSer.remove(id);
    }
    @Override
    public List<BeginBalanceBO> getBeginBalace(String startTime,String endTime) throws SerException {
        return beginBalanceSer.getBeginBalace(startTime, endTime);
    }
}