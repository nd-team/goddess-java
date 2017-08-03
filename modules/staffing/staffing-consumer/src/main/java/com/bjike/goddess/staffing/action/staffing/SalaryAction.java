package com.bjike.goddess.staffing.action.staffing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffing.api.SalaryAPI;
import com.bjike.goddess.staffing.bo.SalaryBO;
import com.bjike.goddess.staffing.dto.SalaryDTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.to.SalaryTO;
import com.bjike.goddess.staffing.vo.SalaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工资区间
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("salary")
public class SalaryAction {
    @Autowired
    private SalaryAPI salaryAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = salaryAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 工资区间数据传输
     * @return class SalaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SalaryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SalaryBO> list = salaryAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, SalaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 工资区间传输对象
     * @return class SalaryVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SalaryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SalaryBO bo = salaryAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SalaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 工资区间id
     * @return class SalaryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/salary/{id}")
    public Result salary(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SalaryBO bo = salaryAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SalaryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 工资区间传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SalaryTO to, BindingResult result) throws ActException {
        try {
            salaryAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工资区间id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            salaryAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 工资区间数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryDTO dto) throws ActException {
        try {
            return ActResult.initialize(salaryAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有薪资区间
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/sal")
    public Result sal() throws ActException {
        try {
            return ActResult.initialize(salaryAPI.sal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}