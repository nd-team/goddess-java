package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.AgeAssistAPI;
import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.vo.AgeAssistVO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.service.UserSetPermissionSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 工龄补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ageassist")
public class AgeAssistAction {

    @Autowired
    private AgeAssistAPI ageAssistAPI;
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

            List<SonPermissionObject> hasPermissionList = ageAssistAPI.sonPermission();
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

            Boolean isHasPermission = ageAssistAPI.guidePermission(guidePermissionTO);
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
     *  工龄补助列表总条数
     *
     * @param ageAssistDTO  工龄补助信息dto
     * @des 获取所有工龄补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AgeAssistDTO ageAssistDTO) throws ActException {
        try {
            Long count = ageAssistAPI.countAgeAssist(ageAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     *  一个工龄补助
     *
     * @param id  工龄补助id
     * @des 一个工龄补助
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id ) throws ActException {
        try {
            AgeAssistVO ageAssistVO = BeanTransform.copyProperties(
                    ageAssistAPI.getOneById(id), AgeAssistVO.class);
            return ActResult.initialize(ageAssistVO );
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工龄补助列表
     *
     * @param ageAssistDTO 工龄补助信息dto
     * @des 获取所有工龄补助信息
     * @return  class AgeAssistVO
     * @version v1
     */
    @GetMapping("v1/listAgeAssist")
    public Result findListAgeAssist(AgeAssistDTO ageAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AgeAssistVO> ageAssistVOList = BeanTransform.copyProperties(
                    ageAssistAPI.listAgeAssist(ageAssistDTO), AgeAssistVO.class, true);
            return ActResult.initialize(ageAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工龄补助
     *
     * @param ageAssistTO 工龄补助基本信息数据to
     * @des 添加工龄补助
     * @return  class AgeAssistVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAgeAssist(@Validated AgeAssistTO ageAssistTO, BindingResult bindingResult) throws ActException {
        try {
            AgeAssistBO ageAssistBO1 = ageAssistAPI.addAgeAssist(ageAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(ageAssistBO1,AgeAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑工龄补助
     *
     * @param ageAssistTO 工龄补助基本信息数据bo
     * @des 添加工龄补助
     * @return  class AgeAssistVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAgeAssist(@Validated AgeAssistTO ageAssistTO) throws ActException {
        try {
            AgeAssistBO ageAssistBO1 = ageAssistAPI.editAgeAssist(ageAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(ageAssistBO1,AgeAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除工龄补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAgeAssist(@PathVariable String id) throws ActException {
        try {
            ageAssistAPI.deleteAgeAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }



    
}