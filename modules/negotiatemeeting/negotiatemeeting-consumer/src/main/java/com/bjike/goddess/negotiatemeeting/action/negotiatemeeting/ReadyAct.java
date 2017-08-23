package com.bjike.goddess.negotiatemeeting.action.negotiatemeeting;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.negotiatemeeting.api.ReadyAPI;
import com.bjike.goddess.negotiatemeeting.bo.ReadyBO;
import com.bjike.goddess.negotiatemeeting.dto.ReadyDTO;
import com.bjike.goddess.negotiatemeeting.to.ReadyTO;
import com.bjike.goddess.negotiatemeeting.vo.ReadyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 协商前准备信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:39 ]
 * @Description: [ 协商前准备信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ready")
public class ReadyAct {
    @Autowired
    private ReadyAPI readyAPI;

//    @PostMapping("v1/save")
//    public Result save(@Validated({ADD.class}) ReadyTO to, BindingResult result, HttpServletRequest request) throws ActException {
//        try {
//            ReadyBO bo = readyAPI.save(to);
//            return ActResult.initialize(BeanTransform.copyProperties(bo, ReadyVO.class, request));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 当前用户协商前准备信息列表总条数
     *
     * @param dto 协商前准备信息dto
     * @des 获取所有协商前准备信息总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(ReadyDTO dto) throws ActException {
        try {
            Long count = readyAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个协商前准备信息
     *
     * @param id id
     * @return class ReadyVO
     * @des 获取一个协商前准备信息
     * @version v1
     */
    @GetMapping("v1/ready/{id}")
    public Result ready(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ReadyBO bo = readyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 协商前准备信息列表
     *
     * @param dto 协商前准备信息信息dto
     * @return class ReadyVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ReadyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ReadyVO> VOS = BeanTransform.copyProperties
                    (readyAPI.list(dto), ReadyVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前用户的协商前准备信息列表
     *
     * @return class ReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/currentList")
    public Result currentList(HttpServletRequest request) throws ActException {
        try {
            List<ReadyVO> VOS = BeanTransform.copyProperties
                    (readyAPI.currentList(), ReadyVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑协商前准备信息信息
     *
     * @param to 协商前准备信息信息数据to
     * @des 编辑协商前准备信息信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ReadyTO to, BindingResult bindingResult) throws ActException {
        try {
            readyAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}