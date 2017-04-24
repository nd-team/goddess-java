package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.DeviceTypeBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.entity.DeviceType;
import com.bjike.goddess.materialbuy.service.DeviceTypeSer;
import com.bjike.goddess.materialbuy.to.DeviceTypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备类型业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("deviceTypeApiImpl")
public class DeviceTypeApiImpl implements DeviceTypeAPI {

    @Autowired
    private DeviceTypeSer deviceTypeSer;

    /**
     * 根据id查询设备类型
     *
     * @param id 设备类型唯一标识
     * @return class DeviceTypeBO
     * @throws SerException
     */
    @Override
    public DeviceTypeBO findById(String id) throws SerException {
        DeviceType model = deviceTypeSer.findById(id);
        return BeanTransform.copyProperties(model, DeviceTypeBO.class);
    }

    /**
     * 分页查询设备类型
     *
     * @return class DeviceTypeBO
     * @throws SerException
     */
    @Override
    public List<DeviceTypeBO> list(DeviceTypeDTO dto) throws SerException {
        return deviceTypeSer.list(dto);
    }

    /**
     * 保存设备类型
     *
     * @param to 设备类型to
     * @return class DeviceTypeBO
     * @throws SerException
     */
    @Override
    public DeviceTypeBO save(DeviceTypeTO to) throws SerException {
        return deviceTypeSer.save(to);
    }

    /**
     * 根据id删除设备类型
     *
     * @param id 设备类型唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        deviceTypeSer.remove(id);
    }

    /**
     * 更新设备类型
     *
     * @param to 设备类型to
     * @throws SerException
     */
    @Override
    public void update(DeviceTypeTO to) throws SerException {
        deviceTypeSer.update(to);
    }
}