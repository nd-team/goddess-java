package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ScoreFormulaSetBO;
import com.bjike.goddess.regularization.dto.ScoreFormulaSetDTO;
import com.bjike.goddess.regularization.entity.ScoreFormulaSet;
import com.bjike.goddess.regularization.to.ScoreFormulaSetTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作表现计分方式设置业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "regularizationSerCache")
@Service
public class ScoreFormulaSetSerImpl extends ServiceImpl<ScoreFormulaSet, ScoreFormulaSetDTO> implements ScoreFormulaSetSer {

    /**
     * 分页查询工作表现计分方式设置
     *
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public List<ScoreFormulaSetBO> list(ScoreFormulaSetDTO dto) throws SerException {
        List<ScoreFormulaSet> list = super.findByPage(dto);
        List<ScoreFormulaSetBO> listBO = BeanTransform.copyProperties(list, ScoreFormulaSetBO.class);
        return listBO;
    }

    /**
     * 保存工作表现计分方式设置
     *
     * @param to 工作表现计分方式设置to
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public ScoreFormulaSetBO save(ScoreFormulaSetTO to) throws SerException {
        ScoreFormulaSet entity = BeanTransform.copyProperties(to, ScoreFormulaSet.class, true);
        entity = super.save(entity);
        ScoreFormulaSetBO bo = BeanTransform.copyProperties(entity, ScoreFormulaSetBO.class);
        return bo;
    }

    /**
     * 更新工作表现计分方式设置
     *
     * @param to 工作表现计分方式设置to
     * @throws SerException
     */
    @Override
    public void update(ScoreFormulaSetTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ScoreFormulaSet model = super.findById(to.getId());
            if (model != null) {
                updateScoreFormulaSet(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新工作表现计分方式设置
     *
     * @param to 工作表现计分方式设置to
     * @param model
     * @throws SerException
     */
    private void updateScoreFormulaSet(ScoreFormulaSetTO to, ScoreFormulaSet model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除工作表现计分方式设置
     *
     * @param id 工作表现计分方式设置唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

}