package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.DrawRegisterAPI;
import com.bjike.goddess.secure.bo.DrawRegisterBO;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.entity.DrawRegister;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.DrawRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 社保卡领取登记表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("drawregister")
public class DrawRegisterAction {
    @Autowired
    private DrawRegisterAPI drawRegisterAPI;

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

            Boolean isHasPermission = drawRegisterAPI.guidePermission(guidePermissionTO);
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
     * @param to      社保卡领取登记表
     * @param request 请求对象
     * @return class DrawRegisterVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) DrawRegisterTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DrawRegisterBO bo = drawRegisterAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DrawRegisterVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 社保卡领取登记表
     * @return class DrawRegisterVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DrawRegisterTO to, BindingResult result) throws ActException {
        try {
            DrawRegisterBO bo = drawRegisterAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DrawRegisterVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 社保卡领取登记表id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            drawRegisterAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     社保卡领取登记表
     * @param request 请求对象
     * @return class DrawRegisterVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DrawRegisterDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DrawRegisterBO> list = drawRegisterAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DrawRegisterVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      社保卡领取登记表id
     * @param request 请求对象
     * @return class DrawRegisterVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/draw/{id}")
    public Result draw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DrawRegisterBO bo = drawRegisterAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DrawRegisterVO.class, request));
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
    public Result count(DrawRegisterDTO dto) throws ActException {
        try {
            return ActResult.initialize(drawRegisterAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 负责人审核
     *
     * @param to 社保卡领取登记表
     * @return class DrawRegisterVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/charge")
    public Result charge(@Validated({DrawRegisterTO.charge.class}) DrawRegisterTO to, BindingResult result) throws ActException {
        try {
            DrawRegisterBO bo = drawRegisterAPI.chargeAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DrawRegisterVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}