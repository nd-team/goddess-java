package com.bjike.goddess.materialcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialAnalyzeBO;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.entity.MaterialAnalyze;
import com.bjike.goddess.materialcheck.service.MaterialAnalyzeSer;
import com.bjike.goddess.materialcheck.to.GuidePermissionTO;
import com.bjike.goddess.materialcheck.to.MaterialAnalyzeTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物资分析业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("materialAnalyzeApiImpl")
public class MaterialAnalyzeApiImpl implements MaterialAnalyzeAPI {

    @Autowired
    private MaterialAnalyzeSer materialAnalyzeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return materialAnalyzeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return materialAnalyzeSer.guidePermission(guidePermissionTO);
    }

    /**
     * 根据id查询物资分析
     *
     * @param id 物资分析唯一标识
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    public MaterialAnalyzeBO findById(String id) throws SerException {
        MaterialAnalyze model = materialAnalyzeSer.findById(id);
        return BeanTransform.copyProperties(model, MaterialAnalyzeBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 物资分析dto
     * @throws SerException
     */
    @Override
    public Long count(MaterialAnalyzeDTO dto) throws SerException {
        return materialAnalyzeSer.count(dto);
    }

    /**
     * 分页查询物资分析
     *
     * @param dto 物资分析dto
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    public List<MaterialAnalyzeBO> list(MaterialAnalyzeDTO dto) throws SerException {
        return materialAnalyzeSer.list(dto);
    }

    /**
     * 保存物资分析
     *
     * @param to 物资分析to
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    public MaterialAnalyzeBO save(MaterialAnalyzeTO to) throws SerException {
        return materialAnalyzeSer.save(to);
    }

    /**
     * 根据id删除物资分析
     *
     * @param id 物资分析唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        materialAnalyzeSer.remove(id);
    }

    /**
     * 更新物资分析
     *
     * @param to 物资分析to
     * @throws SerException
     */
    @Override
    public void update(MaterialAnalyzeTO to) throws SerException {
        materialAnalyzeSer.update(to);
    }

    /**
     * 物资分析
     *
     * @param inventoryType 物资分析类型
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    public List<MaterialAnalyzeBO> materialAnalyze(InventoryType inventoryType) throws SerException {
        return materialAnalyzeSer.materialAnalyze(inventoryType);
    }

    @Override
    public List<String> findAddAllDetails() throws SerException {
        return materialAnalyzeSer.findAddAllDetails();
    }

    @Override
    public List<String> findallMonUser() throws SerException {
        return materialAnalyzeSer.findallMonUser();
    }
}