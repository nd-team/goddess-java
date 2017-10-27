package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.service.WithUnitSer;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.WithUnitTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 往来单位业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("withUnitApiImpl")
public class WithUnitApiImpl implements WithUnitAPI {
    @Autowired
    private WithUnitSer withUnitSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return withUnitSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return withUnitSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countWith(WithUnitDTO withUnitDTO) throws SerException {
        return withUnitSer.countWith(withUnitDTO);
    }

    @Override
    public WithUnitBO getOneById(String id) throws SerException {
        return withUnitSer.getOneById(id);
    }

    @Override
    public List<WithUnitBO> listWith(WithUnitDTO withUnitDTO) throws SerException {
        return withUnitSer.listWith(withUnitDTO);
    }

    @Override
    public WithUnitBO addWith(WithUnitTO withUnitTO) throws SerException {
        return withUnitSer.addWith(withUnitTO);
    }

    @Override
    public WithUnitBO editWith(WithUnitTO withUnitTO) throws SerException {
        return withUnitSer.editWith(withUnitTO);
    }

    @Override
    public void deleteWith(String id) throws SerException {
        withUnitSer.deleteWith(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return withUnitSer.exportExcel();
    }
}