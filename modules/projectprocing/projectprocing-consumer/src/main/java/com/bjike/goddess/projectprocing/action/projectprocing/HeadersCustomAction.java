package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.HeadersCustomAPI;
import com.bjike.goddess.projectprocing.bo.HeadersCustomBO;
import com.bjike.goddess.projectprocing.dto.HeadersCustomDTO;
import com.bjike.goddess.projectprocing.to.HeadersCustomTO;
import com.bjike.goddess.projectprocing.vo.HeadersCustomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 表头定制
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("headerscustom")
public class HeadersCustomAction {
    @Autowired
    private HeadersCustomAPI headersCustomAPI;

    /**
     * 表头定制总条数
     *
     * @param headersCustomDTO 表头定制dto
     * @des 获取所有表头定制总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(HeadersCustomDTO headersCustomDTO) throws ActException {
        try {
            Long count = headersCustomAPI.countHeaders(headersCustomDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个表头定制
     *
     * @param id 表头定制id
     * @return class HeadersCustomVO
     * @des 根据id获取表头定制
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            HeadersCustomVO headersCustomVO = BeanTransform.copyProperties(
                    headersCustomAPI.getOneById(id), HeadersCustomVO.class, true);
            return ActResult.initialize(headersCustomVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 表头定制列表
     *
     * @param headersCustomDTO 表头定制dto
     * @return class HeadersCustomVO
     * @des 获取所有表头定制
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(HeadersCustomDTO headersCustomDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<HeadersCustomVO> projectCarryVOList = BeanTransform.copyProperties(
                    headersCustomAPI.listHeaders(headersCustomDTO), HeadersCustomVO.class, request);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加表头定制
     *
     * @param headersCustomTO 表头定制数据to
     * @return class HeadersCustomVO
     * @des 添加表头定制
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) HeadersCustomTO headersCustomTO, BindingResult bindingResult) throws ActException {
        try {
            HeadersCustomBO headersCustomBO = headersCustomAPI.addHeaders(headersCustomTO);
            return ActResult.initialize(BeanTransform.copyProperties(headersCustomBO, HeadersCustomVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑表头定制
     *
     * @param headersCustomTO 表头定制bo
     * @return class HeadersCustomVO
     * @des 添加表头定制
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) HeadersCustomTO headersCustomTO) throws ActException {
        try {
            HeadersCustomBO headersCustomBO = headersCustomAPI.editHeaders(headersCustomTO);
            return ActResult.initialize(BeanTransform.copyProperties(headersCustomBO, HeadersCustomVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除表头定制
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            headersCustomAPI.deleteHeaders(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
    /**
     * 根据外包单位获取所有表头字段
     *
     * @param outUnit 外包单位
     * @des 获取所有的外包单位
     * @version v1
     */
    @GetMapping("v1/headerByOutUnit")
    public Result findHeaderByOutUnit(@RequestParam String outUnit) throws ActException {
        try {
            List<HeadersCustomVO> headersCustomVO =
                   BeanTransform.copyProperties( headersCustomAPI.getHeaderByOutUnit(outUnit),HeadersCustomVO.class);
            return ActResult.initialize(headersCustomVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}