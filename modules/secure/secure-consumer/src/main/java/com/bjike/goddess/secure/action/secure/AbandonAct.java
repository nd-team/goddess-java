package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.AbandonAPI;
import com.bjike.goddess.secure.bo.AbandonBO;
import com.bjike.goddess.secure.dto.AbandonDTO;
import com.bjike.goddess.secure.to.AbandonTO;
import com.bjike.goddess.secure.vo.AbandonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 放弃购买名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:52 ]
 * @Description: [ 放弃购买名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("abandon")
public class AbandonAct {
    @Autowired
    private AbandonAPI abandonAPI;

    /**
     * 查找
     *
     * @param dto     放弃购买分页信息
     * @param request 请求对象
     * @return class AbandonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(AbandonDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AbandonBO> list = abandonAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AbandonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      放弃购买id
     * @param request 请求对象
     * @return class AbandonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/abandon/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AbandonBO bo = abandonAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AbandonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 放弃信息
     * @return class AbandonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) AbandonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AbandonBO bo = abandonAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AbandonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      放弃名单信息
     * @param request 请求对象
     * @return class AbandonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AbandonTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AbandonBO bo = abandonAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AbandonVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 放弃名单id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            abandonAPI.delete(id);
            return new ActResult("delete SUCCESS!");
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
    public Result count(AbandonDTO dto) throws ActException {
        try {
            return ActResult.initialize(abandonAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}