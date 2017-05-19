package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.api.MultipleProjectMultipleUIAPI;
import com.bjike.goddess.projectmeasure.bo.MultipleProjectMultipleUIBO;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIDTO;
import com.bjike.goddess.projectmeasure.to.MultipleProjectMultipleUITO;
import com.bjike.goddess.projectmeasure.vo.MultipleProjectMultipleUIVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 多项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmeasure/multipleprojectmultipleui")
public class MultipleProjectMultipleUIAct {

    @Autowired
    private MultipleProjectMultipleUIAPI multipleProjectMultipleUIAPI;

    /**
     * 分页查询多项目多个界面
     *
     * @param dto 多项目多个界面传输对象
     * @return class MultipleProjectMultipleUIVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MultipleProjectMultipleUIDTO dto) throws ActException {
        try {
            List<MultipleProjectMultipleUIBO> boList = multipleProjectMultipleUIAPI.list(dto);
            List<MultipleProjectMultipleUIVO> voList = BeanTransform.copyProperties(boList, MultipleProjectMultipleUIVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加多项目多个界面
     *
     * @param to 多项目多个界面to信息
     * @return class MultipleProjectMultipleUIVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MultipleProjectMultipleUITO to) throws ActException {
        try {
            MultipleProjectMultipleUIBO bo = multipleProjectMultipleUIAPI.save(to);
            MultipleProjectMultipleUIVO vo = BeanTransform.copyProperties(bo, MultipleProjectMultipleUIVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除多项目多个界面
     *
     * @param id 多项目多个界面唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            multipleProjectMultipleUIAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑多项目多个界面
     *
     * @param to 多项目多个界面to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MultipleProjectMultipleUITO to) throws ActException {
        try {
            multipleProjectMultipleUIAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}