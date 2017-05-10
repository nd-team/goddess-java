package com.bjike.goddess.courier.action.courier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.courier.api.CourierCompanyAPI;
import com.bjike.goddess.courier.bo.CourierCompanyBO;
import com.bjike.goddess.courier.dto.CourierCompanyDTO;
import com.bjike.goddess.courier.to.CourierCompanyTO;
import com.bjike.goddess.courier.vo.CourierCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 快递公司信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-28 10:29 ]
 * @Description: [ 快递公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("couriercompany")
public class CourierCompanyAct {
    @Autowired
    private CourierCompanyAPI courierCompanyAPI;

    /**
     * 添加
     *
     * @param request 请求对象
     * @param to      快递公司信息
     * @return class CourierCompanyVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(HttpServletRequest request, @Validated({ADD.class}) CourierCompanyTO to, BindingResult result) throws ActException {
        try {
            CourierCompanyBO courierCompanyBO = courierCompanyAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(courierCompanyBO, CourierCompanyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param request 请求对象
     * @param to      快递公司信息
     * @return class CourierCompanyVO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(HttpServletRequest request, @Validated({EDIT.class}) CourierCompanyTO to, BindingResult result) throws ActException {
        try {
            CourierCompanyBO courierCompanyBO = courierCompanyAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(courierCompanyBO, CourierCompanyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param request 请求对象
     * @param dto     快递公司分页信息
     * @return class CourierCompanyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HttpServletRequest request, CourierCompanyDTO dto) throws ActException {
        try {
            List<CourierCompanyBO> list = courierCompanyAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, CourierCompanyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      快递公司id
     * @param request 请求对象
     * @return class CourierCompanyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/couriercompany/{id}")
    public Result couriercompany(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CourierCompanyBO courierCompanyBO = courierCompanyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(courierCompanyBO, CourierCompanyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 快递公司id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            courierCompanyAPI.delete(id);
            return new ActResult("删除成功！");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}