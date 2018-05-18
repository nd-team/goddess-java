package com.bjike.goddess.task.action.task;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.task.api.InnerTemplateAPI;
import com.bjike.goddess.task.bo.InnerTemplateBO;
import com.bjike.goddess.task.dto.InnerTemplateDTO;
import com.bjike.goddess.task.to.InnerTemplateTO;
import com.bjike.goddess.task.vo.InnerTemplateVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 内部协助模板
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("innertemplate")
public class InnerTemplateAction {
    @Autowired
    private InnerTemplateAPI innerTemplateAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 列表
     *
     * @param dto 内部协助模板数据传输
     * @return class InnerTemplateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InnerTemplateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<InnerTemplateBO> list = innerTemplateAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, InnerTemplateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 内部协助模板传输对象
     * @return class InnerTemplateVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) InnerTemplateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InnerTemplateBO bo = innerTemplateAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InnerTemplateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 内部协助模板id
     * @return class InnerTemplateVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/innerTemplate/{id}")
    public Result InnerTemplate(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InnerTemplateBO bo = innerTemplateAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InnerTemplateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 内部协助模板传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) InnerTemplateTO to, BindingResult result) throws ActException {
        try {
            innerTemplateAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 内部协助模板id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            innerTemplateAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 内部协助模板数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InnerTemplateDTO dto) throws ActException {
        try {
            return ActResult.initialize(innerTemplateAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发送邮件
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/send")
    public Result send(@Validated({InnerTemplateTO.SEND.class}) InnerTemplateTO to, BindingResult result) throws ActException {
        try {
            innerTemplateAPI.send(to);
            return new ActResult("发送邮件成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departs")
    public Result departs(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有岗位
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/positions")
    public Result positions() throws ActException {
        try {
            List<String> list = positionDetailAPI.positionNames();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}