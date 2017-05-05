package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.SingleProjectSingleUIAPI;
import com.bjike.goddess.projectmeasure.bo.SingleProjectSingleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectSingleUIDTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectSingleUITO;
import com.bjike.goddess.projectmeasure.vo.SingleProjectSingleUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 单个项目单个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:48 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ssui")
public class SingleProjectSingleUIAct {

    @Autowired
    private SingleProjectSingleUIAPI singleProjectSingleUIAPI;

    /**
     * 根据id查询单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @return class SingleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/ssui/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SingleProjectSingleUIBO bo = singleProjectSingleUIAPI.findById(id);
            SingleProjectSingleUIVO vo = BeanTransform.copyProperties(bo, SingleProjectSingleUIVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 单个项目单个界面dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated SingleProjectSingleUIDTO dto, BindingResult result) throws ActException {
        try {
            Long count = singleProjectSingleUIAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询单个项目单个界面
     *
     * @param dto 单个项目单个界面传输对象
     * @return class SingleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated SingleProjectSingleUIDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<SingleProjectSingleUIBO> boList = singleProjectSingleUIAPI.list(dto);
            List<SingleProjectSingleUIVO> voList = BeanTransform.copyProperties(boList, SingleProjectSingleUIVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加单个项目单个界面
     *
     * @param to 单个项目单个界面to信息
     * @return class SingleProjectSingleUIVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) SingleProjectSingleUITO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SingleProjectSingleUIBO bo = singleProjectSingleUIAPI.save(to);
            SingleProjectSingleUIVO vo = BeanTransform.copyProperties(bo, SingleProjectSingleUIVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除单个项目单个界面
     *
     * @param id 单个项目单个界面唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            singleProjectSingleUIAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑单个项目单个界面
     *
     * @param to 单个项目单个界面to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SingleProjectSingleUITO to, BindingResult result) throws ActException {
        try {
            singleProjectSingleUIAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}