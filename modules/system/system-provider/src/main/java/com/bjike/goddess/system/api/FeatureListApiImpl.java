package com.bjike.goddess.system.api;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.service.FeatureListSer;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.QuestionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能列表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("featureListApiImpl")
public class FeatureListApiImpl implements FeatureListAPI {

    @Autowired
    private FeatureListSer featureListSer;
    @Override
    public Long count(FeatureListDTO dto) throws SerException {
        return featureListSer.count(dto);
    }

    @Override
    public FeatureListBO getOne(String id) throws SerException {
        return featureListSer.getOne(id);
    }

    @Override
    public List<FeatureListBO> list(FeatureListDTO dto) throws SerException {
        return featureListSer.list(dto);
    }

    @Override
    public FeatureListBO insert(FeatureListTO to) throws SerException {
        return featureListSer.insert(to);
    }
    @Override
    public FeatureListBO edit(FeatureListTO to) throws SerException {
        return featureListSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        featureListSer.remove(id);
    }


    @Override
    public void ask(String id, QuestionTO questionTO) throws SerException{
        featureListSer.ask(id,questionTO);
    }

    @Override
    public FeatureListBO findDetail(String id) throws SerException{
        return featureListSer.findDetail(id);
    }
}