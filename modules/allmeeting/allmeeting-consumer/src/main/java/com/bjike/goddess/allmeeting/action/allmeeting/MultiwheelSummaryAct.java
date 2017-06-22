package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.MultiwheelSummaryAPI;
import com.bjike.goddess.allmeeting.dto.MultiwheelSummaryDTO;
import com.bjike.goddess.allmeeting.to.MultiwheelSummaryTO;
import com.bjike.goddess.allmeeting.vo.MultiwheelSummaryVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 简洁交流讨论纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("multiwheelsummary")
public class MultiwheelSummaryAct {

    @Autowired
    private MultiwheelSummaryAPI multiwheelSummaryAPI;


    /**
     * 编辑简洁交流讨论纪要
     *
     * @param to 简洁交流讨论纪要
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MultiwheelSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MultiwheelSummaryVO vo = BeanTransform.copyProperties(multiwheelSummaryAPI.edit(to), MultiwheelSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结简洁交流讨论纪要
     *
     * @param id 简洁交流讨论纪要ID
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            multiwheelSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(MultiwheelSummaryDTO dto) throws ActException {
        try {
            List<MultiwheelSummaryVO> voList = BeanTransform.copyProperties(multiwheelSummaryAPI.pageList(dto), MultiwheelSummaryVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MultiwheelSummaryDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = multiwheelSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询简洁交流讨论纪要
     *
     * @param id 简洁交流讨论纪要id
     * @return class MultiwheelSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MultiwheelSummaryVO vo = BeanTransform.copyProperties(multiwheelSummaryAPI.findById(id), MultiwheelSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}