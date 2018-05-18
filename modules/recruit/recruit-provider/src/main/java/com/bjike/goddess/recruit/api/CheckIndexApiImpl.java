package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import com.bjike.goddess.recruit.entity.CheckIndex;
import com.bjike.goddess.recruit.service.CheckIndexSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考核指标业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("checkIndexApiImpl")
public class CheckIndexApiImpl implements CheckIndexAPI {
    @Autowired
    private CheckIndexSer checkIndexSer;

    @Override
    public List<CheckIndexBO> list() throws SerException {
        return checkIndexSer.list();
    }

    @Override
    public void add(CheckIndexBO bo) throws SerException {
        checkIndexSer.add(bo);
    }

    @Override
    public void delete(String id) throws SerException {
        checkIndexSer.delete(id);
    }

    @Override
    public CheckIndexBO edit(String id) throws SerException {
        return checkIndexSer.edit(id);
    }
}