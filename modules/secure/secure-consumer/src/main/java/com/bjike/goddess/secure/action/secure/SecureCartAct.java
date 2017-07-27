package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.SecureCartAPI;
import com.bjike.goddess.secure.bo.SecureCartBO;
import com.bjike.goddess.secure.dto.SecureCartDTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.SecureCartTO;
import com.bjike.goddess.secure.vo.SecureCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 社保卡基本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-25 01:44 ]
 * @Description: [ 社保卡基本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("securecart")
public class SecureCartAct {
    @Autowired
    private SecureCartAPI secureCartAPI;

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

            Boolean isHasPermission = secureCartAPI.guidePermission(guidePermissionTO);
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
     * @param to 社保卡基本信息
     * @return class SecureCartVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) SecureCartTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SecureCartBO bo = secureCartAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SecureCartVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     社保卡分页信息
     * @param request 请求对象
     * @return class SecureCartVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SecureCartDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SecureCartBO> list = secureCartAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, SecureCartVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 社保卡信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SecureCartTO to, BindingResult result) throws ActException {
        try {
            secureCartAPI.edit(to);
            return new ActResult("更新成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 社保卡id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            secureCartAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      社保卡id
     * @param request 请求对象
     * @return class SecureCartVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/securecart/{id}")
    public Result securecart(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SecureCartBO bo = secureCartAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SecureCartVO.class, request));
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
    public Result count(SecureCartDTO dto) throws ActException {
        try {
            return ActResult.initialize(secureCartAPI.count(dto));
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
//            secureCartAPI.quartz();
//            return new ActResult("启动定时方法成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}