package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityCoalescBO;
import com.bjike.goddess.shareholdersmanage.bo.ProportioAnmountBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityCoalescDTO;
import com.bjike.goddess.shareholdersmanage.service.EquityCoalescSer;
import com.bjike.goddess.shareholdersmanage.to.EquityCoalescTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 股权合并业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:24 ]
 * @Description: [ 股权合并业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityCoalescApiImpl")
public class EquityCoalescApiImpl implements EquityCoalescAPI {
    @Autowired
    private EquityCoalescSer equityCoalescSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return equityCoalescSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return equityCoalescSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCoalesc(EquityCoalescDTO equityCoalescDTO) throws SerException {
        return equityCoalescSer.countCoalesc(equityCoalescDTO);
    }

    @Override
    public EquityCoalescBO getOne(String id) throws SerException {
        return equityCoalescSer.getOne(id);
    }

    @Override
    public List<EquityCoalescBO> findList(EquityCoalescDTO equityCoalescDTO) throws SerException {
        return equityCoalescSer.findList(equityCoalescDTO);
    }

    @Override
    public EquityCoalescBO save(EquityCoalescTO equityCoalescTO) throws SerException {
        return equityCoalescSer.save(equityCoalescTO);
    }

    @Override
    public EquityCoalescBO edit(EquityCoalescTO equityCoalescTO) throws SerException {
        return equityCoalescSer.edit(equityCoalescTO);
    }

    @Override
    public void delete(String id) throws SerException {
        equityCoalescSer.delete(id);
    }

    @Override
    public ProportioAnmountBO proAnmount(String beCombined) throws SerException {
        return equityCoalescSer.proAnmount(beCombined);
    }
}