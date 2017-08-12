package com.bjike.goddess.system.action.system;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.system.api.FeatureListAPI;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.vo.FeatureListVO;
import com.bjike.goddess.system.vo.FieldDockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("featurelist")
public class FeatureListAction extends BaseFileAction{
    @Autowired
    private FeatureListAPI featureListAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能列表总条数
     *
     * @param dto 功能列表记录dto
     * @des 获取所有功能列表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FeatureListDTO dto) throws ActException {
        try {
            Long count = featureListAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个功能列表
     *
     * @param id
     * @return class FeatureListVO
     * @des 获取一个功能列表
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO, FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 功能列表
     *
     * @param dto 功能列表记录dto
     * @return class FeatureListVO
     * @des 获取所有功能列表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FeatureListDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FeatureListVO> featureListVOS = BeanTransform.copyProperties(
                    featureListAPI.list(dto), FeatureListVO.class, request);
            return ActResult.initialize(featureListVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加功能列表
     *
     * @param to 功能列表to
     * @return class FeatureListVO
     * @des 添加功能列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(FeatureListTO to, BindingResult bindingResult) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO,FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑功能列表
     *
     * @param to 功能列表数据to
     * @return class FeatureListVO
     * @des 编辑功能列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(FeatureListTO to, BindingResult bindingResult) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO,FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除功能列表
     *
     * @param id 用户id
     * @des 根据用户id删除功能列表
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            featureListAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}