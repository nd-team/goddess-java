package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.ProblesClassifyPrepareAPI;
import com.bjike.goddess.allmeeting.dto.ProblesClassifyPrepareDTO;
import com.bjike.goddess.allmeeting.to.ProblesClassifyPrepareTO;
import com.bjike.goddess.allmeeting.vo.ProblesClassifyPrepareVO;
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
 * 问题分类议题准备信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 05:44 ]
 * @Description: [ 问题分类议题准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("problesclassify")
public class ProblesClassifyPrepareAct {

    @Autowired
    private ProblesClassifyPrepareAPI problesClassifyPrepareAPI;


    /**
     * 新增问题分类
     *
     * @param to 问题分类
     * @return class ProblesClassifyPrepareVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProblesClassifyPrepareTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            ProblesClassifyPrepareVO voList = BeanTransform.copyProperties(problesClassifyPrepareAPI.add(to), ProblesClassifyPrepareVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑问题分类
     *
     * @param to 问题分类
     * @return class ProblesClassifyPrepareVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProblesClassifyPrepareTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProblesClassifyPrepareVO vo = BeanTransform.copyProperties(problesClassifyPrepareAPI.edit(to), ProblesClassifyPrepareVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结问题分类
     *
     * @param id 问题分类ID
     * @version v1
     */
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            problesClassifyPrepareAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class ProblesClassifyPrepareVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ProblesClassifyPrepareDTO dto) throws ActException {
        try {
            List<ProblesClassifyPrepareVO> voList = BeanTransform.copyProperties(problesClassifyPrepareAPI.pageList(dto), ProblesClassifyPrepareVO.class);
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
    public Result count(ProblesClassifyPrepareDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = problesClassifyPrepareAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询问题分类
     *
     * @param id 问题分类id
     * @return class ProblesClassifyPrepareVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProblesClassifyPrepareVO vo = BeanTransform.copyProperties(problesClassifyPrepareAPI.findById(id), ProblesClassifyPrepareVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}