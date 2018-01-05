package com.bjike.goddess.outcarfare.action.outcarfare;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.outcarfare.api.MoneyReadyAPI;
import com.bjike.goddess.outcarfare.bo.MoneyReadyBO;
import com.bjike.goddess.outcarfare.bo.MoneyReadyCountBO;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.MoneyReadyTO;
import com.bjike.goddess.outcarfare.vo.MoneyReadyCountVO;
import com.bjike.goddess.outcarfare.vo.MoneyReadyVO;
import com.bjike.goddess.outcarfare.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金准备审核
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyready")
public class MoneyReadyAct {
    @Autowired
    private MoneyReadyAPI moneyReadyAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;

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

            List<SonPermissionObject> hasPermissionList = moneyReadyAPI.sonPermission();
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

            Boolean isHasPermission = moneyReadyAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to      资金准备审核信息
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) MoneyReadyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MoneyReadyBO bo = moneyReadyAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 资金准备审核信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MoneyReadyTO to, BindingResult result) throws ActException {
        try {
            moneyReadyAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资金准备审核id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyReadyAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     资金准备审核分页信息
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyReadyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyBO> list = moneyReadyAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      资金准备审核id
     * @param request 请求对象
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/moneyready/{id}")
    public Result moneyready(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MoneyReadyBO bo = moneyReadyAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param dto dto
     * @return class MoneyReadyCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departCount")
    public Result departCount(@Validated(MoneyReadyDTO.DEPART.class) MoneyReadyDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyCountBO> list = moneyReadyAPI.departCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param dto dto
     * @return class MoneyReadyCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaCount")
    public Result areaCount(@Validated(MoneyReadyDTO.AREA.class) MoneyReadyDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyCountBO> list = moneyReadyAPI.areaCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除列表
     *
     * @param dto dto
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/delList")
    public Result delList(MoneyReadyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyBO> list = moneyReadyAPI.delList(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总明细
     *
     * @param dto dto
     * @return class MoneyReadyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/details")
    public Result details(MoneyReadyDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyBO> list = moneyReadyAPI.details(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, MoneyReadyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除列表总条数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/delCount")
    public Result delCount(MoneyReadyDTO dto) throws ActException {
        try {
            return ActResult.initialize(moneyReadyAPI.delCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 撤销删除
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/reback/{id}")
    public Result reback(@PathVariable String id) throws ActException {
        try {
            moneyReadyAPI.reback(id);
            return new ActResult("撤销删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countSum")
    public Result countSum(MoneyReadyDTO dto) throws ActException {
        try {
            return ActResult.initialize(moneyReadyAPI.countSum(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取年份下拉框
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/years")
    public Result years() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            int year = LocalDate.now().getYear();
            for (int i = year - 5; i <= year + 5; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取月份下拉框
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/months")
    public Result months() throws ActException {
        try {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                list.add(i);
            }
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目组
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departs")
    public Result departs() throws ActException {
        try {
            return ActResult.initialize(moneyReadyAPI.findAllGroupTeams());
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加编辑的所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总的所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listAreas")
    public Result listAreas() throws ActException {
        try {
            return ActResult.initialize(moneyReadyAPI.areas());
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
}