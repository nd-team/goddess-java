package com.bjike.goddess.checkhost.api;

import com.bjike.goddess.checkhost.bo.CollectNameBO;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.service.StayDaysSer;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工住宿天数汇总业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("stayDaysApiImpl")
public class StayDaysApiImpl implements StayDaysAPI {
    @Autowired
    private StayDaysSer stayDaysSer;

    @Override
    public Long countStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        return stayDaysSer.countStayDays(stayDaysDTO);
    }

    @Override
    public StayDaysBO getOne(String id) throws SerException {
        return stayDaysSer.getOne(id);
    }

    @Override
    public List<StayDaysBO> findListStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        return stayDaysSer.findListStayDays(stayDaysDTO);
    }

    @Override
    public StayDaysBO insertStayDays(StayDaysTO stayDaysTO) throws SerException {
        return stayDaysSer.insertStayDays(stayDaysTO);
    }

    @Override
    public StayDaysBO editStayDays(StayDaysTO stayDaysTO) throws SerException {
        return stayDaysSer.editStayDays(stayDaysTO);
    }

    @Override
    public void removeStayDays(String id) throws SerException {
        stayDaysSer.remove(id);
    }

    @Override
    public StayDaysBO auditStayDays(String id, CheckStatus checkStatus) throws SerException {
        return stayDaysSer.auditStayDays(id, checkStatus);
    }

    @Override
    public List<CollectNameBO> collectName(String[] names) throws SerException {
        return stayDaysSer.collectName(names);
    }

    @Override
    public List<String> getNames() throws SerException {
        return stayDaysSer.getNames();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return stayDaysSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return stayDaysSer.guidePermission(guidePermissionTO);
    }
}
