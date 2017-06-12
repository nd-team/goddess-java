package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.RecruitProAPI;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.dto.RecruitProDTO;
import com.bjike.goddess.recruit.to.RecruitProTO;
import com.bjike.goddess.recruit.type.AuditType;
import com.bjike.goddess.recruit.vo.RecruitProVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("recruitPro")
public class RecruitProAct {

    @Autowired
    private RecruitProAPI recruitProAPI;

    /**
     * 根据id查询招聘方案
     *
     * @param id 招聘方案唯一标识
     * @return class RecruitProVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/recruitPro/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RecruitProBO bo = recruitProAPI.findById(id);
            RecruitProVO vo = BeanTransform.copyProperties(bo, RecruitProVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 招聘方案dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RecruitProDTO dto, BindingResult result) throws ActException {
        try {
            Long count = recruitProAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 招聘方案dto
     * @return class RecruitProVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RecruitProDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RecruitProBO> boList = recruitProAPI.list(dto);
            List<RecruitProVO> voList = BeanTransform.copyProperties(boList, RecruitProVO.class, request);
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
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RecruitProTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RecruitProBO bo = recruitProAPI.save(to);
            RecruitProVO vo = BeanTransform.copyProperties(bo, RecruitProVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除招聘方案
     *
     * @param id 招聘方案唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
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
     * 补全信息
     *
     * @param to 招聘方案to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RecruitProTO to, BindingResult result) throws ActException {
        try {
            recruitProAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合资源部审核
     *
     * @param id 招聘方案唯一标识
     * @param zhOpinion 综合资源部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/zhOpinion/{id}")
    public Result zhOpinion(@PathVariable String id, @RequestParam(value = "zhOpinion") String zhOpinion) throws ActException {
        try {
            recruitProAPI.zhOpinion(id, zhOpinion);
            return new ActResult("zhOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部审核
     *
     * @param id 招聘方案唯一标识
     * @param yyOpinion 运营商务部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/yyOpinion/{id}")
    public Result yyOpinion(@PathVariable String id, @RequestParam(value = "yyOpinion") String yyOpinion) throws ActException {
        try {
            recruitProAPI.yyOpinion(id, yyOpinion);
            return new ActResult("yyOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办意见
     *
     * @param id 招聘方案唯一标识
     * @param zjbOpinion 总经办意见
     * @param auditType 审核类型
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/zjbOpinion/{id}")
    public Result zjbOpinion(@PathVariable String id, @RequestParam(value = "zjbOpinion") String zjbOpinion, @RequestParam(value = "auditType") AuditType auditType) throws ActException {
        try {
            recruitProAPI.zjbOpinion(id, zjbOpinion, auditType);
            return new ActResult("zjbOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
