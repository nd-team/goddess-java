package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import com.bjike.goddess.housepay.service.PayRecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 已付款记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("payRecordApiImpl")
public class PayRecordApiImpl implements PayRecordAPI {
    @Autowired
    private PayRecordSer payRecordSer;
    @Override
    public Long countPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        return payRecordSer.countPayRecord(payRecordDTO);
    }

    @Override
    public PayRecordBO getOne(String id) throws SerException {
        return payRecordSer.getOne(id);
    }

    @Override
    public List<PayRecordBO> findListPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        return payRecordSer.findListPayRecord(payRecordDTO);
    }
    @Override
    public void removePayRecord(String id) throws SerException {
        payRecordSer.removePayRecord(id);
    }
    @Override
    public List<AreaCollectBO> collectArea(String[] areas) throws SerException {
        return payRecordSer.collectArea(areas);
    }
    @Override
    public List<String> getAreas() throws SerException {
        return payRecordSer.getAreas();
    }
    @Override
    public List<ProjectCollectBO> collectProject(String[] projects) throws SerException {
        return payRecordSer.collectProject(projects);
    }@Override
    public List<String> getProject() throws SerException {
        return payRecordSer.getProject();
    }



}