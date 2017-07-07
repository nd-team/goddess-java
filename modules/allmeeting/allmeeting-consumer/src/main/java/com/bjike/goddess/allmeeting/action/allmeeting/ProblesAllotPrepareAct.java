package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.ProblesAllotPrepareAPI;
import com.bjike.goddess.allmeeting.dto.ProblesAllotPrepareDTO;
import com.bjike.goddess.allmeeting.to.ProblesAllotPrepareTO;
import com.bjike.goddess.allmeeting.vo.ProblesAllotPrepareVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 问题分配责任模块议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 04:55 ]
 * @Description: [ 问题分配责任模块议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problesallot")
public class ProblesAllotPrepareAct {

    @Autowired
    private ProblesAllotPrepareAPI problesAllotPrepareAPI;


    /**
     * 新增工作汇总议题准备
     *
     * @param to 工作汇总议题准备
     * @return class ProblesAllotPrepareVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProblesAllotPrepareTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblesAllotPrepareVO voList = BeanTransform.copyProperties(problesAllotPrepareAPI.add(to), ProblesAllotPrepareVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作汇总议题准备
     *
     * @param to 工作汇总议题准备
     * @return class ProblesAllotPrepareVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProblesAllotPrepareTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblesAllotPrepareVO vo = BeanTransform.copyProperties(problesAllotPrepareAPI.edit(to), ProblesAllotPrepareVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 工作汇总议题准备ID
     * @version v1
     */
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            problesAllotPrepareAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class ProblesAllotPrepareVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ProblesAllotPrepareDTO dto) throws ActException {
        try {
            List<ProblesAllotPrepareVO> voList = BeanTransform.copyProperties(problesAllotPrepareAPI.pageList(dto), ProblesAllotPrepareVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProblesAllotPrepareDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = problesAllotPrepareAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询工作汇总议题准备
     *
     * @param id 工作汇总议题准备id
     * @return class ProblesAllotPrepareVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProblesAllotPrepareVO vo = BeanTransform.copyProperties(problesAllotPrepareAPI.findById(id), ProblesAllotPrepareVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}