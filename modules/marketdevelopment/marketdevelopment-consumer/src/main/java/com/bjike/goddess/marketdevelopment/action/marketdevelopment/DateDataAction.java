package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.DateDataAPI;
import com.bjike.goddess.marketdevelopment.dto.DateDataDTO;
import com.bjike.goddess.marketdevelopment.to.DateDataTO;
import com.bjike.goddess.marketdevelopment.to.DateDataUpdateTO;
import com.bjike.goddess.marketdevelopment.vo.MonthMoneyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 日期数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("datedata")
public class DateDataAction extends BaseFileAction{
    @Autowired
    private DateDataAPI dateDataAPI;

    /**
     //     * 功能导航权限
     //     *
     //     * @param guidePermissionTO 导航类型数据
     //     * @throws ActException
     //     * @version v1
     //     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            Boolean isHasPermission = dateDataAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 列表
     *
     * @param dto 日汇总数据传输对象
     * @return class MonthMoneyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DateDataDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dateDataAPI.maps(dto), MonthMoneyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 日汇总传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DateDataTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dateDataAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改日汇总数据
     *
     * @param to 日汇总传输对象
     * @version v1
     */
    @PutMapping("v1/update/{dateDataId}")
    public Result update(@Validated(EDIT.class) DateDataUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dateDataAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除日汇总数据
     *
     * @param dateDataId 日汇总传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{dateDataId}")
    public Result delete(@PathVariable String dateDataId, HttpServletRequest request) throws ActException {
        try {
            dateDataAPI.delete(dateDataId);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取日汇总数据
     *
     * @param dateDataId 业务方向数据id
     * @return class DateDataVO
     * @version v1
     */
    @GetMapping("v1/findById/{dateDataId}")
    public Result findById(@PathVariable String dateDataId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dateDataAPI.getById(dateDataId), MonthMoneyVO.class, request));
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
    public Result getTotal(DateDataDTO dto) throws ActException {
        try {
            return ActResult.initialize(dateDataAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}