package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.InterviewAddressInforAPI;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;
import com.bjike.goddess.recruit.vo.FailFirstInterviewReasonVO;
import com.bjike.goddess.recruit.vo.InterviewAddressInforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruit/interviewAddressInfor")
public class InterviewAddressInforAct {

    @Autowired
    private InterviewAddressInforAPI interviewAddressInforAPI;

    /**
     * 获取列表
     *
     * @param dto 面试地址信息
     * @return class InterviewAddressInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InterviewAddressInforDTO dto) throws ActException {
        try {
            List<InterviewAddressInforBO> boList = interviewAddressInforAPI.list(dto);
            List<InterviewAddressInforVO> voList = BeanTransform.copyProperties(boList, InterviewAddressInforVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加面试地址信息
     *
     * @param to 面试地址信息to信息
     * @return class InterviewAddressInforVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) InterviewAddressInforTO to) throws ActException {
        try {
            InterviewAddressInforBO bo = interviewAddressInforAPI.save(to);
            InterviewAddressInforVO vo = BeanTransform.copyProperties(bo, InterviewAddressInforVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除面试地址信息
     *
     * @param id 面试地址信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            interviewAddressInforAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑面试地址信息
     *
     * @param to 面试地址信息to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) InterviewAddressInforTO to) throws ActException {
        try {
            interviewAddressInforAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
