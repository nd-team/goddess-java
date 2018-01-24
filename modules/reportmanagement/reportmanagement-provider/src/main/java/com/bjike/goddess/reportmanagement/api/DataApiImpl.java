package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.DataBO;
import com.bjike.goddess.reportmanagement.dto.DataDTO;
import com.bjike.goddess.reportmanagement.service.DataSer;
import com.bjike.goddess.reportmanagement.to.DataTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补充资料表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dataApiImpl")
public class DataApiImpl implements DataAPI {

    @Autowired
    private DataSer dataSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return dataSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dataSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<DataBO> list(DataDTO dto) throws SerException {
        return dataSer.list(dto);
    }

    @Override
    public void save(DataTO to) throws SerException {
        dataSer.add(to);
    }

    @Override
    public DataBO findByID(String id) throws SerException {
        return dataSer.findByID(id);
    }

    @Override
    public void edit(DataTO to) throws SerException {
        dataSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        dataSer.delete(id);
    }

    @Override
    public Long count(DataDTO dto) throws SerException {
        return dataSer.findCount(dto);
    }

}