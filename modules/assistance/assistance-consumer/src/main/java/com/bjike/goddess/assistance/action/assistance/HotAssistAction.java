package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.HotAssistAPI;
import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.HotAssistTO;
import com.bjike.goddess.assistance.vo.HotAssistVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 高温补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:52 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("hotassist")
public class HotAssistAction {

    @Autowired
    private HotAssistAPI hotAssistAPI;

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

            Boolean isHasPermission = hotAssistAPI.guidePermission(guidePermissionTO);
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
     *  总条数
     *
     * @param hotAssistDTO  高温补助信息dto
     * @des 获取所有高温补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HotAssistDTO hotAssistDTO) throws ActException {
        try {
            Long count = hotAssistAPI.countHotAssist(hotAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     *  一个
     *
     * @param id  高温补助id
     * @des 一个高温补助
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id ) throws ActException {
        try {
            HotAssistVO hotAssistVO = BeanTransform.copyProperties(
                    hotAssistAPI.getOneById(id), HotAssistVO.class);
            return ActResult.initialize(hotAssistVO );
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 获取所有高温补助信息
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/listHotAssist")
    public Result findListHotAssist(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.listHotAssist(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param hotAssistTO 高温补助基本信息数据to
     * @des 添加高温补助
     * @return  class HotAssistVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addHotAssist(@Validated(ADD.class) HotAssistTO hotAssistTO, BindingResult bindingResult) throws ActException {
        try {
            HotAssistBO hotAssistBO1 = hotAssistAPI.addHotAssist(hotAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(hotAssistBO1,HotAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param hotAssistTO 高温补助基本信息数据bo
     * @des 添加高温补助
     * @return  class HotAssistVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editHotAssist(@Validated(EDIT.class) HotAssistTO hotAssistTO) throws ActException {
        try {
            HotAssistBO hotAssistBO1 = hotAssistAPI.editHotAssist(hotAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(hotAssistBO1,HotAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除高温补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteHotAssist(@PathVariable String id) throws ActException {
        try {
            hotAssistAPI.deleteHotAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 地区汇总
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 根据地区汇总
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/collectByArea")
    public Result collectByArea(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.collectByArea(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param hotAssistDTO 高温补助信息dto
     * @des 获取所有高温补助信息
     * @return  class HotAssistVO
     * @version v1
     */
    @GetMapping("v1/collectByProGroup")
    public Result collectByProGroup(HotAssistDTO hotAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<HotAssistVO> hotAssistVOList = BeanTransform.copyProperties(
                    hotAssistAPI.collectByProGroup(hotAssistDTO), HotAssistVO.class, true);
            return ActResult.initialize(hotAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总需要的地区
     *
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/listAll/area")
    public Result listAllArea() throws ActException {
        try {
            List<String> users = hotAssistAPI.listAllArea();
            return ActResult.initialize(users);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总需要的项目组
     *
     * @des 获取所有项目组
     * @version v1
     */
    @GetMapping("v1/listAll/projectGroup")
    public Result listAllProject() throws ActException {
        try {
            List<String> users = hotAssistAPI.listAllProject();
            return ActResult.initialize(users);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    
}