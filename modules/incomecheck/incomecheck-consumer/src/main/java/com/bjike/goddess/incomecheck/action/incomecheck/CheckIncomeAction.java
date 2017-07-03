package com.bjike.goddess.incomecheck.action.incomecheck;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.incomecheck.api.CheckIncomeAPI;
import com.bjike.goddess.incomecheck.bo.CheckIncomeBO;
import com.bjike.goddess.incomecheck.dto.CheckIncomeDTO;
import com.bjike.goddess.incomecheck.to.CheckIncomeTO;
import com.bjike.goddess.incomecheck.to.GuidePermissionTO;
import com.bjike.goddess.incomecheck.vo.CheckIncomeVO;
import com.bjike.goddess.incomecheck.vo.SonPermissionObject;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 收入核算资金回笼
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("checkincome")
public class CheckIncomeAction {

    @Autowired
    private CheckIncomeAPI checkIncomeAPI;
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

            List<SonPermissionObject> hasPermissionList = checkIncomeAPI.sonPermission();
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

            Boolean isHasPermission = checkIncomeAPI.guidePermission(guidePermissionTO);
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
     * @param checkIncomeDTO 收入核算信息dto
     * @des 获取所有收入核算信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            Long count = checkIncomeAPI.countCheckIncome(checkIncomeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收入核算
     *
     * @param id 收入核算信息id
     * @return class CheckIncomeVO
     * @des 根据id获取所有收入核算信息
     * @version v1
     */
    @GetMapping("v1/getIndexById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CheckIncomeVO checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.getOneById(id), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收入核算列表
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @param request        前端过滤参数
     * @return class CheckIncomeVO
     * @des 获取所有收入核算信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCheckIncome(CheckIncomeDTO checkIncomeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.listCheckIncome(checkIncomeDTO), CheckIncomeVO.class, request);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加收入核算
     *
     * @param checkIncomeTO 收入核算基本信息数据to
     * @return class CheckIncomeVO
     * @des 添加收入核算
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCheckIncome(@Validated(CheckIncomeTO.TestAdd.class) CheckIncomeTO checkIncomeTO, BindingResult bindingResult) throws ActException {
        try {
            CheckIncomeBO checkIncomeBO1 = checkIncomeAPI.addCheckIncome(checkIncomeTO);
            return ActResult.initialize(BeanTransform.copyProperties(checkIncomeBO1, CheckIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑收入核算
     *
     * @param checkIncomeTO 收入核算基本信息数据bo
     * @return class CheckIncomeVO
     * @des 编辑收入核算
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCheckIncome(@Validated(CheckIncomeTO.TestAdd.class) CheckIncomeTO checkIncomeTO) throws ActException {
        try {
            CheckIncomeBO checkIncomeBO1 = checkIncomeAPI.editCheckIncome(checkIncomeTO);
            return ActResult.initialize(BeanTransform.copyProperties(checkIncomeBO1, CheckIncomeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除收入核算信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCheckIncome(@PathVariable String id) throws ActException {
        try {
            checkIncomeAPI.deleteCheckIncome(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }


    /**
     * 根据地区汇总
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/ctArea")
    public Result collectCom(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.collectArea(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目组汇总
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctGroup")
    public Result ctGroup(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.collectGroup(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据项目汇总
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result collectPro(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.collectProject(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区差异分析
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据地区差异分析
     * @version v1
     */
    @GetMapping("v1/diffArea")
    public Result areaDiff(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.areaDiff(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组差异分析
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据项目组差异分析
     * @version v1
     */
    @GetMapping("v1/diffGroup")
    public Result groupDiff(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.groupDiff(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目差异分析
     *
     * @param checkIncomeDTO 收入核算信息dto
     * @return class CheckIncomeVO
     * @des 根据项目差异分析
     * @version v1
     */
    @GetMapping("v1/diffProject")
    public Result projectDiff(CheckIncomeDTO checkIncomeDTO) throws ActException {
        try {
            List<CheckIncomeVO> checkIncomeVOList = BeanTransform.copyProperties(
                    checkIncomeAPI.projectDiff(checkIncomeDTO), CheckIncomeVO.class);
            return ActResult.initialize(checkIncomeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有汇总地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result areaList() throws ActException {
        try {
            List<String> list = checkIncomeAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目组
     *
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result groupList() throws ActException {
        try {
            List<String> list = checkIncomeAPI.groupList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目
     *
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result projectList() throws ActException {
        try {
            List<String> list = checkIncomeAPI.projectList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}