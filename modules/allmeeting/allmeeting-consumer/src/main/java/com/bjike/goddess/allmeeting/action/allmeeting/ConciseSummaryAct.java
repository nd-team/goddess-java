package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.ConciseSummaryAPI;
import com.bjike.goddess.allmeeting.dto.ConciseSummaryDTO;
import com.bjike.goddess.allmeeting.to.ConciseSummaryTO;
import com.bjike.goddess.allmeeting.vo.ConciseSummaryVO;
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
 * @Date: [ 2017-06-01 10:19 ]
 * @Description: [ 简洁交流讨论纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("concisesummary")
public class ConciseSummaryAct {

    @Autowired
    private ConciseSummaryAPI conciseSummaryAPI;


    /**
     * 编辑简洁交流讨论纪要
     *
     * @param to 简洁交流讨论纪要
     * @return class ConciseSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ConciseSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ConciseSummaryVO vo = BeanTransform.copyProperties(conciseSummaryAPI.edit(to), ConciseSummaryVO.class, request);
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
    @PutMapping
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            conciseSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class ConciseSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ConciseSummaryDTO dto) throws ActException {
        try {
            List<ConciseSummaryVO> voList = BeanTransform.copyProperties(conciseSummaryAPI.pageList(dto), ConciseSummaryVO.class);
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
    public Result count(ConciseSummaryDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = conciseSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询简洁交流讨论纪要
     *
     * @param id 简洁交流讨论纪要id
     * @return class ConciseSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ConciseSummaryVO vo = BeanTransform.copyProperties(conciseSummaryAPI.findById(id), ConciseSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}