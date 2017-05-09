package com.bjike.goddess.incomecheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.incomecheck.bo.CheckIndexBO;
import com.bjike.goddess.incomecheck.dto.CheckIndexDTO;
import com.bjike.goddess.incomecheck.service.CheckIndexSer;
import com.bjike.goddess.incomecheck.to.CheckIndexTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标设置业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("checkIndexApiImpl")
public class CheckIndexApiImpl implements CheckIndexAPI {

    @Autowired
    private CheckIndexSer checkIndexSer;

    @Override
    public Long countCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        return checkIndexSer.countCheckIndex(checkIndexDTO);
    }

    @Override
    public CheckIndexBO getOneById(String id) throws SerException {
        return checkIndexSer.getOneById(id);
    }

    @Override
    public List<CheckIndexBO> listCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        return checkIndexSer.listCheckIndex(checkIndexDTO);
    }

    @Override
    public CheckIndexBO addCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        return checkIndexSer.addCheckIndex(checkIndexTO);
    }

    @Override
    public CheckIndexBO editCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        return checkIndexSer.editCheckIndex(checkIndexTO);
    }

    @Override
    public void deleteCheckIndex(String id) throws SerException {
        checkIndexSer.deleteCheckIndex(id);
    }

    
}