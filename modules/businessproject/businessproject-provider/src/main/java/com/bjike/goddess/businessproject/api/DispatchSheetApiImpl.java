package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.service.DispatchSheetSer;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 商务项目派工单信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dispatchSheetApiImpl")
public class DispatchSheetApiImpl implements DispatchSheetAPI {

    @Autowired
    private DispatchSheetSer dispatchSheetSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return dispatchSheetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dispatchSheetSer.guidePermission(guidePermissionTO);
    }


    @Override
    public Long countDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return dispatchSheetSer.countDispatchSheet(dispatchSheetDTO);
    }

    @Override
    public DispatchSheetBO getOneById(String id) throws SerException {
        return dispatchSheetSer.getOneById(id);
    }

    @Override
    public List<DispatchSheetBO> listDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return dispatchSheetSer.listDispatchSheet(dispatchSheetDTO);
    }

    @Override
    public DispatchSheetBO addDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        return dispatchSheetSer.addDispatchSheet(dispatchSheetTO);
    }

    @Override
    public DispatchSheetBO editDispatchSheet(DispatchSheetTO dispatchSheetTO) throws SerException {
        return dispatchSheetSer.editDispatchSheet(dispatchSheetTO);
    }

    @Override
    public void deleteDispatchSheet(String id) throws SerException {
        dispatchSheetSer.deleteDispatchSheet(id);
    }

    @Override
    public List<DispatchSheetBO> searchDispatchSheet(DispatchSheetDTO dispatchSheetDTO) throws SerException {
        return dispatchSheetSer.searchDispatchSheet(dispatchSheetDTO);
    }

    @Override
    public List<String> listArea() throws SerException {
        return dispatchSheetSer.listArea();
    }

    @Override
    public List<String> listDispatchName() throws SerException {
        return dispatchSheetSer.listDispatchName();
    }

    @Override
    public Set<String> allInnerProjects() throws SerException {
        return dispatchSheetSer.allInnerProjects();
    }

    @Override
    public List<String> allDispatchNum() throws SerException {
        return dispatchSheetSer.allDispatchNum();
    }

    @Override
    public List<DispatchSheetBO> getInfoByDispatchNum(String dispatchNum ) throws SerException {
        return dispatchSheetSer.getInfoByDispatchNum(dispatchNum);
    }

    @Override
    public byte[] exportExcel(DispatchSheetDTO dto) throws SerException {
        return dispatchSheetSer.exportExcel(dto);
    }

    @Override
    public void leadExcel(List<DispatchSheetTO> toList) throws SerException {
        dispatchSheetSer.leadExcel(toList);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return dispatchSheetSer.templateExcel();
    }

    @Override
    public Set<String> nums() throws SerException {
        return dispatchSheetSer.nums();
    }
}