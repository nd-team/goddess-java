package com.bjike.goddess.managefee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.excel.SonPermissionObject;
import com.bjike.goddess.managefee.service.OutFeeSer;
import com.bjike.goddess.managefee.to.GuidePermissionTO;
import com.bjike.goddess.managefee.to.OutFeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外包费业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:39 ]
 * @Description: [ 外包费业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("outFeeApiImpl")
public class OutFeeApiImpl implements OutFeeAPI {

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return outFeeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return outFeeSer.guidePermission(guidePermissionTO);
    }

    @Autowired
    private OutFeeSer outFeeSer;

    @Override
    public Long countOutFee(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.countOutFee(outFeeDTO);
    }

    @Override
    public OutFeeBO getOneById(String id) throws SerException {
        return outFeeSer.getOneById(id);
    }

    @Override
    public List<OutFeeBO> listOutFee(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.listOutFee(outFeeDTO);
    }

    @Override
    public OutFeeBO addOutFee(OutFeeTO outFeeTO) throws SerException {
        return outFeeSer.addOutFee(outFeeTO);
    }

    @Override
    public OutFeeBO editOutFee(OutFeeTO outFeeTO) throws SerException {
        return outFeeSer.editOutFee(outFeeTO);
    }

    @Override
    public void deleteOutFee(String id) throws SerException {
        outFeeSer.deleteOutFee(id);
    }

    @Override
    public List<OutFeeBO> collectArea(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.collectArea( outFeeDTO );
    }

    @Override
    public List<OutFeeBO> collectGroup(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.collectGroup(outFeeDTO);
    }

    @Override
    public List<OutFeeBO> collectProject(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.collectProject(outFeeDTO);
    }

    @Override
    public List<OutFeeBO> collectType(OutFeeDTO outFeeDTO) throws SerException {
        return outFeeSer.collectType( outFeeDTO );
    }

    @Override
    public List<String> yearList() throws SerException {
        return outFeeSer.yearList();
    }

    @Override
    public List<String> areaList() throws SerException {
        return outFeeSer.areaList();
    }

    @Override
    public List<String> groupList() throws SerException {
        return outFeeSer.groupList();
    }

    @Override
    public List<String> projectList() throws SerException {
        return outFeeSer.projectList();
    }
}