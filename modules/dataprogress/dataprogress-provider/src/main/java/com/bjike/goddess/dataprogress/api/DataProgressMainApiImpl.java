package com.bjike.goddess.dataprogress.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dataprogress.bo.DataProgressMainBO;
import com.bjike.goddess.dataprogress.dto.DataProgressChildDTO;
import com.bjike.goddess.dataprogress.dto.DataProgressMainDTO;
import com.bjike.goddess.dataprogress.service.DataProgressMainSer;
import com.bjike.goddess.dataprogress.to.DataProgressMainTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资料收集进度管理主表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-28 10:03 ]
 * @Description: [ 资料收集进度管理主表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dataProgressMainApiImpl")
public class DataProgressMainApiImpl implements DataProgressMainAPI {
    @Autowired
    private DataProgressMainSer dataProgressMainSer;

    @Override
    public Long count(DataProgressChildDTO dataProgressChildDTO) throws SerException {
        return dataProgressMainSer.count(dataProgressChildDTO);
    }

    @Override
    public DataProgressMainBO getId(String id) throws SerException {
        return dataProgressMainSer.getId(id);
    }

    @Override
    public List<DataProgressMainBO> list(DataProgressMainDTO dataProgressMainDTO) throws SerException {
        return dataProgressMainSer.list(dataProgressMainDTO);
    }

    @Override
    public void add(DataProgressMainTO to) throws SerException {
        dataProgressMainSer.add(to);
    }

    @Override
    public void edit(DataProgressMainTO to) throws SerException {
        dataProgressMainSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        dataProgressMainSer.delete(id);
    }

    @Override
    public void follow(DataProgressMainTO to) throws SerException {
        dataProgressMainSer.follow(to);
    }
}