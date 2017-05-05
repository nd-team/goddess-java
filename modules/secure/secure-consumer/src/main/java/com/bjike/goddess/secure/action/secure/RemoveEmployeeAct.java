package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.RemoveEmployeeAPI;
import com.bjike.goddess.secure.bo.RemoveEmployeeBO;
import com.bjike.goddess.secure.dto.RemoveEmployeeDTO;
import com.bjike.goddess.secure.to.RemoveEmployeeTO;
import com.bjike.goddess.secure.vo.RemoveEmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 减员名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 09:48 ]
 * @Description: [ 减员名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("remove-employee")
public class RemoveEmployeeAct {
    @Autowired
    private RemoveEmployeeAPI removeEmployeeAPI;

    /**
     * 查找
     *
     * @param dto     减员分页信息
     * @param request 请求对象
     * @return class RemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(RemoveEmployeeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<RemoveEmployeeBO> list = removeEmployeeAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, RemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      减员信息
     * @param request 请求对象
     * @return class RemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) RemoveEmployeeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RemoveEmployeeBO removeEmployeeBO = removeEmployeeAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(removeEmployeeBO, RemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 减员信息id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            removeEmployeeAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      减员信息id
     * @param request 请求对象
     * @return class RemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/remove-employee/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RemoveEmployeeBO removeEmployeeBO = removeEmployeeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(removeEmployeeBO, RemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认减员
     *
     * @param to 减员信息
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/confirm")
    public Result confirm(RemoveEmployeeTO to) throws ActException {
        try {
            removeEmployeeAPI.confirm(to);
            return new ActResult("确认减员成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过姓名和员工编号查找
     *
     * @param to      减员信息
     * @param request 请求对象
     * @return class RemoveEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByNameAndId")
    public Result findByNameAndId(RemoveEmployeeTO to, HttpServletRequest request) throws ActException {
        try {
            String[] removeName = new String[]{to.getRemoveName()};
            String[] employeeId = new String[]{to.getEmployeeId()};
            RemoveEmployeeBO bo = removeEmployeeAPI.findByNameAndId(removeName, employeeId);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RemoveEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}