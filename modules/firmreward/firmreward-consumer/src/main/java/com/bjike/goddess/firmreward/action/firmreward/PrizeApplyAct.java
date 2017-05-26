package com.bjike.goddess.firmreward.action.firmreward;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.api.PrizeApplyAPI;
import com.bjike.goddess.firmreward.bo.*;
import com.bjike.goddess.firmreward.dto.PrizeApplyDTO;
import com.bjike.goddess.firmreward.to.PrizeApplyTO;
import com.bjike.goddess.firmreward.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 奖品申请
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("prizeapply")
public class PrizeApplyAct {

    @Autowired
    private PrizeApplyAPI prizeApplyAPI;

    /**
     * 根据id查询奖金预算
     *
     * @param id 奖品申请唯一标识
     * @return class PrizeApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/bonusbudget/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            PrizeApplyBO bo = prizeApplyAPI.findById(id);
            PrizeApplyVO vo = BeanTransform.copyProperties(bo, PrizeApplyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 奖品申请dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated PrizeApplyDTO dto, BindingResult result) throws ActException {
        try {
            Long count = prizeApplyAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询奖品申请
     *
     * @param dto 奖品申请dto
     * @return class PrizeApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated PrizeApplyDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<PrizeApplyBO> boList = prizeApplyAPI.list(dto);
            List<PrizeApplyVO> voList = BeanTransform.copyProperties(boList, PrizeApplyVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加奖品申请
     *
     * @param to 奖品申请to
     * @return class PrizeApplyVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) PrizeApplyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            PrizeApplyBO bo = prizeApplyAPI.save(to);
            PrizeApplyVO vo = BeanTransform.copyProperties(bo, PrizeApplyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除奖品申请
     *
     * @param id 奖品申请唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            prizeApplyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑奖品申请
     *
     * @param to 奖品申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated PrizeApplyTO to, BindingResult result) throws ActException {
        try {
            prizeApplyAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加奖品明细
     *
     * @param to 奖品申请to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/addPrizeDetails")
    public Result addPrizeDetails(@Validated PrizeApplyTO to, BindingResult result) throws ActException {
        try {
            prizeApplyAPI.addPrizeDetails(to);
            return new ActResult("addPrizeDetails success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 更新奖品明细
     *
     * @param to 奖品申请
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/updatePrizeDetails")
    public Result updatePrizeDetails(@Validated PrizeApplyTO to, BindingResult result) throws ActException {
        try {
            prizeApplyAPI.updatePrizeDetails(to);
            return new ActResult("updatePrizeDetails success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看奖品明细
     *
     * @param applyId 奖品申请id
     * @return class PrizeDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkPrizeDetails")
    public Result checkPrizeDetails(String applyId, HttpServletRequest request) throws ActException {
        try {
            List<PrizeDetailBO> boList = prizeApplyAPI.checkPrizeDetails(applyId);
            List<PrizeDetailVO> voList = BeanTransform.copyProperties(boList, PrizeDetailVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工奖励汇总
     *
     * @return class StaffRewardCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/staffRewardCollect")
    public Result staffRewardCollect(HttpServletRequest request) throws ActException {
        try {
            List<StaffRewardCollectBO> boList = prizeApplyAPI.staffRewardCollect();
            List<StaffRewardCollectVO> voList = BeanTransform.copyProperties(boList, StaffRewardCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组奖励汇总
     *
     * @return class ProjectGroupRewardCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroupRewardCollect")
    public Result projectGroupRewardCollect(HttpServletRequest request) throws ActException {
        try {
            List<ProjectGroupRewardCollectBO> boList = prizeApplyAPI.projectGroupRewardCollect();
            List<ProjectGroupRewardCollectVO> voList = BeanTransform.copyProperties(boList, ProjectGroupRewardCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区奖励汇总
     *
     * @return class AreaRewardCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaRewardCollect")
    public Result areaRewardCollect(HttpServletRequest request) throws ActException {
        try {
            List<AreaRewardCollectBO> boList = prizeApplyAPI.areaRewardCollect();
            List<AreaRewardCollectVO> voList = BeanTransform.copyProperties(boList, AreaRewardCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}