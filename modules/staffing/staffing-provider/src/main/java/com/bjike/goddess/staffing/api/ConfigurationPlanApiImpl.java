package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.ConfigurationPlanBO;
import com.bjike.goddess.staffing.dto.ConfigurationPlanDTO;
import com.bjike.goddess.staffing.service.ConfigurationPlanSer;
import com.bjike.goddess.staffing.to.ConfigurationPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人数配置-计划业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:33 ]
 * @Description: [ 人数配置-计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("configurationPlanApiImpl")
public class ConfigurationPlanApiImpl implements ConfigurationPlanAPI {
    @Autowired
    private ConfigurationPlanSer configurationPlanSer;

    @Override
    public List<ConfigurationPlanBO> list(ConfigurationPlanDTO dto) throws SerException {
        return configurationPlanSer.list(dto);
    }

    @Override
    public ConfigurationPlanBO save(ConfigurationPlanTO to) throws SerException {
        return configurationPlanSer.save(to);
    }

    @Override
    public void edit(ConfigurationPlanTO to) throws SerException {
        configurationPlanSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        configurationPlanSer.delete(id);
    }

    @Override
    public ConfigurationPlanBO findByID(String id) throws SerException {
        return configurationPlanSer.findByID(id);
    }

    @Override
    public Long count(ConfigurationPlanDTO dto) throws SerException {
        return configurationPlanSer.count(dto);
    }
}