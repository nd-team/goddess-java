package com.bjike.goddess.businessevaluate.action.interiorevaluate;

import com.bjike.goddess.businessevaluate.api.EvaluateProjectInfoAPI;
import com.bjike.goddess.businessevaluate.api.interiorevaluate.ProjectPrincipalEvaluateAPI;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.to.interiorevaluate.ProjectPrincipalEvaluateTO;
import com.bjike.goddess.businessevaluate.vo.EvaluateProjectInfoVO;
import com.bjike.goddess.businessevaluate.vo.interiorevaluate.ProjectPrincipalEvaluateVO;
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
 * 项目负责人评价
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-30 01:55 ]
 * @Description: [ 项目负责人评价 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprincipal")
public class ProjectPrincipalEvaluateAct {

    @Autowired
    private ProjectPrincipalEvaluateAPI projectPrincipalEvaluateAPI;

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
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectPrincipalEvaluateDTO dto) throws ActException {
        try {
            Long count = projectPrincipalEvaluateAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目负责人评价
     *
     * @param id 项目负责人评价id
     * @return class ProjectPrincipalEvaluateVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectPrincipalEvaluateVO vo = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.findById(id), ProjectPrincipalEvaluateVO.class, request);
            if(vo ==null){
                throw new SerException("查询对象不存在!");
            }
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目负责人评价
     *
     * @param to 项目负责人评价
     * @return class ProjectPrincipalEvaluateVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectPrincipalEvaluateVO vo = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.addModel(to), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目负责人评价
     *
     * @param to 项目负责人评价
     * @return class ProjectPrincipalEvaluateVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectPrincipalEvaluateTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectPrincipalEvaluateVO vo = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.editModel(to), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目负责人评价
     *
     * @param id 项目负责人评价ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectPrincipalEvaluateAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询列表
     *
     * @param dto 分页条件
     * @return class ProjectPrincipalEvaluateVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ProjectPrincipalEvaluateDTO dto) throws ActException {
        try {
            List<ProjectPrincipalEvaluateVO> voList = BeanTransform.copyProperties(projectPrincipalEvaluateAPI.pageList(dto), ProjectPrincipalEvaluateVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}