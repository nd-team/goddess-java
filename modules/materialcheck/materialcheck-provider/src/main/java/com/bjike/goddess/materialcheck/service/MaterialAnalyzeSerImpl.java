package com.bjike.goddess.materialcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.bo.MaterialAnalyzeBO;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.entity.MaterialAnalyze;
import com.bjike.goddess.materialcheck.to.MaterialAnalyzeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 物资分析业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialcheckSerCache")
@Service
public class MaterialAnalyzeSerImpl extends ServiceImpl<MaterialAnalyze, MaterialAnalyzeDTO> implements MaterialAnalyzeSer {

    /**
     * 分页查询物资分析
     *
     * @param dto 物资分析dto
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<MaterialAnalyzeBO> list(MaterialAnalyzeDTO dto) throws SerException {
        List<MaterialAnalyze> list = super.findByPage(dto);
        List<MaterialAnalyzeBO> boList = BeanTransform.copyProperties(list, MaterialAnalyzeBO.class);
        return boList;
    }

    /**
     * 保存物资分析
     *
     * @param to 物资分析to
     * @return class MaterialAnalyzeBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MaterialAnalyzeBO save(MaterialAnalyzeTO to) throws SerException {
        MaterialAnalyze entity = BeanTransform.copyProperties(to, MaterialAnalyze.class, true);
        entity = super.save(entity);
        MaterialAnalyzeBO bo = BeanTransform.copyProperties(entity, MaterialAnalyzeBO.class);
        return bo;
    }

    /**
     * 根据id删除物资分析
     *
     * @param id 物资分析唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新物资分析
     *
     * @param to 物资分析to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MaterialAnalyzeTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            MaterialAnalyze model = super.findById(to.getId());
            if (model != null) {
                updateMaterialAnalyze(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新物资分析
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMaterialAnalyze(MaterialAnalyzeTO to, MaterialAnalyze model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

}