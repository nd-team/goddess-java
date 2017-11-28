package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.CollectSchemeBO;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CollectSchemeDTO;
import com.bjike.goddess.task.service.CollectSchemeSer;
import com.bjike.goddess.task.service.CustomizeSer;
import com.bjike.goddess.task.to.CollectSchemeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 汇总方案业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectSchemeApiImpl")
public class CollectSchemeApiImpl implements CollectSchemeAPI {
    @Autowired
    private CollectSchemeSer collectSchemeSer;
    @Autowired
    private CustomizeSer customizeSer;

    @Override
    public List<CollectSchemeBO> list(CollectSchemeDTO dto) throws SerException {
        return collectSchemeSer.list(dto);
    }

    @Override
    public void save(CollectSchemeTO to) throws SerException {
        collectSchemeSer.save(to);
    }

    @Override
    public void edit(CollectSchemeTO to) throws SerException {
        collectSchemeSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        collectSchemeSer.delete(id);
    }

    @Override
    public CollectSchemeBO findByID(String id) throws SerException {
        return collectSchemeSer.findByID(id);
    }

    @Override
    public Long count(CollectSchemeDTO dto) throws SerException {
        return collectSchemeSer.count(dto);
    }

    @Override
    public void quartz() throws SerException {
        collectSchemeSer.quartz();
    }

    @Override
    public String collect(String id) throws SerException {
        return collectSchemeSer.collect(id);
    }

    @Override
    public List<String> fileds(CollectSchemeTO to) throws SerException {
        return collectSchemeSer.fileds(to);
    }

    @Override
    public void notice(CollectSchemeTO to) throws SerException {
        collectSchemeSer.notice(to);
    }

    @Override
    public String detail(String id) throws SerException {
        return collectSchemeSer.detail(id);
    }

    @Override
    public List<CustomizeBO> all() throws SerException {
        return BeanTransform.copyProperties(customizeSer.findAll(),CustomizeBO.class);
    }
}