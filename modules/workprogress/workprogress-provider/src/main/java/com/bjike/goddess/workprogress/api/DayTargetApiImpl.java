package com.bjike.goddess.workprogress.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.workprogress.bo.DayTargetBO;
import com.bjike.goddess.workprogress.dto.DayTargetDTO;
import com.bjike.goddess.workprogress.excel.SonPermissionObject;
import com.bjike.goddess.workprogress.service.DayTargetSer;
import com.bjike.goddess.workprogress.to.DayTargetTO;
import com.bjike.goddess.workprogress.to.GuidePermissionTO;
import com.bjike.goddess.workprogress.to.StandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日指标业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:15 ]
 * @Description: [ 日指标业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dayTargetApiImpl")
public class DayTargetApiImpl implements DayTargetAPI {

    @Autowired
    private DayTargetSer dayTargetSer;

    @Override
    public DayTargetBO save(DayTargetTO to) throws SerException {
        return dayTargetSer.save(to);
    }

    @Override
    public DayTargetBO update(DayTargetTO to) throws SerException {
        return dayTargetSer.update(to);
    }

    @Override
    public DayTargetBO delete(String id) throws SerException {
        return dayTargetSer.delete(id);
    }

    @Override
    public DayTargetBO getById(String id) throws SerException {
        return dayTargetSer.getById(id);
    }

    @Override
    public DayTargetBO updateStandard(StandardTO to) throws SerException {
        return dayTargetSer.updateStandard(to);
    }

    @Override
    public List<DayTargetBO> maps(DayTargetDTO dto) throws SerException {
        return dayTargetSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return dayTargetSer.getTotal();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return dayTargetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dayTargetSer.guidePermission( guidePermissionTO );
    }
}