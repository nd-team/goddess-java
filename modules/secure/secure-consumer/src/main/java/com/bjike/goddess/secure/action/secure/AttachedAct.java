package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.AttachedAPI;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.AttachedDTO;
import com.bjike.goddess.secure.to.AttachedTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.AttachedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 挂靠
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:12 ]
 * @Description: [ 挂靠 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attached")
public class AttachedAct {
    @Autowired
    private AttachedAPI attachedAPI;

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

            Boolean isHasPermission = attachedAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to 挂靠信息
     * @return class AttachedVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) AttachedTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AttachedBO bo = attachedAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AttachedVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补全信息
     *
     * @param to 挂靠信息
     * @return class AttachedVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/complete")
    public Result complete(@Validated({AttachedTO.COM.class}) AttachedTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AttachedBO bo = attachedAPI.complete(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AttachedVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 挂靠信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AttachedTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AttachedBO bo = attachedAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AttachedVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除记录
     *
     * @param id 挂靠信息id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            attachedAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto 挂靠分页信息
     * @return class AttachedVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(AttachedDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AttachedBO> list = attachedAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AttachedVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 挂靠信息id
     * @return class AttachedVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/attached/{id}")
    public Result findByID(@PathVariable String id) throws ActException {
        try {
            AttachedBO bo = attachedAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AttachedVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核通过
     *
     * @param id  挂靠id
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/pass/{id}")
    public Result pass(@Validated(AddEmployeeDTO.CONFIRM.class) AddEmployeeDTO dto, BindingResult result, @PathVariable String id) throws ActException {
        try {
            attachedAPI.pass(dto, id);
            return new ActResult("审核通过");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核不通过
     *
     * @param id  挂靠id
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/notPass/{id}")
    public Result notPass(@Validated(AddEmployeeDTO.CONFIRM.class) AddEmployeeDTO dto, BindingResult result, @PathVariable String id) throws ActException {
        try {
            attachedAPI.notPass(dto, id);
            return new ActResult("审核不通过");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AttachedDTO dto) throws ActException {
        try {
            return ActResult.initialize(attachedAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}