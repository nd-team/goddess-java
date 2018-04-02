package com.bjike.goddess.event.action.event;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.bo.AppListDataBO;
import com.bjike.goddess.event.bo.ContentBO;
import com.bjike.goddess.event.bo.FatherBO;
import com.bjike.goddess.event.dto.EventDTO;
import com.bjike.goddess.event.dto.FatherDTO;
import com.bjike.goddess.event.enums.EventStatus;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.event.vo.AppListDataVO;
import com.bjike.goddess.event.vo.ContentVO;
import com.bjike.goddess.event.vo.EventVO;
import com.bjike.goddess.event.vo.FatherVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.entity.rbac.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * 事件
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 03:58 ]
 * @Description: [ 事件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("event")
public class EventAction {
    @Autowired
    private EventAPI eventAPI;

    @Autowired
    private UserAPI userAPI;

    /**
     * 事件列表
     *
     * @param dto 事件数据传输
     * @return class FatherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FatherDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FatherBO> list = eventAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FatherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 事件总条数
     *
     * @param dto 事件数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(eventAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 待办事件总条数(phone)
     *
     * @param dto 事件数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/phone/count")
    public Result counts(FatherDTO dto) throws ActException {
        try {
            return ActResult.initialize(eventAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过日历查看待办事件
     *
     * @param dto dto
     * @return class ContentVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByMonth")
    public Result findByMonth(@Validated(EventDTO.MONTH.class) EventDTO dto, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            List<ContentBO> list = eventAPI.findByMonth(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list,ContentVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 移动端事件列表
     *
     * @return class AppListDataVO
     * @throws ActException
     * @version v1
     * @desc 请假和加班添加进来的事件内容固定返回三个字段:请假/加班类型,开始时间,结束时间.借款(借款金额,预计借款时间)和报销(报销金额,报销发生时间)
     */
    @GetMapping("v1/allList")
    public Result list(String type, HttpServletRequest request) throws ActException {
        try {
            List<AppListDataBO> list = eventAPI.findAppList(type);
            return ActResult.initialize(BeanTransform.copyProperties(list, AppListDataVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 移动端跳转详情所需数据
     *
     * @return class FatherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findFather/{id}")
    public Result findFather(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FatherBO fatherBO = eventAPI.findFatherById(id);
            return ActResult.initialize(BeanTransform.copyProperties(fatherBO, FatherVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 当前用户事件总条数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/currentUserEvenCount")
    public Result currentUserEvenCount() throws ActException {
        try {
            return ActResult.initialize(eventAPI.currentUserEvenCount());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据计划类型获取对应的数据
     * 计划类型 分为  月计划 周计划 日计划
     *              month week  day
     *
     * @param dto dto
     * @return class FatherVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByPlanType")
    public Result findByPlanType(@Validated(EventDTO.MONTH.class) EventDTO dto, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            List<FatherBO> list = eventAPI.findByPlanType(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list,FatherVO.class,request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 判断新增计划是否为空
     *      主表(Father)一天一个人 只有一条记录
     *          比如: 2017-12-29
     *                  8：30 - 10：00
     *                  10：00 - 12：00
     *                  13：30 - 15：00
     *                  15：00 - 18：00
     *      Father 只可以存在一条记录 (ID) 关联
     *          子表 可以存在多条记录 (father.id) 关联
     *
     * @Date:2017-12-29
     * @version v1
     *
     */
    @PostMapping("v1/saveEvTo")
    public Result saveEvTo(@Validated(ADD.class) EventTO to, BindingResult result) throws ActException {
        try {
            to.setProjectChineseName("日历代办");
            to.setProjectEnglishName("CalendarAgent");
            to.setFunctionChineseName("日历代办");
            to.setFunctionEnglishName("CalendarAgent");
            to.setPermissions(Permissions.ADUIT);
            to.setStatus("待审核");
            to.setName(userAPI.currentUser().getUsername());
            to.setEventId(UUID.randomUUID().toString());

            eventAPI.saveEvTo(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 修改event
     *
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(EventTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(eventAPI.update(to), EventVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}