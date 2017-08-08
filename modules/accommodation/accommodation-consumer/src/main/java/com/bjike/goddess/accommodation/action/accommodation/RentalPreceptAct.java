package com.bjike.goddess.accommodation.action.accommodation;

import com.bjike.goddess.accommodation.api.RentalPreceptAPI;
import com.bjike.goddess.accommodation.bo.RentalPreceptBO;
import com.bjike.goddess.accommodation.dto.RentalPreceptDTO;
import com.bjike.goddess.accommodation.excel.SonPermissionObject;
import com.bjike.goddess.accommodation.to.GuidePermissionTO;
import com.bjike.goddess.accommodation.to.RentalPreceptTO;
import com.bjike.goddess.accommodation.vo.RentalPreceptVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 租房方案业务
 *
 * @Author: [xiazhili]
 * @Date: [17-3-14]
 * @Description: [租房方案业务]
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess]
 */
@RestController
@RequestMapping("rentalPrecept")
public class RentalPreceptAct {
    @Autowired
    private RentalPreceptAPI rentalPreceptAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = rentalPreceptAPI.sonPermission();
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

            Boolean isHasPermission = rentalPreceptAPI.guidePermission(guidePermissionTO);
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
     * 租房方案列表总条数
     *
     * @param rentalPreceptDTO 租房方案记录dto
     * @des 获取所有租房方案
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RentalPreceptDTO rentalPreceptDTO) throws ActException {
        try {
            Long count = rentalPreceptAPI.countRentalPrecept(rentalPreceptDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个租房方案
     *
     * @param id
     * @return class RentalPreceptVO
     * @des 获取一个租房方案
     * @version v1
     */
    @GetMapping("v1/precept/{id}")
    public Result precept(@PathVariable String id) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 租房方案列表
     *
     * @param rentalPreceptDTO 租房方案dto
     * @return class RentalPreceptVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(RentalPreceptDTO rentalPreceptDTO, HttpServletRequest request) throws ActException {
        try {
            List<RentalPreceptVO> rentalPreceptVOList = BeanTransform.copyProperties(
                    rentalPreceptAPI.findListRentalPrecept(rentalPreceptDTO), RentalPreceptVO.class, request);
            return ActResult.initialize(rentalPreceptVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加租房方案
     *
     * @param rentalPreceptTO 租房方案to
     * @return class RentalPreceptVO
     * @des 添加租房方案
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RentalPreceptTO rentalPreceptTO, BindingResult bindingResult) throws ActException {
        try {
            RentalPreceptBO preceptBO = rentalPreceptAPI.insertPecept(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(preceptBO, RentalPreceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑租房方案
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 编辑租房方案
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) RentalPreceptTO rentalPreceptTO, BindingResult bindingResult) throws ActException {
        try {
            RentalPreceptBO rentalPreceptBO = rentalPreceptAPI.editPecept(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(rentalPreceptBO, RentalPreceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除租房方案记录
     *
     * @param id 用户id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            rentalPreceptAPI.removePecept(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商务发展部意见
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 商务发展部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/businessAudit")
    public Result businessAudit(@Validated(RentalPreceptTO.TestBusiness.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.businessAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营财务部意见
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 运营财务部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/financeAudit")
    public Result financeAudit(@Validated(RentalPreceptTO.TestFinance.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.financeAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合资源部意见
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 综合资源部意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/resourceAudit")
    public Result resourceAudit(@Validated(RentalPreceptTO.TestResource.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.resourceAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 项目经理审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/manageAudit")
    public Result manageAudit(@Validated(RentalPreceptTO.TestManager.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.manageAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param rentalPreceptTO 租房方案数据bo
     * @return class RentalPreceptVO
     * @des 总经办审核
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/generalAudit")
    public Result generalAudit(@Validated(RentalPreceptTO.TestGeneral.class) RentalPreceptTO rentalPreceptTO) throws ActException {
        try {
            RentalPreceptBO bo = rentalPreceptAPI.generalAudit(rentalPreceptTO);
            return ActResult.initialize(BeanTransform.copyProperties(bo, RentalPreceptVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取编号
     *
     * @des 获取编号
     * @version v1
     */
    @GetMapping("v1/getNum")
    public Result getNum() throws ActException {
        try {
            List<String> list = rentalPreceptAPI.getNum();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据编号获取租房方案
     *
     * @param rentNum 租房编号
     * @return class RentalPreceptVO
     * @des 添加租房方案
     * @version v1
     */
    @GetMapping("v1/getRent")
    public Result getRent( String rentNum) throws ActException {
        try {
            RentalPreceptBO preceptBO = rentalPreceptAPI.getRent(rentNum);
            return ActResult.initialize(BeanTransform.copyProperties(preceptBO, RentalPreceptVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailAPI.findStatus(), PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = rentalPreceptAPI.getUser();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
