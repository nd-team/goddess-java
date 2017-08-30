package com.bjike.goddess.workprogress.action.workprogress;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workprogress.api.WeekTargetAPI;
import com.bjike.goddess.workprogress.dto.WeekTargetDTO;
import com.bjike.goddess.workprogress.to.GuidePermissionTO;
import com.bjike.goddess.workprogress.to.StandardTO;
import com.bjike.goddess.workprogress.to.WeekTargetTO;
import com.bjike.goddess.workprogress.vo.WeekTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 周指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("weektarget")
public class WeekTargetAction {

    @Autowired
    private WeekTargetAPI weekTargetAPI;

    /**
     * 保存
     *
     * @param to 周指标传输对象
     * @return class WeekTargetVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) WeekTargetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.save(to), WeekTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 周指标传输对象
     * @return class WeekTargetVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) WeekTargetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.update(to), WeekTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 周指标数据id
     * @return class WeekTargetVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.delete(id), WeekTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询周指标数据
     *
     * @param id 周指标数据id
     * @return class WeekTargetVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.getById(id), WeekTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑标准
     *
     * @param to 标准修改传输对象
     * @return class WeekTargetVO
     * @version v1
     */
    @PutMapping("v1/standard/{id}")
    public Result updateStandard(@Validated(ADD.class) StandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.updateStandard(to), WeekTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 周指标数据传输对象
     * @return class WeekTargetVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WeekTargetDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(weekTargetAPI.maps(dto), WeekTargetVO.class, request));
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
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(weekTargetAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            Boolean isHasPermission = weekTargetAPI.guidePermission(guidePermissionTO);
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
     * 获取节点标准
     *
     * @version v1
     */
    @GetMapping("v1/getStandard")
    public Result getStandard() throws ActException{
        try{
            return ActResult.initialize(weekTargetAPI.getStandard());
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

}