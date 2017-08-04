package com.bjike.goddess.costdetail.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.costdetail.bo.DetailTypeBO;
import com.bjike.goddess.costdetail.dto.DetailTypeDTO;
import com.bjike.goddess.costdetail.entity.DetailType;
import com.bjike.goddess.costdetail.excel.SonPermissionObject;
import com.bjike.goddess.costdetail.service.DetailTypeSer;
import com.bjike.goddess.costdetail.to.DetailTypeTO;
import com.bjike.goddess.costdetail.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 明细分类业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-07 09:43 ]
 * @Description: [ 明细分类业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("detailTypeApiImpl")
public class DetailTypeApiImpl implements DetailTypeAPI {
    @Autowired
    private DetailTypeSer detailTypeSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return detailTypeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return detailTypeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<String> findTypeName(String parNode) throws SerException {
        return detailTypeSer.findTypeName(parNode);
    }

    @Override
    public List<DetailTypeBO> list(DetailTypeDTO detailTypeDTO) throws SerException {
        return detailTypeSer.list(detailTypeDTO);
    }

    @Override
    public Long countDetailType(DetailTypeDTO detailTypeDTO) throws SerException {
        return detailTypeSer.countDetailType(detailTypeDTO);
    }

    @Override
    public DetailTypeBO getOneById(String id) throws SerException {
        return detailTypeSer.getOneById(id);
    }

    @Override
    public DetailTypeBO add(DetailTypeTO detailTypeTO) throws SerException {
        return detailTypeSer.add(detailTypeTO);
    }

    @Override
    public DetailTypeBO edit(DetailTypeTO detailTypeTO) throws SerException {
        return detailTypeSer.edit(detailTypeTO);
    }

    @Override
    public List<DetailTypeBO> findByNode(String parNode) throws SerException {
        return detailTypeSer.findByNode(parNode);
    }

    @Override
    public void delete(String id) throws SerException {
        detailTypeSer.delete(id);
    }
}