package com.bjike.goddess.firmreward.action.firmreward;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.api.BonusBudgetAPI;
import com.bjike.goddess.firmreward.bo.BonusBudgetBO;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.BonusBudgetDTO;
import com.bjike.goddess.firmreward.to.BonusBudgetTO;
import com.bjike.goddess.firmreward.vo.BonusBudgetVO;
import com.bjike.goddess.firmreward.vo.RewardProgramRatioVO;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 奖金预算
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bonusbudget")
public class BonusBudgetAct {

    @Autowired
    private BonusBudgetAPI bonusBudgetAPI;

    /**
     * 分页查询奖金预算
     *
     * @param dto 奖金预算dto
     * @return class BonusBudgetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated BonusBudgetDTO dto, BindingResult result) throws ActException {
        try {
            List<BonusBudgetBO> boList = bonusBudgetAPI.list(dto);
            List<BonusBudgetVO> voList = BeanTransform.copyProperties(boList, BonusBudgetVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加奖金预算
     *
     * @param to 奖金预算to
     * @return class BonusBudgetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BonusBudgetTO to, BindingResult result) throws ActException {
        try {
            BonusBudgetBO bo = bonusBudgetAPI.save(to);
            BonusBudgetVO vo = BeanTransform.copyProperties(bo, BonusBudgetVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除奖金预算
     *
     * @param id 奖金预算唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bonusBudgetAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑奖金预算
     *
     * @param to 奖金预算to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated BonusBudgetTO to, BindingResult result) throws ActException {
        try {
            bonusBudgetAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加奖励项目比例
     *
     * @param to 奖金预算to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/addRewardProgramRatios")
    public Result addRewardProgramRatios(@Validated BonusBudgetTO to, BindingResult result) throws ActException {
        try {
            bonusBudgetAPI.addRewardProgramRatios(to);
            return new ActResult("addRewardProgramRatios success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 更新奖励项目比例
     *
     * @param to 奖金预算to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/updateRewardProgramRatios")
    public Result updateRewardProgramRatios(@Validated BonusBudgetTO to, BindingResult result) throws ActException {
        try {
            bonusBudgetAPI.updateRewardProgramRatios(to);
            return new ActResult("updateRewardProgramRatios success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看奖励项目比例
     *
     * @param ratioId 奖励项目id
     * @return class RewardProgramRatioVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkRewardProgramRatios/{ratioId}")
    public Result checkRewardProgramRatios(@Validated String ratioId, BindingResult result) throws ActException {
        try {
            List<RewardProgramRatioBO> boList = bonusBudgetAPI.checkRewardProgramRatios(ratioId);
            List<RewardProgramRatioVO> voList = BeanTransform.copyProperties(boList, RewardProgramRatioVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}