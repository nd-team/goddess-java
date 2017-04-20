package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.bo.DeviceTypeBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.entity.DeviceType;
import com.bjike.goddess.materialbuy.to.DeviceTypeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备类型业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class DeviceTypeSerImpl extends ServiceImpl<DeviceType, DeviceTypeDTO> implements DeviceTypeSer {

    /**
     * 分页查询设备类型
     *
     * @return class DeviceTypeBO
     * @throws SerException
     */
    @Override
    public List<DeviceTypeBO> list(DeviceTypeDTO dto) throws SerException {
        List<DeviceType> list = super.findByPage(dto);
        List<DeviceTypeBO> listBO = BeanTransform.copyProperties(list, DeviceTypeBO.class);
        return listBO;
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
        DeviceType entity = BeanTransform.copyProperties(to, DeviceType.class, true);
        entity = super.save(entity);
        DeviceTypeBO bo = BeanTransform.copyProperties(entity, DeviceTypeBO.class);
        return bo;
    }

    /**
     * 根据id删除设备类型
     *
     * @param id 设备类型唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新设备类型
     *
     * @param to 设备类型to
     * @throws SerException
     */
    @Override
    public void update(DeviceTypeTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            DeviceType model = super.findById(to.getId());
            if (model != null) {
                updateDeviceType(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新设备类型
     *
     * @param to 设备类型to
     * @param model 设备类型
     * @throws SerException
     */
    private void updateDeviceType(DeviceTypeTO to, DeviceType model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}