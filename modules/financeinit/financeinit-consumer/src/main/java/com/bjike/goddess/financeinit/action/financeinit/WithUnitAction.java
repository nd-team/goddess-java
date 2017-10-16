package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.WithUnitAPI;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.entity.WithUnit;
import com.bjike.goddess.financeinit.to.WithUnitTO;
import com.bjike.goddess.financeinit.vo.WithUnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 往来单位
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("withunit")
public class WithUnitAction extends BaseFileAction{
    @Autowired
    private WithUnitAPI withUnitAPI;

    /**
     * 列表总条数
     *
     * @param withUnitDTO 往来单位dto
     * @des 获取所有往来单位总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WithUnitDTO withUnitDTO) throws ActException {
        try {
            Long count = withUnitAPI.countWith(withUnitDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个往来单位
     *
     * @param id 往来单位id
     * @return class WithUnitVO
     * @des 根据id获取往来单位
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            WithUnitVO withUnitVO = BeanTransform.copyProperties(
                    withUnitAPI.getOneById(id), WithUnitVO.class);
            return ActResult.initialize(withUnitVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 往来单位列表
     *
     * @param withUnitDTO 往来单位dto
     * @return class WithUnitVO
     * @des 获取所有往来单位
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(WithUnitDTO withUnitDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<WithUnitVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    withUnitAPI.listWith(withUnitDTO), WithUnitVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加往来单位
     *
     * @param withUnitTO 往来单位数据to
     * @return class WithUnitVO
     * @des 添加往来单位
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) WithUnitTO withUnitTO, BindingResult bindingResult) throws ActException {
        try {
            WithUnitBO withUnitBO = withUnitAPI.addWith(withUnitTO);
            return ActResult.initialize(BeanTransform.copyProperties(withUnitBO, WithUnitVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑往来单位
     *
     * @param withUnitTO 往来单位数据bo
     * @return class WithUnitVO
     * @des 编辑往来单位
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) WithUnitTO withUnitTO, BindingResult bindingResult) throws ActException {
        try {
            WithUnitBO withUnitBO = withUnitAPI.editWith(withUnitTO);
            return ActResult.initialize(BeanTransform.copyProperties(withUnitBO, WithUnitVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除往来单位记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            withUnitAPI.deleteWith(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出往来单位
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "往来单位.xlsx";
            super.writeOutFile(response, withUnitAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}