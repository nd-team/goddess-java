package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.AssetBO;
import com.bjike.goddess.reportmanagement.bo.DetailBO;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeBO;
import com.bjike.goddess.reportmanagement.bo.StructureBO;
import com.bjike.goddess.reportmanagement.dto.AssetDTO;
import com.bjike.goddess.reportmanagement.service.AssetSer;
import com.bjike.goddess.reportmanagement.to.AssetTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-19 11:19 ]
 * @Description: [ 资产表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assetApiImpl")
public class AssetApiImpl implements AssetAPI {
    @Autowired
    private AssetSer assetSer;

    @Override
    public List<AssetBO> list(AssetDTO dto) throws SerException {
        return assetSer.list(dto);
    }

    @Override
    public AssetBO save(AssetTO to) throws SerException {
        return assetSer.save(to);
    }

    @Override
    public List<StructureBO> assetStructure(AssetDTO dto) throws SerException {
        return assetSer.assetStructure(dto);
    }

    @Override
    public List<RepayAnalyzeBO> repayAnalyze(AssetDTO dto) throws SerException {
        return assetSer.repayAnalyze(dto);
    }

    @Override
    public List<DetailBO> findDetails(String id, AssetDTO dto) throws SerException {
        return assetSer.findDetails(id,dto);
    }

    @Override
    public AssetBO findByID(String id) throws SerException {
        return assetSer.findByID(id);
    }

    @Override
    public Long count(AssetDTO dto) throws SerException {
        return assetSer.count(dto);
    }

    @Override
    public void edit(AssetTO to) throws SerException {
        assetSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        assetSer.delete(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return assetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return assetSer.guidePermission(guidePermissionTO);
    }
}