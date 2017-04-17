package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.ScoreFormulaSetAPI;
import com.bjike.goddess.regularization.bo.ScoreFormulaSetBO;
import com.bjike.goddess.regularization.dto.ScoreFormulaSetDTO;
import com.bjike.goddess.regularization.to.ScoreFormulaSetTO;
import com.bjike.goddess.regularization.vo.ScoreFormulaSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 工作表现计分方式设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:47 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("scoreformulaset")
public class ScoreFormulaSetAct {

    @Autowired
    private ScoreFormulaSetAPI scoreFormulaSetAPI;

    /**
     * 分页查询工作表现计分方式设置
     *
     * @param dto 工作表现计分方式设置dto
     * @return class ScoreFormulaSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ScoreFormulaSetDTO dto) throws ActException {
        try {
            List<ScoreFormulaSetBO> boList = scoreFormulaSetAPI.list(dto);
            List<ScoreFormulaSetVO> voList = BeanTransform.copyProperties(boList, ScoreFormulaSetVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作表现计分方式设置
     *
     * @param to 工作表现计分方式设置to
     * @return class ScoreFormulaSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ScoreFormulaSetTO to, BindingResult result) throws ActException {
        try {
            ScoreFormulaSetBO bo = scoreFormulaSetAPI.save(to);
            ScoreFormulaSetVO vo = BeanTransform.copyProperties(bo, ScoreFormulaSetVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除工作表现计分方式设置
     *
     * @param id 工作表现计分方式设置唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            scoreFormulaSetAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作表现计分方式设置
     *
     * @param to 工作表现计分方式设置to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(ScoreFormulaSetTO to) throws ActException {
        try {
            scoreFormulaSetAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}