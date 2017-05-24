package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.RecruitWayAPI;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import com.bjike.goddess.recruit.vo.NotEntryReasonVO;
import com.bjike.goddess.recruit.vo.RecruitWayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruitWay")
public class RecruitWayAct {

    @Autowired
    private RecruitWayAPI recruitWayAPI;

    /**
     * 根据id查询招聘渠道
     *
     * @param id 招聘渠道唯一标识
     * @return class RecruitWayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/recruitWay/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitWayBO bo = recruitWayAPI.findById(id);
            RecruitWayVO vo = BeanTransform.copyProperties(bo, RecruitWayVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘渠道dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RecruitWayDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitWayAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘渠道传输对象
     * @return class RecruitWayVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitWayDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RecruitWayBO> boList = recruitWayAPI.list(dto);
            List<RecruitWayVO> voList = BeanTransform.copyProperties(boList, RecruitWayVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘渠道
     *
     * @param to 招聘渠道to信息
     * @return class RecruitWayVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitWayTO to, HttpServletRequest request) throws ActException {
        try {
            RecruitWayBO bo = recruitWayAPI.save(to);
            RecruitWayVO vo = BeanTransform.copyProperties(bo, RecruitWayVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招聘渠道
     *
     * @param id 招聘渠道唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recruitWayAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘渠道
     *
     * @param to 招聘渠道to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitWayTO to) throws ActException {
        try {
            recruitWayAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
