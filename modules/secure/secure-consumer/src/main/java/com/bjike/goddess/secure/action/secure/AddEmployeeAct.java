package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.AddEmployeeAPI;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.vo.AddEmployeeVO;
import com.bjike.goddess.user.entity.rbac.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 社保增员信息名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("addemployee")
public class AddEmployeeAct {
    @Autowired
    private AddEmployeeAPI addEmployeeAPI;

    /**
     * 查找
     *
     * @param dto 增员分页信息
     * @param re  请求对象
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AddEmployeeDTO dto, HttpServletRequest re) throws ActException {
        try {
            List<AddEmployeeBO> list = addEmployeeAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AddEmployeeVO.class, re));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      增员信息id
     * @param request 请求对象
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/addemployee/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AddEmployeeBO addEmployeeBO = addEmployeeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(addEmployeeBO, AddEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to      增员名单信息
     * @param request 请求对象
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AddEmployeeTO to, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            AddEmployeeBO addEmployeeBO = addEmployeeAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(addEmployeeBO, AddEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 增员民单id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            addEmployeeAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}