package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.BeforeAddAPI;
import com.bjike.goddess.secure.bo.BeforeAddBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.dto.BeforeAddDTO;
import com.bjike.goddess.secure.to.BeforeAddTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.BeforeAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 增员前
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-27 10:10 ]
 * @Description: [ 增员前 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("beforeadd")
public class BeforeAddAct {
    @Autowired
    private BeforeAddAPI beforeAddAPI;

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

            Boolean isHasPermission = beforeAddAPI.guidePermission(guidePermissionTO);
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

//    /**
//     * 启动定时方法
//     *
//     * @throws ActException
//     * @version v1
//     */
//    @PostMapping("v1/quartz")
//    public Result quartz() throws ActException {
//        try {
//            beforeAddAPI.quartz();
//            return new ActResult("启动定时方法成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 添加
     *
     * @param to      增员前信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) BeforeAddTO to, HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补全信息
     *
     * @param to      增员前信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/complete")
    public Result complete(@Validated({BeforeAddTO.complete.class}) BeforeAddTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.complete(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 增员前信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BeforeAddTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            beforeAddAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     增员前分页信息
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BeforeAddDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BeforeAddBO> bo = beforeAddAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      增员前id
     * @param request 请求对象
     * @return class BeforeAddVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/beforeadd/{id}")
    public Result removeadd(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BeforeAddBO bo = beforeAddAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, BeforeAddVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 增员前id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            beforeAddAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审批，新增社保
     *
     * @param id 增员前id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/add/{id}")
    public Result add(@Validated(AddEmployeeDTO.CONFIRM.class) AddEmployeeDTO dto,@PathVariable String id) throws ActException {
        try {
            beforeAddAPI.add(dto,id);
            return new ActResult("审批通过");
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
    public Result count(BeforeAddDTO dto) throws ActException {
        try {
            return ActResult.initialize(beforeAddAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}