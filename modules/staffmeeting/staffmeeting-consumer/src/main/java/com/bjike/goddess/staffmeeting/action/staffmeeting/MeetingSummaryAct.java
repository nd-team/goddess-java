package com.bjike.goddess.staffmeeting.action.staffmeeting;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingSummaryAPI;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;
import com.bjike.goddess.staffmeeting.vo.MeetingSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工代表大会纪要
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("summary")
public class MeetingSummaryAct {

    @Autowired
    private MeetingSummaryAPI meetingSummaryAPI;


    /**
     * 编辑员工代表大会纪要
     *
     * @param to 员工代表大会纪要
     * @return class MeetingSummaryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingSummaryTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingSummaryVO vo = BeanTransform.copyProperties(meetingSummaryAPI.edit(to), MeetingSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结员工代表大会纪要
     *
     * @param id 员工代表大会纪要ID
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            meetingSummaryAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MeetingSummaryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(MeetingSummaryDTO dto) throws ActException {
        try {
            List<MeetingSummaryVO> voList = BeanTransform.copyProperties(meetingSummaryAPI.pageList(dto), MeetingSummaryVO.class);
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
    public Result count(MeetingSummaryDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
            Long count = meetingSummaryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询员工代表大会纪要
     *
     * @param id 员工代表大会纪要id
     * @return class MeetingSummaryVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingSummaryVO vo = BeanTransform.copyProperties(meetingSummaryAPI.findById(id), MeetingSummaryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}