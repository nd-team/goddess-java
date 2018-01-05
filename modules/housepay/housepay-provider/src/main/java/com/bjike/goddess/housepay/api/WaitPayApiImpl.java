package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.*;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.WaitPay;
import com.bjike.goddess.housepay.enums.PayStatus;
import com.bjike.goddess.housepay.excel.SonPermissionObject;
import com.bjike.goddess.housepay.service.WaitPaySer;
import com.bjike.goddess.housepay.to.CollectAreaTO;
import com.bjike.goddess.housepay.to.CollectProjectTO;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.housepay.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitPayApiImpl")
public class WaitPayApiImpl implements WaitPayAPI {
    @Autowired
    private WaitPaySer waitPaySer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return waitPaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return waitPaySer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return waitPaySer.countWaitPay(waitPayDTO);
    }

    @Override
    public WaitPayBO getOne(String id) throws SerException {
        return waitPaySer.getOne(id);
    }

    @Override
    public List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return waitPaySer.findListWaitPay(waitPayDTO);
    }

    @Override
    public WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.insertWaitPay(waitPayTO);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.editWaitPay(waitPayTO);
    }

    @Override
    public void removeWaitPay(String id) throws SerException {
        waitPaySer.removeWaitPay(id);
    }
    @Override
    public void payment(String id) throws SerException {
         waitPaySer.payment(id);
    }
    @Override
    public List<AreaCollectBO> collectArea(CollectAreaTO to) throws SerException {
        return waitPaySer.collectArea(to);
    }
    @Override
    public List<CollectDetailBO> collectAreaDetail(CollectAreaTO to) throws SerException {
        return waitPaySer.collectAreaDetail(to);
    }
    @Override
    public List<String> getAreas() throws SerException {
        return waitPaySer.getAreas();
    }
    @Override
    public List<ProjectCollectBO> collectProject(CollectProjectTO to) throws SerException {
        return waitPaySer.collectProject(to);
    }
    @Override
    public List<CollectDetailBO> collectProjectDatail(CollectProjectTO to) throws SerException {
        return waitPaySer.collectProjectDatail(to);
    }

    @Override
    public List<String> getProject() throws SerException {
        return waitPaySer.getProject();
    }
    @Override
    public Double calculate(WaitPayTO to)throws SerException{
        return waitPaySer.calculate(to);
    }
    @Override
    public List<String> yearList() throws SerException {
        return waitPaySer.yearList();
    }

}