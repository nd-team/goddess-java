package com.bjike.goddess.rentcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.excel.SonPermissionObject;
import com.bjike.goddess.rentcar.service.DriverInfoSer;
import com.bjike.goddess.rentcar.to.DriverInfoTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 租车协议管理业务接口实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:47 ]
 * @Description: [ 租车协议管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("driverInfoApiImpl1")
public class DriverInfoApiImpl implements DriverInfoAPI {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return driverInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return driverInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public DriverInfoBO save(DriverInfoTO to) throws SerException {
        return driverInfoSer.insertModel(to);
    }

    @Override
    public DriverInfoBO edit(DriverInfoTO to) throws SerException {
        return driverInfoSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        driverInfoSer.remove(id);
    }

    @Override
    public Long count(DriverInfoDTO dto) throws SerException {
        return driverInfoSer.count(dto);
    }

    @Override
    public DriverInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(driverInfoSer.findById(id), DriverInfoBO.class);
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException {
        return driverInfoSer.pageList(dto);
    }

    @Override
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        driverInfoSer.audit(id,suggest,audit);
    }

    @Override
    public void recissionAgreement(String id,Boolean breakAgreement, String liftTime) throws SerException {
        driverInfoSer.recissionAgreement(id,breakAgreement,liftTime);
    }

    @Override
    public DriverInfoBO findByName(String driverName) throws SerException {
        return driverInfoSer.findByName(driverName);
    }
}