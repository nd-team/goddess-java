package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.progressmanage.api.TaskReceiveAPI;
import com.bjike.goddess.progressmanage.dto.TaskReceiveDTO;
import com.bjike.goddess.progressmanage.to.TaskReceiveTO;
import com.bjike.goddess.progressmanage.vo.TaskReceiveVO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务接收
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 11:26 ]
 * @Description: [ 任务接收 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taskreceive")
public class TaskReceiveAct {

    @Autowired
    private TaskReceiveAPI taskReceiveAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 项目组或部门下拉列表
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/groups")
    public Result groups(HttpServletRequest request) throws ActException {

        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findAllOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户下拉列表
     *
     * @return class UserBO
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {

        try {
            List<UserBO> voList = BeanTransform.copyProperties(positionDetailUserAPI.findUserList(), UserBO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 新增
     *
     * @param to 任务接收
     * @return class TaskReceiveVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TaskReceiveTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            TaskReceiveVO vo = BeanTransform.copyProperties(taskReceiveAPI.add(to), TaskReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 任务接收
     * @return class TaskReceiveVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) TaskReceiveTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            TaskReceiveVO vo = BeanTransform.copyProperties(taskReceiveAPI.edit(to), TaskReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            taskReceiveAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class TaskReceiveVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(TaskReceiveDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<TaskReceiveVO> voList = BeanTransform.copyProperties(taskReceiveAPI.pageList(dto), TaskReceiveVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}