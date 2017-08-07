package com.bjike.goddess.managefee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.service.ManageFeeSer;
import com.bjike.goddess.managefee.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理费业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("manageFeeApiImpl")
public class ManageFeeApiImpl implements ManageFeeAPI {

    @Autowired
    private ManageFeeSer manageFeeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return manageFeeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return manageFeeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.countManageFee(manageFeeDTO);
    }

    @Override
    public ManageFeeBO getOneById(String id) throws SerException {
        return manageFeeSer.getOneById(id);
    }

    @Override
    public List<ManageFeeBO> listManageFee(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.listManageFee(manageFeeDTO);
    }

    @Override
    public ManageFeeBO addManageFee(ManageFeeTO manageFeeTO) throws SerException {
        return manageFeeSer.addManageFee(manageFeeTO);
    }

    @Override
    public ManageFeeBO editManageFee(ManageFeeTO manageFeeTO) throws SerException {
        return manageFeeSer.editManageFee(manageFeeTO);
    }

    @Override
    public void deleteManageFee(String id) throws SerException {
        manageFeeSer.deleteManageFee(id);
    }

    @Override
    public List<ManageFeeBO> collectAreaDetial(CollectAreaTO collectAreaTO) throws SerException {
        return manageFeeSer.collectAreaDetial(collectAreaTO);
    }

    @Override
    public List<ManageFeeBO> collectGroupDetail(CollectGroupTO collectGroupTO) throws SerException {
        return manageFeeSer.collectGroupDetail( collectGroupTO );
    }

    @Override
    public List<ManageFeeBO> collectProjectDetail(CollectProjectTO collectProjectTO) throws SerException {
        return manageFeeSer.collectProjectDetail( collectProjectTO );
    }

    @Override
    public List<ManageFeeBO> collectTypeDetail(CollectCategoryTO collectCategoryTO) throws SerException {
        return manageFeeSer.collectTypeDetail( collectCategoryTO );
    }

    @Override
    public List<ManageFeeBO> collectArea(CollectAreaTO collectAreaTO) throws SerException {
        return manageFeeSer.collectArea(collectAreaTO);
    }

    @Override
    public List<ManageFeeBO> collectGroup(CollectGroupTO collectGroupTO) throws SerException {
        return manageFeeSer.collectGroup(collectGroupTO);
    }

    @Override
    public List<ManageFeeBO> collectProject(CollectProjectTO collectProjectTO) throws SerException {
        return manageFeeSer.collectProject(collectProjectTO);
    }

    @Override
    public List<ManageFeeBO> collectType(CollectCategoryTO collectCategoryTO) throws SerException {
        return manageFeeSer.collectType(collectCategoryTO);
    }

    @Override
    public List<String> yearList() throws SerException {
        return manageFeeSer.yearList();
    }

    @Override
    public List<String> areaList() throws SerException {
        return manageFeeSer.areaList();
    }

    @Override
    public List<String> groupList() throws SerException {
        return manageFeeSer.groupList();
    }

    @Override
    public List<String> projectList() throws SerException {
        return manageFeeSer.projectList();
    }
}