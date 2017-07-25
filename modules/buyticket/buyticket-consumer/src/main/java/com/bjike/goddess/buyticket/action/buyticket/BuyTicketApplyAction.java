package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BuyTicketApplyAPI;
import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.excel.SonPermissionObject;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.buyticket.vo.BuyTicketApplyVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 车票购买申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyticketapply")
public class BuyTicketApplyAction {
    @Autowired
    private BuyTicketApplyAPI buyTicketApplyAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
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

            List<SonPermissionObject> hasPermissionList = buyTicketApplyAPI.sonPermission();
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
    public Result guidePermission(@Validated(BuyGuidePermissionTO.TestAdd.class) BuyGuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = buyTicketApplyAPI.guidePermission(guidePermissionTO);
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
     * 车票购买申请列表总条数
     *
     * @param buyTicketApplyDTO 车票购买申请dto
     * @des 获取所有车票购买申请总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyTicketApplyDTO buyTicketApplyDTO) throws ActException {
        try {
            Long count = buyTicketApplyAPI.countBuyTicketApply(buyTicketApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个车票购买申请
     *
     * @param id
     * @return class BuyTicketApplyVO
     * @des 获取一个车票购买申请
     * @version v1
     */
    @GetMapping("v1/apply/{id}")
    public Result apply(@PathVariable String id) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(buyTicketApplyBO, BuyTicketApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 车票购买申请列表
     *
     * @param buyTicketApplyDTO 车票购买申请dto
     * @return class BuyTicketApplyVO
     * @des 获取所有车票购买申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BuyTicketApplyDTO buyTicketApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<BuyTicketApplyVO> buyTicketApplyVOS = BeanTransform.copyProperties
                    (buyTicketApplyAPI.findListBuyTicketApply(buyTicketApplyDTO), BuyTicketApplyVO.class, request);
            return ActResult.initialize(buyTicketApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyVO
     * @des 添加车票购买申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BuyTicketApplyTO buyTicketApplyTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.insertBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyVO
     * @des 编辑车票购买申请
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BuyTicketApplyTO buyTicketApplyTO, BindingResult bindingResult) throws ActException {
        try {
            BuyTicketApplyBO buyTicketApplyBO = buyTicketApplyAPI.editBuyTicketApply(buyTicketApplyTO);
            return ActResult.initialize(buyTicketApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除车票购买申请
     *
     * @param id 用户id
     * @des 根据用户id删除车票购买申请记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            buyTicketApplyAPI.removeBuyTicketApply(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块审核意见
     *
     * @param id               车票购买申请id
     * @param planAuditOpinion 规划模块审核意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/planAuditOpinion/{id}")
    public Result planAuditOpinion(@PathVariable String id, @RequestParam(value = "planAuditOpinion") AuditType planAuditOpinion) throws ActException {
        try {
            buyTicketApplyAPI.planAuditBuyTicketApply(id, planAuditOpinion);
            return new ActResult("planAuditOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块审核意见
     *
     * @param id               车票购买申请id
     * @param welfAuditOpinion 规划模块审核意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/welfAuditOpinion/{id}")
    public Result welfAuditOpinion(@PathVariable String id, @RequestParam(value = "welfAuditOpinion") AuditType welfAuditOpinion) throws ActException {
        try {
            buyTicketApplyAPI.welfAuditBuyTicketApply(id, welfAuditOpinion);
            return new ActResult("welfAuditOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            detail = buyTicketApplyAPI.findAddAllDetails();
            return ActResult.initialize(detail);
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
            List<String> getPerson = new ArrayList<>();
            getPerson = buyTicketApplyAPI.findallMonUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}