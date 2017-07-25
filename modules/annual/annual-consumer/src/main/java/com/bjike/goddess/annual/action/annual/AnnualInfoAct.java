package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualInfoAPI;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.to.AnnualInfoTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.annual.vo.AnnualInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 年假信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualinfo")
public class AnnualInfoAct {

    @Autowired
    private AnnualInfoAPI annualInfoAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
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

            Boolean isHasPermission = annualInfoAPI.guidePermission(guidePermissionTO);
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
     * 获取指定用户的年假信息(我的年假记录)
     *
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findByUsername")
    public Result findByUsername() throws ActException {
        try {
            UserBO userBO = userAPI.currentUser();
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.findByUsername(userBO.getUsername()), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户名查询年假信息
     *
     * @param username 用户名
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findByUsers")
    public Result findByUsers(String[] username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.findByUsers(username), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询列表
     *
     * @param dto 年假信息数据传输对象
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.maps(dto), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取年假信息数据
     *
     * @param id 年假信息数据id
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.getById(id), AnnualInfoVO.class));
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
            return ActResult.initialize(annualInfoAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 为体验时提供保存年假信息数据
     *
     * @param to 年假信息传输对象
     * @return class AnnualInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AnnualInfoTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.save(to), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<DepartmentDetailBO> depart = departmentDetailAPI.findStatus();
            return ActResult.initialize(depart);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加中所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/allArea")
    public Result allArea() throws ActException {
        try {
            List<AreaBO> area = departmentDetailAPI.findArea();
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<UserBO> getPerson = positionDetailUserAPI.findUserList();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的岗位层级
     *
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            List<OpinionBO> getThawPerson = arrangementAPI.findThawOpinion();
            return ActResult.initialize(getThawPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的岗位
     *
     * @version v1
     */
    @GetMapping("v1/findAllOpinion")
    public Result findAllOpinion() throws ActException {
        try {
            List<OpinionBO> getOpinion = positionDetailAPI.findAllOpinion();
            return ActResult.initialize(getOpinion);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}