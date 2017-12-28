package com.bjike.goddess.fixedassets.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fixedassets.bo.BaseInfoBO;
import com.bjike.goddess.fixedassets.bo.BaseInfoDetailBO;
import com.bjike.goddess.fixedassets.bo.SummationBO;
import com.bjike.goddess.fixedassets.dto.BaseInfoDTO;
import com.bjike.goddess.fixedassets.excel.SonPermissionObject;
import com.bjike.goddess.fixedassets.service.BaseInfoSer;
import com.bjike.goddess.fixedassets.to.BaseInfoTO;
import com.bjike.goddess.fixedassets.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-23 11:41 ]
 * @Description: [ 基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("baseInfoApiImpl")
public class BaseInfoApiImpl implements BaseInfoAPI {
    @Autowired
    private BaseInfoSer baseInfoSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return baseInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return baseInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<BaseInfoBO> list(BaseInfoDTO dto) throws SerException {
        return baseInfoSer.list(dto);
    }

    @Override
    public BaseInfoBO save(BaseInfoTO to) throws SerException {
        return baseInfoSer.save(to);
    }

    @Override
    public BaseInfoBO edit(BaseInfoTO to) throws SerException {
        return baseInfoSer.edit(to);
    }

    @Override
    public void deleteBaseInfo(String id) throws SerException {
        baseInfoSer.deleteBaseInfo(id);
    }

    @Override
    public Long countBaseInfo(BaseInfoDTO baseInfoDTO) throws SerException {
        return baseInfoSer.countBaseInfo(baseInfoDTO);
    }

    @Override
    public BaseInfoBO getById(String id) throws SerException {
        return baseInfoSer.getById(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return baseInfoSer.exportExcel();
    }

    @Override
    public void importExcel(List<BaseInfoTO> baseInfoTOS) throws SerException {
        baseInfoSer.importExcel(baseInfoTOS);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return baseInfoSer.templateExport();
    }

    @Override
    public List<SummationBO> summation(Integer year, Integer month) throws SerException {
        return baseInfoSer.summation(year,month);
    }

    @Override
    public List<BaseInfoDetailBO> baseInfoDetail(Integer year, Integer month) throws SerException {
        return baseInfoSer.baseInfoDetail(year,month);
    }

    @Override
    public List<String> findallUser() throws SerException {
        return baseInfoSer.findallUser();
    }

}