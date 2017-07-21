package com.bjike.goddess.royalty.action.royalty;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.royalty.api.JobsBetAPI;
import com.bjike.goddess.royalty.bo.JobsBetABO;
import com.bjike.goddess.royalty.dto.JobsBetDTO;
import com.bjike.goddess.royalty.dto.JobsBetEDTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.JobsBetATO;
import com.bjike.goddess.royalty.vo.JobsBetAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("jobsbet")
public class JobsBetAction {
    @Autowired
    private JobsBetAPI jobsBetAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;



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

            Boolean isHasPermission = jobsBetAPI.guidePermission(guidePermissionTO);
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
     * 岗位间对赌表列表总条数
     *
     * @param dto 岗位间对赌表dto
     * @des 获取所有岗位间对赌表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(JobsBetEDTO dto) throws ActException {
        try {
            Long count = jobsBetAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个岗位间对赌表
     *
     * @param id
     * @return class JobsBetAVO
     * @des 获取一个岗位间对赌表
     * @version v1
     */
    @GetMapping("v1/jobs/{id}")
    public Result jobs(@PathVariable String id) throws ActException {
        try {
            JobsBetABO jobsBetABO = jobsBetAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(jobsBetABO, JobsBetAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 岗位间对赌表列表
     *
     * @param dto 岗位间对赌表dto
     * @des 获取所有岗位间对赌表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(JobsBetDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(jobsBetAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加岗位间对赌表
     *
     * @param jobsBetATO 岗位间对赌表to
     * @des 添加岗位间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated JobsBetATO jobsBetATO, BindingResult bindingResult) throws ActException {
        try {

            jobsBetAPI.insert(jobsBetATO);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑岗位间对赌表
     *
     * @param jobsBetATO 岗位间对赌表数据to
     * @des 编辑岗位间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated JobsBetATO jobsBetATO, BindingResult bindingResult) throws ActException {
        try {
            jobsBetAPI.edit(jobsBetATO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除岗位间对赌表
     *
     * @param id 用户id
     * @des 根据用户id删除岗位间对赌表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            jobsBetAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取岗位
     *
     * @return class PositionDetailVO
     * @des 获取岗位
     * @version v1
     */
    @GetMapping("v1/jobs")
    public Result jobs() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findStatus(), PositionDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}