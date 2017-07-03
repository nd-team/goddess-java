package com.bjike.goddess.account.action.account;

import com.bjike.goddess.account.api.AccountInfoAPI;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.excel.SonPermissionObject;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.to.GuidePermissionTO;
import com.bjike.goddess.account.vo.AccountCollectVO;
import com.bjike.goddess.account.vo.AccountInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 明细账信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountinfo")
public class AccountInfoAction {
    @Autowired
    private AccountInfoAPI accountInfoAPI;
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

            List<SonPermissionObject> hasPermissionList = accountInfoAPI.sonPermission();
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

            Boolean isHasPermission = accountInfoAPI.guidePermission(guidePermissionTO);
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
     * 明细账信息列表总条数
     *
     * @param accountInfoDTO 明细账信息dto
     * @des 获取所有明细账信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountInfoDTO accountInfoDTO) throws ActException {
        try {
            Long count = accountInfoAPI.countAccountInfo(accountInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个明细账信息
     *
     * @param id
     * @return class AccountInfoVO
     * @des 获取一个明细账信息
     * @version v1
     */
    @GetMapping("v1/account/{id}")
    public Result account(@PathVariable String id) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(accountInfoBO, AccountInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 明细账信息列表
     *
     * @param accountInfoDTO 明细账信息dto
     * @return class AccountInfoVO
     * @des 获取所有明细账信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AccountInfoDTO accountInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties
                    (accountInfoAPI.findListAccountInfo(accountInfoDTO), AccountInfoVO.class, request);
            return ActResult.initialize(accountInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class AccountInfoVO
     * @des 添加明细账信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) AccountInfoTO accountInfoTO, BindingResult bindingResult) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.insertAccountInfo(accountInfoTO);
            return ActResult.initialize(accountInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class AccountInfoVO
     * @des 编辑明细账信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AccountInfoTO accountInfoTO, BindingResult bindingResult) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.editAccountInfo(accountInfoTO);
            return ActResult.initialize(accountInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除明细账信息
     *
     * @param id 用户id
     * @des 根据用户id删除明细账信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            accountInfoAPI.removeAccountInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 科目汇总
     *
     * @param dto 明细账信息dto
     * @return class AccountCollectVO
     * @des 在所有明细账信息进行科目汇总
     * @version v1
     */
    @GetMapping("v1/subCollect")
    public Result subCollect(@Validated AccountInfoDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<AccountCollectVO> accountCollectVOS = BeanTransform.copyProperties(
                    accountInfoAPI.subCollect(dto), AccountCollectVO.class, true);
            return ActResult.initialize(accountCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 地区汇总
     *
     * @param dto 明细账信息dto
     * @return class AccountCollectVO
     * @des 在所有明细账信息进行地区汇总
     * @version v1
     */
    @GetMapping("v1/areaCollect")
    public Result areaCollect(@Validated AccountInfoDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<AccountCollectVO> accountCollectVOS = BeanTransform.copyProperties(
                    accountInfoAPI.areaCollect(dto), AccountCollectVO.class, true);
            return ActResult.initialize(accountCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目名称汇总
     *
     * @param dto 明细账信息dto
     * @return class AccountCollectVO
     * @des 在所有明细账信息进行项目名称汇总
     * @version v1
     */
    @GetMapping("v1/nameCollect")
    public Result nameCollect(@Validated AccountInfoDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<AccountCollectVO> accountCollectVOS = BeanTransform.copyProperties(
                    accountInfoAPI.nameCollect(dto), AccountCollectVO.class, true);
            return ActResult.initialize(accountCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目组汇总
     *
     * @param dto 明细账信息dto
     * @return class AccountCollectVO
     * @des 在所有明细账信息进行项目组汇总
     * @version v1
     */
    @GetMapping("v1/groupCollect")
    public Result groupCollect(@Validated AccountInfoDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<AccountCollectVO> accountCollectVOS = BeanTransform.copyProperties(
                    accountInfoAPI.groupCollect(dto), AccountCollectVO.class, true);
            return ActResult.initialize(accountCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areasList = accountInfoAPI.getArea();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取项目名称
     *
     * @des 获取项目名称集合
     * @version v1
     */
    @GetMapping("v1/projectName")
    public Result projectName() throws ActException {
        try {
            List<String> projectNameList = accountInfoAPI.getProjectName();
            return ActResult.initialize(projectNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取项目组
     *
     * @des 获取项目组集合
     * @version v1
     */
    @GetMapping("v1/projectGroup")
    public Result projectGroup() throws ActException {
        try {
            List<String> projectGroupList = accountInfoAPI.getProjectGroup();
            return ActResult.initialize(projectGroupList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有一级科目
     *
     * @des 获取所有一级科目
     * @version v1
     */
    @GetMapping("v1/listFirstSubject")
    public Result listFirstSubject() throws ActException {
        try {
            List<String> userList = accountInfoAPI.listFirstSubject();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有二级科目
     *
     * @des 根据一级科目获取所有二级科目
     * @version v1
     */
    @GetMapping("v1/listSubByFirst")
    public Result listSubByFirst(@RequestParam String firstSub) throws ActException {
        try {
            List<String> userList = accountInfoAPI.listSubByFirst(firstSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有三级科目
     *
     * @des 根据一级二级科目获取所有三级科目
     * @version v1
     */
    @GetMapping("v1/listTubByFirst")
    public Result listTubByFirst(@RequestParam String firstSub, @RequestParam String secondSub) throws ActException {
        try {
            List<String> userList = accountInfoAPI.listTubByFirst(firstSub, secondSub);
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}