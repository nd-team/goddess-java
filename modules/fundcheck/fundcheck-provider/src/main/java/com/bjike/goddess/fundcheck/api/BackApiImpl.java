package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.bo.BackBO;
import com.bjike.goddess.fundcheck.dto.BackDTO;
import com.bjike.goddess.fundcheck.entity.Back;
import com.bjike.goddess.fundcheck.excel.SonPermissionObject;
import com.bjike.goddess.fundcheck.service.BackSer;
import com.bjike.goddess.fundcheck.to.BackTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 回款业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:48 ]
 * @Description: [ 回款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("backApiImpl")
public class BackApiImpl implements BackAPI {
    @Autowired
    private BackSer backSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return backSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return backSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(BackDTO backDTO) throws SerException {
        return backSer.count(backDTO);
    }

    @Override
    public BackBO getOne(String id) throws SerException {
        return backSer.getOne(id);
    }

    @Override
    public List<BackBO> findListBack(BackDTO backDTO) throws SerException {
        return backSer.findListBack(backDTO);
    }

    @Override
    public BackBO insert(BackTO backTO) throws SerException {
        return backSer.insert(backTO);
    }

    @Override
    public BackBO edit(BackTO backTO) throws SerException {
        return backSer.edit(backTO);
    }

    @Override
    public void remove(String id) throws SerException {
        backSer.remove(id);
    }

    @Override
    public List<BackBO> backinfo(String startTime, String endTime) throws SerException {
        return backSer.backinfo(startTime, endTime);
    }
    @Override
    public BaseBO importExcel(List<BackTO> backTOS) throws SerException {
        return backSer.importExcel(backTOS);
    }
    @Override
    public byte[] templateExport() throws SerException {
        return backSer.templateExport();
    }
}