package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.entity.DeviceJoin;
import com.bjike.goddess.workjoin.service.DeviceJoinSer;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备交接业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("deviceJoinApiImpl")
public class DeviceJoinApiImpl implements DeviceJoinAPI {
    @Autowired
    private DeviceJoinSer deviceJoinSer;
    @Override
    public Long countDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        return deviceJoinSer.countDeviceJoin(deviceJoinDTO);
    }

    @Override
    public DeviceJoinBO getOne(String id) throws SerException {
        return deviceJoinSer.getOne(id);
    }

    @Override
    public List<DeviceJoinBO> findListDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        return deviceJoinSer.findListDeviceJoin(deviceJoinDTO);
    }

    @Override
    public DeviceJoinBO insertDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        return deviceJoinSer.insertDeviceJoin(deviceJoinTO);
    }

    @Override
    public DeviceJoinBO editDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        return deviceJoinSer.editDeviceJoin(deviceJoinTO);
    }

    @Override
    public void removeDeviceJoin(String id) throws SerException {
        deviceJoinSer.removeDeviceJoin(id);

    }

}