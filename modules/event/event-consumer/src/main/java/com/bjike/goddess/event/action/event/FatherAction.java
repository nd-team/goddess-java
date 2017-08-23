package com.bjike.goddess.event.action.event;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.bo.AreaCountBO;
import com.bjike.goddess.event.bo.ClassifyCountBO;
import com.bjike.goddess.event.bo.FatherBO;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.to.GuidePermissionTO;
import com.bjike.goddess.event.vo.AreaCountVO;
import com.bjike.goddess.event.vo.ClassifyCountVO;
import com.bjike.goddess.event.vo.FatherVO;
import com.bjike.goddess.event.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有人事件
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-10 11:27 ]
 * @Description: [ 所有人事件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("father")
public class FatherAction {
    @Autowired
    private EventAPI eventAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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

            List<SonPermissionObject> hasPermissionList = eventAPI.sonPermission();
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

            Boolean isHasPermission = eventAPI.guidePermission(guidePermissionTO);
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
     * 所有人事件列表
     *
     * @param dto 所有人事件数据传输
     * @return class FatherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FatherDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FatherBO> list = eventAPI.allList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FatherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有人事件总条数
     *
     * @param dto 所有人事件数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(eventAPI.allCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总事件汇总
     *
     * @param dto dto
     * @return class AreaCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/zongCount")
    public Result zongCount(@Validated(EventDTO.AREA.class) EventDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaCountBO> list = eventAPI.zongCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已处理事件汇总
     *
     * @param dto dto
     * @return class AreaCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/haveDealCount")
    public Result haveDealCount(@Validated(EventDTO.AREA.class) EventDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaCountBO> list = eventAPI.haveDealCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 未处理事件汇总
     *
     * @param dto dto
     * @return class AreaCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/noDealCount")
    public Result noDealCount(@Validated(EventDTO.AREA.class) EventDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaCountBO> list = eventAPI.noDealCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 逾期未处理事件汇总
     *
     * @param dto dto
     * @return class AreaCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/passNoDealCount")
    public Result passNoDealCount(@Validated(EventDTO.AREA.class) EventDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<AreaCountBO> list = eventAPI.passNoDealCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 按照分类汇总
     *
     * @param dto dto
     * @return class ClassifyCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/classifyCount")
    public Result classifyCount(@Validated(EventDTO.CLASSIFY.class) EventDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<ClassifyCountBO> list = eventAPI.classifyCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ClassifyCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea() throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            List<String> areas=new ArrayList<>();
            for (AreaBO areaBO:list){
                areas.add(areaBO.getArea());
            }
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有月份
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allMonths")
    public Result allMonths() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取某月的周数
     *
     * @param year  年份
     * @param month 月份
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/getWeek/{year}/{month}")
    public Result getWeek(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            int week = DateUtil.getWeekNum(year, month);
            for (int i = 1; i <= week; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}