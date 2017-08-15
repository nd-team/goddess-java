package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.ApplicationBO;
import com.bjike.goddess.staffshares.dto.ApplicationDTO;
import com.bjike.goddess.staffshares.excel.SonPermissionObject;
import com.bjike.goddess.staffshares.service.ApplicationSer;
import com.bjike.goddess.staffshares.to.ApplicationTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 干股代表申请业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("applicationApiImpl")
public class ApplicationApiImpl implements ApplicationAPI {
    @Autowired
    private ApplicationSer applicationSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return applicationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return applicationSer.guidePermission( guidePermissionTO );
    }

    @Override
    public void save(ApplicationTO to) throws SerException {
        applicationSer.save(to);
    }

    @Override
    public List<ApplicationBO> maps(ApplicationDTO dto) throws SerException {
        return applicationSer.maps(dto);
    }

    @Override
    public ApplicationBO getById(String id) throws SerException {
        return applicationSer.getById(id);
    }

    @Override
    public Long getTotal(ApplicationDTO applicationDTO) throws SerException {
        return applicationSer.getTotal(applicationDTO);
    }

    @Override
    public void examine(ApplicationTO to) throws SerException {
        applicationSer.examine(to);
    }
}