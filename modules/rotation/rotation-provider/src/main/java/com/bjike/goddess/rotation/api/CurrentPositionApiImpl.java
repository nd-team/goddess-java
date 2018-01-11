package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CurrentPositionBO;
import com.bjike.goddess.rotation.dto.CurrentPositionDTO;
import com.bjike.goddess.rotation.excel.SonPermissionObject;
import com.bjike.goddess.rotation.service.CurrentPositionSer;
import com.bjike.goddess.rotation.to.CurrentPositionTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 目前岗位情况业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:30 ]
 * @Description: [ 目前岗位情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("currentPositionApiImpl")
public class CurrentPositionApiImpl implements CurrentPositionAPI {

    @Autowired
    CurrentPositionSer currentPositionSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return currentPositionSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return currentPositionSer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long count(CurrentPositionDTO dto) throws SerException {
        return currentPositionSer.count(dto);
    }

    @Override
    public List<CurrentPositionBO> list(CurrentPositionDTO dto) throws SerException {
        return currentPositionSer.list(dto);
    }

    @Override
    public void add(CurrentPositionTO to) throws SerException {
        currentPositionSer.add(to);
    }

    @Override
    public void update(CurrentPositionTO to) throws SerException {
        currentPositionSer.update(to);
    }

    @Override
    public void update(String id, String arrangement) throws SerException {
        currentPositionSer.update(id, arrangement);
    }

    @Override
    public void delete(String id) throws SerException {
        currentPositionSer.delete(id);
    }

    @Override
    public CurrentPositionBO getOne(String id) throws SerException {
        return currentPositionSer.getOne(id);
    }

    @Override
    public void importExcel(List<CurrentPositionTO> tos) throws SerException {
        currentPositionSer.importExcel(tos);
    }

    @Override
    public byte[] exportExcel(CurrentPositionDTO dto) throws SerException {
        return currentPositionSer.exportExcel(dto);
    }

    @Override
    public byte[] exportTemplate() throws SerException {
        return currentPositionSer.exportTemplate();
    }
}