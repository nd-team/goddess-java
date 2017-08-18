package com.bjike.goddess.reimbursementprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reimbursementprepare.bo.DifferencesBO;
import com.bjike.goddess.reimbursementprepare.bo.WaitPayBO;
import com.bjike.goddess.reimbursementprepare.dto.WaitPayDTO;
import com.bjike.goddess.reimbursementprepare.service.WaitPaySer;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitPayApiImpl")
public class WaitPayApiImpl implements WaitPayAPI {
    @Autowired
    private WaitPaySer waitPaySer;

    @Override
    public List<WaitPayBO> waitPays(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitPays(dto);
    }

    @Override
    public void pay(WaitPayTO to) throws SerException {
        waitPaySer.pay(to);
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return waitPaySer.pays(dto);
    }

    @Override
    public byte[] waitPayExport(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitPayExport(dto);
    }

    @Override
    public byte[] payExport(WaitPayDTO dto) throws SerException {
        return waitPaySer.payExport(dto);
    }

    @Override
    public List<WaitPayBO> conditionsCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.conditionsCount(dto);
    }

    @Override
    public List<DifferencesBO> difference(WaitPayDTO dto) throws SerException {
        return waitPaySer.difference(dto);
    }

    @Override
    public Set<String> allProjectGroups() throws SerException {
        return waitPaySer.allProjectGroups();
    }

    @Override
    public Set<String> allAreas() throws SerException {
        return waitPaySer.allAreas();
    }

    @Override
    public Long payCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.payCount(dto);
    }

    @Override
    public Long waitPayCount(WaitPayDTO dto) throws SerException {
        return waitPaySer.waitPayCount(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return waitPaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return waitPaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public WaitPayBO findWait(String id) throws SerException {
        return waitPaySer.findWait(id);
    }
}