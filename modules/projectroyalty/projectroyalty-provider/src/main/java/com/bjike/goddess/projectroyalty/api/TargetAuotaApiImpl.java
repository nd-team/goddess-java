package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.TargetAuotaBO;
import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
import com.bjike.goddess.projectroyalty.service.TargetAuotaSer;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目提成目标定额业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("targetAuotaApiImpl")
public class TargetAuotaApiImpl implements TargetAuotaAPI {

    @Autowired
    private TargetAuotaSer targetAuotaSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return targetAuotaSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return targetAuotaSer.guidePermission(guidePermissionTO);
    }


    @Override
    public TargetAuotaBO targetSave(TargetAuotaTO to) throws SerException {
        return targetAuotaSer.targetSave(to);
    }

    @Override
    public TargetAuotaBO actualSave(TargetAuotaTO to) throws SerException {
        return targetAuotaSer.actualSave(to);
    }

    @Override
    public TargetAuotaBO update(TargetAuotaTO to) throws SerException {
        return targetAuotaSer.update(to);
    }

    @Override
    public TargetAuotaBO delete(String id) throws SerException {
        return targetAuotaSer.delete(id);
    }

    @Override
    public TargetAuotaBO getById(String id) throws SerException {
        return targetAuotaSer.getById(id);
    }

    @Override
    public List<TargetAuotaBO> targetMaps(TargetAuotaDTO dto) throws SerException {
        return targetAuotaSer.targetMaps(dto);
    }

    @Override
    public List<TargetAuotaBO> actualMaps(TargetAuotaDTO dto) throws SerException {
        return targetAuotaSer.actualMaps(dto);
    }

    @Override
    public Long getTargetTotal() throws SerException {
        return targetAuotaSer.getTargetTotal();
    }

    @Override
    public Long getActualTotal() throws SerException {
        return targetAuotaSer.getActualTotal();
    }
}