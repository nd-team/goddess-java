package com.bjike.goddess.projectroyalty.action.projectroyalty;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.to.ProjectFlatTO;
import com.bjike.goddess.projectroyalty.api.ProjectFactorsAPI;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.ProjectFactorsTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;
import com.bjike.goddess.projectroyalty.vo.ProjectFactorsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目提成分配因素
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectfactors")
public class ProjectFactorsAction {
    @Autowired
    private ProjectFactorsAPI projectFactorsAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = projectFactorsAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 保存项目提成分配因素
     *
     * @param to 项目提成分配因素传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ProjectFactorsTO to, BindingResult result) throws ActException {
        try {
            projectFactorsAPI.save(to);
            return ActResult.initialize("SAVE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 项目提成分配因素传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ProjectFactorsTO to, BindingResult result) throws ActException {
        try {
            projectFactorsAPI.update(to);
            return ActResult.initialize("UPDATE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目提成分配因素数据id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectFactorsAPI.delete(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取业务提成定额数据
     *
     * @param id 项目提成分配因素数据id
     * @return class ProjectFactorsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(projectFactorsAPI.getById(id), ProjectFactorsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成分配因素列表
     *
     * @param dto 项目提成分配因素数据传输对象
     * @return class ProjectFactorsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ProjectFactorsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(projectFactorsAPI.maps(dto), ProjectFactorsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目提成分配因素总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(ProjectFactorsDTO dto) throws ActException {
        try {
            return ActResult.initialize(projectFactorsAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}