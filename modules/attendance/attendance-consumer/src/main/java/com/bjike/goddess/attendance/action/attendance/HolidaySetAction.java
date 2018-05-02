package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.HolidaySetAPI;
import com.bjike.goddess.attendance.bo.HolidaySetBO;
import com.bjike.goddess.attendance.dto.HolidaySetDTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.HolidaySetTO;
import com.bjike.goddess.attendance.vo.HolidaySetVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * 假期设置
 *
 * @Author: [chenjunhao]
 * @Date: [ 2017-10-20 11:54 ]
 * @Description: [ 假期设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("holidayset")
public class HolidaySetAction {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private HolidaySetAPI holidaySetAPI;

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

            Boolean isHasPermission = holidaySetAPI.guidePermission(guidePermissionTO);
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
     * @param dto 假期设置数据传输
     * @return class HolidaySetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(HolidaySetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<HolidaySetBO> list = holidaySetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, HolidaySetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 假期设置传输对象
     * @return class HolidaySetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) HolidaySetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            HolidaySetBO bo = holidaySetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, HolidaySetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 假期设置id
     * @return class HolidaySetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/holiday/set/{id}")
    public Result HolidaySet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            HolidaySetBO bo = holidaySetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, HolidaySetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 假期设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) HolidaySetTO to, BindingResult result) throws ActException {
        try {
            holidaySetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 假期设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            holidaySetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 假期设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HolidaySetDTO dto) throws ActException {
        try {
            return ActResult.initialize(holidaySetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取创建人
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/current/user")
    public Result currentUser() throws ActException {
        try {
            return ActResult.initialize(userAPI.currentUser().getUsername());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取假期天数
     *
     * @param startTime 请假开始时间
     * @param endTime   请假结束时间
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/get/time/{startTime}/{endTime}")
    public Result getTime(@PathVariable String startTime, @PathVariable String endTime) throws ActException {
        try {
            LocalDate start = DateUtil.parseDate(startTime);
            LocalDate end = DateUtil.parseDate(endTime);
            return ActResult.initialize(DateUtil.misDay(end, start) + 1);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}