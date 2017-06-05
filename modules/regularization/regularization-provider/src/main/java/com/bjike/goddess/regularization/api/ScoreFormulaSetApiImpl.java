package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.bo.ScoreFormulaSetBO;
import com.bjike.goddess.regularization.dto.ScoreFormulaSetDTO;
import com.bjike.goddess.regularization.entity.ScoreFormulaSet;
import com.bjike.goddess.regularization.service.ScoreFormulaSetSer;
import com.bjike.goddess.regularization.to.ScoreFormulaSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作表现计分方式设置业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("scoreFormulaSetApiImpl")
public class ScoreFormulaSetApiImpl implements ScoreFormulaSetAPI {

    @Autowired
    private ScoreFormulaSetSer scoreFormulaSetSer;

    /**
     * 根据id查询工作表现计分方式设置
     *
     * @param id 工作表现计分方式设置唯一标识
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public ScoreFormulaSetBO findById(String id) throws SerException {
        ScoreFormulaSet model = scoreFormulaSetSer.findById(id);
        return BeanTransform.copyProperties(model, ScoreFormulaSetBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 工作表现计分方式设置dto
     * @throws SerException
     */
    @Override
    public Long count(ScoreFormulaSetDTO dto) throws SerException {
        return scoreFormulaSetSer.count(dto);
    }

    /**
     * 分页查询工作表现计分方式
     *
     * @return class ScoreFormulaSetBO
     * @throws SerException
     */
    @Override
    public List<ScoreFormulaSetBO> list(ScoreFormulaSetDTO dto) throws SerException {
        return scoreFormulaSetSer.list(dto);
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
        return scoreFormulaSetSer.save(to);
    }

    /**
     * 根据id删除工作表现计分方式
     *
     * @param id 工作表现计分方式唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        scoreFormulaSetSer.remove(id);
    }

    /**
     * 更新工作表现计分方式
     *
     * @param to 工作表现计分方式to
     * @throws SerException
     */
    @Override
    public void update(ScoreFormulaSetTO to) throws SerException {
        scoreFormulaSetSer.update(to);
    }
}