package com.bjike.goddess.regularization.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.regularization.bo.ScoreFormulaSetBO;
import com.bjike.goddess.regularization.dto.ScoreFormulaSetDTO;
import com.bjike.goddess.regularization.entity.ScoreFormulaSet;
import com.bjike.goddess.regularization.to.ScoreFormulaSetTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
     * 分页查询工作表现计分方式
     *
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public List<ScoreFormulaSetBO> list(ScoreFormulaSetDTO dto) throws SerException {
        return null;
    }

    /**
     * 保存工作表现计分方式
     *
     * @param to 工作表现计分方式to
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public ScoreFormulaSetBO save(ScoreFormulaSetTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除工作表现计分方式
     *
     * @param id 工作表现计分方式唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新工作表现计分方式
     *
     * @param to 工作表现计分方式to
     * @throws SerException
     */
    @Override
    public void update(ScoreFormulaSetTO to) throws SerException {

    }
}