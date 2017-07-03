package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.AreaTransferDaySumAPI;
import com.bjike.goddess.materialsummary.bo.AreaTransferDaySumBO;
import com.bjike.goddess.materialsummary.dto.AreaTransferDaySumDTO;
import com.bjike.goddess.materialsummary.to.AreaTransferDaySumTO;
import com.bjike.goddess.materialsummary.vo.AreaTransferDaySumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区调动日汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:50 ]
 * @Description: [ 地区调动日汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areatransferdaysum")
public class AreaTransferDaySumAct {

    @Autowired
    private AreaTransferDaySumAPI areaTransferDaySumAPI;

    /**
     * 根据id查询地区调动日汇总记录
     *
     * @param id 地区调动日汇总记录唯一标识
     * @return class AreaTransferDaySumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areatransferdaysum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AreaTransferDaySumBO bo = areaTransferDaySumAPI.findById(id);
            AreaTransferDaySumVO vo = BeanTransform.copyProperties(bo, AreaTransferDaySumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 地区调动日汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated AreaTransferDaySumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = areaTransferDaySumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 地区调动日汇总记录dto
     * @return class AreaTransferDaySumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated AreaTransferDaySumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaTransferDaySumBO> boList = areaTransferDaySumAPI.list(dto);
            List<AreaTransferDaySumVO> voList = BeanTransform.copyProperties(boList, AreaTransferDaySumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加地区调动日汇总记录
     *
     * @param to 地区调动日汇总记录to信息
     * @return class AreaTransferDaySumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) AreaTransferDaySumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AreaTransferDaySumBO bo = areaTransferDaySumAPI.save(to);
            AreaTransferDaySumVO vo = BeanTransform.copyProperties(bo, AreaTransferDaySumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除地区调动日汇总记录
     *
     * @param id 地区调动日汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            areaTransferDaySumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑地区调动日汇总记录
     *
     * @param to 地区调动日汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) AreaTransferDaySumTO to, BindingResult result) throws ActException {
        try {
            areaTransferDaySumAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summary")
    public Result summary(HttpServletRequest request) throws ActException {
        try {
            List<AreaTransferDaySumBO> listBO = areaTransferDaySumAPI.summary();
            List<AreaTransferDaySumVO> listVO = BeanTransform.copyProperties(listBO, AreaTransferDaySumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}