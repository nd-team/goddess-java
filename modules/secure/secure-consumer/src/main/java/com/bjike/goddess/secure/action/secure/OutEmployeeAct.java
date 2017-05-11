package com.bjike.goddess.secure.action.secure;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.secure.api.OutEmployeeAPI;
import com.bjike.goddess.secure.bo.OutEmployeeBO;
import com.bjike.goddess.secure.dto.OutEmployeeDTO;
import com.bjike.goddess.secure.to.OutEmployeeTO;
import com.bjike.goddess.secure.vo.OutEmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 离职名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 04:11 ]
 * @Description: [ 离职名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("out-employee")
public class OutEmployeeAct {
    @Autowired
    private OutEmployeeAPI outEmployeeAPI;

    /**
     * 查找
     *
     * @param dto     离职名单分页信息
     * @param request 请求对象
     * @return class OutEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result find(OutEmployeeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OutEmployeeBO> list = outEmployeeAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, OutEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否继续购买
     *
     * @param to      离职名单信息
     * @param request 请求对象
     * @return class OutEmployeeVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/isAgain")
    public Result is_again(@Validated({EDIT.class}) OutEmployeeTO to, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            OutEmployeeBO outEmployeeBO = outEmployeeAPI.is_again(to);
            return ActResult.initialize(BeanTransform.copyProperties(outEmployeeBO, OutEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      离职名单id
     * @param request 请求对象
     * @return class OutEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/out-employee/{id}")
    public Result find_by_id(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            OutEmployeeBO outEmployeeBO = outEmployeeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(outEmployeeBO, OutEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 离职名单id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            outEmployeeAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}