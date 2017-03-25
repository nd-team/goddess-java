package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.RecruitProAPI;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.vo.RecruitProVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 17:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruit/recruitPro")
public class RecruitProAct {

    @Autowired
    private RecruitProAPI recruitProAPI;

    /**
     * 获取列表
     *
     * @param dto 招聘方案传输对象
     * @return class NotEntryReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RecruitProDTO dto) throws ActException {
        try {
            List<RecruitProBO> boList = recruitProAPI.list(dto);
            List<RecruitProVO> voList = BeanTransform.copyProperties(boList, RecruitProVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招聘方案
     *
     * @param to 招聘方案to信息
     * @return class RecruitProVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RecruitProTO to) throws ActException {
        try {
            RecruitProBO bo = recruitProAPI.save(to);
            RecruitProVO vo = BeanTransform.copyProperties(bo, RecruitProVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招聘方案
     *
     * @param id 招聘方案唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            recruitProAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招聘方案
     *
     * @param to 招聘方案to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) RecruitProTO to) throws ActException {
        try {
            recruitProAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部审核
     *
     * @param to 招聘方案to信息
     * @param pass 是否通过
     * @throws ActException
     */
    @PutMapping("v1/yyEdit")
    public Result yyEdit(@Validated({EDIT.class}) RecruitProTO to,Boolean pass) throws ActException {
        try {
            recruitProAPI.yyEdit(to, pass);
            return new ActResult("yyEdit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param to 招聘方案to信息
     * @param pass 是否通过
     * @throws ActException
     */
    @PutMapping("v1/managerEdit")
    public Result managerEdit(@Validated({EDIT.class}) RecruitProTO to, Boolean pass) throws ActException {
        try {
            recruitProAPI.managerEdit(to, pass);
            return new ActResult("managerEdit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
