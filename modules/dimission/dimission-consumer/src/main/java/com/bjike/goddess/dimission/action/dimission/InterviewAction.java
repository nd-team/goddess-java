package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.InterviewAPI;
import com.bjike.goddess.dimission.dto.InterviewDTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.InterviewTO;
import com.bjike.goddess.dimission.vo.InterviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 离职管理面谈
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("interview")
public class InterviewAction {
    @Autowired
    private InterviewAPI interviewAPI;

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

            Boolean isHasPermission = interviewAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 离职管理面谈传输对象
     * @return class InterviewVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) InterviewTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(interviewAPI.save(to), InterviewVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 离职管理面谈传输对象
     * @return class InterviewVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) InterviewTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(interviewAPI.update(to), InterviewVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 离职管理面谈数据id
     * @return class InterviewVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(interviewAPI.delete(id), InterviewVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 离职办理节点情况
     * @return class InterviewVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InterviewDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(interviewAPI.list(dto), InterviewVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取数据
     *
     * @param id id
     * @return class InterviewVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(interviewAPI.findById(id), InterviewVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否需项目经理信息离职面谈
     *
     * @version v1
     */
    @GetMapping("v1/judge/manager")
    public Result judgeManager(String name) throws ActException {
        try {
            return ActResult.initialize(interviewAPI.judgeManager(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否需模块负责人离职面谈
     *
     * @version v1
     */
    @GetMapping("v1/judge/principal")
    public Result judgePrincipal(String name) throws ActException {
        try {
            return ActResult.initialize(interviewAPI.judgePrincipal(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否需福利模块离职面谈
     *
     * @version v1
     */
    @GetMapping("v1/judge/welfare")
    public Result judgeWelfare(String name) throws ActException {
        try {
            return ActResult.initialize(interviewAPI.judgeWelfare(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否需要总经办离职面谈
     *
     * @version v1
     */
    @GetMapping("v1/judge/office")
    public Result judgeOffice(String name) throws ActException {
        try {
            return ActResult.initialize(interviewAPI.judgeOffice(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否可挽留
     *
     * @version v1
     */
    @GetMapping("v1/detainment")
    public Result detainment(String name) throws ActException {
        try {
            return ActResult.initialize(interviewAPI.detainment(name));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}