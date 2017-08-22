package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.PlatformClassifyBO;
import com.bjike.goddess.system.dto.PlatformClassifyDTO;
import com.bjike.goddess.system.excel.SonPermissionObject;
import com.bjike.goddess.system.service.PlatformClassifySer;
import com.bjike.goddess.system.to.GuidePermissionTO;
import com.bjike.goddess.system.to.PlatformClassifyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 平台分类业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 01:44 ]
 * @Description: [ 平台分类业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("platformClassifyApiImpl")
public class PlatformClassifyApiImpl implements PlatformClassifyAPI {
    @Autowired
    private PlatformClassifySer platformClassifySer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return platformClassifySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return platformClassifySer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long count(PlatformClassifyDTO dto) throws SerException {
        return platformClassifySer.count(dto);
    }

    @Override
    public PlatformClassifyBO getOne(String id) throws SerException {
        return platformClassifySer.getOne(id);
    }

    @Override
    public List<PlatformClassifyBO> list(PlatformClassifyDTO dto) throws SerException {
        return platformClassifySer.list(dto);
    }

    @Override
    public PlatformClassifyBO insert(PlatformClassifyTO to) throws SerException {
        return platformClassifySer.insert(to);
    }

    @Override
    public PlatformClassifyBO edit(PlatformClassifyTO to) throws SerException {
        return platformClassifySer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        platformClassifySer.remove(id);
    }
    @Override
    public List<String> getPlatformName() throws SerException {
        return platformClassifySer.getPlatformName();
    }
    @Override
    public PlatformClassifyBO importExcel(List<PlatformClassifyTO> platformClassifyTOS) throws SerException {
        return platformClassifySer.importExcel(platformClassifyTOS);
    }

    @Override
    public byte[] exportExcel(PlatformClassifyDTO dto) throws SerException{
        return platformClassifySer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException{
        return platformClassifySer.templateExport();
    }
}