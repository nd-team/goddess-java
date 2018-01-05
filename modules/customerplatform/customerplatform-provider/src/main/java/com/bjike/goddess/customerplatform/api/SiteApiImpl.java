package com.bjike.goddess.customerplatform.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customerplatform.bo.SiteBO;
import com.bjike.goddess.customerplatform.dto.SiteDTO;
import com.bjike.goddess.customerplatform.service.SiteSer;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.SiteTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站点业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:29 ]
 * @Description: [ 站点业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("siteApiImpl")
public class SiteApiImpl implements SiteAPI {

    @Autowired
    private SiteSer siteSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return siteSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return siteSer.guidePermission( guidePermissionTO );
    }

    @Override
    public void save(SiteTO to) throws SerException {
        siteSer.save(to);
    }

    @Override
    public void update(SiteTO to) throws SerException {
        siteSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        siteSer.delete(id);
    }

    @Override
    public List<SiteBO> maps(SiteDTO dto) throws SerException {
        return siteSer.maps(dto);
    }

    @Override
    public SiteBO getById(String id) throws SerException {
        return siteSer.getById(id);
    }

    @Override
    public Long getTotal(SiteDTO dto) throws SerException {
        return siteSer.getTotal(dto);
    }
}