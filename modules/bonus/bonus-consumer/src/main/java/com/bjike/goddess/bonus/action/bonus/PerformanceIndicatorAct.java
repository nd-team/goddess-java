package com.bjike.goddess.bonus.action.bonus;

import com.bjike.goddess.bonus.api.PerformanceIndicatorAPI;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.excel.SonPermissionObject;
import com.bjike.goddess.bonus.to.GuidePermissionTO;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.bonus.vo.PerformanceIndicatorVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 绩效指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("performanceindicator")
public class PerformanceIndicatorAct {

    @Autowired
    private PerformanceIndicatorAPI performanceIndicatorAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = performanceIndicatorAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

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

            Boolean isHasPermission = performanceIndicatorAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to 绩效指标传输对象
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PerformanceIndicatorTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.save(to), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 绩效指标传输对象
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PerformanceIndicatorTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.update(to), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.delete(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 启动
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PutMapping("v1/start/{id}")
    public Result start(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.start(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @PutMapping("v1/close/{id}")
    public Result close(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.close(id), PerformanceIndicatorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据指标状态查询绩效指标数据
     *
     * @param status 绩效指标状态
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Boolean status, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.findByStatus(status), PerformanceIndicatorVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询启动状态绩效指标数据
     *
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/findStart")
    public Result findStart(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.findByStatus(Boolean.TRUE), PerformanceIndicatorVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 绩效指标数据传输对象
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PerformanceIndicatorDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.maps(dto), PerformanceIndicatorVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据\id获取绩效指标数据
     *
     * @param id 绩效指标数据id
     * @return class PerformanceIndicatorVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(performanceIndicatorAPI.getById(id), PerformanceIndicatorVO.class));
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
            return ActResult.initialize(performanceIndicatorAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}