package com.bjike.goddess.managefee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.service.ManageFeeSer;
import com.bjike.goddess.managefee.to.GuidePermissionTO;
import com.bjike.goddess.managefee.to.ManageFeeTO;
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
    public List<ManageFeeBO> collectArea(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.collectArea(manageFeeDTO);
    }

    @Override
    public List<ManageFeeBO> collectGroup(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.collectGroup(manageFeeDTO);
    }

    @Override
    public List<ManageFeeBO> collectProject(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.collectProject(manageFeeDTO);
    }

    @Override
    public List<ManageFeeBO> collectType(ManageFeeDTO manageFeeDTO) throws SerException {
        return manageFeeSer.collectType(manageFeeDTO);
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