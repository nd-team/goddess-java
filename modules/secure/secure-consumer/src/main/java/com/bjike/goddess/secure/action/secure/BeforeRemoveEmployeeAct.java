package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.BeforeRemoveEmployeeAPI;
import com.bjike.goddess.secure.bo.BeforeRemoveEmployeeBO;
import com.bjike.goddess.secure.dto.BeforeRemoveEmployeeDTO;
import com.bjike.goddess.secure.to.BeforeRemoveEmployeeTO;
import com.bjike.goddess.secure.vo.BeforeRemoveEmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 减员前
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:45 ]
 * @Description: [ 减员前 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("before-remove-employee")
public class BeforeRemoveEmployeeAct {
    @Autowired
    private BeforeRemoveEmployeeAPI beforeRemoveEmployeeAPI;

    /**
     * 查找
     *
     * @param dto     减员前分页信息
     * @param request 请求对象
     * @return class BeforeRemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(BeforeRemoveEmployeeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BeforeRemoveEmployeeBO> list = beforeRemoveEmployeeAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, BeforeRemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to      减员前信息
     * @param request 请求对象
     * @return class BeforeRemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) BeforeRemoveEmployeeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            BeforeRemoveEmployeeBO beforeRemoveEmployeeBO = beforeRemoveEmployeeAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(beforeRemoveEmployeeBO, BeforeRemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      减员前id
     * @param request 请求对象
     * @return class BeforeRemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/before-remove-employee/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BeforeRemoveEmployeeBO beforeRemoveEmployeeBO = beforeRemoveEmployeeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(beforeRemoveEmployeeBO, BeforeRemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to      减员前信息
     * @param request 请求对象
     * @return class BeforeRemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/exam")
    public Result exam(BeforeRemoveEmployeeTO to,HttpServletRequest request) throws ActException {
        try {
            BeforeRemoveEmployeeBO beforeRemoveEmployeeBO = beforeRemoveEmployeeAPI.exam(to);
            return ActResult.initialize(BeanTransform.copyProperties(beforeRemoveEmployeeBO, BeforeRemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 减员前id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            BeforeRemoveEmployeeBO beforeRemoveEmployeeBO = beforeRemoveEmployeeAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 获取所有离职名单
//     *
//     * @param request 请求对象
//     * @return class DimissionReasonVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/dimissonInfoList")
//    public Result dimissonInfoList(HttpServletRequest request) throws ActException {
//        try {
//            List<DimissionInfoBO> list = beforeRemoveEmployeeAPI.all();
//            return ActResult.initialize(BeanTransform.copyProperties(list, DimissionReasonVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}