package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.ClosenessFoactorSetBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorSetDTO;
import com.bjike.goddess.customer.service.ClosenessFoactorSetSer;
import com.bjike.goddess.customer.to.ClosenessFoactorSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 亲密度因素层设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("closenessFoactorSetApiImpl")
public class ClosenessFoactorSetApiImpl implements ClosenessFoactorSetAPI {
    @Autowired
    private ClosenessFoactorSetSer closenessFoactorSetSer;
    @Override
    public Long countCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
        return closenessFoactorSetSer.countCloseness(closenessFoactorSetDTO);
    }

    @Override
    public ClosenessFoactorSetBO getOneCloseness(String id) throws SerException {
        return closenessFoactorSetSer.getOneCloseness(id);
    }

    @Override
    public List<ClosenessFoactorSetBO> listCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
        return closenessFoactorSetSer.listCloseness(closenessFoactorSetDTO);
    }

    @Override
    public ClosenessFoactorSetBO addCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        return closenessFoactorSetSer.addCloseness(closenessFoactorSetTO);
    }

    @Override
    public ClosenessFoactorSetBO editCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        return closenessFoactorSetSer.editCloseness(closenessFoactorSetTO);
    }

    @Override
    public void deleteCloseness(String id) throws SerException {
        closenessFoactorSetSer.deleteCloseness(id);
    }
}