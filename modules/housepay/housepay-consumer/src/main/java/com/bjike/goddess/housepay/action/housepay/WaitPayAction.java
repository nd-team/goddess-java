package com.bjike.goddess.housepay.action.housepay;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.api.WaitPayAPI;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.enums.PayStatus;
import com.bjike.goddess.housepay.excel.SonPermissionObject;
import com.bjike.goddess.housepay.to.CollectAreaTO;
import com.bjike.goddess.housepay.to.CollectProjectTO;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.housepay.to.WaitPayTO;
import com.bjike.goddess.housepay.vo.AreaCollectVO;
import com.bjike.goddess.housepay.vo.CollectDetailVO;
import com.bjike.goddess.housepay.vo.ProjectCollectVO;
import com.bjike.goddess.housepay.vo.WaitPayVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAction {
    @Autowired
    private WaitPayAPI waitPayAPI;
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

            List<SonPermissionObject> hasPermissionList = waitPayAPI.sonPermission();
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

            Boolean isHasPermission = waitPayAPI.guidePermission(guidePermissionTO);
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
     * 等待付款列表总条数
     *
     * @param waitPayDTO 等待付款记录dto
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WaitPayDTO waitPayDTO) throws ActException {
        try {
            waitPayDTO.getConditions().add(Restrict.eq("pay", PayStatus.NO));
            Long count = waitPayAPI.countWaitPay(waitPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个等待付款
     *
     * @param id
     * @return class WaitPayVO
     * @des 获取一个等待付款
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(waitPayBO, WaitPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 等待付款列表
     *
     * @param waitPayDTO 等待付款记录dto
     * @return class WaitPayVO
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WaitPayDTO waitPayDTO, HttpServletRequest request) throws ActException {
        try {
            waitPayDTO.getConditions().add(Restrict.eq("pay", PayStatus.NO));
            List<WaitPayVO> waitPayVOS = BeanTransform.copyProperties(
                    waitPayAPI.findListWaitPay(waitPayDTO), WaitPayVO.class, request);
            return ActResult.initialize(waitPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加等待付款
     *
     * @param waitPayTO 等待付款to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(WaitPayTO.TestAdd.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.insertWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(WaitPayTO.TestEdit.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.editWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除等待付款
     *
     * @param id 用户id
     * @des 根据用户id删除等待付款
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.removeWaitPay(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 付款
     *
     * @param id 付款id
     * @des 付款
     * @version v1
     */
    @PutMapping("v1/payment/{id}")
    public Result payment(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.payment(id);
            return ActResult.initialize("付款成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param to 地区
     * @return class AreaCollectVO
     * @des 地区汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(CollectAreaTO to) throws ActException {
        try {
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(
                    waitPayAPI.collectArea(to), AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总详情
     *
     * @param to 地区
     * @return class CollectDetailVO
     * @des 地区汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectAreaDetail")
    public Result collectAreaDetail(CollectAreaTO to) throws ActException {
        try {
            List<CollectDetailVO> collectDetailVOS = BeanTransform.copyProperties(
                    waitPayAPI.collectAreaDetail(to), CollectDetailVO.class);
            return ActResult.initialize(collectDetailVOS);
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
            List<String> areasList = waitPayAPI.getAreas();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目汇总
     *
     * @param to to
     * @return class ProjectCollectVO
     * @des 项目汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectProject")
    public Result collectProject(CollectProjectTO to) throws ActException {
        try {
            List<ProjectCollectVO> projectCollectVOS = BeanTransform.copyProperties(
                    waitPayAPI.collectProject(to), ProjectCollectVO.class);
            return ActResult.initialize(projectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目汇总详情
     *
     * @param to to
     * @return class CollectDetailVO
     * @des 项目汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectProjectDetail")
    public Result collectProjectDetail(CollectProjectTO to) throws ActException {
        try {
            List<CollectDetailVO> collectDetailVOS = BeanTransform.copyProperties(
                    waitPayAPI.collectProjectDatail(to), CollectDetailVO.class);
            return ActResult.initialize(collectDetailVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目
     *
     * @des 获取项目集合
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects() throws ActException {
        try {
            List<String> projectsList = waitPayAPI.getProject();
            return ActResult.initialize(projectsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有年份
     *
     * @des 获取所有年份
     * @version v1
     */
    @PostMapping("v1/year")
    public Result year() throws ActException {
        try {
            List<String> year = waitPayAPI.yearList();
            return ActResult.initialize(year);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算合计
     *
     * @des 根据房租, 水费, 电费, 管理费, 其他费用合计
     * @version v1
     */
    @PostMapping("v1/calculate")
    public Result calculate(@Validated(WaitPayTO.TestCalculate.class) WaitPayTO to, BindingResult bindingResult) throws ActException {
        try {
            Double total = waitPayAPI.calculate(to);
            return ActResult.initialize(total);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}