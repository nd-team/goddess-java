package com.bjike.goddess.checkhost.action.checkhost;

import com.bjike.goddess.checkhost.api.DormitoryInfoAPI;
import com.bjike.goddess.checkhost.bo.DormitoryInfoBO;
import com.bjike.goddess.checkhost.dto.DormitoryInfoDTO;
import com.bjike.goddess.checkhost.to.DormitoryInfoTO;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.vo.DormitoryInfoVO;
import com.bjike.goddess.checkhost.vo.SonPermissionObject;
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
 * 宿舍信息管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:02 ]
 * @Description: [ 宿舍信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dormitoryinfo")
public class DormitoryInfoAction {
    @Autowired
    private DormitoryInfoAPI dormitoryInfoAPI;
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

            List<SonPermissionObject> hasPermissionList = dormitoryInfoAPI.sonPermission();
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

            Boolean isHasPermission = dormitoryInfoAPI.guidePermission(guidePermissionTO);
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
     * 宿舍信息管理列表总条数
     *
     * @param dormitoryInfoDTO 宿舍信息管理dto
     * @des 获取所有宿舍信息管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DormitoryInfoDTO dormitoryInfoDTO) throws ActException {
        try {
            Long count = dormitoryInfoAPI.countDormitoryInfo(dormitoryInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个宿舍信息管理
     *
     * @param id
     * @return class DormitoryInfoVO
     * @des 获取一个宿舍信息管理
     * @version v1
     */
    @GetMapping("v1/dormitory/{id}")
    public Result dormitory(@PathVariable String id) throws ActException {
        try {
            DormitoryInfoBO dormitoryInfoBO = dormitoryInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(dormitoryInfoBO, DormitoryInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 宿舍信息列表
     *
     * @param dormitoryInfoDTO 宿舍信息dto
     * @return class DormitoryInfoVO
     * @des 获取所有宿舍信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DormitoryInfoDTO dormitoryInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<DormitoryInfoVO> dormitoryInfoVOS = BeanTransform.copyProperties
                    (dormitoryInfoAPI.findListDormitoryInfo(dormitoryInfoDTO),DormitoryInfoVO.class,request);
            return ActResult.initialize(dormitoryInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加宿舍信息
     *
     * @param dormitoryInfoTO 宿舍信息数据to
     * @return class DormitoryInfoVO
     * @des 添加宿舍信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) DormitoryInfoTO dormitoryInfoTO, BindingResult bindingResult) throws ActException {
        try {
            DormitoryInfoBO dormitoryInfoBO = dormitoryInfoAPI.insertDormitoryInfo(dormitoryInfoTO);
            return ActResult.initialize(dormitoryInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑宿舍信息
     *
     * @param dormitoryInfoTO 宿舍信息数据to
     * @return class DormitoryInfoVO
     * @des 编辑宿舍信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DormitoryInfoTO dormitoryInfoTO,BindingResult bindingResult) throws ActException {
        try {
            DormitoryInfoBO dormitoryInfoBO = dormitoryInfoAPI.editDormitoryInfo(dormitoryInfoTO);
            return ActResult.initialize(dormitoryInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除宿舍信息
     *
     * @param id 用户id
     * @des 根据用户id删除宿舍信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            dormitoryInfoAPI.removeDormitoryInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}