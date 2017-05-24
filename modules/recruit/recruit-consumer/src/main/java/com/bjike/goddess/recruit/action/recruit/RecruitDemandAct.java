package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.RecruitDemandAPI;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import com.bjike.goddess.recruit.vo.RecruitDemandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruitDemand")
public class RecruitDemandAct {

    @Autowired
    private RecruitDemandAPI recruitDemandAPI;

    /**
     * 根据id查询招聘需求
     *
     * @param id 招聘需求唯一标识
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/recruitDemand/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitDemandBO bo = recruitDemandAPI.findById(id);
            RecruitDemandVO vo = BeanTransform.copyProperties(bo, RecruitDemandVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘需求dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RecruitDemandDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitDemandAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘需求传输对象
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitDemandDTO dto) throws ActException {
        try {
            List<RecruitDemandBO> boList = recruitDemandAPI.list(dto);
            List<RecruitDemandVO> voList = BeanTransform.copyProperties(boList, RecruitDemandVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘需求
     *
     * @param to 招聘需求to信息
     * @return class RecruitDemandVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitDemandTO to) throws ActException {
        try {
            RecruitDemandBO bo = recruitDemandAPI.save(to);
            RecruitDemandVO vo = BeanTransform.copyProperties(bo, RecruitDemandVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招聘需求
     *
     * @param id 招聘需求唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recruitDemandAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘需求
     *
     * @param to 招聘需求to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitDemandTO to) throws ActException {
        try {
            recruitDemandAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
