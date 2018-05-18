package com.bjike.goddess.customerplatform.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customerplatform.bo.OwnerBO;
import com.bjike.goddess.customerplatform.dto.OwnerDTO;
import com.bjike.goddess.customerplatform.service.OwnerSer;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.OwnerTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业主业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:27 ]
 * @Description: [ 业主业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ownerApiImpl")
public class OwnerApiImpl implements OwnerAPI {
    @Autowired
    private OwnerSer ownerSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return ownerSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return ownerSer.guidePermission( guidePermissionTO );
    }

    @Override
    public void save(OwnerTO to) throws SerException {
        ownerSer.save(to);
    }

    @Override
    public void update(OwnerTO to) throws SerException {
        ownerSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        ownerSer.delete(id);
    }

    @Override
    public List<OwnerBO> maps(OwnerDTO dto) throws SerException {
        return ownerSer.maps(dto);
    }

    @Override
    public OwnerBO getById(String id) throws SerException {
        return ownerSer.getById(id);
    }

    @Override
    public Long getTotal(OwnerDTO dto) throws SerException {
        return ownerSer.getTotal(dto);
    }
    @Override
    public List<String> getProvinces() throws SerException {
        return ownerSer.getProvinces();
    }

    @Override
    public List<String> getCity(String provinces) throws SerException {
        return ownerSer.getCity(provinces);
    }

    @Override
    public List<String> getArea(String provinces,String city) throws SerException {
        return ownerSer.getArea(provinces,city);
    }
}