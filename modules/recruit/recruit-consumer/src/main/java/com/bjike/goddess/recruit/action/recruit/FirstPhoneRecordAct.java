package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FirstPhoneRecordAPI;
import com.bjike.goddess.recruit.bo.FirstPhoneRecordBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.entity.FirstPhoneRecord;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.vo.FirstPhoneRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 第一次电访记录
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 15:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("firstPhoneRecord")
public class FirstPhoneRecordAct {


    @Autowired
    private FirstPhoneRecordAPI firstPhoneRecordAPI;

    /**
     * 根据id查询第一次电访记录
     *
     * @param id 第一次电访记录唯一标识
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/firstphonerecord/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FirstPhoneRecordBO bo = firstPhoneRecordAPI.findById(id);
            FirstPhoneRecordVO vo = BeanTransform.copyProperties(bo, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 第一次电访记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FirstPhoneRecordDTO dto, BindingResult result) throws ActException {
        try {
            Long count = firstPhoneRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 第一次电访记录传输对象
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FirstPhoneRecordDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FirstPhoneRecordBO> boList = firstPhoneRecordAPI.list(dto);
            List<FirstPhoneRecordVO> voList = BeanTransform.copyProperties(boList, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(voList);
         } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加第一次电访记录
     *
     * @param to 第一次电访记录to信息
     * @return class FirstPhoneRecordVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FirstPhoneRecordTO to, HttpServletRequest request) throws ActException {
        try {
            FirstPhoneRecordBO bo = firstPhoneRecordAPI.save(to);
            FirstPhoneRecordVO vo = BeanTransform.copyProperties(bo, FirstPhoneRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除第一次电放记录
     *
     * @param id 第一次电访记录唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            firstPhoneRecordAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑第一次电访记录
     *
     * @param to 第一次电访记录to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) FirstPhoneRecordTO to) throws ActException {
        try {
            firstPhoneRecordAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
