package com.bjike.goddess.businessevaluate.action.evaluatecollect;

import com.bjike.goddess.businessevaluate.api.BusinessEvaluateCollectAPI;
import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.dto.BusinessEvaluateCollectDTO;
import com.bjike.goddess.businessevaluate.to.BusinessEvaluateCollectTO;
import com.bjike.goddess.businessevaluate.vo.*;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
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
 * 商务评估汇总
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 03:13 ]
 * @Description: [ 商务评估汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class BusinessEvaluateCollectAct {

    @Autowired
    private BusinessEvaluateCollectAPI businessEvaluateCollectAPI;

    @Autowired
    private EvaluateProjectInfoAPI evaluateProjectInfoAPI;

    /**
     * 查询所有项目
     *
     * @return class EvaluateProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/porjects")
    public Result porjects(HttpServletRequest request) throws ActException {
        try {
            List<EvaluateProjectInfoVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAll(), EvaluateProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有地区
     *
     * @return class AreasVO
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result ares(HttpServletRequest request) throws ActException {
        try {
            List<AreasVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAllArea(),AreasVO.class,request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有不重复项目名称
     *
     * @return class ProjectsVO
     * @version v1
     */
    @GetMapping("v1/allproject")
    public Result allproject(HttpServletRequest request) throws ActException {
        try {
            List<ProjectsVO> voList = BeanTransform.copyProperties(evaluateProjectInfoAPI.findAllProejct(),ProjectsVO.class,request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessEvaluateCollectDTO dto) throws ActException {
        try {
            Long count = businessEvaluateCollectAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询汇总定时器
     *
     * @param id 汇总条件id
     * @return class BusinessEvaluateCollectVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BusinessEvaluateCollectVO vo = BeanTransform.copyProperties(businessEvaluateCollectAPI.findById(id), BusinessEvaluateCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增商务评估汇总
     *
     * @param to 商务评估汇总
     * @return class BusinessEvaluateCollectVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BusinessEvaluateCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            BusinessEvaluateCollectVO vo = BeanTransform.copyProperties(businessEvaluateCollectAPI.addModel(to), BusinessEvaluateCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑商务评估汇总
     *
     * @param to 商务评估汇总
     * @return class BusinessEvaluateCollectVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BusinessEvaluateCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            BusinessEvaluateCollectVO vo = BeanTransform.copyProperties(businessEvaluateCollectAPI.editModel(to), BusinessEvaluateCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结商务评估汇总
     *
     * @param id 商务评估汇总ID
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.freeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻商务评估汇总
     *
     * @param id 商务评估汇总ID
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.breakFreeze(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除商务评估汇总
     *
     * @param id 竞争对手ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessEvaluateCollectAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class BusinessEvaluateCollectVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(BusinessEvaluateCollectDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BusinessEvaluateCollectVO> voList = BeanTransform.copyProperties(businessEvaluateCollectAPI.pageList(dto), BusinessEvaluateCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总详情
     *
     * @param area    地区
     * @param project 项目
     * @return class EvaluateCollectTotalVO
     * @version v1
     */
    @GetMapping("v1/total")
    public Result collectionTotal(String area, String project, HttpServletRequest request) throws ActException {
        try {
            List<EvaluateCollectTotalVO> voList = BeanTransform.copyProperties(businessEvaluateCollectAPI.collectionTotal(area, project), EvaluateCollectTotalVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}