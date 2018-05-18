package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountDepartmentAPI;
import com.bjike.goddess.financeinit.bo.AccountDepartmentBO;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.to.AccountDepartmentTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.AccountDepartmentVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 核算部门
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountdepartment")
public class AccountDepartmentAction {
    @Autowired
    private AccountDepartmentAPI accountDepartmentAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
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

            Boolean isHasPermission = accountDepartmentAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param accountDepartmentDTO 核算部门dto
     * @des 获取所有核算部门总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountDepartmentDTO accountDepartmentDTO) throws ActException {
        try {
            Long count = accountDepartmentAPI.countDepart(accountDepartmentDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个核算部门
     *
     * @param id 核算部门id
     * @return class AccountDepartmentVO
     * @des 根据id获取核算部门
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            AccountDepartmentVO accountDepartmentVO = BeanTransform.copyProperties(
                    accountDepartmentAPI.getOneById(id), AccountDepartmentVO.class);
            return ActResult.initialize(accountDepartmentVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 核算部门列表
     *
     * @param accountDepartmentDTO 核算部门dto
     * @return class AccountDepartmentVO
     * @des 获取所有核算部门
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(AccountDepartmentDTO accountDepartmentDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountDepartmentVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    accountDepartmentAPI.listDepart(accountDepartmentDTO), AccountDepartmentVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加核算部门
     *
     * @param accountDepartmentTO 核算部门数据to
     * @return class AccountDepartmentVO
     * @des 添加核算部门
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) AccountDepartmentTO accountDepartmentTO, BindingResult bindingResult) throws ActException {
        try {
            AccountDepartmentBO accountDepartmentBO = accountDepartmentAPI.addDepart(accountDepartmentTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountDepartmentBO, AccountDepartmentVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑核算部门
     *
     * @param accountDepartmentTO 核算部门数据bo
     * @return class AccountDepartmentVO
     * @des 编辑核算部门
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) AccountDepartmentTO accountDepartmentTO, BindingResult bindingResult) throws ActException {
        try {
            AccountDepartmentBO accountDepartmentBO = accountDepartmentAPI.editDepart(accountDepartmentTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountDepartmentBO, AccountDepartmentVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除核算部门
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            accountDepartmentAPI.deleteDepart(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
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
//            List<String> detail = new ArrayList<>();
//            detail = materialInStockAPI.findAddAllDetails();
//            return ActResult.initialize(detail);
            List<String> list = new ArrayList<>(0);
            if (moduleAPI.isCheck("organize")) {
                List<OpinionBO> opinionBOList = departmentDetailAPI.findThawOpinion();
                if (!CollectionUtils.isEmpty(opinionBOList)) {
                    list = opinionBOList.stream().map(OpinionBO::getValue).distinct().collect(Collectors.toList());
                }
            }
            return ActResult.initialize(list);
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
            List<String> userName=new ArrayList<>(0);
            if(moduleAPI.isCheck("organize")){
                List<UserBO> userBOS = positionDetailUserAPI.findUserList();
                if (!CollectionUtils.isEmpty(userBOS)) {
                    userName = userBOS.stream().map(UserBO::getUsername).distinct().collect(Collectors.toList());
                }
            }
            return ActResult.initialize(userName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}