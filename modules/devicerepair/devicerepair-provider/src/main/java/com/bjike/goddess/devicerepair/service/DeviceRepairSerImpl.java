package com.bjike.goddess.devicerepair.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.entity.DeviceRepair;
import com.bjike.goddess.devicerepair.to.DeviceRepairTO;
import com.bjike.goddess.devicerepair.to.FetchDeviceTO;
import com.bjike.goddess.devicerepair.to.WelfareAuditTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 设备维修业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [ 设备维修业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "devicerepairSerCache")
@Service
public class DeviceRepairSerImpl extends ServiceImpl<DeviceRepair, DeviceRepairDTO> implements DeviceRepairSer {

    @Autowired
    private UserAPI userAPI;

    /**
     * 分页查询设备维修
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<DeviceRepairBO> list(DeviceRepairDTO dto) throws SerException {
        List<DeviceRepair> list = super.findByPage(dto);
        List<DeviceRepairBO> boList = BeanTransform.copyProperties(list, DeviceRepairBO.class);
        return boList;
    }

    /**
     * 保存设备维修
     *
     * @param to 设备维修to
     * @return class DeviceRepairBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public DeviceRepairBO save(DeviceRepairTO to) throws SerException {
        DeviceRepair entity = BeanTransform.copyProperties(to, DeviceRepair.class, true);
        entity = super.save(entity);
        DeviceRepairBO bo = BeanTransform.copyProperties(entity, DeviceRepairBO.class);
        return bo;
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新设备维修
     *
     * @param to 设备维修to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(DeviceRepairTO to) throws SerException {

    }

    /**
     * 福利模块审核
     *
     * @param to 福利模块审核to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void welfareAudit(WelfareAuditTO to) throws SerException {

    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void deviceScrap(String id, String deviceIssue) throws SerException {

    }

    /**
     * 取回设备
     *
     * @param to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void fetchDevice(FetchDeviceTO to) throws SerException {

    }

}