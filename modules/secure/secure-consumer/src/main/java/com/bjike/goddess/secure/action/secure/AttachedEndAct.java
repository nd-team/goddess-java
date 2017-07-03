package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.AttachedEndAPI;
import com.bjike.goddess.secure.bo.AttachedEndBO;
import com.bjike.goddess.secure.dto.AttachedEndDTO;
import com.bjike.goddess.secure.to.AttachedEndTO;
import com.bjike.goddess.secure.vo.AttachedEndVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 挂靠到期
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:04 ]
 * @Description: [ 挂靠到期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attachedEnd")
public class AttachedEndAct {
    @Autowired
    private AttachedEndAPI attachedEndAPI;

    /**
     * 启动定时方法
     *
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/quartz")
    public Result quartz() throws ActException {
        try {
            attachedEndAPI.quartz();
            return new ActResult("启动定时方法成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     挂靠到期分页信息
     * @param request 请求对象
     * @return class AttachedEndVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result find(AttachedEndDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AttachedEndBO> list = attachedEndAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AttachedEndVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否继续挂靠
     *
     * @param to      挂靠到期信息
     * @param request 请求对象
     * @return class AttachedEndVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/isAgain")
    public Result is_again(@Validated({EDIT.class}) AttachedEndTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AttachedEndBO attachedEndBO = attachedEndAPI.is_Again(to);
            return ActResult.initialize(BeanTransform.copyProperties(attachedEndBO, AttachedEndVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 挂靠到期id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            attachedEndAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      挂靠到期id
     * @param request 请求对象
     * @return class AttachedEndVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/attachedEnd/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AttachedEndBO attachedEndBO = attachedEndAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(attachedEndBO, AttachedEndVO.class));
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
    public Result count(AttachedEndDTO dto) throws ActException {
        try {
            return ActResult.initialize(attachedEndAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}