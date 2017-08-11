package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.ApplicationAPI;
import com.bjike.goddess.staffshares.dto.ApplicationDTO;
import com.bjike.goddess.staffshares.entity.Application;
import com.bjike.goddess.staffshares.to.ApplicationTO;
import com.bjike.goddess.staffshares.vo.ApplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 干股代表申请
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("application")
public class ApplicationAction {
    @Autowired
    private ApplicationAPI applicationAPI;

    /**
     * 申请
     *
     * @param to 干股代表申请传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ApplicationTO to, BindingResult result) throws ActException {
        try {
            applicationAPI.save(to);
            return ActResult.initialize("申请成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 编辑
//     *
//     * @param to 干股代表申请传输对象
//     * @version v1
//     */
//    @PutMapping("v1/update/{id}")
//    public Result update(@Validated(EDIT.class) ApplicationTO to, BindingResult result) throws ActException {
//        try {
//            applicationAPI.update(to);
//            return ActResult.initialize("编辑成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param id id
//     * @des 根据id删除项目派工单信息记录
//     * @version v1
//     */
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            applicationAPI.delete(id);
//            return new ActResult("delete success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 干股代表申请列表
     *
     * @param dto 干股代表申请传输对象
     * @return class ApplicationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ApplicationDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(applicationAPI.maps(dto), ApplicationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取干股代表申请数据
     *
     * @param id 干股代表申请数据id
     * @return class ApplicationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(applicationAPI.getById(id), ApplicationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(ApplicationDTO applicationDTO) throws ActException {
        try {
            return ActResult.initialize(applicationAPI.getTotal(applicationDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 干股代表申请传输对象
     * @version v1
     */
    @PutMapping("v1/examine/{id}")
    public Result examine(@Validated(EDIT.class) ApplicationTO to, BindingResult result) throws ActException {
        try {
            applicationAPI.examine(to);
            return ActResult.initialize("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}