package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.entity.DeviceJoin;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class DeviceJoinSerImpl extends ServiceImpl<DeviceJoin, DeviceJoinDTO> implements DeviceJoinSer {

    @Override
    public Long countDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        Long count = super.count(deviceJoinDTO);
        return count;
    }

    @Override
    public DeviceJoinBO getOne(String id) throws SerException {
        DeviceJoin deviceJoin = super.findById(id);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }

    @Override
    public List<DeviceJoinBO> findListDeviceJoin(DeviceJoinDTO deviceJoinDTO) throws SerException {
        List<DeviceJoin> deviceJoins = super.findByPage(deviceJoinDTO);
        List<DeviceJoinBO> deviceJoinBOS = BeanTransform.copyProperties(deviceJoins,DeviceJoinBO.class);
        return deviceJoinBOS;
    }

    @Override
    public DeviceJoinBO insertDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        DeviceJoin deviceJoin = BeanTransform.copyProperties(deviceJoinTO,DeviceJoin.class,true);
        deviceJoin.setCreateTime(LocalDateTime.now());
        super.save(deviceJoin);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }

    @Override
    public DeviceJoinBO editDeviceJoin(DeviceJoinTO deviceJoinTO) throws SerException {
        DeviceJoin deviceJoin = super.findById(deviceJoinTO.getId());
        BeanTransform.copyProperties(deviceJoinTO,deviceJoin,true);
        deviceJoin.setModifyTime(LocalDateTime.now());
        super.update(deviceJoin);
        return BeanTransform.copyProperties(deviceJoin,DeviceJoinBO.class);
    }

    @Override
    public void removeDeviceJoin(String id) throws SerException {
        super.remove(id);

    }

    @Override
    public void upload() throws SerException {
        //TODO 未做上传
        return;

    }

    @Override
    public void download() throws SerException {
        //todo 未做下载
        return;

    }

}