package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.DemandAPI;
import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.SonPermissionObject;
import com.bjike.goddess.businessinteraction.vo.DemandObjectVO;
import com.bjike.goddess.businessinteraction.vo.DemandVO;
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
 * 互动平台需求描述
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:21 ]
 * @Description: [ 互动平台需求描述 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("demand")
public class DemandAction {

    @Autowired
    private DemandAPI demandAPI;

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

            List<SonPermissionObject> hasPermissionList = demandAPI.sonPermission();
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

            Boolean isHasPermission = demandAPI.guidePermission(guidePermissionTO);
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
     * @param demandDTO 互动平台需求信息dto
     * @des 获取所有互动平台需求信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DemandDTO demandDTO) throws ActException {
        try {
            Long count = demandAPI.countInter(demandDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个互动平台需求
     *
     * @param id 互动平台需求信息id
     * @return class DemandVO
     * @des 根据id获取所有互动平台需求信息
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            DemandVO demandVOList = BeanTransform.copyProperties(
                    demandAPI.getOneById(id), DemandVO.class);
            return ActResult.initialize(demandVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 互动平台需求描述列表
     *
     * @param demandDTO 互动平台需求描述信息dto
     * @param request   前端过滤参数
     * @return class DemandVO
     * @des 获取所有互动平台需求描述信息
     * @version v1
     */
    @GetMapping("v1/listDemand")
    public Result findListDemand(DemandDTO demandDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DemandVO> demandVOList = BeanTransform.copyProperties(
                    demandAPI.listDemand(demandDTO), DemandVO.class, request);
            return ActResult.initialize(demandVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加互动平台需求描述
     *
     * @param demandTO 互动平台需求描述基本信息数据to
     * @return class DemandVO
     * @des 添加互动平台需求描述
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDemand(@Validated DemandTO demandTO, BindingResult bindingResult) throws ActException {
        try {
            DemandBO demandBO1 = demandAPI.addDemand(demandTO);
            return ActResult.initialize(BeanTransform.copyProperties(demandBO1, DemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑互动平台需求描述
     *
     * @param demandTO 互动平台需求描述基本信息数据bo
     * @return class DemandVO
     * @des 添加互动平台需求描述
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editDemand(@Validated DemandTO demandTO) throws ActException {
        try {
            DemandBO demandBO1 = demandAPI.editDemand(demandTO);
            return ActResult.initialize(BeanTransform.copyProperties(demandBO1, DemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除互动平台需求描述信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDemand(@PathVariable String id) throws ActException {
        try {
            demandAPI.deleteDemand(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 搜索查询
     *
     * @param demandDTO 互动平台需求描述信息dto
     * @return class DemandVO
     * @des 根据需求者姓名查询获取所有互动平台需求描述信息
     * @version v1
     */
    @GetMapping("v1/searchList")
    public Result searchList(@Validated({DemandDTO.TESTDemandDto.class}) DemandDTO demandDTO, BindingResult bindingResult) throws ActException {
        try {
            List<DemandVO> demandVOList = BeanTransform.copyProperties(
                    demandAPI.searchList(demandDTO), DemandVO.class);
            return ActResult.initialize(demandVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 搜索符合对象
     *
     * @param demandDTO 互动平台需求描述信息dto
     * @return class DemandVO
     * @des 有链接关系获取五大模块（市场信息-客户信息-竞争对手-供应商-商业能力）信息信息
     * @version v1
     */
    @GetMapping("v1/searchDemandFromOther")
    public Result searchDemand(@Validated({DemandDTO.TESTDemandDTO.class}) DemandDTO demandDTO, BindingResult bindingResult) throws ActException {
        try {
            List<DemandObjectVO> demandVOList = BeanTransform.copyProperties(
                    demandAPI.searchDemand(demandDTO), DemandObjectVO.class);
            return ActResult.initialize(demandVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}