package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.AreaCollectBO;
import com.bjike.goddess.staffpay.bo.DepartmentCollectBO;
import com.bjike.goddess.staffpay.bo.NameCollectBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.PayRecordDTO;
import com.bjike.goddess.staffpay.entity.PayRecord;
import com.bjike.goddess.staffpay.service.PayRecordSer;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 已付款记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:00 ]
 * @Description: [ 已付款记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("payRecordApiImpl")
public class PayRecordApiImpl implements PayRecordAPI {
    @Autowired
    private PayRecordSer payRecordSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return payRecordSer.sonPermission();
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return payRecordSer.guidePermission(guidePermissionTO);
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
    public List<DepartmentCollectBO> collectDepartment(String[] departments) throws SerException {
        return payRecordSer.collectDepartment(departments);
    }
    @Override
    public List<String> getDepartments() throws SerException {
        return payRecordSer.getDepartments();
    }
    @Override
    public List<NameCollectBO> collectName(String[] names) throws SerException {
        return payRecordSer.collectName(names);
    }

    @Override
    public List<String> getNames() throws SerException {
        return payRecordSer.getNames();
    }
}