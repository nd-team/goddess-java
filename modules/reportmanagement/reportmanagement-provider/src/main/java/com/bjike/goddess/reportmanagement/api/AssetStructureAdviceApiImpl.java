package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.AssetStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.AssetStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.service.AssetStructureAdviceSer;
import com.bjike.goddess.reportmanagement.to.AssetStructureAdviceTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产结构管理建议设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:38 ]
 * @Description: [ 资产结构管理建议设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assetStructureAdviceApiImpl")
public class AssetStructureAdviceApiImpl implements AssetStructureAdviceAPI {
    @Autowired
    private AssetStructureAdviceSer assetStructureAdviceSer;

    @Override
    public List<AssetStructureAdviceBO> list(AssetStructureAdviceDTO dto) throws SerException {
        return assetStructureAdviceSer.list(dto);
    }

    @Override
    public AssetStructureAdviceBO save(AssetStructureAdviceTO to) throws SerException {
        return assetStructureAdviceSer.save(to);
    }

    @Override
    public void edit(AssetStructureAdviceTO to) throws SerException {
        assetStructureAdviceSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        assetStructureAdviceSer.delete(id);
    }

    @Override
    public AssetStructureAdviceBO findByID(String id) throws SerException {
        return assetStructureAdviceSer.findByID(id);
    }

    @Override
    public Long count(AssetStructureAdviceDTO dto) throws SerException {
        return assetStructureAdviceSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return assetStructureAdviceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return assetStructureAdviceSer.guidePermission(guidePermissionTO);
    }
}