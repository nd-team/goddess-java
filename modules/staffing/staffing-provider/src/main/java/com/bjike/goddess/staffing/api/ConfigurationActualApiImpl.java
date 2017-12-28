package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.ConfigurationActualBO;
import com.bjike.goddess.staffing.dto.ConfigurationActualDTO;
import com.bjike.goddess.staffing.service.ConfigurationActualSer;
import com.bjike.goddess.staffing.to.ConfigurationActualTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人数配置-实际业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-03 11:05 ]
 * @Description: [ 人数配置-实际业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("configurationActualApiImpl")
public class ConfigurationActualApiImpl implements ConfigurationActualAPI {
    @Autowired
    private ConfigurationActualSer configurationActualSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return configurationActualSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return configurationActualSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ConfigurationActualBO> list(ConfigurationActualDTO dto) throws SerException {
        return configurationActualSer.list(dto);
    }

    @Override
    public ConfigurationActualBO save(ConfigurationActualTO to) throws SerException {
        return configurationActualSer.save(to);
    }

    @Override
    public void edit(ConfigurationActualTO to) throws SerException {
        configurationActualSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        configurationActualSer.delete(id);
    }

    @Override
    public ConfigurationActualBO findByID(String id) throws SerException {
        return configurationActualSer.findByID(id);
    }

    @Override
    public Long count(ConfigurationActualDTO dto) throws SerException {
        return configurationActualSer.count(dto);
    }
}