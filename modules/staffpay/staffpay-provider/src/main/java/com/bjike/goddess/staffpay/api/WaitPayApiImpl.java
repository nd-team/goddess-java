package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.entity.WaitPay;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.excel.SonPermissionObject;
import com.bjike.goddess.staffpay.service.WaitPaySer;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
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
    @Override
    public WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.editWaitPay(waitPayTO);
    }

    @Override
    public void removeWaitPay(String id) throws SerException {
        waitPaySer.removeWaitPay(id);

    }
    @Override
    public FirstPayRecordBO payment(WaitPayTO waitPayTO) throws SerException {
        return waitPaySer.payment(waitPayTO);
    }
}