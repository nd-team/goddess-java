package com.bjike.goddess.secure.action.secure;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.vo.StaffRecordsVO;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
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
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.secure.api.AddEmployeeAPI;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.AddEmployeeVO;
import com.bjike.goddess.secure.vo.SonPermissionObject;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 社保增员信息名单
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("addemployee")
public class AddEmployeeAct {
    @Autowired
    private AddEmployeeAPI addEmployeeAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private StaffRecordsAPI staffRecordsAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission(HttpServletRequest request) throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                SonPermissionObject obj = new SonPermissionObject();
                obj.setName("cuspermission");
                obj.setDescribesion("设置");
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
                if (!isHasPermission) {
                    //int code, String msg
                    obj.setFlag(false);
                } else {
                    obj.setFlag(true);
                }
                list.add(obj);
            }
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

            List<SonPermissionObject> hasPermissionList = addEmployeeAPI.sonPermission();
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

            Boolean isHasPermission = addEmployeeAPI.guidePermission(guidePermissionTO);
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
     * 查找
     *
     * @param dto 增员分页信息
     * @param re  请求对象
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AddEmployeeDTO dto, HttpServletRequest re) throws ActException {
        try {
            List<AddEmployeeBO> list = addEmployeeAPI.find(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, AddEmployeeVO.class, re));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      增员信息id
     * @param request 请求对象
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/addemployee/{id}")
    public Result findByID(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AddEmployeeBO addEmployeeBO = addEmployeeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(addEmployeeBO, AddEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 增员名单信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AddEmployeeTO to, BindingResult result) throws ActException {
        try {
            addEmployeeAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 增员民单id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            addEmployeeAPI.delete(id);
            return new ActResult("delete SUCCESS!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部审核
     *
     * @param to 增员信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/commerceAudit")
    public Result commerceAudit(@Validated({AddEmployeeTO.AUDIT.class}) AddEmployeeTO to, BindingResult result) throws ActException {
        try {
            addEmployeeAPI.commerceAudit(to);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办确认新增
     *
     * @param id  增员id
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/managerConfirmAdd/{id}")
    public Result managerConfirmAdd(@Validated(AddEmployeeDTO.CONFIRM.class) AddEmployeeDTO dto, BindingResult result, @PathVariable String id) throws ActException {
        try {
            addEmployeeAPI.managerConfirmAdd(dto, id);
            return new ActResult("确认新增成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 社保管理负责人确认增员
     *
     * @param id 增员id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/confirmAdd/{id}")
    public Result confirmAdd(@PathVariable String id) throws ActException {
        try {
            addEmployeeAPI.confirmAdd(id);
            return new ActResult("确认增员成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AddEmployeeDTO dto) throws ActException {
        try {
            return ActResult.initialize(addEmployeeAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 增员信息
     * @return class AddEmployeeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) AddEmployeeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            AddEmployeeBO bo = addEmployeeAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, AddEmployeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有姓名和员工编号
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findUser")
    public Result findUser(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = positionDetailUserAPI.findUserListInOrgan();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门，项目组
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDepart")
    public Result findDepart(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = departmentDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有岗位和岗位层次
     *
     * @return class PositionDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findPosition")
    public Result findPosition(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名获取身份证号码，户籍地址，电话号码
     *
     * @param name 姓名
     * @return class StaffRecordsVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/userInfo/{name}")
    public Result userInfo(@PathVariable String name, HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("archive")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                List<StaffRecordsBO> list = staffRecordsAPI.listEmployee();
                if (null != list) {
                    for (StaffRecordsBO staffRecordsBO : list) {
                        if (name.equals(staffRecordsBO.getUsername())) {
                            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsBO, StaffRecordsVO.class, request));
                        }
                    }
                }
            }
            return ActResult.initialize(new ArrayList<>());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}