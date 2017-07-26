package com.bjike.goddess.managementpromotion.action.managementpromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managementpromotion.api.GradeLevelAPI;
import com.bjike.goddess.managementpromotion.bo.GradeLevelBO;
import com.bjike.goddess.managementpromotion.dto.GradeLevelDTO;
import com.bjike.goddess.managementpromotion.to.GradeLevelTO;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.vo.GradeLevelVO;
import com.bjike.goddess.managementpromotion.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 管理等级定级
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 01:57 ]
 * @Description: [ 管理等级定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("gradelevel")
public class GradeLevelAct {
    @Autowired
    private GradeLevelAPI gradeLevelAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = gradeLevelAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = gradeLevelAPI.guidePermission(guidePermissionTO);
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
     * 管理等级定级列表总条数
     *
     * @param dto 管理等级定级dto
     * @des 获取所有管理等级定级总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(GradeLevelDTO dto) throws ActException {
        try {
            Long count = gradeLevelAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个管理等级定级
     *
     * @param id id
     * @return class GradeLevelVO
     * @des 获取一个管理等级定级
     * @version v1
     */
    @GetMapping("v1/gradelevel/{id}")
    public Result gradelevel(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            GradeLevelBO bo = gradeLevelAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, GradeLevelVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理等级定级列表
     *
     * @param dto 管理等级定级信息dto
     * @return class GradeLevelVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(GradeLevelDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<GradeLevelVO> VOS = BeanTransform.copyProperties
                    (gradeLevelAPI.find(dto), GradeLevelVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加管理等级定级信息
     *
     * @param to 管理等级定级信息数据to
     * @return class GradeLevelVO
     * @version v1
     */
    //  @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) GradeLevelTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            GradeLevelBO bo = gradeLevelAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, GradeLevelVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑管理等级定级信息
     *
     * @param to 管理等级定级信息数据to
     * @return class GradeLevelVO
     * @des 编辑管理等级定级信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) GradeLevelTO to, BindingResult bindingResult) throws ActException {
        try {
            gradeLevelAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除管理等级定级信息
     *
     * @param id 用户id
     * @des 根据用户id删除管理等级定级信息记录
     * @version v1
     */
    // @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            gradeLevelAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有体系
     *
     * @throws ActException
     * @version v1
     */
        @GetMapping("v1/allHierarchys")
    public Result allHierarchys() throws ActException {
        try {
            Set<String> set = gradeLevelAPI.allHierarchys();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}