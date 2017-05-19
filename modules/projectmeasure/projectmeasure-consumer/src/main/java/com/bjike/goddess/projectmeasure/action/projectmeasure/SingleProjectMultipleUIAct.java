package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.SingleProjectMultipleUIAPI;
import com.bjike.goddess.projectmeasure.bo.SingleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.to.SingleProjectMultipleUITO;
import com.bjike.goddess.projectmeasure.vo.SingleProjectMultipleUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 单个项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/singleprojectmultipleui")
public class SingleProjectMultipleUIAct {

    @Autowired
    private SingleProjectMultipleUIAPI singleProjectMultipleUIAPI;

    /**
     * 分页查询单个项目多个界面
     *
     * @param dto 单个项目多个界面传输对象
     * @return class SingleProjectMultipleUIVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SingleProjectMultipleUIDTO dto) throws ActException {
        try {
            List<SingleProjectMultipleUIBO> boList = singleProjectMultipleUIAPI.list(dto);
            List<SingleProjectMultipleUIVO> voList = BeanTransform.copyProperties(boList, SingleProjectMultipleUIVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加单个项目多个界面
     *
     * @param to 单个项目多个界面to信息
     * @return class SingleProjectMultipleUIVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) SingleProjectMultipleUITO to) throws ActException {
        try {
            SingleProjectMultipleUIBO bo = singleProjectMultipleUIAPI.save(to);
            SingleProjectMultipleUIVO vo = BeanTransform.copyProperties(bo, SingleProjectMultipleUIVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除单个项目多个界面
     *
     * @param id 单个项目多个界面唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            singleProjectMultipleUIAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑单个项目多个界面
     *
     * @param to 单个项目多个界面to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SingleProjectMultipleUITO to) throws ActException {
        try {
            singleProjectMultipleUIAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}