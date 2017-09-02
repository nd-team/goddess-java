package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.ChangeEquityTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.ChangeEquityTypeDTO;
import com.bjike.goddess.shareholdersmanage.entity.ChangeEquityType;
import com.bjike.goddess.shareholdersmanage.service.ChangeEquityTypeSer;
import com.bjike.goddess.shareholdersmanage.to.ChangeEquityTypeTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 变更股权类型业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("changeEquityTypeApiImpl")
public class ChangeEquityTypeApiImpl implements ChangeEquityTypeAPI {
    @Autowired
    private ChangeEquityTypeSer changeEquityTypeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return changeEquityTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return changeEquityTypeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countChangeType(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        return changeEquityTypeSer.countChangeType(changeEquityTypeDTO);
    }

    @Override
    public ChangeEquityTypeBO getOne(String id) throws SerException {
        return changeEquityTypeSer.getOne(id);
    }

    @Override
    public List<ChangeEquityTypeBO> findList(ChangeEquityTypeDTO changeEquityTypeDTO) throws SerException {
        return changeEquityTypeSer.findList(changeEquityTypeDTO);
    }

    @Override
    public ChangeEquityTypeBO save(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        return changeEquityTypeSer.save(changeEquityTypeTO);
    }

    @Override
    public ChangeEquityTypeBO edit(ChangeEquityTypeTO changeEquityTypeTO) throws SerException {
        return changeEquityTypeSer.edit(changeEquityTypeTO);
    }

    @Override
    public void delete(String id) throws SerException {
       changeEquityTypeSer.delete(id);
    }
}