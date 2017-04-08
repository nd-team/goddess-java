package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.DemandTypeBO;
import com.bjike.goddess.attainment.service.DemandTypeSer;
import com.bjike.goddess.attainment.to.DemandTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研需求类型业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("demandTypeApiImpl")
public class DemandTypeApiImpl implements DemandTypeAPI {

    @Autowired
    private DemandTypeSer demandTypeSer;

    @Override
    public DemandTypeBO save(DemandTypeTO to) throws SerException {
        return demandTypeSer.save(to);
    }

    @Override
    public DemandTypeBO update(DemandTypeTO to) throws SerException {
        return demandTypeSer.update(to);
    }

    @Override
    public DemandTypeBO delete(String id) throws SerException {
        return demandTypeSer.delete(id);
    }

    @Override
    public DemandTypeBO congeal(String id) throws SerException {
        return demandTypeSer.congeal(id);
    }

    @Override
    public DemandTypeBO thaw(String id) throws SerException {
        return demandTypeSer.thaw(id);
    }

    @Override
    public List<DemandTypeBO> findThaw() throws SerException {
        return demandTypeSer.findThaw();
    }

}