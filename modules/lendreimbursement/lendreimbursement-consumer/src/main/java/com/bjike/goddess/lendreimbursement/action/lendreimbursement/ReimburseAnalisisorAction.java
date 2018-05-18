package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.ReimburseAnalisisorAPI;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAnalisisorBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAnalisisorDTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.LendPermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseAnalisisorTO;
import com.bjike.goddess.lendreimbursement.vo.ReimburseAnalisisorVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 报销分析人员表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimburseanalisisor")
public class ReimburseAnalisisorAction {

    @Autowired
    private ReimburseAnalisisorAPI reimburseAnalisisorAPI;

//    @Autowired
//    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private UserAPI userAPI;


    /**
     * 功能导航权限
     * @param lendGuidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(LendGuidePermissionTO.TestAdd.class) LendGuidePermissionTO lendGuidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {

//            Boolean isHasPermission = reimburseAnalisisorAPI.guidePermission(lendGuidePermissionTO);
//            if(! isHasPermission ){
                //int code, String msg
//                return new ActResult(0,"没有权限",false );
//            }else{
                return new ActResult(0,"有权限",true );
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
    }


    /**
     * 报销分析人员列表总条数
     *
     * @param reimburseAnalisisorDTO 报销分析人员信息dto
     * @des 获取所有报销分析人员信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws ActException {
        try {
            Long count = reimburseAnalisisorAPI.countReimburseAnalisisor(reimburseAnalisisorDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 报销分析人员列表
     *
     * @param reimburseAnalisisorDTO 报销分析人员信息dto
     * @return class ReimburseAnalisisorVO
     * @des 获取所有报销分析人员信息
     * @version v1
     */
    @GetMapping("v1/listReimburseAnalisisor")
    public Result findListReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ReimburseAnalisisorVO> reimburseAnalisisorVOList = BeanTransform.copyProperties(
                    reimburseAnalisisorAPI.listReimburseAnalisisor(reimburseAnalisisorDTO), ReimburseAnalisisorVO.class, true);
            return ActResult.initialize(reimburseAnalisisorVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个报销分析人员
     *
     * @param id 报销分析人员列表id
     * @return class ReimburseAnalisisorVO
     * @des 一个报销分析人员
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            ReimburseAnalisisorVO reimburseAnalisisorVOList = BeanTransform.copyProperties(
                    reimburseAnalisisorAPI.getOne(id), ReimburseAnalisisorVO.class);
            return ActResult.initialize(reimburseAnalisisorVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加报销分析人员
     *
     * @param reimburseAnalisisorTO 报销分析人员基本信息数据to
     * @return class ReimburseAnalisisorVO
     * @des 添加报销分析人员
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addReimburseAnalisisor( ReimburseAnalisisorTO reimburseAnalisisorTO, BindingResult bindingResult) throws ActException {
        try {
            ReimburseAnalisisorBO reimburseAnalisisorBO1 = reimburseAnalisisorAPI.addReimburseAnalisisor(reimburseAnalisisorTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseAnalisisorBO1, ReimburseAnalisisorVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑报销分析人员
     *
     * @param reimburseAnalisisorTO 报销分析人员基本信息数据bo
     * @return class ReimburseAnalisisorVO
     * @des 添加报销分析人员
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editReimburseAnalisisor( ReimburseAnalisisorTO reimburseAnalisisorTO) throws ActException {
        try {
            ReimburseAnalisisorBO reimburseAnalisisorBO1 = reimburseAnalisisorAPI.editReimburseAnalisisor(reimburseAnalisisorTO);
            return ActResult.initialize(BeanTransform.copyProperties(reimburseAnalisisorBO1, ReimburseAnalisisorVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除报销分析人员信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteReimburseAnalisisor(@PathVariable String id) throws ActException {
        try {
            reimburseAnalisisorAPI.deleteReimburseAnalisisor(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 所有报销分析人员
     *
     * @return class UserBO
     * @des 所有报销分析人员/获取组织结构所有用户
     * @version v1
     */
    @GetMapping("v1/listUser")
    public Result listUser() throws ActException {
        try {
//            List<UserBO> userList = positionDetailUserAPI.findUserListInOrgan();
            List<UserBO> userList = userAPI.findAllUser();
            return ActResult.initialize(userList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}