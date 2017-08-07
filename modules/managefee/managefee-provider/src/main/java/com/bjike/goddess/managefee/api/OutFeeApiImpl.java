package com.bjike.goddess.managefee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.bo.OutFeeBO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.dto.OutFeeDTO;
import com.bjike.goddess.managefee.excel.SonPermissionObject;
import com.bjike.goddess.managefee.service.OutFeeSer;
import com.bjike.goddess.managefee.to.*;
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
    public List<OutFeeBO> collectArea(CollectAreaTO collectAreaTO) throws SerException {
        return outFeeSer.collectArea( collectAreaTO );
    }

    @Override
    public List<OutFeeBO> collectGroup(CollectGroupTO collectGroupTO) throws SerException {
        return outFeeSer.collectGroup( collectGroupTO );
    }

    @Override
    public List<OutFeeBO> collectProject(CollectProjectTO collectProjectTO) throws SerException {
        return outFeeSer.collectProject( collectProjectTO );
    }

    @Override
    public List<OutFeeBO> collectType(CollectCategoryTO collectCategoryTO) throws SerException {
        return outFeeSer.collectType( collectCategoryTO );
    }

    @Override
    public List<OutFeeBO> collectAreaDetial(CollectAreaTO collectAreaTO) throws SerException {
        return outFeeSer.collectAreaDetial( collectAreaTO );
    }

    @Override
    public List<OutFeeBO> collectGroupDetail(CollectGroupTO collectGroupTO) throws SerException {
        return outFeeSer.collectGroupDetail( collectGroupTO );
    }

    @Override
    public List<OutFeeBO> collectProjectDetail(CollectProjectTO collectProjectTO) throws SerException {
        return outFeeSer.collectProjectDetail( collectProjectTO );
    }

    @Override
    public List<OutFeeBO> collectTypeDetail(CollectCategoryTO collectCategoryTO) throws SerException {
        return outFeeSer.collectTypeDetail( collectCategoryTO );
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