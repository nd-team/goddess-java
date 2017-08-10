package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.to.SchemeApplyTO;
import com.bjike.goddess.staffshares.vo.SchemeApplicationVO;
import com.bjike.goddess.staffshares.vo.SchemeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工持股管理
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 08:59 ]
 * @Description: [ 员工持股管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("scheme")
public class SchemeAction {
    @Autowired
    private SchemeAPI schemeAPI;

    /**
     * 申请
     *
     * @param to 员工持股管理传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SchemeApplyTO to, BindingResult result) throws ActException {
        try {
            schemeAPI.save(to);
            return ActResult.initialize("申请成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 员工持股管理传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SchemeApplyTO to, BindingResult result) throws ActException {
        try {
            schemeAPI.update(to);
            return ActResult.initialize("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目派工单信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            schemeAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 股份发行方案列表
     *
     * @param dto 员工持股管理数据传输对象
     * @return class SchemeApplicationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SchemeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(schemeAPI.maps(dto), SchemeApplicationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取员工持股管理数据
     *
     * @param id 员工持股管理数据id
     * @return class SchemeApplicationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(schemeAPI.getById(id), SchemeVO.class));
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
    public Result getTotal(SchemeDTO schemeDTO) throws ActException {
        try {
            return ActResult.initialize(schemeAPI.getTotal(schemeDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 员工持股管理传输对象
     * @version v1
     */
    @PutMapping("v1/examine/{id}")
    public Result examine(SchemeApplyTO to, BindingResult result) throws ActException {
        try {
            schemeAPI.examine(to);
            return ActResult.initialize("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发行
     *
     * @version v1
     */
    @GetMapping("v1/issue/{id}")
    public Result issue(@PathVariable String id) throws ActException {
        try {
            schemeAPI.issue(id);
            return ActResult.initialize("发行成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}