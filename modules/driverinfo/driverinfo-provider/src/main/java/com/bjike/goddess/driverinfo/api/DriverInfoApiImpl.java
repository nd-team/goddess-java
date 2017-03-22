package com.bjike.goddess.driverinfo.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.driverinfo.bo.DriverInfoBO;
import com.bjike.goddess.driverinfo.dto.DriverInfoDTO;
import com.bjike.goddess.driverinfo.service.DriverInfoSer;
import com.bjike.goddess.driverinfo.to.DriverInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [Jason]
 * @Date: [17-3-13 下午3:15]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("driverInfoApiImpl")
public class DriverInfoApiImpl implements DriverInfoAPI {

    @Autowired
    private DriverInfoSer driverInfoSer;

    @Override
    public DriverInfoBO saveDriverInfo(DriverInfoTO to) throws SerException {

        return driverInfoSer.saveDriverInfo(to);
    }

    @Override
    public DriverInfoBO updateDriverInfo(DriverInfoTO to) throws SerException {

        return driverInfoSer.updateDriverInfo(to);
    }

    @Override
    public void updateStatus(String id) throws SerException {
        driverInfoSer.updateStatus(id);
    }

    @Override
    public DriverInfoBO updateAudit(DriverInfoTO to) throws SerException {
        return driverInfoSer.updateAudit(to);
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto, String type) throws SerException {
        return driverInfoSer.pageList(dto,type);
    }

    @Override
    public void remove(String id) throws SerException {
        driverInfoSer.remove(id);
    }
}
