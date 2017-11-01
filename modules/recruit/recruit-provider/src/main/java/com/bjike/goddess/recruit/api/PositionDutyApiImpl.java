package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.PositionDutyBO;
import com.bjike.goddess.recruit.dto.PositionDutyDTO;
import com.bjike.goddess.recruit.service.PositionDutySer;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.PositionDutyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司岗位分类岗位职责业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 09:31 ]
 * @Description: [ 公司岗位分类岗位职责业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("positionDutyApiImpl")
public class PositionDutyApiImpl implements PositionDutyAPI {
    @Autowired
    private PositionDutySer positionDutySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return positionDutySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return positionDutySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(PositionDutyDTO dto) throws SerException {
        return positionDutySer.count(dto);
    }

    @Override
    public PositionDutyBO getId(String id) throws SerException {
        return positionDutySer.getId(id);
    }

    @Override
    public List<PositionDutyBO> list(PositionDutyDTO dto) throws SerException {
        return positionDutySer.list(dto);
    }

    @Override
    public PositionDutyBO save(PositionDutyTO to) throws SerException {
        return positionDutySer.save(to);
    }

    @Override
    public PositionDutyBO update(PositionDutyTO to) throws SerException {
        return positionDutySer.update(to);
    }

    @Override
    public void remove(String id) throws SerException {
        positionDutySer.remove(id);
    }
    @Override
    public void thaw(String id) throws SerException {
        positionDutySer.thaw(id);
    }

    @Override
    public void congeal(String id) throws SerException {
        positionDutySer.congeal(id);
    }
}