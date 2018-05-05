package com.bjike.goddess.bankrecords.action.account;

import com.bjike.goddess.bankrecords.api.BankAccountInfoAPI;
import com.bjike.goddess.bankrecords.dto.BankAccountInfoDTO;
import com.bjike.goddess.bankrecords.to.BankAccountInfoTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.bankrecords.to.SonPermissionObject;
import com.bjike.goddess.bankrecords.vo.BankAccountInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 账号信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bankaccountinfo")
public class BankAccountInfoAct {

    @Autowired
    private BankAccountInfoAPI bankAccountInfoAPI;


    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 新查询账户列表
     *
     * @param dto 账户信息
     * @return class BankAccountInfoVO
     * @version v1
     */
    @GetMapping("v1/findAlltoPage")
    public Result findAlltoPage(BankAccountInfoDTO dto)throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(bankAccountInfoAPI.findAlltoPage(dto),BankAccountInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 按搜索全局银行账户信息查询
     *
     * @param dto 账户信息
     * @return class BankAccountInfoVO
     * @version v1
     */
    @GetMapping("v1/listPage")
    public Result listPage(BankAccountInfoDTO dto,HttpServletRequest reques)throws ActException{
        try {
             List<BankAccountInfoVO> voList =BeanTransform.copyProperties(bankAccountInfoAPI.listPage(dto),BankAccountInfoVO.class,reques);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 新账户列表查询
     *
     * @return class BankAccountInfoVO
     * @version v1
     *
     */
    @GetMapping("v1/listAccount")
    public Result listAccount()throws ActException{
        try {
            return ActResult.initialize(BeanTransform.copyProperties(bankAccountInfoAPI.listAccount(),BankAccountInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            obj.setName("cusperzmission");
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

            List<SonPermissionObject> hasPermissionList = bankAccountInfoAPI.sonPermission();
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

            Boolean isHasPermission = bankAccountInfoAPI.guidePermission(guidePermissionTO);
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
     * 查询列表总条数
     *
     * @param dto 查询条件或分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BankAccountInfoDTO dto) throws ActException {
        try {
            Long count = bankAccountInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询银行流水
     *
     * @param id 竞争对手Id
     * @return class BankAccountInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result findByid(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.findById(id), BankAccountInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增账号信息
     *
     * @param to 账号信息信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) BankAccountInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.add(to), BankAccountInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑账号信息
     *
     * @param to 账号信息信息
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) BankAccountInfoTO to, BindingResult bindingResult) throws ActException {
        try {
            BankAccountInfoVO vo = BeanTransform.copyProperties(bankAccountInfoAPI.edit(to), BankAccountInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除账号信息
     *
     * @param id 账号信息ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bankAccountInfoAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(BankAccountInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<BankAccountInfoVO> voList = BeanTransform.copyProperties(bankAccountInfoAPI.pageList(dto), BankAccountInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}